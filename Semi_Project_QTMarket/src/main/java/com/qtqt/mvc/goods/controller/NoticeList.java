package com.qtqt.mvc.goods.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qtqt.mvc.common.util.PageInfo;
import com.qtqt.mvc.goods.model.service.GoodsService;
import com.qtqt.mvc.goods.model.vo.GoodsBoard;

@WebServlet("/goods/notice")
public class NoticeList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private GoodsService service = new GoodsService();

    public NoticeList() {
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int page = 0;
    	int listCount = 0;
    	PageInfo pageInfo = null;
    	List<GoodsBoard> list = null;
    	String field_ = request.getParameter("f");
    	String field2_ = request.getParameter("q");
    	
    	String field = "";
    	if(field_ != null) 
    		field = field_;
    	
    	String field2 = "";
    	if(field2_ != null)
    		field2 = field2_;
    	
    	try {
    		page = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			page = 1;
		}
    	
    	listCount = service.getBoardCount();
    	pageInfo = new PageInfo(page, 10, listCount, 100);
    	list = service.noticelist(field, field2, pageInfo);
    	
    	System.out.println(listCount);
    	System.out.println(list);
    	
    	request.setAttribute("pageInfo", pageInfo);
    	request.setAttribute("list", list);
    	request.getRequestDispatcher("/views/member/market.jsp").forward(request, response);
	}

}
