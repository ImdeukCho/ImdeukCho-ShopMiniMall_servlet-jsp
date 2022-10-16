package com.controller.goods;

import java.io.IOException;
import java.util.HashMap;
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

@WebServlet("/CartUpdateServlet")
public class CartUpdateServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// CartUpdateServlet
		// 1. 회원전용처리
		// 2. 회원인증 후
		// map(주문번호, 수량) => CartService.cartUpdate => DAO.cartUpdate => CartMapper.cartUpdate update후
		// 수정 갯수 확인
		
		HttpSession session = request.getSession();
		MemberDTO dto = (MemberDTO) session.getAttribute("login");		
		String nextPage = null;
		
		if(dto != null) {	// 로그인 된경우
			String num = request.getParameter("num");
			String gAmount = request.getParameter("gAmount");
			System.out.println(num+"\t"+gAmount);
			
			HashMap<String, Integer> map = new HashMap<>();
			map.put("num", Integer.parseInt(num));
			map.put("gAmount", Integer.parseInt(gAmount));
			
			CartService service = new CartService();
			int n = service.cartUpdate(map);
			System.out.println("수정된 갯수"+n);
			nextPage = "CartListServlet";
			
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
