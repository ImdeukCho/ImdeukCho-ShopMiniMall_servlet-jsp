package com.controller.main;

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


@WebServlet("/main")	// main으로 주소변경, 다른 main.jsp들을 main주소로 변경해야 상품목록들이 출력된다.
public class MainServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. GoodsService.goodsList("top")
		//  => service.goodsList("top") => GoodsDAO.goodsList("top") 
		//  => GoodsMapper id="goodsList" select 시 gcategory로 select
		// 2. MainServlet sysout list 확인
		GoodsService service = new GoodsService();
		List<GoodsDTO> list = service.goodsList("top");
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
