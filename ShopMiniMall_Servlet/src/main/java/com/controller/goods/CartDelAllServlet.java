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

@WebServlet("/CartDelAllServlet")
public class CartDelAllServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 로그인처리
		HttpSession session = request.getSession();
		MemberDTO dto = (MemberDTO) session.getAttribute("login");		
		String nextPage = null;		
		if(dto != null) {	// 로그인 된경우
		// cartlist에서 넘겨준 주문번호들을 파싱해서 출력
			String data = request.getParameter("data");
			System.out.println(data);	// "10,20,21"
			
			// 연결된 데이터를 ,로 나눠 list로 저장
			String [] x = data.split(",");	// ,콤마로 구분해서 배열생성
			List<String> list = Arrays.asList(x);
			System.out.println(list);	// [10, 20, 21 ]
			// asList  
			//  - 배열을 List로 변환
			//  - asList로 만들어진 List는 원소 추가(add)불가 및 값 변경 시 원본 배열 값도 변경됨  
			//  - 원소 추가 및 원본 배열 유지를 위해선 new List 사용
			
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
