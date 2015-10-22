package com.yutong.clw.beans.app;



/**
 * http请求信息对应的JavaBean 
 */
public class RequestBean {
	private String olxDir;
	private String olxVersion;
	private String olxCompress;
	private String functionName; // 很重要
	private String functionSeq; // 很重要
	private String functionTimeOut;
	private String treeObjectVersion;

	// 分页参数
	private boolean usePageParam;
	private String isPage;
	private String pageNo;
	private String pageSize;
	private String maxPageSize;
	
	// 起止时间
	private String beginTime;
	private String endTime;
 	// 请求类型, 只在请求异常记录时用到
	private String type;
	
	private String appid;
	private String pass;
	private String send_command;
	private String send_type;
	private String terminal_id;
	private String sim_card_number;
	private String msg_id;
	private String operate_user_id;
	private String remark;
	private String packet_content;
	private String reggrpid;
	private String chanel_code;
	
	private int terminal;
	private int vehicle;
	
	//下行透传
	private String route_id;
	private String stu_id;
	
	private String batch_id;
	
	//5.4.7	线路文件更新通知
	private String iplength;
	private String ip;
	private String port;
	private String userlength;
	private String username;
	private String passlength;
	private String userpass;
	private String filelength;
	private String filename;
	private String crc;
	
	//liuja增加start
    private String vin;
    private String realLongitude;
    private String realLatitude;
    private String targetLongitude;
    private String targetLatitude;
	
    
    
	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getRealLongitude() {
		return realLongitude;
	}

	public void setRealLongitude(String realLongitude) {
		this.realLongitude = realLongitude;
	}

	public String getRealLatitude() {
		return realLatitude;
	}

	public void setRealLatitude(String realLatitude) {
		this.realLatitude = realLatitude;
	}

	public String getTargetLongitude() {
		return targetLongitude;
	}

	public void setTargetLongitude(String targetLongitude) {
		this.targetLongitude = targetLongitude;
	}

	public String getTargetLatitude() {
		return targetLatitude;
	}

	public void setTargetLatitude(String targetLatitude) {
		this.targetLatitude = targetLatitude;
	}
	//liuja 增加 end
	//5.4.8
	private String exception_event_switch;

	
	public String getException_event_switch() {
		return exception_event_switch;
	}

	public void setException_event_switch(String exceptionEventSwitch) {
		exception_event_switch = exceptionEventSwitch;
	}

	public String getIplength() {
		return iplength;
	}

