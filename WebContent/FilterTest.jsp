<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>멀티 테스트 입니다.</title>
</head>
<body>
<h1>요청받았습니다!</h1>
id 파라미터 : <%=request.getParameter("id") %><br>
name 파라미터 : <%=request.getParameter("name") %><br>
pay 파라미터 : <%=request.getParameter("pay") %><br>
</body>
</html>