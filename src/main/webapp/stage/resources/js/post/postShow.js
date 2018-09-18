$(function() {
	var postId = getQueryString("postId");
	if (!postId) {
		/*window.location.href = "../../../headPage/headpage.html";*/
	} else {
		initPost(postId);
		initFloor(postId);
	}
});

//判断版主
function judge(){
	var judgeModerator = 
	$(".atl-head-reply").hide();
}

// 初始化帖子信息
function initPost(postId) {
	var initPostUrl = "/a4q/post/getPostList?postId=" + postId;
	$.getJSON(initPostUrl, function(data) {
		if (data.state == 0) {
			if (data.data) {
				var post = data.data[0];
				$("#postTitle").text(post.postTitle);
				$("#postContent").text(post.postContent);
				$("#userName").text(post.deployUser.userName);
				$("#createTime").text(format(post.createTime));
			} else {
				alert("帖子信息不存在");
			}
		} else {
			alert("查询失败");
		}
	});
}

// 初始化楼信息
function initFloor(postId) {
	var initFloorUrl = "/a4q/floor/getFloorList?postId=" + postId;
	$.getJSON(initFloorUrl, function(data) {
		if (data.state == 0) {
			$(".floorCount").text(data.data.length+"个回答");
			var tempHtml = "";
			$.map(data.data, function(value, index) {
				tempHtml += '<div class="answer-item atl-item" floorid="1">'+
                			'<div class="atl-head-reply"><a>版主删除权限</a></div>'+
                			'<div class="user" id="floorUser">'+
                			'<a>'+value.user.userName+'</a>&nbsp;'+format(value.createTime)+'&nbsp;'+index+'楼</div>'+
                			'<div class="content" id="floorContent">'+value.floorContent+'</div></div>';
			});
			$("#floorShow").html(tempHtml);
		} else {
			alert("内部错误");
		}
	});
}
