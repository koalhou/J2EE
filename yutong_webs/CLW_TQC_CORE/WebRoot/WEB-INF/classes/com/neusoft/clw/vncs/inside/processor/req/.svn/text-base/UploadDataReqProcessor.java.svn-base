package com.neusoft.clw.vncs.inside.processor.req;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.SchoolBus.vncs.value.Up_XCValue;
import com.neusoft.clw.exception.HandleException;
import com.neusoft.clw.exception.InvalidMessageException;
import com.neusoft.clw.nio.client.ICommunicateService;
import com.neusoft.clw.spring.SpringBootStrap;
import com.neusoft.clw.util.IdCreater;
import com.neusoft.clw.vncs.buffer.DBBuffer;
import com.neusoft.clw.vncs.buffer.insert.InsertBuffer;
import com.neusoft.clw.vncs.cachemanager.TerminalCacheManager;
import com.neusoft.clw.vncs.dao.ICerFailDAO;
import com.neusoft.clw.vncs.dao.ITerminal_ParamDAO;
import com.neusoft.clw.vncs.domain.TerminalBean;
import com.neusoft.clw.vncs.inside.msg.InsideMsgFactory;
import com.neusoft.clw.vncs.inside.msg.InsideMsgStatusCode;
import com.neusoft.clw.vncs.inside.msg.content.CommandCode;
import com.neusoft.clw.vncs.inside.msg.content.ContentParamName;
import com.neusoft.clw.vncs.inside.msg.content.Up_InfoContent;
import com.neusoft.clw.vncs.inside.msg.content.value.Up_BlindAreaValue;
import com.neusoft.clw.vncs.inside.msg.content.value.Up_CerFailValue;
import com.neusoft.clw.vncs.inside.msg.content.value.Up_DriveValue;
import com.neusoft.clw.vncs.inside.msg.content.value.Up_EngLockValue;
import com.neusoft.clw.vncs.inside.msg.content.value.Up_GeneralRespValue;
import com.neusoft.clw.vncs.inside.msg.content.value.Up_ParamValue;
import com.neusoft.clw.vncs.inside.msg.content.value.Up_PhotoValue;
import com.neusoft.clw.vncs.inside.msg.content.value.Up_RealTimeValue;
import com.neusoft.clw.vncs.inside.msg.content.value.Up_RunRecordValue;
import com.neusoft.clw.vncs.inside.msg.content.value.Up_StateValue;
import com.neusoft.clw.vncs.inside.msg.content.value.Up_XcTerDataValue;
import com.neusoft.clw.vncs.inside.msg.content.value.XcUp_ParamValue;
import com.neusoft.clw.vncs.inside.msg.req.UpLoadDataReq;
import com.neusoft.clw.vncs.inside.processor.AbstractInsideProcessor;
import com.neusoft.clw.vncs.inside.processor.resp.UploadDataRespProcessor;
import com.neusoft.clw.vncs.service.BuildSQL;
import com.neusoft.clw.vncs.service.CachePattern;
import com.neusoft.parents.eventBuffer.BasicObject;
import com.neusoft.parents.eventBuffer.EventBuffer;
import com.neusoft.tqcpt.value.Up_ZsptValue;

