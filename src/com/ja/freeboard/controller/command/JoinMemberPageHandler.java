package com.ja.freeboard.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JoinMemberPageHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "/WEB-INF/view/join_member_page.jsp";
	}
 
	
}
