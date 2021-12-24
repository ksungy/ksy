package com.qtqt.mvc.mypage.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qtqt.mvc.common.util.PageInfo;
import com.qtqt.mvc.member.model.vo.Member;
import com.qtqt.mvc.mypage.model.service.GoodsService;
import com.qtqt.mvc.mypage.model.vo.GoodsBoard;

@WebServlet("/QT/mypage")
public class MyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GoodsService service = new GoodsService();
       
    public MyPageServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		HttpSession session = request.getSession(false);
    	Member loginMember = session != null ? (Member)session.getAttribute("loginMember") : null;
    	
    	String id = loginMember.getId();
    	
		int page = 0;
    	int listCount = 0;
    	PageInfo pageInfo = null;
    	List<GoodsBoard> list = null;
    	
    	try {
    		page = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			page = 1;
		}
    	
    	listCount = service.getGoodsCountById(id);
    	pageInfo = new PageInfo(page, 5, listCount, 8);
    	list = service.getGoodsListById(pageInfo, id);
    	
    	System.out.println(page);
    	System.out.println(id);
    	System.out.println(listCount);
    	System.out.println(list);
    	
    	request.setAttribute("pageInfo", pageInfo);
    	request.setAttribute("list", list);
    	request.getRequestDispatcher("/views/mypage/mypage.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
