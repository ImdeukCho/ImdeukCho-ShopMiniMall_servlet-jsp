package com.controller.goods;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.GoodsDTO;
import com.service.GoodsService;

@WebServlet("/GoodsListServlet")
public class GoodsListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// menu.jsp에서 넘어오는 gCategory 데이터 파싱
		String gCategory =  request.getParameter("gCategory");
		// System.out.println(gCategory);
		// gCategory값이 null일경우 기본값 top으로 설정
		if(gCategory == null) {
			gCategory="top";
		}

		GoodsService service = new GoodsService();
		List<GoodsDTO> list = service.goodsList(gCategory);
		// System.out.println(list);
		
		// goodsList 키로 list를 request에 저장 후 main.jsp로 forward
		request.setAttribute("goodsList", list);
		RequestDispatcher dis = request.getRequestDispatcher("main.jsp");
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
