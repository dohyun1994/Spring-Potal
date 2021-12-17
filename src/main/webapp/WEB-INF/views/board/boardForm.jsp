<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="../home/menu.jsp"/>
<div align="center">
	<div><h1>게시글 작성</h1></div>
	<div>
		<form id="frm" action="boardInsert.do" method="post">
			<div>
				<table border="1">
					<tr>
						<th width="100">제 목</th>
						<td width="300">
							<input type="text" id="title" name="title" size="37" required="required">
						</td>
						<th width="100">작성일자</th>
						<td width="150">
							<input type="date" id="wdate" name="wdate" required="required">
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td colspan="3">
							<textarea rows="10" cols="77" id="subject" name="subject"></textarea>
						</td>
					</tr>
				</table>
			</div><br>
			<div>
				<input type="submit" value="등록">&nbsp;&nbsp;
				<input type="reset" value="취소">&nbsp;&nbsp;
				<input type="button" onclick="location.href='noticeList.do'" value="목록">
			</div>
		</form>
	</div>
</div>
</body>
</html>