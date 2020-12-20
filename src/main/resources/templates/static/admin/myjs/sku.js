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
var index = true;
var flag = false;
$("#addGoods").click(function() {
	flag = true;
	if (index) {
		$.ajax({
			type: "get",
			url: "https://localhost/admin/getAllGoodsName/v1",
			data: {
				"status": 1
			},
			dataType: "json",
			success: function(a) {
				$("#goodsIds").bComboSelect({
					showField: "name",
					keyField: "id",
					data: a.data
				})
			},
			error: function(a) {
				alert(a.userMsg)
			}
		});
		index = false
	}
});
function loadFile(a) {
	$("#imgUrls").html(a.name)
}
$("#search").click(function() {
	logger($("#SId").val(), $("#SName").val())
});
$(function() {
	logger()
});
function logger(d, a) {
	$("#sku").bootstrapTable("destroy");
	$("#sku").bootstrapTable({
		method: "get",
		url: "https://localhost/admin/getSkuAll/v1",
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
			title: "物品id",
			field: "goodsId",
			sortable: true
		},
		{
			title: "物品名称",
			field: "goodsName",
			sortable: true
		},
		{
			title: "属性",
			field: "attribute",
			sortable: true
		},
		{
			title: "展示图",
			field: "imgUrl",
			formatter: function(f, g, e) {
				return '<img src= "' + g.imgUrl + '" ></img>'
			}
		},
		{
			title: "进价",
			field: "realPrice",
			sortable: true
		},
		{
			title: "成本价",
			field: "costPrice",
			sortable: true
		},
		{
			title: "售价",
			field: "sellPrice",
			sortable: true
		},
		{
			title: "剩余数量",
			field: "num",
			sortable: true
		},
		{
			title: "预警量",
			field: "warnNum",
			sortable: true
		},
		{
			title: "邮费",
			field: "expPrice",
			sortable: true
		},
		{
			title: "操作",
			formatter: function(f, g, e) {
				return '<a data-toggle="modal" class="btn btn-primary" href="#myModal" onclick="setValue(' + JSON.stringify(g).replace(/"/g, "&quot;") + ') ">编辑</a></br>' + '<a data-toggle="modal" class="btn btn-danger" onclick = "delActivity(' + g.id + ')">删除</a>'
			}
		}]
	});
	function b(f) {
		var e = {
			current: (f.offset / f.limit) + 1,
			size: f.limit,
			id: d,
			name: a
		};
		return e
	}
	function c(e) {
		return {
			"rows": e.data.records,
			"total": e.data.total
		}
	}
}
function delActivity(b) {
	var a = confirm("确认删除嘛？");
	if (a == true) {
		$.ajax({
			url: "https://localhost/admin/delSkuById/v1",
			data: {
				id: b
			},
			type: "delete",
			success: function(c) {
				if (c.code == 0) {
					alert("删除成功");
					$("#sku").bootstrapTable("refresh")
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
function setValue(b) {
	$("#id").val(b.id);
	$("#goodsId").val(b.goodsId);
	$("#goodsName").val(b.goodsName);
	$("#attribute").val(b.attribute);
	$("#imgUrles").html(b.imgUrl.slice(0, -44));
	$("#realPrice").val(b.realPrice);
	$("#costPrice").val(b.costPrice);
	$("#sellPrice").val(b.sellPrice);
	$("#num").val(b.num);
	$("#warnNum").val(b.warnNum);
	$("#expPrice").val(b.expPrice);
	$("#goodsName").attr("readonly", true);
	var a = b.goodsName;
	var c = b.goodsId
}
$("#sub").click(function() {
	$("#imgUrl").val($("#imgUrles").text());
	var a = new FormData(document.getElementById("UpdateSku"));
	$.ajax({
		type: "post",
		url: "https://localhost/admin/updateSkuInfo/v1",
		data: a,
		contentType: false,
		processData: false,
		dataType: "json",
		success: function(b) {
			alert(b.userMsg);
			$("#sku").bootstrapTable("refresh")
		},
		error: function(b) {
			alert(b.userMsg)
		}
	})
});
$("#subs").click(function() {
	var a = new FormData(document.getElementById("addSku"));
	$.ajax({
		type: "post",
		url: "https://localhost/admin/addSkuInfo/v1",
		data: a,
		contentType: false,
		processData: false,
		dataType: "json",
		success: function(b) {
			alert(b.userMsg);
			$("#sku").bootstrapTable("refresh")
		}
	})
});