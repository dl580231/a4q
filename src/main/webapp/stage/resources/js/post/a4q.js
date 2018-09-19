$(function() {
	var isLogin = false;
	var user = null;
	loginState();
	initCourseInfo();
	$("#deploy").click(function() {
		submitForm();
	});

	// 初始化课程信息
	function initCourseInfo() {
		var initCourseInfoUrl = "/a4q/course/getCourseListName";
		$.getJSON(initCourseInfoUrl, function(data) {
			if (data.state == 0) {
				var tempHtml = '<option value="">选择分类</option>';
				$.map(data.data, function(value, index) {
					tempHtml += '<option value="' + value.courseId + '">'
							+ value.courseName + '</option>';
				});
				$("#courseInfo").html(tempHtml);
			} else {
				alert(data.stateInfo);
			}
		});
	}

	// 提交表单
	function submitForm() {
		if (user == null) {
			alert("发表问题之前请登录,登录成功后刷新页面");
		} else {
			var deployUrl = "/a4q/post/deployPost";
			var data = $("#submitForm").serialize();
			$.ajax({
				url : deployUrl,
				type : "POST",
				cache : false,
				data : data,
				success : function(data) {
					if (data.state == 0) {
						alert("发表成功");
					} else {
						alert(data.stateInfo);
					}
				}
			});
		}
	}

	// 登录状态判断
	function loginState() {
		var loginStateUrl = "/a4q/personInfoAdmin/loginState";
		$.ajax({
			url : loginStateUrl,
			type : "GET",
			asyn : false,
			success : function(data) {
				if (data.state == 0) {
					user = data.data;
					isLogin = true;
					$(".loginState").text("个人中心");
					$(".loginState").attr("href",
							"personInfoShow.html?userId=" + user.userId);
					$(".register").hide();
				} else {
					alert("提问之前需要登陆");
					isLogin = false;
				}
			}
		});
	}
});