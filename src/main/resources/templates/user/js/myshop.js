
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
			//$(this).css('border','1px solid #d2d2d2');
			$(this).delay('300').animate(300);
		});
		$('.cate-layer-right-slide img').mouseleave(function() {
			$(this).css('border', 'none')
		});
		$()


		$('.search').kuCity();
		$("#lun2").slide({
			autoplay: true,
			autoTime: 5000,
		});

//	<!--滚动展示-->

		(function($) {

			$.fn.myScroll = function(options) {
				//默认配置
				var defaults = {
					speed: 50, //滚动速度,值越大速度越慢
					rowHeight: 50 //每行的高度
				};

				var opts = $.extend({}, defaults, options),
					intId = [];

				function marquee(obj, step) {

					obj.find("ul").animate({
						marginTop: '-=1'
					}, 0, function() {
						var s = Math.abs(parseInt($(this).css("margin-top")));
						if(s >= step) {
							$(this).find("li").slice(0, 1).appendTo($(this));
							$(this).css("margin-top", 0);
						}
					});
				}

				this.each(function(i) {
					var sh = opts["rowHeight"],
						speed = opts["speed"],
						_this = $(this);
					intId[i] = setInterval(function() {
						if(_this.find("ul").height() <= _this.height()) {
							clearInterval(intId[i]);
						} else {
							marquee(_this, sh);
						}
					}, speed);

					_this.hover(function() {
						clearInterval(intId[i]);
					}, function() {
						intId[i] = setInterval(function() {
							if(_this.find("ul").height() <= _this.height()) {
								clearInterval(intId[i]);
							} else {
								marquee(_this, sh);
							}
						}, speed);
					});

				});

			}

		})(jQuery);

		$(function() {

			$("div.ranklist").myScroll({
				speed: 50,
				rowHeight: 50
			});

		});
