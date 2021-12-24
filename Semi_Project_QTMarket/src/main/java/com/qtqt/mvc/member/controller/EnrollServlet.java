package com.qtqt.mvc.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.qtqt.mvc.common.util.EncryptUtil;
import com.qtqt.mvc.common.util.FileRename;
import com.qtqt.mvc.member.model.service.MemberService;
import com.qtqt.mvc.member.model.vo.Member;

@WebServlet(name="enroll", urlPatterns = "/QT/signinup/enroll")
public class EnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService service = new MemberService();
       
    public EnrollServlet() {
    }

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
    	
    	
    	Member member = new Member();
    	
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

		int result = service.save(member);
		
		if(result > 0) {
			// 회원 가입 완료
			request.setAttribute("msg", "회원 가입 성공!");	
			request.setAttribute("location", "/");
		} else {
			request.setAttribute("msg", "회원 가입 실패!");
			request.setAttribute("location", "/views/member/signinup.jsp");
		}
		
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
    }

}
