$.ajaxSetup({
	headers: {
		"Authorization": $.cookie("token")
	},
	xhrFields: {
		withCredentials: true
	},
	crossDomain: true
});
$("#money").click(function() {
	$("#Modal").modal({
		keyboard: true
	})
});
$(function() {
	if ($.cookie("token") == null || $.cookie("token") == "") {
		window.location.href = "/admin/login.html"
	}
});
$("#exits").click(function() {
	$.get("https://test.wslhome.top/login/exit/v1", null,
	function(a) {
		if (a.code == 0 && a.data) {
			window.location.href = "/admin/login.html"
		}
	},
	"json")
});