package com.servlet_test2.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.servlet_test2.dao.MemberDAO;
import com.servlet_test2.encrypt.AES256Cipher;
import com.servlet_test2.vo.MemberVO;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
	
	@Inject
	MemberDAO dao;

	@ModelAttribute
	public void addBugetToModel(Model model, HttpServletRequest request) throws Exception {
		
		MemberVO vo = new MemberVO();
		vo.setId("id");
		vo.setPw(AES256Cipher.AES_Encode("1234"));
		vo.setName("°ü¸®ÀÚ");
		
		System.out.println("configconfig");
		System.out.println(dao.getAdminData(vo));
		if(dao.getAdminData(vo) == null) dao.addAdminData(vo);

        model.addAttribute("test", (HomeController.sessionCheck(request) ? "success" : "fail") );
    }
}
