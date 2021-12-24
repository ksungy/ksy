package com.qtqt.mvc.goods.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.oreilly.servlet.MultipartRequest;

import com.qtqt.mvc.common.util.FileRename;
import com.qtqt.mvc.goods.model.service.GoodsService;
import com.qtqt.mvc.goods.model.vo.GoodsBoard;


@WebServlet("/goods/update")
public class MarketUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public MarketUpdateServlet() {

    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 체크 & 본인 게시글 수정 여부 확인(직접 적용시켜 보세요.)
		GoodsBoard board = null;
		int no = Integer.parseInt(request.getParameter("no"));
		
		board = new GoodsService().findBoardByNo(no);
		
		request.setAttribute("board", board);
		request.getRequestDispatcher("/views/goods/goodsupdate.jsp").forward(request, response);
}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int result = 0;
    	GoodsBoard board = null;
    	String originalFileName = null;
    	String renamedFileName = null;
    	String path = getServletContext().getRealPath("/resources/upload/goodsimage");
    	int maxSize = 10485760;
    	String encoding = "UTF-8";
    	MultipartRequest mr = new MultipartRequest(request, path, maxSize, encoding, new FileRename());
    	
    	board = new GoodsBoard();
    	
    	board.setNo(Integer.parseInt(mr.getParameter("no")));
    	board.setTitle(mr.getParameter("title"));
    	board.setWriterId(mr.getParameter("writerId"));
    	board.setContent(mr.getParameter("content"));
    	board.setPrice(mr.getParameter("price"));
    	board.setCate(mr.getParameter("cate"));
    	board.setArea1(mr.getParameter("area1"));
    	board.setArea2(mr.getParameter("area2"));
    	board.setOriginalFileName(mr.getParameter("originalFileName"));
    	board.setRenamedFileName(mr.getParameter("renamedFileName"));
    	
    	originalFileName = mr.getOriginalFileName("upfile");
    	renamedFileName = mr.getFilesystemName("upfile");
    	
    	if(originalFileName != null && !originalFileName.equals("")) {    		
    		File file = new File(path + "/" + board.getRenamedFileName());
    		
    		if (file.exists()) {
    			file.delete();
    		}
    		
    		board.setOriginalFileName(originalFileName);
        	board.setRenamedFileName(renamedFileName);
    	}
    	
    	result = new GoodsService().save(board);
    	
    	if(result > 0) {
    		request.setAttribute("msg", "게시글 수정 성공");
    	} else {
    		request.setAttribute("msg", "게시글 수정 실패");
    	}
    	
    	request.setAttribute("location", "/goods/goodsview?no=" + board.getNo());
    	request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

}

