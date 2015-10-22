
var urls = [
  {
    url_reg: /.*\/default.html$/,
    class: 'snav5focus',
    id: '#bi0'
  },
  {
    url_reg: /.*\/1.html$/,
    class: 'snav1focus',
    id: '#bi1'
  },
  {
    url_reg: /.*\/2.html$/,
    class: 'snav2focus',
    id: '#bi2'
  },
  {
    url_reg: /.*\/3.html$/,
    class: 'snav3focus',
    id: '#bi3'
  },
  {
    url_reg: /.*\/4.html$/,
    class: 'snav4focus',
    id: '#bi4'
  }
];

function update_current_nav_css() {
  var href = location.href;
  if (urls && urls.length > 0) {
    $.each(urls, function(idx) {
      console.log(this.url_reg + '.test(' + href + ')->' + (this.url_reg.test(href)));
      if (this.url_reg.test(href)) {
        $(this.id).parent().addClass(this.class);
      }
    });
  }
}

$(function() {    
  update_current_nav_css();
});