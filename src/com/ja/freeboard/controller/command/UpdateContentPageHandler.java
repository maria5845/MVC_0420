package com.ja.freeboard.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ja.freeboard.model.*;
import com.ja.freeboard.vo.*;
public class UpdateContentPageHandler implements  CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		int b_no = Integer.parseInt(request.getParameter("b_no"));
		String b_title = request.getParameter("b_title");
		String b_content = request.getParameter("b_content");
		BoardVo boardVo =new BoardDao().selectByNo(b_no);
		MemberVo memberVo = new MemberDao().selectByNo(boardVo.getM_no());
		ContentDataVo contentVo = new ContentDataVo(memberVo, boardVo);
		request.setAttribute("contentVo", contentVo);
		

		return "/WEB-INF/view/update_content_page.jsp";
	}

}
