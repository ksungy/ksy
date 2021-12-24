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
           	 	<a href="${ path }/QT/login">로그인</a>
         	</li>
          </c:if>
          <c:if test="${ !empty loginMember }">
          	<li class="navbar__menu__item">
            	<a href="${ path }/QT/mypage">마이페이지</a>
         	</li>
         	<c:if test="${ loginMember.role == 'ROLE_ADMIN' }">
         	<li class="navbar__menu__item">
         		<a href="${ path }/QT/mypage">관리자페이지</a>
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
          <img class="main__user__header__profile" src="${ path }/resources/upload/memberProfile/${ loginMember.renamedProfileName }" alt="" />
          <div class="main__user__header__description">
            <p>${ loginMember.name } 님 환영합니다.</p>
            <p>일반 회원</p>
          </div>
        </div>
        <div class="main__user__body">
          <div class="main__user__body__category">
            <button class="category product" onclick="location.href='${ path }/QT/mypage';">상품 관리</button>
            
            
            <button class="category board" onclick="location.href='${ path }/QT/myboards';">게시글 관리</button>
          
          <!--  
            <form action="${ path }/QT/myboards" method="post">
            <input type="hidden" name="userId" value="${ loginMember.id }">
            <button class="category board">게시글 관리</button>
            </form>
            -->
            
            
            <button class="category wish" onclick="location.href='${ path }/QT/mywish';">찜 목록</button>
            <button class="category prefernce" onclick="location.href='${ path }/QT/editprofile';">정보 수정</button>
            <button class="category resign" onclick="location.href='${ path }/QT/withdraw';">회원 탈퇴</button>
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
              
              <c:if test="${ empty list }">
              	<tbody class="table__tbody">			
				<tr>
					<td colspan="6">
						조회된 게시글이 없습니다.
					</td>
				</tr>	
				</tbody>
			</c:if>
			
			<c:if test="${ !empty list }">
				<tbody class="table__tbody">
				<c:forEach var="board" items="${ list }">
					<tr>
						<td><input type="checkbox" name="" id="" /></td>
						<td>${ board.rowNum }</td>
						
						<td>
							<a href="${ pageContext.request.contextPath }/board/view?no=${ board.no }">
								${ board.title }
							</a>
						</td>
						<td>${ board.price }</td>
						<td>${ board.createDate }</td>
						<td>${ board.readCount }</td>
						<td>
                    		<button><i class="far fa-edit"></i></button>
                 		 </td>					
					</tr>
				</c:forEach>
				</tbody>
			</c:if>	
            </table>
            
            
            <div id="pageBar">
			<!-- 맨 처음으로 -->
			<button class="setbutton" onclick="location.href='${ pageContext.request.contextPath }/QT/mypage?page=1'">&lt;&lt;</button>

			<!-- 이전 페이지로 -->
			<button class="movebutton" onclick="location.href='${ pageContext.request.contextPath }/QT/mypage?page=${ pageInfo.prevPage }'">&lt;</button>

			<!--  10개 페이지 목록 -->
			<c:forEach begin="${ pageInfo.startPage }" end="${ pageInfo.endPage }" varStatus="status">
				<c:if test="${ status.current == pageInfo.currentPage }">				
					<button class="pagebutton" disabled>${ status.current }</button>
				</c:if>
				
				<c:if test="${ status.current != pageInfo.currentPage }">				
					<button class="pagebutton" onclick="location.href='${ pageContext.request.contextPath }/QT/mypage?page=${ status.current }'">${ status.current }</button>
				</c:if>
			</c:forEach>

			<!-- 다음 페이지로 -->
			<button class="movebutton" onclick="location.href='${ pageContext.request.contextPath }/QT/mypage?page=${ pageInfo.nextPage }'">&gt;</button>

			<!-- 맨 끝으로 -->
			<button class="setbutton" onclick="location.href='${ pageContext.request.contextPath }/QT/mypage?page=${ pageInfo.maxPage }'">&gt;&gt;</button>
			</div>
		
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
