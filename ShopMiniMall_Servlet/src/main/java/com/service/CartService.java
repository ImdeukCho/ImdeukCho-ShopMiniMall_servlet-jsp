package com.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dao.CartDAO;
import com.dao.MemberDAO;
import com.dto.CartDTO;
import com.dto.GoodsDTO;
import com.dto.OrderDTO;

public class CartService {



	public List<CartDTO> cartList(String userid) {
		CartDAO dao = new CartDAO();
		 SqlSession session = MySqlSessionFactory.getSession();
		 List<CartDTO> list = null; 
		 try {
		      list = dao.cartList(session, userid);
		 }catch (Exception e) {
			e.printStackTrace();
	      }finally {
			session.close();
	      }
	      return list;
		}
	public int cartAdd(CartDTO dto) {
		CartDAO dao = new CartDAO();
		 SqlSession session = MySqlSessionFactory.getSession();
		 int n = 0; 
		 try {
	      n = dao.cartAdd(session, dto);
	       session.commit();	  
	      }finally {
			session.close();
	      }
	      return n;
		}
	public int cartDel(String num) {
		CartDAO dao = new CartDAO();
		 SqlSession session = MySqlSessionFactory.getSession();
		 int n = 0; 
		 try {
	      n = dao.cartDel(session, num);
	       session.commit();	  
	      }finally {
			session.close();
	      }
	      return n;
		}
	public int cartUpdate(HashMap<String, Integer> map) {
		CartDAO dao = new CartDAO();
		 SqlSession session = MySqlSessionFactory.getSession();
		 int n = 0; 
		 try {
	      n = dao.cartUpdate(session, map);
	       session.commit();	  
	      }finally {
			session.close();
	      }
	      return n;
		}
	public int cartAllDel(List<String> list) {
		CartDAO dao = new CartDAO();
		 SqlSession session = MySqlSessionFactory.getSession();
		 int n = 0; 
		 try {
	      n = dao.cartAllDel(session, list);
	       session.commit();	  
	      }finally {
			session.close();
	      }
	      return n;
		}
	public CartDTO cartbyNum(String num) {
		CartDAO dao = new CartDAO();
		 SqlSession session = MySqlSessionFactory.getSession();
		 CartDTO dto = null; 
		 try {
		      dto = dao.cartbyNum(session, num);
		 }catch (Exception e) {
			e.printStackTrace();
	      }finally {
			session.close();
	      }
	      return dto;
		}
	public int orderDone(OrderDTO dto, String cartNum) {
		 CartDAO dao = new CartDAO();
		 SqlSession session = MySqlSessionFactory.getSession();
		 int n = 0; 
		 try {
	      n = dao.orderDone(session, dto);	// order에 insert
	      System.out.println("insert"+n);
	      n = dao.cartDel(session, cartNum);
	      System.out.println("delete"+n);
	       session.commit();	  
	       System.out.println("commit 됨 ======");
		}catch (Exception e) {
			session.rollback();
			System.out.println("rollback 됨 ======="+e);
	    }finally {
			session.close();
	    }
	      return n;
		}

}
