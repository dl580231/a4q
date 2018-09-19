$(function() {
//	初始化一些全局信息
	var courseId;
	var user;
	var post;
	var isLogin = false;
	var isModerator = false;
	var postId = getQueryString("postId");
	if (!postId) {
		/* window.location.href = "../../../headPage/headpage.html"; */
	} else {
		initPost(postId);
		initFloor(postId);
		loginState();// 登录状态判断
		/*if(isModerator){
			$(".moderator-delete").show();
		}*/
	}
	
	//	设置点击事件
	$("#reply").click(function(){
		replyHandle();
	});


	
	// 初始化帖子信息
	function initPost(postId) {
		var initPostUrl = "/a4q/post/getPostList?postId=" + postId;
		$.ajax({
			url : initPostUrl,
			type : "GET",
			asyn : false,
			success : function(data){
					if (data.state == 0) {
						if (data.data) {
							post = data.data[0];
							courseId = post.course.courseId;
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
			}
		});
	}
	
	// 初始化楼信息
	function initFloor(postId) {
		var initFloorUrl = "/a4q/floor/getFloorList?postId=" + postId+"&fresh=" + Math.random();
		$.getJSON(initFloorUrl, function(data) {
			if (data.state == 0) {
				$(".floorCount").text(data.data.length+"个回答");
				var tempHtml = "";
				$.map(data.data, function(value, index) {
					tempHtml += '<div class="answer-item atl-item" floorid="1">'+
	                			'<div class="atl-head-reply moderator-delete"><a onclick="remove('+value.floorId+')">版主删除权限</a></div>'+
	                			'<div class="user" id="floorUser">'+
	                			'<a>'+value.user.userName+'</a>&nbsp;'+format(value.createTime)+'&nbsp;'+(++index)+'楼</div>'+
	                			'<div class="content" id="floorContent">'+value.floorContent+'</div></div>';
				});
				$("#floorShow").html(tempHtml);
				$(".moderator-delete").hide();
			} else {
				alert(data.stateInfo);
			}
		});
	}
	
	// 登录状态判断
	function loginState(){
		var loginStateUrl = "/a4q/personInfoAdmin/loginState?fresh=" + Math.random();
		$.ajax({
			url : loginStateUrl,
			type : "GET",
			asyn : false,
			success : function(data){
				if(data.state == 0){
					user = data.data;
					isLogin = true;
					$(".loginState").text("个人中心");
					$(".loginState").attr("href","personInfoShow.html?userId="+user.userId);
					$(".register").hide();
					moderatorJudge();
				}else{
					alert("f");
					isLogin = false;
				}
			}
		});
	}

	
	//回答操作
	function replyHandle(){
		if(isLogin){
			var addFloorUrl = "/a4q/floor/addFloor";
			var formData = new FormData();
			formData.append("postId",post.postId);
			formData.append("userId",user.userId);
			var floorContent = $(".floorContent").val();
			formData.append("floorContent",floorContent);
			$.ajax({
				url : addFloorUrl,
				contentType : false,
				processData : false,
				cache : false,
				asyn : false,
				type : "Post",
				data : formData,
				success : function(data){
					if(data.state == 0){
						alert("回答成功");
					}else{
						alert(data.stateInfo);
					}
				}
			});
		}else{
			alert("回答问题之前请先登录");
		}
	}

	//版主判断
	function moderatorJudge(){
		alert(courseId);
		var moderatorJudgeUrl = '/a4q/course/moderatorJudge?courseId='+courseId+"&fresh=" + Math.random();
		$.getJSON(moderatorJudgeUrl,function(data){
			if(data.state == 0){
				isModerator = true;
				$(".moderator-delete").show();
			}else{
				alert("不是版主");
			}
		});
	}

});

