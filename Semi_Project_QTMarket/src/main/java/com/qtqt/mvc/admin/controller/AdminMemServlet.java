package com.qtqt.mvc.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qtqt.mvc.common.util.PageInfo;
import com.qtqt.mvc.member.model.service.MemberService;
import com.qtqt.mvc.member.model.vo.Member;


@WebServlet("/QT/admin/member")
public class AdminMemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private MemberService service = new MemberService();
	
    public AdminMemServlet() {

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false); 
    	Member loginMember = session != null ? (Member) session.getAttribute("loginMember") : null;
    	
    	if(loginMember != null && loginMember.getRole().equals("ROLE_ADMIN")) {
    
		int page = 0;
    	int listCount = 0;
    	PageInfo pageInfo = null;
    	List<Member> list = null;
    	
    	try {
    		page = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			page = 1;
		}
    	
    	listCount = service.getMemberCount();
    	pageInfo = new PageInfo(page, 100, listCount, 100);
    	list = service.getMemberList(pageInfo);
    	
    	System.out.println(listCount);
    	System.out.println(list);
    	
    	request.setAttribute("listCount", listCount);
    	request.setAttribute("list", list);
		request.getRequestDispatcher("/views/admin/MemberManagement.jsp").forward(request, response);
		
    	} else {
    		request.setAttribute("msg", "관리자외 접근이 불가합니다.");
			request.setAttribute("location", "/");
			
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
    	}
	}

}
