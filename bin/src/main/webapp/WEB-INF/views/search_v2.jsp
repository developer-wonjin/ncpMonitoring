<!-- Bootstrap 적용,  복사하기 기능-->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">

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


	<div id="card-container" class="container"></div>

	<div class="spinner"></div>

</body>

<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<script>
    var allInfoStr = `${result['allInfoStr']}`;
    var serverList = JSON.parse(allInfoStr);

    // 카드 테이블 시작
    var monitorDate = serverList[0]['monitorDate'];
    var monitorStartDate = serverList[0]['startDate'];
    var monitorEndDate = serverList[0]['endDate'];

    var container = document.getElementById('card-container');
    
    container.innerHTML = '<p style="width:100%">모니터링 시간 : ' + monitorStartDate + ' ~ ' + monitorEndDate +'</p>';
    createCard(serverList);
    

    function createCard(serverList) {

        var container = document.getElementById('card-container');

        for (var num in serverList) {
            
            var serverName          = serverList[num]['serverInfo']['serverName'];
            var cntServerCPU        = serverList[num]['serverInfo']['cntCPUCore'];
            var volumeServerMemory  = serverList[num]['serverInfo']['memoryVolume'];
            var volumeServerDisk    = serverList[num]['serverInfo']['diskVolume'];

            var isOverDisk          = serverList[num]['isOverDisk'];
            var isOverLoadAvg       = serverList[num]['isOverLoadAvg'];
            var isOverSwap          = serverList[num]['isOverSwap'];
            var isOverMemory        = serverList[num]['isOverMemory'];

            // card
            var cardDiv = document.createElement('div');
            cardDiv.className = 'card bg-light mb-3 border-top-primary';
            cardDiv.style.margin = '15px';
            cardDiv.style.width = '45%';

            // card header
            var cardHeader = document.createElement('div');
            cardHeader.className = 'card-header';
            cardHeader.innerText = serverName + ' (' + cntServerCPU +'C, ' + volumeServerMemory + 'GB)';
            cardHeader.style.textAlign = 'center';
            cardHeader.style.fontWeight = 'bold';
            cardDiv.appendChild(cardHeader);

            // card body
            var cardBody = document.createElement('div');
            cardBody.className = 'card-body';
            cardBody.style.padding = '0px';

            // card Table
            var cardTable = document.createElement('table');
            cardTable.className = 'table table-bordered';
            cardTable.dataset.serverNum = num;
            cardTable.style.margin = '0px';

            var cardTableBody = document.createElement('tbody');
            
            // first Row
            var firstRow = document.createElement('tr');
            var innerString =    '<th>dev/xvda1, b1, c1 used(%)</th>' + 
                                    '<th id="loadavgHeader" class="' + isOverLoadAvg + '" style="width: 20px;">LoadAvg';
            if(isOverLoadAvg){
            	innerString += '<span class="copyAble" style="font-size: 14px;"><br>Load '+ cntServerCPU +' 초과</span>'
            }
            innerString +=  '</th>'; 
            innerString +=  '<th id="swapHeader"   class="' + isOverSwap + '">Swap</th>' +
                            '<th id="memoryHeader" class="'  + isOverMemory + '">Memory</th>' +
                            '<th id="diskHeader"   class="' + isOverDisk + '">Disk</th>';
            firstRow.innerHTML = innerString;                                    
            cardTableBody.appendChild(firstRow);

            // second Row
            var secondRow = document.createElement('tr');
            var secondHtml = '<td rowspan="3">' +
                                '<ul class="list-group list-group-flush">' +
                                    '<li class="list-group-item"><span>/dev/xvda1 : </span><span class="copyAble">' + (serverList[num]['xvd'][0]).toFixed(2) + '%</span></li>' +
                                    '<li class="list-group-item"><span>/dev/xvdb1 : </span><span class="copyAble">' + (serverList[num]['xvd'][1]).toFixed(2) + '%</span></li>' +
                                    '<li class="list-group-item"><span>/dev/xvdc1 : </span><span class="copyAble">' + (serverList[num]['xvd'][2]).toFixed(2) + '%</span></li>' +
                                '</ul>' +
                            '</td>' + 
                            '<td class="loadAvg" data-load="01">Mi01';

            if (Object.keys(serverList[num]['overLoadAvgTime_Mi01']).length > 0) {
                secondHtml +=  '<a href="javasript:void(0)" onclick="showDetail(this); return false"> [자세히]</a>';
            }
            secondHtml += '</td>';

            secondHtml += '<td id="swap" class="copyAble" rowspan="3" style="vertical-align: middle">';
            if (isOverSwap == true && serverList[num]['avgOverSwap'] != null) {
                secondHtml += 'Swap ' + serverList[num]['avgOverSwap'].toFixed(1) + '%';
            }
            secondHtml += '</td>';

            secondHtml += '<td id="memory" class="copyAble" rowspan="3" style="vertical-align: middle">';
            if (isOverMemory == true && serverList[num]['avgOverMemory'] != null) {
                secondHtml += 'Memory ' + serverList[num]['avgOverMemory'].toFixed(1) + '%';
            }
            secondHtml += '</td>';

            secondHtml += '<td id="disk" class="copyAble" rowspan="3" style="vertical-align: middle">';
            if (isOverDisk == true && serverList[num]['avgOverDisk'] != null) {
                secondHtml += 'Disk ' + serverList[num]['avgOverDisk'].toFixed(1) + '%';
            }
            secondHtml += '</td>';

            secondRow.innerHTML = secondHtml;
            cardTableBody.appendChild(secondRow);

            var thirdRow = document.createElement('tr');
            var thirdHtml = '';
            thirdHtml = '<td class="loadAvg" data-load="05">Mi05';

            if (Object.keys(serverList[num]['overLoadAvgTime_Mi05']).length > 0) {
                thirdHtml += '<a href="javasript:void(0)" onclick="showDetail(this); return false"> [자세히]</a>';
            }
            thirdHtml += '</td>';

            thirdRow.innerHTML= thirdHtml;
            cardTableBody.appendChild(thirdRow);

            var tmpRow = document.createElement('tr');
            var tmpHtml = '';
            tmpHtml = '<td class="loadAvg" data-load="15">Mi15';

            if (Object.keys(serverList[num]['overLoadAvgTime_Mi15']).length > 0) {
                tmpHtml += '<a href="javasript:void(0)" onclick="showDetail(this); return false"> [자세히]</a>';
            }
            tmpHtml += '</td>';

            tmpRow.innerHTML = tmpHtml;
            cardTableBody.appendChild(tmpRow);

            cardTable.appendChild(cardTableBody);

            var detailTbody = document.createElement('tbody');
            detailTbody.innerHTML = '<tr>' + 
                                        '<td colspan="6">' +
                                            '<pre class="detail"></pre>' +
                                        '</td>' +
                                    '</tr>';

            cardTable.appendChild(detailTbody);
            
            if (isOverDisk != false || isOverLoadAvg != false || isOverMemory != false || isOverSwap != false) {
                cardHeader.classList.add('list-group-item-danger');
            } else {
            	cardHeader.classList.add('list-group-item-success');
            }
            
            cardBody.appendChild(cardTable);
            cardDiv.appendChild(cardBody);
            container.appendChild(cardDiv);

        }
    }

    function showDetail(tagInfo) {
        var $table = $(tagInfo).closest("table");
        var num = $table.data("serverNum");
        var info = serverList[num];
        var $textarea = $(`textarea:eq(` + num + `)`);
        var loadNum = $(tagInfo).closest("td").data("load");
        var loadType = `overLoadAvgTime_Mi` + loadNum;
        var detail = info[loadType];
        var str = JSON.stringify(detail, null, 2);
        var keys = Object.keys(detail);
        var keysStr = '<span class="copyAble">' + keys.join(', ') + '</span><br>';
        var targetPre = $(`.detail:eq(` + num + `)`)
        targetPre.html(keysStr + str);

        setTimeout(() => {
            targetPre.html("");
        }, 60000);
    }

    function clickSubmit() {
        $(".container").remove();
            window.location.href = `/search?serverGroup=` + $('#n').val();
            $('.spinner').css('display', 'block');
    }

    $('#n').on('keydown', function(e) {
        if (e.keyCode == 13) {
            clickSubmit();
        }
    });
    
    $(document).on('click', '.copyAble', function(e){
   	    var $table = $(this).closest("table");
        var num = $table.data("serverNum");
    	var tempElem = document.createElement('textarea');
	 	tempElem.value = $(this).text();  
	 	document.body.appendChild(tempElem);
	 	tempElem.select();
	 	document.execCommand("copy");
 		document.body.removeChild(tempElem);
		
 		
 		$('<div>복사!</div>').insertBefore(`.detail:eq(` + num + `)`).delay(2000).fadeOut();
    })
    
    </script>

</html>