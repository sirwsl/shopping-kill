$(function() {
	$("#Modal").modal({
		keyboard: true
	})
});
$("#subs").click(function() {
	alert("请联系管理员sirwsl")
});
function refresh(a) {
	a.src = "https://test.wslhome.top/verify/getJpg/v1?id=" + Math.random()
}
$("#sub").click(function() {
	$("#login").ajaxSubmit(function(a) {
		if (a.code == 0) {
			window.location.href = "/admin/index.html"
		} else {
			alert(a.userMsg);
			$("img").attr("src", "https://test.wslhome.top/verify/getJpg/v1?id=" + Math.random())
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