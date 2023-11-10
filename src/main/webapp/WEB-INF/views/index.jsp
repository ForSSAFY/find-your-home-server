<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <%@ include file="/WEB-INF/common/header.jsp" %>
    <title>HappyHouse</title>
</head>
<body class="min-vh-100 d-flex flex-column">
<%@ include file="/WEB-INF/common/nav.jsp" %>

<!-- 제목 start -->
<div style="height: 72px"></div>
<div class="parallax" style="padding: 150px 0">
    <div class="container d-flex flex-column align-items-center">
        <h1 class="text-light fw-bold">HappyHouse</h1>
        <hr class="border border-light w-100 mb-0">
        <h5 class="text-light mt-4">우리를 위한 집</h5>
        <a href="${root}/price" class="btn btn-primary btn-lg shadow-lg mt-4">매매 정보 확인 <i class="bi bi-arrow-right"></i></a>
    </div>
</div>
<!-- 제목 end -->

<!-- 중앙 content start -->
<div class="container" style="max-width: 960px;">
    <div class=" row mt-5">
        <div class="col-12 col-md-6">
            <div class="d-flex justify-content-between px-2">
                <h4>
                    <i class="bi bi-piggy-bank"></i>
                    지혜롭게 내집 마련하기
                </h4>
                <a href="#" class="nav-link"><i class="bi bi-plus fs-4"></i></a>
            </div>
            <hr class="my-0">
            <ul class="list-group list-group-flush">
                <li class="list-group-item">
                    <a href="#" class="nav-link">가용자금 확인 및 대출 계획</a>
                </li>
                <li class="list-group-item">
                    <a href="#" class="nav-link">집 종류 및 지역 선택</a>
                </li>
                <li class="list-group-item">
                    <a href="#" class="nav-link">정보 수집 & 시세파악</a>
                </li>
                <li class="list-group-item">
                    <a href="#" class="nav-link">부동산 방문 & 집구경</a>
                </li>
                <li class="list-group-item">
                    <a href="#" class="nav-link">계약 및 잔금 치르기</a>
                </li>
                <li class="list-group-item">
                    <a href="#" class="nav-link">소유권 이전등기</a>
                </li>
                <li class="list-group-item">
                    <a href="#" class="nav-link">인테리어 공사</a>
                </li>
            </ul>
        </div>
        <div class="col-12 col-md-6">
            <div class="d-flex justify-content-between px-2">
                <h4>
                    <i class="bi bi-newspaper"></i>
                    부동산 뉴스
                </h4>
                <a href="#" class="nav-link"><i class="bi bi-plus fs-4"></i></a>
            </div>
            <hr class="my-0">
            <ul class="list-group list-group-flush">
                <li class="list-group-item">
                    <a href="#" class="nav-link">부동산원, 월드스마트시티엑스포서 부동산 거래 관리 시스템 소개</a>
                </li>
                <li class="list-group-item">
                    <a href="#" class="nav-link">강남 부동산만 3채 '월세의 여왕'…"진짜 OOO에 투자해라"</a>
                </li>
                <li class="list-group-item">
                    <a href="#" class="nav-link">[게시판] SK증권, VIP고객 대상 세무·부동산 상담 서비스</a>
                </li>
                <li class="list-group-item">
                    <a href="#" class="nav-link">[그래픽] 산업대출 증가폭 1년만에 확대…부동산·건설업 소폭↑</a>
                </li>
                <li class="list-group-item">
                    <a href="#" class="nav-link">중국 선전, 홍콩 · 마카오 주민에 상업용 부동산 구매 허용</a>
                </li>
                <li class="list-group-item">
                    <a href="#" class="nav-link">낮아진 대출문턱·부동산 거래 회복에 2분기 산업대출 증가폭↑</a>
                </li>
                <li class="list-group-item">
                    <a href="#" class="nav-link">LH 임직원 부동산 매매 신고 0건…"혁신안 유명무실"비판</a>
                </li>
            </ul>
        </div>
    </div>
</div>
<!-- 중앙 content end -->
<%@ include file="/WEB-INF/common/footer.jsp" %>
</body>
</html>
