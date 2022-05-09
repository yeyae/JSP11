<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>검색 화면</title>
</head>
<body>
	<!-- 사용자에게 비밀번호를 입력받아서 비밀번호를 확인하는 요청 -->
	<h2>검색</h2>
	<form action="search" method="post">
		<input type="hidden" name="id" value="${param.id }"> 
		<!-- 이전 화면에서 주소와 동시에 넘겨준 파라미터를 사용
			사용자에게 보여줄 필요가 없는 값을 input type="hidden"
		 -->
		 검색어 입력 : 
		 <input type="text" name="name">
		 <input type="submit" value="확인">
	</form>
</body>
</html>