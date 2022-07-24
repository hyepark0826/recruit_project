$(function(){
    //queryString에 ?boardNo=1 같은 전달 내용을 찾아요
	let queryString=location.search;
    //noticeList에서 받은 boardNo=? 를 서버로 전달해요
	$.ajax({
		url: 'http://localhost:8888/recruit_project_final/boardview'+ queryString,
		method: 'get',
		success: function(jsonObj){
			$('div.boardnum').html(jsonObj.boardnum);
			$('div.boardtitle').html(jsonObj.boardtitle);
			$('div.boardText').html(jsonObj.boardText);
		}
	});
	
	
	$("button.deleteBoard").click(function(){
		alert("delete click");
		let data=$("div.viewBoard div.boardnum").html();
		$.ajax({
			url: '/recruit_project_final/boarddelete?boardNo='+data,
			method: 'get',
			success: function(jsonObj){
				if(jsonObj.status==1){
					alert("삭제 성공");
					location.href="/recruit_project_final/project_html/boardList.html";
				}
			},
			error:function(jqXHR){
				alert(jqXHR.error);
			}
		});
	});
	
    //자유게시판 상세페이지에서 수정하기 버튼이 클릭되었을 때
    //참고로 자유게시판 수정하기 버튼은 관리자에게만 보이기 때문에
    //관리자만 수정가능 하다.
    $("button.updateBoard").click(function(){
		alert("update click");
        //자유게시판 정보를 포함하는 부모 객체를 찾자
        let boardNo=$("div.viewBoard div.boardnum").html();
        let boardTitle=$("div.viewBoard div.boardtitle").html();
        let boardContent=$("div.viewBoard div.boardText").html();
   
        
		let data="boardNo="+boardNo+"&boardTitle="+boardTitle+"&boardContent="+boardContent;
        let url="../project_html/boardUpdate.html?"+data;
        $.ajax({
			url: url,
			method: 'get',
			data: data,
			success:function(){
				location.href="../project_html/boardUpdate.html?" + data;
   				return false;
			},
			error:function(){},
		});
    });
});