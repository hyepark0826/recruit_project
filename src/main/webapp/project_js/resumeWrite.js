$(function(){

    //추가저장 버튼을 눌렀을 때

    //저장공간에 새로운 자기소개서를 추가저장해준다.

    $("button.addsaveBtn").click(function(){

		alert("save click");

        //각각의 값을 입력받는 tag 찾기

        let $age=$(".age");

        let $introduce=$(".introduce");

        let inputTitleValue=$(".title").val();

        //각각의 값을 찾아보자

        let inputAgeValue=$age.val();

        let inputIntroduceValue=$introduce.val();

        // 이름:최희우  key:value

        let url="http://localhost:8888/recruit_project_final/resumewrite";

        let data="age="+inputAgeValue + "&title="+inputTitleValue +"&introduce="+inputIntroduceValue;

       	alert(inputTitleValue);

        $.ajax({  //서버와 통신할 수 있는 ~

            url:url,

            data: data,

            success:function(){

                alert("저장 완료");

                location.href="../project_html/resumeList.html";

            },

            error:function(jqXHR){

                alert(jqXHR.error);

            },

        });

    });

 

    //수정하기 버튼을 눌렀을때

    //저장공간에 저장된 자기소개서 내용을 수정한다.

    $("button.endsaveBtn").click(function(){

         //각각의 값을 입력받는 tag 찾기

         let $age=$("input[type=text].age");

         let $major=$("input[type=text].major");

         let $email=$("input[type=email]");

         let $introduce=$("input[type=text].introduce");

         //각각의 값을 찾아보자

         let inputAgeValue=$age.val();

         let inputMajorValue=$major.val();

         let inputEmailValue=$email.val();

         let inputIntroduceValue=$introduce.val();

         // 이름:최희우  key:value

         let url="server";

         let data="age="+inputAgeValue + "&major="+inputMajorValue + "&email="+inputEmailValue + "&introduce="+inputIntroduceValue;

         $.ajax({

             url:url,

             method:'',//post or get

             data: data,

             success:function(){

                 alert("추가저장이 정상적...")

             },

             error:function(jqXHR){

                 alert(jqXHR.error);

             },

         });

    });

    let $age=$("input[type=text].age");

    let $major=$("input[type=text].major");

    let $email=$("input[type=email]");

    let $introduce=$("input[type=introduce].introduce");

});