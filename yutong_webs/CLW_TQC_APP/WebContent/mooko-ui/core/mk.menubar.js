/**
 *  |- 菜单栏
 *  |- mk.menubar.js
 *  |- MenuBar
 *  |- 参数： 
 *    |- plain:
 *    |- menu: 
 *    |- duration:
 *    |- arrow:
 *  |- 依赖： 
 *    |- Parser: mk.parser.js
 *    |- LinkButton: mk.linkbutton.js
 *    |- Menu: mk.menu.js
 *  |- 2012-08-04
 */
(function( $, undefined ) {
  
  function init(target) {
    var opts = $.data(target, 'mk-menubar-item').options;
    var btn = $(target);
    btn.removeClass('m-btn-active m-btn-plain-active').addClass('m-btn');
    btn.mk_linkbutton($.extend({}, opts, {
      text: opts.text + (opts.arrow ? '<span class="m-btn-downarrow">&nbsp;</span>' : '')
    }));

    if (opts.menu) {
      $(opts.menu).mk_menu({
        on_show: function() {
          btn.addClass((opts.plain == true) ? 'm-btn-plain-active' : 'm-btn-active');
        },
        on_hide: function() {
          btn.removeClass((opts.plain == true) ? 'm-btn-plain-active' : 'm-btn-active');
        }
      });
    }

    btn.bind('mouseleave', function(e) {
      if (opts.menu) {
        var mi = $(opts.menu);
        
        if (!((e.pageX >= mi.offset().left && e.pageX <= mi.offset().left + mi.width()) 
          && (e.pageY >= mi.offset().top && e.pageY <= mi.offset().top + mi.height()))) {
          $(opts.menu).mk_menu('hide');
        }
      }
    });

    set_disabled(target, opts.disabled);
  }

  function set_disabled(target, disabled) {
    var opts = $.data(target, 'mk-menubar-item').options;
    opts.disabled = disabled;
    var btn = $(target);
    if (disabled) {
      btn.mk_linkbutton('disabled');
      btn.unbind('.mk-menubar-item');
    } else {
      btn.mk_linkbutton('enable');
      btn.unbind('.mk-menubar-item');
      btn.bind('click.mk-menubar-item', function() {
        show_menu();
        return false;
      });
      var timeout = null;
      btn.bind('mouseenter.mk-menubar-item', function() {
        timeout = setTimeout(function() {
          show_menu();
        }, opts.duration);
        return false;
      }).bind('mouseleave.mk-menubar-item', function() {
        if (timeout) {
          clearTimeout(timeout);
        }
      });
    }

    function show_menu() {
      if (!opts.menu) {
        return ;
      }
      var left = btn.offset().left;
/**
 * start
 * 2012-11-09，2012-11-23
 * zhaoyong.neu
 * 修复顶部二级菜单定位问题，见bug：1575,修正最右侧菜单在有纵向滚动条时弹出影响视窗
 */
//      if (left + $(opts.menu).outerWidth() + 5 > $(window).width()) {
//          left = $(window).width() - $(opts.menu).outerWidth() - 5;
//        }
      if (left + $(opts.menu).outerWidth() + 5 > $.AllWidth()) {
    	  left = $.AllWidth() - $(opts.menu).outerWidth() - 5;
       
      }
/**
 * end
 * 2012-11-09
 * zhaoyong.neu
 */
      $('body>div.mk-menu-top').mk_menu('hide');
      $(opts.menu).mk_menu('show', {
        left: left,
        top: btn.offset().top + btn.outerHeight()
      });
      btn.blur();
    };
  }

  $.fn.mk_menubar = function(options, _34c) {
	/*
	 * start
	 * 2012-11-23
	 * zhaoyong.neu
	 * 修正最右侧菜单在有纵向滚动条时弹出影响视窗
	 */
	var isCompat=document.compatMode == "CSS1Compat";
    jQuery.extend({
  	  AllWidth:function(){var w=isCompat?document.documentElement.scrollWidth:document.body.scrollWidth;return Math.max(w,$(window).width());}
  	 ,AllHeight:function(){var h=isCompat?document.documentElement.scrollHeight:document.body.scrollHeight;return Math.max(h,$(window).height());}  
    });
    /*
     * end
     * 2012-11-23
	 * zhaoyong.neu
     */
    if (typeof options == 'string') {
      return $.fn.mk_menubar.methods[options](this, _34c);
    }

    options = options || {};
   
   
    return this.each(function() {
      var state = $.data(this, 'mk-menubar-item');
      if (state) {
        $.extend(state.options, options);
      } else {
        $.data(this, 'mk-menubar-item', {
          options: $.extend({}, $.fn.mk_menubar.defaults, $.fn.mk_menubar.parse_options(this), options)
        });
        $(this).removeAttr('disabled');
      }

      init(this);
    });
  };

  $.fn.mk_menubar.methods = {
    options: function(jq) {},
    enable: function(jq) {},
    disable: function(jq) {},
    destroy: function(jq) {}
  };

  $.fn.mk_menubar.parse_options = function(target) {
    // var t = $(target); // 没有用到
    return $.extend({}, $.fn.mk_linkbutton.parse_options(target), 
      $.mk_parser.parse_options(target, ['menu', {
        plain: 'boolean',
        duration: 'number',
        arrow: 'boolean'
      }]));
  };

  $.fn.mk_menubar.defaults = $.extend({}, $.fn.mk_linkbutton.defaults, {
    plain: true,
    menu: null,
    duration: 100,
    arrow: false
  });
})( jQuery );