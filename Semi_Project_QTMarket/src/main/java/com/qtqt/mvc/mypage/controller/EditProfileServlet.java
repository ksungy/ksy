package com.qtqt.mvc.mypage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qtqt.mvc.member.model.vo.Member;


@WebServlet("/QT/editprofile")
public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EditProfileServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false); 
    	Member loginMember = session != null ? (Member) session.getAttribute("loginMember") : null;
    	
    	if(loginMember != null) {    		
    		request.getRequestDispatcher("/views/mypage/editprofile.jsp").forward(request, response);    		
    	} else {
    		request.setAttribute("msg", "로그인 후 수정해 주세요.");
			request.setAttribute("location", "/");
			
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
    	}
	}
	
}
