package com.controller.member;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.dto.MemberDTO;
import com.service.MemberService;

@WebServlet("/MemberAddServlet")
public class MemberAddServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. form데이터 파싱
		// dto 저장 후 dto sysout 확인
		// 2. MemberService.memverAdd(dto)
		// 3. MemberDAO.memberAdd() => id "mamberAdd"
		// insert 후 insert갯수 출력
		// 4. insert된 경우 mesg = "회원가입성공" => 세션에 저장
		//   => main.jsp로 위임
		// 5. main.jsp 회원가입 성공메세지 경고창에 출력
		request.setCharacterEncoding("UTF-8");
		String userid = request.getParameter("userid");
		String passwd = request.getParameter("passwd");
		String username = request.getParameter("username");
		String post = request.getParameter("post");
		String addr1 = request.getParameter("addr1");
		String addr2 = request.getParameter("addr2");
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		
		MemberDTO dto = 
				new MemberDTO(userid, passwd, username, post, addr1, addr2, phone1, phone2, phone3, email1, email2);

		MemberService service = new MemberService();
		int n = service.memberAdd(dto);
		System.out.println("insert 갯수"+n);
		
		if(n==1) {
			HttpSession session = request.getSession();
			session.setAttribute("memberAdd",userid+"님 회원가입성공");
			session.setMaxInactiveInterval(60*30);
			response.sendRedirect("main");	
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
