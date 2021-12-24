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
    <link rel="stylesheet" href="${ path }/resources/css/adminpage/community/main.css" />
    <script src="${ path }/resources/js/adminpage/list.js"></script>
    <script src="${ path }/resources/js/login/jquery-3.6.0.js"></script>
    <script src="${ path }/resources/js/adminpage/community/main.js" defer></script>
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
            <li><a href="${ path }/QT/admin/member">회원관리</a></li>
            <li><a href="${ path }/QT/admin/market">마켓관리</a></li>
            <li><a class="active" href="${ path }/QT/admin/community">게시판관리</a></li> 
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
        <p>: : 게시판관리 : :</p>
        <!-- 총 회원 수 DB따와서 출력 -->
        <span>총 ${ listCount } 게시글</span>
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
              <button class="sort" data-sort="title">제목 순</button>
              <button class="sort" data-sort="writer">작성자 순</button>
              <button class="sort" data-sort="hits">조회 순</button>
              <button class="sort" data-sort="enroll">등록 순</button>
            </div>
          </div>
          <table class="tbl_member">
            <thead>
              <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>내용</th>
                <th>파일</th>
                <th>작성일</th>
                <th>조회수</th>
                <th>삭제</th>
              </tr>
            </thead>
              <!-- <tr>
                <td>
                  조회된 게시글이 없습니다.
                </td>
              </tr>	 -->
            <tbody class="list">
              <c:if test="${ !empty list }">
            	<c:forEach var="board" items="${ list }">
	              <tr>
	                <td class="no">${ board.no }</td>
	                <td class="title">${ board.title }</td>
	                <td class="writer">${ board.writerId }</td>
	                <td class="content">${ board.content }</td>
	                <td class="file">
	                <c:if test="${ empty board.originalFileName }">
						<span> - </span>
					</c:if>
					<c:if test="${ !empty board.originalFileName }">
						<img src="${ pageContext.request.contextPath }/resources/imgs/file.png" width="20" height="20"/>
					</c:if>
					</td>
	                <td class="enroll">${ board.createDate }</td>
	                <td class="hits">${ board.hits }</td>
	                <td>
	                  <input class="deleteBtn" name="deleteBtn" type="button" value="X" data-boardNo="${ board.no }" ></input>
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
