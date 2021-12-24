<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="path" value="${ pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>물품 등록</title>
    <meta name="description" content="큐티마켓" />
    <meta name="author" content="Dapalzo" />
    <link rel="icon" type="image/png" sizes="152x152" href="${ path }/resources/imgs/favicon/favicon.png" />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link href="https://fonts.googleapis.com/css2?family=Gugi&display=swap" rel="stylesheet" />
    <script src="https://kit.fontawesome.com/91b5983e4b.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="${ path }/resources/css/goods/boardstyle.css" />
    <link rel="stylesheet" href="${ path }/resources/css/market/style.css" />
    <link rel="stylesheet" href="${ path }/resources/css/goods/goods.css">
    <script src="https://kit.fontawesome.com/91b5983e4b.js" crossorigin="anonymous"></script>
    <script src="${ path }/resources/js/login/jquery-3.6.0.js"></script>
    <script src="${ path }/resources/js/login/main.js" defer></script>
    <style>
	    div#comment-container button#btn-insert{width:60px;height:45px; color:white; background-color:#3300FF;position:relative;top:-20px;}
    </style>
</head>
<body>
	<div class="board_wrap">
        <div class="board_title">
            <strong>물품 조회</strong>
        </div>
	        <div class="board_view_wrap">
	            <div class="board_view">
	                <div class="title">
	                    ${ board.title }
	                    <div id="title_ex">거래대기</div>
	                </div>
	                <div class="info">
	                    <dl>
	                        <dt>번호</dt>
	                        <dd>${ board.no }</dd>
	                    </dl>
	                    <dl>
	                        <dt>작성자</dt>
	                        <dd>${ board.writerId }</dd>
	                    </dl>
	                    <dl>
	                        <dt>작성일</dt>
	                        <dd>${ board.createDate }</dd>
	                    </dl>
	                    <dl>
	                        <dt>조회</dt>
	                        <dd>${ board.readCount }</dd>
	                    </dl>
	                    <dl>
	                        <dt>${ board.price }원</dt>
	                    </dl>
	                </div>
	                <div class="cont">
	                	<c:if test="${ board.status == 'Y' }">
	                    <img src="${ path }/resources/upload/goodsimage/${ board.renamedFileName }" alt="" width="570px" />
	                	</c:if>
	                    <c:if test="${ board.status == 'N' }">
	                    	<img src="${ path }/resources/imgs/products/5.jpg" alt="" width="570px" />
	                	</c:if>
	                    ${ board.content }                
	                </div>
	            </div>
	            
	            <div class="bt_wrap">
	                <a href="${ pageContext.request.contextPath }/QT/market" class="on">목록</a>
	                <!-- 작성자나 글쓴이만 가능 -->
	               <c:if test="${ ! empty loginMember && loginMember.id == board.writerId }">
	               <a id="btncomplete" class="buy">거래 완료</a>
	               <a href="${ pageContext.request.contextPath }/goods/update?no=${ board.no }" class="edit" >수정</a>
	               <a class="delete" id="btnDelete">삭제</a>
	           	   </c:if>
	           	   <form action="${ pageContext.request.contextPath }/goods/goodswish" method="POST">
			    		<input type="hidden" name="boardNo" value="${ board.no }">
						<button type="submit" id="btn-insert">찜</button>	    			
		    	   </form>
	            </div>
				
	            <hr>
	            <div id="comment-container">
		    	<div class="comment-editor">
			    		<form action="${ pageContext.request.contextPath }/goods/reply" method="POST">
			    			<input type="hidden" name="boardNo" value="${ board.no }">
							<textarea name="content" id="replyContent" cols="70" rows="3"></textarea>
							<button type="submit" id="btn-insert">등록</button>	    			
		    			</form>
		    		</div>
		    	</div>
		    	<form action="${ pageContext.request.contextPath }/goods/goodscomdelete" method="GET">
            	<table id="tbl-comment">
            	<c:forEach var="reply" items="${ board.replies }">
		    		<input type="hidden" name="replyNo" value="${ reply.no }">
                	<tr>
				    	<td class="comment-writer"><c:out value="${ reply.writerId }"/></td>
				        <td class="comment-date"><fmt:formatDate type="date" value="${ reply.createDate }" /></td>
				        <td class="btn-comment">
                    		<c:if test="${ !empty loginMember && loginMember.id == reply.writerId }">
	                        	<button type="submit" value="수정" id="btnCEdit" data-eno="${ reply.no }">수정</button>
	                        	<button type="button" value="삭제" id="btnCDelete" data-no="${ reply.no }">삭제</button>
                        	</c:if>
                    	</td>
                	</tr>
	                <tr>
	                    <td colspan="3" id="comment-content"><c:out value="${ reply.content }"/> </td>
	                </tr>
	            </c:forEach>
	            </table>
	            </form>
		    	
	            
	        </div>

    </div>
    
    <script>
    $(document).ready(() => {
    	$("#btnDelete").on("click", () => {
    		if(confirm("정말로 게시글을 삭제 하시겠습니까?")) {
    			location.replace("${ pageContext.request.contextPath }/goods/delete?no=${ board.no }");
    		}
    	})
    	
    	$("#btnCDelete").on("click", (event) => {
				const btn = event.target.dataset.no;
			if(confirm("댓글을 삭제하시겠습니까?")){
				location.replace("${ pageContext.request.contextPath }/goods/goodscomdelete?no=" + btn);
			}
			
		})
		
		$("#btncomplete").on("click",() => {
    		if(confirm("정말로 거래완료 처리 하시겠습니까?")) {
    			location.replace("${ pageContext.request.contextPath }/goods/complete?no=${ board.no }");
    		}
    	})
    	
    });
    </script>
</body>
</html>