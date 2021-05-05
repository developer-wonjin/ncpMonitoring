<!-- 부트스트랩 적용전 JSP, 기본기능만 갖춘상태 -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"
	session="false"%>
<% String urlBase = request.getContextPath(); %>
<html>
<head>
<title>NCP</title>
<meta charset="UTF-8">

<!-- css -->
<link rel="stylesheet" type="text/css" href="/resources/css/style.css">

</head>
<body>

	<div id="form" class="form-inline">
		<label class="mx-sm-3 col-form label">bizspring(n)의 번호 n 입력</label> <input
			class="form-control mx-sm-3" id="n" name="n"
			placeholder="1 ~ 6 사이 숫자">
		<button type="submit" id="submit" class="btn btn-secondary"
			onclick="clickSubmit()">제출</button>
	</div>
	
	<!-- spinner -->
	<div class="spinner"></div>
	
	
	<div class="container">
<!-- 		<div class="item">helloflex</div> -->
<!-- 		<div class="item">abc</div> -->
<!-- 		<div class="item">helloflex</div> -->
	
	<c:forEach var="ele" items="${result['allInfo']}" varStatus="status">
		<table data-serverNum="${status.index}">
			<tbody>
		      	<tr>
	      			<th colspan="5" id="">
	      				${ele["serverInfo"]["serverName"]}
	      				&nbsp;
	      				(${ele["serverInfo"]["cntCPUCore"]}C,&nbsp; ${ele["serverInfo"]["memoryVolume"]}GB)	
      				</th>
		      		
		      		
		      		
		      	</tr>
		        <tr>
		          <th colspan="" id="">/dev/xvda1, b1,c1 used(%)</th>
		          <th class="${ele['isOverLoadAvg'] }" 	 id="loadavgHeader" colspan="" >
			          <c:if test="${ele['isOverLoadAvg'] ne true }">LoadAvg</c:if>
			          <c:if test="${ele['isOverLoadAvg'] eq true }">Load${ele["serverInfo"]["cntCPUCore"]}&nbsp;초과</c:if>
		          </th>
		          <th class="${ele['isOverSwap'] }"  	 id="swapHeader">Swap</th>
		          <th class="${ele['isOverMemory'] }" 	 id="memoryHeader">Memory</th>
		          <th class="${ele['isOverDisk'] }"		 id="diskHeader">Disk</th>
		        </tr>
		      
		        <tr>
		          <td rowspan="3">
		          		<ul>
							<li>/dev/xvda1: <fmt:formatNumber value="${ele['xvd'][0]}"
									pattern="0.00" />%
							</li>
							<li>/dev/xvdb1: <fmt:formatNumber value="${ele['xvd'][1]}"
									pattern="0.00" />%
							</li>
							<li>/dev/xvdc1: <fmt:formatNumber value="${ele['xvd'][2]}"
									pattern="0.00" />%
							</li>
						</ul>

					</td>
		          <td class="loadAvg" data-load="01">Mi01<c:if test="${fn:length(ele['overLoadAvgTime_Mi01']) > 0 }"><a href="javascript:void(0)" onclick="showDetail(this); return false">[자세히보기]</a></c:if></td>
		          <td id="swap" rowspan="3">
					<c:if test="${ele['isOverSwap'] eq true && ele['avgOverSwap'] != null}">
						<fmt:parseNumber value="${ele['avgOverSwap']}" integerOnly="true" />%
					</c:if>
				  </td>
		          <td id="memory" rowspan="3">
		          	<c:if test="${ele['isOverMemory'] eq true && ele['avgOverMemory'] != null}">
						<fmt:parseNumber value="${ele['avgOverMemory']}" integerOnly="true" />%
					</c:if>
		          </td>
		          <td id="disk" rowspan="3">
		          	<c:if test="${ele['isOverDisk'] eq true && ele['avgOverDisk'] != null}">
						<fmt:parseNumber value="${ele['avgOverDisk']}" integerOnly="true" />%
					</c:if>
		          </td>
		        </tr>
		        <tr>
		          <td class="loadAvg" data-load="05">Mi05<c:if test="${fn:length(ele['overLoadAvgTime_Mi05']) > 0 }"><a href="javascript:void(0)" onclick="showDetail(this); return false">[자세히보기]</a></c:if></td>
		        </tr>
		        <tr>
		          <td class="loadAvg" data-load="15">Mi15<c:if test="${fn:length(ele['overLoadAvgTime_Mi15']) > 0 }"><a href="javascript:void(0)" onclick="showDetail(this); return false">[자세히보기]</a></c:if></td>
		        </tr>
		      </tbody>
		      <tbody>
				<tr>
					<td colspan="6">
						<pre class="detail"></pre>
					</td>
				<tr>	      	
		      </tbody>
		      
		 </tbody>
	    </table>
	</c:forEach>
</div>

	<div id="loading">
	</div>

</body>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<script>
	// $(document).ready(function(){
	//    $('#loading').hide(); //첫 시작시 로딩바를 숨겨준다.
	// })
	// .ajaxStart(function(){
	// 	$('#loading').show(); //ajax실행시 로딩바를 보여준다.
	// })
	// .ajaxStop(function(){
	// 	$('#loading').hide(); //ajax종료시 로딩바를 숨겨준다.
	// });
	var allInfoStr = `${result['allInfoStr']}`;
	var list = JSON.parse(allInfoStr);
	
	console.log(list)

	function showDetail(tagInfo){
		console.log(tagInfo);
		var $table = $(tagInfo).closest("table");
		var serverNum = $table.data("servernum");
		var info = list[serverNum];
		var $textarea = $(`textarea:eq(`+ serverNum +`)`);
		var loadNum = $(tagInfo).closest("td").data("load");
		var loadType = `overLoadAvgTime_Mi`+loadNum;
		var detail = info[loadType];
		var str = JSON.stringify(detail, null, 2);
		var targetPre = $(`.detail:eq(`+serverNum+`)`) 
		targetPre.html(str);
		
		setTimeout(() => {
			targetPre.html("");
		}, 15000);
	}
	
	function clickSubmit() {
		$(".container").remove();
		window.location.href = `/search?serverGroup=`+ $('#n').val();
		$('.spinner').css('display', 'block');
	}
	
	$('#n').on('keydown', function(e){
		if(e.keyCode == 13){
			clickSubmit()
		}
	})
	
</script>

</html>
