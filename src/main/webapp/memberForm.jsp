<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type= "text/javascript">
	function fn_sendMember () {
		// 이 페이지의 form
		var frmMember = document.frmMember;
		
		var id = frmMember.id.value;
		var pwd = frmMember.pwd.value;
		var name = frmMember.name.value;
		var email = frmMember.email.value;
		
		if (id.length == 0 || id == "") {
			alert("아이디를 입력해주세요.")
		} else if (pwd.length == 0 || pwd == "") {
			alert("비밀번호를 입력해주세요.")
		} else if (name.length == 0 || name == "") {
			alert("이름을 입력해주세요.")
		} else if (email.length == 0 || email == "") {
			alert("이메일를 입력해주세요.")
		} else {
			// form의 method, action 을 JS에서 처리
			frmMember.method="post";
			frmMember.action="member.do";
			
			frmMember.submit();
		}
		
	}
</script>
<title>Insert title here</title>
</head>
<body>
<h2>회원 가입 폼</h2>
<!--
	원래 form에 action과 method 속성을 선언해준다.
	아래 방식은 action과 method를 JS에서 처리한것
-->
<form name = "frmMember">
	<table>
		<tr>
			<td>아이디 : </td>
			<td><input type="text" name="id" /></td>
		</tr>
		<tr>
			<td>비밀번호 : </td>
			<td><input type="password" name="pwd" /></td>
		</tr>
		<tr>
			<td>이름 : </td>
			<td><input type="text" name="name" /></td>
		</tr>
		<tr>
			<td>이메일 : </td>
			<td><input type="email" name="email" /></td>
		</tr>
		
		<tr>
			<td colspan="2" align="center">
				<input type="button" value="가입" onclick="fn_sendMember()" />
				<input type="reset" value="다시입력" />
				
				<input type="hidden" name="command" value="addMember" />
			</td>
		</tr>
	</table>
</form>

</body>
</html>