$(function() {
	//아이디입력객체찾기
	let $inputId = $('.id');
	/*var boolean = 0; // 실패중*/
	//가입버튼객체찾기
	let $btSubmit = $('button[type=submit]');


	//--아이디중복확인버튼 클릭 START--
	let $btIdchk = $('button.idchk');

	$btIdchk.click(function() {
		$.ajax({
			url: 'http://localhost:8888/recruit_project_final/idchk',
			method: 'get',
			data: { id: $inputId.val() },
			success: function(jsonObj) {
				if (jsonObj.status == 1) { //사용가능한 아이디인 경우
					alert($inputId.val() + "는 사용가능합니다.");
					$btSubmit.show();
				} else {
					alert(jsonObj.msg);
					$('.id').focus();
					$('.id').val("");
					return false;
				}
			},
			error: function(jqXHR) {
				alert('오류' + jqXHR.status);
			}

		});

		$btSubmit.show(); //기본이벤트처리가 데이터 전송 
	})
	//--아이디중복확인버튼 클릭 END--

	//--아이디입력란에 포커스 START--

	$inputId.focus(function() {
		$btSubmit.hide();
	});
	//--아이디입력란에 포커스 END--

	//이메일입력객체찾기
	let $inputEmail = $('.email');

	//--이메일중복확인버튼 클릭 START--
	let $btemailchk = $('button.emailchk');
	$btemailchk.click(function() {
		$.ajax({
			url: 'http://localhost:8888/recruit_project_final/emailchk',
			method: 'get',
			data: { email: $inputEmail.val() },
			success: function(jsonObj) {
				if (jsonObj.status == 1) { //사용가능한 이메일인 경우
					alert($inputEmail.val() + "는(은) 사용가능한 이메일입니다.");
					$btSubmit.show();
				} else {
					alert(jsonObj.msg);
					$('.email').focus();
					$('.email').val("");
					return false;
				}
			},
			error: function(jqXHR) {
				alert('오류' + jqXHR.status);
			}

		});

		$btSubmit.show(); //기본이벤트처리가 데이터 전송 
	})
	//--이메일중복확인버튼 클릭 END--
	//--이메일입력란에 포커스 START--

	$inputEmail.focus(function() {
		$btSubmit.hide();
	});
	//--이메일입력란에 포커스 END--
	//--폼 전송 START --
	//가입버튼클릭이벤트발생->폼서브밋이벤트발생->기본처리(전송)
	//폼객체찾기
	let $form = $('form');
	$form.submit(function() {
		alert('submit start');
		/*let $subBtn = $('button[type=submit]');
		$subBtn.click(function(){
		  alert('submit start');*/
		//비밀번호 일치확인

		let $pwd = $('input[name=pwd]');
		let $pwdChk = $('input[name=pwdChk]');

		if ($pwd.val() != $pwdChk.val()) {
			alert('비밀번호가 일치하지 않습니다');
			$pwd.focus();
			return false;
		}
		else if ($pwd.val().length < 10) {
			alert("비밀번호 길이가 너무 짧습니다");
			$pwd.focus();
			return false;
		}else{
			
		}
		//패스워드에 영문이랑 숫자가 혼합되었는지 확인하자
		var passRule = /^(?=.*[A-Za-z0-9])(?=.*[!@#$%^*+=-])(?=..*[0-9]).{10,16}$/;
		if (!passRule.test($pwd.val())) {
			alert("비밀번호는 영문, 숫자, 특수문자 조합으로 10글자 이상이어야 합니다.");
			return false;

		}

		let url = 'http://localhost:8888/recruit_project_final/signup';
		//let data = $("#number").serialize(); //querystring만들어줌
		let InputNameValue = $(".name").val();
		let InputIdValue = $(".id").val();
		let InputPwdValue = $pwd.val();
		let InputEmailValue = $(".email").val();

		let data = "name=" + InputNameValue + "&id=" + InputIdValue + "&pwd=" + InputPwdValue + "&email=" + InputEmailValue
		
		$.ajax({
			url: url,
			method: 'post',
			data: data,
			// success:function(responseText){
			//  let jsonObj = JSON.parse(responseText);
			success: function(jsonObj) {
				alert(jsonObj.msg);
				if (jsonObj.status == 1)
					location.href = "mainPage.html";
			},
			error: function(jqXHR) {
				alert('에러코드:' + jqXHR.status);
			}

		});
		return false;


	});

	//--폼 전송 END--

});