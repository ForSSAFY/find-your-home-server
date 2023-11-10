<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <%@ include file="/WEB-INF/common/header.jsp" %>
    <title>공지사항 | HappyHouse</title>
</head>
<body class="min-vh-100 d-flex flex-column">
<%@ include file="/WEB-INF/common/nav.jsp" %>

<!-- 제목 start -->
<div style="height: 72px"></div>
<div class="parallax" style="padding: 50px 0;">
    <div class="container d-flex flex-column">
        <h1 class="text-light fw-bold ms-4">공지사항</h1>
        <hr class="border border-light mb-0">
    </div>
</div>
<!-- 제목 end -->

<!-- 중앙 content start -->
<div class="container" style="max-width: 720px;">
    <div class="container d-flex flex-column mt-5">
        <h2>${notice.subject}</h2>
        <div class="d-flex justify-content-between">
            <div class="d-flex gap-2 align-items-center">
                <img src="https://raw.githubusercontent.com/twbs/icons/main/icons/person-fill.svg"
                     class="avatar bg-light p-2"/>
                <div class="d-flex flex-column">
                    <div class="fw-bold">${notice.userId}</div>
                    <div class="text-secondary fw-light me-auto"> ${notice.registerTime} 조회 : ${notice.hit} </div>
                </div>
            </div>
            <div>
                <br>
                댓글 : 0
            </div>
        </div>
        <hr>
        <div class="text-secondary">
            ${notice.content}
        </div>
        <hr>
        <div class="d-flex justify-content-end">
            <a href="${root}/notice" id="btn-list" class="btn btn-outline-primary mb-3">
                글목록
            </a>
            <c:if test="${user.userId eq notice.userId}">
                <a href="${root}/notice/${notice.noticeNo}/modify" id="btn-mv-modify"
                   class="btn btn-outline-success mb-3 ms-1">
                    글수정
                </a>
                <form action="${root}/notice/${notice.noticeNo}/delete" method="post">
                    <button type="submit" id="btn-delete" class="btn btn-outline-danger mb-3 ms-1">
                        글삭제
                    </button>
                </form>
            </c:if>
        </div>
    </div>
</div>
<!-- 중앙 content end -->
<%@ include file="/WEB-INF/common/footer.jsp" %>
</body>
</html>
