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
    <title>큐티마켓_About Us</title>
    <meta name="description" content="큐티마켓" />
    <meta name="author" content="Dapalzo" />
    <link rel="icon" type="image/png" sizes="152x152" href="${ path }/resources/imgs/favicon/favicon.png" />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link href="https://fonts.googleapis.com/css2?family=Gugi&display=swap" rel="stylesheet" />
    <script src="https://kit.fontawesome.com/91b5983e4b.js" crossorigin="anonymous"></script>
    <script src="${ path }/resources/js/aboutus/main.js" defer></script>
    <link rel="stylesheet" href="${ path }/resources/css/aboutus/style.css" />
  </head>
  <body>
    <!-- navbar -->
    <main class="main">
      <!-- Navbar -->
	<nav id="navbar">
		<div class="navbar__logo">
			<img src="${ path }/resources/imgs/favicon/favicon.png" alt="logo">
			<a href="${ path }">큐티마켓</a>
		</div>
		<ul class="navbar__menu">
			<li class="navbar__menu__item"><a href="${ path }">메인</a></li>
			<li class="navbar__menu__item"><a href="${ path }/QT/market">마켓</a>
			</li>
			<li class="navbar__menu__item"><a href="${ path }/QT/community">커뮤니티</a>
			</li>
			<c:if test="${ empty loginMember }">
				<li class="navbar__menu__item"><a href="${ path }/QT/signinup">로그인</a>
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
      <home id="home">
        <section class="section">
          <p>
            세상 모든 장록 속 방치된 물건이 없어지길 바라는!! <br />
            저희는 다팔조입니다!!!!
          </p>
          <article class="clothes">
            <div>
              <img src="${ path }/resources/imgs/profile/no-image.png" alt="" />
              <h2>서수민</h2>
              <p>다팔조의 조장!</p>
              <div class="home__href">
                <a href="#" target="_blank"><i class="fab fa-github"></i></a>
                <a href="#" target="_blank"><i class="fab fa-instagram"></i></a>
                <a href="#"><i class="fas fa-at"></i></a>
              </div>
            </div>
          </article>
          <article class="clothes">
            <div>
              <img src="${ path }/resources/imgs/profile/no-image.png" alt="" />
              <h2>김성엽</h2>
              <p>다팔조의 조원 1</p>
              <div class="home__href">
                <a href="#" target="_blank"><i class="fab fa-github"></i></a>
                <a href="#" target="_blank"><i class="fab fa-instagram"></i></a>
                <a href="#"><i class="fas fa-at"></i></a>
              </div>
            </div>
          </article>
          <article class="home__Appliances">
            <div>
              <img src="${ path }/resources/imgs/profile/3.png" alt="" />
              <h2>이산아</h2>
              <p>다팔조의 조원 2</p>
              <div class="home__href">
                <a href="https://github.com/Athesana" target="_blank"><i class="fab fa-github"></i></a>
                <a href="#" target="_blank"><i class="fab fa-instagram"></i></a>
                <a href="#"><i class="fas fa-at"></i></a>
              </div>
            </div>
          </article>
          <article class="home__Appliances">
            <div>
              <img src="${ path }/resources/imgs/profile/4.png" alt="" />
              <h2>임현규</h2>
              <p>큐티마켓의 전반적인 프론트엔드 작업을 담당했습니다.</p>
              <div>
                <div class="home__href">
                  <a href="https://github.com/gyulsbox" target="_blank"><i class="fab fa-github"></i></a>
                  <a href="https://www.instagram.com/aloofelicidad/" target="_blank"><i class="fab fa-instagram"></i></a>
                  <a href="mailto:anwjr7878@gmail.com"><i class="fas fa-at"></i></a>
                </div>
              </div>
            </div>
          </article>
          <article class="clothes">
            <div>
              <img src="${ path }/resources/imgs/profile/no-image.png" alt="" />
              <h2>맹세정</h2>
              <p>다팔조의 조원 4</p>
              <div class="home__href">
                <a href="#" target="_blank"><i class="fab fa-github"></i></a>
                <a href="#" target="_blank"><i class="fab fa-instagram"></i></a>
                <a href="#"><i class="fas fa-at"></i></a>
              </div>
            </div>
          </article>
        </section>
      </home>
      <!-- footer -->
      <footer id="footer">
        <div>
          <p><a href="${ path }/QT/aboutus" class="footer__au">about us</a></p>
        </div>
        <div>
          <p>2021 Dapalzo - All right reserved</p>
        </div>
      </footer>
    </main>
  </body>
</html>
