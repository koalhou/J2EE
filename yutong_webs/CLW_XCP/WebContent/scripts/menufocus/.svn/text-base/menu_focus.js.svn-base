
var urls = [
  {
    url_reg: /^.*\/busalarm\/newmorealarm.shtml.*$/,
    class_name: 'nav9focus',
    id: '#mi9'
  },
  {
    url_reg: /^.*\/energy\/*.*$/,
    class_name: 'nav8focus',
    id: '#mi8'
  },
  {
    url_reg: /^.*\/vehicle\/.*$/,
    class_name: 'nav7focus',
    id: '#mi7'
  },
  {
	  url_reg: /^.*\/rm\/.*$/,
	  class_name: 'nav7focus',
	  id: '#mi7'
  },
  {
	  url_reg: /^.*\/usm\/usermanageAction.shtml.*$/,
	  class_name: 'nav7focus',
	  id: '#mi7'
  },
  {
	  url_reg: /^.*\/usm\/usermanageAction.shtml.*$/,
	  class_name: 'nav7focus',
	  id: '#mi7'
  },
  {
	  url_reg: /^.*\/usm\/userupdateinitAction.shtml.*$/,
	  class_name: 'nav7focus',
	  id: '#mi7'
  },
  {
	  url_reg: /^.*\/usm\/useraddinitAction.shtml.*$/,
	  class_name: 'nav7focus',
	  id: '#mi7'
  },
  {
	  url_reg: /^.*\/userpkg\/resetPwdBeforeP\.shtml.*$/,
	  class_name: 'nav7focus',
	  id: '#mi7'
  },
  {
	  url_reg: /^.*\/enti\/.*$/,
	  class_name: 'nav7focus',
	  id: '#mi7'
  },
  {
	  url_reg: /^.*\/logoset\/logoset.shtml\.*$/,
	  class_name: 'nav7focus',
	  id: '#mi7'
  },
  {
	  url_reg: /^.*\/zdnew\/.*$/,
	  class_name: 'nav7focus',
	  id: '#mi7'
  },
  {
	  url_reg: /^.*\/ock\/.*$/,
	  class_name: 'nav7focus',
	  id: '#mi7'
  },
  {
	  url_reg: /^.*\/oilset\/.*$/,
	  class_name: 'nav7focus',
	  id: '#mi7'
  },
  {
	  url_reg: /^.*\/vehiclestatus\/newvehiclestatus\.shtml.*$/,
	  class_name: 'nav6focus',
	  id: '#mi6'
  },
  {
	  url_reg: /^.*\/oilused\/oilused\.shtml.*$/,
	  class_name: 'nav6focus',
	  id: '#mi6'
  },
  {
	  url_reg: /^.*\/baddriv\/baddriving.shtml\.*$/,
	  class_name: 'nav6focus',
	  id: '#mi6'
  },
  {
	  url_reg: /^.*\/runoil\/ready\.shtml.*$/,
	  class_name: 'nav6focus',
	  id: '#mi6'
  },
  {
	  url_reg: /^.*\/zspt\/.*$/,
	  class_name: 'nav6focus',
	  id: '#mi6'
  },  
  {
	  url_reg: /^.*\/student\/.*$/,
	  class_name: 'nav5focus',
	  id: '#mi5'
  },
  {
	  url_reg: /^.*\/driver\/.*$/,
	  class_name: 'nav5focus',
	  id: '#mi5'
  },
  {
	  url_reg: /^.*\/steward\/.*$/,
	  class_name: 'nav5focus',
	  id: '#mi5'
  },
  {
	  url_reg: /^.*\/handmobile\/.*$/,
	  class_name: 'nav5focus',
	  id: '#mi5'
  },
  {
	  url_reg: /^.*\/smsreminder\/.*$/,
	  class_name: 'nav5focus',
	  id: '#mi5'
  },
  {
	  url_reg: /^.*\/sms\/.*$/,
	  class_name: 'nav5focus',
	  id: '#mi5'
  },
  {
	  url_reg: /^.*\/station\/stationmanage\.shtml.*$/,
	  class_name: 'nav4focus',
	  id: '#mi4'
  },
  {
	  url_reg: /^.*\/route\/.*$/,
	  class_name: 'nav4focus',
	  id: '#mi4'
  },
  {
	  url_reg: /^.*\/infomanage\/.*$/,
	  class_name: 'nav4focus',
	  id: '#mi4'
  },
  {
	  url_reg: /^.*\/holiday\/.*$/,
	  class_name: 'nav4focus',
	  id: '#mi4'
  },
  {
	  url_reg: /^.*\/carrun\/ready\.shtml.*$/,
	  class_name: 'nav3focus',
	  id: '#mi3'
  },
  {
	  url_reg: /^.*\/ridealarm\/ready.shtml.*$/,
	  class_name: 'nav3focus',
	  id: '#mi3'
  },
  {
	  url_reg: /^.*\/humanbaddrv\/baddrive.shtml.*$/,
	  class_name: 'nav3focus',
	  id: '#mi3'
  },
  {
	  url_reg: /^.*\/stride\/ready\.shtml.*$/,
	  class_name: 'nav3focus',
	  id: '#mi3'
  },
  {
	  url_reg: /^.*\/stushuaka\/ready.shtml\.*$/,
	  class_name: 'nav3focus',
	  id: '#mi3'
  },
  {
	  url_reg: /^.*\/drivershuaka\/ready.shtml\.*$/,
	  class_name: 'nav3focus',
	  id: '#mi3'
  },
  {
	  url_reg: /^.*\/message\/.*$/,
	  class_name: 'nav3focus',
	  id: '#mi3'  
  },
  {
	  url_reg: /^.*\/photomonitor\/.*$/,
	  class_name: 'nav3focus',
	  id: '#mi3'  
  },
  {
	  url_reg: /^.*\/overload\/.*$/,
	  class_name: 'nav3focus',
	  id: '#mi3'  
  },
  {
	  url_reg: /^.*\/busalarm\/.*$/,
	  class_name: 'nav3focus',
	  id: '#mi3'  
  },
  {
	  url_reg: /^.*\/routechart\/ready\.shtml.*$/,
	  class_name: 'nav2focus',
	  id: '#mi2'
  },
  {
	  url_reg: /^.*\/gps\/gpsAction.shtml\.*$/,
	  class_name: 'nav1focus',
	  id: '#mi1'
  }
];

function update_current_nav_css() {
  var href = location.href;
  if (urls && urls.length > 0) {
    jQuery.each(urls, function(idx) {
      if (this.url_reg.test(href)) {
    	  jQuery(this.id).addClass(this.class_name);
      }
    });
  }
}

jQuery(function() {    
  update_current_nav_css();
});