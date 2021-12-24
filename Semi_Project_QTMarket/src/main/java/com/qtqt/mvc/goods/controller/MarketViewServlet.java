package com.qtqt.mvc.goods.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qtqt.mvc.goods.model.service.GoodsService;
import com.qtqt.mvc.goods.model.vo.GoodsBoard;


@WebServlet("/board/view")
public class MarketViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private GoodsService service = new GoodsService();

    public MarketViewServlet() {

    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int no = Integer.parseInt(request.getParameter("no"));
    	
    	System.out.println("No : " + no);
    	
    	GoodsBoard board =  service.findBoardByNo(no);
    	
    	request.setAttribute("board", board);
    	request.getRequestDispatcher("views/board/goods_view.jsp").forward(request, response);
		}

}
