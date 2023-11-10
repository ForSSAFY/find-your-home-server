<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <%@ include file="/WEB-INF/common/header.jsp" %>
    <title>관심 지역 | HappyHouse</title>
</head>
<body class="min-vh-100 d-flex flex-column">
<%@ include file="/WEB-INF/common/nav.jsp" %>

<!-- 제목 start -->
<div style="height: 72px"></div>
<div class="parallax" style="padding: 50px 0;">
    <div class="container d-flex flex-column">
        <h1 class="text-light fw-bold ms-4">관심 지역</h1>
        <hr class="border border-light mb-0">
    </div>
</div>
<!-- 제목 end -->

<!-- 중앙 content start -->
<div class="container mt-3" style="max-width: 960px;">
    <div class="row">
        <div class="col-12 col-lg-4 order-1 order-lg-0">
            <div class="d-flex justify-content-end gap-4 mt-3 me-2">
                <button type="button" class="btn page-link text-primary not-edit-only">
                    <i class="bi bi-plus-lg"></i>
                    추가
                </button>
                <button type="button" class="btn page-link text-primary not-edit-only" onclick="editMode()">
                    <i class="bi bi-pencil"></i>
                    편집
                </button>
                <button type="button" class="btn page-link text-primary edit-only" onclick="stopEditMode()">
                    <i class="bi bi-save"></i>
                    저장
                </button>
            </div>
            <div class="list-group mt-2" id="aptList">
                <!-- <div class="list-group-item">
                    관심 지역이 없습니다.
                </div> -->
                <div class="list-group-item p-3">
                    <div class="d-flex align-items-start">
                        <div class="fs-5 fw-bold me-auto">
                            서울 강남구 역삼동
                            <span class="badge bg-primary">대표</span>
                        </div>
                        <a href="#" class="link-danger fs-5 edit-only">
                            <i class="bi bi-trash"></i>
                        </a>
                    </div>
                    <div class="mt-3">
                        <button type="button" class="btn btn-outline-primary btn-sm not-edit-only">지도에 표시</button>
                        <button type="button" class="btn btn-outline-secondary btn-sm edit-only disabled">대표 지역으로
                            설정</button>
                    </div>
                </div>
                <div class="list-group-item p-3">
                    <div class="d-flex align-items-center">
                        <div class="fs-5 fw-bold me-auto">
                            대전 유성구 덕명동
                        </div>
                        <a href="#" class="link-danger fs-5 edit-only">
                            <i class="bi bi-trash"></i>
                        </a>
                    </div>
                    <div class="mt-3">
                        <button type="button" class="btn btn-outline-primary btn-sm not-edit-only">지도에 표시</button>
                        <button type="button" class="btn btn-outline-secondary btn-sm edit-only">대표 지역으로 설정</button>
                    </div>
                </div>
                <div class="list-group-item p-3">
                    <div class="d-flex align-items-center">
                        <div class="fs-5 fw-bold me-auto">
                            경북 구미시 임수동
                        </div>
                        <a href="#" class="link-danger fs-5 edit-only">
                            <i class="bi bi-trash"></i>
                        </a>
                    </div>
                    <div class="mt-3">
                        <button type="button" class="btn btn-outline-primary btn-sm not-edit-only">지도에 표시</button>
                        <button type="button" class="btn btn-outline-secondary btn-sm edit-only">대표 지역으로 설정</button>
                    </div>
                </div>
                <div class="list-group-item p-3">
                    <div class="d-flex align-items-center">
                        <div class="fs-5 fw-bold me-auto">
                            광주 광산구 오선동
                        </div>
                        <a href="#" class="link-danger fs-5 edit-only">
                            <i class="bi bi-trash"></i>
                        </a>
                    </div>
                    <div class="mt-3">
                        <button type="button" class="btn btn-outline-primary btn-sm not-edit-only">지도에 표시</button>
                        <button type="button" class="btn btn-outline-secondary btn-sm edit-only">대표 지역으로 설정</button>
                    </div>
                </div>
                <div class="list-group-item p-3">
                    <div class="d-flex align-items-center">
                        <div class="fs-5 fw-bold me-auto">
                            부산 강서구 송정동
                        </div>
                        <a href="#" class="link-danger fs-5 edit-only">
                            <i class="bi bi-trash"></i>
                        </a>
                    </div>
                    <div class="mt-3">
                        <button type="button" class="btn btn-outline-primary btn-sm not-edit-only">지도에 표시</button>
                        <button type="button" class="btn btn-outline-secondary btn-sm edit-only">대표 지역으로 설정</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- kakao map start -->
        <div class="col">
            <div id="map" class="mt-3" style="width: 100%; height: max(50vh, 400px)"></div>
        </div>
        <!-- kakao map end -->
    </div>

