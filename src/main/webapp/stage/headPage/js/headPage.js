$(function(){
	initPage();
});

function initPage(){
	initCourse();
}

//初始化课程信息展示
function initCourse(){
	var initUrl = "/a4q/course/getCourseList";
	$.ajax({
		url : initUrl,
		type : "GET",
		cache : false,
		success : function(data) {
			if (data.state == 0) {
				var tempHtml = "";
				$.map(data.data,function(value,index){
					tempHtml +='<dl><dt>'+
								'<a href="">'+value.courseName+'</a>'+
								'</dt></dl>'
				});
				$("#courseInfo").html(tempHtml);
			} else {
				alert(data.stateInfo);
			}
		}
	});
}