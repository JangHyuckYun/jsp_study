package com.first_project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.first_project.vo.MemberVO;

import con.first_project.utility.DBConnector;

public class MemberDAO {
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	
	public HashMap<String, String> login_action(MemberVO vo) {
		HashMap<String, String> result = null;
		try {
			conn = DBConnector.connection();
			String 
			sql = "SELECT * FROM users WHERE id = ? AND pw = ?",
			id = vo.getId(),
			pw = vo.getPw();
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.setString(2, pw);
			
			rs = stmt.executeQuery();
			if(rs.next()) {
				result = new HashMap<>();
				
				result.put("id",rs.getString("id"));
				result.put("pw",rs.getString("pw"));
				result.put("dates",rs.getString("dates"));
				result.put("name",rs.getString("name"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnector.close(conn, stmt, rs);
		}
		
		return result;
	}
	
	
	
}
