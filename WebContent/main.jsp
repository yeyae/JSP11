<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인</title>
<style>

button{
border: 2px solid yellowgreen;
background-color: yellowgreen;
}
</style>
</head>
<body>
<h1>🍀회원가입에 성공한 자🍀</h1>
<h3><%= session.getAttribute("userid") %> 님 반갑습니다. </h3>
<button onclick="location.href='memberList'">회원목록 보기</button>
<button onclick="location.href='modifyForm'">내 정보 수정</button>
<button onclick="location.href='logout'">로그 아웃</button>
<button onclick="location.href='messageList'">글 목록</button>
<button onclick="location.href='search.jsp'">검색</button>
</body>
</html>