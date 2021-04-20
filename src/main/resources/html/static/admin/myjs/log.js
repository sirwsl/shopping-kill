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
$(function() {
	logger(1)
});
function logClick(a) {
	logger(a);
	$("#adminLog").bootstrapTable("refresh")
}
function logger(b) {
	$("#adminLog").bootstrapTable("destroy");
	$("#adminLog").bootstrapTable({
		method: "get",
		url: "http://localhost/admin/getAllLoggerByType/v1",
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
		queryParams: a,
		responseHandler: c,
		columns: [{
			title: "Id",
			field: "id",
			sortable: true
		},
		{
			title: "操作细节",
			field: "detail",
			sortable: true
		},
		{
			title: "操作人姓名",
			field: "name",
			sortable: true
		},
		{
			title: "操作人Id",
			field: "manId",
			sortable: true
		},
		{
			title: "IP地址",
			field: "ip",
			sortable: true
		},
		{
			title: "操作时间",
			field: "creatTime",
			sortable: true
		},
		{
			title: "操作等级",
			field: "grade",
			formatter: function(f, g, d) {
				var e = "";
				if (g.grade == 1) {
					e = '<t style="color:green">正常</t>'
				} else {
					if (g.grade == 2) {
						e = '<t style="color:blue">一般</t>'
					} else {
						if (g.grade == 3) {
							e = '<t style="color:pink">严重</t>'
						} else {
							if (g.grade == 4) {
								e = '<t style="color:red">非常严重</t>'
							}
						}
					}
				}
				return e
			}
		}]
	});
	function a(e) {
		var d = {
			page: (e.offset / e.limit) + 1,
			num: e.limit,
			type: b
		};
		return d
	}
	function c(d) {
		return {
			"rows": d.data.records,
			"total": d.data.total
		}
	}
};