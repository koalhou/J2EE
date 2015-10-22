/**
 * |- 页面自适应 |- mk.autoresize.js |- AutoResize |- 参数： |- width_exclude:
 * 计算宽度时排除的元素，从主元素的宽度逐个减去此属性中的元素 |- width_include: 计算宽度时包含的元素，该属性中所有元素的宽度应该是相同的 |-
 * width_offset: 计算宽度时偏差值 |- min_width: 最小宽度 |- height_exclude:
 * 计算高度时排除的元素，从主元素的高度逐个减去此属性中的元素 |- height_include: 计算高度时包含的元素，该属性中所有元素的高度应该是相同的 |-
 * height_offset：计算高度时偏差值 |- min_height: 最小高度 |- 依赖： |- Parser: mk.parser.js |-
 * 2012-08-06 |- Author: <a mailto="hegq@neusoft.com">Puras.He</a>
 */
(function($, undefined) {
	function init(target) {
		if($.data(target, 'mk-autoresize')&&$.data(target, 'mk-autoresize').options) {
			var opts = $.data(target, 'mk-autoresize').options;
			if (opts.is_handler) {
				$(window).wresize(function() {
					__resize(target);
				});
			}
	
			// $(window).trigger('resize');
			__resize(target);
		}
	}

	function __resize(target) {
		// var target = event.target;
		if ($.data(target, 'mk-autoresize')&&$.data(target, 'mk-autoresize').options) {
			var opts = $.data(target, 'mk-autoresize').options;
			var width = $(target).width(), height = $(target).height();
			height = (height <= opts.min_height ? opts.min_height : height);
			width = (width <= opts.min_width ? opts.min_width : width);

			if (typeof opts.height_exclude == 'string') {
				height = height - $(opts.height_exclude).height();
			} else {
				if (opts.height_exclude && opts.height_exclude.length > 0) {
					$.each(opts.height_exclude, function(i) {
						height = height - $(opts.height_exclude[i]).height();
					});
				}
			}
			height = height - opts.height_offset;
			if (typeof opts.height_include == 'string') {
				$(opts.height_include).height(height);
			} else {
				if (opts.height_include && opts.height_include.length > 0) {
					$.each(opts.height_include, function(i) {
						$(opts.height_include[i]).height(height);
					});
				}
			}

			if (typeof opts.width_exclude == 'string') {
				width = width - $(opts.width_exclude).width();
			} else {
				if (opts.width_exclude && opts.width_exclude.length > 0) {
					$.each(opts.width_exclude, function(i) {
						width = width - $(opts.width_exclude[i]).width();
					});
				}
			}
			width = width - opts.width_offset;
			if (typeof opts.width_include == 'string') {
				$(opts.width_include).width(width);
			} else {
				if (opts.width_include && opts.width_include.length > 0) {
					$.each(opts.width_include, function(i) {
						$(opts.width_include[i]).width(width);
					});
				}
			}
		}

	}

	$.fn.mk_autoresize = function(options) {
		return this.each(function() {
			var state = $.data(this, 'mk-autoresize');
			if (state&&state.options) {
				$.extend(state.options, options);
			} else {
				$.data(this, 'mk-autoresize', {
					options : $.extend({}, $.fn.mk_autoresize.defaults, $.fn.mk_autoresize.parse_options(this), options)
				});
			}

			init(this);
		});
	};

	$.fn.mk_autoresize.parse_options = function(target) {
		return $.extend({}, $.mk_parser.parse_options(target, []), {});
	};

	$.fn.mk_autoresize.methods = {};

	$.fn.mk_autoresize.defaults = {
		min_width : 0,
		min_height : 0,
		width_exclude : null,
		width_include : null,
		width_offset : 4,
		height_exclude : null,
		height_include : null,
		height_offset : 0,
		is_handler : true
	};
})(jQuery);