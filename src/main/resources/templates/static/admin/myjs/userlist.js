$.ajaxSetup({
	headers: {
		"Authorization": $.cookie("token")
	},
	xhrFields: {
		withCredentials: false
	},
	crossDomain: true
});
$(function() {
	if ($.cookie("token") == null || $.cookie("token") == "") {
		window.location.href = "/admin/login.html"
	}
});
var value;
var name;
$(function() {
	$("#userTable").bootstrapTable("destroy");
	$("#userTable").bootstrapTable({
		method: "get",
		url: "https://test.wslhome.top/admin/getUserAll/v1",
		dataType: "json",
		headers: {
			"Authorization": $.cookie("token")
		},
		ajaxOptions: {
			xhrFields: {
				withCredentials: false
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
		queryParams: a,
		responseHandler: b,
		columns: [{
			title: "Id",
			field: "id",
			sortable: true
		},
		{
			title: "昵称",
			field: "nickName",
			sortable: true
		},
		{
			title: "账号",
			field: "name",
			sortable: true
		},
		{
			title: "密码",
			field: "password",
			sortable: true
		},
		{
			title: "头像",
			field: "img",
			sortable: true
		},
		{
			title: "性别",
			field: "sex",
			formatter: function(d, e, c) {
				if (e.sex == 0) {
					return "男"
				} else {
					return "女"
				}
			}
		},
		{
			title: "手机",
			field: "phone",
			sortable: true
		},
		{
			title: "邮箱",
			field: "email",
			sortable: true
		},
		{
			title: "身份证号",
			field: "idCard",
			sortable: true
		},
		{
			title: "真实姓名",
			field: "realName",
			sortable: true
		},
		{
			title: "微信",
			field: "weChat",
			sortable: true
		},
		{
			title: "支付宝账户",
			field: "apply",
			sortable: true
		},
		{
			title: "注册时间",
			field: "creatTime",
			sortable: true
		},
		{
			title: "操作",
			field: "grade",
			formatter: function(d, e, c) {
				return '<a data-toggle="modal" class="btn btn-info" href="#modal-form" data-toggle="modal" data-target="#myModal" onclick = "setValue(' + e.id + ')">编辑</a>'
			}
		}]
	});
	function a(d) {
		var c = {
			current: (d.offset / d.limit) + 1,
			size: d.limit
		};
		return c
	}
	function b(c) {
		return {
			"rows": c.data.records,
			"total": c.data.total
		}
	}
});
function setValue(a) {
	value = a;
	$("#id").val(value)
}
$("#sub").click(function() {
	$("#addBlackList").ajaxSubmit(function(a) {
		alert(a.userMsg)
	})
});
$("#del").click(function() {
	$.ajax({
		type: "DELETE",
		url: "https://test.wslhome.top/admin/delUser/v1",
		data: {
			id: value
		},
		success: function(a) {
			alert(a.userMsg);
			$("#userTable").bootstrapTable("refresh")
		},
		error: function(a) {
			alert(a.userMsg)
		}
	})
});