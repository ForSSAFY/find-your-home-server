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
        <form id="form-register" method="POST" action="${root}/notice/write">
            <div class="mb-3">
                <label for="userid" class="form-label">작성자 ID : </label>
                <input
                        type="text"
                        class="form-control"
                        id="userid"
                        name="userid"
                        value="${user.userId}"
                        placeholder="작성자ID"
                        readonly
                />
            </div>
            <div class="mb-3">
                <label for="subject" class="form-label">제목 : </label>
                <input
                        type="text"
                        class="form-control"
                        id="subject"
                        name="subject"
                        placeholder="제목..."
                />
            </div>
            <div class="mb-3">
                <label for="content" class="form-label">내용 : </label>
                <textarea class="form-control" id="content" name="content" rows="7"></textarea>
            </div>
            <div class="col-auto text-center">
                <button type="button" id="btn-register" class="btn btn-outline-primary mb-3">
                    글작성
                </button>
                <button type="reset" class="btn btn-outline-danger mb-3">초기화</button>
            </div>
        </form>
    </div>
</div>
<!-- 중앙 content end -->
<%@ include file="/WEB-INF/common/footer.jsp" %>
<script>
    document.querySelector("#btn-register").addEventListener("click", function () {
        if (!document.querySelector("#subject").value) {
            alert("제목 입력!!");
            return;
        } else if (!document.querySelector("#content").value) {
            alert("내용 입력!!");
            return;
        } else {
            let form = document.querySelector("#form-register");
            form.submit();
        }
    });
</script>

</body>
</html>
