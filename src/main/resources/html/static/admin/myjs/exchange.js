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
$("#search").on("click",
function() {
	logger($("#SId").val(), $("#orderId").val(), $("#types").val(), $("#resout").val())
});
$(function() {
	logger(null, null, null, null)
});
function logger(f, b, d, a) {
	$("#afterSale").bootstrapTable("destroy");
	$("#afterSale").bootstrapTable({
		method: "get",
		url: "http://localhost/admin/getAfterSalesAll/v1",
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
		pageSize: 10,
		pageList: [5, 10, 20, 30, 50],
		showRefresh: true,
		silent: true,
		cache: false,
		singleSelect: true,
		showColumns: true,
		queryParams: c,
		responseHandler: e,
		columns: [{
			title: "售后编号",
			field: "id",
			sortable: true
		},
		{
			title: "交易单号",
			field: "orderId",
			sortable: true
		},
		{
			title: "用户Id",
			field: "userId",
			sortable: true,
			visible: false
		},
		{
			title: "用户姓名",
			field: "userName",
			sortable: true
		},
		{
			title: "用户手机号",
			field: "userPhone",
			visible: false
		},
		{
			title: "用户昵称",
			field: "userNickName",
			visible: false
		},
		{
			title: "物品id",
			field: "goodsId",
			sortable: true,
			visible: false
		},
		{
			title: "物品名称",
			field: "goodsName",
			sortable: true
		},
		{
			title: "SkuId",
			field: "skuId",
			sortable: true,
			visible: false
		},
		{
			title: "(SKU)商品属性",
			field: "skuDetail",
			sortable: true
		},
		{
			title: "购买数量",
			field: "num",
			sortable: true
		},
		{
			title: "购买价格",
			field: "payPrice",
			sortable: true
		},
		{
			title: "售后类型",
			field: "type",
			formatter: function(h, i, g) {
				if (i.type == 1) {
					return "仅退款"
				} else {
					if (i.type == 2) {
						return "换货"
					} else {
						if (i.type == 3) {
							return "退货退款"
						}
					}
				}
			}
		},
		{
			title: "售后内容",
			field: "detail",
		},
		{
			title: "处理结果",
			field: "result",
			formatter: function(h, i, g) {
				if (i.result) {
					return '<i class="fa fa-check text-navy">已处理</i>'
				} else {
					return '<a data-toggle="modal" href="#Modal" class="btn btn-info" onclick = "setValue(' + JSON.stringify(i).replace(/"/g, "&quot;") + ')">处理</a>'
				}
			}
		}]
	});
	function c(h) {
		var g = {
			current: (h.offset / h.limit) + 1,
			size: h.limit,
			id: f,
			orderId: b,
			type: d,
			result: a
		};
		return g
	}
	function e(g) {
		return {
			"rows": g.data.records,
			"total": g.data.total
		}
	}
}
function setValue(d, a) {
	var c = "订单类型:&nbsp&nbsp&nbsp";
	if (d.type == 1) {
		c += " 仅退款"
	} else {
		if (d.type == 2) {
			c += " 换货"
		} else {
			if (d.type == 3) {
				c += " 退货退款"
			}
		}
	}
	$("#myTypes").html(c);
	$("#oldSku").html("原Sku(" + d.skuId + "):&nbsp&nbsp&nbsp" + d.skuDetail);
	$("#skuId").empty();
	var b = "";
	$.each(d.skuList,
	function(e, f) {
		b += '<option value="' + f.id + '">(' + f.num + ")" + f.attribute + "</option>"
	});
	$("#skuId").append(b);
	$("#id").val(d.id);
	$("#type").val(d.type);
	$("#oldSkuId").val(d.skuId)
}
$("#sub").click(function() {
	var a = new FormData(document.getElementById("updates"));
	$.ajax({
		type: "put",
		url: "http://localhost/admin/refundGoodsAndMoney/v1",
		data: a,
		contentType: false,
		processData: false,
		dataType: "json",
		success: function(b) {
			if (b != 0) {
				alert(b.userMsg)
			}
			$("#afterSale").bootstrapTable("refresh")
		},
		error: function(b) {
			alert(b.userMsg)
		}
	})
});