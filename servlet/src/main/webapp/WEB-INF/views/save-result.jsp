<%@page import="hello.servlet.domain.member.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
성공
<ul>
	<li>id=${member.id}</li>
	<li>username=${member.username}</li>
	<li>age=${member.age}</li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>