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
$(document).ready(function() {
	$(".footable").footable()
});
$("#search").on("click",
function() {
	logger($("#ids").val(), $("#userNames").val(), $("#goodsNames").val())
});
$(function() {
	logger(null, null, null)
});
function logger(e, d, a) {
	$("#cheet").bootstrapTable("destroy");
	$("#cheet").bootstrapTable({
		method: "get",
		url: "https://localhost/admin/getAppraisalAll/v1",
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
		pageSize: 10,
		pageList: [5, 10, 20, 30, 50],
		showRefresh: true,
		silent: true,
		cache: false,
		singleSelect: true,
		showColumns: true,
		queryParams: b,
		responseHandler: c,
		columns: [{
			title: "评价id",
			field: "id",
			sortable: true
		},
		{
			title: "用户姓名",
			field: "userName",
		},
		{
			title: "用户昵称",
			field: "userNickName",
		},
		{
			title: "物品名称",
			field: "goodsName",
		},
		{
			title: "评价等级",
			field: "grade",
			sortable: true
		},
		{
			title: "评价内容",
			field: "detail",
			visible: false
		},
		{
			title: "创建时间",
			field: "creatTime",
			sortable: true
		},
		{
			title: "操作",
			field: "tool",
			formatter: function(g, h, f) {
				return '<a data-toggle="modal" class="btn btn-danger" onclick = "delActivity(' + h.id + ')">删除</a>'
			}
		}]
	});
	function b(g) {
		var f = {
			current: (g.offset / g.limit) + 1,
			size: g.limit,
			id: e,
			userName: d,
			goodsName: a
		};
		return f
	}
	function c(f) {
		return {
			"rows": f.data.records,
			"total": f.data.total
		}
	}
}
function delActivity(b) {
	var a = confirm("确认删除嘛？");
	if (a == true) {
		$.ajax({
			url: "https://localhost/admin/delAppraisalById/v1",
			data: {
				id: b
			},
			type: "delete",
			success: function(c) {
				if (c.code == 0) {
					alert("删除成功");
					$("#cheet").bootstrapTable("refresh")
				} else {
					alert(c.userMsg)
				}
			},
			error: function(c) {
				alert(c.userMsg)
			}
		})
	}
};