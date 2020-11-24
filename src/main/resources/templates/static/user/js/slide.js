/*
 * create by zhangxiaojian
 *调用方法 $(ID/class).slide({
 *	autoplay:true/false,
 *	autoTime:4000  //自己设定 
 *});
 *
 *
 */

(function ($) {
	$.fn.slide = function(options) { 
		var dft = {
			zs_lunLock:true,
			autoplay:true,
			autoTime:4000,
			curentImg:0,
			height:0,
			clickLunTime:500
		};
		var ops = $.extend(dft,options);
		
		var zs_item = $(this).find(".slideItem");
		var zs_itemWidth = $(this).find(".slideItem").width();
		var t_time;
		var isImgLoad = false;
		var $this = this;
		var zs_dotListHtml = "<ul>";
		var zs_dots;
		var maxHeight = 0;
		
		var checkVideo = 0;
		
		if(ops.height==0){
			t_time = setInterval(function(){
				$($this).find(".slideItem img").each(function(){
					if(this.height === 0&&$(this).is(":visible")){
						isImgLoad = false;
						return false;
					}else{
						maxHeight = maxHeight>$(this).height() ? maxHeight:$(this).height();
						isImgLoad = true;
					}
				});
				if(isImgLoad){
					maxHeight = maxHeight>$($this).height() ? maxHeight:$($this).height();
					clearInterval(t_time);
					if($($this).find(".slideItem").find("video").length>0&&checkVideo==0){
						checkVideo=1;
					}
					if(checkVideo>0){
						$($this).find(".carouse").height(maxHeight-5);
					}else{
						$($this).find(".carouse").height(maxHeight);
					}
					$($this).find(".playBtn").css("top",(maxHeight-50)/2);
					lunbo();
				}
				//ItemImgLoad(callback); // 递归扫描
				//console.log();
			},100); // 我这里设置的是100毫秒就扫描一次，可以自己调整
			
		}
		function lunbo(){
			zs_itemWidth = $($this).find(".slideItem").width();
			if(ops.height==0){
				if(checkVideo>0){
					$($this).find(".carouse").height($($this).find(".slideItem").height()-5);
				}else{
					$($this).find(".carouse").height($($this).find(".slideItem").height());
				}
				$($this).find(".playBtn").css("top",($($this).find(".slideItem").height()-50)/2);
			}else{
				$($this).find(".carouse").height(ops.height);
			}
			for(var i=0;i<zs_item.length;i++){
				$($this).find(".itemIndex"+i).css("left",zs_itemWidth*i);
			}
		}
		$(window).resize(function() {
			lunbo();
		});
		for(var i=0;i<zs_item.length;i++){
			var toLeft = zs_itemWidth*i;
			$(this).find(zs_item[i]).css("left",toLeft);
			$(this).find(zs_item[i]).addClass("itemIndex"+i);
			zs_dotListHtml+='<li class="dot" dotIndex='+i+'></li>';
			if(i==zs_item.length-1){
				zs_dotListHtml+="</ul>";
				var zs_showWord = "<p class='showLunWord'></p>";
				$(this).find(".dotList").html(zs_dotListHtml);
				$(this).find(".arti-content").html(zs_showWord);
				$(this).find('.dotList ul li').first().addClass("active");
				 $($this).find(".arti-content .showLunWord").html($($this).find(zs_item[0]).find(".slidedetail").html());
				zs_dots = $(this).find('.dot');
			}
		}
		var zs_next = function(){
			lunbo();
			if(ops.zs_lunLock){
				ops.zs_lunLock = false;
				var Move_left =0;
				var intemcount = zs_item.length-1;
				$($this).find(".itemIndex"+intemcount).css("left",-zs_itemWidth);
				 for(var i=0;i<zs_item.length;i++){
					Move_left =parseFloat($($this).find(".itemIndex"+i).css("left"))+parseFloat($($this).find(".slideItem").width());
					$($this).find(".itemIndex"+i).animate({left: Move_left},500);
				}
				 for(var j=0;j<zs_item.length;j++){
					 var className = $($this).find(zs_item[j]).attr("class")+"";
					 var count = className.indexOf("itemIndex");
					 var index = className.substring(count,className.length);
					 var pp = index.indexOf(" ");
					 if(pp>0){
						 index = index.substring(9,pp);
					 }else{
						 index = index.substring(9,index.length);
					 }
					 $($this).find(zs_item[j]).removeClass("itemIndex"+index);
					 var next_index = parseInt(index)+1;
					 if(next_index==zs_item.length){
						 next_index=0;
					 }
					 //控制底部圆点
					 $($this).find(zs_dots[j]).removeClass("active");
					 if(next_index==0){
						 $($this).find(zs_dots[j]).addClass("active");
						 $($this).find(".arti-content .showLunWord").html($($this).find(zs_item[j]).find(".slidedetail").html());
					 }
					 $($this).find(zs_item[j]).addClass("itemIndex"+next_index);
				 }
				 
				 setTimeout(function(){
					 ops.zs_lunLock = true;
				 },500);
			}
		};
		var zs_pre = function(){
			lunbo();
			if(ops.zs_lunLock){
				ops.zs_lunLock = false;
				var Move_left =0;
				 for(var i=0;i<zs_item.length;i++){
					Move_left =parseFloat($($this).find(".itemIndex"+i).css("left"))-parseFloat($($this).find(".slideItem").width());
					$($this).find(" .itemIndex"+i).animate({left: Move_left},500);
				}
				 for(var j=0;j<zs_item.length;j++){
					 var className = $($this).find(zs_item[j]).attr("class")+"";
					 var count = className.indexOf("itemIndex");
					 var index = className.substring(count,className.length);
					 var pp = index.indexOf(" ");
					 if(pp>0){
						 index = index.substring(9,pp);
					 }else{
						 index = index.substring(9,index.length);
					 }
					 $($this).find(zs_item[j]).removeClass("itemIndex"+index);
					 var next_index = parseInt(index)-1;
					 if(next_index==-1){
						 next_index=zs_item.length-1;
					 }
					
					//控制底部圆点
					 $($this).find(zs_dots[j]).removeClass("active");
					 if(next_index==0){
						 $($this).find(zs_dots[j]).addClass("active");
						 $($this).find(".arti-content .showLunWord").html($($this).find(zs_item[j]).find(".slidedetail").html());
					 }
					 $($this).find(zs_item[j]).addClass("itemIndex"+next_index);
				 }
				
				  setTimeout(function(){
					  var des = parseFloat(zs_itemWidth)*(zs_item.length-1);
					  $($this).find(".itemIndex"+(zs_item.length-1)).animate({left: des},0.01);
					  ops.zs_lunLock = true;
				  },500);
			}
		}
		$(this).find(".Next").click(function(){
			zs_next();
		});
		$(this).find(".Previous").click(function(){zs_pre();});
		$(this).find(".dot").click(function(){
			lunbo();
			var dotIntex = $(this).attr("dotIndex");
			var itemIndex="";
			for(var i=0;i<zs_item.length;i++){
				var tagClass = $($this).find(zs_item[i]).attr("class")+"";
				if(tagClass.indexOf("itemIndex0")>0){
					itemIndex = i;
				}
			}
			var Dvalue = dotIntex-itemIndex;
			if(Dvalue>0){
				zs_prevGo(Dvalue);
			}else{
				zs_nextGo(-Dvalue);
			}
		});
			var zs_prevGo = function(Dvalue){
				if(ops.zs_lunLock){
					ops.zs_lunLock = false;
					var Move_left =0;
					 for(var i=0;i<zs_item.length;i++){
						Move_left =parseFloat($($this).find(".itemIndex"+i).css("left"))-Dvalue*parseFloat($($this).find(".slideItem").width());
						$($this).find(" .itemIndex"+i).animate({left: Move_left},ops.clickLunTime);
					}
					 for(var j=0;j<zs_item.length;j++){
						 var className = $($this).find(zs_item[j]).attr("class")+"";
						 var count = className.indexOf("itemIndex");
						 var index = className.substring(count,className.length);
						 var pp = index.indexOf(" ");
						 if(pp>0){
							 index = index.substring(9,pp);
						 }else{
							 index = index.substring(9,index.length);
						 }
						 $($this).find(zs_item[j]).removeClass("itemIndex"+index);
						 var next_index = parseInt(index)-Dvalue;
						 if(next_index<0){
							 next_index=zs_item.length+next_index;
						 }
						
						//控制底部圆点
						 $($this).find(zs_dots[j]).removeClass("active");
						 if(next_index==0){
							 $($this).find(zs_dots[j]).addClass("active");
							 $($this).find(".arti-content .showLunWord").html($($this).find(zs_item[j]).find(".slidedetail").html());
						 }
						 $($this).find(zs_item[j]).addClass("itemIndex"+next_index);
					 }
					
					  setTimeout(function(){
						  for(var m=0;m<zs_item.length;m++){
							  var des = parseFloat(zs_itemWidth)*(zs_item.length-1-m);
							  $($this).find(".itemIndex"+(zs_item.length-1-m)).animate({left: des},0.01);
							  
						  }
						  ops.zs_lunLock = true;
					  },500);
				}
				
			}
			var zs_nextGo = function(Dvalue){
				if(ops.zs_lunLock){
					ops.zs_lunLock = false;
					var Move_left =0;
					var y=zs_item.length-1;
					for(var x=0;x<Dvalue;x++){
						$($this).find(".itemIndex"+y).css("left",-zs_itemWidth-x*zs_itemWidth);
						 y--;
					}
					 for(var i=0;i<zs_item.length;i++){
						Move_left =parseFloat($($this).find(".itemIndex"+i).css("left"))+Dvalue*parseFloat($($this).find(".slideItem").width());
						$($this).find(" .itemIndex"+i).animate({left: Move_left},ops.clickLunTime);
					}
					 for(var j=0;j<zs_item.length;j++){
						 var className = $($this).find(zs_item[j]).attr("class")+"";
						 var count = className.indexOf("itemIndex");
						 var index = className.substring(count,className.length);
						 var pp = index.indexOf(" ");
						 if(pp>0){
							 index = index.substring(9,pp);
						 }else{
							 index = index.substring(9,index.length);
						 }
						 $($this).find(zs_item[j]).removeClass("itemIndex"+index);
						 var next_index = parseInt(index)+Dvalue;
						 if(next_index>=zs_item.length){
							 next_index=next_index-zs_item.length;
						 }
						 //控制底部圆点
						 $($this).find(zs_dots[j]).removeClass("active");
						 if(next_index==0){
							 $($this).find(zs_dots[j]).addClass("active");
							 $($this).find(".arti-content .showLunWord").html($($this).find(zs_item[j]).find(".slidedetail").html());
						 }
						 $($this).find(zs_item[j]).addClass("itemIndex"+next_index);
					 }
					 
					 setTimeout(function(){
						 ops.zs_lunLock = true;
					 },500);
				}
			};
			$(this).bind('touchstart',function(e){
			    startX = e.originalEvent.changedTouches[0].pageX,
			    startY = e.originalEvent.changedTouches[0].pageY;
			});
			$(this).bind('touchend',function(e){
				endX = e.originalEvent.changedTouches[0].pageX,
				endY = e.originalEvent.changedTouches[0].pageY;
				//获取滑动距离
				distanceX = endX-startX;
				distanceY = endY-startY;
				//判断滑动方向
				if(Math.abs(distanceX)>Math.abs(distanceY) && distanceX>0){
					zs_next();
				}else if(Math.abs(distanceX)>Math.abs(distanceY) && distanceX<0){
					zs_pre();
				}
			});
			
			
			var zs_lunInterval = setInterval(function(){
				if(ops.autoplay){
					zs_pre();
				}
			},ops.autoTime);
			if(!ops.autoplay){
				clearInterval(zs_lunInterval);
			}
		
		
	};
})(jQuery);	