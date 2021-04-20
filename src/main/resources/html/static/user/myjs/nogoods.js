
		$.ajaxSetup({
			headers: {
				"Authorization": $.cookie("token")
			},
			xhrFields: {
				withCredentials: true
			},
			crossDomain: true
		});
		
		$(function(){
			
			var key = localStorage.getItem('key');
			window.localStorage.removeItem("key");
			$.get("http://localhost/api/getAllGoods/v1", { name: key, size: "100" },
			  function(msg){
				 var html='';
				 if (msg.code == 0 && msg.data.total != 0){
					html +='<div class="goods_show_con">';
					msg.data.records.forEach(function(items){
						
						var temp = '';
						html += '<div class="picFocus1"><div class="bd" ><div class="tempWrap" onclick="openss('+items.id+')"><ul>';
						if (items.skuImgUrl.length > 0){
							var t = items.skuId;
							items.skuImgUrl.forEach(function(item,indexs){
								html += '<li><a target="displayArea" href="goodsInfo.html "><img src="'+item+'"></a></li>';
								if (indexs == 0){
									temp += '<li class="on" value = '+t[indexs]+'><img src="'+item+'"></li>';
								}else{
									temp += '<li class="" value = '+t[indexs]+'><img src="'+item+'"></li>';
								}
							})
						}
						html += '</ul></div></div><div class="hd"><ul id = '+items.id+'>'+temp+'</ul></div>';
						html +='<div class="p_mount"><div class="p_price"><em>￥</em>'+items.minPrice+'</div><div class="p_num">剩余数量<em>'+items.number+'</em>件</div></div>';
						html +='<div class="p_name"><a href="goodsInfo.html" onclick="openss('+items.id+')" target="displayArea" >'+items.name+'</a></div>'
						html += '<div class="in_cart"><span title="网站自营，品质保障">自营</span><span title="该商品参与满减活动">满减</span><div class="in_ca"><i><img src="../static/user/imgs/cart2.png"/></i><a onclick = "addcart('+items.id+')">加入收藏夹</a></div></div></div>';	
					})	
				}else {
					html += '<div class="no_search"><img src="../static/user/imgs/none1.jpg" /><a href="index.html">返回首页</a></div>';
				}
				$("#htmls").empty();
				$("#htmls").append(html);
				jQuery(".picFocus1").slide({ mainCell: ".bd ul", effect: "left", });
			  });
		})
		
		function addcart(id){
			var msg = "请确认是否添加进您的收藏夹"; 
			 if (confirm(msg)==true){ 
			 var skuId=$("#"+id+" li[class='on']").attr("value");
			 $.post("http://localhost/user/addCart/v1", {"skuId":skuId,"num":"1"},function(msg) {
			   alert(msg.data);
			 });
			 }else{ 
			  return false; 
			 } 
		}

		function openss(id){
			window.localStorage.setItem('goodsInfoId',id);
			window.localStorage.setItem('killFlag','1');
		}	
		
		