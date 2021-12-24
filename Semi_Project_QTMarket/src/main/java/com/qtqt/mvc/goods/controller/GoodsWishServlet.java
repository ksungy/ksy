package com.qtqt.mvc.goods.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qtqt.mvc.goods.model.service.GoodsService;
import com.qtqt.mvc.goods.model.vo.GoodsWish;
import com.qtqt.mvc.member.model.vo.Member;

@WebServlet("/goods/goodswish")
public class GoodsWishServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private GoodsService service = new GoodsService();

    public GoodsWishServlet() {
    }

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		HttpSession session = request.getSession(false);
    	Member loginMember = session != null ? (Member)session.getAttribute("loginMember") : null;
		GoodsWish wish = new GoodsWish();
		
		if(loginMember != null) {
			wish.setNo(boardNo);
			wish.setWriterId(loginMember.getId());
			
			int result = service.goodswish(wish);
			
			if(result > 0) {
	     		request.setAttribute("msg", "목록 추가 성공!");
	     		request.setAttribute("location", "/QT/market");
			} else {
				request.setAttribute("msg", "목록 추가 실패!");
	     		request.setAttribute("location", "/QT/market");
			} 
		} else {
	     	request.setAttribute("msg", "로그인 후 사용할 수 있습니다.");
	     	request.setAttribute("location", "/");
	    }

	
	request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

}
