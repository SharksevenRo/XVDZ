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
	var mySwiper = new Swiper('.swiper-container', {
		autoplay : 5000,
		pagination : '.swiper-pagination',
		paginationClickable : true
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
			$('body').css({
				'padding-top' : '0px'
			});
		} /*else {
			$('body').css({
				'padding-top' : '44px'
			});
		}*/

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
	});

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
			// ajax异步请求开始
		}

	});

	// 搜索页面评论事件
	$(document).on('click', '.card-footer a.leave-a-message', function(ev) {
		var pid=$(this).attr("pid");
		$('#comment').show();

		$('#cancel').click(function() {
			$('#comment').hide();
		});

		$('#confirm').click(function() {
			var text = $("#textarea").val();
			if (text == "") {
				$('#dialog').show();
				setTimeout(function() {
					$('#dialog').hide();
				}, 3000);
				return;
			}
			// ajax 提交写在这里
			$.ajax({
				url : "../admin/ProductComment/saveAjax.do",
				type : "get",
				data : {
					"product.id":pid,
					cmtContent:$("#textarea").val()
				},
				contentType : "application/json; charset=utf-8",
				dataType : "json",
				success : function(data) {
					if (data.code=="1") {
						$('#toast').show();
						setTimeout(function() {
							$('#toast').hide();
						}, 3000);
					}
				}
			});

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
	for ( var i = 0; i < titleBarRight.length; i++) {
		var curr = titleBarRight[i];
		$(curr).attr('style',
				'left: ' + parseInt($(window).width() - (55 * (i + 1))) + 'px');
	}
	;

	// 修正定制页面按钮位置
	var fixTilebarTightBottomPos = function(num, cb) {

		num = num || 100;

		var titleBarRightBottom = $('.title-bar.right-bottom');
		for ( var i = 0; i < titleBarRightBottom.length; i++) {
			var curr = titleBarRightBottom[i];
			$(curr).attr(
					'style',
					'left: ' + parseInt($(window).width() - 55) + 'px;top:'
							+ parseInt($(window).height() - (55 * i) - num)
							+ 'px');
		}
		;

		$('.title-bar.right-bottom').animateCss('fadeInDown');

		if (cb) {
			cb();
		} else {

			$('#front-this').css({
				left : 15
			});

		}

	};

	fixTilebarTightBottomPos(100, function() {
		$('#front-this').css({
			left : 15,
			display : 'none'
		});
	});

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
	$('#cart-this').click(function() {
		var cartList = $.cookie("cart");
		var item = {};
		item.pdtId = $(this).attr("pdtId");
		item.color = $("#typesForm input[name=color").val();
		item.size = $("#typesForm input[name=size").val();
		item.style = $("#typesForm input[name=style").val();
		item.orDtMount = $("#typesForm input[name=orDtMount").val();
		cartList.push(item);
		$.cookie('cart', cartList); 
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
	$('#c').attr('height', ($(window).height()) + 'px');
	var canvas = new fabric.Canvas('c');
	var f = fabric.Image.filters;

	var isShowBack = false;

	// 拓展fabric库，增加获得绝对位置
	fabric.Canvas.prototype.getAbsoluteCoords = function(object) {
		return {
			left : object.left + this._offset.left,
			top : object.top + this._offset.top
		};
	};

	var exitCanvas = function() {
		canvas.clear();
		displayCloths('../img/cloths/front.jpg');
		$('#remove-thisobj').hide();
	};

	// 定制页面左上角按钮事件
	$('#back-prev-menu').click(function() {

		$.weui.confirm('确认放弃当前编辑吗？', function() {

			exitCanvas();
			$('.weui_tabbar').slideToggle();
			$('.weui_tabbar_item:nth-child(' + 1 + ')').click();

		}, function() {

		});

	});

	// 保存图片
	$('#save-this').click(function() {

		$('#cloth-previewer').attr('src', canvas.toDataURL());

		$('#preiview-cloths').show();

	});

	$('#prev-cancel').click(function() {
		$('#preiview-cloths').hide();
	});

	// 确认保存
	$('#prev-confirm').click(function() {

	});

	// 删除当前对象事件
	$('#remove-thisobj').click(function() {
		if (canvas.getActiveObject() != undefined) {
			canvas.remove(canvas.getActiveObject());
		}
		$(this).hide();
	});

	// 定位按钮（居中按钮）
	function positionBtn(obj) {
		var absCoords = canvas.getAbsoluteCoords(obj);

		var btn = $('#remove-thisobj');
		btn.show();

		btn.css({
			left : (absCoords.left - btn.width() / 2) + 'px',
			top : (absCoords.top - btn.height() / 2) + 'px'
		});
	}

	// 点击对象后加入删除按钮
	canvas.on('mouse:up', function(e) {
		positionBtn(e.target);
	});

	var displayCloths = function(src) {

		// canvas.setBackgroundImage(src, canvas.renderAll.bind(canvas), {
		// left: ($(document).width() - 640) / 2.5,
		// top: 44
		// });

		if (canvas.getActiveObject() != undefined) {
			canvas.remove(canvas.getActiveObject());
		}

		fabric.Image.fromURL(src, function(img) {

			img.scale(1).set({
				left : ($(document).width() - 640) / 2.5,
				top : 44,
				angle : 0,
				hasControls : false,
				hasBorders : false,
				selectable : false
			});

			canvas.add(img).setActiveObject(img);

		});
	};

	displayCloths('../img/cloths/front.jpg');

	// ------------------------滤镜相关------------------------

	function applyFilter(index, filter) {
		var obj = canvas.getActiveObject();
		obj.filters[index] = filter;
		obj.applyFilters(canvas.renderAll.bind(canvas));
	}

	function applyFilterValue(index, prop, value) {
		var obj = canvas.getActiveObject();
		if (obj.filters[index]) {
			obj.filters[index][prop] = value;
			obj.applyFilters(canvas.renderAll.bind(canvas));
		}
	}

	(function() {

		canvas
				.on({
					'object:selected' : function() {
						fabric.util.toArray(
								document.getElementsByTagName('input'))
								.forEach(function(el) {
									el.disabled = false;
								});

						var filters = [ 'grayscale', 'invert', 'remove-white',
								'sepia', 'sepia2', 'brightness', 'noise',
								'gradient-transparency', 'pixelate', 'blur',
								'sharpen', 'emboss', 'tint', 'multiply',
								'blend' ];

						for ( var i = 0; i < filters.length; i++) {
							$(filters[i]).checked = !!canvas.getActiveObject().filters[i];
						}
					},

					'selection:cleared' : function() {
						fabric.util.toArray(
								document.getElementsByTagName('input'))
								.forEach(function(el) {
									el.disabled = true;
								});
					}
				});

		$('#grayscale').click(function() {
			applyFilter(0, this.checked && new f.Grayscale());
		});

		$('#invert').click(function() {
			applyFilter(1, this.checked && new f.Invert());
		});

		$('#remove-white').click(function() {
			applyFilter(2, this.checked && new f.RemoveWhite({
				threshold : $('remove-white-threshold').value,
				distance : $('remove-white-distance').value
			}));
		});

		$('#remove-white-threshold').change(function() {
			applyFilterValue(2, 'threshold', this.value);
		});

		$('#remove-white-distance').change(function() {
			applyFilterValue(2, 'distance', this.value);
		});

		$('#sepia').click(function() {
			applyFilter(3, this.checked && new f.Sepia());
		});

		$('#sepia2').click(function() {
			applyFilter(4, this.checked && new f.Sepia2());
		});

		$('#brightness').click(function() {
			applyFilter(5, this.checked && new f.Brightness({
				brightness : parseInt(100, 10)
			}));
		});

		$('#brightness-value').change(function() {
			applyFilterValue(5, 'brightness', parseInt(this.value, 10));
		});

		$('#noise').click(function() {
			applyFilter(6, this.checked && new f.Noise({
				noise : parseInt(100, 10)
			}));
		});

		$('#noise-value').change(function() {
			applyFilterValue(6, 'noise', parseInt(this.value, 10));
		});

		$('#gradient-transparency').click(
				function() {
					applyFilter(7, this.checked
							&& new f.GradientTransparency({
								threshold : parseInt(
										$('gradient-transparency-value').value,
										10)
							}));
				});

		$('#gradient-transparency-value').change(function() {
			applyFilterValue(7, 'threshold', parseInt(this.value, 10));
		});

		$('#pixelate').click(function() {
			applyFilter(8, this.checked && new f.Pixelate({
				blocksize : parseInt($('pixelate-value').value, 10)
			}));
		});

		$('#pixelate-value').change(function() {
			applyFilterValue(8, 'blocksize', parseInt(this.value, 10));
		});

		$('#blur').click(
				function() {
					applyFilter(9, this.checked
							&& new f.Convolute({
								matrix : [ 1 / 9, 1 / 9, 1 / 9, 1 / 9, 1 / 9,
										1 / 9, 1 / 9, 1 / 9, 1 / 9 ]
							}));
				});

		$('#sharpen').click(function() {
			applyFilter(10, this.checked && new f.Convolute({
				matrix : [ 0, -1, 0, -1, 5, -1, 0, -1, 0 ]
			}));
		});

		$('#emboss').click(function() {
			applyFilter(11, this.checked && new f.Convolute({
				matrix : [ 1, 1, 1, 1, 0.7, -1, -1, -1, -1 ]
			}));
		});

		$('#tint')
				.click(
						function() {
							applyFilter(
									12,
									this.checked
											&& new f.Tint(
													{
														color : document
																.getElementById('tint-color').value,
														opacity : parseFloat(document
																.getElementById('tint-opacity').value)
													}));
						});

		$('#tint-color').change(function() {
			applyFilterValue(12, 'color', this.value);
		});

		$('#tint-opacity').change(function() {
			applyFilterValue(12, 'opacity', parseFloat(this.value));
		});

		$('#multiply').click(function() {
			applyFilter(13, this.checked && new f.Multiply({
				color : document.getElementById('multiply-color').value
			}));
		});

		$('#multiply-color').change(function() {
			applyFilterValue(13, 'color', this.value);
		});

		$('#blend').click(function() {
			applyFilter(14, this.checked && new f.Blend({
				color : document.getElementById('blend-color').value,
				mode : document.getElementById('blend-mode').value
			}));
		});

		$('#blend-mode').change(function() {
			applyFilterValue(14, 'mode', this.value);
		});

		$('#blend-color').change(function() {
			console.log(this.value);
			applyFilterValue(14, 'color', this.value);
		});

	})();

	// ------------------------滤镜相关------------------------

	// 初始化衣服背景
	(function() {

		// 显示背面
		$('#back-this').click(function() {

			var self = this;

			$(this).animateCss('fadeOutRight', function() {

				$(self).css({
					display : 'none'
				});

				$('#front-this').show();

			});

			$('#front-this').show();
			$('#front-this').animateCss('fadeInLeft');

			displayCloths('../img/cloths/back.jpg');
			isShowBack = true;

		});

		// 显示正面
		$('#front-this').click(function() {

			var self = this;

			$(this).animateCss('fadeOutLeft', function() {

				$(self).css({
					display : 'none'
				});

				$('#back-this').show();

			});

			$('#back-this').show();
			$('#back-this').animateCss('fadeInRight');

			displayCloths('../img/cloths/front.jpg');
			isShowBack = false;

		});

	})();

	$('#trends, #pictures').height($(document).height() - 44).css({
		'z-index' : 65530
	});

	var drawingOptionsEl = $('#drawing-mode-options'), drawingColorEl = $('#drawing-color'), drawingShadowColorEl = $('#drawing-shadow-color'), drawingLineWidthEl = $('#drawing-line-width'), drawingShadowWidth = $('#drawing-shadow-width'), drawingShadowOffset = $('#drawing-shadow-offset');

	var toolPanelIsShow = false;
	// 定制页面底部菜单
	var startToolPanelAnimation = function(open) {

		var displayControlBtn = isShowBack ? 'block' : 'none';

		if (toolPanelIsShow) {
			$('#' + open).animateCss('fadeOutDown', function() {
				$('#' + open).hide();
			});

			fixTilebarTightBottomPos(100, function() {

				$('#front-this').css({
					left : 15,
					display : displayControlBtn
				});

			});

			$('#' + open).removeClass('active');
			toolPanelIsShow = false;
		} else {
			fixTilebarTightBottomPos(340, function() {

				$('#front-this').css({
					left : 15,
					display : displayControlBtn
				});

			});
			$('#' + open).show();
			$('#' + open).animateCss('fadeInUp');
			toolPanelIsShow = true;
			$('#' + open).addClass('active');
		}

	};

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

		$(document).on('click', '#colors .card-cover', function() {

			if ($(this).hasClass('cloth-chosen')) {
				return false;
			}

			fabric.Image.fromURL($(this).attr('src'), function(img) {

				img.scale(1).set({
					left : 150,
					top : 150,
					angle : -15
				});

				canvas.add(img).setActiveObject(img);

			});

		});

		// $('#colors .card-cover').click(function() {

		// // fabric.util.loadImage($(this).attr('src'), function(img) {
		// // cloth.fill = new fabric.Pattern({
		// // source: img,
		// // repeat: 'repeat'
		// // });
		// // canvas.renderAll();
		// // });

		// });

	})();

	// 更换图案
	(function() {

		$(document).on('click', '.pattern-wall img', function() {

			if ($(this).hasClass('cloth-chosen')) {
				return false;
			}

			fabric.Image.fromURL($(this).attr('src'), function(img) {

				img.scale(1).set({
					left : 150,
					top : 150,
					angle : -15
				});

				canvas.add(img).setActiveObject(img);

				// 点击物体后加入删除按钮
				img.on('moving', function(e) {
					positionBtn(img);
				});

				positionBtn(img);

			});

			if ($('.diytool-panel.active').length > 0) {
				startToolPanelAnimation($('.diytool-panel.active').attr('id'));
			}

		});

	})();

	$('.diytool-panel').find('.weui_bar_item_on').removeClass(
			'weui_bar_item_on');

	(function() {

		// 款式选择
		$(document).on(
				'click',
				'#clothes .card-cover.cloth-chosen',
				function() {

					$('#clothes .card-cover.cloth-chosen.active').removeClass(
							'active');
					$(this).addClass('active');
					displayCloths($(this).attr('src'));

				});

	})();

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
		img.src = '../img/honey_im_subtle.png';

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

});
