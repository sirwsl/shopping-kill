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
			var tfalg;
			var ttt = 5;
			$(function(){
				tfalg = true;
				pinjia(true);
			})

			function getPin(flag){
				tfalg = flag;
				pinjia(flag);
			}
			
			function pinjia(flag){
				var html ='';
				$.get('https://test.wslhome.top/user/getAppraisalAll/v1',{'flag':flag},function(msg){
					if (msg.code == 0 && msg.data.length > 0){
						msg.data.forEach(function(v){
							html += '<dl class="fl"><dt><a href="#"><img src="'+v.imgUrl+'"/></a></dt>'
								+'<dd><a href="#">'+v.goodsName+'</a></dd><dd>'+v.price+'</dd>';
							
							if (flag==true){
								html +='<dd><a onclick = "setDetail('+v.orderId+')">评价</a></dd></dl>';
							}else{
								html+='<dd><a onclick = "getDetail('+ JSON.stringify(v).replace(/"/g, "&quot;") +')">查看评价</a></dd></dl>';
							}
							
						})
						$('#htmlss').empty();
						$('#htmlss').append(html);
						$(".sx dl dd").find("a").click(function(){
							$(".mask").show();
							$(".pj").show();
						});
					}
				});
			}
		function setDetail(v){
			for (var i = 0; i < 5; i++){
				$("#xin .t"+i).attr('src','../static/user/imgs/xin.png');
			}
			$("#ping").val('');
			$("#orderIds").val(v);

		}
		function sets(t){
			for (var i = 0; i < 5; i++){
				$("#xin .t"+i).attr('src','../static/user/imgs/xin.png');
			}
			ttt = t;
			if (tfalg == true){
				for (var i = 0; i < t; i++){
					$("#xin .t"+i).attr('src','../static/user/imgs/hxin.png');
				}
			}
		}
		function getDetail(v){
			for (var i = 0; i < 5; i++){
				$("#xin .t"+i).attr('src','../static/user/imgs/xin.png');
			}
			$("#orderIds").val(v.id);
			for (var i = 0; i < v.grade; i++){
				$("#xin .t"+i).attr('src','../static/user/imgs/hxin.png');
			}
			$("#ping").val(v.detail);
			$("#bao").val("删除");
		}
		
		$('#bao').click(function(){
			if ($("#bao").val()=="删除"){
				$.ajax({
				    type: 'delete',
				    url: "https://test.wslhome.top/user/deleteAppraisal/v1",
				    data: {'id':$("#orderIds").val()},
				    dataType: "json",
				    success: function (msg) {
						alert(msg.data);
						pinjia(false);
				     }
				})
			}else if ($("#bao").val()=="保存"){
				$.post('https://test.wslhome.top/user/addAppraisal/v1',{'orderId':$("#orderIds").val(),'detail':$("#ping").val(),'grade':ttt},function(msg){
					if (msg.code == 0){
						alert(msg.data);
						pinjia(true);
					}else{
						alert(msg.userMsg);
					}
				})
				
			}
		})