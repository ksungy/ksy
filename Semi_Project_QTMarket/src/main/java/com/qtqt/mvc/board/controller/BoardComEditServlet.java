package com.qtqt.mvc.board.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qtqt.mvc.board.model.service.BoardService;
import com.qtqt.mvc.board.model.vo.Board;
import com.qtqt.mvc.board.model.vo.Reply;


@WebServlet("/board/boardComEdit")
public class BoardComEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BoardService service = new BoardService();
	
    public BoardComEditServlet() {
    }


    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int result = 0;
    	Board board = null;
    	int boardNo = Integer.parseInt(request.getParameter("boardNo"));
    	int no = 0;
    	
    	board = new Board();
    	
    	Reply reply = new Reply();

    	/*
    	try {
			no = Integer.parseInt(request.getParameter("no"));
		} catch (NumberFormatException e) {
			no = reply.getNo();
		}
		*/
    	
    	
		reply.setNo(Integer.parseInt(request.getParameter("no")));
    	reply.setBoardNo(boardNo);
		reply.setContent(request.getParameter("content"));
			
		result = service.updateReply(reply);
		
		if(result > 0) {
    		request.setAttribute("msg", "댓글 수정 성공");
    		request.setAttribute("location", "/QT/community");
    	} else {
    		request.setAttribute("msg", "댓글 수정 실패");
    		request.setAttribute("location", "/QT/community");
    	}
  	
//    	request.setAttribute("location", "/board/boardview?no=" + board.getNo());
    	request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
    	
    	
	}


}
