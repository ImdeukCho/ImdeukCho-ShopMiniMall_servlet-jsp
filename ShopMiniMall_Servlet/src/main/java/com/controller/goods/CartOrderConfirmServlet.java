package com.controller.goods;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.CartDTO;
import com.dto.MemberDTO;
import com.service.CartService;
import com.service.MemberService;


@WebServlet("/CartOrderConfirmServlet")
public class CartOrderConfirmServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// CartDelServlet
		// 1. 회원전용처리
		// 2. 회원인 경우
		// 	 num(주문한 주문번호)값 파싱 => CartService.cartbyNum(카트번호)
		//     => dao.cartbyNum(카트번호) => CartMapper.cartbyNum(카트번호)
		// 카트 번호 CartDTO Sysout 확인
		// 세션에서 사용자 id => service.mypage 이용 사용자 정보 => memberDTO
		
		HttpSession session = request.getSession();
		MemberDTO dto = (MemberDTO) session.getAttribute("login");		
		String nextPage = null;		
		
		if(dto != null) {	// 로그인 된경우
		String num = request.getParameter("num");	// Cart번호
		System.out.println("주문한 주문번호"+num);
		CartService service = new CartService();
		CartDTO cDTO = service.cartbyNum(num);
		System.out.println("주문한 상품정보"+cDTO);	// Cart 정보조회
		
		String userid = dto.getUserid();	// 사용자id
		MemberService service2 = new MemberService();
		MemberDTO mDTO = service2.mypage(userid);
		System.out.println(mDTO);	// 사용자 정보 조회
		
		request.setAttribute("cDTO", cDTO);	// 상품주문정보
		request.setAttribute("mDTO", mDTO); // 고객정보

			nextPage = "orderConfirm.jsp";

		} else {	// 로그인 안된경우 => LoginUIServlet
			nextPage = "LoginUIServlet";
			session.setAttribute("mesg", "로그인이 필요한 작업입니다.");
		}
		
		RequestDispatcher dis = request.getRequestDispatcher(nextPage);
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
