package com.servlet_test2.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.servlet_test2.dao.MemberDAO;
import com.servlet_test2.encrypt.AES256Cipher;
import com.servlet_test2.service.MemberService;
import com.servlet_test2.vo.MemberVO;


@Controller
public class UserController {

	@Inject
	MemberService service;

	@Inject
	MemberDAO dao;

	@ResponseBody
	@RequestMapping(value = "/user/login_action", method = RequestMethod.POST)
	public List<MemberVO> login_action(Locale locale, Model model, HttpServletRequest request) throws Exception {
		if (HomeController.sessionCheck(request))
			return null;

		String id = request.getParameter("id"), pw = request.getParameter("pw");

		MemberVO vo = new MemberVO();
		vo.setId(id);

		List<MemberVO> list = dao.login(vo);
		if (list != null) {
			System.out.println(AES256Cipher.AES_Decode(list.get(0).getPw()));
			String check_pw = AES256Cipher.AES_Decode(list.get(0).getPw());
			System.out.println(check_pw + "\t" + pw + "\t" + (check_pw == pw));
			if(check_pw.equals(pw)) {
				HttpSession ss = request.getSession();
				ss.setAttribute("list", list);
			}else {
				list = new ArrayList<>();
			}
		}

		return list;
	}

	@RequestMapping(value = "/user/join", method = RequestMethod.GET)
	public String join(Locale locale, Model model, HttpServletRequest request) throws Exception {

		return "join";
	}

	@ResponseBody
	@RequestMapping(value = "/user/join_action", method = RequestMethod.POST)
	public int join_action(Locale locale, Model model, HttpServletRequest request) throws Exception {
		String id = request.getParameter("id"), pw = request.getParameter("pw"), name = request.getParameter("name");

		MemberVO vo = new MemberVO();
		vo.setId(id);
		vo.setPw(AES256Cipher.AES_Encode(pw));
		vo.setName(name);
		System.out.println(name);
		return dao.joinAction(vo);
	}

	@RequestMapping(value = "/user/list", method = RequestMethod.GET)
	public String list(Locale locale, Model model, HttpServletRequest request) throws Exception {

		if (!HomeController.sessionCheck(request))
			model.addAttribute("msg", "fail");
		else
			model.addAttribute("msg", "success");

		List<MemberVO> memberList = service.allMember();

		model.addAttribute("memberList", memberList);

		return "list";
	}

	@ResponseBody
	@RequestMapping(value = "/user/select", method = RequestMethod.POST)
	public List<MemberVO> viewList(Locale locale, Model model, HttpServletRequest request) throws Exception {
		String value = request.getParameter("value");

		MemberVO vo = new MemberVO();
		vo.setValue(value);

		List<MemberVO> list = value == "" ? service.allMember() : service.selectMember(vo);
		model.addAttribute("memberList", list);
		for(MemberVO vo2 : list) {
				System.out.println(vo2.getDates());
		}
		return list;
	}
	
	@ResponseBody
	@RequestMapping(value = "/user/getUserData", method = RequestMethod.POST)
	public List<MemberVO> GetUserData(Locale locale, Model model, HttpServletRequest request) throws Exception {
		 int idx = Integer.parseInt(request.getParameter("idx"));
		 System.out.println(idx + "\t idx");
		 MemberVO vo = new MemberVO();
		 vo.setIdx(idx);

		 List<MemberVO> list = dao.getUserData(vo);
		 
		 for(MemberVO vo2 : list) {
			vo2.setPw(AES256Cipher.AES_Decode(vo2.getPw()));
		 }
		 
		 
		 return list;
	}

	@ResponseBody
	@RequestMapping(value = "/user/updateUserData", method = RequestMethod.POST)
	public int UpdateUserData(Locale locale, Model model, HttpServletRequest request) throws Exception {
		 String
		 id = request.getParameter("id"),
		 pw = request.getParameter("pw"),
		 name = request.getParameter("name");
		 
		 int idx = Integer.parseInt(request.getParameter("idx"));
		 
		 MemberVO vo = new MemberVO();
		 vo.setId(id);
		 vo.setPw(AES256Cipher.AES_Encode(pw));
		 vo.setName(name);
		 vo.setIdx(idx);
		 
		 int result = dao.updateUserData(vo);
		 return result;
	}

	@ResponseBody
	@RequestMapping(value = "/user/logout", method = RequestMethod.GET)
	public String logout(Locale locale, Model model, HttpServletRequest request) throws Exception {
		if (!HomeController.sessionCheck(request))
			return "fail";
		HttpSession ss = request.getSession();
		ss.setAttribute("list", null);

		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value = "/user/removeUserData", method = RequestMethod.POST)
	public String removeUserData(Locale locale, Model model, HttpServletRequest request) throws Exception {
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		 MemberVO vo = new MemberVO();
		 vo.setIdx(idx);
		 
		 int result = dao.removeUserData(vo);
		
		return "success";
	}

}
