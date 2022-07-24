$(function(){
	
	let queryString=location.search;
	$.ajax({
		url: '/recruit_project_final/boardupdate'+queryString,
		method: 'get',
		success: function(jsonObj){
			alert("success");
			$(".title").attr('value',jsonObj.boardtitle);
			$("textarea.boardText").html(jsonObj.boardText);
			$(".boardNo").html(jsonObj.boardnum);
		}	
	});
	
	
	$("button.updateBoard").click(function(){
        //자유게시판 정보를 포함하는 부모 객체를 찾자
        let boardNo=$(".boardNo").html();
        let boardTitle=$(".title").val();
        let boardContent=$("textarea.boardText").val();
   
        let url="/recruit_project_final/boardupdate";
		let data="boardNo="+boardNo+"&boardTitle="+boardTitle+"&boardContent="+boardContent;
        $.ajax({
			url: url,
			method: 'get',
			data: data,
			success:function(){
				alert("변경 성공");
				location.href="../project_html/boardList.html";
   				return false;
			},
			error:function(){},
		});
    });
});