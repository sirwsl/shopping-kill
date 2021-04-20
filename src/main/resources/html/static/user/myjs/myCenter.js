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
		$(function(){
			var titleName = decodeURIComponent($.cookie("name"));
			var img = decodeURIComponent($.cookie("img"));
			if (titleName!=null && titleName !=''&&titleName != 'undefined'){
				$('#userName').html(titleName);
			}
			if (img!=null && img !='' && img != 'undefined'){
				$("#imgId").attr('src',img);
			}
			
			$.get('http://localhost/user/getOrderCount/v1',function(msg){
				if (msg.code == 0){
					$("#noGet").html(msg.data.noGet);
					$("#noComm").html(msg.data.noComm);
					$("#noPay").html(msg.data.noPay);
					$("#cart").html(msg.data.cart);
				}
			})
			
		})
		function setId(id){
			window.localStorage.setItem('myOrderStatus',id);
		}