package com.ja.freeboard.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ja.freeboard.model.*;
import com.ja.freeboard.vo.*;
public class ReadContentPageHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
            int b_no = Integer.parseInt(request.getParameter("b_no")); 
            BoardVo boardVo =new BoardDao().selectByNo(b_no);
            MemberVo memberVo =new MemberDao().selectByNo(boardVo.getM_no());
            ContentDataVo contentDataVo = new ContentDataVo(memberVo, boardVo);
            request.setAttribute("contentDataVo", contentDataVo);
            
		return "/WEB-INF/view/read_content_page.jsp";
	}

}
