$(function(){
	let queryString=location.search;
    //proejctView페이지에서 데이터 받아서 보여줌
   // $("#input[name='deadlineDay']").datepicker({ dateFormat: 'yy-mm-dd' });
    $(".selector").datepicker({ dateFormat: 'yy/mm/dd' });
	$.ajax({
		url: '/recruit_project_final/projectview'+ queryString,
		method: 'get',
		success: function(jsonObj){
			var content = document.getElementById('p_content');
			content.value = jsonObj.project.p_content;
			$("input[name=p_no]").attr('value',jsonObj.project.p_no);
			$("input[name=user_no]").attr('value',jsonObj.project.user_no);
			$("input[name=p_title]").attr('value',jsonObj.project.p_title);
			$("input[name=p_view]").attr('value',jsonObj.project.p_view);
			$("input[name=deadlineDay]").attr('value',jsonObj.project.p_deadlineday);
		
		},
		error:function(jqXHR){
                alert('오류:'+jqXHR.status);
                alert('projectupdate.js 오류');
            }
	});
	/* $(document).on("click",".button.updateBoard",function(){

	});*/
	//수정완료 눌렀을때
	$("input[name='projectupdate']").click(function(){
		let userNo = $("input[name='user_no']").val();
		let projectNo=$("input[name='p_no']").val();
        let projectTitle=$("input[name='p_title']").val();
        let projectContent=$("textarea[name='p_content']").val();
        let projectDeadlineday = $("input[name='deadlineDay']").val();
		let projectView = $("input[name='p_view']").val();

		let data = "user_no="+userNo+"&p_no="+projectNo+"&p_title="+projectTitle
		+"&p_content="+projectContent+"&p_deadlineday="+projectDeadlineday+"&p_view="+projectView;
		let url ="http://localhost:8888/recruit_project_final/projectupdate?"+data;
		//alert(url);
		
		$.ajax({
			url: url,
			method: 'get',
			data: data,
			success:function(){
				
				location.href="http://localhost:8888/recruit_project_final/project_html/projectList.html";
   				return false;
			},

			  error:function(jqXHR){
                alert('projectUpdate오류:'+jqXHR.status);
            } 

		});

    });
    	$("input[name='delete']").click(function(){
			//alert("test");
			//location.href="http://localhost:8888/project1/html/projectList.html";
   	 		//페이지 이동만되고 삭제는 아직 안됨
   	 		let userNo = $("input[name='user_no']").val();
   	 		let projectNo=$("input[name='p_no']").val();
   	 		let data = "user_no="+userNo+"&p_no="+projectNo;
   	 		let url ="http://localhost:8888/recruit_project_final/projectdelete?"+data;
   	 		$.ajax({
			url: url,
			method: 'get',
			data: data,
			success:function(){
				//alert(data);
				location.href="http://localhost:8888/recruit_project_final/project_html/projectList.html";
   				return false;
			},

			  error:function(jqXHR){
                alert('projectUpdate오류:'+jqXHR.status);
            } 

		});
   	 	});
    });
