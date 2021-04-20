	
	$.ajaxSetup({
		headers: {
			"Authorization": $.cookie("token")
		},
		xhrFields: {
			withCredentials: true
		},
		crossDomain: true
	})
	
	$("#denglu").click(function() {
		if ($("#codes").val() == null || $("#codes").val() ==''){
			alert("请输入验证码");
			return;
		}
		if ($("#name").val() == null || $("#name").val() ==''){
			alert("注册账号不能为空");
			return;
		}
		if ($("#password").val() == null || $("#name").val() ==''){
			alert("密码不能为空");
			return;
		}
		if ($("#password1").val() == null || $("#password1").val() ==''){
			alert("确认密码不能为空");
			return;
		}
		if ($("#nickName").val() == null || $("#nickName").val() ==''){
			alert("昵称不能为空");
			return;
		}
		if ($("#realName").val() == null || $("#realName").val() ==''){
			alert("真实姓名不能为空");
			return;
		}
		if ($("#idCard").val() == null || $("#idCard").val() ==''){
			alert("身份证号不能为空");
			return;
		}
		if ($("#phone").val() == null || $("#phone").val() ==''){
			alert("手机号不能为空");
			return;
		}
		if ($("#email").val() == null || $("#email").val() ==''){
			alert("邮箱不能为空");
			return;
		}
		if ($("#password").val() != $("#password1").val() ){
			alert("两次输入密码不一致");
			return;
		}
		$("#regoster").ajaxSubmit(function(a) {
			if (a.code == 0) {
				alert(a.data);
				parent.location.href = "login.html";
			} else {
				alert(a.userMsg);
				$("#imgcode").attr("src", "http://localhost/verify/getJpg/v1?id=" + Math.random());
			}
		})
	})
	