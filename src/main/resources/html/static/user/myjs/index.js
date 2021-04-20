if (window.parent !== window.self) {
			        window.parent.location.reload();
			    }
			
			function check2(){
				var titleName = decodeURIComponent($.cookie("name"));
				if(titleName==null || titleName ==''|| titleName == 'undefined'){
					alert("您还没有登录，无法进行相关操作，请先登录");
					parent.document.getElementById("displayArea").src="login.html";
				}else{
					  parent.document.getElementById("displayArea").src="my.html";
				}
			}
			$.ajaxSetup({
				headers: {
					"Authorization": $.cookie("token")
				},
				xhrFields: {
					withCredentials: false
				},
				crossDomain: true
			});
			$(function(){
				var titleName = decodeURIComponent($.cookie("name"));
				if (titleName!=null && titleName !=''&&titleName != 'undefined'){
					$("#ECS_MEMBERZONE").empty();
					$("#ECS_MEMBERZONE").append('<a href="my.html" target="displayArea">'+titleName+'</a>');
					$('#myleft').empty();
					$('#myleft').append('<li><div class="dt"><a href="my.html" target="displayArea">个人中心</a></div></li>'
						+'<li class="spacer"></li><li><div class="dt"><a onclick = "callPhone()" >客户服务</a></div></li>'
						+'<li class="spacer"></li><li><div class="dt"><a  onclick = "exits()">退出登录</a></div></li>');
						
				}else{
					$("#ECS_MEMBERZONE").empty();
					$("#ECS_MEMBERZONE").append('<a href="login.html" class="link-login red" target="displayArea">请登录</a>'
					+'<a href="register.html" class="link-regist"  target="displayArea">免费注册</a>');
				$('#myleft').empty();
				$('#myleft').append('<li><div class="dt"><a onclick = "callPhone()">客户服务</a></div></li>')
				}
			})
			function reinitIframe() {
			var iframe = document.getElementById("displayArea");
			try {
				var bHeight = iframe.contentWindow.document.body.scrollHeight;
				var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
				var height = Math.min(bHeight, dHeight);
				iframe.height = height;
				
				} catch (ex) { }
			}
			window.setInterval("reinitIframe()", 100);
			
			$('.li_dorpdown').hover(function() {
				$('.dorpdown-layer').show();
			});
			$('.li_dorpdown').mouseleave(function() {
				$('.dorpdown-layer').hide();
			});
			$('.icon-close').click(function() {
				$('.top_banner').hide();
			});
			$('.my_center_box_left a').hover(function() {
			
			});
			$('.cate-layer-right-slide img').mouseenter(function() {
			
				$(this).delay('300').animate(300);
			});
			$('.cate-layer-right-slide img').mouseleave(function() {
				$(this).css('border', 'none')
			});
			
			$("#callPhone").click(function(){
				alert("如有问题与反馈建议，请联系QQ：2572396933");
			})
			
			$("#search").click(function(){
				$("#home1").removeClass("curr");
				//设置localStorage
				window.localStorage.setItem('key',$("#keyword").val());
			})
			
		$("#ty li a").click(function(){
			$(".curr").removeClass("curr");
		})
			$('#ty').find('li a').each(function() {
				$(this).click(function() {
					$(this).addClass("curr");
			    });
				
			});
		function exits(){
			var msg = "请确确定要退出系统吗？";
			 if (confirm(msg)==true){ 
				$.get('http://localhost/login/exit/v1',function(msg){
					if (msg.code ==0 && msg.data==true){
						alert("退出系统成功");
						window.location.href = "index.html";
					}
				})	 
			 }else{
				 return false;
			 }
			
		}
		
		function callPhone(){
			alert("如有需要，请联系QQ：2572396933，注明原因。或者发送邮件至shoppingkill@163.com或sirwsl@163.com,谢谢您的配合");
		}