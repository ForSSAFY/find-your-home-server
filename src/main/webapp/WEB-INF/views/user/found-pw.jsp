<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <div class="card-body d-flex flex-column p-4 p-sm-5">
                <h4 class="text-primary">
                    <i class="bi bi-house"></i>
                    HappyHouse
                </h4>
                <h3 class="card-title mt-3 mb-2">비밀번호 찾기</h3>
                <c:if test="${empty foundPw}">
                    일치하는 회원이 없습니다.
                    <a href="${root}/user/find-pw" class="btn btn-primary mt-4">돌아가기</a>
                </c:if>
                <c:if test="${!empty foundPw}">
                    당신의 비밀번호는 "${foundPw}"입니다.
                    <a href="${root}/user/login" class="btn btn-primary mt-4">로그인</a>
                </c:if>
            </div>
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
