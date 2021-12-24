package com.qtqt.mvc.board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.qtqt.mvc.board.model.service.BoardService;
import com.qtqt.mvc.board.model.vo.Board;
import com.qtqt.mvc.common.util.FileRename;


@WebServlet("/board/boardedit")
public class BoardEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public BoardEditServlet() {
    }


    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// 로그인 체크
    	// 본인 게시글 맞는지 확인하고 삭제하는 로직 구현 필요!
    	
    	Board board = null;
    	int no = Integer.parseInt(request.getParameter("no"));
    	
    	board = new BoardService().findBoardbyNo(no, true);
    	
    	request.setAttribute("board", board);
    	request.getRequestDispatcher("/views/board/boardedit.jsp").forward(request, response);
    	
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int result = 0;
    	Board board = null;
    	String originalFileName = null;
    	String renamedFileName = null;
    	
    	String path = getServletContext().getRealPath("/resources/upload/board");
    	
    	int maxSize = 52428800;
    	String encoding = "UTF-8";
    	
    	MultipartRequest mr = new MultipartRequest(request, path, maxSize ,encoding, new FileRename()); 
    	
    	board = new Board();
    	
    	board.setNo(Integer.parseInt(mr.getParameter("no")));
    	board.setTitle(mr.getParameter("title"));
    	board.setWriterId(mr.getParameter("writer"));
    	board.setContent(mr.getParameter("writer"));
    	board.setContent(mr.getParameter("content"));
    	board.setOriginalFileName(mr.getParameter("originalFileName"));
    	board.setRenamedFileName(mr.getParameter("renamedFileName"));
    	board.setContent(mr.getParameter("content"));
    	board.setOriginalFileName(mr.getParameter("originalFileName"));
    	board.setRenamedFileName(mr.getParameter("renamedFileName"));
    	board.setCategory(mr.getParameter("category"));

    	// 여기는 사용자가 edit할 때 파일이 바뀌었을 경우
    	originalFileName = mr.getOriginalFileName("upfile");
    	renamedFileName = mr.getFilesystemName("upfile");
    	
    	// 사용자가 파일을 수정했을 경우
    	if(originalFileName != null && !originalFileName.equals("")) {
    		File file = new File(path + "/" + board.getRenamedFileName());
    		
    		// 사용자가 글 수정에서 파일도 수정했을 경우, 기존 파일은 삭제하는 로직
    		if(file.exists()) {
    			file.delete();
    		}
    		
    		board.setOriginalFileName(originalFileName);
        	board.setRenamedFileName(renamedFileName);
    	} 
    	
    	result = new BoardService().save(board);
    	
    	if(result > 0) {
    		request.setAttribute("msg", "게시글 수정 성공");
    	} else {
    		request.setAttribute("msg", "게시글 수정 실패");
    	}
    	

    	System.out.println(board);

    	request.setAttribute("location", "/board/boardview?no=" + board.getNo());
    	request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
    	
    	
	}

}
