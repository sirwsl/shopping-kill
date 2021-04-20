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
	var userOrderIds;
		$(function(){
			addressUpload();
			userOrderIds = localStorage.getItem('userOrderId');
			$.get('http://localhost/user/getOrderDetail/v1',{'id':userOrderIds},function(msg){
			
				if (msg.code == 0){
					var v = msg.data;
					$('#imgss').html('<img src="'+v.imgUrl+'">');
					$('#name').html(v.goodsName);
					$('#details').html(v.skuName);
					$('#num').html(v.num);
					$('#money').html('¥'+v.price+'x'+v.num);
					$('#price').html('¥'+v.price*v.num);
					$('#yunfei').html('¥'+v.logisticsPrice);
					$('#totalPrice').html('¥'+v.totalPrice);
				}
			})
		})

	function addressUpload(){
		$.get('http://localhost/user/getAddress/v1',function(msg){
			var html = '';
			if (msg.code == 0 && msg.data.length > 0){
				
				msg.data.forEach(function(v){
					
					if (v.status){
						html += '<div class="addre fl on" ><div class="tit clearfix">'
						+ '<p class="fl">'+v.name+'<span class="default">[默认地址]</span></p>'	;
					}else{
						html += '<div class="addre fl"><div class="tit clearfix">'
						+'<p class="fl">'+v.name+'<span class=""></span></p>';	
					}		
					html+='<input style="display: none;" value = "'+v.id+'">'
							+'<p class="fr"><a href="#" onclick = "del('+v.id+')">删除</a><span>|</span>'
							+'<a href="#" onclick = "add('+ JSON.stringify(v).replace(/"/g, "&quot;") + ')">编辑</a><span>|</span>'
							+'<a href="#" onclick = "setMO('+v.id+')">默认</a></p>'
							+'</div><div class="addCon"><p>'+v.address+'</p>'
							+'<p>'+v.phone+'</p></div></div>';
				})
			}else{
				html += '<h3>没有收件人</h3>';
			}
			$('#addresses').empty()
			$('#addresses').append(html);
		})		
	}
	$("#addresses").on("click",".addre.fl",function(){ 
		$(this).addClass('on').siblings().removeClass("on");
	})

	function add(v){
		$("#mask1").css("display","block");
		$("#mask2").css("display","block");
		
		$("#id").val(v.id);
		$("#name").val(v.name);
		$("#phone").val(v.phone);
		$("#addressNum").val(v.addressNum);
		$("#address").val(v.address);
	}
	
	$(".fr.edit").click(function(){
		$("#id").val('');
		$("#name").val('');
		$("#phone").val('');
		$("#addressNum").val('');
		$("#address").val('');
	})
	
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
	function del(id){
		var msg = "请确认是否删除该收件人";
		 if (confirm(msg)==true){ 
			$.ajax({
			    url : "http://localhost/user/delAddressById/v1",
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
	
	function setMO(id){
		var msg = "请确认是否将该地址设置为默认地址";
		 if (confirm(msg)==true){ 
			$.ajax({
			    url : "http://localhost/user/setDefaultAddress/v1",
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
	
	function subs(){
		var addressId = $("#addresses .addre.fl.on input").val();
		var type = $('#payaway .on').attr("alt");
		var detail = $('#detail').val();
		var orderId = userOrderIds;
		$.post('http://localhost/user/pay/v1',
			{'orderId':orderId,'payType':type,'addressId':addressId,'detail':detail},
			function(msg){
				if (msg.code == 0){
					parent.document.getElementById("displayArea").src="payOk.html";
				}else{
					alert(msg.userMsg);
				}
			}
		)
		
	}