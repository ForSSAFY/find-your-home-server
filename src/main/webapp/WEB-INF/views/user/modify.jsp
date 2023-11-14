<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <%@ include file="/WEB-INF/common/header.jsp" %>
    <title>정보 수정 | HappyHouse</title>
</head>
<body class="min-vh-100 d-flex flex-column">
<%@ include file="/WEB-INF/common/nav-simple.jsp" %>

<!-- 제목 start -->
<div style="height: 72px"></div>
<div class="container" style="max-width: 720px;">
    <div class="container d-flex flex-column mt-5">
        <h3 class="text-dark fw-bold mb-0">
            <a class="page-link d-inline" href="${root}/user"><i class="bi bi-arrow-left"></i></a>
            정보 수정
        </h3>
        <hr class="border border-dark">
    </div>
</div>
<!-- 제목 end -->

<!-- 중앙 content start -->
<div class="container" style="max-width: 720px;">
    <form action="${root}/user/modify" method="post" class="container d-flex flex-column gap-3 mt-2">
        <div class="mypage__info-row">
            <label for="userid">아이디</label>
            <input type="text" class="form-control-plaintext" id="userid" name="userid" value="${user.userId}" placeholder="" readonly>
        </div>
        <div class="mypage__info-row">
            <label for="username">이름</label>
            <input type="text" class="form-control" id="username" name="username" value="${user.userName}" placeholder="" required>
        </div>
        <div class="mypage__info-row">
            <label for="address">주소</label>
            <input type="text" class="form-control" id="address" name="address" value="${user.address}" placeholder="" required>
        </div>
        <div class="mypage__info-row">
            <label for="phone">전화번호</label>
            <input type="tel" class="form-control" id="phone" name="phone" value="${user.phone}" placeholder="" required>
        </div>
        <div class="d-flex flex-column d-sm-block gap-2">
            <button type="submit" class="btn btn-primary">
                <i class="bi bi-check-lg"></i>
                수정
            </button>
            <a href="${root}/user" class="btn btn-secondary">
                <i class="bi bi-x-lg"></i>
                취소
            </a>
        </div>
    </form>
</div>
<!-- 중앙 content end -->

<%@ include file="/WEB-INF/common/footer.jsp" %>
</body>
</html>

