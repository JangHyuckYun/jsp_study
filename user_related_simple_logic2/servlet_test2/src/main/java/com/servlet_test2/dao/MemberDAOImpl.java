package com.servlet_test2.dao;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
 
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
 
import com.servlet_test2.vo.MemberVO;
 
@Repository
public class MemberDAOImpl implements MemberDAO {
 
    @Inject
    private SqlSession sqlSession;
    
    private static final String Namespace = "com.servlet_test2.mappers.memberMapper";
    
    @Override
    public List<MemberVO> getAdminData(MemberVO vo) throws Exception {
    	return sqlSession.selectList(Namespace+".login", vo);
    }
    
    @Override
    public void addAdminData(MemberVO vo) throws Exception {
    	sqlSession.insert(Namespace+".joinAction", vo);
    }
    
    @Override
    public List<MemberVO> allMember() throws Exception {
 
        return sqlSession.selectList(Namespace+".allMember");
    }
    
    @Override
    public List<MemberVO> selectMember(MemberVO vo) throws Exception {

        return sqlSession.selectList(Namespace+".selectMember", vo);
    }
    
    @Override
    public List<MemberVO> login(MemberVO vo) {
    	System.out.println("값 최동 전달됨" + "\t" + vo.getId() + "\t" + vo.getPw() );
    	return sqlSession.selectList(Namespace+".login", vo);
    }
    
    @Override
    public int joinAction(MemberVO vo) {
    	
    	int result = sqlSession.insert(Namespace+".joinAction", vo);
    	return result;
    }
    
    @Override
    public List<MemberVO> getUserData(MemberVO vo) throws Exception {
    	System.out.println(vo.getIdx());
    	return sqlSession.selectList(Namespace+".getUserData", vo);
    }
    
    @Override
    public int updateUserData(MemberVO vo) throws Exception {
    	System.out.println("媛믪씠 �뱾�뼱媛붿뼱�쑀");
    	int result = sqlSession.update(Namespace+".updateUserData", vo);
    	
    	return result;
    }
    
    @Override
    public int removeUserData(MemberVO vo) throws Exception {
    	System.out.println("媛믪씠 �뱾�뼱媛붿뼱�쑀2");
    	int result = sqlSession.delete(Namespace+".deleteUserData", vo);
    	
    	return result;
    }
 
}
 