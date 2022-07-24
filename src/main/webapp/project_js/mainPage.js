const gather = document.getElementById("gather").style.display = "none";

const login = document.getElementByClassName("login_tag").style.display = "block";
const logout = document.getElementByClassName("logout_tag").style.display = "none";

const mypage = document.getElementById("myInfo_id").style.display = "none";
$(function() {
	let url = "http://localhost:8888/recruit_project_final/loginstatus";
	$.ajax({
		url: url,
		type: 'post',
		dataType: "json",
		success: function(jsonObj) {
			if (jsonObj.loginInfo == 1) {
				alert('login success');
				login.style.display = "none";
				logout.style.display = "block";
				gather.style.display = "block";
				mypage.style.display = "block";
			} else { //로그인 실패했을 경우 메세지
			}
		},
		error: function(jqXHR) {
			alert('NO');
		}
	});
	
	$(".logout_tag").click(function() {
		$.ajax({
			url: url,
			type: 'post',
			data: data,
			dataType: "json",
			success: function(jsonObj) {
				if (jsonObj.loginInfo == 0) {
					alert('logout');
					login.style.display = "block";
					logout.style.display = "none";
					gather.style.display = "none";
					mypage.style.display = "none";
				}
			}
		});
	});

});
