package com.ja.freeboard.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginPageHandler implements CommandHandler {
	// 인터페이스를 상속받는 과정
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {

		// 포워딩 과 리다이렉트는 각 핸들러 자바에서 선택 ! -> 이것을 ControllerUsing으로 값을 리턴함
		return "/WEB-INF/view/login_page.jsp";

	}
}
