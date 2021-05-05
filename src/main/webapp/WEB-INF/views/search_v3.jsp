<!-- Bootstrap 적용,  복사하기 기능, tooltip 이용-->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"
	session="false"%>
<%  String urlBase = request.getContextPath(); 
	String requestURI = request.getRequestURI();
	String[] parts = requestURI.split("/");
	String fileFullname = parts[parts.length-1];
	String[] parts2 = fileFullname.split("\\.");
%>
<html>
	<head>
		<title>NCP</title>
		<meta charset="UTF-8">
		<!-- css -->
		<link rel="stylesheet" href="/resources/css/style.css">
		<link rel="stylesheet" href="/resources/css/bootstrap.min.css">
	</head>
	
	<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
	<script src="/resources/js/bootstrap.bundle.min.js"></script>

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
	
		<div id="" class="noticePopup">
		 <div class="noticeMessage">
		 </div>
		</div>
<!-- 		<button style="position:fixed; bottom: 5px; left: 5px;" onclick="noticePopupInit( { message:'버튼을 누르셨습니다.' } );">버튼을 눌러보세요.</button> -->
		
	</body>
	

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
	                                    '<th id="loadavgHeader" class="' + isOverLoadAvg + '" style="width: 134px;">LoadAvg';
	            if(isOverLoadAvg){
	            	innerString += '<span style="font-size: 14px;"><br>Load '+ cntServerCPU +' 초과</span><a href="#" class="copy" data-toggle="tooltip" data-placement="top" title="" data-original-title="Copy to Clipboard">copy</a>'
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
	                                    '<li class="list-group-item"><span>/dev/xvda1 : </span><span>' + (serverList[num]['xvd'][0]).toFixed(2) + '%</span><a href="#" class="copy" data-toggle="tooltip" data-placement="top" title="" data-original-title="Copy to Clipboard">copy</a></li>' +
	                                    '<li class="list-group-item"><span>/dev/xvdb1 : </span><span>' + (serverList[num]['xvd'][1]).toFixed(2) + '%</span><a href="#" class="copy" data-toggle="tooltip" data-placement="top" title="" data-original-title="Copy to Clipboard">copy</a></li>' +
	                                    '<li class="list-group-item"><span>/dev/xvdc1 : </span><span>' + (serverList[num]['xvd'][2]).toFixed(2) + '%</span><a href="#" class="copy" data-toggle="tooltip" data-placement="top" title="" data-original-title="Copy to Clipboard">copy</a></li>' +
	                                '</ul>' +
	                            '</td>' + 
	                            '<td class="loadAvg" data-load="01">Mi01';
	
	            if (Object.keys(serverList[num]['overLoadAvgTime_Mi01']).length > 0) {
	                secondHtml +=  '<a href="javasript:void(0)" onclick="showDetail(this); return false"> [자세히]</a>';
	            }
	            secondHtml += '</td>';
	
	            secondHtml += '<td id="swap" rowspan="3" style="vertical-align: middle">';
	            if (isOverSwap == true && serverList[num]['avgOverSwap'] != null) {
	                secondHtml += '<span>Swap ' + serverList[num]['avgOverSwap'].toFixed(1) + '%</span><a href="#" class="copy" data-toggle="tooltip" data-placement="top" title="" data-original-title="Copy to Clipboard">copy</a>';
	            }
	            secondHtml += '</td>';
	
	            secondHtml += '<td id="memory" rowspan="3" style="vertical-align: middle">';
	            if (isOverMemory == true && serverList[num]['avgOverMemory'] != null) {
	                secondHtml += '<span>Memory ' + serverList[num]['avgOverMemory'].toFixed(1) + '%</span><a href="#" class="copy" data-toggle="tooltip" data-placement="top" title="" data-original-title="Copy to Clipboard">copy</a>';
	            }
	            secondHtml += '</td>';
	
	            secondHtml += '<td id="disk" rowspan="3" style="vertical-align: middle">';
	            if (isOverDisk == true && serverList[num]['avgOverDisk'] != null) {
	                secondHtml += '<span>Disk ' + serverList[num]['avgOverDisk'].toFixed(1) + '%</span><a href="#" class="copy" data-toggle="tooltip" data-placement="top" title="" data-original-title="Copy to Clipboard">copy</a>';
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
	                                        '<td colspan="6" class="detail">' +
	                                        	'<ul>' +
	                                            	'<li style="text-align: right;"></li>'+
	                                            	'<li></li>'+
	                                            	'<li></li>'+
                                            	'</ul>' +
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
	        var keys = Object.keys(detail);
	        
	        var copyStr = '<a href="#" class="copyTimes" data-toggle="tooltip" data-placement="top" title="" data-original-title="Copy to Clipboard">copy</a>'
        	var keysStr = '<textarea rows="3" cols="60">' + keys.join(', ') + '</textarea>';
        	var str = JSON.stringify(detail, null, 2);
	       
        	var targetli 	 = $(`.detail:eq(` + num + `) li`)
	        targetli.eq(0).html(copyStr)
	        targetli.eq(1).html(keysStr)
	        targetli.eq(2).html('<pre>'+str+'</pre>')
	        
	        setTimeout(() => {
	            targetPre.html("");
	        }, 60000);
	    }
	
	    function clickSubmit() {
	        $(".container").remove();
            window.location.href = `/<%=parts2[0]%>?serverGroup=` + $('#n').val();
            $('.spinner').css('display', 'block');
	    }
	
	    $('#n').on('keydown', function(e) {
	        if (e.keyCode == 13) {
	            clickSubmit();
	        }
	    });
	    
	    $(document).ready(function(){
		    $(".copy").tooltip();
	    });
	    
	    $(document).on('click', ".copy", function(e){
	    	e.preventDefault();
   	     	var val = $(this).prev().text();
	    	var tempElem = document.createElement('textarea');
		 	tempElem.value = val;  
		 	document.body.appendChild(tempElem);
		 	tempElem.select();
    	    try {
    	         var success = document.execCommand('copy');
    	         if (success) {
	    	        $(this).trigger('copied', ['Copied!']);
	    	        
    	         } else {
	    	        $(this).trigger('copied', ['Copy with Ctrl-c']);
	    	     }
    	         tempElem.remove();
	   	    }catch (err) {
	    	   $(this).trigger('copied', ['Copy with Ctrl-c']);
    	    }
	    })
	    
	    $(document).on('copied', ".copy", function(e,msg){
	    	$(this).attr('title', msg)
			.tooltip('_fixTitle')
			.tooltip('show')
			.attr('title', "Copy to Clipboard")
	        .tooltip('_fixTitle');
	    })
	    
	    $(document).on('click', ".copyTimes", function(e){
	    	e.preventDefault();
	    	var tempElem = $(this).parent().next().find('textarea');
		 	tempElem.select();
    	    try {
    	         var success = document.execCommand('copy');
    	         if (success) {
	    	        $(this).trigger('copied', ['Copied!']);
	    	        
    	         } else {
	    	        $(this).trigger('copied', ['Copy with Ctrl-c']);
	    	     }
	   	    }catch (err) {
	    	   $(this).trigger('copied', ['Copy with Ctrl-c']);
    	    }
	    })
	    
	    $(document).on('copied', ".copyTimes", function(e,msg){
	    	$(this).attr('title', msg)
			.tooltip('_fixTitle')
			.tooltip('show')
			.attr('title', "Copy to Clipboard")
	        .tooltip('_fixTitle');
	    })
	    
	    
    </script>

	<script>
		//Jquery 로딩여부
		if (jQuery) { 
		   console.log("JQuery 설치됨");
		} else {
			console.log("JQuery 설치안됨");
		}

		 var noticePopupTimer = setTimeout(function(){}, 1);
		 var noticePopupDefaultOption = {
		  id: 'default',
		  message: '기본 메시지 입니다.',
		  padding: '20px 40px',
		  fade: 500,
		  duration: 1500,
		     beforeShow: function(){},
		 };
		
		 function noticePopupInit(options)
		 {
			  // Set Options
			  var settings = $.extend({}, noticePopupDefaultOption, options);
			  $("div.noticePopup").attr("id", settings.id);
			
			  var element = "div#"+settings.id+".noticePopup";
			   console.log("element: ", $(element))
			  // Set Style
			  $(element).css("padding", settings.padding);
			  // Set Message
			  $(element+">div.noticeMessage").html(settings.message);
			
			  // Set Position
			  var width = $(element).outerWidth();
			  var height = $(element).outerHeight();
			  $(element).css("margin-left", String(-width/2)+"px");
			  $(element).css("margin-top", String(-height/2)+"px");
			
			  // Clear Animation
			  $(element).stop();
			  clearTimeout(noticePopupTimer);
			  $(element).css("display", "none");
			
			  // Start Animation
			  $(element).fadeIn(settings.fade, function()
			  {
			   noticePopupTimer = setTimeout(function()
			   {
			    $(element).fadeOut(settings.fade);
			   }, settings.duration);
			  });
		 }
		 
		 
	</script>
</html>