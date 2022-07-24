$(function() {
	$(document).on("click", "button.resumedelete", function() {
		alert("delete click");
		let data = $("div.resumeNum").html();
		let url = "http://localhost:8888/recruit_project_final/resumedelete?resumeNo=" + data;
		$.ajax({
			url: url,
			success: function(jsonObj) {
				if (jsonObj.status == 1) {
					alert("삭제 완료");
					location.href = "../project_html/resumeList.html";
				}
			},
		});
	});

	let queryString = location.search;
	//noticeList에서 받은 boardNo=? 를 서버로 전달해요
	$.ajax({
		url: 'http://localhost:8888/recruit_project_final/resumeview' + queryString,
		method: 'get',
		success: function(jsonObj) {
			$('div.resumeNum').html(jsonObj.resume_no);
			$('div.resumeTitle').html(jsonObj.resume_title);
			$('div.age').html("자기소개서 나이=" + jsonObj.resume_age);
			$('div.resumeMajor').html("자기소개서 전공=" + jsonObj.resume_major);
			$('div.introduce').html(jsonObj.resume_introduce);
		}
	});
	//업데이트 버튼 눌렀을 떄 

	$("button.resumeupdate").click(function() {

		alert("update click");

		//자유게시판 정보를 포함하는 부모 객체를 찾자

		let resumeNo = $("div.resumeNum").html();

		let resumeTitle = $("div.resumeTitle").html();

		let resumeIntroduce = $("div.introduce").html();



		let data = "resumeNo=" + resumeNo + "&resumeTitle=" + resumeTitle + "&resumeIntroduce=" + resumeIntroduce;

		let url = "../project_html/resumeUpdate.html?" + data;

		$.ajax({

			url: url,

			data: data,

			method: 'get',

			success: function() {

				location.href = "../project_html/resumeUpdate.html?" + data;

				return false;

			},

			error: function() { },

		});

	});
	/*	$.ajax({
			url: '/back4/resumeview',
			method: 'get',
			success: function(jsonObj){
				$(jsonObj).each(function(index, r){
					var divHtml = '<div>';
					divHtml += '<div class="resumeNum">';
					divHtml += r.resume_no;
					divHtml += '</div>';
					divHtml += '<div class="resumeTitle">';
					divHtml += r.resume_title;
					divHtml += '</div>';
					divHtml += '<div class="age">';
					divHtml += r.resume_age;
					divHtml += '</div>';
					divHtml += '<div class="resumeMajor">';
					divHtml += r.resume_Major;
					divHtml += '</div>';
					divHtml += '<div class="resumeintroduce">';
					divHtml += r.resume_introduce;
					divHtml += '</div>';
					
					divHtml += '</div>';
					$('div').html(divHtml);
				});
			}
		});*/
});