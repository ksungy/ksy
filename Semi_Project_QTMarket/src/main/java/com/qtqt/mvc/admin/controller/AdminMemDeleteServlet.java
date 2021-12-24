package com.qtqt.mvc.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qtqt.mvc.member.model.service.MemberService;
import com.qtqt.mvc.member.model.vo.Member;

@WebServlet("/QT/admin/member/delete")
public class AdminMemDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService service = new MemberService();
       
    public AdminMemDeleteServlet() {
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int result = 0;
    	HttpSession session = request.getSession(false); 
    	Member loginMember = session != null ? (Member) session.getAttribute("loginMember") : null;

    	if(loginMember != null && loginMember.getRole().equals("ROLE_ADMIN")) {
    		result = service.delete(request.getParameter("id"));
    		
    		System.out.println(result);
    		
    		if(result > 0) {
        		request.setAttribute("msg", "정상적으로 추방되었습니다.");
    			request.setAttribute("location", "/QT/admin/member");
    		} else {
        		request.setAttribute("msg", "추방에 실패하였습니다.");
    			request.setAttribute("location", "/QT/admin/member");
    		}

    	} else {
    		request.setAttribute("msg", "관리자외 접근 할 수 없습니다.");
			request.setAttribute("location", "/");
    	}
    	
    	request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

}
