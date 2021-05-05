var group_idx = -1;
var manager = null;
var manager_idx = -1;
	
function clickSubmit() {
	
	if(group_idx == -1 && manager_idx != -1){
		alert('모니터링 번호 선택해주세요');
		return;
	}
	
	if(group_idx != -1 && manager_idx == -1){
		alert('모니터링 확인자 선택해주세요');
		return;
	}
	
	if(group_idx == -1 && manager_idx == -1){
		alert('모니터링 번호와 모니터링 확인자 선택해주세요');
		return;
	}
	
	window.location.href = `/search?serverGroup=`+ group_idx +`&manager=` + manager;
	$('.spinner').css('display', 'block');
}

$('#serverGroup button').on('click', function(e){
	if(group_idx != -1){
		$(this).parent().find(`button:eq(${group_idx})`).removeClass('active');
	}
	$(this).addClass('active');
	group_idx = parseInt($(this)[0].value, 10);
	console.log(group_idx)
})

$('#manager button').on('click', function(e){
	if(manager_idx != -1){
		$(this).parent().find(`button:eq(${manager_idx})`).removeClass('active');
	}
	$(this).addClass('active');
	manager_idx = parseInt($(this).val(), 10) - 1;
	manager 	= $(this).text();
})

function clickSubmitInSearch() {
    $(".container").remove();
    window.location.href = `/search?serverGroup=`+ group_idx +`&manager=` + manager;
	$('.spinner').css('display', 'block');
}
	
	