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
<body>

	<div id="form" class="form-inline">
<!-- 		<label class="mx-sm-3 col-form label">bizspring(n)의 번호 n 입력</label>  -->
<!-- 		<input class="form-control mx-sm-3" id="n" name="n" placeholder="1 ~ 6 사이 숫자"><br> -->
		
		<label class="mx-sm-3 col-form label">모니터링 확인자</label> 
		<input class="form-control mx-sm-3" id="manager" placeholder="" value="박성민">
			
		<button type="submit" id="submit" class="btn btn-secondary"
			onclick="clickSubmit()">제출</button>
	</div>

	<!-- spinner -->
	<div class="spinner"></div>

</body>

<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<script>
	var $n 		= $('#n');
	var $manager = $('#manager');
	
	function clickSubmit() {
		window.location.href = `/busan_search?serverGroup=1&manager=` + $manager.val();
		$('.spinner').css('display', 'block');
	}
	
	$n.on('keydown', function(e){
		if(e.keyCode == 13 && $n.val() != "" && $manager.val() != ""){
			clickSubmit()
		}
	})
	
	$manager.on('keydown', function(e){
		if(e.keyCode == 13 && $n.val() != "" && $manager.val() != ""){
			clickSubmit()
		}
	})
</script>

</html>
