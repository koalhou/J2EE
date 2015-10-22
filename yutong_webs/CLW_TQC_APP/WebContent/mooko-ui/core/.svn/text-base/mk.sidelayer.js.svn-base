/**
 *  |- 侧边栏图层
 *  |- mk.sidelayer.js
 *  |- SideLayer
 *  |- 参数： 
 *    |- top: 弹出层相距页面顶部距离，默认为100px
 *    |- width: 弹出层宽度，默认为500px
 *    |- bar_width:
 *    |- height: 弹出层高度，默认为500px
 *    |
 *    |- url: 弹出层欲加载的页面地址，默认为空
 *    |- auto_hide: 是否当鼠标离开自动隐藏
    top: '100px',
    width: '500px',
    bar_width: '160px',
    height: '500px',
    bar_height: '56px',
    url: null,
    is_show: true,
    auto_hide: false,
    can_close: true,
    title: 'SideLayer'
 *  |- 依赖： 
 *    |- Parser: mk.parser.js
 *  |- 2012-08-06
 *  |- Author: <a mailto="hegq@neusoft.com">Puras.He</a>
 */
(function( $, undefined ) {
  function init(target) {
    // $(target).hide();
    // __close(target);
    // $(target).animate({
    //   'width': 0
    // }, function() {
    //   $(target).hide();
    //   $(target).find('.mk-sidelayer-tools').hide();
    //   $(target).find('.mk-sidelayer-content').hide();
    //   $(target).find('.mk-sidelayer-bar-close').hide();
    // });

    var opts = $.data(target, 'mk-sidelayer').options;
    if (!$(target).find('.mk-sidelayer-bar-title')[0]) {
      $(target).find('.mk-sidelayer-bar-btn').first().after($('<h1 class="mk-sidelayer-bar-title"></h1>').html(opts.title));
    }
    if (opts.can_close) {
      $(target).prepend($('<div class="mk-sidelayer-bar-close"></div>'));
    }

    if (opts.is_show) {
      __show(target);
    } else {
      	$(target).css("width",0);
//      $(target).animate({
//        'width': 0
//      }, function() {
        $(target).hide();
        $(target).find('.mk-sidelayer-tools').hide();
        $(target).find('.mk-sidelayer-content').hide();
        $(target).find('.mk-sidelayer-bar-close').hide();
//      });
    }

    $(target).find('.mk-sidelayer-bar-btn').bind('click.mk-sidelayer-bar-btn', function() {
      if (opts.is_show) {
        __hide(target);
      } else {
        __show(target);
      }
    });

    $(target).find('.mk-sidelayer-bar-close').bind('click.mk-sidelayer-bar-close', function() {
      __close(target);
    });

    $(target).css({
      'top': opts.top,
      'height': opts.height
    });
  }

  function __show(target) {
    var opts = $.data(target, 'mk-sidelayer').options;  
    opts.is_show = true;
    $(target).show();
    $(target).height(opts.height);
    $(target).animate({
      'width': opts.width
    }, function() {
      $(target).removeClass('mk-sidelayer-small');
      $(target).find('.mk-sidelayer-tools').show();
      $(target).find('.mk-sidelayer-content').show();
      $(target).find('.mk-sidelayer-bar-close').hide(); 
      __load(target, opts.url, opts.query_param, opts.reload_callback);
    });
  }

  function __hide(target) {
    $(target).show();
    var opts = $.data(target, 'mk-sidelayer').options;
    opts.is_show = false;
    
    $(target).animate({
      'width': opts.bar_width
    }, function() {    
    	
      $(target).height(opts.bar_height);
      $(target).addClass('mk-sidelayer-small');
      $(target).find('.mk-sidelayer-tools').hide();
      $(target).find('.mk-sidelayer-content').hide();
      $(target).find('.mk-sidelayer-bar-close').show();
      
      opts.hide_callback.call(); 
    });
  }

  function __close(target) {
    var opts = $.data(target, 'mk-sidelayer').options;
    $(target).animate({
      'width': 0
    }, function() {
      opts.is_show = false;
      $(target).hide('normal', opts.close_callback);
      $(target).find('.mk-sidelayer-tools').hide();
      $(target).find('.mk-sidelayer-content').hide();
      $(target).find('.mk-sidelayer-bar-close').hide();
    });
  }

  function __reload(target, url, query_param, callback) {
    var opts = $.data(target, 'mk-sidelayer').options;
    opts.url = url;
    opts.query_param = query_param;
    opts.reload_callback = callback;
    // opts.url = url;
    if (opts.is_show) {
      __load(target, url, query_param, callback);
    } else {
      __show(target);
    }
  }

  function __load(target, url, query_param, callback) {
    query_param = query_param || null;
    var content = $(target).find('.mk-sidelayer-content').first();
    if (content) {
      content.html($("<div class=\"mk-sidelayer-loading\"></div>").html('正在加载...'));
      if (url) {
        content.load(url, query_param, callback);
      }
    }
  }

  function __set_title(target, title) {
    var opts = $.data(target, 'mk-sidelayer').options;
    var datatitle = title.split(",");
    $(target).find('.mk-sidelayer-bar-title').first().html('<font size="5">'+getStrConvert(datatitle[0])+'</font><font size="3">('+datatitle[1]+')</font>');
  }
  
  //内容转换
  function getStrConvert(str){
	 var newStr=trim(str);
	 if(newStr == "外租"){
	 }else{
		newStr=newStr+"号"; 
	 }
	 return newStr;
  }
  //截取报告
  function trim(s){
	    return s.replace(/^\s*/, "").replace(/\s*$/, "");
  }

  function __set_width(target, width) {
	  var opts = $.data(target, 'mk-sidelayer').options;
	  opts.width = width;
  }
  
  function __set_height(target, height) {
	  var opts = $.data(target, 'mk-sidelayer').options;
	  opts.height = height;
  }
  
  $.fn.mk_sidelayer = function(options, param) {
    if (typeof options == 'string') {
      return $.fn.mk_sidelayer.methods[options](this, param);     
    }

    options = options || {};
    return this.each(function() {
      var state = $.data(this, 'mk-sidelayer');
      if (state) {
        $.extend(state.options, options);
      } else {
        $.data(this, 'mk-sidelayer', {
          options: $.extend({}, $.fn.mk_sidelayer.defaults, $.fn.mk_sidelayer.parse_options(this), options)
        });
      }

      init(this);
    });
  };

  $.fn.mk_sidelayer.parse_options = function() {};

  $.fn.mk_sidelayer.methods = {
    show: function(elm) {
      return elm.each(function() {
        __show(this);
      });
    },
    hide: function(elm) {
      return elm.each(function() {
        __hide(this);
      });
    },
    reload: function(elm, param) {
      return elm.each(function() {
        var url = null;
        var query_param = null;
        var callback = null;
        if (typeof param == 'string') url = param;
        else if (typeof param == 'object') {
          url = param.url;
          query_param = param.query_param;
          callback = param.callback;
        }
        __reload(this, url, query_param, callback);
      });
    },
    close: function(elm) {
      return elm.each(function() {
        __close(this);
      });
    },
    is_show: function(elm) {
      return $.data(elm[0], 'mk-sidelayer').options.is_show;
    },
    set_title: function(elm, title) {
      return elm.each(function() {
        __set_title(this, title);
      });
    },
    set_width: function(elm, width) {
        return elm.each(function() {
          __set_width(this, width);
        });
    },
    set_height: function(elm, height) {
        return elm.each(function() {
          __set_height(this, height);
        });
      }
  };

  $.fn.mk_sidelayer.defaults = {
	//top: '38px',//2011-11-09 zhaoyong.neu 修复弹出框随滚动条移动问题， 见bug:1601
    width: '500px',
    bar_width: '230px',//'160px',
    height: '500px',
    bar_height: '56px',
    url: null,
    query_param: null,
    reload_callback: function() {},
    close_callback: function() {},
    hide_callback: function() {},
    is_show: true,
    auto_hide: false,
    can_close: true,
    title: 'SideLayer'
  };
})( jQuery );