$(function(){
	
	let queryString=location.search;
	$.ajax({
		url: 'http://localhost:8888/recruit_project_final/resumeupdate'+queryString,
		method: 'get',
		success: function(jsonObj){
			alert("success");
			$(".resumeNum").html(jsonObj.resumeNo);
			$(".resumeTitle").attr('value',jsonObj.resumeTitle);
			$("textarea.introduce").html(jsonObj.resumeIntroduce);
		}	
	});
	
	
	$("button.resumeUpdate").click(function(){
        //자유게시판 정보를 포함하는 부모 객체를 찾자
        let resumeNo=$("div.resumeNum").html();
        let resumeTitle=$(".resumeTitle").val();
        let resumeIntroduce=$(".introduce").val();
   
        let url="http://localhost:8888/recruit_project_final/resumeupdate";
		let data="resumeNo="+resumeNo+"&resumeTitle="+resumeTitle+"&resumeIntroduce="+resumeIntroduce;
        $.ajax({
			url: url,
			method: 'get',
			data: data,
			success:function(){
				alert("수정 성공");
				location.href="../project_html/resumeList.html";
   				return false;
			},
			error:function(){},
		});
    });
});