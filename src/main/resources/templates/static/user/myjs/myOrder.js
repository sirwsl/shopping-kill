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
				getAll(null,null,100);
			})
			
			function getAll(current,name,status){
				$.get('https://test.wslhome.top/user/getUserOrderInfo/v1',{'current':current,'name':name,'status':status},function(msg){
					var html = '';
					if (msg.code == 0 && msg.data.total > 0){
					msg.data.records.forEach(function(v){
							var temp = '';
							var tempHtml='';
							var t = v.status;
							if (t == 0){
								temp = "已取消";
								tempHtml = '<a href="index.html" onclick="setGoodsId('+v.goodsId+')" target= "myinfo">重新购买</a>';
							}else if (t == 1){
								temp = "未支付";
								tempHtml = '<a href="order.html" onclick="setOrderId('+v.orderId+')" target= "myinfo">立即支付</a>';
							}else if (t == 2){
								temp = "已支付";
								tempHtml = '<a href="#" onclick="redo()" target= "myinfo">提醒发货</a>';
							}else if (t == 3){
								temp = "已出库";
								tempHtml = '<a href="wuliu.html" target= "myinfo">查看物流</a>';
							}else if (t == 4){
								temp = "已收货";
								tempHtml = '<a href="myProd.html" target= "myinfo">待评价</a>';
							}else if (t == 5){
								temp = "已评价";
								tempHtml = '<a href="myProd.html" target= "myinfo">查看评价</a>';
							}else {
								temp = "未知";	
							}
							if (v.status == 3){
								html+= '<div class="dkuang clearfix deng"><p class="one fl">待收货</p><div  class="clearfix">'
								+'<div class="fl vewwl"><a href="wuliu.html" class="ckwl">查看物流</a>'
								+'<div class="wuliu"><h4>圆通速递：858888888888888</h4><ul>'
								+'<li><p>妥投投递并签收，已签收。签收人：本人签收</p><p>2016-02-03 17:30:00</p></li>'
								+'<li><p>深圳市南油速递营销部安排投递（投递员姓名：六六六。联系电话：14243452522;</p><p>2016-02-03 08:50:00</p></li>'
								+'<li><p>到达广东省邮政速递物流有限公司深圳航空邮件处理中心处理中心（经转）</p><p>2021-02-03 02:20:00</p></li>'
								+'<li>以上为最新跟踪信息<a href="wuliu.html">查看全部</a></li></ul></div></div></div>';;
							}else if(v.status == 1 || v.status == 2){
								html+= '<div class="dkuang clearfix deng"><p class="one">'+temp+'</p>';
							}else{
								html+= '<div class="dkuang"><p class="one">'+temp+'</p>';
							}
							html+= 
								'<div class="word clearfix"><ul class="fl clearfix">'
								+'<li>'+v.creatTime+'</li><li>'+v.userName+'</li><li>订单号:'+v.orderId+'</li><li>在线支付</li></ul>'
								+'<p class="fr">订单金额：<span>'+v.totalPrice+'</span>元</p></div>'
								+'<div class="shohou clearfix"><a href="#" class="fl"><img src="'+v.imgUrl+'"/></a>'
								+'<p class="fl"><a href="#">'+v.goodsName+'</a><a href="#">¥'+v.price+'×'+v.num+'</a></p>'
								+'<p class="fr">'+tempHtml+' <a href="orderxq.html" onclick="setOrderDetailId('+v.orderId+')" target= "myinfo">订单详情</a></p></div></div>';
							
						})	
					
					}
					$("#htmls").empty();
					$("#htmls").append(html);
				})
				
			}
			function setGoodsId(id){
				window.localStorage.setItem('goodsInfoId',id);
				window.localStorage.setItem('killFlag','1');
			}
			
			function setOrderId(id){
				window.localStorage.setItem('userOrderId',id);
			}
			function redo(){
				alert("商家已收到您的请求，请耐心等待处理，感谢您的体谅");
			}
			
			function setOrderDetailId(id){
				window.localStorage.setItem('orderInfoXQId',id);
			}
			function exchange(index){
				getAll(null,null,index);
			}
			function search(){
				getAll(null,$('#ordernamees').val(),null);
			}