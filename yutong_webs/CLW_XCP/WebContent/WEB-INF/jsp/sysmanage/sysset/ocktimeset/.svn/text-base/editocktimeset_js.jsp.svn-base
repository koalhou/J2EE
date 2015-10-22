<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript">
function firstrisize(){
	jQuery('#content').mk_autoresize({
        height_include: '#main',
        height_offset: 0 
      });
}
jQuery(function() {
	firstrisize();
	jQuery(window).mk_autoresize({
	     height_include: '#content',
	     height_exclude: ['#header', '#footer'],
	     height_offset: 0,
	     width_include: ['#header', '#content', '#footer'],
	     width_offset: 0
	  });
	 firstrisize();
});
</script>