/**
 *  |- 链接按钮
 *  |- mk.linkbutton.js
 *  |- LinkButton
 *  |- 参数：
 *    |- id: 
 *    |- disabled: 
 *    |- plain:
 *    |- text: 
 *    |- icon_cls
 *  |- 依赖： 
 *    |- Parser: mk.parser.js
 *  |- 2012-08-04
 */
(function( $, undefined ) {
  function create_button(target) {
    var opts = $.data(target, 'mk-linkbutton').options;
    $(target).empty();
    $(target).addClass('l-btn'); // 增加按钮右侧样式
    // 判断是否有ID
    if (opts.id) {
      $(target).attr('id', opts.id);
    } else {
      $(target).attr('id', '');
    }
    // 判断是否是立体按钮
    if (opts.plain) {
      $(target).addClass('l-btn-plain');
    } else {
      $(target).removeClass('l-btn-plain');
    }
    // 判断按钮是否有文字
    if (opts.text) {
      $(target).html(opts.text).wrapInner('<span class="l-btn-left"><span class="l-btn-text"></span></span>');
      // 判断是否有图标
      if (opts.icon_cls) {
        $(target).find('.l-btn-text').addClass(opts.icon_cls).css('padding-left', '20px');
      }
    } else { // 没有文字的情况下，直接放个空标签
      $(target).html('&nbsp;').wrapInner('<span class="l-btn-left"><span class="l-btn-text">' + 
        '<span class="l-btn-empty"></span></span></span>');
      // 判断是否有图标，图标样式加到空标签上
      if (opts.icon_cls) {
        $(target).find('.l-btn-empty').addClass(opts.icon_cls);
      }
    }

    // if (opts.events && opts.events.length > 0) {
    //   $.each(opts.events, function(idx) {
    //     console.log(this);
    //   });
    // }

    // 绑定获取焦点与失去焦点事件
    $(target).unbind('.mk-linkbutton').bind('focus.mk-linkbutton', function() {
      if (!opts.disabled) {
        $(this).find('span.l-btn-text').addClass('l-btn-focus');
      }
    }).bind('blur.mk-linkbutton', function() {
      $(this).find('span.l-btn-text').removeClass('l-btn-focus');
    });

    // 设置按钮状态(是否禁用)
    set_disabled(target, opts.disabled);
  }

  function set_disabled(target, disabled) {
    var opts = $.data(target, 'mk-linkbutton');
    if (disabled) {
      opts.options.disabled = true;
      var href = $(target).attr('href');
      if (href) {
        opts.href = href;
        $(target).attr('href', 'javascript:void(0);');
      }
      if (target.onclick) {
        opts.onclick = target.onclick;
        target.onclick = null;
      }
      $(target).addClass('l-btn-disabled');
    } else {
      opts.options.disabled = false;
      if (opts.href) {
        $(target).attr('href', opts.href);
      }
      if (opts.onclick) {
        target.onclick = opts.onclick;
        opts.onclick = null;
      }
      $(target).removeClass('l-btn-disabled');
    }
  }

  $.fn.mk_linkbutton = function(options, _68) {
    if (typeof options == 'string') {
      return $.fn.mk_linkbutton.methods[options](this, _68);
    }
    options = options || {};
    return this.each(function() {
      var state = $.data(this, 'mk-linkbutton');
      if (state) {
        $.extend(state.options, options);
      } else {
        $.data(this, 'mk-linkbutton', {
          options: $.extend({}, $.fn.mk_linkbutton.defaults, $.fn.mk_linkbutton.parse_options(this), options)
        });
        $(this).removeAttr('disabled');
      }

      create_button(this);
    });
  };

  $.fn.mk_linkbutton.methods = {
    options: function(jq) {
      return $.data(jq[0], 'mk-linkbutton').options;
    },
    enable: function(jq) {
      return jq.each(function() {
        set_disabled(this, false);
      });
    },
    disable: function(jq) {
      return jq.each(function() {
        set_disabled(this, true);
      });
    }
  };

  $.fn.mk_linkbutton.parse_options = function(target) {
    var elm = $(target);
    return $.extend({}, $.mk_parser.parse_options(target, ['id', 'icon_cls', {
      plain: 'boolean'
    }]), {
      disabled: (elm.attr('disabled') ? true : undefined),
      text: $.trim(elm.html()),
      icon_cls: (elm.attr('icon') || elm.attr('icon_cls'))
    });
  };

  $.fn.mk_linkbutton.defaults = {
    id: null,
    disabled: false,
    plain: false,
    text: '',
    icon_cls: null,
    events: null
  };
})( jQuery );