</div>
<!-- 중앙 content end -->
<%@ include file="/WEB-INF/common/footer.jsp" %>
<script>
    const HIDDEN = "d-none";
    const EDIT_ONLY = "edit-only"; // 편집 상태에서만 보이는 엘리먼트
    const NOT_EDIT_ONLY = "not-edit-only"; // 편집 상태가 아닐 때만 보이는 엘리먼트

    let isEdit = false;

    addEventListener("load", () => {
        stopEditMode();
    });

    function editMode() {
        isLogin = true;
        onEditMode();
    }

    function stopEditMode() {
        isLogin = false;
        onNotEditMode();
    }

    function onEditMode() {
        for (const element of document.getElementsByClassName(EDIT_ONLY)) {
            element.classList.remove(HIDDEN);
        }
        for (const element of document.getElementsByClassName(NOT_EDIT_ONLY)) {
            element.classList.add(HIDDEN);
        }
    }

    function onNotEditMode() {
        for (const element of document.getElementsByClassName(EDIT_ONLY)) {
            element.classList.add(HIDDEN);
        }
        for (const element of document.getElementsByClassName(NOT_EDIT_ONLY)) {
            element.classList.remove(HIDDEN);
        }
    }

    /**
     * @param {string} name
     */
    function shortenSidoName(name) {
        if (name.endsWith("남도")) return name[0] + "남";
        if (name.endsWith("북도")) return name[0] + "북";
        return name.substring(0, 2);
    }

    <%--// 카카오지도--%>
    <%--var mapContainer = document.getElementById("map"); // 지도를 표시할 div--%>
    <%--var mapOption = {--%>
    <%--    center: new kakao.maps.LatLng(37.500613, 127.036431), // 지도의 중심좌표--%>
    <%--    level: 3, // 지도의 확대 레벨--%>
    <%--};--%>

    <%--// 지도를 표시할 div와 지도 옵션으로 지도를 생성합니다--%>
    <%--var map = new kakao.maps.Map(mapContainer, mapOption);--%>

    <%--var marker, infowindow;--%>

    <%--function viewMap(apt, address, price, area) {--%>
    <%--    if (marker != null && infowindow != null) {--%>
    <%--        marker.setMap(null);--%>
    <%--        infowindow.close();--%>
    <%--    }--%>
    <%--    // 주소-좌표 변환 객체를 생성합니다--%>
    <%--    var geocoder = new kakao.maps.services.Geocoder();--%>

    <%--    // 주소로 좌표를 검색합니다--%>
    <%--    geocoder.addressSearch(address, function (result, status) {--%>
    <%--        // 정상적으로 검색이 완료됐으면--%>
    <%--        if (status === kakao.maps.services.Status.OK) {--%>
    <%--            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);--%>

    <%--            // 결과값으로 받은 위치를 마커로 표시합니다--%>
    <%--            marker = new kakao.maps.Marker({--%>
    <%--                map: map,--%>
    <%--                position: coords,--%>
    <%--            });--%>

    <%--            // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다--%>
    <%--            map.setCenter(coords);--%>

    <%--            //m2을 평수로 변환합니다.--%>
    <%--            let squareFeet = area * 0.3048;--%>

    <%--            // 마커를 mouseover했을 때 마커 위에 표시할 인포윈도우를 생성합니다--%>
    <%--            var iwContent = `<div style="width:auto;white-space:nowrap;padding:0 10px"><div style="text-align:center;padding:6px 0;" class="text-primary fw-bold">${apt}</div>--%>
    <%--  <div style="text-align:center;padding:6px 0;" class="">\${address}</div>--%>
    <%--  <div style="text-align:center;padding:6px 0;" class="">\${squareFeet.toFixed(--%>
    <%--    1--%>
    <%--  )}평</div></div>`; // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다--%>

    <%--            //상세 인포 윈도우를 생성합니다.--%>
    <%--            var moreInfowindow = new kakao.maps.InfoWindow({--%>
    <%--                content: iwContent,--%>
    <%--            });--%>

    <%--            // 마커에 mouseover이벤트를 등록합니다--%>
    <%--            kakao.maps.event.addListener(marker, "mouseover", function () {--%>
    <%--                // 마커 위에 인포윈도우를 표시합니다--%>
    <%--                moreInfowindow.open(map, marker);--%>
    <%--            });--%>

    <%--            // 마커에 mouseout이벤트를 등록합니다.--%>
    <%--            kakao.maps.event.addListener(marker, "mouseout", function () {--%>
    <%--                // 마커 위에 인포윈도우를 사라지게 합니다.--%>
    <%--                moreInfowindow.close();--%>
    <%--            });--%>
    <%--        }--%>
    <%--    });--%>
    <%--}--%>

</script>
</body>
</html>
