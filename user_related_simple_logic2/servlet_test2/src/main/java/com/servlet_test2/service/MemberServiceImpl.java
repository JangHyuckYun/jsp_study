package com.servlet_test2.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.servlet_test2.dao.MemberDAO;
import com.servlet_test2.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {
	@Inject
    private MemberDAO dao;
	
	 @Override
	    public List<MemberVO> allMember() throws Exception {
	 
	        return dao.allMember();
	    }
    
    @Override
    public List<MemberVO> selectMember(MemberVO vo) throws Exception {
        return dao.selectMember(vo);
    }
}
