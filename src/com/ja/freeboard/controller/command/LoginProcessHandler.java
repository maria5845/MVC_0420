package com.ja.freeboard.controller.command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ja.freeboard.model.MemberDao;
import com.ja.freeboard.vo.MemberVo;
public class LoginProcessHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		String m_id = request.getParameter("m_id");
		String m_pw = request.getParameter("m_pw");
		MemberVo membervo =new MemberDao().selectbyIdAndPw(m_id, m_pw);
		
		
		if(membervo != null) {
		      
			request.getSession().setAttribute("sessionUserInfo", membervo);
			
		   	return "redirect:./main_page.do";
			//return "/WEB-INF/view/main_page.jsp"; 포워딩 하는 순간 리퀘스트 저장공간을 호출할 수가 없으므로 
		   	// 
		}else {
			return "/WEB-INF/view/login_fail.jsp";
		}
		
	
	}

}
