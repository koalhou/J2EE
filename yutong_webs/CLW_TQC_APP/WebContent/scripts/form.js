// JavaScript Document
jQuery(document).ready(function(){


	jQuery(".selectSetVal li").hover(function(){
		jQuery(this).css('background','#d9d9d9')
	},function(){
		jQuery(this).css('background','none')
	})


   jQuery(".selectSetVal li").click(function(){
		var val = jQuery(this).text();
		jQuery(this).parent().siblings(".selectGet").children(".selectGetVal").text(val);
		jQuery(this).parent(".selectSetVal").slideUp("fast");
	})

	jQuery(".selectSetVal").hide();
   jQuery(".selectGet").click(function(){
		jQuery(this).siblings(".selectSetVal").slideDown();
	})

    jQuery(".formCon").hover(function(){},function(){
		jQuery(this).find(".selectSetVal").slideUp();
	})
	
	jQuery(".buttonbarRig").hover(function(){},function(){
		jQuery(this).find(".selectSetVal").slideUp();
	})
	 jQuery(".alarm_arr_t").css("cursor","pointer")
	 
	 jQuery(".alarm_arr_t").click(function(){
	jQuery(".alarm_t_list").show();
     })

	jQuery(".alarm_c").hover(function(){
							
		
	},function(){
		jQuery(this).children(".alarm_t_list").hide();		
	})
	 
	 var sval = jQuery(".alarm_sel_c select").find("option:selected").text();
	 jQuery(".select_see_val").text(sval);
	 
	 
	 

	
    jQuery('#select').change(function(){
        var sval = jQuery(this).children("option:selected").text(); 
		jQuery(this).siblings(".select_see_val").text(sval);
		 
    });

    //20120828-放大镜效果
    jQuery(".magnifier_a ").addClass("a_img");
    jQuery(".magnifier_a ").hover(function(){
    	jQuery(this).addClass("a_imgs").removeClass("a_img");								  
	},function(){
		jQuery(this).addClass("a_img").removeClass("a_imgs").removeClass("a_imgc");
	})
	jQuery(".magnifier_a ").click(function(){
		jQuery(this).addClass("a_imgc").removeClass("a_imgs");
	})
	 
})