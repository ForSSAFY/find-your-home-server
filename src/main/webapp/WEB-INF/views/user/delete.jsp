<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <%@ include file="/WEB-INF/common/header.jsp" %>
    <title>회원 탈퇴 | HappyHouse</title>
</head>
<body class="min-vh-100 d-flex flex-column">
<%@ include file="/WEB-INF/common/nav-simple.jsp" %>

<!-- 제목 start -->
<div style="height: 72px"></div>
<div class="container" style="max-width: 720px;">
    <div class="container d-flex flex-column mt-5">
        <h3 class="text-dark fw-bold mb-0">
            <a class="page-link d-inline" href="${root}/user"><i class="bi bi-arrow-left"></i></a>
            회원 탈퇴
        </h3>
        <hr class="border border-dark">
    </div>
</div>
<!-- 제목 end -->

<!-- 중앙 content start -->
<div class="container" style="max-width: 720px;">
    <form action="${root}/user/delete" method="post" id="deleteForm" class="container d-flex flex-column gap-3 mt-2">
        <div class="mypage__info-row">
            <div>아이디</div>
            <div class="text-danger fw-bold">${user.userId}</div>
        </div>
        <p class="text-body m-0">
            탈퇴할 경우 되돌릴 수 없습니다.<br>
            아래에 탈퇴하려는 계정의 아이디를 입력해주세요.
        </p>
        <input type="text" class="form-control" name="userid" id="userid" placeholder="아이디" required>
        <div class="d-flex flex-column d-sm-block gap-2">
            <button type="button" class="btn btn-danger disabled" id="deleteButton" onclick="onClick()">
                <i class="bi bi-trash"></i>
                삭제
            </button>
            <a class="btn btn-secondary" href="${root}/user">
                <i class="bi bi-x-lg"></i>
                취소
            </a>
        </div>
    </form>
</div>
<!-- 중앙 content end -->

<%@ include file="/WEB-INF/common/footer.jsp" %>
<script>
    const form = document.querySelector("#deleteForm");
    const userid = document.querySelector("#userid");
    const deleteButton = document.querySelector("#deleteButton");

    userid.addEventListener("change", () => {
        if (userid.value === "${user.userId}") {
            deleteButton.classList.remove("disabled");
        } else {
            deleteButton.classList.add("disabled");
        }
    })

    function onClick() {
        form.submit();
    }
</script>
</body>
</html>
