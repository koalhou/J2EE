/**
 *  |- 菜单
 *  |- mk.menu.js
 *  |- Menu
 *  |- 参数： 
 *    |- plain:
 *    |- menu: 
 *    |- duration:
 *    |- arrow:
 *  |- 依赖： 
 *    |- Parser: mk.parser.js
 *  |- 2012-08-04
 */
(function( $, undefined ) {
  // function init(_317) {
  function init(target) {
    $(target).appendTo('body');
    $(target).addClass('mk-menu-top');

    var menus = [];
    // 递归出菜单的子菜单
    adjust($(target));
    var time = null;
    for (var i = 0; i < menus.length; i++) {
      var menu = menus[i];
      // 为子菜单增加相应的样式(mk-menu-item等)
      wrap_menu(menu);
      menu.children('div.mk-menu-item').each(function() {
        _31e(target, $(this));
      });

      menu.bind('mouseenter', function() {
        if (time) {
          clearTimeout(time);
          time = null;
        }
      }).bind('mouseleave', function() {
        time = setTimeout(function() {
          hide_all(target);
        }, 100);
      });
    }

    function adjust(menu) {
      menus.push(menu);
      menu.find('>div').each(function() {
        var item    = $(this);
        var submenu = item.find('>div');
        if (submenu.length) {
          submenu.insertAfter(target);
          item[0].submenu = submenu;
          adjust(submenu);
        }
      });
    };

    function wrap_menu(menu) {
      menu.addClass('mk-menu').find('>div').each(function() {
        var item = $(this);
        // 如果是菜单分符
        if (item.hasClass('mk-menu-sep')) {
          item.html('&nbsp;');
        } else {
          var opts = $.extend({}, $.mk_parser.parse_options(this, ['name', 'icon_cls', 'href']), 
                              {disabled: (item.attr('disabled') ? true : undefined)});
          item.attr('name', opts.name || '').attr('href', opts.href || '');
          var text = item.addClass('mk-menu-item').html();
          item.empty().append($('<div class="mk-menu-text"></div>').html(text));
          if (opts.icon_cls) {
            $('<div class="mk-menu-icon"></div>').addClass(opts.icon_cls).appendTo(item);
          }
          if (opts.disabled) {
            _31d(target, item[0], true);
          }
          if (item[0].submenu) {
            $('<div class="mk-menu-rightarrow"></div>').appendTo(item);
          }
          // 针对宇通项目，对该值进行修改，从22修改成28
          // item._outer_height(22);
          item._outer_height(28);
        }
      });
      menu.hide();
    };
  }

  function _31e(_31f, item) {
    item.unbind('.mk-menu');
    item.bind('mousedown.mk-menu', function() {
      return false;
    }).bind('click.mk-menu', function() {
      // 如果是禁用菜单，不响应，直接返回
      if ($(this).hasClass('mk-menu-item-disabled')) {
        return ;
      }
      // 如果没有子菜单
      if (!this.submenu) {
        hide_all(_31f);
        var href = $(this).attr('href');
        if (href) {
          location.href = href;
        }
      }

      var item = $(_31f).mk_menu('get_item', this);
      $.data(_31f, 'mk-menu').options.on_click.call(_31f, item);
    }).bind('mouseenter.mk-menu', function(e) {
      item.siblings().each(function() {
        if (this.submenu) {
          hide_menu(this.submenu);
        }
        $(this).removeClass('mk-menu-active');
      });
      item.addClass('mk-menu-active');
      if ($(this).hasClass('mk-menu-item-disabled')) {
        item.addClass('mk-menu-active-disabled');
        return ;
      }
      var _320 = item[0].submenu;
      if (_320) {
        var left = item.offset().left + item.outerWidth() - 2;
        if (left + _320.outerWidth() + 5 > $(window).width() + $(document).scrollLeft()) {
          left = item.offset().left - _320.outerWidth() + 2;
        }
        var top = item.offset().top - 3;
        if (top + _320.outerHeight() > $(window).height() + $(document).scrollTop()) {
          top = $(window).height() + $(document).scrollTop() - _320.outerHeight() - 5;
        }

        show_menu(_320, {
          left: left,
          top: top
        });
      }
    }).bind('mouseleave.mk-menu', function(e) {
      item.removeClass('mk-menu-active mk-menu-active-disabled');
      var _321 = item[0].submenu;
      if (_321) {
        if (e.pageX >= parseInt(_321.css('left'), 10)) {
          item.addClass('mk-menu-active');
        } else {
          hide_menu(_321);
        }
      } else {
        item.removeClass('mk-menu-active');
      }
    });
  };

  function hide_all(target) {
    var opts = $.data(target, 'mk-menu').options;
    hide_menu($(target));
    $(document).unbind('.mk-menu');
    opts.on_hide.call(target);
    return false;
  }

  function show_top_menu(target, pos) {
    var opts = $.data(target, 'mk-menu').options;
    if (pos) {
      opts.left = pos.left;
      opts.top = pos.top;
/**
 * start
 * 2012-11-09
 * zhaoyong.neu
 * 修复顶部菜单弹出定位错误问题，见bug：1575
 */
//      if (opts.left + $(target).outerWidth() > $(window).width() + $(document).scrollLeft()) {
//        opts.left = $(window).width() + $(document).scrollLeft() - $(target).outerWidth() - 5;
//      }
//      if (opts.top + $(target).outerHeight() > $(window).height() + $(document).scrollTop()) {
//        opts.top -= $(target).outerHeight();
//      }
/**
 * end
 * 2012-11-09
 * zhaoyong.neu
 */
    }

    show_menu($(target), {
      left: opts.left,
      top: opts.top
    }, function() {
      $(document).unbind('.mk-menu').bind('mousedown.mk-menu', function() {
        hide_all(target);
        $(document).unbind('.mk-menu');
        return false;
      });
      opts.on_show.call(target);
    });
  }

  function show_menu(menu, pos, callback) {
    if (!menu) {
      return ;
    }
    
    if (pos) {
      menu.css(pos);
    }
    menu.show(0, function() {
      if (!menu[0].shadow) {
        menu[0].shadow = $('<div class="mk-menu-shadow"></div>').insertAfter(menu);
      }
      menu[0].shadow.css({
        display: 'block',
        z_index: $.fn.mk_menu.defaults.z_index++,
        left: menu.css('left'),
        top: menu.css('top'),
        width: menu.outerWidth(),
        height: menu.outerHeight()
      });
      menu.css('z-index', $.fn.mk_menu.defaults.z_index++);

      if (callback) {
        callback();
      }
    });
  }

  function hide_menu(menu) {
    if (!menu) {
      return ;
    }
    hideit(menu);
    menu.find('div.mk-menu-item').each(function() {
      if (this.submenu) {
        hide_menu(this.submenu);
      }
      $(this).removeClass('mk-menu-active');
    });

    function hideit(m) {
      m.stop(true, true);
      if (m[0].shadow) {
        m[0].shadow.hide();
      }
      m.hide();
    }
  }

  function _31d(_32d, _32e, _32f) {
    var t = $(_32e);
    if (_32f) {
      t.addClass('mk-menu-item-disabled');
      if (_32e.onclick) {
        _32e.onclick1 = _32e.onclick;
        _32e.onclick = null;
      }
    } else {
      t.removeClass('mk-menu-item-disabled');
      if (_32e.onclick1) {
        _32e.onclick = _32e.onclick1;
        _32e.onclick1 = null;
      }
    }
  }

  $.fn.mk_menu = function(options, param) {
    if (typeof options == 'string') {
      return $.fn.mk_menu.methods[options](this, param);
    }

    options = options || {};
    return this.each(function() {
      var state = $.data(this, 'mk-menu');
      if (state) {
        $.extend(state.options, options);
      } else {
        state = $.data(this, 'mk-menu', {
          options: $.extend({}, $.fn.mk_menu.defaults, $.fn.mk_menu.parse_options(this), options)
        });
        init(this);
      }
      $(this).css({
        left: state.options.left,
        top: state.options.top
      });
    });
  };

  $.fn.mk_menu.methods = {
    show: function(jq, pos) {
      return jq.each(function() {
        show_top_menu(this, pos);
      });
    },
    hide: function(jq) {
      return jq.each(function() {
        hide_all(this);
      });
    },
    destroy: function(jq) {},
    set_text: function(jq, _33d) {},
    set_icon: function(jq, _33e) {},
    get_item: function(jq, _33f) {},
    find_item: function(jq, text) {},
    append_item: function(jq, _340) {},
    remove_item: function(jq, _341) {},
    enable_item: function(jq, _342) {},
    disable_item: function(jq, _343) {}
  };

  $.fn.mk_menu.parse_options = function(target) {
    return $.extend({}, $.mk_parser.parse_options(target, ['left', 'top']));
  };

  $.fn.mk_menu.defaults = {
    z_index: 110000,
    left: 0,
    top: 0,
    on_show: function() {},
    on_hide: function() {},
    on_click: function(item) {}
  };
})( jQuery );