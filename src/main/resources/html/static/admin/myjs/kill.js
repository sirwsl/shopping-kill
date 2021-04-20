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
$(".modal-backdrop").remove();
var arrTemp = new Array();
var flag = 2;
var bo = true;
var killId;
var killName;
var goodId;
var goodName;
var startTime;
var endTime;
function col(a) {
	flag = a;
	bo = true;
	goodName = "";
	goodId = "";
	killName = "";
	killId = "";
	$("#killAvtivity").bootstrapTable("refresh")
}
$(function killActivity() {
	$("#killAvtivity").bootstrapTable("destroy");
	$("#killAvtivity").bootstrapTable({
		method: "get",
		url: "http://localhost/admin/getActivityAll/v1",
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
		sidePagination: "client",
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
			field: "goodsName",
			sortable: true
		},
		{
			title: "开始时间",
			field: "startTime",
		},
		{
			title: "结束时间",
			field: "endTime",
			sortable: true
		},
		{
			title: '<table><thead><tr><th style="width:110px">skuId</th><th style="width:450px">商品属性</th><th style="width:130px">价格</th><th style="width:130px">已售</th><th>总数</th></tr></thead></table>',
			field: "skuList",
			formatter: function(f, g, d) {
				var e = "<table class='table'>";
				if (g.skuList != null) {
					$.each(g.skuList,
					function(h, j) {
						e += "<tr><td style='width:100px'>" + j.id + "</td><td style='width:450px'>" + j.name + "</td><td style='width:130px'>" + j.price + "</td><td style='width:130px'>" + j.sellNum + "</td><td >" + j.totalNum + "</td></tr>"
					})
				}
				e += "</table>";
				return e
			}
		},
		{
			title: "操作",
			field: "Button",
			align: "center",
			formatter: a
		}]
	});
	function b(e) {
		var d = {
			status: flag,
			id: killId,
			name: killName,
			startTime: startTime,
			endTime: endTime
		};
		return d
	}
	function c(d) {
		return d.data
	}
	function a(g, h, f) {
		var d = new Array();
		$.each(h.skuList,
		function(j, k) {
			d[j] = k.aid
		});
		arrTemp[f] = d;
		if (flag == 1) {
			var e = '<a data-toggle="modal" class="btn btn-primary" href="#modal-form" onclick="setValue(' + JSON.stringify(h).replace(/"/g, "&quot;") + ') ">编辑</a></br>' + '<a data-toggle="modal" class="btn btn-danger" onclick = "delActivity(' + f + ')">删除</a>';
			return e
		} else {
			if (flag == 2) {
				return '<i class="fa fa-check text-navy">进行中</i>'
			} else {
				if (flag == 3) {
					return '<i class="fa fa-check text-navy">已结束</i>'
				}
			}
		}
	}
});
function delActivity(a) {
	var b = confirm("确认删除嘛？");
	if (b == true) {
		$.ajax({
			url: "http://localhost/admin/delActivity/v1",
			data: {
				id: arrTemp[a]
			},
			type: "delete",
			success: function(c) {
				if (c.code == 0) {
					alert("删除成功");
					$("#killAvtivity").bootstrapTable("refresh")
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
$("#goods").click(function() {
	bo = false;
	$("#goodsAvtivity").bootstrapTable("destroy");
	$("#goodsAvtivity").bootstrapTable({
		method: "get",
		url: "http://localhost/admin/getActivityByGoods/v1",
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
			field: "goodsName",
			sortable: true
		},
		{
			title: '<table><thead><tr><th style="width:110px">skuId</th><th style="width:450px">商品属性</th><th style="width:130px">进价</th><th style="width:130px">售价</th><th style="width:130px">成本价</th><th>总数</th></tr></thead></table>',
			field: "skuList",
			formatter: function(f, g, d) {
				var e = "<table class='table'>";
				$.each(g.skuList,
				function(h, j) {
					e += "<tr><td style='width:100px'>" + j.id + "</td><td style='width:450px'>" + j.name + "</td><td style='width:130px'>" + j.realPrice + "</td><td style='width:130px'>" + j.price + "</td><td style='width:130px'>" + j.costPrice + "</td><td>" + j.totalNum + "</td></tr>"
				});
				e += "</table>";
				return e
			}
		},
		{
			title: "操作",
			field: "Button",
			align: "center",
			formatter: a
		}]
	});
	function b(e) {
		var d = {
			current: (e.offset / e.limit) + 1,
			size: e.limit,
			id: goodId,
			name: goodName
		};
		return d
	}
	function c(d) {
		return {
			"rows": d.data.records,
			"total": d.data.total
		}
	}
	function a(e, f, d) {
		return '<a data-toggle="modal" class="btn btn-success" href="#modal-form" onclick="setValues(' + JSON.stringify(f).replace(/"/g, "&quot;") + ')">开启</a>'
	}
});
function setValue(b, a) {
	$("#modal-form").on("show.bs.modal",
	function(e) {
		var f = $(e.relatedTarget).data("id");
		var d = $(this);
		d.find("#id").val(b.id);
		d.find("#goodsName").val(b.goodsName);
		d.find("#startTime").val(b.startTime);
		d.find("#endTime").val(b.endTime);
		var c = "";
		$.each(b.skuList,
		function(g, j) {
			var h = 0;
			if (j.aid != null && j.aid > 0) {
				h = j.aid
			}
			c += '<div class="form-group"><input name ="skuList[' + g + '].aId"  value="' + h + '" style="display:none"/>' + '<label><input name = "skuList[' + g + '].id" type="hidden" class="i-checks col-xl-4" checked="true" value="' + j.id + '">' + j.name + "</label></br>" + '<label>数量</label><input name = "skuList[' + g + '].totalNum" type="number" placeholder="数量" class="form-control" style="width:60%" value = "' + (parseInt(j.totalNum / 2)) + '" max = "' + j.totalNum + '"/>' + '<label>价格</label><input name = "skuList[' + g + '].price" class="form-control " style="width:60%" value ="' + j.price + '" min = "' + (j.price - 20) + '"/></div>'
		});
		$("#details").empty();
		$("#details").append(c)
	})
}
function setValues(b, a) {
	b.id = null;
	setValue(b, a);
}
$("#sub").click(function() {
	$("#activity").ajaxSubmit(function(a) {
		if (a.code == 0) {
			if (a.data==true){
				alert("编辑成功");
				$("#killAvtivity").bootstrapTable("refresh");
				window.location.reload()
			}else{
				alert("存在相同时段，数据库更新失败");
			}

		} else {
			alert(a.userMsg)
		}
	})
});
$.ajaxSetup({
	headers: {
		"Authorization": $.cookie("token")
	},
	xhrFields: {
		withCredentials: true
	},
	crossDomain: true
});
function serches() {
	if (bo) {
		killName = $("#searchName").val();
		killId = $("#searchId").val();
		$("#killAvtivity").bootstrapTable("refresh")
	} else {
		goodName = $("#searchName").val();
		goodId = $("#searchId").val();
		$("#goodsAvtivity").bootstrapTable("refresh")
	}
};