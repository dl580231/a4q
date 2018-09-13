		var currentPage=0;
		var pageCount=0;
		$(function() {
			pageInit(currentPage+1);
		});
		
		function lastPage(){
			pageInit(pageCount);
		}
		
		function headPage(){
			pageInit(1);
		}

		function beforePage(){
			pageInit(currentPage-1);
		}
		
		function afterPage(){
			pageInit(currentPage+1);
		}
		
		//实现分页查询的函数
		function pageInit(currentPageLocal,personInfo) {
			var initUrl = "/a4q/personInfoAdmin/getPersonInfoList";
			var data={};
			var pageDivide={};
			pageDivide.currentPage = currentPageLocal;
			data.pageDivide=pageDivide;
			$.ajax({
				url : initUrl,
				type : "POST",
				data : JSON.stringify(data),
				contentType : "application/json",
				dataType : false,
				success : function(data) {
					if (data.state == 0) {
						var pageDivide = data.data;
						/* 判断页码的显示结束 */
						if(pageDivide.pageCount == 1){
							$('.page-control').hide();
						}
						if(pageDivide.currentPage == 1){
							$('#head-page').hide();
							$('#before-page').hide();
						}else{
							$('#head-page').show();
							$('#before-page').show();
						}
						if(pageDivide.currentPage == pageDivide.pageCount){
							$("#last-page").hide();
							$('#after-page').hide();
						}else{
							$("#last-page").show();
							$('#after-page').show();
						}
						/* 判断页码的显示 结束*/
						/*给元素赋值开始*/
						$("#rowCount").text(pageDivide.rowCount);
						$("#currentPage").text(pageDivide.currentPage+'/'+pageDivide.pageCount);
						var tempHtml='<tr><th width="30"><input type="checkbox" id="all" onclick="selectOrClearAllCheckbox(this);" /></th>'+
							'<th>用户ID</th><th>用户名</th><th>性别</th><th>profile_img</th><th>电话</th><th>邮箱</th><th>user_type</th><th>label</th>'+
							'<th>创建时间</th><th>最后修改时间</th><th>操作</th></tr>';
						$.map(pageDivide.entityList, function(value, index) {
							tempHtml+='<tr><td><input type="checkbox" name="IDCheck" value="'+value.userId+'" class="acb" /></td><td>'+
							 value.userId+'</td><td>'+value.userName+'</td><td>'+value.gender+'</td><td>'+value.profileImg+'</td><td>'+value.phone+
							 '</td><td>'+value.email+'</td><td>'+value.userType+'</td><td>'+value.lable+'</td><td>'+value.createTime+'</td><td>'
							 +value.lastEditTime+'</td><td><a href="user_edit.html?fyID=14458579642011" class="edit">编辑</a>/'+
							 '<a onClick="removeUser('+value.userId+')">删除</a></td></tr>';
						});
						$('.table').html(tempHtml);
						/*给元素赋值结束*/
						pageCount = pageDivide.pageCount;
						currentPage = currentPageLocal;
					} else {
						alert(data.stateInfo);
					}
				}
			});
		}
		
		//实现用户删除
		function removeUser(userId){
			var removeUrl="/a4q/personInfoAdmin/removeUser?userId="+userId;
			$.getJSON(removeUrl,function (data){
				if(data.state == 0){
					alert("用户信息删除成功");
					pageInit(currentPage);
				}else{
					alert("删除失败");
				}
			});
		}
		
		//模糊查询用户信息
		function search(){
			//1.获取查询的信息
			var userName = $("#userName").val();
			var gender = $("#gender option:selected").val();
			var phone = $("#phone").val();
			var email = $("#email").val();
			var userType = $("#userType option:selected").val();
			var personInfo={};
			personInfo.userName = userName;
			personInfo.gender = gender;
			personInfo.phone = phone;
			personInfo.email = email;
			personInfo.userType = userType;
		}
		
		