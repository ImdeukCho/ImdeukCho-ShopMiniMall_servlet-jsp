package com.controller.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.MemberDTO;
import com.service.MemberService;


@WebServlet("/MyPageServlet")
public class MyPageServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// MyPageServlet
		// 1. 회원전용처리 -> 회원이 아닌 경우 loginForm
		// 2. 회원인증된 경우
		//    : 세션에서 사용자 id 얻기
		//    : MemberService.mypage(userid) => dao.mypage(userid)
		//      => MemberMapper.mypage 사용자 정보 select
		//      sysout 확인
		// 세션 정보 덮어쓰기
		// => myPage.jsp로 데이터 저장(세션이용) 이동, 세션정보 출력
		HttpSession session = request.getSession();
		MemberDTO dto = (MemberDTO) session.getAttribute("login");		
		String nextPage = null;
		if(dto != null) {	// 로그인 된경우
			// 세션사용자정보 말고 더 최신의 db사용자정보를 가져오자.
			nextPage = "myPage.jsp";
			String userid = dto.getUserid();
			
			MemberService service = new MemberService();
			MemberDTO dto2 = service.mypage(userid);
			// System.out.println(dto2);
			session.setAttribute("login", dto2);	// 세션데이터 덮어쓰기			
		} else {	// 로그인 안된경우 => LoginUIServlet
			nextPage = "LoginUIServlet";		
		}
		response.sendRedirect(nextPage);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
