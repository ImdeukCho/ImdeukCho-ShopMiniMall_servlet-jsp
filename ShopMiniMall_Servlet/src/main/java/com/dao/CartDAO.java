package com.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.dto.CartDTO;
import com.dto.GoodsDTO;
import com.dto.OrderDTO;

public class CartDAO {

	public List<CartDTO> cartList(SqlSession session, String userid) {
		List<CartDTO> list =  session.selectList("CartMapper.cartList", userid );
		return list;
	}
	
	public int cartAdd(SqlSession session, CartDTO dto) {
		int n =  session.insert("CartMapper.cartAdd" , dto );
		return n;
	}

	public int cartUpdate(SqlSession session, HashMap<String, Integer> map) {
		int n =  session.update("CartMapper.cartUpdate" , map );
		return n;
	}
	
	public int cartDel(SqlSession session, String num) {
		int n =  session.delete("CartMapper.cartDel" , num );
		return n;
	}

	public int cartAllDel(SqlSession session, List<String> list) {
		int n =  session.delete("CartMapper.cartAllDel" , list );
		return n;
	}

	public CartDTO cartbyNum(SqlSession session, String num) {
		CartDTO dto =  session.selectOne("CartMapper.cartbyNum", num );
		return dto;
	}

	public int orderDone(SqlSession session, OrderDTO dto) {
		int n =  session.insert("CartMapper.orderDone" , dto );
		System.out.println("CartDAO.orderDone insert ==== "+n);
		return n;
	}

}
