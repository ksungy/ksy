package com.qtqt.mvc.goods.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.oreilly.servlet.MultipartRequest;
import com.qtqt.mvc.common.util.FileRename;
import com.qtqt.mvc.goods.model.service.GoodsService;
import com.qtqt.mvc.goods.model.vo.GoodsBoard;
import com.qtqt.mvc.member.model.vo.Member;

@WebServlet("/goods/goodsadd")
public class GoodsAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private GoodsService service = new GoodsService();

    public GoodsAdd() {
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false); 
    	Member loginMember = session != null ? (Member) session.getAttribute("loginMember") : null;
    	String viewName = "/views/goods/goods_add.jsp";
    	
    	if(loginMember == null) {    
    		viewName = "/views/common/msg.jsp";
    		request.setAttribute("msg", "로그인 후 사용할 수 있습니다.");
			request.setAttribute("location", "/");
    	}
    	
    	
    	request.getRequestDispatcher(viewName).forward(request, response);
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int result = 0;
    	
    	// 파일이 저장될 경로
    	String path = getServletContext().getRealPath("/resources/upload/goodsimage");
    	 
    	// 파일의 사이즈 지정 (10MB)
    	int maxSize = 10485760;
    	
    	// 문자에 대한 인코딩 설정
    	String encoding = "UTF-8";
    	
    	MultipartRequest mr = new MultipartRequest(request, path, maxSize, encoding, new FileRename());
    	
    	String writerId = mr.getParameter("writerId");
    	String title = mr.getParameter("title");
    	String content = mr.getParameter("content");
    	String price = mr.getParameter("price");
    	String cate = mr.getParameter("cate");
    	String area1 = mr.getParameter("area1");
    	String area2 = mr.getParameter("area2");
    	String status = mr.getParameter("status");

    	
    	// 파일에 대한 정보를 가져올 때
    	String filesystemName = mr.getFilesystemName("upfile");
    	String originalFileName = mr.getOriginalFileName("upfile");
    	
    	// 로그인 안된 사용자가 게시글 작성이 불가능하도록 체크하는 로직
    	HttpSession session = request.getSession(false); 
    	Member loginMember = session != null ? (Member) session.getAttribute("loginMember") : null;
    	
    	if(loginMember != null) {    
    		GoodsBoard board = new GoodsBoard();
    		
    		board.setWriterId(writerId);
    		board.setTitle(title);
    		board.setContent(content);
    		board.setPrice(price);
    		board.setCate(cate);
    		board.setOriginalFileName(originalFileName);
    		board.setRenamedFileName(filesystemName);
    		board.setArea1(area1);
    		board.setArea2(area2);

    		board.setStatus(status);
    		

    		result = service.save(board);
    		
    		if(result > 0) {
    			request.setAttribute("msg", "게시글 등록 성공");
    			request.setAttribute("location", "/QT/market");
    			
        	} else {
    			request.setAttribute("msg", "게시글 등록 실패");
    			request.setAttribute("location", "/QT/market");
    			
    			System.out.println(board.getWriterId());
    			System.out.println(board.getTitle());
        	}
    	} else {
    		request.setAttribute("msg", "로그인 후 사용할 수 있습니다.");
			request.setAttribute("location", "/");			
    	}
    	
    	request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

}
