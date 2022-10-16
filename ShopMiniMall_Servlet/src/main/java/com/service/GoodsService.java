package com.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dao.GoodsDAO;
import com.dto.GoodsDTO;

public class GoodsService {

	public List<GoodsDTO> goodsList(String gCategory) {
		GoodsDAO dao = new GoodsDAO();
		 SqlSession session = MySqlSessionFactory.getSession();
		 List<GoodsDTO> list = null; 
		 try {
		      list = dao.goodsList(session, gCategory);
		 }catch (Exception e) {
			e.printStackTrace();
	      }finally {
			session.close();
	      }
	      return list;
		}

	public GoodsDTO goodsRetrieve(String gCode) {
		GoodsDAO dao = new GoodsDAO();
		 SqlSession session = MySqlSessionFactory.getSession();
		 GoodsDTO dto = null; 
		 try {
			 dto = dao.goodsRetrieve(session, gCode);
		 }catch (Exception e) {
			e.printStackTrace();
	      }finally {
			session.close();
	      }
	      return dto;
		}

}
