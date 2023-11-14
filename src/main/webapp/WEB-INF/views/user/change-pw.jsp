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
    <form action="${root}/user/change-pw" method="post" id="modifyForm" class="container d-flex flex-column gap-3 mt-2">
        <div class="mypage__info-row">
            <label for="pwCurrent">현재 비밀번호</label>
            <input type="password" class="form-control" id="pwCurrent" name="pwcurrent" required>
        </div>
        <div class="mypage__info-row">
            <label for="pw">새 비밀번호</label>
            <input type="password" class="form-control" id="pw" name="pw" required>
        </div>
        <div class="mypage__info-row">
            <label for="pwRepeat">새 비밀번호 확인</label>
            <input type="password" class="form-control" id="pwRepeat" name="pwrepeat" required>
        </div>
        <div class="d-flex flex-column d-sm-block gap-2">
            <button type="button" class="btn btn-primary" onclick="onModify()">
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
<script>
    const form = document.querySelector("#modifyForm");
    const pwCurrent = document.querySelector("#pwCurrent");
    const pw = document.querySelector("#pw");
    const pwRepeat = document.querySelector("#pwRepeat");

    function onModify() {
        if (pw.value !== pwRepeat.value) {
            alert("비밀번호 확인이 다릅니다.");
            return;
        }
        if (pwCurrent.value && pw.value && pwRepeat.value) {
            form.submit();
        }
    }
</script>
</html>
