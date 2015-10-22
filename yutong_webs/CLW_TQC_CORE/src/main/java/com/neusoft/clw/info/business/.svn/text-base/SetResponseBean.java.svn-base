package com.neusoft.clw.info.business;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.neusoft.clw.info.bean.RequestBean;
import com.neusoft.clw.info.bean.ResponseBean;
import com.neusoft.clw.info.bean.SummaryRecord;
import com.neusoft.clw.info.bean.TerminalBizRecord;
import com.neusoft.clw.info.bean.TerminalRecord;
import com.neusoft.clw.info.bean.VehicRecord;
import com.neusoft.clw.info.dao.SendCommandDAO;
import com.neusoft.clw.info.dao.TerminalBizDAO;
import com.neusoft.clw.info.dao.TerminalInfoDAO;
import com.neusoft.clw.info.dao.VehicInfoDAO;
import com.neusoft.clw.info.exception.BusyAnalyzeException;
import com.neusoft.clw.info.exception.DBErrorException;
import com.neusoft.clw.info.exception.InvalidXmlFormatException;
import com.neusoft.clw.info.exception.SendInfoException;
import com.neusoft.clw.info.global.FunctionName;
import com.neusoft.clw.servlet.SendUpdateThread;
import com.neusoft.clw.spring.SpringBootStrap;

public class SetResponseBean {
	// private static Logger LOGGER = Logger.getLogger(SetResponseBean.class);

	@SuppressWarnings("unused")
	private static final String NAME = "SetResponseBean";

	public static boolean FLAG = false;

	private SetResponseBean() {
	}

	/**
	 * 出现异常的情况下,设置响应消息ResponseBean
	 */
	public static void setBeanWhenExceptionOccurs(ResponseBean respBean,
			final RequestBean reqBean) {

		// 响应消息xml基本信息
		respBean.setOlxDir("down"); // 在响应消息中,olx.dir属性固定不变的!
		respBean.setOlxVersion("0.0.1"); // 在响应消息中,olx.version属性固定不变的!
		respBean.setOlxCompress("false");
		respBean.setFunctionName(reqBean.getFunctionName());
		respBean.setFunctionSeq(reqBean.getFunctionSeq());
		respBean.setTreeObjectVersion("0.0.1"); // 在响应消息中,tree-object.version属性固定不变的!
	}

	/**
	 * 正常响应时,设置响应消息ResponseBean
	 * 
	 * @throws SQLException
	 * @throws SendInfoException
	 * @throws DBErrorException
	 * @throws ParseException
	 * @throws UnsupportedEncodingException 
	 */
	public static boolean setBean(ResponseBean respBean,
			final RequestBean reqBean) throws InvalidXmlFormatException,
			BusyAnalyzeException, SQLException, DBErrorException,
			SendInfoException, ParseException, UnsupportedEncodingException {

		// try {
		// 响应消息xml基本信息
		respBean.setOlxDir("down");
		respBean.setOlxVersion("0.0.1");
		respBean.setOlxCompress("false");
		respBean.setFunctionName(reqBean.getFunctionName());
		respBean.setFunctionSeq(reqBean.getFunctionSeq());
		respBean.setTreeObjectVersion("0.0.1");
		//
		// 起止时间
		respBean.setBeginTime(reqBean.getBeginTime());
		respBean.setEndTime(reqBean.getEndTime());
		//
		// 详细列表
		if (respBean.getFunctionName().equals(FunctionName.VNCS_APP_VEHICLEINFO)) { // 车辆信息
			vehicleInfo(respBean, reqBean);
		} else if (respBean.getFunctionName().equals(FunctionName.VNCS_APP_TERMINALINFO)) { // 终端信息同步
			terminalInfo(respBean, reqBean);
		} else if (respBean.getFunctionName().equals(FunctionName.VNCS_APP_TERMINALBIZ)) { // 业务关系同步
			terminalBiz(respBean, reqBean);
		} else if (respBean.getFunctionName().equals(FunctionName.VNCS_APP_SENDCOMMAND)) { // 指令下发
			sendCommand(respBean, reqBean);
		} else if (reqBean.getFunctionName().equals(FunctionName.VNCS_APP_SENDUPDATE)) {
			if (FLAG == false) {
				FLAG = true;
				SendUpdateThread suThread = new SendUpdateThread(reqBean);
				Thread work1 = new Thread(suThread);
				work1.start();
			}
		} else if (reqBean.getFunctionName().equals(FunctionName.VNCS_APP_SENDHOLS)) {
			sendHolesCommand(respBean, reqBean);
		} else if (reqBean.getFunctionName().equals(FunctionName.VNCS_APP_SENDLINE)) {
			sendLineCommand(respBean, reqBean);
		} else if (reqBean.getFunctionName().equals(FunctionName.VNCS_APP_SENDLOCK)) {
			sendLockCommand(respBean, reqBean);
		} else if (reqBean.getFunctionName().equals(FunctionName.VNCS_APP_EXCEPTIONSETTING)) {
			sendExceptionSettingCommand(respBean, reqBean);
		} else {
			throw new InvalidXmlFormatException(respBean.getFunctionName() + "接口不存在"); // function.name错误
		}
		// } catch (SQLException sqlExp) {
		// LOGGER.error("SetResponseBean.setBean()中出现异常SQLException:" +
		// sqlExp.getMessage());
		// throw new Exception(sqlExp.getMessage());
		// } catch (Exception e) {
		// e.printStackTrace();
		// LOGGER.error("SetResponseBean.setBean()中出现异常:" + e.getMessage());
		// throw e;
		// }

		return true;
	}

