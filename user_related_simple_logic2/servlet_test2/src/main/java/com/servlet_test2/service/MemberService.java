package com.servlet_test2.service;

import java.util.List;

import com.servlet_test2.vo.MemberVO;

public interface MemberService {
	
	public List<MemberVO> allMember() throws Exception;

	public List<MemberVO> selectMember(MemberVO vo) throws Exception;
	
}
