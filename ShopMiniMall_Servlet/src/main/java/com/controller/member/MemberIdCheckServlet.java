package com.controller.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.service.MemberService;


@WebServlet("/MemberIdCheckServlet")
public class MemberIdCheckServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userid= request.getParameter("userid");
		// System.out.println(userid);
		
		MemberService service = new MemberService();
		int count = service.idCheck(userid);
		// System.out.println("아이디 중복갯수"+count);
		
		
		// out.print한 데이터가 ajax data로 넘어간다.
		String mesg = "아이디 사용 가능";
		if(count ==1) {
			mesg = "아이디 중복";
		}
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		out.print(mesg);	// 응답 메세지 전송
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
