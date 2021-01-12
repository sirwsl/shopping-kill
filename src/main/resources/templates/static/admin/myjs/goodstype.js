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
		window.location.href = "/admin/login.html"
	}
});
$(function() {
	goodsType("https://test.wslhome.top/admin/getTypesAll/v1", null, null)
});
function serches() {
	var b = "https://test.wslhome.top/admin/getTypesByNameAndId/v1";
	var c = $("#searchId").val();
	var a = $("#searchName").val();
	goodsType(b, c, a)
}
function goodsType(b, e, a) {
	$("#goodstype").bootstrapTable("destroy");
	$("#goodstype").bootstrapTable({
		method: "get",
		url: b,
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
		queryParams: c,
		clickToSelect: true,
		responseHandler: d,
		columns: [{
			title: "Id",
			field: "id",
			edit: true,
			sortable: true
		},
		{
			title: "名称",
			field: "name",
			editable: {
				type: "text",
				title: "名称",
				validate: function(f) {
					if (!f) {
						return "类别名称不能为空"
					}
				}
			}
		},
		{
			title: "更新时间",
			field: "updateTime",
			sortable: true
		},
		{
			title: "创建时间",
			field: "creatTime",
			sortable: true
		},
		{
			title: "操作",
			field: "grade",
			formatter: function(g, h, f) {
				return '<button type="button" class="btn  btn-danger" style="margin:5px" onclick=" deltype(' + h.id + ')">删除</button>'
			}
		}],
		onEditableSave: function(h, i, f, g) {
			$.ajax({
				type: "put",
				url: "https://test.wslhome.top/admin/updateTypes/v1",
				data: i,
				dataType: "JSON",
				success: function(j) {
					if (j.code == 0 && j.data == true) {
						alert("修改成功")
					} else {
						alert("修改失败")
					}
				},
				error: function() {
					alert("编辑失败")
				},
				complete: function() {
					$("#goodstype").bootstrapTable("refresh")
				}
			})
		}
	});
	function c(g) {
		var f = {
			current: (g.offset / g.limit) + 1,
			size: g.limit,
			id: e,
			name: a
		};
		return f
	}
	function d(f) {
		return {
			"rows": f.data.records,
			"total": f.data.total
		}
	}
}
function deltype(b) {
	var a = confirm("确认删除吗？");
	if (a == true) {
		$.ajax({
			type: "DELETE",
			url: "https://test.wslhome.top/admin/delTypes/v1",
			data: {
				id: b
			},
			dataType: "JSON",
			success: function(c) {
				if (c.code == 0 && c.data == true) {
					alert("删除成功")
				} else {
					alert("删除失败")
				}
			},
			error: function() {
				alert("编辑失败")
			},
			complete: function() {
				$("#goodstype").bootstrapTable("refresh")
			}
		})
	}
}
$("#sub").click(function() {
	$("#addTypes").ajaxSubmit(function(a) {
		alert(a.userMsg);
		$("#goodstype").bootstrapTable("refresh")
	})
});