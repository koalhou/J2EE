/**
 *  |- 布局
 *  |- mk.panel.js
 *  |- Panel
 *  |- 参数： 
 *    |- top: 弹出层相距页面顶部距离，默认为100px
 *    |- width: 弹出层宽度，默认为500px
 *    |- height: 弹出层高度，默认为500px
 *    |- url: 弹出层欲加载的页面地址，默认为空
 *    |- auto_hide: 是否当鼠标离开自动隐藏
 *  |- 依赖： 
 *    |- Parser: mk.parser.js
 *  |- 2012-08-08
 *  |- Author: <a mailto="hegq@neusoft.com">Puras.He</a>
 */
(function( $, undefined ) {

  function remove_node(node) {
    node.each(function() {
      $(this).remove();
      if ($.browser.msie) {
        this.outerHTML = "";
      }
    });
  }

  function _3(_4, _5) {
    var _6 = $.data(_4, 'mk-panel').options;
    var _7 = $.data(_4, 'mk-panel').panel;
    var _8 = _7.children('div.mk-panel-header');
    var _9 = _7.children('div.mk-panel-body');
    if (_5) {
      if (_5.width) _6.width = _5.width;
      if (_5.height) _6.height = _5.height;
      if (_5.left != null) _6.left = _5.left;
      if (_5.top != null) _6.top = _5.top;
    }

    if (_6.fit == true) {
      var p = _7.parent();
      p.addClass('mk-panel-noscroll');
      if (p[0].tagName == 'BODY') {
        $('html').addClass('mk-panel-fit');
      }
      _6.width = p.width();
      _6.height = p.height();
    }

    _7.css({
      left: _6.left,
      top: _6.top
    });
    if (!isNaN(_6.width)) {
      _7._outer_width(_6.width);
    } else {
      _7.width('auto');
    }
    _8.add(_9)._outer_width(_7.width());
    if (!isNaN(_6.height)) {
      _7._outer_width(_6.height);
      _9._outer_width(_7.height() - _8._outer_width());
    } else {
      _9.height('auto');
    }
    _7.css('height', '');
    _6.on_resize.apply(_4, [_6.width, _6.height]);
    _7.find('>div.mk-panel-body>div').triggerHandler('_resize');
  }

  function wrap_panel(target) {
    $(target).addClass('mk-panel-body');
    var panel_parent = $('<div class="mk-panel-parent"></div>').insertBefore(target);
    panel_parent[0].appendChild(target);
    panel_parent.bind('_resize', function() {
      console.log('resize');
      var opts = $.data(target, 'mk-panel').options;
      if (opts.fit == true) {
        _3(target);
      }
      return false;
    });
    return panel_parent;
  }

  function add_header(target) {
    var opts = $.data(target, 'mk-panel').options;
    var panel = $.data(target, 'mk-panel').panel;
    if (opts.tools && typeof opts.tools == 'string') {
      panel.find('>div.mk-panel-header>div.mk-panel-tool .mk-panel-tool-a').appendTo(opts.tools);
    }
    remove_node(panel.children('div.mk-panel-header'));
    if (opts.title && !opts.noheader) {
      var panel_header = $('<div class="mk-panel-header"><div class="mk-panel-title">' + 
        opts.title + '</div></div>').prependTo(panel);

      if (opts.icon_cls) {
        panel_header.find('.mk-panel-title').addClass('mk-panel-with-icon');
        $('<div cass="mk-panel-icon"></div>').addClass(opts.icon_cls).appendTo(panel_header);
      }

      var panel_tool = $('<div class="mk-panel-tool"></div>').appendTo(panel_header);
      panel_tool.bind('click', function(e) {
        e.stopPropagation();
      });

      if (opts.tools) {
        if (typeof opts.tools == 'string') {
          $(opts.tools).children().each(function() {
            $(this).addClass($(this).attr('icon_cls')).addClass('mk-panel-tool-a').appendTo(panel_tool);
          });
        } else {
          for (var i = 0; i < opts.tools.length; i++) {
            var t = $('<a href="javascript:void(0)"></a>')
              .addClass(opts.tools[i].icon_cls).appendTo(panel_tool);
            if (opts.tools[i].handler) {
              t.bind('click', eval(opts.tools[i].handler));
            }
          }
        }
      }
      if (opts.collapsible) {
        $('<a class="mk-panel-tool-collapse" href="javascript:void(0)"></a>')
          .appendTo(panel_tool).bind('click', function() {
            if (opts.collapsed == true) {
              _39(target, true);
            } else {
              _29(target, true);
            }
            return false;
          });
      }
      if (opts.minimizable) {
        $('<a class="mk-panel-tool-min" href="javascript:void(0)"></a>')
          .appendTo(panel_tool).bind('click', function() {
            _44(target);
            return false;
          });
      }
      if (opts.maximizable) {
        $('<a class="mk-panel-tool-max" href="javascript:void(0)"></a>')
          .appendTo(panel_tool).bind('click', function() {
            if (opts.maximized == true) {
              _48(target);
            } else {
              _28(target);
            }
            return false;
          });
      }
      if (opts.closable) {
        $('<a class="mk-panel-tool-close" href="javascript:void(0)></a>')
          .appendTo(panel_tool).bind('click', function() {
            _19(target);
            return false;
          });
      }
      panel.children('div.mk-panel-body').removeClass('mk-panel-body-noheader');
    } else {
      panel.children('div.mk-panel-body').addClass('mk-panel-body-noheader');
    }
  }

  function set_border(target) {
    var opts = $.data(target, 'mk-panel').options; // 取到各个参数
    var panel = $.data(target, 'mk-panel').panel; // 取到主面板
    // 通过mk_panel('header')来调用该UI组件的header方法，获取到头部元素
    var header = $(target).mk_panel('header'); // 取到面板的头部
    var body = $(target).mk_panel('body'); // 取到面板的主体
    panel.css(opts.style); // 对面板应用参数中设置的样式
    panel.addClass(opts.cls); // 对面板应用参数中设置的样式class
    // 根据参数中的设置，来增加/删除noborder样式
    if (opts.border) { 
      header.removeClass('mk-panel-header-noborder');
      body.removeClass('mk-panel-body-noborder');
    } else {
      header.addClass('mk-panel-header-noborder');
      body.addClass('mk-panel-body-noborder');
    }
    header.addClass(opts.header_cls); // 为面板中的头部设置参数中相应的样式
    body.addClass(opts.body_cls); // 为面板中的主体设置参数中相应的样式
    // 如果参数中设置了id，则为目标对象设置参数id，否则取消目标对象的id
    if (opts.id) {
      $(target).attr('id', opts.id);
    } else {
      $(target).attr('id', '');
    }
  }

  $.fn.mk_panel = function(options, param) {
  	if (typeof options == 'string') {
  		return $.fn.mk_panel.methods[options](this, param);
  	}

  	options = options || {};

  	return this.each(function() {
      console.log('test');
  		var state = $.data(this, 'mk-panel');
  		var opts;
  		if (state) {
  			opts = $.extend(state.options, options);
  		} else {
  			opts = $.extend({}, $.fn.mk_panel.defaults, $.fn.mk_panel.parse_options(this), options);
  			$(this).attr('title', '');
  			state = $.data(this, 'mk-panel', {
  				options: opts,
  				panel: wrap_panel(this),
  				is_loaded: false
  			});
  		}

  		if (opts.content) {
  			$(this).html(opts.content);
  			if ($.mk_parser) {
  				$.mk_parser.parse(this);
  			}
  		}

  		add_header(this);
  		set_border(this);

  		if (opts.do_size == true) {
  			state.panel.css('display', 'block');
  			_3(this);
  		}

  		if (opts.closed == true || opts.minimized == true) {
  			state.panel.hide();
  		} else {
  			// _23(this);
  		}
  	});
  };

  $.fn.mk_panel.parse_options = function(target) {
    var elm = $(target);
    return $.extend({}, $.mk_parser.parse_options(target, ['id', 'width', 'height', 
      'left', 'top', 'title', 'icon_cls', 'cls', 'header_cls', 'body_cls', 'tools',
      'href', {
        cache: 'boolean',
        fit: 'boolean',
        border: 'boolean',
        noheader: 'boolean'
      }, {
        collapsible: 'boolean',
        minimizable: 'boolean',
        maximizable: 'boolean'
      }, {
        closable: 'boolean',
        collapsed: 'boolean',
        minimized: 'boolean',
        maximized: 'boolean',
        closed: 'boolean'
      }]), {
      loading_message: (elm.attr('loading_message') != undefined ? 
        elm.attr('loading_message') : undefined)
    });
  };

  $.fn.mk_panel.methods = {
    options: function(elm) {
      return $.data(elm[0], 'mk-panel').options;
    },
    panel: function(elm) {
      return $.data(elm[0], 'mk-panel').panel;
    },
    header: function(elm) {
      return $.data(elm[0], 'mk-panel').panel.find('>div.mk-panel-header');
    },
    body: function(elm) {
      return $.data(elm[0], 'mk-panel').panel.find('>div.mk-panel-body');
    },
    set_title: function() {},
    open: function() {},
    close: function() {},
    destory: function() {},
    refresh: function() {},
    resize: function() {},
    move: function() {},
    maximize: function() {},
    minimize: function() {},
    restore: function() {},
    collapse: function() {},
    expand: function() {}
  };

  $.fn.mk_panel.defaults = {
    id: null,
    title: null,
    icon_cls: null,
    width: 'auto',
    height: 'auto',
    left: null,
    top: null,
    header_cls: null,
    body_cls: null,
    style: {},
    href: null,
    cache: true,
    fit: false,
    border: true,
    do_size: true,
    noheader: false,
    content: null,
    collapsible: false,
    minimizable: false,
    maximizable: false,
    closable: false,
    collapsed: false,
    minimized: false,
    maximized: false,
    closed: false,
    tools: null,
    loading_message: 'Loading...',
    on_resize: function() {}
  };
})( jQuery );