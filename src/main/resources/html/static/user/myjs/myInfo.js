$(function(){
				getUserInfo();
			})
			$.ajaxSetup({
				headers: {
					"Authorization": $.cookie("token")
				},
				xhrFields: {
					withCredentials: true
				},
				crossDomain: true,
				async: true
				
			});
			$(function() {
				if ($.cookie("token") == null || $.cookie("token") == "") {
					window.location.href = "index.html"
				}
			});
			function getUserInfo(){
				$.get('http://localhost/user/getUserInfo/v1',function(msg){
					if (msg.code == 0){
						$('#myId').html(msg.data.id);
						$('#myName').html(msg.data.name);
						$('#myRealName').html(msg.data.realName);
						$('#idCard').html(msg.data.idCard);
						$('#myPhone').html(msg.data.phone);
						$('#myNickName').html(msg.data.nickName);
						$('#mySex').html(msg.data.sex);
						$('#mySignature').html(msg.data.sinanature);
						$('#creatTime').html(msg.data.creatTime);
						$('#myApply').html(msg.data.apply);
						$('#myWeChat').html(msg.data.weChat);
						$('#myEmail').html(msg.data.email);
						$('#imgId').attr('src',msg.data.img);
						$('#id').val(msg.data.id);
						$('#name').val(msg.data.name);
						$('#nickName').val(msg.data.nickName);
						$('#signature').val(msg.data.signature);
						$('#phone').val(msg.data.phone);
						$('#email').val(msg.data.email);
						$('#weChat').val(msg.data.weChat);
						$('#apply').val(msg.data.apply);
					}
				})
			}
			function shows(){
				if($('#file').val() != null){
					$('#imgshow').html('文件已上传，点击重新选择');
					$('#shows').html('所上传文件为：\n'+$('#file').val());
				}
			}
			$('#subjichu').click(function(){
				$("#regoster").ajaxSubmit(function(a) {
					if (a.code == 0) {
						alert(a.data);
						getUserInfo();
					} else {
						alert(a.userMsg);
					}
				})
			})
			
			$('#subPass').click(function(){
				$("#regosterpass").ajaxSubmit(function(a) {
					if (a.code == 0) {
						alert(a.data+"，即将为您跳转登录页面进行重新登录");
						window.location.href = "login.html";
					} else {
						alert(a.userMsg);
					}
				})
			})
			
			$('#subimg').click(function(){
				$("#regosterimg").ajaxSubmit(function(a) {
					if (a.code == 0) {
						getUserInfo();
						alert(a.data);
					} else {
						alert(a.userMsg);
					}
					$(".mask").hide();
					$(".adddz").hide();
					$(".avatar").hide();
				})
			})
			