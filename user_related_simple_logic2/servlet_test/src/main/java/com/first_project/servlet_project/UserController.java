/**
 * 
 */
package com.first_project.servlet_project;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.first_project.dao.MemberDAO;
import com.first_project.vo.MemberVO;

/**
 * @author yunjanghyeog
 *
 */
@Controller
public class UserController {
	
	
	@ResponseBody
	@RequestMapping(value="/user/login_action", method=RequestMethod.POST)
	public String login_action(Locale locale, Model model, HttpSession session) {
		HttpServletRequest request = null;
		HashMap<String, String> list;
		String result = "fail";
		String id = (String) session.getAttribute("user"), pw = "";
		if(id != null) return "user/list";
		else {
			id = request.getParameter("id");
			pw = request.getParameter("pw");
			
			MemberDAO dao = new MemberDAO();
			MemberVO vo = new MemberVO();
			vo.setId(id);
			vo.setPw(pw);
			list = dao.login_action(vo);
			
			if(list != null) {
				session = request.getSession();
				session.setAttribute("user", list);
			}
		}
		
		return result;
	}
	
	@RequestMapping(value="/user/join", method=RequestMethod.GET)
	public String Join(Locale locale, Model model, HttpSession session) {
		String id = (String) session.getAttribute("user");
		if(id != null) return "home";
		else return "join";
	}
}
