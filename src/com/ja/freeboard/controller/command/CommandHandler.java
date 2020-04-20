package com.ja.freeboard.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 의존관계를 낮추고 표준을 맞추는 과정 
public interface CommandHandler {
	// 모든 핸들러가 인터페이스를 상속받아 오버라이딩 하기위한 인터페이스 생성 (의존도를 낮추기 위
	public String process(HttpServletRequest request, HttpServletResponse response);

}