public class UploadDataReqProcessor extends
		AbstractInsideProcessor<Up_InfoContent, ICommunicateService> {

	private static Logger log = LoggerFactory
			.getLogger(UploadDataRespProcessor.class);

	private static final String NAME = "<UploadDataReqProcessor>";
	
	public static final UploadDataReqProcessor PROCESSOR = new UploadDataReqProcessor();
	private ITerminal_ParamDAO ter_paramDAO = null;
	
//	private CacheBean cb = new CacheBean();
	
	private TerminalCacheManager tcm = TerminalCacheManager.getInstance();

	private UploadDataReqProcessor() {
		ter_paramDAO = (ITerminal_ParamDAO) SpringBootStrap.getInstance().getBean("ter_paramDAO");
	}

	public static UploadDataReqProcessor getInstance() {
		return PROCESSOR;
	}

	public void handle(Up_InfoContent msg, ICommunicateService communicateService)
			throws HandleException {
		try {
			byte[] resp = InsideMsgFactory.createUpLoadDataResp(msg.getStatusCode(), msg.getSeq()).getBytes();
			communicateService.send(resp);
			log.info(NAME+"接收到上行消息，并返回应答："+ new String(resp));
		} catch (Exception e) {
			e.printStackTrace();
			log.info(NAME+"生成应答或发送应答时发生异常"+e.getMessage());
		}
		msg = null;
	}

	public Up_InfoContent parse(byte[] buf){
		Up_InfoContent udp = new Up_InfoContent();
		try {
			int location = super.parseHeader(buf, udp);
//			System.out.println(udp.getCommand());
			// 截取terminal_id、packet_length
			byte[] terminalIdBuf = new byte[UpLoadDataReq.TERMINALIDSIZE];
			byte[] packetLenBuf = new byte[UpLoadDataReq.PACKETLENSIZE];
			System.arraycopy(buf, location, terminalIdBuf, 0, UpLoadDataReq.TERMINALIDSIZE);
			location += UpLoadDataReq.TERMINALIDSIZE;
			String terminalId = new String(terminalIdBuf);
			udp.setTerminalId(terminalId);
			System.arraycopy(buf, location, packetLenBuf, 0, UpLoadDataReq.PACKETLENSIZE);
			location += UpLoadDataReq.PACKETLENSIZE;
			String packetLen = new String(packetLenBuf);
			udp.setPacketLen(packetLen);
			location += ContentParamName.REGULARLEN;
			byte[] bytelen = new byte[ContentParamName.REGULARLEN];
			System.arraycopy(buf, location, bytelen, 0,ContentParamName.REGULARLEN);
			location += ContentParamName.REGULARLEN;
			String len = new String(bytelen);
			bytelen = null;
			
			// 0x00元素值
			byte[] bytevalue = new byte[Integer.parseInt(len,16)];
			System.arraycopy(buf, location, bytevalue, 0, Integer.parseInt(len,16));
			location += Integer.parseInt(len,16);
			
			String cmd = new String(bytevalue);
			if (cmd.equals(CommandCode.up1000)) {// 1000上行实时信息
				log.info(NAME+"上行实时数据处理流程开始.");
				Up_RealTimeValue.getUp_RealTimeContent(location, buf,udp);
//				告警处理
				String id = IdCreater.getUUid();
				if(udp.getAlarm_state()!=null||udp.getXcstate()!=null){
					CachePattern.getInstance().setAlarmSql(udp,id);
				}
				// 将sql加入队列中
				InsertBuffer.getInstance().add(BuildSQL.getInstance().buildInsertUp_RealTimeSql(udp,id));
				DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateTerminalSql(udp));

				if(udp.getMeg_resp_id()!=null&&!udp.getMeg_resp_id().equals("")){
					udp.setDeal_state("3");
					DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateSendCmdSql(udp,udp.getMeg_resp_id()));
				}
				if(udp.getMeg_info()!=null&&!udp.getMeg_info().equals("")){
					DBBuffer.getInstance().add(BuildSQL.getInstance().buildInsertUPMESSAGESQL(udp));
				}
				log.info(NAME+"上行实时数据处理流程结束.");
				
				
				//liuja增加start    设置了10秒定时任务，暂时将此处的更新缓存信息停止
				/*
				log.info("开始更新位置信息缓存");
                try
                {
                    BasicObject boSendCommand=new BasicObject();
                    boSendCommand.setObjectName("com.neusoft.parents.service.VehicleRealInfoService");                                     
                    boSendCommand.setFunctionName("CallVehicleLocationInterface");
                    List<Object> paramsSendCommand=new ArrayList<Object>();
                    paramsSendCommand.add(udp);
                    boSendCommand.setParamsList(paramsSendCommand);
                    //将BasicObject加入事件处理缓存
                    EventBuffer.getInstance().add(boSendCommand);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    log.error(NAME + ",学生状态发送错误");
                }
                log.info("结束更新位置信息缓存");
                */
              //liuja增加end
                
                
                
                
                
			} else if (cmd.equals(CommandCode.up1001)) {// 1001上行图片信息
				log.info(NAME+ "上行图片信息处理流程开始.");
				// 解析上行图片信息，并将信息存入Up_PhotoContent
				Up_PhotoValue.getUpPhotoContent(location, buf, udp);
				// 拼装sql语句将图片信息录入到拍照信息表中
				
				if(ter_paramDAO.selectPhotoId(udp.getMsg_id())>0){
					DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateUpMuldiaSql(udp));
				}else{
					log.debug(BuildSQL.getInstance().buildInsertUpPhotoSql(udp));
					InsertBuffer.getInstance().add(BuildSQL.getInstance().buildInsertUpPhotoSql(udp));
					if(udp.getPhoto_event().equals("0")){
						log.info(NAME+"命令已下发至终端，并成功反馈.");
						udp.setDeal_state("3");
	//					log.debug(BuildSQL.getInstance().buildUpdateSendCmdSql(udp,udp.getMsg_id()));
						DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateSendCmdSql(udp,udp.getMsg_id()));
						log.info(NAME+ "更新 终端下发命令表处理状态：成功.");
					}
				}
				log.info(NAME+"上行图片信息处理流程结束.");
			} else if (cmd.equals(CommandCode.up1005)) {// 1005上行鉴权不通过通知消息
				log.info(NAME+"上行鉴权不通过通知消息处理流程开始.");
				// 解析上行鉴权不通过通知消息，并存入Up_CerFailContent
				Up_CerFailValue.getUpCerFailContent(location, buf, udp);
				ICerFailDAO cerFailDAO = (ICerFailDAO) SpringBootStrap.getInstance().getBean("cerFailDAO");
				// 查询终端鉴权失败表中，某车辆的终端是否鉴权通过，结果大于0说明已经鉴权并且未通过，否则等待鉴权
				if (cerFailDAO.getCerFailByID(udp.getVehicle_vin(),udp.getTerminal_id(),udp.getSim()) > 0) {
					log.info(NAME+"车辆"+udp.getVehicle_vin()+"已上报鉴权未通过.");
//					log.debug(BuildSQL.getInstance().buildUpdateUpCerFailSql(udp));
					DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateUpCerFailSql(udp));
				} else {
//					log.debug(BuildSQL.getInstance().buildInsertUpCerFailSql(udp));
					InsertBuffer.getInstance().add(BuildSQL.getInstance().buildInsertUpCerFailSql(udp));
				}
				log.info(NAME+ "上行鉴权不通过通知消息处理流程结束.");
			} else if (cmd.equals(CommandCode.up1007)) {//上行参数信息
				log.info(NAME+ "上行参数信息处理流程开始.");
				Up_ParamValue.getUpParamContent(location, buf, udp);
//				log.debug(BuildSQL.getInstance().buildInsertUpParamSql(udp));
				DBBuffer.getInstance().add(BuildSQL.getInstance().buildInsertUpParamSql(udp));
//				log.debug(BuildSQL.getInstance().buildUpdateTerminalParamSql(udp));
//				TerminalBean tb = BuildSQL.getInstance().getTerminal(udp.getTerminalId());
				TerminalBean tb = tcm.getValue(udp.getTerminalId());
				int num = ter_paramDAO.getParamCount(tb.getTerminal_id());
				if(num==0){
					InsertBuffer.getInstance().add(BuildSQL.getInstance().buildInsert808TerminalParamSql(udp,tb));
				}else{
					DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdate808TerminalParamSql(udp,tb));
				}
				udp.setDeal_state("3");
				DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateSendCmdSql(udp,udp.getMsg_id()));
				log.info(NAME+"上行参数信息处理流程结束.");
			} else if(cmd.equals("1B00")){
				log.info(NAME+"驾驶行为事件处理流程开始");
				Up_DriveValue.getUpDriveContent(location, buf, udp);
				if(udp.getEndtime().compareTo(udp.getStarttime())>=0){
					setAlarmEventsId(udp);
					InsertBuffer.getInstance().add(BuildSQL.getInstance().buildInsertDriveEventSql(udp));
				}else{
					log.info(NAME+",该驾驶行为事件，开始时间大于结束时间，故丢弃");
				}
				log.info(NAME+"驾驶行为事件处理流程结束");
			} else if (cmd.equals(CommandCode.up1104)) {//上行参数信息
				log.info(NAME+ "上行参数信息处理流程开始.");
				XcUp_ParamValue.getUpParamContent(location, buf, udp);
				TerminalBean tb = tcm.getValue(udp.getTerminalId());
				DBBuffer.getInstance().add(BuildSQL.getInstance().buildInsert808UpParamSql(udp,tb));
				int num = ter_paramDAO.getParamCount(tb.getTerminal_id());
				if(num==0){
					InsertBuffer.getInstance().add(BuildSQL.getInstance().buildInsert808TerminalParamSql(udp,tb));
				}else{
					DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdate808TerminalParamSql(udp,tb));
				}
				udp.setDeal_state("3");
				DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateSendCmdSql(udp,udp.getMsg_id()));
				log.info(NAME+"上行参数信息处理流程结束.");
			}else if(cmd.equals(CommandCode.up1C00)){
				Up_GeneralRespValue.getUp_GeneralResp(location, buf, udp);
				if(udp.getRespevent().equals("1")){
					log.info(NAME+"接收到电话回拨应答,电话回拨功能应答处理开始");
					if(udp.getState().equals("1")){
						udp.setDeal_state("3");
						DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateSendCmdSql(udp, udp.getMsg_id()));
						log.info(NAME+ "更新"+udp.getTerminalId()+","+udp.getMsg_id()+" 电话回拨命令下发状态：成功.");
					}else{
						log.info(NAME+ "更新"+udp.getTerminalId()+","+udp.getMsg_id()+" 电话回拨命令下发状态：失败.");
					}
					log.info(NAME+"电话回拨功能应答处理结束");
				}else if(udp.getRespevent().equals("2")){
					log.info(NAME+"接收到摄像头立即拍照应答,摄像头立即拍照应答处理开始");
					if(udp.getState().equals("1")){
						udp.setDeal_state("3");
						DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateSendCmdSql(udp, udp.getMsg_id()));
						log.info(NAME+ "更新"+udp.getTerminalId()+","+udp.getMsg_id()+" 摄像头立即拍照命令下发状态：成功.");
					}else{
						log.info(NAME+ "更新"+udp.getTerminalId()+","+udp.getMsg_id()+" 摄像头立即拍照命令下发状态：失败.");
					}
					log.info(NAME+"摄像头立即拍照应答处理结束");
				}else if(udp.getRespevent().equals("3")){
					log.info(NAME+"接收到临时位置跟踪应答,临时位置跟踪应答处理开始");
					if(udp.getState().equals("1")){
						udp.setDeal_state("3");
						DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateSendCmdSql(udp, udp.getMsg_id()));
						log.info(NAME+ "更新"+udp.getTerminalId()+","+udp.getMsg_id()+" 临时位置跟踪命令下发状态：成功.");
					}else{
						log.info(NAME+ "更新"+udp.getTerminalId()+","+udp.getMsg_id()+" 临时位置跟踪命令下发状态：失败.");
					}
					log.info(NAME+"临时位置跟踪应答处理结束");
				}else if(udp.getRespevent().equals("4")){
					log.info(NAME+"接收到透传事件应答,透传事件应答处理开始");
					if(udp.getState().equals("1")){
						udp.setDeal_state("3");
						DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateSendCmdSql(udp, udp.getMsg_id()));
						log.info(NAME+ "更新"+udp.getTerminalId()+","+udp.getMsg_id()+" 透传事件命令下发状态：成功.");
					}else{
						log.info(NAME+ "更新"+udp.getTerminalId()+","+udp.getMsg_id()+" 透传事件命令下发状态：失败.");
					}
					log.info(NAME+"透传事件应答处理结束");
				}else if(udp.getRespevent().equals("5")){
					log.info(NAME+"接收到告警取消应答,告警取消应答处理开始");
					if(udp.getState().equals("1")){
						udp.setDeal_state("3");
						DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateSendCmdSql(udp, udp.getMsg_id()));
						DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateSOSAlarmSql(udp, udp.getMsg_id()));
						log.info(NAME+ "更新"+udp.getTerminalId()+","+udp.getMsg_id()+" 告警取消命令下发状态：成功.");
					}else{
						log.info(NAME+ "更新"+udp.getTerminalId()+","+udp.getMsg_id()+" 告警取消命令下发状态：失败.");
					}
					log.info(NAME+"告警取消应答处理结束");
				}else if(udp.getRespevent().equals("6")){
					log.info(NAME+"接收到下行参数设置应答,下行参数设置应答处理开始");
					if(udp.getState().equals("1")){
						udp.setDeal_state("3");
						DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateSendCmdSql(udp, udp.getMsg_id()));
						log.info(NAME+ "更新"+udp.getTerminalId()+","+udp.getMsg_id()+" 下行参数设置命令下发状态：成功.");
					}else{
						log.info(NAME+ "更新"+udp.getTerminalId()+","+udp.getMsg_id()+" 下行参数设置命令下发状态：失败.");
					}
					log.info(NAME+"下行参数设置应答处理结束");
				}else if(udp.getRespevent().equals("7")){
					log.info(NAME+"接收到终端控制应答,终端控制应答处理开始");
					if(udp.getState().equals("1")){
						udp.setDeal_state("3");
						DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateSendCmdSql(udp, udp.getMsg_id()));
						log.info(NAME+ "更新"+udp.getTerminalId()+","+udp.getMsg_id()+" 终端控制命令下发状态：成功.");
					}else{
						log.info(NAME+ "更新"+udp.getTerminalId()+","+udp.getMsg_id()+" 下终端控制命令下发状态：失败.");
					}
					log.info(NAME+"终端控制应答处理结束");
				}else if(udp.getRespevent().equals("8")){
					log.info(NAME+"特征系数自动修正控制应答处理开始");
					if(udp.getState().equals("1")){
						udp.setDeal_state("3");
						DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateSendCmdSql(udp, udp.getMsg_id()));
						log.info(NAME+ "更新"+udp.getTerminalId()+","+udp.getMsg_id()+" 特征系数自动修正控制下发状态：成功.");
					}else{
						log.info(NAME+ "更新"+udp.getTerminalId()+","+udp.getMsg_id()+" 特征系数自动修正控制下发状态：失败.");
					}
					log.info(NAME+"特征系数自动修正控制应答处理结束");
				}else if(udp.getRespevent().equals("9")){
					log.info(NAME+"报站学习功能控制命令应答处理开始");
					if(udp.getState().equals("1")){
						udp.setDeal_state("3");
						DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateSendCmdSql(udp, udp.getMsg_id()));
						log.info(NAME+ "更新"+udp.getTerminalId()+","+udp.getMsg_id()+" 报站学习功能控制命令下发状态：成功.");
					}else{
						log.info(NAME+ "更新"+udp.getTerminalId()+","+udp.getMsg_id()+" 报站学习功能控制命令下发状态：失败.");
					}
					log.info(NAME+"报站学习功能控制命令应答处理结束");
				}
			}else if(cmd.equals(CommandCode.up1D00)){
				log.info(NAME+"盲区数据补传开始");
				Up_BlindAreaValue.getUp_BlindAreaValue(location,buf,udp);
				log.info(NAME+"盲区数据补传结束");
			}else if(cmd.equals(CommandCode.up1E00)){
				log.info(NAME+"程序版本信息汇报处理开始");
				Up_XcTerDataValue.getUp_XcTerDataValue(location,buf,udp);
				DBBuffer.getInstance().add(BuildSQL.getInstance().buildXcTerDataSql(udp));
				log.info(NAME+"程序版本信息汇报处理结束");
			}else if(cmd.equals(CommandCode.up1800)){
				log.info(NAME+"多媒体事件信息上传处理开始");
				log.info(NAME+"多媒体事件信息上传处理结束");
			}else if(cmd.equals(CommandCode.up1900)){
		      log.info(NAME+"上行透传处理流程开始.");
		      Up_XCValue.getUp_XCContent(location,buf,udp);
		      Up_ZsptValue.getUp_ZSContent_Xcpt(location,buf,udp);//add by ningdonghai  2013-05-14
		      log.info(NAME+"上行透传处理流程结束.");
			}else if (cmd.equals(CommandCode.up1003)) {//上行区域设置响应消息
				Up_StateValue.getUp_State(location,buf,udp);
				log.info(NAME+ "上行区域设置响应消息处理流程开始.");
				DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateUpSql(udp));
				log.info(NAME+"上行区域设置响应消息处理流程结束.");
			} else if (cmd.equals(CommandCode.up1004)) {//上行报警参数设置响应消息
				Up_StateValue.getUp_State(location,buf,udp);
				log.info(NAME+"上行报警参数设置响应消息处理流程开始.");
				DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateUpAlarmParamSql(udp));
				log.info(NAME+ "上行报警参数设置响应消息处理流程结束.");
			} else if (cmd.equals(CommandCode.up1009)) {//上行一般车辆参数设置响应消息
				Up_StateValue.getUp_State(location,buf,udp);
				log.info(NAME+"上行一般车辆参数设置响应消息处理流程开始.");
				DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateUp_VehicleSql(udp));
				log.info(NAME+"上行一般车辆参数设置响应消息处理流程结束.");
			} else if(cmd.equals("100A")){
				Up_StateValue.getUp_State(location,buf,udp);
				log.info(NAME+"上行短消息响应消息开始");
				DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateUp_MessageSql(udp));
				log.info(NAME+"上行短消息响应消息结束");
			} else if(cmd.equals(CommandCode.up1F01)){
				log.info(NAME+"单日行车记录上报消息开始");
				Up_RunRecordValue.getUpOnRecordContent(location, buf, udp);
				DBBuffer.getInstance().add(BuildSQL.getInstance().buildInsertRunRecordSql(udp));
				log.info(NAME+"单日行车记录上报消息结束");
			} else if(cmd.equals(CommandCode.up1F06)){
				log.info(NAME+"远程锁车命令下发消息应答开始");
				Up_StateValue.getUp_State(location,buf,udp);
				Up_EngLockValue.setUpM2MLockData(location, buf, udp);
				DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateUpSql(udp));
				log.info(NAME+"远程锁车命令下发消息应答结束");
			} else if(cmd.equals(CommandCode.up1F17)){
				log.info(NAME+"远程锁车命令下发终端结果上报开始");
				Up_EngLockValue.setUpEngLockData(location, buf, udp);
				log.info(NAME+"远程锁车命令下发终端结果上报结束");
			}
			cmd = null;
			udp.setStatusCode(InsideMsgStatusCode.STATUS_CODE_SUCCESS);
		} catch (Throwable t) {
			udp.setStatusCode(InsideMsgStatusCode.STATUS_COMMAND_WRONG);
			log.error(NAME+"parse upload data failed:" + t);
		}
		return udp;
	}
	
	private void setAlarmEventsId(Up_InfoContent udp){
		switch (udp.getTempEventsId()) {
		case 0:udp.setEventsId("23");break;
		case 1:udp.setEventsId("82");break;
		case 2:udp.setEventsId("49");break;
		case 3:udp.setEventsId("50");break;
		case 4:udp.setEventsId("81");break;
		case 5:udp.setEventsId("46");break;
		case 6:udp.setEventsId("51");break;
		case 7:udp.setEventsId("83");break;
		case 8:udp.setEventsId("45");break;
		case 9:udp.setEventsId("32");break;
		case 10:udp.setEventsId("33");break;
		default:
			break;
		}
	}

	public void valid(Up_InfoContent msg) throws InvalidMessageException {
		super.validHeader(msg);
	}
}
