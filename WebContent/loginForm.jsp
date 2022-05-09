<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
input[type="text"]{
border:2px solid white;
color: #F06EA3;
}
input[type="text"]:hover{
border: 2px solid #A35374;

}
input[type="submit"]{
background-color: #DED602;
border: 2px solid #DED602;
color: white;
}
input[type="button"]{
background-color: #FC945D;
border: 2px solid #FC945D;
color: white;
}
.center{
position: absolute;
left: 50%;
top: 50%;
transform: translate(-50%, -50%);
}
.change{
color: #DB798A;
}
</style>

</head>
<body>
<span class="change">
	<form action="login" method="post" class="center">
		<p>아이디 : <input type="text" name="userid"></p>
		<p>비밀번호 : <input type="password" name="userpw"></p>
		<input type="submit" value="로그인">
		<input type="button" value="회원가입" onclick
		="location.href='joinForm'">
	</form>
	</span>
</body>
</html>