
package com.neusoft.clw.info.global;

/**
 * 全局状态码以及状态描述 
 */

public final class Globals {
	private Globals() {
	}
	
	////////////////////////////////////////////////////////////////////
    // 油耗告警的响应码
	public static final int RES_CODE_ALARM_EXIST_OIL_EXCEPTION_INT = 0; // 发现异常油耗
	public static final int RES_CODE_ALARM_NORMALLY_INT            = 1; // 正常,
	public static final int RES_CODE_ALARM_ERROR_IN_PROCESS_INT    = 2; // 处理过程发生异常
	
	////////////////////////////////////////////////////////////////////
    // 核心应用的返回响应码及其描述
    
	// 成功处理请求并返回响应消息
	public static final int     RES_CODE_200_INT        = 200;
	public static final String  RES_CODE_200_STR        = "200";
	public static final String  RES_CODE_200_DESC       = "请求成功";
	
	// 请求消息格式或语法错误
	public static final int     RES_ERROR_CODE_601_INT  = 601;
	public static final String  RES_ERROR_CODE_601_STR  = "601";
	public static final String  RES_ERROR_CODE_601_DESC = "请求消息的格式或语法错误";
	
	// 服务器内部错误,无响应消息
	public static final int     RES_ERROR_CODE_602_INT  = 602;
    public static final String  RES_ERROR_CODE_602_STR  = "602";
    public static final String  RES_ERROR_CODE_602_DESC = "服务器内部错误";

   // 服务器正忙于计算,拒绝请求
	public static final int     RES_ERROR_CODE_603_INT  = 603;
    public static final String  RES_ERROR_CODE_603_STR  = "603";
    public static final String  RES_ERROR_CODE_603_DESC = "计算忙,稍后重试";
 // 鉴权失败
	public static final int     RES_ERROR_CODE_604_INT  = 604;
    public static final String  RES_ERROR_CODE_604_STR  = "604";
    public static final String  RES_ERROR_CODE_604_DESC = "鉴权失败";
    
    public static final int RES_ERROR_CODE_610_INT = 610;
    public static final String RES_ERROR_CODE_610_STR = "610";
    public static final String RES_ERROR_CODE_610_DESC = "初始消息入库异常";
    
