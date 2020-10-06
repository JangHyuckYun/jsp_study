package com.servlet_test2.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.servlet_test2.vo.MemberVO;

import com.servlet_test2.utility.DBConnector;

public interface MemberDAO {
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	
	public List<MemberVO> getAdminData(MemberVO vo) throws Exception;
	
	public void addAdminData(MemberVO vo) throws Exception;
	
	public List<MemberVO> login(MemberVO vo) throws Exception;
	
	public int joinAction(MemberVO vo) throws Exception;
	
	public List<MemberVO> allMember() throws Exception;	
	
	public List<MemberVO> selectMember(MemberVO vo) throws Exception;	
	
	public List<MemberVO> getUserData(MemberVO vo) throws Exception;
	
	public int updateUserData(MemberVO vo) throws Exception;
	
	public int removeUserData(MemberVO vo) throws Exception;
	
}
