<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>게시글수정</h1>
<form action="./update_content_process.do" method="post">
 작성자 : ${contentVo.memberVo.m_nick}<br>
 제목 : <input type="text" value="${contentVo.boardVo.b_title}" name="b_title"><br>
 내용 : <br>
 <textarea rows="10" cols="40" name="b_content">${contentVo.boardVo.b_content}</textarea><br>
 <br>
 <input type="hidden" value="${contentVo.boardVo.b_no}" name="b_no"> 
 <input type="submit" value="글수정">

</form>
</body>
</html>