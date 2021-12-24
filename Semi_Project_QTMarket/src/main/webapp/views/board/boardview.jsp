<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="path" value="${ pageContext.request.contextPath }" />
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시판</title>
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/css/community/boardstyle.css" />
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Gugi&display=swap" rel="stylesheet">
    <meta name="description" content="큐티마켓" />
    <meta name="author" content="Dapalzo" />
    <link rel="icon" type="image/png" sizes="152x152" href="${ path }/resources/imgs/favicon/favicon.png" />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link href="https://fonts.googleapis.com/css2?family=Gugi&display=swap" rel="stylesheet" />
    <script src="https://kit.fontawesome.com/91b5983e4b.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="${ path }/resources/css/market/style.css" />
    <script src="${ path }/resources/js/market/isotope.pkgd.min.js"></script>
    <script src="${ path }/resources/js/market/main.js" defer></script>
    
    <style>
    	body {
      		background: url(${ path }/resources/imgs/backgorund/background2.png) center/cover no-repeat;
      	}	
    </style>
</head>
<body>
   <!-- navbar -->
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
           <a href="${ path }/QT/market" class="active">마켓</a>
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
         	<li class="navbar__menu__item">
         	  <a href="${ path }/logout">로그아웃</a>
        	</li>
         </c:if>
        </ul>
     </nav>
     
     <!-- main -->
    <div class="board_wrap">
        <div class="board_title">
            <h1><b>큐티 게시판</b></h1>
            <p>큐티님을 위한 공간입니다.</p>
        </div>
        <div class="board_view_wrap">
            <div class="board_view">
                <div class="title">
                    ${ board.title }
                </div>
                <div class="info">
                	<dl>
                		<dt>카테고리</dt>
                		<dd>${ board.category }</dd>
                	</dl>
                    <dl>
                        <dt>번호</dt>
                        <dd>${ board.no }</dd>
                    </dl>
                    <dl>
                        <dt>글쓴이</dt>
                        <dd>${ board.writerId }</dd>
                    </dl>
                    <dl>
                        <dt>조회수</dt>
                        <dd>${ board.hits }</dd>
                    </dl>
                    <dl>
                    	<dt>첨부파일</dt>
                        <dd>
                        	<c:if test="${ empty board.originalFileName }">
							<span> - </span>
							</c:if>
							<c:if test="${ !empty board.originalFileName }">
							 <a href="javascript:fileDownload('${ board.originalFileName }', '${ board.renamedFileName }')">
						 	<c:out value="${ board.originalFileName }"/>
						 	</a>
							</c:if>
                        </dd>
                    </dl>
                </div>
                <div class="content">
					${ board.content }
                </div>
            </div>
            <div id="comment-container">
		    	<div class="comment-editor">
		    		<form action="${ pageContext.request.contextPath }/board/reply" method="POST">
		    			<input type="hidden" name="boardNo" value="${ board.no }">
						<textarea name="content" id="replyContent" rows="3"  placeholder="댓글을 입력해주세요"></textarea>
						
						<button type="submit" id="btn-insert">등록</button>
							    			
		    		</form>
		    	</div>
	    	</div>
	    	<form name="comeEdit" action="${ pageContext.request.contextPath }/board/boardComEdit" method="POST">
            <table id="tbl-comment">
            <c:forEach var="reply" items="${ board.replies }">
		    	<input type="hidden" name="replyNo" value="${ reply.no }">
		    	<input type="hidden" name="replyContent" value="${ reply.content }">
		    	<input type="hidden" name="boardNo" value="${ board.no }">
                <tr>
                    <td class="comment-writer"><c:out value="${ reply.writerId }"/></td>
                    <td class="comment-date"><fmt:formatDate type="date" value="${ reply.createDate }" pattern="yyyy-MM-dd" /></td>
                    <td class="btn-comment">
                    <!-- <form action="${ pageContext.request.contextPath }/board/boardComEdit" method="POST">  -->
                    	<c:if test="${ !empty loginMember && loginMember.id == reply.writerId }">
	                        <button type="submit" value="수정" id="btnCEdit" data-eno="${ reply.no }">수정</button>
	                        <button type="button" value="삭제" id="btnCDelete" data-no="${ reply.no }">삭제</button>
                        </c:if>
                 <!--    </form>  -->
                    </td>
                </tr>
                <tr>
                    <td colspan="3" id="comment-content"> <c:out value="${ reply.content }"/> </td>
                </tr>
            </c:forEach>
            </table>
      		</form>
            <div class="btn_wrap">
                <button type="button" class="on" onclick="location.href='${ pageContext.request.contextPath }/QT/community'">목록</button>
                <%-- 로그인 한 사람만, 로그인한 아이디와 게시글 작성자가 동일인일 경우만 버튼이 보이고 수정, 삭제 가능 --%>
                <c:if test="${ loginMember != null && loginMember.id == board.writerId }">
                	<button type="button" id="btnEdit" onclick="location.href='${ pageContext.request.contextPath }/board/boardedit?no=${ board.no }'">수정</button>
                	<button type="button" id="btnDelete">삭제</button>
            	</c:if>
            </div>
        </div>
    </div>

<script>
	$(document).ready(() => {
		$("#btnDelete").on("click", () => {
			if(confirm("정말 게시글을 삭제 하시겠습니까?")){
				location.replace("${ pageContext.request.contextPath }/board/boarddelete?no=${ board.no }");
			} 
			
		});
		
		$("#replyContent").on("click", () => {
			if(${ empty loginMember }) {
				alert("로그인 후 이용해주세요!");
				
				location.replace("${ pageContext.request.contextPath }/QT/login");
			}
		});
		
		$("#btnCDelete").on("click", (event) => {
				const btn = event.target.dataset.no;
			if(confirm("댓글을 삭제하시겠습니까?")){
				location.replace("${ pageContext.request.contextPath }/board/boardComDelete?no=" + btn);
			}
			
		});
		

		
		
		$("#btn-insert").click(function() {
			if($("#replyContent").val().length==0){
				alert("댓글을 입력하세요."); 
				$("#replyContent").focus(); 
				return false;}
		})

	});
	
	
	$("#btnCEdit").on("click", (event) => {
			const btn = event.target.dataset.eno;
	    if(confirm("수정 하시겠습니까?")){
	 	window.open('${ pageContext.request.contextPath }/board/boardComEdit?no=' + no);

	                }
	        });
	

	function fileDownload(oname, rname){
		
		location.assign("${ pageContext.request.contextPath }/board/boardFileDown?oname=" + encodeURIComponent(oname) + "&rname=" + encodeURIComponent(rname));
	}
	


</script>

</body>

</html>