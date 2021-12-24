<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${ pageContext.request.contextPath }" />

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>물품 수정</title>
    <meta name="description" content="큐티마켓" />
    <meta name="author" content="Dapalzo" />
    <link rel="icon" type="image/png" sizes="152x152" href="${ path }/resources/imgs/favicon/favicon.png" />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link href="https://fonts.googleapis.com/css2?family=Gugi&display=swap" rel="stylesheet" />
    <script src="https://kit.fontawesome.com/91b5983e4b.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="${ path }/resources/css/market/style.css" />
    <link rel="stylesheet" href="${ path }/resources/css/goods/goods.css">
    <script src="https://kit.fontawesome.com/91b5983e4b.js" crossorigin="anonymous"></script>
    <script src="${ path }/resources/js/login/jquery-3.6.0.js"></script>
    <script src="${ path }/resources/js/login/main.js" defer></script>
</head>
<body>
    <div class="board_wrap">
        <div class="board_title">
            <strong>물품 수정</strong>
        </div>
        <form action="${ pageContext.request.contextPath }/goods/update" method="post" enctype="multipart/form-data">
	        <input type="hidden" name="no" value="${ board.no }">
			<input type="hidden" name="originalFileName" value="${ board.originalFileName }">
			<input type="hidden" name="renamedFileName" value="${ board.renamedFileName }">
	        <div class="board_write_wrap">
	            <div class="board_write">
	            	<div>
						작성자 :
						<input type="text" name="writerId" value="${ board.writerId }" readonly>
					</div>
	                <div class="title">
	                    <div class="input-group up">
	                		<i class="fas fa-map-marker-alt"></i>
	                		<select name="area1" id="area1"></select>
	                		<select name="area2" id="area2"></select>
	              		</div>
	              		
	              		<label for="cate">카테고리 : </label>
				        <select name="cate" id="cate">				         
				        <option value="clothes">의류</option>
				        <option value="home__Appliances">가전</option>
				        <option selected>------</option>
				        </select>
				        
	                    <dl>
	                        <dd><input type="text" name="title" value="${ board.title }"></dd>
	                    </dl>
	                </div>	                
	                <div class="info">
	                    <dl>
	                        <dd><input type="text" name="price" value="${ board.price }" class="info_price">원</dd>
	                    </dl>
	                    <dl>
	                        <dd><input type="password" placeholder="비밀번호 입력" class="info_pass"></dd>
	                    </dl>
	                    <dl>
	                        <input type="file" name="upfile" id="upfile">
	                    </dl>
	                </div>
	                <div class="cont">
	                    <textarea name="content">${ board.content }</textarea>
	                </div>
	            </div>
	            <div class="bt_wrap">
	                <input type="submit" value="등록" class="on">
					<input type="reset" value="취소" class="off">
	            </div>
	        </div>
        </form>
    </div>
</body>
</html>