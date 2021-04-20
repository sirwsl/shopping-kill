$.ajaxSetup({
				headers: {
					"Authorization": $.cookie("token")
				},
				xhrFields: {
					withCredentials: true,
				},
				crossDomain: true,
				async: true
			});
			$(function() {
				if ($.cookie("token") == null || $.cookie("token") == "") {
					window.location.href = "index.html"
				}
			});
			function reinitIframe() {
			var iframe = document.getElementById("myinfo");
			try {
				var bHeight = iframe.contentWindow.document.body.scrollHeight;
				var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
				var height = Math.min(bHeight, dHeight);
				iframe.height = height;
				
				} catch (ex) { }
			}
			window.setInterval("reinitIframe()", 40);
			
			$(function(){
				var titleName = decodeURIComponent($.cookie("name"));
				var img = decodeURIComponent($.cookie("img"));
				console.log(img)
				if (titleName!=null && titleName !=''&&titleName != 'undefined'){
					$('#userName').html(titleName);
				}
				if (img!=null && img !='' && img != 'undefined'){
					$("#imgId").attr('src',img);
				}
				
			})
			
			
				
			}