	public void setIplength(String iplength) {
		this.iplength = iplength;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUserlength() {
		return userlength;
	}

	public void setUserlength(String userlength) {
		this.userlength = userlength;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasslength() {
		return passlength;
	}

	public void setPasslength(String passlength) {
		this.passlength = passlength;
	}

	public String getUserpass() {
		return userpass;
	}

	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}

	public String getFilelength() {
		return filelength;
	}

	public void setFilelength(String filelength) {
		this.filelength = filelength;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getCrc() {
		return crc;
	}

	public void setCrc(String crc) {
		this.crc = crc;
	}

	public String getBatch_id() {
		return batch_id;
	}

	public void setBatch_id(String batchId) {
		batch_id = batchId;
	}

	public String getRoute_id() {
		return route_id;
	}

	public void setRoute_id(String routeId) {
		route_id = routeId;
	}

	public String getStu_id() {
		return stu_id;
	}

	public void setStu_id(String stuId) {
		stu_id = stuId;
	}

	private String scope;
	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public synchronized void setTerminalAndVehicle(String str,int num) {
		if(str.equals("terminal")){
			terminal+=num;
		}
		if(str.equals("vehicle")){
			vehicle+=num;
		}
	}

	public int getTerminal() {
		return terminal;
	}

	public int getVehicle() {
		return vehicle;
	}	

	public String getReggrpid() {
		return reggrpid;
	}

	public void setReggrpid(String reggrpid) {
		this.reggrpid = reggrpid;
	}

	public String getChanel_code() {
		return chanel_code;
	}

	public void setChanel_code(String chanelCode) {
		chanel_code = chanelCode;
	}

	private String seq;//发送给基础平台的头包的seq；
	
 
	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	 

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	} 

	public String getSend_command() {
		return send_command;
	}

	public void setSend_command(String sendCommand) {
		send_command = sendCommand;
	}

	public String getSend_type() {
		return send_type;
	}

	public void setSend_type(String sendType) {
		send_type = sendType;
	}

	public String getTerminal_id() {
		return terminal_id;
	}

	public void setTerminal_id(String terminalId) {
		terminal_id = terminalId;
	}

	public String getSim_card_number() {
		return sim_card_number;
	}

	public void setSim_card_number(String simCardNumber) {
		sim_card_number = simCardNumber;
	}

	public String getMsg_id() {
		return msg_id;
	}

	public void setMsg_id(String msgId) {
		msg_id = msgId;
	}

	public String getOperate_user_id() {
		return operate_user_id;
	}

	public void setOperate_user_id(String operateUserId) {
		operate_user_id = operateUserId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPacket_content() {
		return packet_content;
	}

	public void setPacket_content(String packetContent) {
		packet_content = packetContent;
	}

	

    public RequestBean() {
		olxDir = null;
		olxVersion = null;
		olxCompress = null;
		functionName = null;
		functionSeq = null;
		functionTimeOut = null;
		treeObjectVersion = null;

		usePageParam = false;
		isPage = null;
		pageNo = null;
		pageSize = null;
		maxPageSize = null;

		 
		beginTime = null;
		endTime = null;
 

		type = null;
	 
	}

	public String getOlxDir() {
		return olxDir;
	}

	public void setOlxDir(String olxDir) {
		this.olxDir = olxDir;
	}

	public String getOlxVersion() {
		return olxVersion;
	}

	public void setOlxVersion(String olxVersion) {
		this.olxVersion = olxVersion;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getFunctionSeq() {
		return functionSeq;
	}

	public void setFunctionSeq(String functionSeq) {
		this.functionSeq = functionSeq;
	}

	public String getFunctionTimeOut() {
		return functionTimeOut;
	}

	public void setFunctionTimeOut(String functionTimeOut) {
		this.functionTimeOut = functionTimeOut;
	}

	public String getTreeObjectVersion() {
		return treeObjectVersion;
	}

	public void setTreeObjectVersion(String treeObjectVersion) {
		this.treeObjectVersion = treeObjectVersion;
	}

	public boolean isUsePageParam() {
		return usePageParam;
	}

	public void setUsePageParam(boolean usePageParam) {
		this.usePageParam = usePageParam;
	}

	public String getIsPage() {
		return isPage;
	}

	public void setIsPage(String isPage) {
		this.isPage = isPage;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getMaxPageSize() {
		return maxPageSize;
	}

	public void setMaxPageSize(String maxPageSize) {
		this.maxPageSize = maxPageSize;
	}

	 

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	 

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	 

	@Override
	public String toString() {
		return "RequestBean [beginTime=" + beginTime + ", endTime=" + endTime
				+ ", functionName=" + functionName + ", functionSeq="
				+ functionSeq + ", functionTimeOut=" + functionTimeOut
				+ ", isPage=" + isPage + ", maxPageSize=" + maxPageSize
				+ ", msg_id=" + msg_id + ", olxCompress=" + olxCompress
				+ ", olxDir=" + olxDir + ", olxVersion=" + olxVersion
				+ ", operate_user_id=" + operate_user_id + ", packet_content="
				+ packet_content + ", pageNo=" + pageNo + ", pageSize="
				+ pageSize + ", pass=" + pass + ", remark=" + remark
				+ ", send_command=" + send_command + ", send_type=" + send_type
				+ ", sim_card_number=" + sim_card_number + ", terminal_id="
				+ terminal_id + ", treeObjectVersion=" + treeObjectVersion
				+ ", type=" + type + ", usePageParam=" + usePageParam
				+ ", appid=" + appid + "]";
	}

	public String getOlxCompress() {
		return olxCompress;
	}

	public void setOlxCompress(String olxCompress) {
		this.olxCompress = olxCompress;
	}

    
}
