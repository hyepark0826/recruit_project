//임시저장 눌렀을때
$("input[name='tmp_save']").click(function() {
	alert("test");
	let userNo = $("input[name='user_no']").val();
	let projectNo = $("input[name='p_no']").val();
	let projectTitle = $("input[name='p_title']").val();
	let projectContent = $("textarea[name='p_content']").val();
	let projectDeadlineday = $("input[name='deadlineDay']").val();

	let data = "user_no=" + userNo + "&p_no=" + projectNo + "&p_title=" + projectTitle
		+ "&p_content=" + projectContent + "&p_deadlineday=" + projectDeadlineday + "&p_view=" + projectView;
	let url = "http://localhost:8888/recruit_project_final/tempprojectwrite?" + data;
	alert(url);

	$.ajax({
		url: url,
		method: 'get',
		//data : pNo,
		success: function() {
			alert(data);
			//location.href="http://localhost:8888/project1/html/projectUpdate.html?p_no="+pNo+"&p_view="+pView;
			return false;
		},

		error: function(jqXHR) {
			alert('오류:' + jqXHR.status);
			alert('projectview 오류');
		},

	});
});