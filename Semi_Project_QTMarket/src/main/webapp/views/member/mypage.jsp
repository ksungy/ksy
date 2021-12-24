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
    <link rel="stylesheet" href="${ path }/resources/css/mypage/style.css" />
    <script src="${ path }/resources/js/mypage/main.js" defer></script>
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
    <!-- user_table -->
    <main id="main">
      <section class="main__user">
        <div class="main__user__header">
          <img class="main__user__header__profile" src="${ path }/resources/imgs/profile/4.png" alt="" />
          <div class="main__user__header__description">
            <p>{닉네임} || {실명} 님 환영합니다.</p>
            <p>일반 회원</p>
          </div>
        </div>
        <div class="main__user__body">
          <div class="main__user__body__category">
            <button class="category product">상품 관리</button>
            <button class="category board">게시글 관리</button>
            <button class="category wish">찜 목록</button>
            <button class="category prefernce">정보 수정</button>
            <button class="category resign">회원 탈퇴</button>
          </div>
          <div class="main__user__body__product">
            <p>상품 관리</p>
            <table class="main__user__body__table">
              <thead>
                <tr>
                  <th class="table__sel">선택</th>
                  <th class="table__no">번호</th>
                  <th class="table__title">제목</th>
                  <th class="table__author">가격</th>
                  <th class="table__enroll">날짜</th>
                  <th class="table__hits">조회수</th>
                  <th class="table__change">수정</th>
                </tr>
              </thead>
              <tbody class="table__tbody">
                <tr>
                  <td><input type="checkbox" name="" id="" /></td>
                  <td>8</td>
                  <td>스타벅스 원두 사야하는데..</td>
                  <td>3,000</td>
                  <td>21.12.05</td>
                  <td>8</td>
                  <td>
                    <button><i class="far fa-edit"></i></button>
                  </td>
                </tr>
                <tr>
                  <td><input type="checkbox" name="" id="" /></td>
                  <td>7</td>
                  <td>빠바 갈까..?</td>
                  <td>23,000</td>
                  <td>21.12.04</td>
                  <td>12</td>
                  <td>
                    <button><i class="far fa-edit"></i></button>
                  </td>
                </tr>
                <tr>
                  <td><input type="checkbox" name="" id="" /></td>
                  <td>6</td>
                  <td>오늘은 날씨가 따듯해</td>
                  <td>30,000</td>
                  <td>21.12.03</td>
                  <td>8</td>
                  <td>
                    <button><i class="far fa-edit"></i></button>
                  </td>
                </tr>
                <tr>
                  <td><input type="checkbox" name="" id="" /></td>
                  <td>5</td>
                  <td>은평구 사건</td>
                  <td>12,000</td>
                  <td>21.12.02</td>
                  <td>7</td>
                  <td>
                    <button><i class="far fa-edit"></i></button>
                  </td>
                </tr>
                <tr>
                  <td><input type="checkbox" name="" id="" /></td>
                  <td>4</td>
                  <td>요즘 날씨가?</td>
                  <td>15,000</td>
                  <td>21.12.01</td>
                  <td>5</td>
                  <td>
                    <button><i class="far fa-edit"></i></button>
                  </td>
                </tr>
                <tr>
                  <td><input type="checkbox" name="" id="" /></td>
                  <td>3</td>
                  <td>요즘 관악구 살기 좋나요?</td>
                  <td>5,000</td>
                  <td>21.12.01</td>
                  <td>4</td>
                  <td>
                    <button><i class="far fa-edit"></i></button>
                  </td>
                </tr>
                <tr>
                  <td><input type="checkbox" name="" id="" /></td>
                  <td>2</td>
                  <td>이거 당근 짭아닌가요</td>
                  <td>20,000</td>
                  <td>21.11.31</td>
                  <td>12</td>
                  <td>
                    <button><i class="far fa-edit"></i></button>
                  </td>
                </tr>
                <tr>
                  <td><input type="checkbox" name="" id="" /></td>
                  <td>1</td>
                  <td>첫 글 루팡</td>
                  <td>9,000</td>
                  <td>96.01.24</td>
                  <td>27</td>
                  <td>
                    <button><i class="far fa-edit"></i></button>
                  </td>
                </tr>
              </tbody>
            </table>
            <div class="product__btn">
              <button>거래 완료</button>
              <button>상품 삭제</button>
            </div>
          </div>
        </div>
      </section>
    </main>
  </body>
</html>
