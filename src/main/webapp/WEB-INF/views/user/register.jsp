<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <%@ include file="/WEB-INF/common/header.jsp" %>
    <title>회원가입 | HappyHouse</title>
</head>
<body>
<!-- 중앙 content start -->
<div class="parallax min-vh-100 d-flex align-items-start align-items-sm-center">
    <div class="container p-0" style="max-width: 448px">
        <div class="card shadow-lg">
            <form action="${root}/user/register" method="post" id="registerForm"
                  class="card-body d-flex flex-column p-4 p-sm-5">
                <h4 class="text-primary">
                    <i class="bi bi-house"></i>
                    HappyHouse
                </h4>
                <h3 class="card-title mt-3">회원가입</h3>
                <input type="text" class="form-control mt-2" id="userid" name="userid" placeholder="아이디" required>
                <input type="password" class="form-control mt-2" id="pw" name="pw" placeholder="비밀번호" required>
                <input type="password" class="form-control mt-2" id="pwrepeat" name="pwrepeat" placeholder="비밀번호 확인" required>
                <hr class="mt-4">
                <input type="text" class="form-control mt-2" id="username" name="username" placeholder="이름" required>
                <input type="text" class="form-control mt-2" id="address" name="address" placeholder="주소" required>
                <input type="tel" class="form-control mt-2" id="phone" name="phone" placeholder="전화번호" required>
                <button type="button" class="btn btn-primary mt-4" onclick="onRegister()">등록</button>
                <div class="d-flex justify-content-center gap-4 mt-4">
                    <a href="${root}/user/login" class="link-secondary">로그인</a>
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
    const form = document.querySelector("#registerForm");
    const userid = document.querySelector("#userid");
    const pw = document.querySelector("#pw");
    const pwRepeat = document.querySelector("#pwrepeat");
    const username = document.querySelector("#username");
    const address = document.querySelector("#address");
    const phone = document.querySelector("#phone");

    function onRegister() {
        if (pw.value !== pwRepeat.value) {
            alert("비밀번호 확인이 다릅니다.");
            return;
        }
        if (userid.value && pw.value && username.value && address.value && phone.value) {
            form.submit();
        }
    }
</script>
</body>
</html>
