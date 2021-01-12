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
				var id = localStorage.getItem('orderInfoXQId');
				$.get('https://test.wslhome.top/user/getOrderDetail/v1',{'id':id},function(msg){
					if (msg.code == 0){
							$('#orderId').html(msg.data.orderId);
							$('#userName').html(msg.data.userName);
							$('#goodsName').html(msg.data.goodsName);
							$('#imgUrl').attr('src',msg.data.imgUrl);
							$('#price').html(msg.data.price*msg.data.num);
							$('#priceAndNum').html('¥'+msg.data.price+' x'+msg.data.num);
							$('#logisticsPrice').html(msg.data.logisticsPrice);
							$('#totalPrice').html(msg.data.totalPrice);
							$('#userPhone').html(msg.data.userPhone);
							$('#userAddress').html(msg.data.userAddress);
							var temp = msg.data.status;
							if (temp == 0){
								$('status').html("已取消");
							}else if (temp == 1){
								$('status').html("未支付");
							}else if (temp == 2){
								$('status').html("已支付");
							}else if (temp == 3){
								$('status').html("已出库");
							}else if (temp == 4){
								$('status').html("已收货");
							}else if (temp == 5){
								$('status').html("已评价");
							}else {
								$('status').html("未知");	
							}
							
					}
				})
			})