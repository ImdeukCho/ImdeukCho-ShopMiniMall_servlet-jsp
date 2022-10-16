package com.controller.goods;

import java.io.IOException;
import java.util.List;

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


@WebServlet("/CartDelServlet")
public class CartDelServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// CartDelServlet
		// 1. 회원전용처리
		// 2. 회원인 경우
		// 	 num값 파싱 => CartService.cartDel => DAO.cartDel => CartMapper.cartDel 이용 삭제
		//   삭제 갯수 확인
		
		HttpSession session = request.getSession();
		MemberDTO dto = (MemberDTO) session.getAttribute("login");		
		String nextPage = null;		
		if(dto != null) {	// 로그인 된경우
	 		String num = request.getParameter("num");
			System.out.println("삭제한 주문번호"+num);
			CartService service = new CartService();
			int n = service.cartDel(num);
			System.out.println("삭제된 갯수"+n);
			nextPage = "CartListServlet";
			
		} else {	// 로그인 안된경우 => LoginUIServlet
			nextPage = "LoginUIServlet";
			session.setAttribute("mesg", "로그인이 필요한 작업입니다.");
		}
		response.sendRedirect(nextPage);
//		RequestDispatcher dis = request.getRequestDispatcher(nextPage);
//		dis.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
