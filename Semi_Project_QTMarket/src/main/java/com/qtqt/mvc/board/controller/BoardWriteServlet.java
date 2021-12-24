package com.qtqt.mvc.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.qtqt.mvc.board.model.service.BoardService;
import com.qtqt.mvc.board.model.vo.Board;
import com.qtqt.mvc.common.util.FileRename;
import com.qtqt.mvc.member.model.vo.Member;


@WebServlet("/board/boardwrite")
public class BoardWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private BoardService service = new BoardService();

    public BoardWriteServlet() {
    }

    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false); 
    	Member loginMember = session != null ? (Member)session.getAttribute("loginMember") : null;
    	String viewName = "/views/board/boardwrite.jsp";

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
    	
    	String path = getServletContext().getRealPath("/resources/upload/board");
    	// 파일 사이즈 (50MB)
    	int maxSize = 52428800;
    	String encoding = "UTF-8";
    	
    	MultipartRequest mr = new MultipartRequest(request, path, maxSize ,encoding, new FileRename()); 
    	
    	String title = mr.getParameter("title");
    	String writerId = mr.getParameter("writerId");
    	String content = mr.getParameter("content");
    	String category = mr.getParameter("category");
    	
    	String filesystemName = mr.getFilesystemName("upfile");
    	String originalFileName = mr.getOriginalFileName("upfile");
    	
    	HttpSession session = request.getSession(false); 
    	Member loginMember = session != null ? (Member)session.getAttribute("loginMember") : null;
    	
    	if(loginMember != null) {
    		Board board = new Board();
    		
    		board.setWriterId(loginMember.getId());
    		board.setTitle(title);
    		board.setContent(content);
    		board.setOriginalFileName(originalFileName);
    		board.setRenamedFileName(filesystemName);
    		board.setCategory(category);

    		
    		result = service.save(board);
    		
    		if(result > 0) {
    			// 게시글 등록이 성공했다는 뜻
    			request.setAttribute("msg", "게시글 등록 성공");
    			request.setAttribute("location", "/QT/community");
    		} else {
    			// 게시글 등록이 실패했다는 뜻
    			request.setAttribute("msg", "게시글 등록 실패");
    			request.setAttribute("location", "/QT/community");
    		}
    		
    	} else {
    		request.setAttribute("msg", "로그인 후 사용할 수 있습니다.");
    		request.setAttribute("location", "/");    		
    	}
    	
    	request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);	
		

	}

}
