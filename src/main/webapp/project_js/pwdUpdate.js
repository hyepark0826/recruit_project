$(function(){
    $("button.pwdChangeBtn").click(function(){
        //id,pwd와 pwdChk의 객체를 찾아 주자 (비밀번호와 비밀번호 확인)
        let $id=$("input[name=id]");
        let $pwd=$("input[name=pwd]");
        let $pwdChk=$('input[name=pwdChk]');
        //pwd와 pwdChk 값을 가져오고 일단 두개가 같은지 비교하자 (비밀번호와 비밀번호 확인이 일치하는지) 
        let inputIdValue=$id.val();
        let inputPwdValue=$pwd.val();
        let inputPwdChkValue=$pwdChk.val();
       
        //일치하지 않는다면 다시 입력하라고 alert 발생 
        if(inputPwdValue!=inputPwdChkValue){
            alert("비밀번호와 비밀번호 확인 값이 다릅니다 다시 입력해주세요");
            return false;
        }
        //패스워드 길이가 10자리 이상인지 확인하자
        if($pwd.val().length<10){
            alert("비밀번호 길이가 너무 짧습니다");
            return false;
        }
        //패스워드에 영문이랑 숫자가 혼합되었는지 확인하자
        var passRule=/^(?=.*[A-Za-z0-9])(?=.*[!@#$%^*+=-])(?=..*[0-9]).{10,16}$/;
        if(!passRule.test($pwd.val())){
            alert("비밀번호는 영문, 숫자, 특수문자 조합으로 10글자 이상이어야 합니다.")
            return false;
        }
        //일치하면 변경할 pwd외 사용자가 누군지 특정할 수 있는 id를 전달해주자
        let data="id="+inputIdValue+"&pwd="+inputPwdValue;
        let url="http://localhost:8888/recruit_project_final/pwdupdate";
        $.ajax({
            url:url,
            method: 'post',
            data: data,
            success:function(jsonObj){
                //id,pwd와 pwdChk 전송이 정상적으로 전달되면 서버에서 해당 id로 회원정보를 특정한 후에 기존 pwd를 회원이 보내준 pwd로 바꿔준다.
                //그리고 password 변경이 정상적으로 완료되었으면 status 1?.. 
                if(jsonObj.status!=0){
                    alert("비밀번호가 성공적으로 변경되었습니다");
                    location.href="./mainPage.html";
                }
            },
            error:function(jqXHR){
                alert(jqXHR.error);
            }
        });
    });

});
	