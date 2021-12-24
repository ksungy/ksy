package com.qtqt.mvc.goods.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qtqt.mvc.goods.model.service.GoodsService;




@WebServlet("/goods/delete")
public class MarketDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private GoodsService service = new GoodsService();
	
    public MarketDeleteServlet() {

    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//로그인 체크 & 본인 게시글 삭제 여부 확인(직접 적용)
    	int result = 0;
    	int no = Integer.parseInt(request.getParameter("no"));
    	
    	System.out.println("No : " + no);
    	
    	result = service.delete(no);
    	

    	if(result > 0) {
    		request.setAttribute("msg", "게시글 삭제 성공");
    		request.setAttribute("location", "/QT/market");
    	} else {
    		request.setAttribute("msg", "게시글 삭제 실패");
    		request.setAttribute("location", "/QT/market");
    	}
    	
    request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
    }

}