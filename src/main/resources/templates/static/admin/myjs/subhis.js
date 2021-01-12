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
var url = "https://test.wslhome.top/admin/getAllSubscription/v1";
var stime;
var etime;
$(function() {
	logger()
});
$("#search").click(function() {
	url = "https://test.wslhome.top/admin/getSubscriptionByTime/v1";
	stime = $("#startTime").val();
	etime = $("#endTime").val();
	logger()
});
function logger() {
	$("#history").bootstrapTable("destroy");
	$("#history").bootstrapTable({
		method: "get",
		url: url,
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
		pageList: [5, 10, 15, 20, 30, 50],
		showRefresh: false,
		silent: true,
		cache: false,
		queryParams: a,
		responseHandler: b,
		columns: [{
			formatter: function(f, g, c) {
				var e = "短信订阅";
				if (g.type == 0) {
					e = "邮件订阅"
				}
				var d = '<div class="row"><div class="col-sm-12"><div class="ibox float-e-margins">' + '<div class="ibox-title"><h5>' + e + "&nbsp" + "id:" + g.id + "</h5>" + '</div><div class="ibox-content"><h2>' + g.title + "</h2>" + "<p>" + g.detail + "</p>" + "<hr/><h4>创建时间：" + g.creatTime + "</h4>" + "<h4>发布者Id：" + g.adminId + "</h4></div></div></div></div>";
				return d
			}
		}]
	});
	function a(d) {
		var c = {
			page: (d.offset / d.limit) + 1,
			num: d.limit,
			beginTime: stime,
			endTime: etime
		};
		return c
	}
	function b(c) {
		return {
			"rows": c.data.records,
			"total": c.data.total
		}
	}
};