package com.controller.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.MemberDTO;
import com.service.MemberService;


@WebServlet("/MemberUpdateServlet")
public class MemberUpdateServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// MemberUpdateServlet
		// 1. 회원전용처리
		// 2. 회원인증된 경우
				//    : 파싱 => dto
				//    : MemberService.memberUpdate(dto) => dao.memberUpdate(dto)
				//      => MemberMapper.memberUpdate
				//      updatte 실행 후 갯수 sysout
		HttpSession session = request.getSession();
		MemberDTO dto = (MemberDTO) session.getAttribute("login");		
		String nextPage = null;
		if(dto != null) {	// 로그인 된경우
			request.setCharacterEncoding("UTF-8");
			String userid = request.getParameter("userid");
			String passwd = request.getParameter("passwd");	// null
			String username = request.getParameter("username");	// null
			String post = request.getParameter("post");
			String addr1 = request.getParameter("addr1");
			String addr2 = request.getParameter("addr2");
			String phone1 = request.getParameter("phone1");
			String phone2 = request.getParameter("phone2");
			String phone3 = request.getParameter("phone3");
			String email1 = request.getParameter("email1");
			String email2 = request.getParameter("email2");
			
			MemberDTO dto2 = 
					new MemberDTO(userid, passwd, username, post, addr1, addr2, phone1, phone2, phone3, email1, email2);
			System.out.println(dto2);
			MemberService service = new MemberService();
			int n = service.memberUpdate(dto2);
			// System.out.println("update 갯수"+n);
			if(n==1) {
				dto2 = service.mypage(userid);
				session.setAttribute("login", dto2);	// 세션에 최신정보 저장
				session.setAttribute("memberAdd", "회원정보가 수정되었습니다.");						
			}	
			nextPage = "main";
		} else {	// 로그인 안된경우 => LoginUIServlet
			nextPage = "LoginUIServlet";	
			request.setAttribute("mesg", "로그인이 필요한 작업입니다.");
		}
		//response.sendRedirect(nextPage);	// request가 있으므로 forward를 사용해야한다
		RequestDispatcher dis = request.getRequestDispatcher(nextPage);
		dis.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
