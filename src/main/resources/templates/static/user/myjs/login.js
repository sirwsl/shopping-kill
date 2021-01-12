$.ajaxSetup({
			headers: {
				"Authorization": $.cookie("token")
			},
			xhrFields: {
				withCredentials: true
			},
			crossDomain: true
		});
		
		function refresh(a) {
			a.src = "https://test.wslhome.top/verify/getJpg/v1?id=" + Math.random()
		}
		$("#toIndex").click(function() {
			$("#login").ajaxSubmit(function(a) {
				if (a.code == 0) {
					parent.location.href = "index.html"
				} else {
					alert(a.userMsg);
					$("#codes").attr("src", "https://test.wslhome.top/verify/getJpg/v1?id=" + Math.random())
				}
			})
		});