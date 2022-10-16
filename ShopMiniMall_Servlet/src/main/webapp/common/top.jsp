<%@ page import="com.dto.MemberDTO" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
MemberDTO dto = (MemberDTO)session.getAttribute("login");
// 로그인시 회원인증 후 login 데이터 세션에 저장

if(dto != null){
		String username = dto.getUsername();
%>
안녕하세요. <%= username %>님
	<a href="LogoutServlet">로그아웃</a>	
	<a href="MyPageServlet">mypage</a>
	<a href="CartListServlet">장바구니</a>
<% }else{ %>
	<a href="LoginUIServlet">로그인</a>
	<a href="MemberUIServlet">회원가입</a>	<!-- mvc 패턴 지키기 -->
<% } // end if~else %>	
