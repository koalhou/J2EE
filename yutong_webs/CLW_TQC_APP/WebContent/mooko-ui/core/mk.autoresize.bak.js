/**
 *  |- 侧边栏图层
 *  |- mk.autoresize.js
 *  |- AutoResize
 *  |- 参数： 
 *    |- width_exclude: 弹出层相距页面顶部距离，默认为100px
 *    |- width_include: 弹出层宽度，默认为500px
 *    |- width_offset: 弹出层高度，默认为500px
 *    |- height_exclude: 弹出层欲加载的页面地址，默认为空
 *    |- height_include: 是否当鼠标离开自动隐藏
 *    |- height_offset
 *  |- 依赖： 
 *    |- Parser: mk.parser.js
 *  |- 2012-08-06
 *  |- Author: <a mailto="hegq@neusoft.com">Puras.He</a>
 */
(function( $, undefined ) {
  function init(target) {
    console.log('auto resize init');
    $('.wrapper').css('width', $(target).width());
    $(target).resize(__resize);
    $(target).resize();
  }

  function __resize(event) {
    var target = event.target;
    console.log('window->[' + $(target).width() + ', ' + $(target).height() + ']');
    var opts = $.data(target, 'mk-autoresize').options;
    var width = $(target).width(), height = $(target).height();
    console.log('default->[' + width + ', ' + height + ']');
    if (typeof opts.height_exclude == 'string') {
      height -= $(opts.height_exclude).height();
    } else {
      if (opts.height_exclude && opts.height_exclude.length > 0) {
        $.each(opts.height_exclude, function(idx) {
          var elm = opts.height_exclude[idx];
          height -= $(elm).height();
        });
      }
    }
    height = height - opts.height_offset;
    if (typeof opts.height_include == 'string') {
      $(opts.height_include).css('height', height);
    } else {
      if (opts.height_include && opts.height_include.length > 0) {
        $.each(opts.height_include, function(idx) {
          var elm = opts.height_include[idx];
          $(elm).css('height', height + 'px');
        })
      }
    }

    if (typeof opts.width_exclude == 'string') {
      width = width - $(opts.width_exclude).width();
    } else {
      if (opts.width_exclude && opts.width_exclude.length > 0) {
        $.each(opts.width_exclude, function(idx) {
          console.log('www-> ' + width);
          var elm = opts.width_exclude[idx];
          width = width - $(elm).width();
        });
      }
    }
    width = width - opts.width_offset;
    console.log('resize[' + width + ', ' + height + ']');
    if (typeof opts.width_include == 'string') {
      $(opts.width_include).css('width', width + 'px');
    } else {
      if (opts.width_include && opts.width_include.length > 0) {
        $.each(opts.width_include, function(idx) {
          var elm = opts.width_include[idx];
          $(elm).css('width', width + 'px');
        })
      }
    }
  }

  $.fn.mk_autoresize = function(options) {
    return this.each(function() {
      var state = $.data(this, 'mk-autoresize');
      if (state) {
        $.extend(state.options, options);
      } else {
        $.data(this, 'mk-autoresize', {
          options: $.extend({}, $.fn.mk_autoresize.defaults, $.fn.mk_autoresize.parse_options(this), options)
        });
      }

      init(this);
    });
  };

  $.fn.mk_autoresize.parse_options = function(target) {
    return $.extend({}, $.mk_parser.parse_options(target, []), {});
  };

  $.fn.mk_autoresize.defaults = {
    width_exclude: null,
    width_include: null,
    width_offset: 4,
    height_exclude: null,
    height_include: null,
    height_offset: 0
  };
})( jQuery );