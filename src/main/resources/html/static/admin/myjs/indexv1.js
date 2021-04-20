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
		window.location.href = "/html/admin/login.html"
	}
});
$.ajax({
	type: "GET",
	url: "http://localhost/admin/getLoggersAll/v1",
	dataType: "json",
	success: function(a) {
		if (a.code == 0 && a.data != null) {
			$("#admin").bootstrapTable("load", a.data.admin.records);
			$("#user").bootstrapTable("load", a.data.user.records)
		}
		$("#admin").bootstrapTable("hideLoading");
		$("#user").bootstrapTable("hideLoading")
	},
	error: function() {
		alert("获取失败")
	}
});
$.ajax({
	type: "GET",
	url: "http://localhost/admin/getTotalToday/v1",
	dataType: "json",
	success: function(a) {
		if (a.code == 0 && a.data != null) {
			$("#Number").html(a.data.Number);
			$("#TodayOrder").html(a.data.TodayOrder);
			$("#TodayNum").html(a.data.TodayNum);
			$("#TodayOut").html(a.data.TodayOut)
		} else {
			$("#Number").html(0);
			$("#TodayOrder").html(0);
			$("#TodayNum").html(0);
			$("#TodayOut").html(0)
		}
	},
	error: function(a) {
		alert(a.userMsg)
	}
});