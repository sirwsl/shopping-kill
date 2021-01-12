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
				addressUpload();
			})
			$(function() {
				if ($.cookie("token") == null || $.cookie("token") == "") {
					window.location.href = "index.html"
				}
			});
			function addressUpload(){
				$.get('https://test.wslhome.top/user/getAddress/v1',function(msg){
					var html = '<div class="add"><div><a href="#2" id="addxad" style = "margin: 60px auto 0;"><img src="../static/user/imgs/jia.png"/></a></div>';
					if (msg.code == 0 && msg.data.length > 0){
						var i = 1;
						msg.data.forEach(function(v){
							var tt = v.name;
							if(v.status==true){
								tt += '  <a style="font-size: 0.625rem;">[默认]</a>';
							}
							var ht = '<div class="dizhi"><p>'+tt+'</p><p>'+v.phone+'</p><p>'+v.address+'</p><p>'+v.addressNum+'</p><p class="addp"><a href="#" id="readd" onclick = "add('+ JSON.stringify(v).replace(/"/g, "&quot;") + ')">修改</a><a href="#" onclick="del('+v.id+')">删除</a><a href="#" onclick="setMO('+v.id+')">设置默认</a></p></div>';
							if (i < 3){
								html += ht; 
							}else {
								var temp = i%3;
								if (temp == 0){
									html +='</div><div class="add">'+ht;
								}else{
									html +=ht;
								}
								
							}
							++i;
						})
						
						html+='</div>';
					}
					$('#htmlss').empty()
					$('#htmlss').append(html);
				
					$('#addxad').click(function(){
						$(".mask").show();
						$(".adddz").show();
					})
					
				})		
			}
			$("#adOk").click(function() {
				$("#addressAdd").ajaxSubmit(function(a) {
					if (a.code == 0) {
						alert(a.data);
						addressUpload();
					} else {
						alert(a.userMsg);
					}
				})
			});
			function add(v){	
				$(".mask").show();
				$(".adddz").show();
				$("#id").val(v.id);
				$("#name").val(v.name);
				$("#phone").val(v.phone);
				$("#addressNum").val(v.addressNum);
				$("#address").val(v.address);
			}
			function setMO(id){
				var msg = "请确认是否将该地址设置为默认地址";
				 if (confirm(msg)==true){ 
					$.ajax({
					    url : "https://test.wslhome.top/user/setDefaultAddress/v1",
					    data : {id:id},
					    type : "PUT",
					    success : function(msg){
					        if (msg.code == 0) {
					        	alert(msg.data);
					        	addressUpload();
					        } else {
					        	alert(msg.userMsg);
					        }
					    }
					})
				 }else{
					 return false;
				 }
			}
			function del(id){
				var msg = "请确认是否删除该收件人";
				 if (confirm(msg)==true){ 
					$.ajax({
					    url : "https://test.wslhome.top/user/delAddressById/v1",
					    data : {id:id},
					    type : "DELETE",
					    success : function(msg){
					        if (msg.code == 0) {
					        	alert(msg.data);
					        	addressUpload();
					        } else {
					        	alert(msg.userMsg);
					        }
					    }
					})
				 }else{
					 return false;
				 }
			}