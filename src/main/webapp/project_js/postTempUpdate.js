/*
*	$(function(){})를 사용하는 의미와 이유
*	DOM(Document Object Model) 객체가 생성되어 준비되는 시점에서 호출된다는 의미
*/
$(function(){

	//url에 있는 특정 
	let getPno = HyeJs.fnGetUrlParam("pNo",location.href);
	//파라미터에 #이 붙어 있을 때 처리
	getPno = $.trim(getPno.replace("#",""));
	
	//post방식으로 나의임시글 ajax를 호출한다. ▶▶▶ hye.js에서 작성한 공통 ajax 사용
	HyeJs.fnAjaxPost(
		{ pNo : getPno }
		, "../posttempselectview"	//ajax 호출하는 URL
		, fnAjaxPostCallBack	
	);
	
	//수정하기 버튼
	$('#btnUpt').click(function(){
		
		//form 태그의 모든 값을 json 형태로 만든다.
		let serializedValues = $('#frm').serializeObject()

		//post방식으로 나의 임시글내용 수정 ajax를 호출한다.▶▶▶ hye.js에서 작성한 공통 ajax 사용
		HyeJs.fnAjaxPost(
			serializedValues		//form 태그의 모든 값을 json 형태로 만든다.
			, "../posttempupdate"		//ajax 호출하는 URL
			, updateCallBack	
		);
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
* @desc 수정하기 ajax 콜백함수
​*/ 
let updateCallBack = function(data){
	
	if(data.response > 0){
	 	Swal.fire({
	    	icon: 'success', // Alert 타입 
	        title: '수정완료', // Alert 제목 
	 	});	
	}else {
	 	Swal.fire({
	    	icon: 'warning', // Alert 타입 
	        title: '수정실패', // Alert 제목 
	 	});
	}
	
}


