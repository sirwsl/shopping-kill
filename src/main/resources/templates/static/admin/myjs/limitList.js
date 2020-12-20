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
var id999;
var update = true;
var flag = true;
var url = "https://test.wslhome.top/admin/getBlackListForPhone/v1";
var number = "";
$(function() {
	limit();
	$("#titles").html("手机黑名单");
	$("#seNum").attr("placeholder", "请输入手机号查询")
});
function change(a) {
	$("#titles").html("手机号黑名单");
	if (a == 10) {
		url = "https://test.wslhome.top/admin/getBlackListForIp/v1";
		flag = false;
		$("#titles").html("IP地址黑名单");
		$("#seNum").attr("placeholder", "请输入IP地址查询")
	}
	limit();
	$("#limitList").bootstrapTable("refresh")
}
$("#search").click(function() {
	url = "https://test.wslhome.top/admin/selectBlackListByPhone/v1";
	if (!flag) {
		url = "https://test.wslhome.top/admin/selectBlackListByIp/v1"
	}
	number = $("#seNum").val();
	limit();
	$("#limitList").bootstrapTable("refresh")
});
function limit() {
	$("#limitList").bootstrapTable("destroy");
	$("#limitList").bootstrapTable({
		method: "get",
		url: url,
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
		pageList: [5, 10, 20, 30],
		showRefresh: true,
		silent: true,
		cache: false,
		queryParams: a,
		responseHandler: b,
		columns: [{
			checkbox: true
		},
		{
			title: "Id",
			field: "id",
			sortable: true
		},
		{
			title: "号码/地址",
			field: "number",
			sortable: true
		},
		{
			title: "开始时间",
			field: "startTime",
			sortable: true
		},
		{
			title: "结束时间",
			field: "endTime",
			sortable: true
		},
		{
			title: "更新时间",
			field: "updateTime",
			sortable: true
		},
		{
			title: "操作",
			field: "Button",
			align: "center",
			formatter: function(e, f, c) {
				var d = '<a data-toggle="modal" style="margin: 5px;" class="btn btn-success" data-target="#EditModal" onclick = "updates(' + JSON.stringify(f).replace(/"/g, "&quot;") + ');">修改</a>' + '<a style="margin: 5px;" class="btn btn-danger" onclick = "delonly(' + f.id + ');">删除</a>';
				return d
			}
		}]
	});
	function a(d) {
		var c = {
			page: (d.offset / d.limit) + 1,
			num: d.limit,
			number: number
		};
		return c
	}
	function b(c) {
		return {
			"rows": c.data.records,
			"total": c.data.total
		}
	}
}
function updates(b, a) {
	if (flag) {
		$("#addtitle").html("添加手机号")
	} else {
		$("#addtitle").html("添加IP地址")
	}
	$("#EditModal").on("show.bs.modal",
	function(d) {
		var e = $(d.relatedTarget).data("id");
		var c = $(this);
		c.find("#adress").val(b.number);
		c.find("#startTime").val(b.startTime);
		c.find("#endTime").val(b.endTime)
	});
	id999 = b.id;
	update = false;
	type = b.type
}
function delonly(a) {
	if (!confirm("是否确认删除？")) {
		return
	}
	$.ajax({
		url: "https://test.wslhome.top/admin/delBlackListById/v1",
		data: "id=" + a,
		type: "delete",
		dataType: "json",
		success: function(b) {
			$("#limitList").bootstrapTable("refresh")
		},
		error: function(b) {
			alert(b.userMsg)
		}
	})
}
$("#del").on("click",
function() {
	if (!confirm("是否确认删除？")) {
		return
	}
	var b = $("#limitList").bootstrapTable("getSelections");
	if (b.length == 0) {
		alert("请先选择要删除的记录!");
		return
	} else {
		var a = new Array();
		$(b).each(function() {
			a.push(this.id)
		});
		deleteMs(a)
	}
});
function deleteMs(a) {
	$.ajax({
		url: "https://test.wslhome.top/admin/delBlackListByIds/v1",
		data: "ids=" + a,
		type: "delete",
		dataType: "json",
		success: function(b) {
			$("#limitList").bootstrapTable("refresh")
		}
	})
}
$("#addLimit").click(function() {
	if (flag) {
		$("#addtitle").html("添加手机号");
		$("#adress").attr("placeholder", "请输入手机号，eg: 18300000000")
	} else {
		$("#addtitle").html("添加IP地址");
		$("#adress").attr("placeholder", "请输入ip地址，eg: 127.0.0.1")
	}
});
function submits(c) {
	var b = "https://test.wslhome.top/admin/addBlackListByPhone/v1";
	if (!flag) {
		b = "https://test.wslhome.top/admin/addBlackListByIp/v1"
	}
	if (!update) {
		b = "https://test.wslhome.top/admin/updateBlackListById/v1"
	}
	var c = $("#adress").val();
	var a = $("#startTime").val();
	var e = $("#endTime").val();
	var f = /^[1][3,4,5,7,8][0-9]{9}$/;
	if (flag && !f.test(c)) {
		alert("手机号不正确");
		return
	}
	var d = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
	if (!flag && !d.test(c)) {
		alert("IP地址不正确");
		return
	}
	$.ajax({
		type: "POST",
		url: b,
		dataType: "json",
		data: {
			"id": id999,
			"number": c,
			"startTime": a,
			"endTime": e,
		},
		success: function(g) {
			alert(g.userMsg);
			$("#limitList").bootstrapTable("refresh")
		},
		error: function() {
			alert("失败,请稍后再试")
		}
	});
	update = true;
	id999 = "";
	type = ""
};