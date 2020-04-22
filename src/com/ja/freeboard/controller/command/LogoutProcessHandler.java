package com.ja.freeboard.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutProcessHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		
		// 세션에 저장된 값을 제거시킴으로서 로그아웃역할을 함 
		
		request.getSession().invalidate();
		return "redirect:./main_page.do";
	}

}
