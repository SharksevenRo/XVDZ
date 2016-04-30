document.body.setAttribute('ontouchstart', '');

$(function(){
	
	//----------------------默认数据初始化----------------------

	localStorage.currentPageIndex = localStorage.currentPageIndex || 1;

	if(localStorage.currentPageIndex == '2') {
		localStorage.currentPageIndex = localStorage.prevPageIndex;
	}

  if(localStorage.currentPageIndex == '3') {
    $('.weui_tabbar').slideToggle();
    $('#diy-tools').slideUp();
  }

	localStorage.currentTheme = localStorage.currentTheme || '';

	localStorage.searchContent = localStorage.searchContent || '';

	//----------------------默认数据初始化----------------------

  $.fn.extend({
      animateCss: function (animationName, cb) {
          var animationEnd = 'webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend';
          $(this).addClass('animated ' + animationName).one(animationEnd, function() {
              $(this).removeClass('animated ' + animationName);
              if(cb) {
                cb();                
              }
          });
      }
  });

	//初始化主题
	$('body').attr('class', localStorage.currentTheme);

	//初始化地步菜单栏
    $('.weui_tab').tab();

    //幻灯片
	var mySwiper = new Swiper('.swiper-container', {
		autoplay: 5000,
		pagination: '.swiper-pagination',
        paginationClickable: true
	});

	//初始化列表事件
	$(document).on('click','.list-block ul li', function () {
		if($(this).hasClass('item-link')) {
			window.location.href = $(this).attr('id') + '.html';
		}
	});

	$('.back').click(function() {
		window.history.go(-1);
	});

	$('header button.button-nav').click(function() {
		window.location.href = $(this).attr('id') + '.html';
	});

	//主页菜单栏点击事件，保存上一个活跃的菜单和当前活跃菜单
	$('.weui_tabbar_item').click(function() {

		localStorage.prevPageIndex = localStorage.currentPageIndex;

		localStorage.currentPageIndex = $(this).index() + 1;

		if(localStorage.currentPageIndex == '2') {
			window.location.href = 'voice.html';
		}

    if(localStorage.currentPageIndex == '3') {
      $('.weui_tabbar').slideUp();
      $('#diy-tools').slideDown();
    }

	});

	$('.weui_tabbar_item:nth-child(' + localStorage.currentPageIndex + ')').click();

	//退出事件
	$(document).on('click','#logout', function () {
	 	$.weui.confirm('确认退出?', function () {
      console.log('确认');
    }, function (){
      console.log('取消');
    });
	});

	//砖到购物车页面
  $('#toMarket').click(function() {
		$('.weui_tabbar_item:nth-child(4)').click();
  });

	//主题配置事件
	$(document).on('click', '#theme-chosen ul li', function() {
		var id = $(this).attr('id');
		id = id == 'theme-default' ? '' : id;
		$('body').attr('class', id);
		localStorage.currentTheme = id;
	})

	//购物车去付款事件
	$('#to_pay').click(function() {
		window.location.href = 'pay.html';
	});

	//主页搜索框事件
	$('#search_input').keydown(function(evt) {

		if(evt.keyCode == 13) {
  		if($(this).val() == '') {
		 	$.weui.alert('请输入搜索内容');
				return false;
  		}
  		localStorage.searchContent = $(this).val();
			window.location.href = 'search.html';
		}

	});

	//搜索页面搜索框事件
	$('#search').val(localStorage.searchContent);
	localStorage.searchContent = '';
	$('#search').keydown(function(evt) {

		//这里写搜索事件
		if(evt.keyCode == 13) {
  		if($(this).val() == '') {
		 	$.weui.alert('请输入搜索内容');
				return false;
  		}
  		localStorage.searchContent = $(this).val();
  		//ajax异步请求开始
		}

	});

	//搜索页面评论事件
	$(document).on('click', '.card-footer a.leave-a-message', function(ev) {

  	$('#comment').show();

  	$('#cancel').click(function() {
  		$('#comment').hide();
  	});

  	$('#confirm').click(function() {

  		//ajax 提交写在这里

  		$('#comment').hide();
  	});

	});

	//商品列表点击事件

	$(document).on('mousedown', '.facebook-card .card-content', function(ev) {

		if($(this).next().find('.leave-a-message').length > 0) {
			$(this).next().find('.card-footer').attr('style', 'background: rgb(238, 238, 238)');
		}

	});

	$(document).on('mouseup', '.facebook-card .card-content', function(ev) {

    // console.log($(this).next().find('.leave-a-message').length);

		if($(this).next().find('.leave-a-message').length > 0) {
			$(this).next().find('.card-footer').attr('style', 'background: rgb(250, 250, 250)');

			window.location.href = 'goods_detail.html';

		}

	});

	//修正上方按钮位置
	var titleBarRight = $('.title-bar.right');
	for (var i = 0; i < titleBarRight.length; i++) {
		var curr = titleBarRight[i];
		$(curr).attr('style', 'left: ' + parseInt($(window).width() - (55 * (i + 1))) + 'px');
	};

  //修正定制页面按钮位置
  var fixTilebarTightBottomPos = function(num) {

    num = num || 100;

    var titleBarRightBottom = $('.title-bar.right-bottom');
    for (var i = 0; i < titleBarRightBottom.length; i++) {
      var curr = titleBarRightBottom[i];
      $(curr).attr('style', 'left: ' + parseInt($(window).width() - 55) + 'px;top:' + parseInt($(window).height() - (55 * i) - num) + 'px');
    };

    $('.title-bar.right-bottom').animateCss('fadeInDown');

  }

  fixTilebarTightBottomPos();

  //titlebar点击事件
  $('.title-bar.blue').mousedown(function() {

    $(this).attr('style', $(this).attr('style') + ';color:rgb(143, 143, 146);background:rgba(117, 147, 213, 0.4)');

  });

  $('.title-bar.blue').mouseup(function() {

    $(this).attr('style', $(this).attr('style') + ';color:rgb(255, 255, 255);background:rgba(117, 147, 213, 0.8)');

  });

	$('#go-back').click(function() {
		window.history.go(-1);
	});

  //定制页面左上角按钮事件
  $('#back-prev-menu').click(function() {
    $('.weui_tabbar').slideToggle();
    $('.weui_tabbar_item:nth-child(' + 1 + ')').click();
  });

  //提现按钮事件
  $('#topup').click(function() {

    $('#top-up-form').show();

    $('#cancel').click(function() {
      $('#top-up-form').hide();
    });

    $('#confirm').click(function() {

      //ajax 提交写在这里

      $('#top-up-form').hide();
    });

    $('#withdrawall').click(function() {

      $('#withdraw-balance').val($('#balance-had').html());

      return false;

    });

  });

  //------------------------------------------定制页面控制逻辑------------------------------------------

  var toolPanelIsShow = false;
  //定制页面底部菜单
  var startToolPanelAnimation = function(open) {

    if(toolPanelIsShow) {
      $('#' + open).animateCss('fadeOutDown', function() {
        $('#' + open).hide();
      });
      fixTilebarTightBottomPos(100);
      $('#' + open).removeClass('active');
      console.log($('#' + open));
      toolPanelIsShow = false;
    }else {
      fixTilebarTightBottomPos(320);
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

    if(sessionStorage.currentTool == sessionStorage.preTool) {
      startToolPanelAnimation(open);
      $(this).removeClass('active');
      return false;
    }

    if($('.diytool-panel.active').length > 0) {

      $('.diytool-panel.active').animateCss('fadeOutDown');
      $('.diytool-panel.active').hide();
      $('.diytool-panel.active').removeClass('active');
      $('#' + sessionStorage.preTool).removeClass('animated');
      $('#' + sessionStorage.preTool).removeClass('fadeOutDown');

      toolPanelIsShow = false;

      startToolPanelAnimation(open);

    }else {
      startToolPanelAnimation(open);
    }

  });

  $('#down-toolpanel').click(function() {
    if($('.diytool-panel.active').length > 0) {
      startToolPanelAnimation($('.diytool-panel.active').attr('id'));      
    }
  });

  var canvas = new fabric.Canvas('c');
  fabric.Image.fromURL('../img/icon_nav_progress.png', function(img) {
    img.scale(0.5).set({
      left: 150,
      top: 150,
      angle: -15
    });
    canvas.add(img).setActiveObject(img);
  });

  var info = document.getElementById('info');

  canvas.on({
    'touch:gesture': function() {
      info.insertBefore(text, info.firstChild);
    },
    'touch:drag': function() {
      info.insertBefore(text, info.firstChild);
    },
    'touch:orientation': function() {
      info.insertBefore(text, info.firstChild);
    },
    'touch:shake': function() {
      info.insertBefore(text, info.firstChild);
    },
    'touch:longpress': function() {
      info.insertBefore(text, info.firstChild);
    }
  });

  //------------------------------------------定制页面控制逻辑------------------------------------------

});

$(function(){

  var max = 200;
  $('#textarea').on('input', function(){
     var text = $(this).val();
     var len = text.length;
    
     $('#count').text(len);
    
     if(len > max){
       $(this).closest('.weui_cell').addClass('weui_cell_warn');
     }
     else{
       $(this).closest('.weui_cell').removeClass('weui_cell_warn');
     }
     
  });

})
