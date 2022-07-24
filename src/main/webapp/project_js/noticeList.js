$(function(){
	
	//queryString에 ?boardNo=1 같은 전달 내용을 찾아요
	//let queryString=location.search;
    //noticeList에서 받은 boardNo=? 를 서버로 전달해요
	$.ajax({
		url: '/recruit_project_final/noticelist',
		method: 'get',
		success: function(jsonObj){
			$(jsonObj).each(function(index, n){
				var divHtml = '<div class="noticeContext">';
				divHtml += '<div class="boardnum">';
				divHtml += n.n_no;
				divHtml += '</div>';
				
				divHtml += '<div class="boardtitle">';
				divHtml += '<a class="linkNotice" href="">';
				divHtml += n.n_title;
				divHtml += '</a>';
				divHtml += '</div>';
				divHtml += '</div>';
				$('div.board_list').append(divHtml);
			});
		}
	});
	
	//js로 추가한 html tag 선택하기 위해선 on 이벤트를 써야합니다
	$(document).on("click",".linkNotice",function(){
		//  div(글 정보포함 ) > div > a()
  		let $title=$(this);
  		let $boardtitle=$title.parent();
    	let $board=$boardtitle.parent();
    	console.log($title);
    	console.log($boardtitle);
    	console.log($board);
   		let $boardNo=$board.children().first();
  		console.log($boardNo);
   		let boardNo=$boardNo.html();
   		console.log(boardNo);
   		//$('.linkNotice').prop('href',"http://localhost:8888/back4/noticeview?boardNo="+boardNo);
 
   		location.href="../project_html/noticeView.html?boardNo=" + boardNo;
   		return false;
	});
	//클릭한 게시글 번호를 a태그에 넣어서 
	//noticeView?boardNo=1
	//이런식으로 ajax요청이 아닌 주소요청 방식(GET)으로 서버에 게시글번호를 주면
	//서버에선 해당 글번호를 받아서 DB에 일치하는 게시글 정보를 찾고
	//noticeView.html 페이지에 게시글 정보를 붙여ㅓ 사용자에게 보여준다
	
   	$(".linkNotice").click(function(){
		//  div(글 정보포함 ) > div > a()
  		let $title=$(this);
  		let $divtitle=$title.parent();
    	let $notice=$divtitle.parent();
    	console.log($title);
    	console.log($divtitle);
    	console.log($notice);
   		let $boardNo=$notice.children(":first");
  		console.log($boardNo);
   		let boardNo=$boardNo.html();
   		console.log(boardNo);
   		//$('.linkNotice').prop('href',"http://localhost:8888/back4/noticeview?boardNo="+boardNo);
   		
   		location.href="../project_html/noticeView.html?boardNo=" + boardNo;
   		return false;
	});
});