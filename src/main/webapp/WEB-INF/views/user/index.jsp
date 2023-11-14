<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <%@ include file="/WEB-INF/common/header.jsp" %>
    <title>마이페이지 | HappyHouse</title>
</head>
<body class="min-vh-100 d-flex flex-column">
<%@ include file="/WEB-INF/common/nav-simple.jsp" %>

<!-- 제목 start -->
<div style="height: 72px"></div>
<div class="container" style="max-width: 720px;">
    <div class="container d-flex flex-column mt-5">
        <h3 class="text-dark fw-bold mb-0">
            <a class="page-link d-inline" href="${root}/"><i class="bi bi-arrow-left"></i></a>
            마이페이지
        </h3>
        <hr class="border border-dark">
    </div>
</div>
<!-- 제목 end -->

<!-- 중앙 content start -->
<div class="container" style="max-width: 720px;">
    <div class="container d-flex flex-column gap-3 mt-2">
        <div class="mypage__info-row">
            <div>아이디</div>
            <div>${user.userId}</div>
        </div>
        <div class="mypage__info-row">
            <div>비밀번호</div>
            <div>****</div>
        </div>
        <div class="mypage__info-row">
            <div>이름</div>
            <div>${user.userName}</div>
        </div>
        <div class="mypage__info-row">
            <div>주소</div>
            <div>${user.address}</div>
        </div>
        <div class="mypage__info-row">
            <div>전화번호</div>
            <div>${user.phone}</div>
        </div>
        <div>
            <div class="d-flex flex-column d-sm-block gap-2">
                <a class="btn btn-primary" href="${root}/user/modify">
                    <i class="bi bi-pen"></i>
                    정보 수정
                </a>
                <a class="btn btn-secondary" href="${root}/user/change-pw">
                    <i class="bi bi-pass"></i>
                    비밀번호 변경
                </a>
            </div>
            <hr>
            <div class="d-flex flex-column d-sm-block gap-2">
                <a class="btn btn-danger" href="${root}/user/delete">
                    <i class="bi bi-trash"></i>
                    계정 삭제
                </a>
            </div>
        </div>
    </div>
</div>
<!-- 중앙 content end -->

<%@ include file="/WEB-INF/common/footer.jsp" %>
</body>
</html>
