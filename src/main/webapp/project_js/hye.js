
/****************************************
* @author hye
* @desc 공통 hye.js작성
​*/    
const HyeJs = (function(){
	
	let message = "load Hye Js init....";

	jQuery.fn.serializeObject = function() { 
		
		var obj = null; 
	  	
	  	try { 
	    	if(this[0].tagName && this[0].tagName.toUpperCase() == "FORM" ) { 
	       		var arr = this.serializeArray(); 
	          	if(arr){ obj = {}; 
	          	jQuery.each(arr, function() { 
	           		obj[this.name] = this.value; }); 
	          	} 
	  		} 
	 	}catch(e) { 
	   		alert(e.message); 
	  	}finally {} 
		
		return obj; 
	};
	
	console.log(message);
	
	return {
		
		/****************************************
		* @author hye
		* @desc 초기화 함수
		​*/    	
			
		init : function() {
			return this.message;
		},
	
		/****************************************
		* @author hye	
		* @desc get 방식 ajax요청
		* @param 파라미터
		* @param url 호출 URL
		* @param callback 콜백함수
		* 
		​*/     
		fnAjaxGet : (param, url, callback) => {
				
			//name=value&age=value 형태로 만들어준다.
			let queryStr  = $.param(param);
				
			console.log(queryStr);
				
			//ajax 간소화 방식
			$.get(
				url+"?"+queryStr	// WebServlet에 정의한 호출 주소
			    ,"json" 			// xml, text, script, html 
			)
			//통신 성공시 콜백
			.done(data => {
				callback(data);
			})
			.fail(err => {
			    alert("통신 에러 발생");
				console.log(err);
			});		
		},
		/****************************************
		* @author hye
		* @desc post 방식 ajax요청
		* @param 파라미터
		* @param url 호출 URL
		* @param callback 콜백함수
		*
		​*/ 
		fnAjaxPost : (param, url, callback) => {
			
			//ajax 간소화 방식
			$.post(
				url				//WebServlet에 정의한 호출 주소
			    , param			//웹서블릿으로 보내는 파라미터
			    ,'json' 		//xml, text, script, html
			)
			//통신 성공시 콜백
			.done(data => {
				callback(data);
			})
			//통실 실패시 콜백
			.fail(err => {
			    alert("통신 에러 발생");
				console.log(err);
			});		
			
		},
		
		/****************************************
		* @author hye
		* @desc get 파라미터의 값을 추출한다.
		* @param key 꺼낼 키값
		* @param url 현재 url
		*
		​*/
		fnGetUrlParam : function(key, url){
		    var rtnval = '';  
		    var nowAddress = unescape(url);  
		    var parameters = (nowAddress.slice(nowAddress.indexOf('?') + 1,  
		            nowAddress.length)).split('&');  
		    for (var i = 0; i < parameters.length; i++) {  
		        var varName = parameters[i].split('=')[0];  
		        if (varName.toUpperCase() == key.toUpperCase()) {  
		            rtnval = parameters[i].split('=')[1];  
		            break;  
		        }  
		    }  
		    return rtnval;  
		},
		
		/****************************************
		* @author hye
		* @desc html 인풋에 값을 셋팅한다.
		* @param elementObj 오브젝트형 엘리먼트
		*
		​*/
		fnElementValueSet : function(elementObj){
	
			// Object의 key만큼 배열을 반복한다.
			for (let i in Object.keys(elementObj)) {
		
				let _id = Object.keys(elementObj)[i];
		
				let _tagType = $('#' + _id).prop('tagName');
		
				let _value = elementObj[Object.keys(elementObj)[i]];
		
				$('#' + _id).val(_value);
				
				//if (_tagType == "INPUT" || _tagType == "TEXTAREA") {
				//$('#' + _id).val(_value);
				//}
			}
			
		}
	}

})();
