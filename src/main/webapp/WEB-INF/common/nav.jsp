<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<nav class="navbar navbar-expand-lg navbar-light bg-light shadow fixed-top py-3">
    <div class="container" style="min-height: 40px;">
        <a href="${root}/" class="navbar-brand fw-bold mx-4">
            <h4 class="mb-0 text-primary">
                <i class="bi bi-house"></i>
                HappyHouse
            </h4>
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="${root}/notice">공지사항</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="#">오늘의 뉴스</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="${root}/feed">관심지역</a>
                </li>
            </ul>
            <!-- 로그인 전 -->
            <c:if test="${empty user}">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="${root}/user/register">회원가입</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="${root}/user/login">로그인</a>
                    </li>
                </ul>
            </c:if>
            <!-- 로그인 후 -->
            <c:if test="${!empty user}">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="${root}/user/logout">로그아웃</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="${root}/user">마이페이지</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                           data-bs-toggle="dropdown"
                           aria-expanded="false">
                            관리자
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li>
                                <a class="dropdown-item" href="#" data-bs-toggle="modal"
                                   data-bs-target="#pollModal">투표만들기</a>
                            </li>
                            <li><a class="dropdown-item" href="#">차트보기</a></li>
                            <li><a class="dropdown-item" href="#">회원관리</a></li>
                        </ul>
                    </li>
                </ul>
            </c:if>
        </div>
    </div>
</nav>