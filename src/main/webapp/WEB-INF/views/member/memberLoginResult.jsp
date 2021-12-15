<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="../home/menu.jsp" />
<div align="center">
	<div>
	<c:if test= "${name != null}">		<!--  로그인 실패 -->
		<h1>${name } ${message }</h1>	
	</c:if>	
	<c:if test="${name eq null }">		<!--  로그인 성공 -->
		<h1>${message }</h1>
	</c:if>
	</div>
</div>
</body>
</html>