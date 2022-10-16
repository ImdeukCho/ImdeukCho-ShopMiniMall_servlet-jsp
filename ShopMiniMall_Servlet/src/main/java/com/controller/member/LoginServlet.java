package com.controller.member;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.MemberDTO;
import com.service.MemberService;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// LoginServlet
		// 1. 파싱
		// 2. select문 실행 => 1 또는 null
		// 3. 1인 경우 : 세션에 회원정보저장 => main.jsp로 이동 (세션을 검사 후 메뉴를 다르게 출력)
		//    null인 경우 : 로그인화면으로 이동
		String userid = request.getParameter("userid");
		String passwd = request.getParameter("passwd");
		// System.out.println(userid+"\t"+passwd);
		
		HashMap<String, String> map = new HashMap<>();
		map.put("userid", userid);
		map.put("passwd", passwd);
		
		MemberService service = new MemberService();
		MemberDTO dto = service.login(map);
		// System.out.println(dto);
		
		
		String nextPage=null;
		if(dto != null) {	// 인증완료
			nextPage = "main";
			HttpSession session = request.getSession();
			session.setAttribute("login", dto);	// 인증정보 저장 후 회원전용페이지에서 활용
			session.setMaxInactiveInterval(60*60);
		} else {	// 회원아닌 경우
			nextPage = "LoginUIServlet";	// 모든 요청은 컨트롤러롤 통해서하자.
		}
		response.sendRedirect(nextPage);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
