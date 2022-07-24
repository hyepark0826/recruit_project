$(function(){
	//아이디 찾기 버튼을 눌렀을 때 발생할 이벤트
	$('button.idFindBtn').click(function(){
		let url='http://localhost:8888/recruit_project_final/idfind';//아이디가 있는 back의 주소로
		
  		//--이름과 이메일 입력 객체를 찾아서
  		let $name = $("input[name=name]");
  		let $email = $("input[name=email]");
 		//--이름과 이메일의 입력값을 받고 data에 집어넣는다
   		let inputNameValue = $name.val(); //사용자가 입력해준 이름 값
    	let inputEmailValue = $email.val(); //사용자가 입력해준 이메일 값
    	let data = 'name=' + inputNameValue + '&email='+ inputEmailValue;
    	
    	//--back에 있는 주소로 이름과 이메일을 보내준 후 back애서 2가지 응답을 줘야 할 것으로 예상
    	//-- 1. 회원정보에서 입력한 이름과 이메일을 가진 회원정보가 없을 땐 회원정보를 찾을 수 없습니다 라고 출력 ex)status: 1이 아닌 나머지 값으로
    	//-- 2. 회원정보에서 입력한 이름과 이메일과 회원정보의 데이터 중 일치하는 것이 있는 경우애는 id값을 alert로 보여줌 ex)status:1로
		$.ajax({
			url: url,
			method:'post',
			data: data,
			success: function(jsonObj){
				if(jsonObj.status==1){
					//회원정보 데이터 중 일치하는 것이 있으면
					//회원정보에 있는 id값을 알람으로 보여주자
					alert("아이디 : "+jsonObj.user_id);
				}
				else{
					//입력한 이메일과 이름으로 회원정보에 있는 데이터랑 일치하는 것이 없어요
					//그런 회원은 없다고 알려주자
					alert("입력한 회원정보와 일차한 아이디는 존재하지 않습니다");
				}
				
			},
			error: function(jqXHR){
				alert(jqXHR.status);
			}, 
		});
	});
})
	