    public static final int RES_ERROR_CODE_611_INT = 611;
    public static final String RES_ERROR_CODE_611_STR = "611";
    public static final String RES_ERROR_CODE_611_DESC = "消息下发失败";
    
    
    /**************************************** 各协议允许的排序字段设置 ****************************/
    /** 车辆油耗汇总的可选排序字段*/
    public static final String ORDER_FEILD_OF_VEHIC_OIL_SUM = ",targetId,name,ao,uo,eo,";
    /** 车辆油耗异常记录查询的可选排序字段*/
    public static final String ORDER_FEILD_OF_VEHIC_OIL_EXP = ",targetId,name,addr,lon,lat,eo,date,speed,type,";
    /** 集团油耗汇总的可选排序字段*/
    public static final String ORDER_FEILD_OF_CORPS_OIL_SUM = ",corpId,corpname,ao,uo,eo,";
    /** 车辆油耗日汇总的可选排序字段*/
    public static final String ORDER_FEILD_OF_VEHIC_DAILY_OIL_SUM = ",date,ao,uo,eo,";
    /** 分组油耗汇总的可选排序字段*/
    public static final String ORDER_FEILD_OF_GROUP_OIL_SUM = ",groupId,ao,uo,eo,";
    
    
    
    
    /**************************************** 常量 ****************************/
    public static final String COMMA_STR = ",";
    
//    ////////////////////////////////////////////////////////////////////
//    // 请求消息格式错误详细
//    
//    // olx节点空
//    public static final int    REQ_ERROR_CODE_611_INT  = 611;  
//    public static final String REQ_ERROR_CODE_611_STR  = "611";  
//    public static final String REQ_ERROR_CODE_611_DESC = "olx节点为空或缺少必要属性";
//    
//    // function节点空
//    public static final int    REQ_ERROR_CODE_612_INT  = 612;   
//    public static final String REQ_ERROR_CODE_612_STR  = "612";  
//    public static final String REQ_ERROR_CODE_612_DESC = "function节点为空或缺少必要属性";
//
//    // param节点空
//    public static final int    REQ_ERROR_CODE_613_INT  = 613;   
//    public static final String REQ_ERROR_CODE_613_STR  = "613";  
//    public static final String REQ_ERROR_CODE_613_DESC = "param节点为空";
//
//    // tree-object节点空
//    public static final int    REQ_ERROR_CODE_614_INT  = 614;   
//    public static final String REQ_ERROR_CODE_614_STR  = "614";  
//    public static final String REQ_ERROR_CODE_614_DESC = "tree-Object节点为空";
//
//    // 分页参数isPage=true
//    public static final int    REQ_ERROR_CODE_615_INT  = 615;   
//    public static final String REQ_ERROR_CODE_615_STR  = "615";  
//    public static final String REQ_ERROR_CODE_615_DESC = "无效的分页参数:isPage=true时,必需有pageNo和pageSize参数";
//
//    // 分页参数isPage=false
//    public static final int    REQ_ERROR_CODE_616_INT  = 616;   
//    public static final String REQ_ERROR_CODE_616_STR  = "616";  
//    public static final String REQ_ERROR_CODE_616_DESC = "无效的分页参数:isPage=false时,必需有maxPageSize参数来限定页面大小";
//
//    // 排序参数无效
//    public static final int    REQ_ERROR_CODE_617_INT  = 617;   
//    public static final String REQ_ERROR_CODE_617_STR  = "617";  
//    public static final String REQ_ERROR_CODE_617_DESC = "无效的排序参数:如果使用排序参数,则必需有name和asc参数, 且name是响应消息中包含的字段名";
//
//    // 缺少起止时间参数
//    public static final int    REQ_ERROR_CODE_618_INT  = 618;   
//    public static final String REQ_ERROR_CODE_618_STR  = "618";  
//    public static final String REQ_ERROR_CODE_618_DESC = "无效的请求时间段:请求中必需包含起止时间beginTime和endTime,格式'yyyy-mm-dd'";
//
//    // olx.dir值错误
//    public static final int    REQ_ERROR_CODE_619_INT  = 619;   
//    public static final String REQ_ERROR_CODE_619_STR  = "619";  
//    public static final String REQ_ERROR_CODE_619_DESC = "请求xml中olx.dir必须是\"up\"";
//
//    // olx.version值错误
//    public static final int    REQ_ERROR_CODE_620_INT  = 620;   
//    public static final String REQ_ERROR_CODE_620_STR  = "620";  
//    public static final String REQ_ERROR_CODE_620_DESC = "olx.Version属性值错误, 系统当前支持的版本号仅为\"0.0.1\"";
//
//    // function.name值错误
//    public static final int    REQ_ERROR_CODE_621_INT  = 621;   
//    public static final String REQ_ERROR_CODE_621_STR  = "621";  
//    public static final String REQ_ERROR_CODE_621_DESC = "function.name属性值必须是接口协议中描述的四种之一";
//
//    // function.seq值错误
//    public static final int    REQ_ERROR_CODE_622_INT  = 622;   
//    public static final String REQ_ERROR_CODE_622_STR  = "622";  
//    public static final String REQ_ERROR_CODE_622_DESC = "function.seq属性值必须是整数";
//
//    // function.timout值错误
//    public static final int    REQ_ERROR_CODE_623_INT  = 623;   
//    public static final String REQ_ERROR_CODE_623_STR  = "623";  
//    public static final String REQ_ERROR_CODE_623_DESC = "function.timout属性值必须是正整数";
//
//    public static final int    REQ_ERROR_CODE_624_INT  = 624;   
//    public static final String REQ_ERROR_CODE_624_STR  = "624";  
//    public static final String REQ_ERROR_CODE_624_DESC = "tree-object.Version属性值必须和olx.version保持一致!";
//
//    // isPage值错误
//    public static final int    REQ_ERROR_CODE_625_INT  = 625;   
//    public static final String REQ_ERROR_CODE_625_STR  = "625";  
//    public static final String REQ_ERROR_CODE_625_DESC = "分页参数中,isPage的value属性必须是\"true\"或\"false\"";
//
//    // pageNo或pageSize值错误
//    public static final int    REQ_ERROR_CODE_626_INT  = 626;   
//    public static final String REQ_ERROR_CODE_626_STR  = "626";  
//    public static final String REQ_ERROR_CODE_626_DESC = "分页参数中,pageNo和pageSize的value属性必须是正整数";
//
//    // maxPageSize值错误
//    public static final int    REQ_ERROR_CODE_627_INT  = 627;
//    public static final String REQ_ERROR_CODE_627_STR  = "627";  
//    public static final String REQ_ERROR_CODE_627_DESC = "分页参数中,maxPageSize的value属性必须是正整数";
//
//    // 排序字段name值错误
//    public static final int    REQ_ERROR_CODE_628_INT  = 628;   
//    public static final String REQ_ERROR_CODE_628_STR  = "628";  
//    public static final String REQ_ERROR_CODE_628_DESC = "无效的排序字段名";
//
//    // 排序方式asc值错误
//    public static final int    REQ_ERROR_CODE_629_INT  = 629;   
//    public static final String REQ_ERROR_CODE_629_STR  = "629";  
//    public static final String REQ_ERROR_CODE_629_DESC = "无效的排序字方式:排序参数中的asc必须是\"true\"或\"false\"";
//
//    // 起止时间格式错误
//    public static final int    REQ_ERROR_CODE_630_INT  = 630;   
//    public static final String REQ_ERROR_CODE_630_STR  = "630";  
//    public static final String REQ_ERROR_CODE_630_DESC = "无效的起止时间:beginTime,endTime必需满足格式'yyyy-mm-dd'";

    ///////////////////////////////////////////////////////////////////
}
