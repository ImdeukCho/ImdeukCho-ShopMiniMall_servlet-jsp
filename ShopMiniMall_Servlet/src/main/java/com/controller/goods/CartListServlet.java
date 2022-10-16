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

@WebServlet("/CartListServlet")
public class CartListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// CartListServlet
		// 회원전용처리
		// 회원인 경우 : 사용자 id이용
		// CartService.cartList => Dao.cartList => CartMapper.cartList
		// id에 해당하는 주문목록 select 
		// list sysout 확인
		
		HttpSession session = request.getSession();
		MemberDTO dto = (MemberDTO) session.getAttribute("login");		
		String nextPage = null;
		
		if(dto != null) {	// 로그인 된경우
	 		String userid = dto.getUserid();	// 세션에서 id가져오기
			CartService service = new CartService();
			List<CartDTO> list = service.cartList(userid);
			// System.out.println(list);
			request.setAttribute("cartList", list);
			nextPage = "cartList.jsp";
			
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
