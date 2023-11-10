<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <%@ include file="/WEB-INF/common/header.jsp" %>
    <title>로그인 | HappyHouse</title>
</head>
<body>
<!-- 중앙 content start -->
<div class="parallax min-vh-100 d-flex align-items-start align-items-sm-center">
    <div class="container p-0" style="max-width: 448px">
        <div class="card shadow-lg">
            <form action="${root}/user/login" method="post" id="loginForm" class="card-body d-flex flex-column p-4 p-sm-5">
                <h4 class="text-primary">
                    <i class="bi bi-house"></i>
                    HappyHouse
                </h4>
                <h3 class="card-title mt-3">로그인</h3>
                <input type="text" name="userid" id="userid" class="form-control mt-2" placeholder="아이디" required/>
                <input type="password" name="pw" id="pw" class="form-control mt-2" placeholder="비밀번호" required/>
                <button type="button" class="btn btn-primary mt-4" onclick="onLogin()">로그인</button>
                <div class="d-flex justify-content-center gap-4 mt-4">
                    <a href="${root}/user/find-pw" class="link-secondary">비밀번호 찾기</a>
                    |
                    <a href="${root}/user/register" class="link-secondary">회원가입</a>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- 중앙 content end -->

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
<script>
    const form = document.querySelector("#loginForm");
    const userid = document.querySelector("#userid");
    const pw = document.querySelector("#pw");

    function onLogin() {
        if (userid.value && pw.value) {
            form.submit();
        }
    }
</script>
</body>
</html>
