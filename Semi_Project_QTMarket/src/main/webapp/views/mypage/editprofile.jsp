<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${ pageContext.request.contextPath }" />       
    
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>큐티마켓_마이페이지</title>
    <meta name="description" content="큐티마켓" />
    <meta name="author" content="Dapalzo" />
    <link rel="icon" type="image/png" sizes="152x152" href="${ path }/resources/imgs/favicon/favicon.png" />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link href="https://fonts.googleapis.com/css2?family=Gugi&display=swap" rel="stylesheet" />
    <script src="https://kit.fontawesome.com/91b5983e4b.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="${ path }/resources/css/editprofile/style.css" />
    <script src="${ path }/resources/js/login/jquery-3.6.0.js"></script>
    <script src="${ path }/resources/js/editprofile/main.js" defer></script>
    <style>
    	body {
      		background: url(${ path }/resources/imgs/backgorund/background2.png) center/cover no-repeat;
      	}	
    </style>
  </head>
  <body>
      <!-- Navbar -->
      <nav id="navbar">
        <div class="navbar__logo">
          <img src="${ path }/resources/imgs/favicon/favicon.png" alt="logo">
          <a href="${ path }">큐티마켓</a>
        </div>
        <ul class="navbar__menu">
          <li class="navbar__menu__item">
            <a href="${ path }">메인</a>
          </li>
          <li class="navbar__menu__item">
            <a href="${ path }/QT/market">마켓</a>
          </li>
          <li class="navbar__menu__item">
            <a href="${ path }/QT/community">커뮤니티</a>
          </li>
          <c:if test="${ empty loginMember }">
          	<li class="navbar__menu__item">
           	 	<a href="${ path }/QT/login">로그인</a>
         	</li>
          </c:if>
          <c:if test="${ !empty loginMember }">
          	<li class="navbar__menu__item">
            	<a href="${ path }/QT/mypage" class="active">마이페이지</a>
         	</li>
          	<li class="navbar__menu__item">
          	  <a href="${ path }/logout">로그아웃</a>
         	</li>
          </c:if>
        </ul>
      </nav>
      
     <!-- SIGN UP -->
    <main id="main">
      <section class="main_user">
                <p>회원정보 수정</p>
                <form class="form sign-up" name="memberEnrollFrm" action="${ path }/QT/update" enctype="multipart/form-data" method="post">
                  <img class="profileimage" src="${ path }/resources/upload/memberProfile/${ loginMember.renamedProfileName }"/>
                    <div class="filebox"> 
                		<label for="ex_file">이미지 변경</label> 
                		<input type="file" name="profile" id="ex_file" title="프로필사진" value="${ loginMember.originalProfileName }" required/> 
                	</div>
                    <div class="input-group up">
                      <i class="fas fa-user"></i>
                      <input type="text" name="userId" id="newId" placeholder="아이디" value="${ loginMember.id }" readonly required />
                    </div>
                    <div class="input-group up">
                        <i class="fas fa-lock"></i>
                        <input type="password" name="userPwd" id="pass1" placeholder="비밀번호" required />
                    </div>
                    <div class="input-group up">
                        <i class="fas fa-lock"></i>
                        <input type="password" id="pass2" placeholder="비밀번호 확인" required />
                    </div>
                    <div class="input-group up">
                        <i class="fas fa-user"></i>
                        <input type="text" name="userName" id="userName" placeholder="이름" value="${ loginMember.name }" readonly required />
                    </div>
                  <div class="input-group up">
                    <i class="fas fa-mobile-alt"></i>
                    <input type="tel" name="phone" id="phone" maxlength="11" placeholder="전화번호" value="${ loginMember.phone }" required />
                  </div>
                  <div class="input-group up">
                    <i class="fas fa-envelope"></i>
                    <input type="email" name="email" id="email" placeholder="이메일" value="${ loginMember.email }" required />
                  </div>
                  <div class="input-group up">
                    <i class="fas fa-map-marker-alt"></i>
                    <select name="area1" id="area1"></select>
                    <select name="area2" id="area2"></select>
                  </div>
                  <button class="btn sign-ups">정보수정</button>
                </form>
      </section>
    </main>
  </body>
</html>