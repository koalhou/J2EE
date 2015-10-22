(function( $, undefined ) {
  $.mk_parser = {
    auto: true,
    onComplete: function(context) {},
    plugins: ['linkbutton', 'menu'],
    parse: function(context) {
      var aa = [];
      for (var i = $.mk_parser.plugins.length - 1; i >= 0; i--) {
        var name = $.mk_parser.plugins[i];
        console.log('name->' + name);
        var r = $('.mk-' + name, context);
        console.log('r.length->' + r.length);
        if (r.length) {
          console.log('r[name]?->' + (r[name] ? 'true' : 'false'));
          if (r[name]) {
            r[name]();
          } else {
            aa.push({name: name, jq: r});
          }
        }
      }

      if (aa.length && window.easyloader) {
        var names = [];
        for (var i = aa.length - 1; i >= 0; i--) {
          names.push(aa[i].name);
        }
        console.log(names);
        // easyloader.load
      } else {
        $.mk_parser.onComplete.call($.mk_parser, context);
      }
    },
    parse_options: function(target, attrs) {
      var elm = $(target);
      var opts = {};
      var data = $.trim(elm.attr('data-options'));
      if (data) {
        var data_s = data.substring(0, 1);
        var data_e = data.substring(data.length - 1);
        if (data_s != "{") {
          data = "{" + data;
        }
        if (data_e != "}") {
          data = data + "}";
        }
        opts = (new Function("return " + data))();
      }
      if (attrs) {
        var attr_opts = {};
        for (var i = 0; i < attrs.length; i++) {
          var attr = attrs[i];
          if (typeof attr == 'string') {
            if (attr == 'width' || attr == 'height' || attr == 'left' || attr == 'top') {
              attr_opts[attr] = parseInt(target.style[attr]) || undefined;
            } else {
              attr_opts[attr] = elm.attr(attr);
            }
          } else {
            for (var key in attr) {
              var val = attr[key];
              if (val == 'boolean') {
                attr_opts[key] = elm.attr(key) ? (elm.attr(key) == 'true') : undefined;
              } else {
                if (val == 'number') {
                  attr_opts[key] = elm.attr(key) == '0' ? 0 : parseFloat(elm.attr(key)) || undefined;
                }
              }
            }
          }
        }

        $.extend(opts, attr_opts);
      }

      return opts;
    }
  };

  $(function() {
    if (!window.easyloader && $.mk_parser.auto) {
      // $.mk_parser.parse();
    }
  });

  $.fn._outer_width = function(width) {
    return this.each(function() {
      if (!$.boxModel && $.browser.msie) {
        $(this).width(width);
      } else {
        $(this).width(width - ($(this).outerWidth() - $(this).width()));
      }
    });
  };

  $.fn._outer_height = function(height) {
    return this.each(function() {
      if (!$.boxModel && $.browser.msie) {
        $(this).height(height);
      } else {
        $(this).height(height - ($(this).outerHeight() - $(this).height()));
      }
    });
  };
})( jQuery );