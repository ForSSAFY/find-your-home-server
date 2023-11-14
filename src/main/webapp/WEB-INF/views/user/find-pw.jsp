<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <%@ include file="/WEB-INF/common/header.jsp" %>
    <title>비밀번호 찾기 | HappyHouse</title>
</head>
<body>
<div class="parallax min-vh-100 d-flex align-items-start align-items-sm-center">
    <!-- 중앙 content start -->
    <div class="container p-0" style="max-width: 448px">
        <div class="card shadow-lg">
            <form action="${root}/user/find-pw" method="post" id="findForm"
                  class="card-body d-flex flex-column p-4 p-sm-5">
                <h4 class="text-primary">
                    <i class="bi bi-house"></i>
                    HappyHouse
                </h4>
                <h3 class="card-title mt-3">비밀번호 찾기</h3>
                <input type="text" name="username" id="username" class="form-control mt-2" placeholder="이름"
                       required/>
                <input type="text" name="userid" id="userid" class="form-control mt-2" placeholder="아이디" required/>
                <input type="tel" name="phone" id="phone" class="form-control mt-2" placeholder="전화번호" required/>
                <button type="button" class="btn btn-primary mt-4" onclick="onFind()">찾기</button>
                <div class="d-flex justify-content-center gap-4 mt-4">
                    <a href="${root}/user/register" class="link-secondary">회원가입</a>
                </div>
            </form>
        </div>
    </div>
    <!-- 중앙 content end -->
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
<script>
    const form = document.querySelector("#findForm");
    const userid = document.querySelector("#userid");
    const username = document.querySelector("#username");
    const phone = document.querySelector("#phone");

    function onFind() {
        if (userid.value && username.value && phone.value) {
            form.submit();
        }
    }
</script>
</body>
</html>
