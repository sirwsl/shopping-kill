$(function(){

//	订单页面  修改地址
	$(".edit").click(function(){
		$(".mask").show();
		$(".adddz").show();
	});
	
	$(".bc>input").click(function(){
		if($(this).val()=="保存"){
			$(".mask").hide();
			$(".adddz").hide();
			$(".bj").hide();
			$(".xg").hide();
			$(".remima").hide();
			$(".pj").hide();
			$(".chak").hide();
		}else{
			$(".mask").hide();
			$(".adddz").hide();
			$(".bj").hide();
			$(".xg").hide();
			$(".remima").hide();
			$(".pj").hide();
			$(".chak").hide();
		}
	});
	
	
	
//	我的订单tab切换
	$("#wa li").click(function(){
		$(this).addClass("on").siblings().removeClass("on");
		var txt1=$(this).find("a").text();
		$(".dkuang").find("p.one").each(function(){
			var txt2=$(this).text();
				if(txt1==txt2){
					$(this).parent(".dkuang").show().siblings(".dkuang").hide();
				}
				$("#wa li").eq(0).click(function(){
					$(".dkuang").show();
				})
		});
	});
	
//	评价 tab切换
		
	$(".sx div:gt(0)").hide();
	$(".sx div").each(function(i){
		if($(this).html()==''){
			var str = $("#pro li").eq(i).find("a").text();
			var txt ='';
			txt = '<div class="noz">当前没有'+str+'。</div>';
			$(this).html(txt);
		}
	});
	$("#pro li").click(function(){
		$(this).addClass("on").siblings().removeClass("on");
		var index=$(this).index();
		$(".sx > div").eq(index).show().siblings().hide();
	});
	
	//评价打心

	
//	个人信息 编辑
	$("#edit1").click(function(){
		$(".mask").show();
		$(".bj").show();
	});
	$("#edit2").click(function(){
		$(".mask").show();
		$(".xg").show();
	});
	
//修改头像
	$("#avatar").click(function(){
		$(".mask").show();
		$(".avatar").show();
	});

//	弹框关闭按钮
	$(".gb").click(function(){
		$(".mask").hide();
		$(".bj").hide();
		$(".xg").hide();
		$(".remima").hide();
		$(".avatar").hide();
		$(".pj").hide();
		$(".chak").hide();
	});
	
	
	
	
	
	
	
	
	
//	address
	
//	查看物流
	$(".vewwl").hover(function(){
		$(this).children(".wuliu").fadeIn(100);
	},function(){
		$(this).children(".wuliu").fadeOut(100);
	});
	
})
