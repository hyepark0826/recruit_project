$(function(){
	let queryString=location.search;
	$.ajax({
		url: 'http://localhost:8888/recruit_project_final/resumelist'+queryString,
		method: 'get',
		success: function(jsonObj){
			$(jsonObj).each(function(index, r){
				var divHtml = '<tr>';
				divHtml += '<th class="num">';
				divHtml += r.resume_no;
				divHtml += '</div>';
				divHtml += '<td>';
				divHtml += '<a class="linkNotice" href="">';
				divHtml += r.resume_title;
				divHtml += '</a>';
				divHtml += '<td>';
				divHtml += '<td>';
				divHtml += '</td>';
				
				divHtml += '</tr>';
				$('tbody').append(divHtml);
			});
		}
	});
	
	$(document).on("click",".linkNotice",function(){
		let $tdObj=$(this).parent();
        let $trObj=$tdObj.parent();
        let $resumeNo=$trObj.children(":first");
        let resumeNo=$resumeNo.html();
        //$(this).prop( 'href','resumeWrite?introNo='+introNo);
        location.href="../project_html/resumeView.html?resumeNo=" + resumeNo;
   		return false;
	});
	
    $("td>a").click(function(){
        let $tdObj=$(this).parent();
        let $trObj=$tdObj.parent();
        let $resumeNo=$trObj.children(":first");
        let resumeNo=$resumeNo.html();
        //$(this).prop( 'href','resumeWrite?introNo='+introNo);
        location.href="../project_html/resumeView.html?resumeNo=" + resumeNo;
   		return false;
    });
});