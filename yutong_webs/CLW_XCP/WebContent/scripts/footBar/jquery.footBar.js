/*
//@ version: footerBar v1.0.0 
//@ datatime:2013-12-02
*/
!function(a,b,c){a.fn.footBar=function(b){var d={minHeight:894},e=a.extend({},d,b);return this.each(function(){var b=a(this),d=this.clientHeight,f=c.body.clientHeight;f>e.minHeight?b.css({top:"auto",bottom:0}):b.css({top:e.minHeight-d+"px",bottom:"auto"})})}}(jQuery,window,document);