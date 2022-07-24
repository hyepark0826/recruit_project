$(function(){

    $.ajax({
        url:'/recruit_project_final/projectdelete',
        method:'get',
        success:function(jsonObj){
            $(jsonObj).each(function(index, p){
               
            });
        }
    });
    });