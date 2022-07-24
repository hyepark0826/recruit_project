$(function(){
	//alert("test123");
	
    $.ajax({
        url:'/recruit_project_final/projectlist',
        method:'get',
        success:function(jsonObj){
	//console.log(jsonObj);
            $(jsonObj).each(function(index, p){
                var divHtml = '<div class="data">';
				divHtml += '<div class="projectNum">';
				divHtml += p.p_no;
				divHtml += '</div>';
				divHtml += '<div class="projectTitle">';
				divHtml += '<a class="linkProject" href="http://localhost:8888/recruit_project_final/project_html/projectView.html?p_no="'+p.p_no+'>';
				divHtml += p.p_title;
				divHtml += '</a>';
				divHtml += '</div>';
				divHtml += '<div class="projectWriter">';
				divHtml += p.user_no;
				divHtml += '</div>';
				divHtml += '<div class="projectCreateDay">';
				divHtml += p.p_createday;
				divHtml += "</div>";
				divHtml += '<div class="peojctView">';
				divHtml += p.p_view;
				divHtml += "</div>";
				divHtml += "</div>";
				$('div.project_list').append(divHtml);
            });
        },
        error:function(jqXHR){
                alert('projectList오류:'+jqXHR.status);
            }
    });
    $(document).on("click",".linkProject",function(){
		//  div(글 정보포함 ) > div > a()
  		let $title=$(this);
  		let $projectTitle=$title.parent();
    	let $project=$projectTitle.parent();
   		let $projectNum=$project.children().first();
   		let projectNo=$projectNum.html();
   	    
   	    let $projectView = $project.children().last();
   	    let projectview=$projectView.html();
   		
   		//$('.linkNotice').prop('href',"http://localhost:8888/back4/noticeview?boardNo="+boardNo);
 		let url = "projectView.html?p_no="+ projectNo+"&p_view="+projectview;
   		location.href=url;
   		return false;
	});

});