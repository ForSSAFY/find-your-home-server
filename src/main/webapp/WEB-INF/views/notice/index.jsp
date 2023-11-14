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
<div class="container" style="max-width: 960px;">
    <div class="table-responsive-sm mt-5 text-end">
        <table class="table table-hover">
            <thead>
            <tr class="text-center">
                <th scope="col">글번호</th>
                <th scope="col">제목</th>
                <th scope="col">작성자</th>
                <th scope="col">조회수</th>
                <th scope="col">작성일</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="notice" items="${notices}">
                <tr class="text-center">
                    <th scope="row">${notice.noticeNo}</th>
                    <td>
                        <a href="${root}/notice/${notice.noticeNo}"
                           class="article-title link-dark"
                           data-no="${notice.noticeNo}"
                           style="text-decoration: none"
                        >
                                ${notice.subject}
                        </a>
                    </td>
                    <td>${notice.userId}</td>
                    <td>${notice.hit}</td>
                    <td>${notice.registerTime}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <a href="${root}/notice/write" class="btn btn-outline-primary me-2"><i class="bi bi-pencil"></i> 글쓰기</a>
    </div>
</div>
<!-- 중앙 content end -->
<%@ include file="/WEB-INF/common/footer.jsp" %>
</body>
</html>
