$(function(){
	$("input[name=save]").click(function(){
		//분야 지역 나이대 제목 글내용 일자~
		let $field=$("");
		let feild=$title.val();
		
		let $title=$("input[type=text].form-control");
		let title=$title.val();
		
		let data="field="+field+"%location"+location;
		aelrt("Test");
		$.ajax({
       	 	url:'http://localhost:8888/recruit_project_final/projectwrite2',
        	method:'post',
       	 	data:data,
        	success:function(jsonObj){ 
				alert(jsonObj.msg);
				console.log("ㄴㄴㄴㄴㄴ")
				
        },
        	error: function(jqXHR){
            	alert('에러코드 : '+jqXHR.status);
            	console.log("ㄷㄷㄷㄷ")

        }
    });

    return false;

    });
	// html 페이지에 입력된 카테고리 값 가져오기
	$("select[name=location]").change(function(){
		let $location = $("");
		console.log($(this).val()); //값 가져오기
		console.log($("select[name=location] option:seleted").text()) //text값 가져오기
		
	});
});