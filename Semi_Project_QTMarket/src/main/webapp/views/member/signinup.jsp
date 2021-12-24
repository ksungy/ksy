<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<c:set var="path" value="${ pageContext.request.contextPath }" />
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>큐티마켓_로그인</title>
    <meta name="description" content="큐티마켓" />
    <meta name="author" content="Dapalzo" />
    <link rel="icon" type="image/png" sizes="152x152" href="${ path }/resources/imgs/favicon/favicon.png" />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link href="https://fonts.googleapis.com/css2?family=Gugi&display=swap" rel="stylesheet" />
    <link rel="stylesheet" href="${ path }/resources/css/login/style.css" />
    <script src="https://kit.fontawesome.com/91b5983e4b.js" crossorigin="anonymous"></script>
    <script src="${ path }/resources/js/login/jquery-3.6.0.js"></script>
    <script src="${ path }/resources/js/login/main.js" defer></script>
</head>
<body>
 <div id="container" class="container">
      <!-- FORM SECTION -->
      <div class="row">
        <!-- SIGN UP -->
        <div class="col align-items-center flex-col sign-up">
          <div class="form-wrapper align-items-center">
            <form class="form sign-up" name="memberEnrollFrm" action="${ path }/QT/signinup/enroll" enctype="multipart/form-data" method="post">
              <div class="input-group up">
                <i class="fas fa-user"></i>
                <input type="text" name="userId" id="newId" placeholder="아이디" required />
              </div>
              <input class="enroll__idcheck" type="button" id="checkDuplicate" value="중복검사"	>
              <div class="input-group up">
                <i class="fas fa-lock"></i>
                <input type="password" name="userPwd" id="pass1" placeholder="비밀번호" required />
              </div>
              <div class="input-group up">
                <i class="fas fa-lock"></i>
                <input type="password" id="pass2" placeholder="비밀번호 확인" />
              </div>
              <div class="input-group up">
                <i class="fas fa-user"></i>
                <input type="text" name="userName" id="userName" placeholder="이름"  required />
              </div>
              <div class="input-group up">
                <i class="fas fa-images"></i>
                <input type="file" name="profile" id="profile" title="프로필사진" required />
              </div>
              <div class="input-group up">
                <i class="fas fa-mobile-alt"></i>
                <input type="tel" name="phone" id="phone" maxlength="11" placeholder="전화번호" required />
              </div>
              <div class="input-group up">
                <i class="fas fa-envelope"></i>
                <input type="email" name="email" id="email" placeholder="이메일" required />
              </div>
              <div class="input-group up">
                <i class="fas fa-map-marker-alt"></i>
                <select name="area1" id="area1"></select>
                <select name="area2" id="area2"></select>
              </div>
              <button class="btn sign-ups">회원가입</button>
              <p>
                <span> 이미 계정이 있으신가요? </span>
                <b onclick="toggle()" class="pointer"> 로그인 </b>
              </p>
              <p>
                <b onclick="location.href='${ path }/index.jsp';" style="cursor: pointer">메인으로 돌아가기</b>
              </p>
            </form>
          </div>
        </div>
        <!-- END SIGN UP -->
        <!-- SIGN IN -->
        <div class="col align-items-center flex-col sign-in">
          <div class="form-wrapper align-items-center"
          >
            <form action="${ path }/QT/signinup/login" method="post" class="form sign-in">
              <div class="input-group in">
                <i class="fas fa-user"></i>
                <input type="text" name="userId" id="userId" value="${ empty cookie.saveId ? '' : cookie.saveId.value }" placeholder="아이디를 입력해주세요." />
              </div>
              <div class="input-group in">
                <i class="fas fa-lock"></i>
                <input type="password" name="userPwd" id="userPwd" placeholder="패스워드를 입력해주세요." />
              </div>
              <div class="login__save">
                <input type="checkbox" name="saveId"/>
                <span>아이디 자동 저장</span>
              </div>
              <input class="btn sign-ins" type="submit" value="로그인"></input>
              <p>
                <b> 비밀번호를 잊으셨나요? </b>
              </p>
              <p>
                <span> 어랏, 계정이 없으신가요? </span>
                <b onclick="toggle()" class="pointer"> 회원가입 </b>
              </p>
              <p>
                <b onclick="location.href='${ path }/index.jsp';" style="cursor: pointer">메인으로 돌아가기</b>
              </p>
            </form>
          </div>
          <div class="form-wrapper"></div>
        </div>
        <!-- END SIGN IN -->
      </div>
      <!-- END FORM SECTION -->
      <!-- CONTENT SECTION -->
      <div class="row content-row">
        <!-- SIGN IN CONTENT -->
        <div class="col align-items-center flex-col">
          <div class="text sign-in">
            <h2>환영합니다</h2>
          </div>
          <div class="img sign-in"></div>
        </div>
        <!-- END SIGN IN CONTENT -->
        <!-- SIGN UP CONTENT -->
        <div class="col align-items-center flex-col">
          <div class="img sign-up"></div>
          <div class="text sign-up">
            <h2>큐티마켓과 함께 해요</h2>
          </div>
        </div>
        <!-- END SIGN UP CONTENT -->
      </div>
      <!-- END CONTENT SECTION -->
    </div>
</body>
</html>