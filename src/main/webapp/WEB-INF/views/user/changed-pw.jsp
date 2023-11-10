<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <%@ include file="/WEB-INF/common/header.jsp" %>
    <title>비밀번호 변경 | HappyHouse</title>
</head>
<body class="min-vh-100 d-flex flex-column">
<%@ include file="/WEB-INF/common/nav-simple.jsp" %>

<!-- 제목 start -->
<div style="height: 72px"></div>
<div class="container" style="max-width: 720px;">
    <div class="container d-flex flex-column mt-5">
        <h3 class="text-dark fw-bold mb-0">
            <a class="page-link d-inline" href="${root}/user"><i class="bi bi-arrow-left"></i></a>
            비밀번호 변경
        </h3>
        <hr class="border border-dark">
    </div>
</div>
<!-- 제목 end -->

<!-- 중앙 content start -->
<div class="container" style="max-width: 720px;">
    <div class="container d-flex flex-column gap-3 mt-2">
        <c:if test="${!empty changedPw}">
            비밀번호 변경에 성공했습니다.
            <div class="d-flex flex-column d-sm-block gap-2">
                <a href="${root}/user" class="btn btn-primary">
                    <i class="bi bi-check-lg"></i>
                    돌아가기
                </a>
            </div>
        </c:if>
        <c:if test="${empty changedPw}">
            비밀번호 변경에 실패했습니다.
            <div class="d-flex flex-column d-sm-block gap-2">
                <a href="${root}/user/change-pw" class="btn btn-secondary">
                    <i class="bi bi-x-lg"></i>
                    돌아가기
                </a>
            </div>
        </c:if>
    </div>
</div>
<!-- 중앙 content end -->

<%@ include file="/WEB-INF/common/footer.jsp" %>
</html>
