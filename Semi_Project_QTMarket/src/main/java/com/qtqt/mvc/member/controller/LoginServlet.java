package com.qtqt.mvc.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qtqt.mvc.member.model.service.MemberService;
import com.qtqt.mvc.member.model.vo.Member;

@WebServlet(name="login", urlPatterns = "/QT/signinup/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private MemberService service = new MemberService();
	
    public LoginServlet() {
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = null;
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String saveId = request.getParameter("saveId");
		
		System.out.println(userId + ", " + userPwd + ", " + saveId);
		
		Member loginMember = service.login(userId, userPwd);
		
		System.out.println(loginMember);
		
		if(saveId != null) {
			Cookie cookie = new Cookie("saveId", userId);
			
			cookie.setMaxAge(259200);
			response.addCookie(cookie);
		} else {
			Cookie cookie = new Cookie("saveId", "");
			
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}

		if(loginMember != null) {
			session = request.getSession();
			
			session.setAttribute("loginMember", loginMember);
			
			response.sendRedirect(request.getContextPath() + "/");
			
		} else {
			request.setAttribute("msg", "아이디나 비밀번호가 일치하지 않습니다.");
			request.setAttribute("location", "/views/member/signinup.jsp");
			
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
		
		
		
	}

}
