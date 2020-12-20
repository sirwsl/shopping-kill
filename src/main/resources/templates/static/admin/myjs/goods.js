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
$("#EditModal").on("hidden.bs.modal",
function() {
	$("#id").val("");
	$("#name").val("");
	$("#shelf").val("");
	$("#detail").val("");
	$("#imgUrls").html("")
});
function loadFile(a) {
	$("#imgUrls").html(a.name)
}
$(function() {
	var a = $("#typeName").val();
	$.ajax({
		url: "https://localhost/admin/getTypesAll/v1",
		dataType: "json",
		type: "GET",
		async: true,
		data: {
			size: 100
		},
		success: function(d) {
			var e = d.data.records;
			var c;
			$("#typeId").empty();
			for (var b = 0; b < e.length; b++) {
				c += '<option value="' + e[b].id + '">' + e[b].name + "</option>"
			}
			$("#typeId").append(c)
		},
		error: function() {
			alert("查询失败！请检查你的网络或稍后重试")
		},
	})
});
$("#search").click(function() {
	goods($("#searchId").val(), $("#searchName").val(), $("#shelf").val())
});
$(function() {
	goods(null, null, null)
});
function goods(e, d, f) {
	$("#goods").bootstrapTable("destroy");
	$("#goods").bootstrapTable({
		method: "get",
		url: "https://localhost/admin/getGoods/v1",
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
		pageSize: 5,
		pageList: [5, 10, 20, 30],
		showRefresh: true,
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
			title: "商品名",
			field: "name",
			sortable: true
		},
		{
			title: "展示图",
			field: "imgUrl",
			formatter: function(h, i, g) {
				return '<img src = "' + i.imgUrl + '"></img>'
			}
		},
		{
			title: "类别",
			field: "typeName",
			sortable: true
		},
		{
			title: "商品描述",
			field: "detail",
			sortable: true
		},
		{
			title: "上架",
			formatter: function(i, j, g) {
				var h = '<label class="toggle toggle-positive">';
				if (j.shelf == true) {
					h = h + ' <input type="checkbox"  checked onclick="shelf(' + j.id + ')" />'
				} else {
					h = h + ' <input type="checkbox" onclick="shelf(' + j.id + ')" />'
				}
				return h + '<div class="track"><div class="handle"></div></div></label>'
			}
		},
		{
			title: '<table><thead><tr><th style="width:110px">skuId</th><th style="width:400px">商品属性</th><th>总数</th></tr></thead></table>',
			field: "skuList",
			formatter: function(i, j, g) {
				var h = "<table class='table'>";
				if (j.skuList != null) {
					$.each(j.skuList,
					function(k, l) {
						h += "<tr><td style='width:100px'>" + l.id + "</td><td style='width:400px'>" + l.attribute + "</td><td >" + l.num + "</td></tr>"
					})
				}
				h += "</table>";
				return h
			}
		},
		{
			title: "操作",
			field: "Button",
			align: "center",
			formatter: a
		}]
	});
	function b(h) {
		var g = {
			current: (h.offset / h.limit) + 1,
			size: h.limit,
			id: e,
			name: d,
			shelf: f
		};
		return g
	}
	function c(g) {
		if (g.code != 0) {
			alert("商品列表获取异常，请稍后再试");
			return null
		}
		return {
			"rows": g.data.records,
			"total": g.data.total
		}
	}
	function a(h, i, g) {
		return '<a data-toggle="modal" class="btn btn-primary" href="#EditModal" onclick="setValue(' + JSON.stringify(i).replace(/"/g, "&quot;") + ') ">编辑</a></br>' + '<a data-toggle="modal" class="btn btn-danger" onclick = "delGoods(' + i.id + ')">删除</a>'
	}
	e = null;
	d = null;
	f = null
}
function delGoods(a) {
	var b = confirm("确认删除嘛？");
	if (b == true) {
		$.ajax({
			url: "https://localhost/admin/delGoodsById/v1",
			data: {
				id: a
			},
			type: "delete",
			async: true,
			success: function(c) {
				if (c.code == 0) {
					alert(c.userMsg);
					$("#goods").bootstrapTable("refresh")
				} else {
					alert(c.userMsg)
				}
			},
			error: function(c) {
				alert(c.userMsg)
			}
		})
	}
}
var url;
var method;
$("#addGoods").click(function() {
	url = "https://localhost/admin/addGoods/v1";
	method = "POST"
});
function setValue(d, b) {
	$("#id").val(d.id);
	$("#name").val(d.name);
	$("#shelf").val(d.shelf);
	$("#detail").val(d.detail);
	$("#imgUrls").html(d.imgUrl.slice(0, -44));
	var a = $("#typeId  option");
	for (var c = 0; c < a.length; c++) {
		if (a[c].value == d.typeId) {
			a[c].selected = true
		}
	}
	url = "https://localhost/admin/updateGoods/v1";
	method = "PUT"
}
$("#sub").click(function() {
	$("#imgUrl").val($("#imgUrls").text());
	var a = new FormData(document.getElementById("addOrUpdateGoods"));
	$.ajax({
		type: method,
		url: url,
		data: a,
		contentType: false,
		processData: false,
		async: true,
		dataType: "json",
		success: function(b) {
			alert(b.userMsg);
			$("#goods").bootstrapTable("refresh")
		},
		error: function(b) {
			alert(b.userMsg)
		}
	})
});
function shelf(a) {
	$.ajax({
		type: "PUT",
		url: "https://localhost/admin/merchandise/v1",
		data: {
			id: a
		},
		dataType: "json",
		async: true,
		success: function(b) {
			alert(b.userMsg);
			$("#goods").bootstrapTable("refresh")
		},
		error: function(b) {
			alert(b.userMsg)
		}
	})
};