	private static void sendExceptionSettingCommand(ResponseBean respBean,
			RequestBean reqBean) throws UnsupportedEncodingException, SendInfoException, ParseException, DBErrorException {
		SendCommandDAO sendCommandDAO = (SendCommandDAO) SpringBootStrap
		.getInstance().getBean("sendCommandDAO");

		SendExceptionSettingCommandBean.sendCommand(reqBean, sendCommandDAO);
		
		/* 指令下发平台，更新状态 */
		sendCommandDAO.UpdateOneTimes(reqBean.getMsg_id());
	}

	private static void sendLineCommand(ResponseBean respBean,
			RequestBean reqBean) throws SendInfoException, ParseException, DBErrorException, UnsupportedEncodingException {
		SendCommandDAO sendCommandDAO = (SendCommandDAO) SpringBootStrap
				.getInstance().getBean("sendCommandDAO");
		// 1.String s=UUID.randomUUID().toString().replaceAll("-", "");
		/* 指令入库 */
		// sendCommandDAO.saveHolesCommand(reqBean);
		/* 指令下发 */
		SendLineCommandBean.sendCommand(reqBean, sendCommandDAO);

		/* 指令下发平台，更新状态 */
		sendCommandDAO.UpdateOneTimes(reqBean.getMsg_id());
	}

	private static void sendLockCommand(ResponseBean respBean,
			RequestBean reqBean) throws SendInfoException, ParseException, DBErrorException, UnsupportedEncodingException {
		SendCommandDAO sendCommandDAO = (SendCommandDAO) SpringBootStrap
				.getInstance().getBean("sendCommandDAO");
		/* 指令下发 */
		SendLineCommandBean.sendCommand_lock(reqBean, sendCommandDAO);

		/* 指令下发平台，更新状态 */
		//sendCommandDAO.UpdateOneTimes(reqBean.getMsg_id());
	}

