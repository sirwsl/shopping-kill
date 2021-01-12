$.ajaxSetup({
	headers: {
		"Authorization": $.cookie("token")
	},
	xhrFields: {
		withCredentials: true
	},
	crossDomain: true
});
$(function() {
	if ($.cookie("token") == null || $.cookie("token") == "") {
		window.location.href = "/admin/login.html"
	}
});
$(function() {
	$.get("https://test.wslhome.top/admin/getAdmin/v1",
	function(a) {
		$("#id").val(a.data.id);
		$("#name").val(a.data.name);
		$("#password").val(a.data.password);
		$("#nickName").val(a.data.nickName);
		$("#img").val(a.data.img);
		$("#sex").val(a.data.sex);
		$("#idCard").val(a.data.idCard);
		$("#phone").val(a.data.phone);
		$("#mail").val(a.data.mail);
		$("#address").val(a.data.address);
		$("#creatTime").val(a.data.creatTime)
	})
});
$("#sub").click(function() {
	if ($("#confirm_password").val() != $("#password").val()) {
		alert("两次输入密码不一致");
		return
	}
	if ($("#password").val().length < 6) {
		alert("密码不能少于6位");
		return
	}
	if ($("#phone").val() == null) {
		alert("手机号不能为空");
		return
	}
	var a = /^[1][3,4,5,7,8][0-9]{9}$/;
	if (!a.test($("#phone").val())) {
		alert("手机号不正确");
		return
	}
	if ($("#mail").val() == null) {
		alert("邮箱不能为空不正确");
		return
	}
	if ($("#address").val() == null) {
		alert("家庭住址不能为空不正确");
		return
	}
	if ($("#sex").val() == "男") {
		$("#sex").val("MAN")
	} else {
		$("#sex").val("WOMAN")
	}
	$.ajax({
		type: "put",
		url: "https://test.wslhome.top/admin/updateAdmin/v1",
		data: $("#login").serialize(),
		dataType: "json",
		success: function(b) {
			alert(b.userMsg)
		},
		error: function(b) {
			alert(b.userMsg)
		}
	})
});