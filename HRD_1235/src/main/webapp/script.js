function fn_button() {
	var fn = document.frm;
	alert("정보를 지우고 처음부터 다시 입력합니다!")
		return fn.jumin.focus();
}


function fn_submit() {
	var fn = document.frm;
	
	if (fn.jumin.value == "") {
		alert("주민번호가 입력되지 않았습니다!");
		fn.jumin.focus();
		return false;
	}
	
	if (fn.name.value == "") {
		alert("성명이 입력되지 않았습니다!");
		fn.name.focus();
		return false;
	}
	
	if (fn.number.value == "" || fn.number.value == 0) {
		alert("후보번호가 선택되지 않았습니다!");
		fn.number.focus();
		return false;
	}
	
	if (fn.time.value == "") {
		alert("투표시간이 입력되지 않았습니다!");
		fn.time.focus();
		return false;
	}
	
	if (fn.place.value == "") {
		alert("투표장소가 입력되지 않았습니다!");
		fn.place.focus();
		return false;
	}
	
	if (fn.check.value == "") {
		alert("유권자확인이 선택되지 않았습니다!");
		fn.place.focus();
		return false;
	}
	
	fn.submit();
}