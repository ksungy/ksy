package com.qtqt.mvc.goods.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qtqt.mvc.common.util.PageInfo;
import com.qtqt.mvc.goods.model.service.GoodsService;
import com.qtqt.mvc.goods.model.vo.GoodsWish;
import com.qtqt.mvc.member.model.vo.Member;

@WebServlet("/goods/goodswishlist")
public class GoodsWishList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private GoodsService service = new GoodsService();

    public GoodsWishList() {
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 게시글 리스트 조회
    	// 2. 페이징 처리
    	int page = 0;
    	int listCount = 0;
    	HttpSession session = request.getSession(false);
    	Member loginMember = session != null ? (Member)session.getAttribute("loginMember") : null;
    	PageInfo pageInfo = null;
    	GoodsWish wish = new GoodsWish();
    	List<GoodsWish> list = null;
    	
    	wish.setWriterId(loginMember.getId());
    	
    	try {
    		page = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			page = 1;
		}
    	
    	listCount = service.goodswishcount();
    	pageInfo = new PageInfo(page, 10, listCount, 100);
    	list = service.goodswishlist(pageInfo, wish);
    	
    	System.out.println(listCount);
    	System.out.println(list);
    	
    	request.setAttribute("pageInfo", pageInfo);
    	request.setAttribute("list", list);
    	request.getRequestDispatcher("/views/goods/goodswish.jsp").forward(request, response);
	}

}
