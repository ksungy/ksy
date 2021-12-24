package com.qtqt.mvc.goods.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qtqt.mvc.goods.model.service.GoodsService;



@WebServlet("/goods/goodscomdelete")
public class GoodsComDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private GoodsService service = new GoodsService();

    public GoodsComDeleteServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 체크
    	// 본인 게시글 맞는지 확인하고 삭제하는 로직 구현 필요!
 
    	int result = 0;
    	int no = Integer.parseInt(request.getParameter("no"));
    	result = service.deleteReply(no);

    	System.out.println(no);
    	
    	if(result > 0) {
    		request.setAttribute("msg", "댓글 삭제 성공");
			request.setAttribute("location", "/QT/market");
    	} else {
    		request.setAttribute("msg", "댓글 삭제 실패");
			request.setAttribute("location", "/QT/market");
    	}
    	
    	request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}


}
