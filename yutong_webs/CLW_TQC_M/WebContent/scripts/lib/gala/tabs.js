Gala.Tabs = new Class({
  options : {
        mouseOverClass : 'active',
        activateOnLoad : 'first'
    },

   initialize : function(element, options) {
      this.setOptions(options);
      this.el = $(element);
      this.elid = element;
      
      this.titles = $$('#' + this.elid + ' ul.gala_tab_title li');
      this.panelArr = [];//$$('#' + this.elid + ' .gala_tab_panel');
      
      this.titles.each(function(item) {
          item.addEvent('click', function() {
              item.removeClass(this.options.mouseOverClass);
              this.activate(item);
            }.bind(this));

          item.addEvent('mouseover', function(){
              if (item != this.activeTitle) {
                item.addClass(this.options.mouseOverClass);
              }
            }.bind(this));

          item.addEvent('mouseout', function(){
              if (item != this.activeTitle) {
                item.removeClass(this.options.mouseOverClass);
              }
            }.bind(this));
            
            this.panelArr.include($(item.getProperty('targetId')));
        }.bind(this));
        
        this.panels = new Elements(this.panelArr);

      if (this.options.activateOnLoad != 'none') {
        if (this.options.activateOnLoad == 'first') {
          this.activate(this.titles[0]);
        } else {
          this.activate(this.options.activateOnLoad);
        }
      }
    },
    
   activate : function(tab) {
      if ($type(tab) == 'string') {
        var myTab = $$('#' + this.elid + ' ul li').filterByAttribute('targetId', '=', tab)[0];
        tab = myTab;
      }

      if ($type(tab) == 'element') {
        var newTab = tab.getProperty('targetId');	

        this.panels.removeClass('gala_tab_panel_show');
        this.panels.addClass('gala_tab_panel_hide');
        var selects = $$('select');
        selects.each(function(sel){
        	sel.addClass('gala-tab-hide-select');
        });
        this.activePanel = this.panels.filterById(newTab)[0];
        this.activePanel.addClass('gala_tab_panel_show');
        var activeSelects = $$('div.gala_tab_panel_show select');
        activeSelects.each(function(sel){
        	sel.removeClass('gala-tab-hide-select');
        });

        this.titles.removeClass('active');

        tab.addClass('active');

        this.activeTitle = tab;
      }
    }
});

Gala.Tabs.implement(new Events, new Options);
