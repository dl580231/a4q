$(function(){
	initPage();
});

function initPage(){
	initCourse();
	initResolved();
	initUnResolved();
	initRank();
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
								'<a href="/a4q/stage/postList.html?courseId='+value.courseId+'" target="_blank"">'+value.courseName+'</a>'+
								'</dt></dl>'
				});
				$("#courseInfo").html(tempHtml);
			} else {
				alert(data.stateInfo);
			}
		}
	});
}

//初始化已解决问题列表
function initResolved(){
	var initResolvedUrl = "/a4q/post/getResolvedPost";
	$.ajax({
		url : initResolvedUrl,
		type : "GET",
		cache :false,
		success : function(data){
			if(data.state == 0){
				$("#resolved").html(iterator(data));
			}else{
				alert(data.stateInfo);
			}
		}
	});
}

//遍历内容
function iterator(data){
		var tempHtml = '<tr class="tr_boeder">'+
		   '<th class="textleft">问题</th>'+
		   '<th width="12%">提问时间</th>'+
		   '<th width="20%">提问者</th> </tr>';
	$.map(data.data,function(value,index){
	tempHtml += '<tr><td class="qaTitle"><span><a target="_blank" class="qaTitle_link" style="cursor: pointer; display: block;">'+value.postTitle+'</a></span></td>'+
			'<td>'+formatD(value.createTime)+'</td>'+
			'<td class="qa_askname"><a target="_blank">'+value.deployUser.userName+'</a></td></tr>';
	});
	return tempHtml;
}

//初始化已解决问题列表
function initUnResolved(){
	var initResolvedUrl = "/a4q/post/getUnResolvedPost";
	$.ajax({
		url : initResolvedUrl,
		type : "GET",
		cache :false,
		success : function(data){
			if(data.state == 0){
					$("#unResolved").html(iterator(data));
			}else{
				alert(data.stateInfo);
				}
			}
	});
}

//初始化排行榜
function initRank(){
	var initRankUrl = "/a4q/post/getUserRank";
	$.ajax({
		url : initRankUrl,
		typr : "GET",
		cache : false,
		success : function(data){
			if(data.state == 0){
				var tempHtml = '';
				$.map(data.data,function(value,index){
					tempHtml += '<li class="s_c_list l_1">'+
					   		   '<span><a>'+value.userName+'</a></span>'+
					   		   '<em>'+value.num+'</em></li>';					
				});
				$("#rank").html(tempHtml);
			}else{
				alert(data.stateInfo)
			}
		}
	});
}

//搜索功能实现
function search(){
	var key = $("#key").val();
	var url = "/a4q/stage/postList.html?key="+key;
	window.open(url); 
}