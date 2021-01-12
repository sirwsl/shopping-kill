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
		function reinitIframe() {
		var iframe = document.getElementById("displayArea");
		try {
		var bHeight = iframe.contentWindow.document.body.scrollHeight;
		var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
		var height = Math.min(bHeight, dHeight);
		iframe.height = height;
		
		} catch (ex) { }
		
		}
		
		
		$(function(){
			var key = localStorage.getItem('goodsInfoId');
			var flag = localStorage.getItem('killFlag');
			reload(key,flag);
			$.get('https://test.wslhome.top/api/getRecommendedGoods/v1',{'size':'5'},function(msg){
				if (msg.code == 0){
					var html = '';
					msg.data.forEach(function(good){
						html += '<li class="opacity_img1" id = "'+good.id+'"><a target="_blank" onclick = "reload('+good.id+',1)"><div class="p_img">'
								+'<img src="'+good.imgUrl+'" /></div><div class="no_ename" title="'+good.name+'">'+good.name+'</div><div class="no_eprice">'
								+'<em>¥</em>'+good.maxPrice+'</div></a></li>'
					})
					$("#htmls").empty();
					$("#htmls").append(html);
				}
			})
		})
		var goods;
		function reload(id,f){
		
			var buyBut;
			if (f == 100){
				buyBut = '<a class="btn_buynow" onclick = "buy(100)" style="background-color: #FF5722">立即秒杀</a>';
			}else if (f == 0){
				buyBut = '<a class="btn_buynow" style="background-color: #DDDDDD;">即将开始</a>';
			}else if (f == 1){
				buyBut = '<a class="btn_buynow" onclick = "buy(1)">立即购买</a>';
			}
			$("#temp").empty();
			$("#temp").append(buyBut);
			
			$.get('https://test.wslhome.top/api/getDetail/v1',{'id':id,'flag':f},function(msg){
				
				if (msg.code == 0 && msg.data.skuList.length > 0){
					goods  = msg.data.skuList;
					var html = '';
					var lname = '';
					msg.data.skuList.forEach(function(d,i){
						html+='<img src="'+d.imgUrl+'" width="280" height="280" />';
						if (i == 0){
							lname += '<li class="regular_sort" value = "'+d.id+'" onclick="changes('+i+',this)">'+d.name+'</li>';
						}else{
							lname += '<li class="" value = "'+d.id+'" onclick="changes('+i+',this)">'+d.name+'</li>';
						}
						
					})
					var hsee = '<li class="goods_photo">'+html+'</li><li class="goods_photo1" >'+html+'</li>';
					
					$('#listName').empty();
					$('#showbox').empty();
					$('#showbox').append(hsee);
					$('#goodsName').html(msg.data.name);
					$('#eval').html(msg.data.evalNum);
					$('#listName').append(lname);
					$('#price').html(goods[0].price);
					$('#maxPrice').html(goods[0].price*1.2);
					$('#expPrice').html(goods[0].expPrice);
					$('#goodsNum').html(goods[0].total);
					$('#skuIdn').val(goods[0].id);
				
					var showproduct = {
						"boxid": "showbox",
						"sumid": "showsum",
						"boxw": 400, //宽度,该版本中请把宽高填写成一样
						"boxh": 400, //高度,该版本中请把宽高填写成一样
						"sumw": 60, //列表每个宽度,该版本中请把宽高填写成一样
						"sumh": 60, //列表每个高度,该版本中请把宽高填写成一样
						"sumi": 7, //列表间隔
						"sums": 5, //列表显示个数
						"sumsel": "sel",
						"sumborder": 1, //列表边框，没有边框填写0，边框在css中修改
						"lastid": "showlast",
						"nextid": "shownext"
					};
					$.ljsGlasses.pcGlasses(showproduct); //方法调用，务必在加载完后执行
				}
			})
		}
		
		function changes(index,it){
			$('#number').val(1);
			$('#price').html(goods[index].price);
			$('#maxPrice').html(goods[index].price*1.2);
			$('#expPrice').html(goods[index].expPrice);
			$('#goodsNum').html(goods[index].total);
			 $(it).addClass('regular_sort').siblings().removeClass("regular_sort");
			 $('#skuIdn').val(goods[index].id);
		}
		
		function setNum(flag){
			var titleName = decodeURIComponent($.cookie("name"));
			if(titleName==null || titleName ==''|| titleName == 'undefined'){
				alert("您还没有登录，无法进行相关操作，请先登录");
				window.location.href = "login.html";
				 return;
				
			}
			var t = $('#number').val();
			var sum = $('#goodsNum').html();
			
			if (flag){
				$('#number').val(++t);
				$('#goodsNum').html(sum-1);
			}else{
				if (t > 1){
					$('#number').val(t-1);
					$('#goodsNum').html(++sum);
				}
				
				
			}
		}
		
		function addcart(){
			var titleName = decodeURIComponent($.cookie("name"));
			if(titleName==null || titleName =='' || titleName == 'undefined'){
				alert("您还没有登录，无法进行相关操作，请先登录");
				window.location.href = "login.html";
				return;
				
			}
			var msg = "请确认是否添加进您的收藏夹"; 
			 if (confirm(msg)==true){ 
			 var skuId=$("#skuIdn").val();
			 $.post("https://test.wslhome.top/user/addCart/v1", {"skuId":skuId,"num":$('#number').val()},function(msg) {
			   alert(msg.data);
			 });
			 }else{ 
			  return false; 
			 } 
		}
		
		function buy(flag){
			var titleName = decodeURIComponent($.cookie("name"));
			if(titleName==null || titleName =='' || titleName == 'undefined'){
				alert("您还没有登录，无法进行相关操作，请先登录");
				window.location.href = "login.html";
				return;
				
			}
			var skuId  = $('.regular_sort').val();
			var num = $('#number').val();
			if (flag == 100){
				$.get('https://test.wslhome.top/api/'+skuId+'/getUrl',function(msg){
					if (msg.code == 0 && msg.data.seconds > 0){
						$("#mask1").css("display","block");
						$("#mask2").css("display","block");
						$.post('https://test.wslhome.top/user/killGoods/'+skuId+'/'+num+'/'+msg.data.md5+'',function(m){
							if (m.code == 0){
								$("#mask2").html("恭喜您，抢购成功，订单有效期五分钟，超时将会被取消订单哦\n正在为您跳转")
								$("#mask1").css("display","none");
								$("#mask2").css("display","none");
								window.localStorage.setItem('userOrderId',m.data);
								parent.document.getElementById("displayArea").src="order.html";
							}else{
								$("#mask1").css("display","none");
								$("#mask2").css("display","none");
								alert(m.userMsg);
							}
						})
					}else{
						alert("该活动已结束或不存在，去看看其他商品吧");
					}
				})
			}else if (flag == 1){
				$("#mask1").css("display","block");
				$("#mask2").css("display","block");
				$.post('https://test.wslhome.top/user/commonGoods/v1',{'skuId':skuId,'num':num},function(m){
					if (m.code == 0){
						
						$("#mask2").html("恭喜您，下单成功，订单有效期五分钟，超时将会被取消订单哦\n正在为您跳转");
						
						$("#mask1").css("display","none");
						$("#mask2").css("display","none");
						window.localStorage.setItem('userOrderId',m.data);
						parent.document.getElementById("displayArea").src="order.html";
						
					}else{
						$("#mask1").css("display","none");
						$("#mask2").css("display","none");
						alert(m.userMsg);
					}
				})
			}
		}
