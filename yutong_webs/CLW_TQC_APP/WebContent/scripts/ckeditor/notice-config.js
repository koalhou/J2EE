/*
Copyright (c) 2003-2011, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/
//公告管理编辑框个性化配置
CKEDITOR.editorConfig = function( config )
{
	//工具栏是否可以被收缩（即：右上角的三角符号是否显示）
    config.toolbarCanCollapse = false;
	config.resize_enabled = false;
	 //定义语言，此处改为中文
    config.language = 'zh-cn'; //中文
    //字体
    config.font_names = '宋体;楷体;新宋体;黑体;隶书;幼圆;微软雅黑;Arial;Comic Sans MS;Courier New;Tahoma;Times New Roman;Verdana';
	config.toolbar = 'Full';
	config.toolbar_Full =
	[
//	    ['Source','Preview','RemoveFormat'],
//	    ['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
//	    ['NumberedList','BulletedList','-','Outdent','Indent'],
//	    ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
//	    ['Table','HorizontalRule','SpecialChar','PageBreak'],
//	    '/',
//	    ['Link','Unlink','Anchor'],
//	    ['Styles','Format','Font','FontSize'],
//	    ['TextColor','BGColor'],
//	    ['Maximize','-','About']
	    ['Preview','RemoveFormat'],
	    ['Bold','Italic','Underline','-','Subscript','Superscript'],
	    ['NumberedList','BulletedList','-','Outdent','Indent'],
	    ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock','Font','TextColor','BGColor','Maximize'],
	    '/',
	    ['Table','HorizontalRule','SpecialChar'],
	    ['Link','FontSize']
	];
};
