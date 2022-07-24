$(function(){
	//수정하기 칸에 이미 DB에 저장되어있는 내용을 넣어주자
	let queryString=location.search;
	$.ajax({
		url: '/recruit_project_final/noticeupdate'+queryString,
		method: 'get',
		success: function(jsonObj){
			alert("success");
			$(".boardtitle").attr('value',jsonObj.boardtitle);
			$("textarea.boardText").html(jsonObj.boardText);
			$(".boardnum").html(jsonObj.boardnum);
		}	
	});
	
	//수정완료 눌렀을 때 DB에 저장하자 
	$("button.updateNotice").click(function(){
        //자유게시판 정보를 포함하는 부모 객체를 찾자
        let boardNo=$("div.viewBoard div.boardnum").html();
        let boardTitle=$(".boardtitle").val();
        let boardContent=$("textarea.boardText").val();
   
        let url="/recruit_project_final/noticeupdate";
		let data="boardNo="+boardNo+"&boardTitle="+boardTitle+"&boardContent="+boardContent;
        $.ajax({
			url: url,
			method: 'get',
			data: data,
			success:function(){
				alert("변경 성공");
				location.href="../project_html/noticeList.html";
   				return false;
			},
			error:function(){},
		});
    });
});