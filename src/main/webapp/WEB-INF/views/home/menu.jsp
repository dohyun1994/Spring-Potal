<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/menu.css">
</head>
<body>
	<div align="center">
		<div>
			<br>
		</div>
		<div>
			<!-- 메뉴부분 -->
			<ul>
				<li><a class="active" href="home.do">Home</a></li>
				<c:if test="${id eq null}">			<!--  로그인이 안되었으면. -->
					<li><a href="loginForm.do">Login</a></li>	
				</c:if>
				
				<c:if test="${id != null }">		<!--  로그인이 되었으면. -->
					<li><a href="memberLogout.do">LogOut</a></li>		<!--  로그아웃 페이지를 보여줘야 된다. -->
					<li><a href="#">Contact</a></li>		<!--  게시판 -->
					<li><a href="noticeList.do">Notice</a></li>		<!--  로그인 되어있을 때 글쓰기 권한 설정. -->
					<li><a href="#">Product</a></li>
					<li><a href="#">Service</a></li>
				</c:if>
				
				<c:if test="${id eq null }">
					<li><a href="noticeList.do">Notice</a></li>
				</c:if>
				
				<c:if test="${author eq 'ADMIN' }">			<!--  author가 'ADMIN'이면 Members 버튼을 보여줘라. -->
					<li><a href="#about">Members</a></li>
				</c:if>		
			</ul>
		</div>
	</div>
</body>

</html>