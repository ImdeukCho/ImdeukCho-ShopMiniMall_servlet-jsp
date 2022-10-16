<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {

		$("form").submit(function () {
			var userid = $("#userid").val();
			var passwd = $("#passwd").val();
				if(userid.length ==0){
					alert("아이디를 입력하세요");
					$("#userid").focus();
					event.preventDefault();
				} else if (passwd.length ==0){
					alert("비밀번호를 입력하세요");
					$("#passwd").focus();
					event.preventDefault();
				}
		});
	});
</script>
<form action="LoginServlet" method="post">
	아이디: <input type="text" name="userid" id="userid"><br>
	비밀번호: <input type="text" name="passwd" id="passwd"><br>
	<input type="submit" value="로그인">
	<input type="reset" value="취소">
</form>