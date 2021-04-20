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
var flag = true;
$(function() {
	var a = "http://localhost/admin/getSubscriberBySms/v1";
	$("#title").html("短信订阅列表");
	logger(a);
	view()
});
function change(a) {
	if (a == 100) {
		url = "http://localhost/admin/getSubscriberBySms/v1";
		flag = true
	} else {
		url = "http://localhost/admin/getSubscriberByEmail/v1";
		flag = false
	}
	logger(url);
	view()
}
function view() {
	if (flag) {
		$("#title").html("短信订阅列表");
		$("#add").html("短信订阅（手机号）");
		$("#type").val(1)
	} else {
		$("#title").html("邮箱订阅列表");
		$("#add").html("邮件订阅");
		$("#type").val(0)
	}
}
$("#addLimit").click(function() {
	view()
});
$("#addsub").click(function() {
	$("#addNumber").ajaxSubmit(function(a) {
		alert(a.userMsg);
		$("#sub").bootstrapTable("refresh")
	})
});
function logger(a) {
	$("#sub").bootstrapTable("destroy");
	$("#sub").bootstrapTable({
		method: "get",
		url: a,
		dataType: "json",
		headers: {
			"Authorization": $.cookie("token")
		},
		ajaxOptions: {
			xhrFields: {
				withCredentials: true
			},
			crossDomain: true
		},
		striped: true,
		pageNumber: 1,
		pagination: true,
		sidePagination: "server",
		pageSize: 15,
		pageList: [5, 10, 15, 20, 30, 50],
		showRefresh: false,
		silent: true,
		cache: false,
		queryParams: b,
		responseHandler: c,
		columns: [{
			title: "Id",
			field: "id",
			sortable: true
		},
		{
			title: "号码",
			field: "number",
			sortable: true
		},
		{
			title: "是否订阅",
			field: "status",
			formatter: function(e, f, d) {
				if (f.status == 0) {
					return "是"
				} else {
					return "否"
				}
			}
		},
		{
			title: "操作",
			field: "Button",
			align: "center",
			formatter: function(e, f, d) {
				return '<a style="margin: 5px;" class="btn btn-danger" onclick = "delonly(' + f.id + ');">删除</a>'
			}
		}]
	});
	function b(e) {
		var d = {
			current: (e.offset / e.limit) + 1,
			size: e.limit
		};
		return d
	}
	function c(d) {
		return {
			"rows": d.data.records,
			"total": d.data.total
		}
	}
}
function delonly(a) {
	if (!confirm("是否确认删除？")) {
		return
	}
	$.ajax({
		url: "http://localhost/admin/delSubscriber/v1",
		data: "id=" + a,
		type: "delete",
		dataType: "json",
		success: function(b) {
			alert(b.userMsg);
			$("#sub").bootstrapTable("refresh")
		},
		error: function(b) {
			alert(b.userMsg)
		}
	})
};