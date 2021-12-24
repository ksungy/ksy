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
    <title>큐티마켓</title>
    <meta name="description" content="큐티마켓" />
    <meta name="author" content="Dapalzo" />
    <link rel="icon" type="image/png" sizes="152x152" href="${ path }/resources/imgs/favicon/favicon.png" />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link href="https://fonts.googleapis.com/css2?family=Gugi&display=swap" rel="stylesheet" />
    <script src="${ path }/resources/js/main/main.js" defer></script>
    <link rel="stylesheet" href="${ path }/resources/css/main/style.css" />
  </head>
  <body>
    <!-- main -->
    <main class="main">
      <!-- Navbar -->
      <nav id="navbar">
        <div class="navbar__logo">
          <img src="${ path }/resources/imgs/favicon/favicon.png" alt="logo">
          <a href="${ path }">큐티마켓</a>
        </div>
        <ul class="navbar__menu">
          <li class="navbar__menu__item">
            <a href="${ path }" class="active">메인</a>
          </li>
          <li class="navbar__menu__item">
            <a href="${ path }/QT/market">마켓</a>
          </li>
          <li class="navbar__menu__item">
            <a href="${ path }/QT/community">커뮤니티</a>
          </li>
          <c:if test="${ empty loginMember }">
          	<li class="navbar__menu__item">
           	 	<a href="${ path }/QT/signinup">로그인</a>
         	</li>
          </c:if>
          <c:if test="${ !empty loginMember }">
          	<li class="navbar__menu__item">
            	<a href="${ path }/QT/mypage">마이페이지</a>
         	</li>
         	<c:if test="${ loginMember.role == 'ROLE_ADMIN' }">
         	<li class="navbar__menu__item">
         		<a href="${ path }/QT/admin/member">관리자페이지</a>
         	</li>
         	</c:if>
          	<li class="navbar__menu__item">
          	  <a href="${ path }/logout">로그아웃</a>
         	</li>
          </c:if>
        </ul>
      </nav>
      <!-- home -->
      <section id="home" class="section" >
        <div class="home section__container" >
          <div class="home__logo"></div>
            <img src="${ path }/resources/imgs/logo.png" alt="logo" class="home__logo__image"/>
          </div>
          <div class="home__description">
            <p>큐티마켓은</p><br>
            <p class="text"></p><br>
            <p>있는 마켓 입니다.</p>
          </div>
      </section>
      <!-- footer -->
      <footer id="footer">
        <div>
          <p><a href="${ path }/QT/aboutus" class="footer__au">about us</a></p>
        </div>
        <div>
          <p>2021 Dapalzo - All right reserved </p>
        </div>
      </footer>
    </main>
  </body>
</html>
