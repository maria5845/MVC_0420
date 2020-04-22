package com.ja.freeboard.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Redirect;

import com.ja.freeboard.controller.command.CommandHandler;
// MVC 1번째 UsingURI 서블릿을 만들어서 분기를 나눠준다! 
/**
 * Servlet implementation class ControllerUsingURI
 */
//@WebServlet("/ControllerUsingURI") // web.xml에서 대신 설정 
    public class ControllerUsingURI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommandFactory commandfactory;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerUsingURI() {
		super();
		// 커맨드 팩토리먼저 제작하고 생성 
		commandfactory = new CommandFactory();
		
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	
		// 1. 받아온 명령어를 참조한다. 
		String command = request.getRequestURI();
		
		// request.getContextPath().length() 컨텍스트 명이 가변적이 때문에 이렇게 잘라야함.
		// 결과는 ...do로 날라옴 컨테스트패스에 랭스만큼만 자르면 됩니다. 
		// 문자의 길이(int값)을 반환해줌 
		command = command.substring(request.getContextPath().length());
		
		// 적절한 로그를 찍어주자! 
		System.out.println("넘어온 명령어 :" + command);
		
		// 2. 핸들러들이 담겨있는 커맨드 팩토리에서 커맨드를 앱력받아 핸들러에 저장한다. 
		// 다형성에 의해서 적합한 핸들러가 들어가게 된다.
		CommandHandler handler = commandfactory.getCommandHandler(command);
		
		
		
		// 3 .가져온 핸들러의 프로세스를 실행
		// url에 null값이 입력되는 것을 방지하기 위해 예외처리 진행
		String view = null;
		
		if (handler != null) { // 핸들러값이 null이 아니면 
			// 어떤 핸들러가 들어왔느냐에 따라 분기되어 나타남 
			view = handler.process(request, response);
		} else {
			// 없다면 로그를 띄워주자! 
			System.out.println("[경고] 명령어에 매핑된 객체가 없습니다.");
		}
		// 4. 뷰는 2가지 경우로 나뉘어 진행 : 리다이렉트 혹은 포워드
		if (view != null) {
			// 페이지를 리다이렉팅 시킬 것이냐?[리다이렉트 쓰는 법]
			
			if (view.startsWith("redirect:")) { // startsWith : 문자열의 시작할때 포함되는 지 확인하는 API
				view = view.substring("redirect:".length()); // 넘어오는 문자열 값을 잘라서 리다이렉트 시키는 작업
				// .do로 리다이렉트를 보낸다. 
				response.sendRedirect(view);
			} else {
				// 페이지를 포워딩 시킬것 리퀘스트 객체는 남아있음 
				RequestDispatcher dispatcher = request.getRequestDispatcher(view);
				dispatcher.forward(request, response);
			}
		}

	}
     // 이후 
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
