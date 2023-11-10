<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/common/header.jsp"%>
<title>매매 정보 | HappyHouse</title>
</head>
<body class="min-vh-100 d-flex flex-column">
	<%@ include file="/WEB-INF/common/nav.jsp"%>
	<!-- 제목 start -->
	<div style="height: 72px"></div>
	<div class="parallax" style="padding: 50px 0;">
		<div class="container d-flex flex-column">
			<h1 class="text-light fw-bold ms-4">매매 정보</h1>
			<hr class="border border-light mb-0">
		</div>
	</div>
	<!-- 제목 end -->
	<!-- 중앙 content start -->
	<div class="container mt-4">
		<!-- 아파트 매매 정보 검색 start -->
		<div class="row justify-content-center mb-2">
			<div class="form-group col-12 col-lg-3 mt-2">
				<label class="form-label">시/도</label> <select class="form-select"
					id="sido" title="sido">
					<option selected>선택</option>
					<c:forEach items="${sidoList}" var="sido">
						<option value="${sido}">${sido}</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group col-12 col-lg-3 mt-2">
				<label class="form-label">구/군</label> <select class="form-select"
					id="gugun" title="gugun">
					<option selected>선택</option>
				</select>
			</div>
			<div class="form-group col-12 col-lg-3 mt-2">
				<label class="form-label">동</label> <select class="form-select"
					id="dong" title="dong">
					<option selected>선택</option>
				</select>
			</div>
			<div class="col-12 col-lg-3 mt-3 mt-lg-2">
				<label class="form-label d-none d-lg-block">&nbsp;</label>
				<div class="d-flex gap-3">
					<button type="button" id="list-btn"
						class="btn btn-primary flex-fill" onclick="updateList()">
						<i class="bi bi-search"></i> 조회
					</button>
					<button type="button" id="date-btn" class="btn btn-secondary"
						title="기준일 설정" data-bs-toggle="collapse"
						data-bs-target="#collapseDate" aria-expanded="false"
						aria-controls="collapseDate">
						<i class="bi bi-gear"></i>
					</button>
				</div>
			</div>
			<div class="collapse" id="collapseDate">
				<div class="card mt-3 mt-lg-2">
					<div class="card-body">
						<div class="card-title">기준일 설정</div>
						<div class="row">
							<div class="form-group col-6">
								<label class="form-label">연도</label> <select class="form-select"
									id="year" title="year">
									<c:forEach items="${years}" var="year">
										<option value="${year}">${year}년</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group col-6">
								<label class="form-label">월</label> <select class="form-select"
									id="month" title="month">
								</select>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- 정렬 -->
		<div class="row mb-2">
			<div class="form-group col-12 col-lg-3 mt-2">
				<label class="form-label">정렬</label> <select class="form-select"
					id="order-select" onchange="sortSelect(this)"
					style="display: inline;">
					<option value="date">거래일자</option>
					<option value="amount">실거래가</option>
					<option value="area">평수</option>
				</select>
			</div>
			<div class="col-sm-2">
				<input type="checkbox" name="selectOrder" id="selectOrder"
					onclick="changeOrderImg()" checked> <label
					for="selectOrder"> <img id="orderImg" class="order-img"
					src="/assets/img/up.png">
				</label>
			</div>
		</div>


		<div class="row">
			<div class="col-12 col-lg-4 order-1 order-lg-0">
				<div class="list-group mt-3" id="aptList">
					<div class="list-group-item">지역을 선택하고 조회해주세요.</div>
				</div>
			</div>
			<!-- kakao map start -->
			<div class="col">
				<div id="map" class="mt-3"
					style="width: 100%; height: max(50vh, 400px)"></div>
			</div>
			<!-- kakao map end -->
		</div>
		<!-- 아파트 매매 정보 검색 end -->
	</div>
	<!-- 중앙 content end -->

	<%@ include file="/WEB-INF/common/footer.jsp"%>
	<script>
    const sidoSelect = document.querySelector("#sido");
    const gugunSelect = document.querySelector("#gugun");
    const dongSelect = document.querySelector("#dong");
    const yearSelect = document.querySelector("#year");
    const monthSelect = document.querySelector("#month");
    
    const amount ='dealAmount', area = 'area';
    const asc = false, desc = true;
    const year = 'dealYear', month = 'dealMonth', day = 'dealDay';


    sidoSelect.addEventListener("change", () => {
        const value = sidoSelect[sidoSelect.selectedIndex].value;
        if (value !== "선택") {
            fetchAndSetOptions("${root}/price/gugun", {sido: value}, gugunSelect);
        } else {
            setOptions(gugunSelect, [])
            setOptions(dongSelect, [])
        }
    });
    gugunSelect.addEventListener("change", () => {
        const sido = sidoSelect[sidoSelect.selectedIndex].value;
        const value = gugunSelect[gugunSelect.selectedIndex].value;
        if (value !== "선택") {
            fetchAndSetOptions("${root}/price/dong", {sido, gugun: value}, dongSelect);
        } else {
            setOptions(dongSelect, [])
        }
    });

    updateMonths(); // manually fire first event handler

    yearSelect.addEventListener("change", updateMonths);

    function updateMonths() {
        const date = new Date();
        let month = date.getMonth() + 1;
        let monthOpt = "";
        let m = yearSelect[yearSelect.selectedIndex].value == date.getFullYear() ? month : 13;
        for (let i = 1; i < m; i++) {
            monthOpt += `<option value="\${i < 10 ? "0" + i : i}">\${i}월</option>`;
        }
        monthSelect.innerHTML = monthOpt;
        monthSelect.selectedIndex = Math.max(0, month - 2);
    } // 자동으로 가장 최근 월로 설정


    function fetchAndSetOptions(url, params, select) {
        fetch(url + "?" + new URLSearchParams(params))
            .then(res => res.json())
            .then(json => setOptions(select, json));
    }

    function setOptions(select, list) {
        select.length = 1;
        let html = "";
        list.forEach(str => html += `<option value="` + str + `">` + str + `</option>`);
        select.innerHTML += html;
    }

    const aptList = document.querySelector("#aptList");

    function updateList() {
        fetch("${root}/price/deal?" + new URLSearchParams({
            sido: sidoSelect[sidoSelect.selectedIndex].value,
            gugun: gugunSelect[gugunSelect.selectedIndex].value,
            dong: dongSelect[dongSelect.selectedIndex].value,
            year: yearSelect[yearSelect.selectedIndex].value,
            month: monthSelect[monthSelect.selectedIndex].value,
        }))
            .then(res => res.json())
            .then(json => {
            	window.localStorage.setItem("list", JSON.stringify(json));
                aptList.replaceChildren();
                json.forEach(apt => aptList.appendChild(dataRowToElementMapper(apt)));
            });
    }

    function dataRowToElementMapper(apt) {
        console.log(apt)
        const button = document.createElement("button");
        button.className =
            "list-group-item list-group-item-action d-flex flex-column";

        const nameDiv = document.createElement("div");
        nameDiv.className = "fw-bold";
        nameDiv.appendChild(document.createTextNode(apt.apartmentName));
        button.appendChild(nameDiv);

        const priceDiv = document.createElement("div");
        priceDiv.className = "text-primary fs-5 fw-bold";
        const price = Number(apt.dealAmount.replace(",", ""));
        priceDiv.appendChild(document.createTextNode(`매매 \${formatPrice(price)}`));
        button.appendChild(priceDiv);

        const infoDiv = document.createElement("div");
        var now = new Date(); //건물이 몇년식인지 알기 위해 DATE() 받아옴
        infoDiv.appendChild(
            document.createTextNode(
                `\${apt.dong} · ` +
                `\${apt.area.substring(0, 5)}m² · ` +
                `\${apt.floor}층 · ` +
                `\${apt.buildYear}년(\${now.getFullYear() - apt.buildYear}년차)`
            )
        );
        button.appendChild(infoDiv);

        const dateDiv = document.createElement("div");
        dateDiv.className = "text-muted";
        dateDiv.innerHTML = '<i class="bi bi-calendar-check"></i> ';
        dateDiv.appendChild(document.createTextNode(formatDate(
            apt.dealYear.toString(), apt.dealMonth.toString(), apt.dealDay.toString())));
        button.appendChild(dateDiv);

        let address = `\${apt.dong} \${apt.jibun}`;
        button.addEventListener("click", () =>
            viewMap(
                apt.apartmentName,
                address,
                apt.dealAmount.replace(",", ""),
                apt.area.substring(0, 5)
            )
        );

        return button;
    }

    function formatPrice(price) {
        if (price >= 10000) {
            if (price % 10000 > 0) {
                return `\${formatNumber(Math.floor(price / 10000))}억 \${formatNumber(price % 10000)}`;
            } else {
                return formatNumber(Math.floor(price / 10000)) + "억";
            }
        } else {
            return formatNumber(price);
        }
    }

    function formatNumber(num) {
        return ("" + num).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, ($1) => $1 + ",");
    }

    function formatDate(year, month, day) {
        return `\${year.substring(year.length - 2)}.\${month.padStart(2, "0")}.\${day.padStart(2, "0")}`;
    }

    // 카카오지도
    var mapContainer = document.getElementById("map"); // 지도를 표시할 div
    var mapOption = {
        center: new kakao.maps.LatLng(37.500613, 127.036431), // 지도의 중심좌표
        level: 3, // 지도의 확대 레벨
    };

    // 지도를 표시할 div와 지도 옵션으로 지도를 생성합니다
    var map = new kakao.maps.Map(mapContainer, mapOption);

    let marker, infowindow;
    function viewMap(apt, address, price, area) {
        if (marker != null && infowindow != null) {
            marker.setMap(null);
            infowindow.close();
        }
        // 주소-좌표 변환 객체를 생성합니다
        var geocoder = new kakao.maps.services.Geocoder();

        // 주소로 좌표를 검색합니다
        geocoder.addressSearch(address, function (result, status) {
            // 정상적으로 검색이 완료됐으면
            if (status === kakao.maps.services.Status.OK) {
                var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

                // 결과값으로 받은 위치를 마커로 표시합니다
                marker = new kakao.maps.Marker({
                    map: map,
                    position: coords,
                });

                // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
                map.setCenter(coords);

                //m2을 평수로 변환합니다.
                let squareFeet = area * 0.3048;

                // 마커를 mouseover했을 때 마커 위에 표시할 인포윈도우를 생성합니다
                // prettier-ignore
                var iwContent =
                    `<div style="width:auto;white-space:nowrap;padding:0 10px">` +
                    `<div style="display:flex;align-items:center;justify-content: space-between;">` +
                    `<div style="text-align:center;padding:6px 0;" class="text-primary fw-bold">\${apt}</div>` +
                    `<div style="text-align:center;padding:6px 0; color:black;" class="">\${squareFeet.toFixed(1)}평</div>` +
                    `</div>` +
                    `<div style="text-align:center;padding:6px 0;" class="">\${address}</div>` +
                    `</div>`; // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다

                //상세 인포 윈도우를 생성합니다.
                var moreInfowindow = new kakao.maps.InfoWindow({
                    content: iwContent,
                });

                let _m = marker;
                // 마커에 mouseover이벤트를 등록합니다
                kakao.maps.event.addListener(marker, "mouseover", function () {
                    // 마커 위에 인포윈도우를 표시합니다
                    moreInfowindow.open(map, _m);
                });

                // 마커에 mouseout이벤트를 등록합니다.
                kakao.maps.event.addListener(marker, "mouseout", function () {
                    // 마커 위에 인포윈도우를 사라지게 합니다.
                    moreInfowindow.close();
                });
            }
        });
    }
    
    function sortAmount(order){
    	// 인덱스 1번이면 가격을 기준으로 한다.
    	selectionSort(amount, order);
    	aptList.replaceChildren();
    	let list = JSON.parse(window.localStorage.getItem("list"));
    	list.forEach((apt) => {
    		  const button = dataRowToElementMapper(apt);
    		  aptList.appendChild(button);
    	});
    }

    function sortArea(order){
    	selectionSort(area, order);
    	aptList.replaceChildren();
    	
    	let list = JSON.parse(window.localStorage.getItem("list"));
    	list.forEach((apt) => {
    		  const button = dataRowToElementMapper(apt);
    		  aptList.appendChild(button);
    	});
    }

    function sortDate(order){
    	selectionSortDate(order);
    	aptList.replaceChildren();
    	
    	let list = JSON.parse(window.localStorage.getItem("list"));
    	list.forEach((apt) => {
    		  const button = dataRowToElementMapper(apt);
    		  aptList.appendChild(button);
    	});
    }

    function sortSelect(e){
    	let type = e.value;
    	let order = document.getElementById("selectOrder").checked;
    	if(type === "area"){
    		sortArea(order);
    	}else if(type === "amount"){
    		sortAmount(order);
    	}else{
    		sortDate(order);
    	}
    }


    function selectionSort(type, order){
    	  let list = JSON.parse(window.localStorage.getItem("list"));
    	  let i,j,min;
    	  let n = list.length;
    	  if(order){
    		  for(let i = 0; i < n-1; i++){
    			  min = i;
    			  for(let j = i+1; j < n; j++){
    				  if(parseFloat(list[j][type].replace(/,/g, ''), 10) > parseFloat(list[min][type].replace(/,/g, ''), 10) ) {
    					  min = j
    				  }
    			  }
    			  if(min != i){    	  
    				  let temp = list[i];
    				  list[i] = list[min]
    				  list[min] = temp;
    			  }
    		  }
    	  }else{
    		  for(let i = 0; i < n-1; i++){
    			  min = i;
    			  for(let j = i+1; j < n; j++){
    				  if(parseFloat(list[j][type].replace(/,/g, ''), 10) < parseFloat(list[min][type].replace(/,/g, ''), 10) ) {
    					  min = j
    				  }    	  
    			  }
    			  if(min != i){    	  
    				  let temp = list[i];
    				  list[i] = list[min]
    				  list[min] = temp;
    			  }
    		  }  
    	  }

    	  window.localStorage.setItem("list", JSON.stringify(list));
    }

    function selectionSortDate(order){
    		let list = JSON.parse(window.localStorage.getItem("list"));
    		let i,j,min;
    		let n = list.length;
    		if(order){
    			for(let i = 0; i < n-1; i++){
    				min = i;
    				for(let j = i+1; j < n; j++){
    					if(new Date(list[j][year],list[j][month],list[j][day]) > new Date(list[min][year],list[min][month],list[min][day]) ) {
    						min = j
    					}
    				}			
    				if(min != i){    	  
    					let temp = list[i];
    					list[i] = list[min]
    					list[min] = temp;
    				}
    			}
    		}else{
    			for(let i = 0; i < n-1; i++){
    				min = i;
    				for(let j = i+1; j < n; j++){
    					if(new Date(list[j][year],list[j][month],list[j][day]) < new Date(list[min][year],list[min][month],list[min][day]) ) {
    						min = j
    					}    	  
    				}
    				if(min != i){    	  
    					let temp = list[i];
    					list[i] = list[min]
    					list[min] = temp;
    				}
    			}
    		}
    		window.localStorage.setItem("list", JSON.stringify(list));
    }

    function changeOrderImg(){
    		let checkBox = document.getElementById("selectOrder");
    		let img = document.getElementById("orderImg");
    		if(checkBox.checked){
    			img.src = "/assets/img/up.png";
    		}else{
    			img.src = "/assets/img/down.png";
    		}
    		
    		//데이터를 가져오는 코드 
    		let list = JSON.parse(window.localStorage.getItem("list"));
    		
    		let orderType = document.getElementById("order-select");
    		sortSelect(orderType);
    }
    
    
</script>
</body>
</html>
