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
$("#Editupdate").on("hidden.bs.modal",
function() {
	$("#myLabel").html("添加广告");
	$("#id").val("");
	$("#imgUrls").html("");
	$("#targetUrl").val("");
	$("#startTime").val("");
	$("#endTime").val("")
});
var flag = false;
function loadFile(a) {
	$("#imgUrls").html(a.name)
}
$("#sub").click(function() {
	var a = "https://localhost/admin/addAdvertise/v1";
	if (flag) {
		a = "https://localhost/admin/updateAdvertise/v1";
		flag = false
	}
	if ($("#targetUrl").val() == null) {
		alert("目标地址不能为空");
		return
	}
	$("#imgUrl").val($("#imgUrls").text());
	var b = new FormData(document.getElementById("advertise"));
	$.ajax({
		type: "post",
		url: a,
		data: b,
		contentType: false,
		processData: false,
		dataType: "json",
		success: function(c) {
			alert(c.userMsg);
			$("#advertiesmant").bootstrapTable("refresh")
		},
		error: function(c) {
			alert(c.userMsg)
		}
	})
});
function col(b) {
	var a;
	if (b == 0) {
		a = "https://localhost/admin/getAdvertiseAll/v1"
	} else {
		if (b == 1) {
			a = "https://localhost/admin/getAdvertiseBegin/v1"
		} else {
			if (b == 2) {
				a = "https://localhost/admin/getAdvertiseDoing/v1"
			} else {
				if (b == 3) {
					a = "https://localhost/admin/getAdvertiseOver/v1"
				}
			}
		}
	}
	dev(a)
}
$(function() {
	var a = "https://localhost/admin/getAdvertiseAll/v1";
	dev(a)
});
function dev(a) {
	$("#advertiesmant").bootstrapTable("destroy");
	$("#advertiesmant").bootstrapTable({
		method: "get",
		url: a,
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
		queryParams: c,
		responseHandler: d,
		columns: [{
			title: "Id",
			field: "id",
			sortable: true
		},
		{
			title: "广告图片",
			field: "imgUrl",
			formatter: function(f, g, e) {
				return '<img src="' + g.imgUrl + '" style="width: 50px; height: 50px;" />'
			}
		},
		{
			title: "目标地址",
			field: "targetUrl",
			formatter: function(f, g, e) {
				return '<a target="_blank" href="' + g.targetUrl + '">' + g.targetUrl + "</a>"
			}
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
			formatter: b
		}]
	});
	function c(f) {
		var e = {
			page: (f.offset / f.limit) + 1,
			size: f.limit,
		};
		return e
	}
	function d(e) {
		return {
			"rows": e.data.records,
			"total": e.data.total
		}
	}
	function b(h, i, g) {
		var e = new Array();
		var f = '<a data-toggle="modal" class="btn btn-primary" href="#Editupdate" onclick="setValue(' + JSON.stringify(i).replace(/"/g, "&quot;") + ') ">编辑</a></br>' + '<a data-toggle="modal" class="btn btn-danger" onclick = "del(' + i.id + ')">删除</a>';
		return f
	}
}
function setValue(b, a) {
	$("#myLabel").html("广告id: " + b.id);
	$("#id").val(b.id);
	$("#imgUrls").html(b.imgUrl.slice(0, -44));
	$("#targetUrl").val(b.targetUrl);
	$("#startTime").val(b.startTime);
	$("#endTime").val(b.endTime);
	flag = true
}
function del(a) {
	var b = confirm("确认删除吗？");
	if (b == true) {
		$.ajax({
			type: "delete",
			url: "https://localhost/admin/delAdvertise/v1",
			data: {
				"id": a
			},
			dateType: "json",
			success: function(c) {
				alert(c.userMsg);
				$("#advertiesmant").bootstrapTable("refresh")
			},
			error: function(c) {
				alert(c.userMsg)
			}
		})
	}
};