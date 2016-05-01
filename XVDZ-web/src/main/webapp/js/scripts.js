document.body.setAttribute('ontouchstart', '');

$(function() {

	// ----------------------默认数据初始化----------------------

	localStorage.currentPageIndex = localStorage.currentPageIndex || 1;

	if (localStorage.currentPageIndex == '2') {
		localStorage.currentPageIndex = localStorage.prevPageIndex;
	}

	if (localStorage.currentPageIndex == '3') {
		$('.weui_tabbar').slideToggle();
		$('#diy-tools').slideUp();
	}

	localStorage.currentTheme = localStorage.currentTheme || '';

	localStorage.searchContent = localStorage.searchContent || '';

	// ----------------------默认数据初始化----------------------

	$.fn
			.extend({
				animateCss : function(animationName, cb) {
					var animationEnd = 'webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend';
					$(this).addClass('animated ' + animationName).one(
							animationEnd,
							function() {
								$(this)
										.removeClass(
												'animated ' + animationName);
								if (cb) {
									cb();
								}
							});
				}
			});

	// 初始化主题
	$('body').attr('class', localStorage.currentTheme);

	// 初始化地步菜单栏
	$('.weui_tab').tab();

	// 幻灯片
	$.ajax({
		url : "../admin/Advertisment/getSliderAds.do",
		type : "get",
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		data : {
			pageNo : 1,
			pageSize : 3
		},
		success : function(data) {
			var html = baidu.template('t:ads_temp', {
				"list" : data.result
			});
			$("#ads-wraper").html(html);
			// 幻灯片
			var mySwiper = new Swiper('.swiper-container', {
				autoplay : 5000,
				pagination : '.swiper-pagination',
				paginationClickable : true
			});
		}
	});
	//产品类型
	$.ajax({
		url : "../admin/type/productType.do",
		type : "get",
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			var html = baidu.template('t:product_type', {
				"list" : data.result
			});
			$("#productType").html(html);
		}
	});
	// 最热作品
	$.ajax({
		url : "../admin/product/hotProduct.do",
		type : "get",
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		data : {
			pageNo : 1,
			pageSize : 5
		},
		success : function(data) {
			var oldHtml = $("#tab1").html();
			var html = baidu.template('t:hot_product', {
				"list" : data.result
			});
			$("#tab1").html(oldHtml+html);
		}
	});
	// 最新作品
	$.ajax({
		url : "../admin/product/newProduct.do",
		type : "get",
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		data : {
			pageNo : 1,
			pageSize : 5
		},
		success : function(data) {
			var oldHtml = $("#tab2").html();
			var html = baidu.template('t:new_product', {
				"list" : data.result
			});
			$("#tab2").html(oldHtml+html);
		}
	});

	// 初始化列表事件
	$(document).on('click', '.list-block ul li', function() {
		if ($(this).hasClass('item-link')) {
			window.location.href = $(this).attr('id') + '.html';
		}
	});

	$('.back').click(function() {
		window.history.go(-1);
	});

	$('header button.button-nav').click(function() {
		window.location.href = $(this).attr('id') + '.html';
	});

	// 主页菜单栏点击事件，保存上一个活跃的菜单和当前活跃菜单
	$('.weui_tabbar_item').click(function() {

		localStorage.prevPageIndex = localStorage.currentPageIndex;

		localStorage.currentPageIndex = $(this).index() + 1;

		if (localStorage.currentPageIndex == '2') {
			window.location.href = 'voice.html';
		}

		if (localStorage.currentPageIndex == '3') {
			$('.weui_tabbar').slideUp();
			$('#diy-tools').slideDown();
		}

	});

	$('.weui_tabbar_item:nth-child(' + localStorage.currentPageIndex + ')')
			.click();

	// 退出事件
	$(document).on('click', '#logout', function() {
		$.weui.confirm('确认退出?', function() {
			console.log('确认');
		}, function() {
			console.log('取消');
		});
	});

	// 砖到购物车页面
	$('#toMarket').click(function() {
		$('.weui_tabbar_item:nth-child(4)').click();
	});

	// 主题配置事件
	$(document).on('click', '#theme-chosen ul li', function() {
		var id = $(this).attr('id');
		id = id == 'theme-default' ? '' : id;
		$('body').attr('class', id);
		localStorage.currentTheme = id;
	})

	// 购物车去付款事件
	$('#to_pay').click(function() {
		window.location.href = 'pay.html';
	});

	// 主页搜索框事件
	$('#search_input').keydown(function(evt) {

		if (evt.keyCode == 13) {
			if ($(this).val() == '') {
				$.weui.alert('请输入搜索内容');
				return false;
			}
			localStorage.searchContent = $(this).val();
			window.location.href = 'search.html';
		}

	});

	// 搜索页面搜索框事件
	$('#search').val(localStorage.searchContent);
	localStorage.searchContent = '';
	$('#search').keydown(function(evt) {

		// 这里写搜索事件
		if (evt.keyCode == 13) {
			if ($(this).val() == '') {
				$.weui.alert('请输入搜索内容');
				return false;
			}
			localStorage.searchContent = $(this).val();
			// 根据类型、商品名等多条件搜索商品
			$.ajax({
				url : "../admin/product/searchProduct.do",
				type : "get",
				contentType : "application/json; charset=utf-8",
				dataType : "json",
				data : {
					pageNo : 1,
					pageSize : 5,
					searchText:$(this).val()
				},
				success : function(data) {
					var html = baidu.template('t:all_product', {
						"list" : data.result
					});
					$("#listContent").html(html);
				}
			});
		}

	});

	// 搜索页面评论事件
	$(document).on('click', '.card-footer a.leave-a-message', function(ev) {

		$('#comment').show();

		$('#cancel').click(function() {
			$('#comment').hide();
		});

		$('#confirm').click(function() {

			// ajax 提交写在这里

			$('#comment').hide();
		});

	});

	// 商品列表点击事件

	$(document).on(
			'mousedown',
			'.facebook-card .card-content',
			function(ev) {

				if ($(this).next().find('.leave-a-message').length > 0) {
					$(this).next().find('.card-footer').attr('style',
							'background: rgb(238, 238, 238)');
				}

			});

	$(document).on(
			'mouseup',
			'.facebook-card .card-content',
			function(ev) {

				// console.log($(this).next().find('.leave-a-message').length);

				if ($(this).next().find('.leave-a-message').length > 0) {
					$(this).next().find('.card-footer').attr('style',
							'background: rgb(250, 250, 250)');

					window.location.href = 'goods_detail.html';

				}

			});

	// 修正上方按钮位置
	var titleBarRight = $('.title-bar.right');
	for (var i = 0; i < titleBarRight.length; i++) {
		var curr = titleBarRight[i];
		$(curr).attr('style',
				'left: ' + parseInt($(window).width() - (55 * (i + 1))) + 'px');
	}
	;

	// 修正定制页面按钮位置
	var fixTilebarTightBottomPos = function(num) {

		num = num || 100;

		var titleBarRightBottom = $('.title-bar.right-bottom');
		for (var i = 0; i < titleBarRightBottom.length; i++) {
			var curr = titleBarRightBottom[i];
			$(curr).attr(
					'style',
					'left: ' + parseInt($(window).width() - 55) + 'px;top:'
							+ parseInt($(window).height() - (55 * i) - num)
							+ 'px');
		}
		;

		$('.title-bar.right-bottom').animateCss('fadeInDown');

	}

	fixTilebarTightBottomPos();

	// titlebar点击事件
	$('.title-bar.blue')
			.mousedown(
					function() {

						$(this)
								.attr(
										'style',
										$(this).attr('style')
												+ ';color:rgb(143, 143, 146);background:rgba(117, 147, 213, 0.4)');

					});

	$('.title-bar.blue')
			.mouseup(
					function() {

						$(this)
								.attr(
										'style',
										$(this).attr('style')
												+ ';color:rgb(255, 255, 255);background:rgba(117, 147, 213, 0.8)');

					});

	$('#go-back').click(function() {
		window.history.go(-1);
	});

	// 定制页面左上角按钮事件
	$('#back-prev-menu').click(function() {
		$('.weui_tabbar').slideToggle();
		$('.weui_tabbar_item:nth-child(' + 1 + ')').click();
	});

	// 提现按钮事件
	$('#topup').click(function() {

		$('#top-up-form').show();

		$('#cancel').click(function() {
			$('#top-up-form').hide();
		});

		$('#confirm').click(function() {

			// ajax 提交写在这里

			$('#top-up-form').hide();
		});

		$('#withdrawall').click(function() {

			$('#withdraw-balance').val($('#balance-had').html());

			return false;

		});

	});

	// ------------------------------------------定制页面控制逻辑------------------------------------------

	// 初始化canvas
	$('#c').attr('width', $(window).width() + 'px');
	$('#c').attr('height', ($(window).height() - 44) + 'px');
	var canvas = new fabric.Canvas('c');

	fabric.Image.fromURL('./src/images/icon_nav_progress.png', function(img) {

		img.scale(1).set({
			left : 150,
			top : 150,
			angle : -15
		});

		canvas.add(img).setActiveObject(img);

	});

	$('#trends, #pictures').height($(document).height() - 44).css({
		'z-index' : 65530
	});

	var drawingOptionsEl = $('#drawing-mode-options'), drawingColorEl = $('#drawing-color'), drawingShadowColorEl = $('#drawing-shadow-color'), drawingLineWidthEl = $('#drawing-line-width'), drawingShadowWidth = $('#drawing-shadow-width'), drawingShadowOffset = $('#drawing-shadow-offset');

	var toolPanelIsShow = false;
	// 定制页面底部菜单
	var startToolPanelAnimation = function(open) {

		if (toolPanelIsShow) {
			$('#' + open).animateCss('fadeOutDown', function() {
				$('#' + open).hide();
			});
			fixTilebarTightBottomPos(100);
			$('#' + open).removeClass('active');
			console.log($('#' + open));
			toolPanelIsShow = false;
		} else {
			fixTilebarTightBottomPos(340);
			$('#' + open).show();
			$('#' + open).animateCss('fadeInUp');
			toolPanelIsShow = true;
			$('#' + open).addClass('active');
		}

	}

	$('#diy-tools .tab-item').click(function() {

		$('#diy-tools .tab-item.active').toggleClass('active');
		$(this).addClass('active');

		var open = $(this).attr('data-open');

		sessionStorage.currentTool = open;
		sessionStorage.preTool = $('.diytool-panel.active').attr('id');

		if (sessionStorage.currentTool == sessionStorage.preTool) {
			startToolPanelAnimation(open);
			$(this).removeClass('active');
			return false;
		}

		if ($('.diytool-panel.active').length > 0) {

			$('.diytool-panel.active').animateCss('fadeOutDown');
			$('.diytool-panel.active').hide();
			$('.diytool-panel.active').removeClass('active');
			$('#' + sessionStorage.preTool).removeClass('animated');
			$('#' + sessionStorage.preTool).removeClass('fadeOutDown');

			toolPanelIsShow = false;

			startToolPanelAnimation(open);

		} else {
			startToolPanelAnimation(open);
		}

		if (open == 'diy') {
			// 进入自由绘画（涂鸦）状态
			canvas.isDrawingMode = true;
			$.weui.topTips('进入编辑状态');
		} else {
			canvas.isDrawingMode = false;
		}

	});

	$('#down-toolpanel').click(function() {
		if ($('.diytool-panel.active').length > 0) {
			startToolPanelAnimation($('.diytool-panel.active').attr('id'));
		}
	});

	// console.log($(document).css('font-family'));

	(function() {

		var align = 'left', lineHeight = 1, textBackgroundColor = '', stroke = '', strokeWidth = 1, fontStyle = '', fontFamily = '微软雅黑', textDecoration = 'none';

		$('#text-font-family').change(function() {
			fontFamily = this.value;
		});

		$('#text-font-style').change(function() {
			fontStyle = this.value;
			console.log(fontStyle);
		});

		$('#text-text-deco').change(function() {
			textDecoration = this.value;
			console.log(textDecoration);
		});

		$('#text-stroke-width').change(function() {
			strokeWidth = this.value;
		});

		$('#text-stroke-color').change(function() {
			stroke = this.value;
		});

		$('#text-line-height').change(function() {
			lineHeight = this.value;
		});

		$('#text-bg-color').change(function() {
			textBackgroundColor = this.value;
		});

		$('#add-text-fuck').click(function() {

			$('#text-adding-form').show();

			$('#cancel-text-adding').click(function() {
				$('#text-adding-form').hide();
			});

		});

		$('#confirm-text-adding').click(function() {

			var textToAdd = $('#text-adding-field').val();

			var text = new fabric.Text(textToAdd, {

				textAlign : align,

				left : 100,
				top : 100,

				lineHeight : lineHeight,
				textBackgroundColor : textBackgroundColor,
				stroke : stroke,
				strokeWidth : strokeWidth,
				fontStyle : fontStyle,
				fontFamily : fontFamily,
				textDecoration : textDecoration

			});

			canvas.add(text);

			$('#text-adding-form').hide();

		});

		$('#text-align a').click(function() {

			align = $(this).attr('id').split('-');
			align = align[1];

			$(this).parent().find('a.active').removeClass('active');
			$(this).addClass('active');

		});

	})();

	// 更换颜色
	(function() {

		$('#colors .card-cover').click(function() {

			// fabric.util.loadImage($(this).attr('src'), function(img) {
			// cloth.fill = new fabric.Pattern({
			// source: img,
			// repeat: 'repeat'
			// });
			// canvas.renderAll();
			// });

			fabric.Image.fromURL($(this).attr('src'), function(img) {

				img.scale(1).set({
					left : 150,
					top : 150,
					angle : -15
				});

				canvas.add(img).setActiveObject(img);

			});

		});

	})();

	$('.diytool-panel').find('.weui_bar_item_on').removeClass(
			'weui_bar_item_on');

	// 涂鸦面板按钮事件
	$('.diytool-panel .weui_navbar .weui_navbar_item').click(function() {

		var fun = {
			next : function() {

			},

			prev : function() {

			},

			clearCanvas : function() {
				canvas.clear();
			}
		};

		fun[$(this).attr('id')]();

		return false;

	});

	$('#scale-this').click(function() {

	});

	canvas.on({
		'touch:gesture' : function() {
			// info.insertBefore(text, info.firstChild);
		},
		'touch:drag' : function() {
			// info.insertBefore(text, info.firstChild);
		},
		'touch:orientation' : function() {
			// info.insertBefore(text, info.firstChild);
		},
		'touch:shake' : function() {
			// info.insertBefore(text, info.firstChild);
		},
		'touch:longpress' : function() {
			// info.insertBefore(text, info.firstChild);
		}
	});

	if (fabric.PatternBrush) {
		var vLinePatternBrush = new fabric.PatternBrush(canvas);
		vLinePatternBrush.getPatternSrc = function() {

			var patternCanvas = fabric.document.createElement('canvas');
			patternCanvas.width = patternCanvas.height = 10;
			var ctx = patternCanvas.getContext('2d');

			ctx.strokeStyle = this.color;
			ctx.lineWidth = 5;
			ctx.beginPath();
			ctx.moveTo(0, 5);
			ctx.lineTo(10, 5);
			ctx.closePath();
			ctx.stroke();

			return patternCanvas;
		};

		var hLinePatternBrush = new fabric.PatternBrush(canvas);
		hLinePatternBrush.getPatternSrc = function() {

			var patternCanvas = fabric.document.createElement('canvas');
			patternCanvas.width = patternCanvas.height = 10;
			var ctx = patternCanvas.getContext('2d');

			ctx.strokeStyle = this.color;
			ctx.lineWidth = 5;
			ctx.beginPath();
			ctx.moveTo(5, 0);
			ctx.lineTo(5, 10);
			ctx.closePath();
			ctx.stroke();

			return patternCanvas;
		};

		var squarePatternBrush = new fabric.PatternBrush(canvas);
		squarePatternBrush.getPatternSrc = function() {

			var squareWidth = 10, squareDistance = 2;

			var patternCanvas = fabric.document.createElement('canvas');
			patternCanvas.width = patternCanvas.height = squareWidth
					+ squareDistance;
			var ctx = patternCanvas.getContext('2d');

			ctx.fillStyle = this.color;
			ctx.fillRect(0, 0, squareWidth, squareWidth);

			return patternCanvas;
		};

		var diamondPatternBrush = new fabric.PatternBrush(canvas);
		diamondPatternBrush.getPatternSrc = function() {

			var squareWidth = 10, squareDistance = 5;
			var patternCanvas = fabric.document.createElement('canvas');
			var rect = new fabric.Rect({
				width : squareWidth,
				height : squareWidth,
				angle : 45,
				fill : this.color
			});

			var canvasWidth = rect.getBoundingRectWidth();

			patternCanvas.width = patternCanvas.height = canvasWidth
					+ squareDistance;
			rect.set({
				left : canvasWidth / 2,
				top : canvasWidth / 2
			});

			var ctx = patternCanvas.getContext('2d');
			rect.render(ctx);

			return patternCanvas;
		};

		var img = new Image();
		img.src = '../assets/honey_im_subtle.png';

		var texturePatternBrush = new fabric.PatternBrush(canvas);
		texturePatternBrush.source = img;
	}

	$('#drawing-mode-selector').change(function() {

		console.log(this);

		if (this.value === 'hline') {
			canvas.freeDrawingBrush = vLinePatternBrush;
		} else if (this.value === 'vline') {
			canvas.freeDrawingBrush = hLinePatternBrush;
		} else if (this.value === 'square') {
			canvas.freeDrawingBrush = squarePatternBrush;
		} else if (this.value === 'diamond') {
			canvas.freeDrawingBrush = diamondPatternBrush;
		} else if (this.value === 'texture') {
			canvas.freeDrawingBrush = texturePatternBrush;
		} else {
			canvas.freeDrawingBrush = new fabric[this.value + 'Brush'](canvas);
		}

		if (canvas.freeDrawingBrush) {
			// canvas.freeDrawingBrush.color = drawingColorEl.value;
			// canvas.freeDrawingBrush.width =
			// parseInt(drawingLineWidthEl.value, 10) || 1;
			// canvas.freeDrawingBrush.shadowBlur =
			// parseInt(drawingShadowWidth.value, 10) || 0;
		}
	});

	drawingColorEl.change(function() {
		canvas.freeDrawingBrush.color = this.value;
	});

	drawingShadowColorEl.change(function() {
		canvas.freeDrawingBrush.shadowColor = this.value;
	});

	drawingLineWidthEl.change(function() {
		canvas.freeDrawingBrush.width = parseInt(this.value, 10) || 1;
		this.previousSibling.innerHTML = this.value;
	});

	drawingShadowWidth.change(function() {
		canvas.freeDrawingBrush.shadowBlur = parseInt(this.value, 10) || 0;
		this.previousSibling.innerHTML = this.value;
	});

	drawingShadowOffset
			.change(function() {
				canvas.freeDrawingBrush.shadowOffsetX = canvas.freeDrawingBrush.shadowOffsetY = parseInt(
						this.value, 10) || 0;
				this.previousSibling.innerHTML = this.value;
			});

	if (canvas.freeDrawingBrush) {
		// canvas.freeDrawingBrush.color = drawingColorEl.value;
		// canvas.freeDrawingBrush.width = parseInt(drawingLineWidthEl.value,
		// 10) || 1;
		// canvas.freeDrawingBrush.shadowBlur = 0;
	}

	// ------------------------------------------定制页面控制逻辑------------------------------------------

});

$(function() {

	var max = 200;
	$('#textarea').on('input', function() {
		var text = $(this).val();
		var len = text.length;

		$('#count').text(len);

		if (len > max) {
			$(this).closest('.weui_cell').addClass('weui_cell_warn');
		} else {
			$(this).closest('.weui_cell').removeClass('weui_cell_warn');
		}

	});

})
