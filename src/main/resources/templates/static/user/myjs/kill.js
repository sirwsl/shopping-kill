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
			$(function(){
				$.get('https://test.wslhome.top/api/getActivityDoing/v1',function(msg){
					var html = '';
					if (msg.code == 0 && msg.data.length > 0){
						msg.data.forEach(function(v){
							html += 
							'<li class="mod_shodow"><div class="p_img"><a href="goodsInfo.html" onclick="setId('+v.id+')" target="displayArea">'
							+'<img src="'+v.imgUrl+'" />'			
							+'</a></div>'
							+'<div class="p_name"><a href="goodsInfo.html" onclick="setId('+v.id+')"  target="displayArea">'+v.name+'</a></div>'
							+'<div class="p_lie clear"><div class="p_price"><em>￥</em>'+v.minPrice+'</div>'
							+'<div class="p_del"><em>结束时间：</em>'+v.endTime+'</div>'
							+'</div><a class="sc_kill_btn btn" href="goodsInfo.html" onclick="setId('+v.id+')" target="displayArea">立即抢购</a></li>';
						})
					}else{
						html += '<div class="no_search"><img src="../static/user/imgs/none.jpg" />还没有活动哦，其他处逛逛吧<a href="index.html">返回首页</a></div>';
					}
					$('#getDoing').empty()
					$('#getDoing').append(html);
					
				})
			})
			
			function setId(id){
				window.localStorage.setItem('goodsInfoId',id);
				window.localStorage.setItem('killFlag','100');
			}