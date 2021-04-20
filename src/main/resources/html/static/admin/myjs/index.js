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
		window.location.href = "/html/admin/login.html"
	}
});
$("#exits").click(function() {
	$.get("http://localhost/login/exit/v1", null,
	function(a) {
		if (a.code == 0 && a.data) {
			window.location.href = "/html/admin/login.html"
		}
	},
	"json")
});