<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"
	session="false"%>
<%
	String urlBase = request.getContextPath();
%>
<html>
<head>
<title>NCP</title>
<meta charset="UTF-8">

<!-- css -->
<link rel="stylesheet" type="text/css" href="/resources/css/style.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
<link rel="icon" href="https://ssl.pstatic.net/static/ncp/img/ko/favicon.png">

</head>
<style>
#submit{
	margin-left: 60px;
}
</style>
<body>

	<div id="form" class="form-inline">
		<label class="mx-sm-3 col-form label">bizspring(n)의 번호 n 입력</label> 
		
		<div class="btn-group-vertical" id="serverGroup">
			<button type="button" class="btn btn-secondary" value="1">bizspring(1)</button>
			<button type="button" class="btn btn-secondary" value="2">bizspring(2)</button>
			<button type="button" class="btn btn-secondary" value="3">bizspring(3)</button>
			<button type="button" class="btn btn-secondary" value="4">bizspring(4)</button>
			<button type="button" class="btn btn-secondary" value="5">bizspring(5)</button>
  			<button type="button" class="btn btn-secondary" value="6">tbase</button>
		</div>
		
		
		<label class="mx-sm-3 col-form label">모니터링 확인자</label> 
		<div class="btn-group-vertical" id="manager">
			<button type="button" class="btn btn-secondary" value="1">박성민</button>
  			<button type="button" class="btn btn-secondary" value="2">전상원</button>
  			<button type="button" class="btn btn-secondary" value="3">김한석</button>
  			<button type="button" class="btn btn-secondary" value="4">문지선</button>
  			<button type="button" class="btn btn-secondary" value="5">허진실</button>
		</div>
		<button type="submit" id="submit" class="btn btn-secondary"
			onclick="clickSubmit()">제출</button>
	</div>
	<a target="_blank" href="https://docs.google.com/spreadsheets/d/13u7ByMN5riz6YwP8tVUye2KoN_FtFYUG1KcKrm39ffw/edit#gid=256418943">NCP 모니터링 - 2021.04</a>
<!-- 	<iframe width="1200" height="600" -->
<!-- 	src="https://docs.google.com/spreadsheets/d/1_ZQjOQbwucBZMDv84r8MOD-DLm54aIsgnH8O8mtu2NQ/edit#gid=256418943" -->
<!-- 	name="test" id="schedule" frameborder="1" scrolling="yes" align="left"></iframe> -->
	<!-- spinner -->
	<div class="spinner"></div>

</body>

<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<script src="<%=urlBase %>/resources/mainScript.js"></script>

</html>
