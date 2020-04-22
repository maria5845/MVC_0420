package com.ja.freeboard.controller;

// 팩토리 : 공장 (인스턴스를 찍어내는 역할 ) 
// 한번에 생성하는 작업 
import com.ja.freeboard.controller.command.*;
import java.util.*;

public class CommandFactory {
	// 호출될때마다 각 인터페이스를 찍어냄
	// 내부에서 리턴할 용도로 사용될 예정이기 때문에 private 선언
	private HashMap<String, CommandHandler> commandMap;

	public CommandFactory() {
		commandMap = new HashMap<String, CommandHandler>();
		commandMap.put("/login_page.do", new LoginPageHandler());
        commandMap.put("/Join_member_page.do",new JoinMemberPageHandler());
        commandMap.put("/Join_member_process.do",new JoinMemberProcessHandler()); 
        commandMap.put("/login_process.do",new LoginProcessHandler());
        commandMap.put("/main_page.do", new MainPageHandler());
        commandMap.put("/logout_process.do",new LogoutProcessHandler());
        commandMap.put("/write_content_page.do",new WriteContentPageHandler());
        commandMap.put("/write_content_process.do",new WriteContentProcessHandler());
        commandMap.put("/read_content_page.do",new ReadContentPageHandler());
        commandMap.put("/delete_content_process.do",new DeleteContentProcessHandler());
        commandMap.put("/update_content_page.do",new UpdateContentPageHandler());
        commandMap.put("/update_content_process.do",new UpdateContentProcessHandler());
        
	}

	public CommandHandler getCommandHandler(String command) {
		return commandMap.get(command);
	}
}
