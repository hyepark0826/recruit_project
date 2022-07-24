$(function(){
	$(".loginBtn").click(function(){
		//아이디 입력칸 비밀번호 입력칸 찾아줘
		let $id=$(".id");
		let $pwd=$(".pwd");
		//입력한 아이디 값이랑 비밀번호 값을 찾아야지
		let inputIdValue=$id.val();
		let inputPwdValue=$pwd.val();
		
		
		//localStorage test
		/*localStorage.setItem('idjson',JSON.stringify({id:inputIdValue,pwd:inputPwdValue}));
		alert(JSON.parse(localStorage.getItem('idjson')));
		*/
		//서버로 보내주자 
		let url="http://localhost:8888/recruit_project_final/login";
		let data="id="+inputIdValue+"&pwd="+inputPwdValue;
		$.ajax({
			url: url,
			type:'post',
			data: data,
			dataType: "json",
			success:function(jsonObj){
				if(jsonObj.status==1){
					alert("로그인 성공입니다");
					location.href="./mainPage.html";
				}else{ //로그인 실패했을 경우 메세지
                    alert('로그인 실패');
                }
			},
			error:function(jqXHR){
				alert('NO');
			}
		});
	});
});