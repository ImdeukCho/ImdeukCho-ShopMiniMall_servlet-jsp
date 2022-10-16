package com.controller.goods;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.MemberDTO;
import com.dto.OrderDTO;
import com.service.CartService;

@WebServlet("/CartOrderDoneServlet")
public class CartOrderDoneServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



		// CartOrderDoneServlet
			// 회원전용처리
			
			HttpSession session = request.getSession();
			MemberDTO dto = (MemberDTO) session.getAttribute("login");		
			String nextPage = null;
			
			if(dto != null) {	// 로그인 된경우
				String userid = dto.getUserid();
				String gCode = request.getParameter("gCode");
				String gName = request.getParameter("gName");
				String gPrice = request.getParameter("gPrice");
				String gSize = request.getParameter("gSize");
				String gColor = request.getParameter("gColor");
				String gAmount = request.getParameter("gAmount");
				String gImage = request.getParameter("gImage");
				String orderName = request.getParameter("orderName");
				String post = request.getParameter("post");
				String addr1 = request.getParameter("addr1");
				String addr2 = request.getParameter("addr2");
				String phone = request.getParameter("phone");
				String payMethod = request.getParameter("payMethod");

				// orderinfo insert 자료
				OrderDTO dto2 = new OrderDTO(0, userid, gCode, gName, 
						Integer.parseInt(gPrice), gSize, gColor, 
						Integer.parseInt(gAmount), gImage, 
						orderName, post, addr1, addr2, phone, 
						payMethod, null );	// orderday는 sql문에서 오늘날짜로 입력할 예정
				
				
				String cartNum = request.getParameter("cartNum");	// 장바구니 삭제 번호
				
				System.out.println("CartOrderDoneServlet.dto2 : "+dto2);	// order테이블에 insert
				System.out.println("CartOrderDoneServlet.cartNum : "+cartNum);	// cart테이블에서 delete
				
				CartService service = new CartService();
				int n = service.orderDone(dto2, cartNum);// tx처리
				System.out.println("수정된 갯수"+n);
				
				request.setAttribute("orderDTO", dto2);	// 주문번호 확인용
				nextPage = "orderDone.jsp";
				
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
