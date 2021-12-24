package com.qtqt.mvc.mypage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.qtqt.mvc.common.util.EncryptUtil;
import com.qtqt.mvc.common.util.FileRename;
import com.qtqt.mvc.member.model.service.MemberService;
import com.qtqt.mvc.member.model.vo.Member;


@WebServlet("/QT/update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService service = new MemberService();
	
    public UpdateServlet() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 로그인 된 사용자인지 체크
    	HttpSession session = request.getSession(false); 
    	Member loginMember = session != null ? (Member) session.getAttribute("loginMember") : null;
    	
    	int result = 0;
    	
    	Member member = null;
    	
    	String path = getServletContext().getRealPath("/resources/upload/memberProfile");
    	int maxSize = 10485760;
    	
    	String encoding = "UTF-8";
    	
    	MultipartRequest mr = new MultipartRequest(request, path, maxSize, encoding, new FileRename());
    	
    	String id = mr.getParameter("userId");
    	String password = EncryptUtil.oneWayEnc(mr.getParameter("userPwd"), "SHA-256");
    	String name = mr.getParameter("userName");
    	String email = mr.getParameter("email");
    	String phone = mr.getParameter("phone");
    	String area1 = mr.getParameter("area1");
    	String area2 = mr.getParameter("area2");
    	String filesystemName = mr.getFilesystemName("profile");
    	String originalFileName = mr.getOriginalFileName("profile");
    	
    	if(loginMember != null) {
    		member = new Member();
        	
    		member.setId(id);
    		member.setPassword(password);
    		member.setName(name);
    		member.setEmail(email);
    		member.setOriginalProfileName(originalFileName);
    		member.setRenamedProfileName(filesystemName);
    		member.setPhone(phone);
    		member.setArea1(area1);
    		member.setArea2(area2);
    		
    		System.out.println(member);
    		
    		result = service.update(member);
    		
    		if(result > 0) {
    			session.setAttribute("loginMember", service.findMemberById(loginMember.getId()));
    		
        		request.setAttribute("msg", "회원 정보 수정에 완료하였습니다.");
    			request.setAttribute("location", "/QT/editprofile");
    		} else {
        		request.setAttribute("msg", "회원 정보 수정에 실패하였습니다.");
    			request.setAttribute("location", "/QT/editprofile");
    		}
    	} else {
    		request.setAttribute("msg", "로그인 후 수정해 주세요.");
			request.setAttribute("location", "/");
    	}
    	
    	request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		
	}

}
