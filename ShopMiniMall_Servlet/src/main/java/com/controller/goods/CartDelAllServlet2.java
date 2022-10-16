package com.controller.goods;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.MemberDTO;
import com.service.CartService;

@WebServlet("/CartDelAllServlet2")
public class CartDelAllServlet2 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 로그인처리
		HttpSession session = request.getSession();
		MemberDTO dto = (MemberDTO) session.getAttribute("login");		
		String nextPage = null;		
		if(dto != null) {	// 로그인 된경우
		
			String[] data = request.getParameterValues("check");	// form데이터의 name값이 key값이이된다.
			System.out.println(data);	// 배열의 주소 출력 , [Ljava.lang.String;@3fa61e2d
			for (String s : data) {	// foreach문 돌려 각 배열값 출력
				System.out.println(s);	// 818283 주문번호3개 넘어옴
				
			}
			List<String> list = Arrays.asList(data);	// 배열의 주소를 바로 리스트에 저장가능
			System.out.println(list);	// [81, 82, 83]
			CartService service = new CartService();
			int n = service.cartAllDel(list);
			System.out.println(n+"개 전체삭제");
			
			nextPage = "CartListServlet";
			
		} else {	// 로그인 안된경우 => LoginUIServlet
			nextPage = "LoginUIServlet";
			session.setAttribute("mesg", "로그인이 필요한 작업입니다.");
		}
		response.sendRedirect(nextPage);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