	/**
	 * 终端信息同步
	 * 
	 * @param respBean
	 * @param reqBean
	 * @throws SQLException
	 */
	private static void terminalInfo(ResponseBean respBean, RequestBean reqBean)
			throws SQLException {
		TerminalInfoDAO terminalInfoDAO = (TerminalInfoDAO) SpringBootStrap
				.getInstance().getBean("terminalInfoDAO");

		/* 获取该终端的日汇总记录总数 */
		int totalCount = 0;

		/* 获取该终端的分页日数据 */
		List<TerminalRecord> terminalRecord = null;
		if (reqBean.getScope().equals("0")) {
			totalCount = terminalInfoDAO.getCountOfSum(reqBean);
			terminalRecord = terminalInfoDAO.getPagedRecord(reqBean);
		} else {
			totalCount = terminalInfoDAO.getCountAllOfSum();
			terminalRecord = terminalInfoDAO.getAllRecord(reqBean);
		}
		respBean.setTerminalRecord(terminalRecord);

		/* 分页参数设置 */
		setRespPageParam(respBean, reqBean, totalCount,
				null == terminalRecord ? 0 : terminalRecord.size());
	}

	/**
	 * 车辆信息同步
	 * 
	 * @param respBean
	 * @param reqBean
	 * @throws SQLException
	 */
	private static void vehicleInfo(ResponseBean respBean, RequestBean reqBean)
			throws SQLException {

		VehicInfoDAO vehicInfoDAO = (VehicInfoDAO) SpringBootStrap
				.getInstance().getBean("vehicInfoDAO");

		/* 获取该车辆的日汇总记录总数 */
		int totalCount = 0;

		/* 获取该车辆的分页日列表 */
		List<VehicRecord> vehicRecord = null;
		if (reqBean.getScope().equals("0")) {
			totalCount = vehicInfoDAO.getCountOfVehicDailyOilSum(reqBean);
			vehicRecord = vehicInfoDAO.getPagedVehicRecord(reqBean);
		} else if (reqBean.getScope().equals("1")) {
			totalCount = vehicInfoDAO.getCountAllOfVehicSum();
			vehicRecord = vehicInfoDAO.getAllVehicRecords(reqBean);
		}
		respBean.setVehicRecord(vehicRecord);
		/* 分页参数设置 */
		setRespPageParam(respBean, reqBean, totalCount, null == vehicRecord ? 0
				: vehicRecord.size());

	}

	/**
	 * 终端命令下发
	 * 
	 * @param respBean
	 * @param reqBean
	 * @throws DBErrorException
	 * @throws DBErrorException
	 * @throws SendInfoException
	 * @throws SendInfoException
	 * @throws UnsupportedEncodingException 
	 * @throws Exception
	 */
	private static void sendCommand(ResponseBean respBean, RequestBean reqBean)
			throws DBErrorException, SendInfoException, UnsupportedEncodingException {
		// boolean b =
		// ActiveCoreService.getInstance().getMainCore(Constant.CORE_ID,
		// Integer.parseInt(Config.props.getProperty("core.active.time")));
		// //判断是否是主核心
		// if(b){
		SendCommandDAO sendCommandDAO = (SendCommandDAO) SpringBootStrap
				.getInstance().getBean("sendCommandDAO");

		// 1.String s=UUID.randomUUID().toString().replaceAll("-", "");
		/* 指令入库 */
		sendCommandDAO.saveCommand(reqBean);
		/* 指令下发 */
		SendCommandBean.sendCommand(reqBean);

		/* 指令下发平台，更新状态 */
		sendCommandDAO.UpdateOneTimes(reqBean.getMsg_id());
		// }else{
		// LOGGER.info(LogFormatter.formatMsg("SetResponseBean","当前核心不为主核心，不进行命令下发"));
		// }
	}

	/**
	 * 下行透传请假销假
	 * 
	 * @param respBean
	 * @param reqBean
	 * @throws DBErrorException
	 * @throws DBErrorException
	 * @throws SendInfoException
	 * @throws SendInfoException
	 * @throws ParseException
	 * @throws Exception
	 */
	private static void sendHolesCommand(ResponseBean respBean,
			RequestBean reqBean) throws DBErrorException, SendInfoException,
			ParseException {
		// boolean b =
		// ActiveCoreService.getInstance().getMainCore(Constant.CORE_ID,
		// Integer.parseInt(Config.props.getProperty("core.active.time")));
		// //判断是否是主核心
		// if(b){
		SendCommandDAO sendCommandDAO = (SendCommandDAO) SpringBootStrap
				.getInstance().getBean("sendCommandDAO");

		// 1.String s=UUID.randomUUID().toString().replaceAll("-", "");
		/* 指令入库 */
		// sendCommandDAO.saveHolesCommand(reqBean);
		/* 指令下发 */
		SendHolesCommandBean.sendCommand(reqBean, sendCommandDAO);

		/* 指令下发平台，更新状态 */
		sendCommandDAO.UpdateOneTimes(reqBean.getMsg_id());
		// }else{
		// LOGGER.info(LogFormatter.formatMsg("SetResponseBean","当前核心不为主核心，不进行命令下发"));
		// }
	}

