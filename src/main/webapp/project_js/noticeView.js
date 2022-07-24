$(function(){
	let queryString=location.search;
	$.ajax({
		url: '/recruit_project_final/noticeview'+ queryString,
		method: 'get',
		success: function(jsonObj){
			$('div.boardnum').html(jsonObj.boardnum);
			$('div.boardtitle').html(jsonObj.boardtitle);
			$('div.boardText').html(jsonObj.boardText);
		}
	});
	
	$("button.deleteNotice").click(function(){
		alert("delete click");
		let data=$("div.viewBoard div.boardnum").html();
		$.ajax({
			url: '/recruit_project_final/noticedelete?boardNo='+data,
			method: 'get',
			success: function(jsonObj){
				if(jsonObj.status==1){
					alert("삭제 성공");
					location.href="/recruit_project_final/project_html/noticeList.html";
				}
			},
			error:function(jqXHR){
				alert(jqXHR.error);
			}
		});
	});
	
	
    //공지사항 상세페이지에서 수정하기 버튼이 클릭되었을 때
    //참고로 공지사항 수정하기 버튼은 관리자에게만 보이기 때문에
    //관리자만 수정가능 하다.
   	$("button.updateNotice").click(function(){
		alert("update click");
        //자유게시판 정보를 포함하는 부모 객체를 찾자
        let boardNo=$(".boardnum").html();
        let boardTitle=$(".boardtitle").html();
        let boardContent=$(".boardText").html();
        
		let data="boardNo="+boardNo+"&boardTitle="+boardTitle+"&boardContent="+boardContent;
        let url="../project_html/noticeUpdate.html?"+data;
        $.ajax({
			url: url,
			data:data,
			method: 'get',
			success:function(){
				location.href="../project_html/noticeUpdate.html?" + data;
   				return false;
			},
			error:function(){},
		});
    });
});