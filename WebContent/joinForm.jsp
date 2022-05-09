<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

input[type="text"]{border: 2px solid gray; 
}

input[type="password"]{border: 2px solid gray; 
}

input[type="submit"]{border: 2px solid #A3D64F;
background-color: #A3D64F;
opacity: 0.8;
}
input[type="reset"]{border: 2px solid #FA5F5F;
background-color: #FA5F5F;
opacity: 0.8;
}

.center{
position: absolute;
left: 50%;
top: 50%;
transform: translate(-50%, -50%);
}
</style>
</head>
<body>
	<form action="join" method="post" class="center">
		<p>아이디 : <input type="text" name="userid"/></p>
		<p>비밀번호 : <input type="password" name="userpw"/></p>
		<p>이름 : <input type="text" name="name"/></p>
		<p>이메일 : <input type="text" name="email"/></p>
		<input type="submit" value ="회원가입"/>
		<input type="reset" value ="다시작성"/>
	</form> 
</body>
</html>