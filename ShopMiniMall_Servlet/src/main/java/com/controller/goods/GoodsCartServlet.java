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

@WebServlet("/GoodsCartServlet")
public class GoodsCartServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 로그인처리
		// 로그인 된 경우
		// form 데이터 파싱 7개, + 세션에서 사용자 id => cartDTO
		// cartDTO sysout 출력
		// CartService.cartAdd(dto) => CartDAO.cartAdd(dto) => CartMapper.cartAdd
		// 테이블에 insert, insert 갯수 sysout
		HttpSession session = request.getSession();
		MemberDTO dto = (MemberDTO) session.getAttribute("login");		
		String nextPage = null;
		if(dto != null) {	// 로그인 된경우
			request.setCharacterEncoding("UTF-8");
			String gCode = request.getParameter("gCode"); 
	 		String gName = request.getParameter("gName");  
	 		String gPrice = request.getParameter("gPrice"); 
	 		String gSize = request.getParameter("gSize");
	 		String gColor = request.getParameter("gColor");
	 		String gAmount = request.getParameter("gAmount");
	 		String gImage = request.getParameter("gImage"); 
	 		String userid = dto.getUserid();	// 세션에서 id가져오기
			
	 		// int num = 0;
			// CartDTO xx = new CartDTO(num, userid, gCode, gName, Integer.parseInt(gPrice), gSize, gColor, Integer.parseInt(gAmount), gImage);			
	 		CartDTO xx  = new CartDTO();
	  		xx.setgImage(gImage);
	  		xx.setgCode(gCode);
	  		xx.setgName(gName);
	  		xx.setgPrice(Integer.parseInt(gPrice));
	  		xx.setgSize(gSize);
	  		xx.setgColor(gColor);
	  		xx.setgAmount(Integer.parseInt(gAmount));
	  		xx.setUserid(userid);
	 		
	 		CartService service = new CartService();
			int n = service.cartAdd(xx);
			System.out.println("insert 갯수 "+n);
			
			nextPage = "GoodsRetrieveServlet?gCode="+gCode;	// gCode를 넘겨주기위해 sendRedirect가 편하다. 장바구니에 계속 담을때 오류방지 : 데이터 유지되지않음, url이 바뀜
			session.setAttribute("mesg", gCode+"Cart 저장성공");	// sendRedirect를 사용했기때문에 request에 담지못하므로 세션에 저장한다.

		} else {	// 로그인 안된경우 => LoginUIServlet
			nextPage ="LoginUIServlet";
			session.setAttribute("mesg", "로그인이 필요한 작업입니다.");
		}
		response.sendRedirect(nextPage);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
