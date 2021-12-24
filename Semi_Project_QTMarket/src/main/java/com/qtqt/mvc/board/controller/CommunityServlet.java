package com.qtqt.mvc.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qtqt.mvc.board.model.vo.Board;
import com.qtqt.mvc.board.model.service.BoardService;
import com.qtqt.mvc.common.util.PageInfo;

@WebServlet("/QT/community")
public class CommunityServlet extends HttpServlet {
	
	private BoardService service = new BoardService();
	
	private static final long serialVersionUID = 1L;
       
    public CommunityServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int page = 0;
		int listCount = 0;
		PageInfo pageInfo = null;
		List<Board> list = null;
		
		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			page = 1;
		}
		
		listCount = service.getBoardCount();	
		pageInfo = new PageInfo(page, 10, listCount, 10);
		list = service.getBoardList(pageInfo);
		
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/views/board/community.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
