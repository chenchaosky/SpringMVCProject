$(document).ready(function() {
	$("#myButton").click(function(){
		
		//JSONP方式实现跨域请求的前端实现方法
		$.ajax({
	        url: 'http://localhost:8080/SpringMVCDemo_Interceptor_CORS_JSONP/getJSONP',
	        type: 'post',
	        dataType:'jsonp',
	        jsonp: "callback",
	        data: {
	            "type":'0',
	            "mobilePhone": '0470261890'
	        },
	        success:function(data){
	            alert("ret: " + data.ret + ", type: "+data.type+", mobilePhone: "+data.mobilePhone);
	        },
	        error:function(data){
	        	alert("Error occured");
	        }
	    });
	})
});