	/**
	 * 终端业务关系同步
	 * 
	 * @param respBean
	 * @param reqBean
	 * @throws InvalidXmlFormatException
	 */
	private static void terminalBiz(ResponseBean respBean, RequestBean reqBean)	throws InvalidXmlFormatException {

		TerminalBizDAO terminalBizDAO = (TerminalBizDAO) SpringBootStrap.getInstance().getBean("terminalBizDAO");

		/* 获取记录总数 */
		int totalCount = terminalBizDAO.getCountOfSum(reqBean);

		/* 获取列表 */
		List<TerminalBizRecord> terminalBizRecord = terminalBizDAO.getPagedRecord(reqBean);
		respBean.setTerminalBizRecord(terminalBizRecord);

		/* 分页参数设置 */
		setRespPageParam(respBean, reqBean, totalCount,	null == terminalBizRecord ? 0 : terminalBizRecord.size());
	}

	/**
	 * 将分组油耗汇总信息列表字段排序
	 * 
	 * @param sumList
	 *            未排序的列表
	 * @param orderAsc
	 *            是否升序
	 * @return 排序后的列表
	 */
	@SuppressWarnings( { "unchecked", "unused" })
	private static List<SummaryRecord> getOrderedSumList(
			List<SummaryRecord> sumList, String orderAsc) {
		// Collections排序后为升序
		Collections.sort(sumList);

		if (orderAsc.equals("true")) { // 升序
			return sumList;
		} else { // 降序
			List<SummaryRecord> orderedList = new ArrayList<SummaryRecord>();
			for (int i = sumList.size() - 1; i >= 0; i--) {
				orderedList.add(sumList.get(i));
			}
			return orderedList;
		}
	}

	/**
	 * 根据分页参数，从全部数据中获取分页列表
	 * 
	 * @param allDataList
	 *            全部数据列表
	 * @param pageNo
	 *            第几页
	 * @param pageSize
	 *            每页记录数
	 * @return 分页数据列表
	 */
	@SuppressWarnings("unused")
	private static List<SummaryRecord> getPagedSumList(
			List<SummaryRecord> allDataList, int pageNo, int pageSize) {
		int minNum = pageSize * (pageNo - 1) + 1;
		int maxNum = pageSize * pageNo;

		List<SummaryRecord> pagedSumList = new ArrayList<SummaryRecord>();
		int totalCount = allDataList.size();
		if (minNum <= totalCount && minNum > 0) {
			for (int i = minNum - 1; i < maxNum; i++) {
				pagedSumList.add(allDataList.get(i));
				if ((i + 1) >= totalCount) {
					break;
				}
			}
		}

		return pagedSumList;
	}

	/**
	 * 设置应答中的分页参数
	 * 
	 * @param respBean
	 * @param reqBean
	 * @param totalCount
	 */
	private static void setRespPageParam(ResponseBean respBean,
			RequestBean reqBean, int totalCount, int recordNum) {
		int nPageCount = totalCount / Integer.parseInt(reqBean.getPageSize());
		if (0 != totalCount % Integer.parseInt(reqBean.getPageSize())) {
			nPageCount = nPageCount + 1;
		}
		respBean.setPageCount(nPageCount); // 总页数
		respBean.setPageNo(Integer.parseInt(reqBean.getPageNo()));// 当前页号
		respBean.setRecordNum(recordNum); // 当前页记录数
		respBean.setTotalCount(totalCount); // 记录总数
	}

}
