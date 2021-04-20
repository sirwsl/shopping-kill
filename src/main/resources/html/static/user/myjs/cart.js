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
			getAll();
		})
	
	function getAll(){
		$.get('http://localhost/user/getCart/v1',function(msg){
				 var html='';
			if (msg.code == 0 && msg.data.total != 0){
				html +='<div class="goods_show_con">';
				msg.data.forEach(function(items){
					
					
					html += '<div class="picFocus1"><div class="bd" ><div class="tempWrap" onclick="setIds('+items.goodsId+')"><ul>';
					html += '<li><a target="displayArea" href="goodsInfo.html "><img src="'+items.imgUrl+'"></a></li>';
					html += '</ul></div></div><div class="hd"><ul id = '+items.goodsId+'>'+
					'<li class="on" ><img src="'+items.imgUrl+'"></li></ul></div>';
					html +='<div class="p_mount"><div class="p_price"><em>￥</em>'+items.price+'</div></div>';
					html +='<div class="p_name"><a href="goodsInfo.html" onclick="setIds('+items.goodsId+')" target="displayArea" >'+items.goodsName+'</br>'+items.skuName+'</a></div>'
					html += '<div class="in_cart"><span"><a href="goodsInfo.html" onclick="setIds('+items.goodsId+')" target="displayArea">查看</a></span><div class="in_ca"><a onclick = "delcart('+items.id+')">删除</a></div></div></div>';	
				})	
			}else {
				html += '<div class="no_search"><img src="../static/user/imgs/none1.jpg" /><a href="index.html">返回首页</a></div>';
			}
			$("#htmlAll").empty();
			$("#htmlAll").append(html);
		
		})
	}
	
	function setIds(id){
		window.localStorage.setItem('goodsInfoId',id);
		window.localStorage.setItem('killFlag','1');
	}
	
	
	function delcart(id){
		$.ajax({
		    type: 'delete',
		    url: "http://localhost/user/delCartByIds/v1",
		    data: {'ids':id},
		    dataType: "json",
		    success: function (msg) {
				alert(msg.data);
				getAll();
		     }
		})
	
	}