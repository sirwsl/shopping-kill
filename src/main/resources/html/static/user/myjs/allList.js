
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
			
		$(".closehd").click(function() { //右下角红包图标点击变小
			$(this).hide();
			$('.bk_foot_redbag a').animate({
				width: '80px',
				height: '100px'
			});
		});
		$('#red_bag').click(function() {
			$(this).hide();
			$('.closehd').hide();
			$('.font').show();
			$('.font').css('display', 'inline-block')
			$('.red_bag').animate({
				width: '400px',
				height: '300px'
			});
		});
		$('#ensure').click(function() {
			$('.red_bag').fadeOut();
		});
		$('#red_bag').click(function() {
			$(this).hide();
			$('.closehd').hide();
			$('.font').show();
			$('.font').css('display', 'inline-block')
			$('.red_bag1').animate({
				width: '400px',
				height: '300px'
			});
		});
		$('#ensure').click(function() {
			$('.red_bag1').fadeOut();
		});
		
		//添加成功
		$('.in_ca').click(function(){
			$('.succeed').show();
			$(function () {
    		setTimeout(function () {
       			$(".succeed").fadeOut();
    		}, 1000);
			})
		});

		$(function(){
			$.get('http://localhost/api/getRecommendedGoods/v1',{'size':'4'},function(msg){
				if (msg.code == 0){
					var html = '';
					msg.data.forEach(function(good,i){
						if (i == 3){
							html+='<li class="last">';
						}else{
							html+='<li class="">';
						}
						html += '<div class="item"><div class="p_img">'
							+'<a onclick = "setIds('+good.id+')" href="goodsInfo.html" target="displayArea">'
							+'<img src="'+good.imgUrl+'" />'
							+'</a></div><div class="p_name">'
							+'<a href="goodsInfo.html" onclick = "setIds('+good.id+')" title="'+good.name+'"  target="displayArea">'	
							+good.name+'</a></div><div class="p_price"><em>￥</em>'+good.maxPrice+'</div>'
							+'<div class="p_btn"><a href="goodsInfo.html" onclick = "setIds('+good.id+')" target="displayArea">立即购买</a></div></div></li>';
					})
					$("#htmls").empty();
					$("#htmls").append(html);
				}
				
			})
			
			$.get("http://localhost/api/getAllGoods/v1", {'current':'1'},
			  function(msg){
				 var html='';
				 if (msg.code == 0 && msg.data.total != 0){
					html +='<div class="goods_show_con">';
					msg.data.records.forEach(function(items){
						
						var temp = '';
						html += '<div class="picFocus1"><div class="bd" ><div class="tempWrap" onclick="setIds('+items.id+')"><ul>';
						if (items.skuImgUrl.length > 0){
							var t = items.skuId;
							items.skuImgUrl.forEach(function(item,indexs){
								html += '<li><a target="displayArea" href="goodsInfo.html "><img src="'+item+'"></a></li>';
								if (indexs < 14){
									if (indexs == 0){
										temp += '<li class="on" value = '+t[indexs]+'><img src="'+item+'"></li>';
									}else{
										temp += '<li class="" value = '+t[indexs]+'><img src="'+item+'"></li>';
									}
								}
							})
						}
						html += '</ul></div></div><div class="hd"><ul id = '+items.id+'>'+temp+'</ul></div>';
						html +='<div class="p_mount"><div class="p_price"><em>￥</em>'+items.minPrice+'</div><div class="p_num">剩余数量<em>'+items.number+'</em>件</div></div>';
						html +='<div class="p_name"><a href="goodsInfo.html" onclick="setIds('+items.id+')" target="displayArea" >'+items.name+'</a></div>'
						html += '<div class="in_cart"><span title="网站自营，品质保障">自营</span><span title="该商品参与满减活动">满减</span><div class="in_ca"><i><img src="../static/user/imgs/cart2.png"/></i><a onclick = "addcart('+items.id+')">加入收藏夹</a></div></div></div>';	
					})	
				}else {
					html += '<div class="no_search"><img src="../static/user/imgs/none1.jpg" /><a href="index.html">返回首页</a></div>';
				}
				$("#htmlAll").empty();
				$("#htmlAll").append(html);
				jQuery(".picFocus1").slide({ mainCell: ".bd ul", effect: "left", });
			  });
		})
		
		function setIds(id){
			window.localStorage.setItem('goodsInfoId',id);
			window.localStorage.setItem('killFlag','1');
		}
		
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