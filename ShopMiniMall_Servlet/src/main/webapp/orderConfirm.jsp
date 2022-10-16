<%@page import="com.dto.CartDTO"%>
<%@page import="com.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>주문 확인 화면입니다.</h1>
	<!-- flush="true" 변경사항이 페이지에 바로바로 적용되게함 -->
	<jsp:include page="common/top.jsp" flush="true" /><br>
	<jsp:include page="common/menu.jsp" flush="true" />
	<hr>
	<jsp:include page="order/orderConfirm.jsp" flush="true" />
	
</body>
</html>