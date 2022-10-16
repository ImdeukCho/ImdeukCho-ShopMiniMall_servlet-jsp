package com.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dao.MemberDAO;
import com.dto.MemberDTO;

public class MemberService {

	public int memberAdd(MemberDTO dto) {
		MemberDAO dao = new MemberDAO();
		 SqlSession session = MySqlSessionFactory.getSession();
		 int n = 0; 
		 try {
	      n = dao.memberAdd(session, dto);
	       session.commit();	  
	      }finally {
			session.close();
	      }
	      return n;
		}

	public int idCheck(String userid) {
		MemberDAO dao = new MemberDAO();
		 SqlSession session = MySqlSessionFactory.getSession();
		 int n = 0; 
		 try {
		      n = dao.idCheck(session, userid);
		       session.commit();	  
		      }finally {
				session.close();
		      }
		      return n;
			}

	public MemberDTO login(HashMap<String,String> map) {
		MemberDAO dao = new MemberDAO();
		 SqlSession session = MySqlSessionFactory.getSession();
		 MemberDTO dto = null; 
		 try {
		      dto = dao.login(session, map);
		 }catch (Exception e) {
			e.printStackTrace();
	      }finally {
			session.close();
	      }
	      return dto;
		}

	public MemberDTO mypage(String userid) {
		MemberDAO dao = new MemberDAO();
		 SqlSession session = MySqlSessionFactory.getSession();
		 MemberDTO dto = null; 
		 try {
			 dto = dao.mypage(session, userid);
		 }catch (Exception e) {
			e.printStackTrace();
	      }finally {
			session.close();
	      }
	      return dto;
		}

	public int memberUpdate(MemberDTO dto) {
		MemberDAO dao = new MemberDAO();
		 SqlSession session = MySqlSessionFactory.getSession();
		 int n = 0; 
		 try {
		      n = dao.memberUpdate(session, dto);
		       session.commit();	  
		      }finally {
				session.close();
		      }
		      return n;
			}

}
