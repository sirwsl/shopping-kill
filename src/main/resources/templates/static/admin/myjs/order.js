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
function onclicks(a) {
	order(a, null, null, null, null, null)
}
$("#search").on("click",
function() {
	order($("#type").val(), $("#id").val(), $("#nickName").val(), $("#name").val(), $("#goodsId").val(), $("#userId").val())
});
$(function() {
	order(2, null, null, null, null, null)
});
function order(e, h, g, b, a, c) {
	$("#order").bootstrapTable("destroy");
	$("#order").bootstrapTable({
		method: "get",
		url: "https://localhost/admin/getOrderAllById/v1",
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
		queryParams: d,
		responseHandler: f,
		columns: [{
			title: "订单编号",
			field: "id",
			sortable: true
		},
		{
			title: "用户昵称",
			field: "nickName",
		},
		{
			title: "商品名",
			field: "goodsName",
		},
		{
			title: "商品属性",
			field: "attribute",
		},
		{
			title: "购买数量",
			field: "num",
		},
		{
			title: "支付金额",
			field: "payPrice",
		},
		{
			title: "创建时间",
			field: "orderTime",
		},
		{
			title: "支付时间",
			field: "payTime",
			sortable: true,
			visible: e == 2 || e == 3 ? true: false
		},
		{
			title: "出库时间",
			field: "sendTime",
			sortable: true,
			visible: e == 3 ? true: false
		},
		{
			title: "收件人",
			field: "sendName",
			sortable: true,
		},
		{
			title: "收件人电话",
			field: "sendPhone",
			sortable: true
		},
		{
			title: "收件地址",
			field: "remark",
			visible: false
		},
		{
			title: "处理结果",
			field: "result",
			formatter: function(j, k, i) {
				if (e == 1) {
					return '<a data-toggle="modal" href="#Modal"  style="margin-left: 5px;"  class="btn btn-danger" onclick = "onmoney(' + JSON.stringify(k).replace(/"/g, "&quot;") + ')">修改价格</a>' + '<a data-toggle="modal"  style="margin-left: 5px;"  class="btn btn-info" onclick = "onpay(' + k.id + ')">提醒支付</a>'
				} else {
					if (e == 2) {
						return '<a data-toggle="modal" class="btn btn-primary" onclick = "onout(' + k.id + ')">出库</a>'
					} else {
						if (e == 3) {
							return '<a data-toggle="modal" class="btn btn-info" onclick = "onlook(' + k.id + ')">查看物流</a>'
						} else {
							if (e == 4) {
								return '<a data-toggle="modal" class="btn btn-success" onclick = "oncomm(' + k.id + ')">提醒评价</a>'
							} else {
								if (e == 5) {
									return '<i class="fa fa-check text-navy">已评价</i>'
								} else {
									if (e == 0) {
										return '<i class="fa fa-warning text-navy">已取消</i>'
									}
								}
							}
						}
					}
				}
			}
		}]
	});
	function d(j) {
		var i = {
			current: (j.offset / j.limit) + 1,
			size: j.limit,
			id: h,
			type: e,
			nickName: g,
			name: b,
			goodsId: a,
			userId: c
		};
		return i
	}
	function f(i) {
		return {
			"rows": i.data.records,
			"total": i.data.total
		}
	}
}
function onmoney(b, a) {
	$("#orderId").val(b.id);
	$("#bigDecimal").val(b.payPrice);
	$("#oldMoney").val(b.payPrice)
}
$("#sub").click(function() {
	$("#exchanges").ajaxSubmit(function(a) {
		alert(a.userMsg)
	})
});
function onpay(b) {
	var a = "https://localhost/admin/remind2Pay/v1";
	total(a, b)
}
function onout(b) {
	var a = "https://localhost/admin/outGoods/v1";
	total(a, b)
}
function oncomm(b) {
	var a = "https://localhost/admin/reminderEvaluation/v1";
	total(a, b)
}
function total(a, b) {
	$.ajax({
		url: a,
		data: {
			orderId: b
		},
		type: "get",
		success: function(c) {
			alert(c.userMsg)
		},
		error: function(c) {
			alert(c.userMsg)
		}
	})
}
function onlook(a) {
	alert("物流功能还在开发中，请稍后再试")
};