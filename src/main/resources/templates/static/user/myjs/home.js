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
		
		$(function(){
			$.get('https://test.wslhome.top/api/getRecommendedGoods/v1',{'size':'10'},function(msg){
				if (msg.code == 0){
					var html = '';
					msg.data.forEach(function(good){
						html += '<li class="opacity_img1" id = "'+good.id+'"><a  href="goodsInfo.html" target="displayArea" onclick = "reload('+good.id+')"><div class="p_img">'
								+'<img src="'+good.imgUrl+'" /></div><div class="no_ename" title="'+good.name+'">'+good.name+'</div><div class="no_eprice">'
								+'<em>¥</em>'+good.maxPrice+'</div></a></li>'
					})
					$("#htmlss").empty();
					$("#htmlss").append(html);
				}
			})
			$.get('https://test.wslhome.top/api/getAdvertiseForView/v1',function(msg){
				if (msg.code == 0 && msg.data.length >0){
					var html = '';
					msg.data.forEach(function(v){
						html += '<div class="slideItem" >'
					+'<a href="'+v.targetUrl+'" target="_self" ><img class="banner-img" src="'+v.imgUrl+'" style="height: 500px;"></a>'
					+'</div>';
					})
					
					$('#adver').html();
					$('#adver').html(html);
				}
				$("#lun2").slide({
					autoplay: true,
					autoTime: 2000,
				});
			})
			
		})
		function reload(id){
			window.localStorage.setItem('goodsInfoId',id);
			window.localStorage.setItem('killFlag','1');
		}
		
		

		$(".closehd").click(function() { //右下角红包图标点击变小
			$(this).hide();
			$('.bk_foot_redbag a').animate({
				width: '80px',
				height: '100px'
			});
		});
		$('#red_bag').click(function(){
			$(this).hide();
			$('.closehd').hide();
			$('.font').show();
			$('.font').css('display','inline-block')
			$('.red_bag').animate({
				width:'400px',
				height:'300px'
			});
		});
		$('#ensure').click(function(){
			$('.red_bag').fadeOut();
		});
		
		$('.search').kuCity();
		
		
