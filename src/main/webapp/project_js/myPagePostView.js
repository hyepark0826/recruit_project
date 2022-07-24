/*
*	$(function(){})를 사용하는 의미와 이유
*	DOM(Document Object Model) 객체가 생성되어 준비되는 시점에서 호출된다는 의미
*/
$(function(){

	//url에 있는 특정 파라미터의 키출
	let getPno = HyeJs.fnGetUrlParam("pNo",location.href);
	//파라미터에 #이 붙어 있을 때 처리
	getPno = $.trim(getPno.replace("#",""));
	
	//post방식으로 나의 모집글 상세 ajax를 호출한다.▶▶▶ hye.js에서 작성한 공통 ajax 사용
	HyeJs.fnAjaxPost(
		{ pNo : getPno }
		, "../mypagepostselectview"	//ajax 호출하는 URL
		, fnAjaxPostCallBack	
	);
	
	//수정하기 버튼
	$('#btnUpt').click(function(){
		location.href = "../project_html/myPagePostUpdate.html?pNo="+getPno;
	});

	//삭제하기 버튼
	$('#btnDel').click(function(){

		Swal.fire({
		   title: '정말로 삭제하시겠습니까?',
		   icon: 'warning',
		   showCancelButton: true, // cancel버튼 보이기. 기본은 원래 없음
		   confirmButtonColor: '#3085d6', // confrim 버튼 색깔 지정
		   cancelButtonColor: '#d33', // cancel 버튼 색깔 지정
		   confirmButtonText: '승인', // confirm 버튼 텍스트 지정
		   cancelButtonText: '취소', // cancel 버튼 텍스트 지정
		   reverseButtons: true, // 버튼 순서 거꾸로
		}).then(result => {
		   // 만약 Promise리턴을 받으면,
	   		if (result.isConfirmed) { // 만약 모달창에서 confirm 버튼을 눌렀다면
				//post방식으로 나의임시글 삭제 ajax를 호출한다.
				HyeJs.fnAjaxPost(
					{ pNo : getPno}
					, "../mypagepostdelete"	//ajax 호출하는 URL
					, deleteCallback
				);
		   }
		});
	});

});

/****************************************
* @author hye
* @desc ajax가 성공 후 호출하는 콜백 함수
​*/    	
let fnAjaxPostCallBack = function(data){

	let _obj = data.response;
	
	HyeJs.fnElementValueSet(_obj);
	
}

/****************************************
* @author hye
* @desc 삭제 ajax가 성공 후 호출하는 콜백 함수
​*/    	
let deleteCallback = function(data){
	
	if(data.response > 0){
	 	Swal.fire({
	    	icon: 'success', // Alert 타입 
	        title: '삭제성공', // Alert 제목 
	 	});
		location.href="../project_html/myPagePostList.html";
	}else {
	 	Swal.fire({
	    	icon: 'warning', // Alert 타입 
	        title: '삭제실패', // Alert 제목 
	 	});
	}
	
}