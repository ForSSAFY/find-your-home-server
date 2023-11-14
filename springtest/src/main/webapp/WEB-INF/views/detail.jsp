<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html>
  <head>
    <title>SSAFY</title>
    <style>
      form > label {
        display: inline-block;
        width: 100px;
      }

      form > :input {
        display: inline-block;
        width: 100px;
      }
    </style>
  </head>

  <body>
    <%@ include file="/WEB-INF/views/header.jsp"%>
    <form>
      <label for="ano" class="inputtitle">이슈 번호</label>
      <input type="text" name="ano" id="ano" value="${detail.ano }" readonly="readonly" />
      <br />
      <label for="usernumber" class="inputtitle">학번</label>
      <input type="text" name="" id="" value="${detail.userNumber }" readonly="readonly" />
      <br />
      <label for="name" class="inputtitle">교육생명</label>
      <input type="text" name="" id="" value="${detail.user.name }" readonly="readonly" />
      <br />
      <label for="rclass" class="inputtitle">반</label>
      <input type="number" name="" id="" value="${detail.user.rclass }" readonly="readonly" />
      <br />
      <label for="rname" class="inputtitle">지역</label>
      <input type="text" name="" id="" value="${detail.user.rname }" readonly="readonly" />
      <br />
      <label for="issuedate" class="inputtitle">날짜</label>
      <input type="text" name="" id="" value="${detail.issueDate }" readonly="readonly" />
      <br />
      <label for="issue" class="inputtitle">사유</label>
      <input type="text" name="" id="" value="${detail.issue}" readonly="readonly" />
      <br />
    </form>
    <%@ include file="/WEB-INF/views/footer.jsp"%>
  </body>
</html>
