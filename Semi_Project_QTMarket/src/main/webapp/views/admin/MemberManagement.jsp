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
    <title>큐티마켓_커뮤니티</title>
    <meta name="description" content="큐티마켓" />
    <meta name="author" content="Dapalzo" />
    <link rel="icon" type="image/png" sizes="152x152" href="${ path }/resources/imgs/favicon/favicon.png" />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link href="https://fonts.googleapis.com/css2?family=Gugi&display=swap" rel="stylesheet" />
    <script src="https://kit.fontawesome.com/91b5983e4b.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="${ path }/resources/css/adminpage/member/main.css" />
    <script src="${ path }/resources/js/adminpage/list.js"></script>
    <script src="${ path }/resources/js/login/jquery-3.6.0.js"></script>
    <script src="${ path }/resources/js/adminpage/member/main.js" defer></script>
  </head>
<body>
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
         		<a href="${ path }/QT/admin/member" class="active">관리자페이지</a>
         	</li>
         	</c:if>
          	<li class="navbar__menu__item">
          	  <a href="${ path }/logout">로그아웃</a>
         	</li>
          </c:if>
        </ul>
      </nav>
  <main id="main">
    <header class="main__header">
      <div class="main__header__categories box">
        <p>카테고리</p>
        <div>
          <ul>
            <li><a class="active" href="${ path }/QT/admin/member">회원관리</a></li>
            <li><a href="${ path }/QT/admin/market">마켓관리</a></li>
            <li><a href="${ path }/QT/admin/community">게시판관리</a></li>   
          </ul>
        </div>
      </div>
      <div class="main__header__best box">
        <p>관리자 규칙</p>
        <div>
          <p>1. 권한 남용 금지</p>
          <br>
          <p>2. 사용자의 자유를 최대한 보장</p>
          <br>
          <p>3. 규칙위반을 제외한 활동에 간섭 X</p>
          <br>
          <p>4. 권한 남용 금지!</p>
        </div>
      </div>
      <div class="main__header__img box">
        <div>
          <img src="${ path }/resources/imgs/favicon/favicon.png" alt="">
        </div>
      </div>
    </header>
    <home class="main__home">
      <div class="main__home__categories box">
        <p>: : 회원관리 : :</p>
        <span>총 ${ listCount } 회원</span>
      </div>
      <div class="main__home__board box">
        <div id="list" class="main__home__board__members">
          <div class="members__nav">
            <div class="members__search">
              <div class="search-box">
                <input class="search" type="text" placeholder="검색어를 입력해 주세요!">
                <div class="search-icon">
                  <i class="fas fa-search"></i>
                </div>
                <div class="cancel-icon">
                  <i class="fas fa-times"></i>
                </div>
              </div>    
            </div>
            <div class="members__sort">
              <button class="sort" data-sort="no">번호 순</button>
              <button class="sort" data-sort="name">이름 순</button>
              <button class="sort" data-sort="id">아이디 순</button>
              <button class="sort" data-sort="enroll">가입일 순</button>
              <button class="sort" data-sort="city">도시 순</button>
            </div>
          </div>
          <table class="tbl_member">
            <thead>
              <tr>
                <th>번호</th>
                <th>아이디</th>
                <th>이름</th>
                <th>전화번호</th>
                <th>이메일</th>
                <th>가입일</th>
                <th>시/도</th>
                <th>구/군</th>
                <th>권한</th>
                <th>추방</th>
              </tr>
            </thead>
            <tbody class="list">
            <c:if test="${ !empty list }">
            	<c:forEach var="member" items="${ list }">
	              <tr>
	                <td class="no">${ member.rowNum }</td>
	                <td class="id">${ member.id }</td>
	                <td class="name">${ member.name }</td>
	                <td class="phone">${ member.phone }</td>
	                <td class="email">${ member.email }</td>
	                <td class="enroll">${ member.enrollDate }</td>
	                <td class="city">${ member.area1 }</td>
	                <td class="district">${ member.area2 }</td>
	                <c:if test="${ member.role == 'ROLE_ADMIN' }">
		                <td class="role">
		                  <select name="Authorize" id="Authorize">
		                    <option value="ROLE_USER">일반회원</option>
		                    <option value="ROLE_ADMIN" selected>관리자</option>
		                  </select>
		                </td>
	                </c:if>
                    <c:if test="${ member.role == 'ROLE_USER' }">
		                <td class="role">
		                  <select name="Authorize" id="Authorize">
		                    <option value="ROLE_USER" selected>일반회원</option>
		                    <option value="ROLE_ADMIN">관리자</option>
		                  </select>
		                </td>
	                </c:if>
	                <td>
	                  <input class="deleteBtn" name="deleteBtn" type="button" value="X" data-memberId="${ member.id }" ></input>
	                </td>
	              </tr>
              </c:forEach>
            </c:if>
            </tbody>
          </table>      
        </div>
      </div>
    </home>
   </main> 
  </body>
</html>
