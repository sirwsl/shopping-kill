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
			$.get('http://localhost/api/getRecommendedGoods/v1',{'size':'8'},function(msg){
				if (msg.code == 0){
					var html = '';
					msg.data.forEach(function(good,i){
						if (i < 7){
							html += '<a href="goodsInfo.html" onclick = "setIds('+good.id+')" >';
						}else{
							html += '<a href="goodsInfo.html" onclick = "setIds('+good.id+')" class="last">';
						}
						html += '<dl><dt><img src="'+good.imgUrl+'"></dt>'
						+'<dd>'+good.name+'</dd>'	
						+'<dd>￥'+good.maxPrice+'</dd></dl></a>'	;
					})

					$("#htmlss").empty();
					$("#htmlss").append(html);
				}
				
			})
		})
		function setIds(id){
			window.localStorage.setItem('goodsInfoId',id);
			window.localStorage.setItem('killFlag','1');
		}