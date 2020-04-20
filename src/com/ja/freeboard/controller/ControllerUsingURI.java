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
		commandfactory = new CommandFactory();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// (1) 넘어온 명령어 확인 코드
		String command = request.getRequestURI();
		// 문자를 자르는 API 잘써야함 ^^
		// request.getContextPath().length() 컨텍스트 명이 가변적이 때문에 이렇게 잘라야함.
		command = command.substring(request.getContextPath().length());
		System.out.println("넘어온 명령어 :" + command);

		// (2) 명령어 호출된 값을 가져오는 코드
		CommandHandler handler = commandfactory.getCommandHandler(command);
		// 가져온 핸들러의 프로세스를 실행
		// url에 null값이 입력되는 것을 방지하기 위해 예외처리 진행
		String view = null;
		if (handler != null) {
			view = handler.process(request, response);
		} else {
			System.out.println("[경고] 명령어에 매핑된 객체가 없습니다.");
		}
		// 뷰는 2가지 경우로 나뉘어 진행 : 리다이렉트 혹은 포워드
		if (view != null) {
			// 페이지를 리다이렉팅 시킬 것이냐?
			if (view.startsWith("redirect : ")) { // startsWith : 문자열의 시작할때 포함되는 지 확인하는 API
				view.substring("redirect  : ".length()); // 넘어오는 문자열 값을 잘라서 리다이렉트 시키는 작업

				response.sendRedirect(view);
			} else {
				// 페이지를 포워딩 시킬것
				RequestDispatcher dispatcher = request.getRequestDispatcher(view);
				dispatcher.forward(request, response);
			}
		}

	}

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
