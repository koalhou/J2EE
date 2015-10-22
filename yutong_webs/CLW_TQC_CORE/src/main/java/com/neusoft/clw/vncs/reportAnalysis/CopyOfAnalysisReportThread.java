package com.neusoft.clw.vncs.reportAnalysis;

/**
 * 
 */

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.configuration.Config;
import com.neusoft.clw.constant.Constant;
import com.neusoft.clw.info.bean.ClwSecData;
import com.neusoft.clw.info.bean.DayReport;
import com.neusoft.clw.info.bean.HisCalRecord;
import com.neusoft.clw.info.bean.RapidBean;
import com.neusoft.clw.info.bean.RealTimeRecord;
import com.neusoft.clw.info.bean.RotateSpeed;
import com.neusoft.clw.info.bean.VehicleSpeed;
import com.neusoft.clw.info.dao.ReportDAO;
import com.neusoft.clw.info.util.tool.CommonDataUtil;
import com.neusoft.clw.spring.SpringBootStrap;
import com.neusoft.clw.vncs.cachemanager.HarmDefCacheManager;
import com.neusoft.clw.vncs.domain.HarmDefBean;
import com.neusoft.clw.vncs.inside.msg.utils.DateUtil;
import com.neusoft.clw.vncs.util.StringUtil;

/**
 * @author tmc
 */
public class CopyOfAnalysisReportThread extends Thread implements Runnable {
    @SuppressWarnings("unused")
    private static final String NAME = "AnalysisAlgorithmThread";

    private Logger log = LoggerFactory.getLogger(CopyOfAnalysisReportThread.class);

    private String strVin;

    private String strDate;

    private List<RealTimeRecord> realTimeRecord;

    private ReportDAO reportDAO;

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // String sumEngine = "0"; // 发动机运行时间
    @SuppressWarnings("unused")
    private CopyOfAnalysisReportThread() {
    }

    public CopyOfAnalysisReportThread(String strVin, String strDate, List<RealTimeRecord> realTimeRecord) {
        this.strVin = strVin;
        this.strDate = strDate;
        this.realTimeRecord = realTimeRecord;
        this.reportDAO = (ReportDAO) SpringBootStrap.getInstance().getBean("reportDAO");
    }

    @SuppressWarnings("static-access")
    public void run() {
        log.info("线程开始======strVin：" + strVin + "strDate:" + strDate);
        MyCountDown myCount = MyCountDown.instance();
        myCount.countAdd();
        try {
//        	strVin = "LZYTGG55555667788";
            // Thread.sleep(1000*60*4);
            // 获取实时数据
            this.realTimeRecord = reportDAO.getRecord(strVin, strDate);
            // sumEngine = reportDAO.getSumEngine(strVin, strDate);获取发动机时间
//            // 超速次数，超速时间
//            reportDAO.insertOverSpeedInfo(strVin,strDate,"32");
//            reportDAO.updateCsdsData(strVin, strDate, "32");
            analyseREAl();// 分析实时数据
//            analyseSEC("");// 分析车联网秒记录
//            analyseJjs();// 分析急加速 急减速
            // 分析开关量
            // analyseOnOffList();

            /* 疲劳驾驶算法，add by zhanglzh,2011-8-10 */
//            analyseFatigueDrive();
            // 超速次数，超速时间
//            reportDAO.insertOverSpeedInfo(strVin,strDate,"32");
            
            reportDAO.updateCsdsDataXc(strVin, strDate, "32");            
            //超转
            reportDAO.updateCsdsDataXc(strVin, strDate, "49");
//            空档滑行
            reportDAO.updateCsdsDataXc(strVin, strDate, "46");
//            超长怠速
            reportDAO.updateCsdsDataXc(strVin, strDate, "50");
//            空调怠速
            reportDAO.updateCsdsDataXc(strVin, strDate, "51");
//            非经济区运行
            reportDAO.updateCsdsDataXc(strVin, strDate, "81");
//            疲劳驾驶
            reportDAO.updateCsdsDataXc(strVin, strDate, "33");
            //生成非法点火，和告警明细
            reportDAO.makczdetail(strVin, strDate);           
            //校车异常乘车统计2012-03-14
            reportDAO.xcYcStatistics(strVin, strDate);
            //校车上座率统计2012-03-14
            reportDAO.xcSzlStatistics(strVin, strDate);
            //校车非法点火
            reportDAO.updateCsdsDataXc(strVin, strDate, "54");
            //校车超载
            reportDAO.updateCsdsDataXc(strVin, strDate, "72");
            // 更新结束时间
            reportDAO.updateEndData(strVin, strDate);
            
            myCount.countDown();// 线程结束
            log.info("线程结束======strVin：" + strVin + ",strDate:" + strDate);
            this.interrupted();
        } catch (Exception e) {
            myCount.countDown();
            e.printStackTrace();
            log.info("报表分析线程异常=strVin：" + strVin + ",strDate:" + strDate + e.toString());
            this.interrupted();
            return;
        }

        // System.out.println("报表分析线程异常===========：" + e.toString());
    }
    

    /**
     * 分析疲劳驾驶
     * @throws Exception
     */
    @SuppressWarnings("unused")
	private void analyseFatigueDrive() throws Exception {
        log.info("开始分析车机【" + strVin + "】在【" + strDate + "】的疲劳驾驶。");
        int totalFagDriveNum = 0; // 疲劳驾驶次数
        long totalFagDriveTime = 0; // 疲劳驾驶总时间
        boolean getNextDayDataFlag = false; // 是否获取第二天的数据的标志
        boolean findFag = false; // 找到一次真实的疲劳驾驶的标志
        Date nextDate = DateUtil.getDateByDiscreDays(strDate, 1);
        Date date = DateUtil.string2date("yyyy-MM-dd", strDate);

        /* 查询该车本次统计的开始时间 */
        String preDateStr = DateUtil.changeDateToFull8String(DateUtil.getDateByDiscreDays(strDate,
                -1));
        HisCalRecord hisCalRecord = reportDAO.getVehicFatigueTimeByDate(strVin, preDateStr);
        Date fatigueTime = (null == hisCalRecord) ? null : hisCalRecord.getEND_TIME();
        log.debug("车机【" + strVin + "】在【" + preDateStr + "】的算法判断结束时间为【" + fatigueTime + "】");

        // 根据历史起始判断时间，获取算法所需的源数据列表
        int startNum = getBeginCalFagPos(fatigueTime);
        List<RealTimeRecord> sourceList = realTimeRecord;

        /* 开始疲劳驾驶判断 */
        Date fagStartTime = null; // 一次疲劳驾驶的开始时间
        Date fagEndTime = null; // 一次疲劳驾驶的结束时间
        long duration = 0; // 一次疲劳驾驶的持续时间，单位：秒
        Date terminalTime = null; // 终端时间
        RealTimeRecord fagOnRecord = null; // 疲劳驾驶开的记录
        RealTimeRecord fagOffRecord = null; // 疲劳驾驶关的记录
        RealTimeRecord parkingRecord = null; // 停车记录
        String fag_alert = null;
        for (int i = startNum; i < sourceList.size(); i++) {
            fagOnRecord = sourceList.get(i);
            fag_alert = fagOnRecord.getFATIGUE_ALERT();
            terminalTime = DateUtil.full14String2date(fagOnRecord.getTERMINAL_TIME());
            if (!StringUtil.isEmpty(fag_alert) && fag_alert.equals(Constant.FAG_DRIVE_ALERT_ON)
                    && satisfyFagSpace(terminalTime, fatigueTime)) { // 疲劳驾驶报警开，且距上次疲劳驾驶的结束时间已满4小时
//                if (terminalTime.getTime() >= nextDate.getTime()) {
//                    log.info("该疲劳驾驶报警开的时间【" + terminalTime + "】已经是第二天的时间，当天将不作处理！");
//                    break;
//                }

                // 记录本次疲劳驾驶的开始时间
                fagStartTime = terminalTime;
                // 本次的疲劳驾驶的结束时间暂时初始化为当前记录终端时间
                fagEndTime = terminalTime;
                while (false == findFag && (++i < sourceList.size() || false == getNextDayDataFlag)) {
                    // 顺次获取下一条记录，如果当天已没有记录，且还未加入第二天的记录，则追加第二天记录入源数据列表，并取出一条
                    if(needSupplySource(i, getNextDayDataFlag, sourceList)){
                        //查找第二天12点前的记录
                        sourceList = reportDAO
                              .getRecordList(strVin, nextDate, DateUtil.getDateByDiscreHours(DateUtil.getDateByDiscreDays(strDate, 1),
                                      Constant.HOURS_OF_GET_NEXT_SOURCE_DATA));
                        i=0;
                        getNextDayDataFlag = true;
                    }
                    
                    if (null == sourceList || i >= sourceList.size()) { // 源数据列表已被遍历完
                        log.debug("源数据列表已被遍历完。");
                        /* 处理遍历完信息列表的情况下的疲劳驾驶数据 */
                        if (processNoRecordExpFrag(fagOnRecord)) { // 有异常情况下的疲劳驾驶记录
                            totalFagDriveNum++;
                            totalFagDriveTime = totalFagDriveTime + Constant.INIT_FAG_DRIVE_SEC;
                            fatigueTime = DateUtil.getDateByDiscreMinutes(fagStartTime,
                                    Constant.MIN_PARKING_MINUTES_OF_CAN_FAG);
                        }
                        break;
                    } else {
                        fagOffRecord = sourceList.get(i);
                    }

                    fag_alert = fagOffRecord.getFATIGUE_ALERT();
                    if (!StringUtil.isEmpty(fag_alert)
                            && fag_alert.equals(Constant.FAG_DRIVE_ALERT_OFF)) { // 疲劳驾驶报警关
                        // 记录本次疲劳驾驶结束时间
                        fagEndTime = DateUtil.full14String2date(fagOffRecord.getTERMINAL_TIME());

                        while (++i < sourceList.size() || false == getNextDayDataFlag) {
                            if(needSupplySource(i, getNextDayDataFlag, sourceList)){
                                //查找第二天12点前的记录
                                sourceList = reportDAO
                                      .getRecordList(strVin, nextDate, DateUtil.getDateByDiscreHours(DateUtil.getDateByDiscreDays(strDate, 1),
                                              Constant.HOURS_OF_GET_NEXT_SOURCE_DATA));
                                i=0;
                                getNextDayDataFlag = true;
                            }

                            if (null == sourceList || i >= sourceList.size()) {
                                log.debug("源数据列表已被遍历完。");
                                /* 处理遍历完信息列表的情况下的疲劳驾驶数据 */
                                if (processNoRecordExpFrag(fagOnRecord)) { // 有异常情况下的疲劳驾驶记录
                                    totalFagDriveNum++;
                                    totalFagDriveTime = totalFagDriveTime
                                            + Constant.INIT_FAG_DRIVE_SEC;
                                    fatigueTime = DateUtil.getDateByDiscreMinutes(fagStartTime,
                                            Constant.MIN_PARKING_MINUTES_OF_CAN_FAG);
                                }

                                findFag = true;
                                break;
                            } else {
                                parkingRecord = sourceList.get(i);
                            }

                            fag_alert = parkingRecord.getFATIGUE_ALERT();
                            terminalTime = DateUtil.full14String2date(parkingRecord
                                    .getTERMINAL_TIME());
                            if ((StringUtil.isEmpty(fag_alert) && terminalTime.getTime()
                                    - fagEndTime.getTime() >= Constant.MIN_PARKING_TIME_OF_CAN_FAG) // 连续上报状态延续信号达20分钟
                                    || (!StringUtil.isEmpty(fag_alert) && terminalTime.getTime()
                                            - fagEndTime.getTime() > Constant.MIN_PARKING_TIME_OF_CAN_FAG)) {// 非延续信号，但距报警关时间已超过20分钟
                                /* 停车满20分钟，该次疲劳驾驶报警结束,记录该次疲劳驾驶信息 */
                                duration = Constant.INIT_FAG_DRIVE_SEC
                                        + (fagEndTime.getTime() - fagStartTime.getTime()) / 1000;
                                saveAndSyncFragDriveRecord(fagOnRecord, fagEndTime, duration);
                                totalFagDriveNum++;
                                totalFagDriveTime = totalFagDriveTime + duration;

                                fatigueTime = fagEndTime;

                                /* 如果当前点是疲劳驾驶报警开，查看其是否在上次疲劳驾驶的4小时之后 */
                                if (!StringUtil.isEmpty(fag_alert)
                                        && fag_alert.equals(Constant.FAG_DRIVE_ALERT_ON)) {
                                    if (terminalTime.getTime() < nextDate.getTime() 
                                            && (terminalTime.getTime() - fatigueTime.getTime()) >= Constant.INIT_FAG_DRIVE_SEC * 1000) {
                                        
                                        // 本次疲劳驾驶报警开距上次疲劳驾驶等于或超过4小时，且当前时间<第二天，则该点为下次疲劳驾驶的判断起点
                                        i--;
                                    }
                                }

                                findFag = true; // 找到一次疲劳驾驶
                                break;
                            } else if (!StringUtil.isEmpty(fag_alert)
                                    && fag_alert.equals(Constant.FAG_DRIVE_ALERT_OFF)) {
                                /* 当前点为报警关，但与报警开的时间<20分钟，则该点作为新的报警关时间，继续寻找停车时段 */
                                fagOffRecord = (RealTimeRecord) CommonDataUtil
                                        .depthClone(parkingRecord);
                                fagEndTime = DateUtil.full14String2date(fagOffRecord
                                        .getTERMINAL_TIME());
                                continue;
                            } else if (StringUtil.isEmpty(fag_alert)) {
                                /* 当前为状态延续信号，继续寻找停车时段 */
                                continue;
                            } else {
                                // 其他情况，则终止本次寻找停车时段的操作,继续找本次疲劳驾驶结束的记录
                                break;
                            }
                        }
                    } else { // 非疲劳驾驶报警关信号
                        /* 查看当前记录与报警开的记录的时差是否超过12小时，如果超过，表明数据有误报，处理本次疲劳驾驶为4小时 */
                        terminalTime = DateUtil.full14String2date(fagOffRecord.getTERMINAL_TIME());
                        if (terminalTime.getTime() - fagStartTime.getTime() >= Constant.MAX_PERIOD_TIME_OF_FD_CAL) {
                            // 判断时间已超出12小时，处理本次疲劳驾驶为4小时,将本次疲劳驾驶入库，并统计总数据
                            fagEndTime = DateUtil.getDateByDiscreMinutes(fagStartTime,
                                    Constant.MIN_PARKING_MINUTES_OF_CAN_FAG);
                            saveAndSyncFragDriveRecord(fagOnRecord, fagEndTime,
                                    Constant.INIT_FAG_DRIVE_SEC);
                            totalFagDriveNum++;
                            totalFagDriveTime = totalFagDriveTime + Constant.INIT_FAG_DRIVE_SEC;

                            fatigueTime = fagEndTime;
                            break;
                        }
                    }
                }
                
                if(getNextDayDataFlag){
                    //已追加了第二天的记录，则不再寻找下一次疲劳驾驶（因其已是第二天的时间）
                    break;
                }

                duration = 0;
                findFag = false; // 开始下一次疲劳驾驶的寻找
            }
        }

        /* 疲劳驾驶统计信息入库 */
        if (totalFagDriveNum > 0 && totalFagDriveTime > 0) {
            // 将当天的疲劳驾驶统计数据入库
            reportDAO.updateFagDriveSyncData(strVin, strDate, totalFagDriveNum, totalFagDriveTime);// 更新疲劳驾驶
            log.debug("车机【" + strVin + "】在【" + strDate + "】的疲劳驾驶统计信息已入库");
        }

        /* 更新疲劳驾驶的开始判断时间 */
        if (null == fatigueTime || fatigueTime.getTime() < date.getTime()) {
            // 如果判断时间为空或<当天，则将其初始为第二天0点-4小时（即当天20点）
            fatigueTime = DateUtil.getDateByDiscreHours(nextDate, -Constant.INIT_FAG_DRIVE_HOURS);
        }
        reportDAO.insertOrUpdateHisCalRecord(strVin, strDate, fatigueTime);
        log.debug("已更新车机【" + strVin + "】在【" + strDate + "】的算法判断结束时间为【" + fatigueTime + "】");
        log.info("分析车机【" + strVin + "】在【" + strDate + "】的疲劳驾驶算法结束。");
    }

    /**
     * 查看某疲劳驾驶距上次疲劳驾驶的时间是否已满4小时
     * @param turnOnTime 该次疲劳驾驶报警开的时间
     * @param fatigueTime 上次疲劳驾驶的结束时间
     * @return 是否满足条件
     */
    private boolean satisfyFagSpace(Date turnOnTime, Date fatigueTime) {
        if (null == fatigueTime) { // 没有历史判断时间记录
            return true;
        }
        if (turnOnTime.getTime() - fatigueTime.getTime() >= Constant.INIT_FAG_DRIVE_SEC * 1000) {
            // 距上次疲劳驾驶结束时间已满4小时，可以认为是一次新的疲劳驾驶
            return true;
        }
        return false;
    }

    /**
     * 处理信息记录遍历完情况下的疲劳驾驶数据
     * @param fagOnRecord 疲劳驾驶开的源数据记录
     * @return 若有疲劳驾驶，返回true;否则返回false
     */
    private boolean processNoRecordExpFrag(RealTimeRecord fagOnRecord) {
        // 源数据的标准判断结束时间：第二天12点
        Date standardNextTime = DateUtil.getDateByDiscreHours(DateUtil.getDateByDiscreDays(strDate,
                1), Constant.HOURS_OF_GET_NEXT_SOURCE_DATA);
        // 疲劳驾驶报警开的时间
        Date startTime = DateUtil.full14String2date(fagOnRecord.getTERMINAL_TIME());

        /* 将异常情况下的疲劳驾驶的参数入库:处理疲劳驾驶报警开后，后续没有数据的情形 */
        if ((standardNextTime.getTime() - startTime.getTime()) >= Constant.MAX_PERIOD_TIME_OF_FD_CAL) {
            // 报警开时间距判断日第二天12点已超过12小时，则疲劳驾驶时长置为4小时.将该次疲劳驾驶入库
            Date endTime = DateUtil.getDateByDiscreMinutes(startTime,
                    Constant.MIN_PARKING_MINUTES_OF_CAN_FAG);
            saveAndSyncFragDriveRecord(fagOnRecord, endTime, Constant.INIT_FAG_DRIVE_SEC);
            return true;
        }
        return false;
    }

    /**
     * 保存一次疲劳驾驶详细信息，并统计疲劳驾驶数据
     * @param fagOnRecord 疲劳驾驶开的源数据记录
     * @param fagEndTime 疲劳驾驶结束时间
     * @param durations 本次疲劳驾驶时长
     */
    private void saveAndSyncFragDriveRecord(RealTimeRecord fagOnRecord, Date fagEndTime,
            long durations) {
        reportDAO.makePldetail(strVin, strDate, fagOnRecord, fagEndTime, durations);
        log.debug("已将车机【" + strVin + "】的一条从【" + fagOnRecord.getTERMINAL_TIME() + "】到【" + fagEndTime
                + "】的疲劳驾驶记录入库");
    }

    /**
     * 判断是否设置终端信息列表数据
     * @param i 当前记录在列表中的位置
     * @param getNextDayDataFlag 是否获取第二天记录的标志
     * @param sourceList 终端信息列表数据
     * @param 是否需要追加第二天的数据
     */
    private boolean needSupplySource(int i, boolean getNextDayDataFlag,
            List<RealTimeRecord> sourceList) {
        if (i < sourceList.size()) { // 列表中有数据
            return false;
        } else if (false == getNextDayDataFlag) { // 当天的源数据列表已遍历完，且没有追加过第二天的源数据
            return true; // 追加第二天记录
        } else { // 当天和第二天的数据都已遍历完
            return false;
        }
    }
    
    /**
     * 根据疲劳驾驶的判断时间获取疲劳驾驶算法所需的源数据列表中的起始计算条目
     * @param fatigueTime 疲劳驾驶的判断时间
     * @return 起始计算条目
     */
    private int getBeginCalFagPos(Date fatigueTime) {
        if (null != fatigueTime) {
            // 取上次判断结束时间的4小时之后的记录
            Date startTime = DateUtil.getDateByDiscreHours(fatigueTime,
                    Constant.INIT_FAG_DRIVE_HOURS);
            Date teminalTime = null;
            int i = 0;
            for (; i < realTimeRecord.size(); i++) {
                teminalTime = DateUtil.full14String2date(realTimeRecord.get(i).getTERMINAL_TIME());
                if (teminalTime.getTime() >= startTime.getTime()) {
                    // 终端上报时间>=疲劳驾驶初始判断时间，则从该记录开始进行疲劳驾驶算法处理
                    break;
                }
            }
            return i;
        }
        return 0;
    }

    // 分析实时数据第一条和最后一条
    @SuppressWarnings("unused")
	private void analyseBeginEnd(List<RealTimeRecord> rows) throws Exception {
        // 总里程、总油耗等
        float COUNT_MILEAGE = 0;// 总行车里程
        float MILEAGE = 0;// 行车里程(当日差值)
        float COUNT_OIL_TOTAL = 0; // 总燃油消耗
        float OIL = 0; // 燃油消耗(当日差值)
        String OIL_INSTANT = "0";
        DecimalFormat def = new DecimalFormat(".#####");
        if (rows.size() == 1) {// 一条记录
            if (rows.get(0).getMILEAGE()!=null&&!rows.get(0).getMILEAGE().equals("FFFF")) {
                COUNT_MILEAGE = Float.parseFloat(realTimeRecord.get(0).getMILEAGE());
            } else {
                COUNT_MILEAGE = 0;
            }
            if (!rows.get(0).getOIL_TOTAL().equals("FFFF")) {
               COUNT_OIL_TOTAL = Float.parseFloat(rows.get(0).getOIL_TOTAL());
            } else {
            	COUNT_OIL_TOTAL = 0;            	
            }
            if (rows.get(0).getOIL_INSTANT().equals("FFFF")) {
                OIL_INSTANT = "0";
            } else {
                OIL_INSTANT = rows.get(0).getOIL_INSTANT();
            }
            // 更新车辆状态表
//            reportDAO.updateVeztdOilMieage(def.format(COUNT_MILEAGE), def.format(MILEAGE), def
//                    .format(COUNT_OIL_TOTAL), def.format(OIL), rows.get(0).getTERMINAL_TIME(),
//                    OIL_INSTANT, strVin, strDate);
        } else if (rows.size() > 1) {// 两条记录
            int len = rows.size() - 1;
            // System.out.println("rows(1).getMILEAGE():"
            // + realTimeRecord.get(1).getMILEAGE()
            // + "::rows(0).getMILEAGE()"
            // + rows.get(0).getMILEAGE());
            if (rows.get(0).getMILEAGE()!= null && !rows.get(0).getMILEAGE().equals("FFFF") && !rows.get(len).getMILEAGE().equals("FFFF")) 
            {
                COUNT_MILEAGE = Float.parseFloat(rows.get(len).getMILEAGE());
                MILEAGE = Float.parseFloat(rows.get(len).getMILEAGE())
                        - Float.parseFloat(rows.get(0).getMILEAGE());               
            } else {
                COUNT_MILEAGE = 0;
                MILEAGE = 0;
            }
            if (rows.get(0).getOIL_TOTAL()!=null && !rows.get(0).getOIL_TOTAL().equals("FFFF")
                    && !rows.get(len).getOIL_TOTAL().equals("FFFF")) {
                COUNT_OIL_TOTAL = Float.parseFloat(rows.get(len).getOIL_TOTAL());
                OIL = Float.parseFloat(rows.get(len).getOIL_TOTAL())
                        - Float.parseFloat(rows.get(0).getOIL_TOTAL());
            } else {               
                COUNT_OIL_TOTAL = 0;
                OIL = 0;
            }
//            if (rows.get(len).getOIL_INSTANT() == null
//                    || rows.get(len).getOIL_INSTANT().equals("FFFF")) {
//                OIL_INSTANT = "0";
//            } else {
//                OIL_INSTANT = rows.get(len).getOIL_INSTANT();
//            }
            //增加对负数的修正2011-08-18
            if(MILEAGE<0){ 	MILEAGE=0; }
            if(OIL<0){ OIL=0; }
            
            // 更新车辆状态表            
//            reportDAO.updateVeztdOilMieage(def.format(COUNT_MILEAGE), def.format(MILEAGE), def
//                    .format(COUNT_OIL_TOTAL), def.format(OIL), rows.get(len).getTERMINAL_TIME(),
//                    OIL_INSTANT, strVin, strDate);
        } else {
            COUNT_OIL_TOTAL = 0;
            OIL = 0;
        }
        // 更新日报表
        reportDAO.updateOilMieage(def.format(COUNT_MILEAGE), def.format(MILEAGE), def
                .format(COUNT_OIL_TOTAL), def.format(OIL), strVin, strDate);

    }

    // 分析实时数据
    private void analyseREAl() throws Exception {
    	try{
	    	 // 总里程、总油耗等
	    	float COUNT_MILEAGE_START = 0;// 总行车里程开始
	        float COUNT_MILEAGE = 0;// 总行车里程
	        float MILEAGE = 0;// 行车里程(当日差值)
	        float COUNT_OIL_TOTAL_START = 0; // 总燃油消耗开始
	        float COUNT_OIL_TOTAL = 0; // 总燃油消耗
	        float OIL = 0; // 燃油消耗(当日差值)
	        @SuppressWarnings("unused")
			String OIL_INSTANT = "0";
	        DecimalFormat def = new DecimalFormat(".#####");        
	        @SuppressWarnings("unused")
			DayReport reportResult = new DayReport();// 存当日累计结果
	        List<RealTimeRecord> rows = realTimeRecord;
	        @SuppressWarnings("unused")
			RealTimeRecord cSPerv = null, CsNonce = null,perv = null, nonce = null; 
	//        plPerv = null, plNonce = null, 
	        float diff = 0;
	        long reportTimeSpace = Long.valueOf(Config.props.getProperty("reportTimeSpace"));
	        boolean continuous;
	        @SuppressWarnings("unused")
			float SPEEDING_OIL = 0;// 本日超速下油耗
	        @SuppressWarnings("unused")
			float SPEEDING_MILEAGE = 0;// 本日超速下行驶里程
	        @SuppressWarnings("unused")
	        RealTimeRecord ktdsBegin = null;// 空调怠速开始
	        @SuppressWarnings("unused")
	        RealTimeRecord ktdsEnd = null;// 空调怠速结束
	        @SuppressWarnings("unused")
	        RealTimeRecord kdBegin = null;// 空档滑行开始
	        @SuppressWarnings("unused")
	        RealTimeRecord kdEnd = null;// 空档滑行结束
	        @SuppressWarnings("unused")
	        float IDLAIR_SPD = (float) 2; // 车速阀值(km/h)-怠速空调
	        @SuppressWarnings("unused")
	        float IDLAIR_RPM = 800; // 发动机转速(r/min)-怠速空调
	        @SuppressWarnings("unused")
	        float IDLAIR_TIME = 10 * 60; // 持续时间(min)-怠速空调
	        long reltime = 10;// 单条记录的持续时间  
	        int po = 0; // 连续的时候赋值为0，断开开的时候加1，如果大于等于2则说明前一点为为单点
	        HarmDefBean hdf = HarmDefCacheManager.harmdefMap.get(strVin);
	        if (hdf != null) {
	            // 空调怠速
	            if (hdf.getIdlair_spd() != null && hdf.getIdlair_rpm() != null
	                    && hdf.getIdlair_time() != null) {
	                IDLAIR_SPD = Float.valueOf(hdf.getIdlair_spd()).floatValue();
	                IDLAIR_RPM = Float.valueOf(hdf.getIdlair_rpm()).floatValue();
	                IDLAIR_TIME = Float.valueOf(hdf.getIdlair_time()).floatValue();
	            }
	            // log.info("AnalysisReportThread没有vin:"+strVin+"车辆的不良驾驶定义信息，无法生成此车辆相关报表数据！");
	            // return;
	        }
	        if (null == rows || 0 == rows.size()) {
	            // 如果没有实时记录将不计算
	            return;
	        }
	        // 分析实时数据的第一条和最后一条
	//        analyseBeginEnd(rows);
	        // try {
	        if (rows != null & rows.size() > 0) {
	        	//计算里程和油耗
	        	if (rows.size() == 1) {// 一条记录
	                if (rows.get(0).getMILEAGE()!=null&&!rows.get(0).getMILEAGE().equals("FFFF")) {
	                    COUNT_MILEAGE = Float.parseFloat(rows.get(0).getMILEAGE());
	                } else {
	                    COUNT_MILEAGE = 0;
	                }
	                if (rows.get(0).getOIL_TOTAL()!=null&&!rows.get(0).getOIL_TOTAL().equals("FFFF")) {
	                	COUNT_OIL_TOTAL = Float.parseFloat(rows.get(0).getOIL_TOTAL());
	                } else {
	                	COUNT_OIL_TOTAL = 0;            	
	                }
	                if (rows.get(0).getOIL_INSTANT().equals("FFFF")) {
	                    OIL_INSTANT = "0";
	                } else {
	                    OIL_INSTANT = rows.get(0).getOIL_INSTANT();
	                }
	                // 更新车辆状态表
	//                reportDAO.updateVeztdOilMieage(def.format(COUNT_MILEAGE), def.format(MILEAGE), def
	//                        .format(COUNT_OIL_TOTAL), def.format(OIL), rows.get(0).getTERMINAL_TIME(),
	//                        OIL_INSTANT, strVin, strDate);
	            }
	        	 if (rows.get(0).getMILEAGE()!=null&&!rows.get(0).getMILEAGE().equals("FFFF")) {
	                 COUNT_MILEAGE_START = Float.parseFloat(rows.get(0).getMILEAGE());
	             } 
	             if (rows.get(0).getOIL_TOTAL()!=null&&!rows.get(0).getOIL_TOTAL().equals("FFFF")) {
	                COUNT_OIL_TOTAL_START = Float.parseFloat(rows.get(0).getOIL_TOTAL());
	             } 
	             
	            perv = rows.get(0);
	//            // 累计开关量2011-08-06
	//            if (rows.get(0) != null && rows.get(0).getON_OFF().length() == 32) {
	//                reportResult = analyseOnOff(reportResult, rows.get(0),reltime);// 累计开关量
	//            }
	//            // 超速
	//            if (rows.get(0).getOVERSPEED_ALERT() != null
	//                    && rows.get(0).getOVERSPEED_ALERT().equals(2)) {
	//                cSPerv = rows.get(0);
	//            }            
	//            // 空调怠速
	//            if (rows.get(0).getON_OFF() != null && rows.get(0).getSPEEDING() != null
	//                    && rows.get(0).getENGINE_ROTATE_SPEED() != null
	//                    && rows.get(0).getON_OFF().length() == 32
	//                    && !rows.get(0).getSPEEDING().equals("FFFF")
	//                    && !rows.get(0).getENGINE_ROTATE_SPEED().equals("FFFF")) {
	//                if (Float.valueOf(rows.get(0).getSPEEDING()).floatValue() < IDLAIR_SPD
	//                        && Float.valueOf(rows.get(0).getENGINE_ROTATE_SPEED()).floatValue() < IDLAIR_RPM
	//                        && rows.get(0).getON_OFF().substring(22, 23).equals("1")) {
	//                    ktdsBegin = rows.get(0);
	//                }
	//            }
	            // 空挡滑行
	//            if (rows.get(0).getON_OFF() != null && rows.get(0).getSPEEDING() != null
	//                    && rows.get(0).getON_OFF().length() == 32
	//                    && !rows.get(0).getSPEEDING().equals("FFFF")) {
	//                if (rows.get(0).getON_OFF().substring(21, 22).equals("1")
	//                        && Float.valueOf(rows.get(0).getSPEEDING()).floatValue() > 0) {
	//                    kdBegin = rows.get(0);
	//                }
	//            }
	
	            for (int i = 1; i < rows.size(); i++) {
	            	//总里程，总油耗
	            	if (rows.get(0).getMILEAGE()!=null&&!rows.get(i).getMILEAGE().equals("FFFF")) {
	            		if(COUNT_MILEAGE_START==0){
	            			COUNT_MILEAGE_START = Float.parseFloat(rows.get(i).getMILEAGE());
	            		}else{
	            			COUNT_MILEAGE = Float.parseFloat(rows.get(i).getMILEAGE());
	            		}                    
	                } 
	                if (rows.get(0).getOIL_TOTAL()!=null&&!rows.get(i).getOIL_TOTAL().equals("FFFF")) {
	                	if(COUNT_OIL_TOTAL_START==0){
	                		COUNT_OIL_TOTAL_START = Float.parseFloat(rows.get(i).getOIL_TOTAL());
	            		}else{
	            			COUNT_OIL_TOTAL = Float.parseFloat(rows.get(i).getOIL_TOTAL());
	            		}                 
	                } 
	                if (rows.get(i).getOIL_INSTANT() != null && !rows.get(i).getOIL_INSTANT().equals("FFFF")) {
	                    OIL_INSTANT = rows.get(i).getOIL_INSTANT();
	                }
	                nonce = rows.get(i);
	                
	//                // 累计开关量2011-08-06
	//                if (rows.get(i) != null && rows.get(i).getON_OFF().length() == 32) {
	//                    reportResult = analyseOnOff(reportResult, rows.get(i),reltime);// 累计开关量
	//                }
	                // 超速
	//                if (cSPerv == null && rows.get(i).getOVERSPEED_ALERT() != null
	//                        && rows.get(i).getOVERSPEED_ALERT().equals(2)) {
	//                    cSPerv = rows.get(i);
	//                }
	//                if (rows.get(i).getOVERSPEED_ALERT() != null
	//                        && rows.get(i).getOVERSPEED_ALERT().equals(0) && cSPerv != null) {
	//                    CsNonce = rows.get(i);
	//                }
	//                if (cSPerv != null && CsNonce != null) {
	//                    SPEEDING_OIL = SPEEDING_OIL
	//                            + (Float.valueOf(CsNonce.getOIL_TOTAL()).floatValue() - Float.valueOf(
	//                                    cSPerv.getOIL_TOTAL()).floatValue());
	//                    SPEEDING_MILEAGE = SPEEDING_MILEAGE
	//                            + (Float.valueOf(CsNonce.getMILEAGE()).floatValue() - Float.valueOf(
	//                                    cSPerv.getMILEAGE()).floatValue());
	//                    cSPerv = null;
	//                    CsNonce = null;
	//                }              
	                // 空调怠速
	//                if (ktdsBegin == null && nonce.getON_OFF() != null && nonce.getSPEEDING() != null
	//                        && nonce.getENGINE_ROTATE_SPEED() != null
	//                        && nonce.getON_OFF().substring(22, 23).equals("1")
	//                        && !nonce.getSPEEDING().equals("FFFF")
	//                        && !nonce.getENGINE_ROTATE_SPEED().equals("FFFF")
	//                        && Float.valueOf(nonce.getSPEEDING()).floatValue() < IDLAIR_SPD
	//                        && Float.valueOf(nonce.getENGINE_ROTATE_SPEED()).floatValue() < IDLAIR_RPM
	//                        &&Float.valueOf(nonce.getENGINE_ROTATE_SPEED()).floatValue() >0) {
	//                    ktdsBegin = nonce;
	//                }
	                // 空挡滑行
	//                if (kdBegin == null && nonce.getON_OFF() != null && nonce.getSPEEDING() != null
	//                        && nonce.getON_OFF().length() == 32 && !nonce.getSPEEDING().equals("FFFF")) {
	//                    if (nonce.getON_OFF().substring(21, 22).equals("1")
	//                            && Float.valueOf(nonce.getSPEEDING()).floatValue() > 0) {
	//                        kdBegin = nonce;
	//                    }
	//                }
	                diff = (df.parse(nonce.getTERMINAL_TIME()).getTime() - df.parse(
	                        perv.getTERMINAL_TIME()).getTime()) / 1000;
	                if (diff >= 0 && diff < reportTimeSpace) {
	                    continuous = true;// 两条记录连续
	                } else {
	                    continuous = false;// 两条记录不连续
	                }
	                if (diff == 0) {
	                    diff = reltime;// 如果为零付给时间。
	                }
	                // 计算各种持续时间
	                if (continuous) {// 记录连续的时候累计时间
	                    po = 0;
	                    // 08-06开关量相关持续时间开始
	//                    float Brake_shoe_time = 0;// 制动蹄片磨损时长
	//                    if (perv.getON_OFF().substring(10, 11).equals("1")) {
	//                        Brake_shoe_time = diff;
	//                    }
	//                    if (i == rows.size() && nonce.getON_OFF().substring(10, 11).equals("1")) {
	//                        Brake_shoe_time = diff;
	//                    }
	//                    reportResult.setBRAKE_SHOE_TIME(reportResult.getBRAKE_SHOE_TIME()
	//                            + Brake_shoe_time);
	
	//                    float Air_filter_clog_num = 0;// 空滤堵塞时间
	//                    if (perv.getON_OFF().substring(9, 10).equals("1")) {
	//                        Air_filter_clog_num = diff;
	//                    }
	//                    if (i == rows.size() && nonce.getON_OFF().substring(9, 10).equals("1")) {
	//                        Air_filter_clog_num = diff;
	//                    }
	//                    reportResult.setAIR_FILTER_CLOG_TIME(reportResult.getAIR_FILTER_CLOG_TIME()
	//                            + Air_filter_clog_num);
	//
	//                    float JIARE_TIME = 0; // 加热器时间(当日合计时间)
	//                    if (perv.getON_OFF().substring(16, 17).equals("1")) {
	//                        JIARE_TIME = diff;
	//                    }
	//                    if (i == rows.size() && nonce.getON_OFF().substring(16, 17).equals("1")) {
	//                        JIARE_TIME = diff;
	//                    }
	//                    reportResult.setJIARE_TIME(reportResult.getJIARE_TIME() + JIARE_TIME);
	//                    float KONGTIAO_TIME = 0; // 空调时间(当日合计时间)
	//                    if (perv.getON_OFF().substring(22, 23).equals("1")) {
	//                        KONGTIAO_TIME = diff;
	//                    }
	//                    if (i == rows.size() && nonce.getON_OFF().substring(22, 23).equals("1")) {
	//                        KONGTIAO_TIME = diff;
	//                    }
	//                    reportResult.setKONGTIAO_TIME(reportResult.getKONGTIAO_TIME() + KONGTIAO_TIME);
	                    // 08-06开关量相关持续时间结束
	
	                    // 空调怠速
	//                    if (nonce.getON_OFF().substring(22, 23).equals("1")
	//                            && nonce.getSPEEDING() != null
	//                            && !nonce.getSPEEDING().equals("FFFF")
	//                            && nonce.getENGINE_ROTATE_SPEED() != null
	//                            && !nonce.getENGINE_ROTATE_SPEED().equals("FFFF")
	//                            && Float.valueOf(nonce.getSPEEDING()).floatValue() < IDLAIR_SPD
	//                            && Float.valueOf(nonce.getENGINE_ROTATE_SPEED()).floatValue() < IDLAIR_RPM
	//                            && Float.valueOf(nonce.getENGINE_ROTATE_SPEED()).floatValue() > 0) {
	//                        if (ktdsBegin == null) {
	//                            ktdsBegin = nonce;
	//                        } else {
	//                            ktdsEnd = nonce;
	//                        }
	//                    } else {
	                          // 空调怠速连续记录中结束
	//                        if (ktdsBegin != null && ktdsEnd != null) {
	//                            long cxTime = (df.parse(ktdsEnd.getTERMINAL_TIME()).getTime() - df
	//                                    .parse(ktdsBegin.getTERMINAL_TIME()).getTime()) / 1000;
	//                            if (cxTime > IDLAIR_TIME) {// 持续时间>规定值min 记录详细表
	//                                reportDAO.insertCcds(ktdsEnd, ktdsBegin, cxTime, "51");
	//                            }
	//                        } else if (ktdsBegin != null && ktdsEnd == null) {
	//                            reportDAO.insertCcds(ktdsBegin, ktdsBegin, diff, "51");
	//                        }
	//                        ktdsBegin = null;
	//                        ktdsEnd = null;
	//                    }
	                    // 空挡滑行
	//                    if (nonce.getON_OFF() != null && nonce.getSPEEDING() != null
	//                            && nonce.getON_OFF().length() == 32
	//                            && !nonce.getSPEEDING().equals("FFFF")) {
	//                        if (nonce.getON_OFF().substring(21, 22).equals("1")
	//                                && Float.valueOf(nonce.getSPEEDING()).floatValue() > 0) {
	//                            if (kdBegin == null) {
	//                                kdBegin = nonce;
	//                            } else {
	//                                kdEnd = nonce;
	//                            }
	//
	//                        } else {// 空挡滑行连续记录结束
	//                            if (kdBegin != null && kdEnd != null) {
	//                                long cxTime = (df.parse(kdEnd.getTERMINAL_TIME()).getTime() - df
	//                                        .parse(kdBegin.getTERMINAL_TIME()).getTime()) / 1000;
	//                                if (cxTime==0)
	//                                	cxTime=reltime;
	//                                reportDAO.insertCcds(kdEnd, kdBegin, cxTime, "46");
	//                            }
	//                            if (kdBegin != null && kdEnd == null) {
	//                                reportDAO.insertCcds(kdBegin, kdBegin, reltime, "46");
	//                            }
	//                            kdBegin = null;
	//                            kdEnd = null;
	//                        }
	//                    }
	                } else {// 不连续
	                    po = po + 1;
	                    // 处理空调怠速
	//                    if (ktdsBegin != null && ktdsEnd != null) {
	//                        long cxTime = (df.parse(ktdsEnd.getTERMINAL_TIME()).getTime() - df.parse(
	//                                ktdsBegin.getTERMINAL_TIME()).getTime()) / 1000;
	//                        if (cxTime > IDLAIR_TIME) {// 持续时间>规定值min 记录详细表
	//                            reportDAO.insertCcds(ktdsEnd, ktdsBegin, cxTime, "51");
	//                        }
	//                    }
	//                    ktdsBegin = null;
	//                    ktdsEnd = null;
	                    // 处理空挡滑行
	//                    if (kdBegin != null && kdEnd != null) {
	//                        long cxTime = (df.parse(kdEnd.getTERMINAL_TIME()).getTime() - df.parse(
	//                                kdBegin.getTERMINAL_TIME()).getTime()) / 1000;
	//                        if(cxTime==0)
	//                        {
	//                        	cxTime=reltime;
	//                        }
	//                        reportDAO.insertCcds(kdEnd, kdBegin, cxTime, "46");
	//                    } else if (kdBegin != null && kdEnd == null) {
	//                        reportDAO.insertCcds(kdBegin, kdBegin, reltime, "46");
	//                    }
	//                    kdBegin = null;
	//                    kdEnd = null;
	                    if (po >= 2) {// 前一点为孤立点
	//                        if (perv.getON_OFF() != null) {
	//                            // 空挡滑行
	//                            if (perv.getSPEEDING() != null && perv.getON_OFF().length() == 32
	//                                    && perv.getON_OFF().substring(21, 22).equals("1")
	//                                    && Float.valueOf(perv.getSPEEDING()).floatValue() > 0) {
	//                                reportDAO.insertCcds(perv, perv, reltime, "46");
	//                            }
	//                            // 08-06开关量相关统计开始
	//                            // 制动蹄片磨损时长
	//                            if (perv.getON_OFF().substring(10, 11).equals("1"))
	//                                reportResult.setBRAKE_SHOE_TIME(reportResult.getBRAKE_SHOE_TIME()
	//                                        + reltime);
	//                            // 空滤堵塞时间
	//                            if (perv.getON_OFF().substring(9, 10).equals("1"))
	//                                reportResult.setAIR_FILTER_CLOG_TIME(reportResult
	//                                        .getAIR_FILTER_CLOG_TIME()
	//                                        + reltime);
	//                            // 加热器时间(当日合计时间)
	//                            if (perv.getON_OFF().substring(16, 17).equals("1"))
	//                                reportResult.setJIARE_TIME(reportResult.getJIARE_TIME() + reltime);
	//                            // 空调时间(当日合计时间)
	//                            if (perv.getON_OFF().substring(22, 23).equals("1"))
	//                                reportResult.setKONGTIAO_TIME(reportResult.getKONGTIAO_TIME()
	//                                        + reltime);
	//                            // 08-06开关量相关统计结束
	//                        }
	                    }
	                }
	                perv = nonce;
	            }
	//            2011-08-18 对最后连续记录为入库数据入库
	//            // 处理空挡滑行
	//            if (kdBegin != null && kdEnd != null) {
	//                long cxTime = (df.parse(kdEnd.getTERMINAL_TIME()).getTime() - df.parse(
	//                        kdBegin.getTERMINAL_TIME()).getTime()) / 1000;
	//                if(cxTime==0) cxTime=reltime;
	//                reportDAO.insertCcds(kdEnd, kdBegin, cxTime, "46");
	//            } else if (kdBegin != null && kdEnd == null) {
	//                reportDAO.insertCcds(kdBegin, kdBegin, reltime, "46");
	//            }
	            kdBegin = null;
	            kdEnd = null;
	            // 处理空调怠速
	//            if (ktdsBegin != null && ktdsEnd != null) {
	//                long cxTime = (df.parse(ktdsEnd.getTERMINAL_TIME()).getTime() - df.parse(
	//                        ktdsBegin.getTERMINAL_TIME()).getTime()) / 1000;
	//                if (cxTime > IDLAIR_TIME) {// 持续时间>规定值min 记录详细表
	//                    reportDAO.insertCcds(ktdsEnd, ktdsBegin, cxTime, "51");
	//                }
	//            }
	            ktdsBegin = null;
	            ktdsEnd = null;            
	            //总里程，总油耗 计算及入库
	            MILEAGE = COUNT_MILEAGE  - COUNT_MILEAGE_START;    
	            OIL = COUNT_OIL_TOTAL    - COUNT_OIL_TOTAL_START;
	            //增加对负数的修正2011-08-18
	            if(MILEAGE<0){ 	MILEAGE=0; }
	            if(OIL<0){ OIL=0; }            
	//            // 更新车辆状态表            
	//            if(rows.size()>0){
	//            reportDAO.updateVeztdOilMieage(def.format(COUNT_MILEAGE), def.format(MILEAGE), def
	//                    .format(COUNT_OIL_TOTAL), def.format(OIL), rows.get(rows.size()-1).getTERMINAL_TIME(),
	//                    OIL_INSTANT, strVin, strDate);
	//            }          
	            reportDAO.updateOilMieage(def.format(COUNT_MILEAGE), def.format(MILEAGE), def.format(COUNT_OIL_TOTAL), def.format(OIL), strVin, strDate);// 更新油耗
	            
	//            reportDAO.updateOnOffData(reportResult, strVin, strDate);// 更新开关量相关
	//            reportDAO.updateCsOilMileage(strVin, strDate, SPEEDING_OIL, SPEEDING_MILEAGE);// 更新超速
	            // reportDAO.updateCsdsData(strVin, strDate, "33");// 更新疲劳驾驶
	//            reportDAO.updateCsdsData(strVin, strDate, "51");// 怠速空调计算
	//            reportDAO.updateCsdsData(strVin, strDate, "46"); // 空档滑行
	        }
         } catch (Exception e) {
	         log.info("实时数据异常"+e.toString());
	         e.printStackTrace();
	         throw new Exception("分析实时数据时发生异常："+e.getMessage());
         }
    }

    // 分析急加速急减速数据
    @SuppressWarnings("unused")
	private void analyseJjs() throws Exception {

        float diff = 0, interval = (float) 1.8;
        String type = "00";// 急加速 47 ;急减速 48
        // System.out.println("interval:"+interval);
        List<RapidBean> rows = reportDAO.getClwJjsRecord(strVin, strDate);
        boolean continuous = false;// 连续两条记录是否连续 连续ture 反之亦然。
        interval = Float.valueOf(Config.props.getProperty("reportJjsSpace"));// 数据时间间隔
        RapidBean rapidPerv = null, rapidNonce = null;
        if (rows != null & rows.size() > 0) {
            for (int i = 1; i < rows.size(); i++) {
                if (rapidPerv == null) {
                    rapidPerv = rows.get(i);
                    continue;
                }
                diff = (df.parse(rows.get(i).getTEMINAL_TIME()).getTime() - df.parse(
                        rows.get(i - 1).getTEMINAL_TIME()).getTime()) / 1000;
                if (diff >= 0 && diff < interval) {
                    continuous = true;// 两条记录连续
                } else {
                    continuous = false;// 两条记录不连续
                }
                // 计算各种持续时间
                if (continuous) {// 记录连续的时候累计时间
                    rapidNonce = rows.get(i);
                    continue;
                } else {// 断开为一次急加速或急减速记录数据库
                    if (rapidPerv != null && rapidNonce != null && rapidNonce.getSPEEDING() != null
                            && rapidNonce.getSPEEDING() != "FFFF"
                            && rapidPerv.getSPEEDING() != null && rapidPerv.getSPEEDING() != "FFFF") {
                        if (Float.valueOf(rapidNonce.getSPEEDING()).floatValue() > Float.valueOf(
                                rapidPerv.getSPEEDING()).floatValue()) {
                            type = "47";
                        } else {
                            type = "48";
                        }
                        long cxTime = (df.parse(rapidNonce.getTEMINAL_TIME()).getTime() - df.parse(
                                rapidPerv.getTEMINAL_TIME()).getTime()) / 1000;
                        if (cxTime == 0) {
                            cxTime = 1;
                        }
                        reportDAO.makerapiddetail(strDate, type, rapidPerv, rapidNonce, cxTime);
                    }
                    rapidPerv = rows.get(i);// 新的开始；
                    rapidNonce = null;
                }
            }
            // 更新急加速、急减速驾驶
            reportDAO.updateCsdsData(strVin, strDate, "47");
            reportDAO.updateCsdsData(strVin, strDate, "48");
        }
    }

    // 分析秒数据
    public void analyseSEC(String sumEngine) throws Exception {
        List<ClwSecData> rows = reportDAO.getClwSecRecord(strVin, strDate);
        ClwSecData clwSecDataPrevious = new ClwSecData();
        ClwSecData clwSecData = new ClwSecData();
        DayReport reportResult = new DayReport();// 存当日累计结果
        long secinterval = Long.valueOf(Config.props.getProperty("secinterval"));
//        long reportSecSpace = Long.valueOf(Config.props.getProperty("reportSecSpace"));// 秒数据时间间隔
        float diff = 0;
        boolean continuous = false;// 连续两条记录是否连续 连续ture 反之亦然。
        ClwSecData ccdsBegin = null;// 超长怠速开始
        ClwSecData ccdsEnd = null;// 超长怠速结束
        ClwSecData czBegin = null;// 超转开始
        ClwSecData czEnd = null;// 超转结束
        VehicleSpeed v_s = new VehicleSpeed();// 车速报表;
        RotateSpeed r_s = new RotateSpeed();// 转速报表
//        ClwSecData dwbdBegin = null;// 档位不当行开始
//        ClwSecData dwbdEnd = null;// 档位不当结束
        HarmDefBean hdf = HarmDefCacheManager.harmdefMap.get(strVin);
        float GREENAREA_RPM_U = 1600;// 超经济区rpm上线
        float GREENAREA_RPM_L = 1100;// 超经济区rpm下线
        float ERS=200;               //发动机转速下限
        float STANDARD_ROTATE = 1600;// 车辆标准转速
        float LIDL_SPD = (float) 2;  // 车速阀值(km/h)-长时间怠速
        float LIDL_RPM = 800;        // 发动机转速(r/min)-长时间怠速
        float LIDL_TIME = 180;       // 持续时间(min)-长时间怠速
        float SPEED_MAX = 0;// 最高车速
        float RPM_MAX = 0;// 最高转速
        float V_SPEED = 0;// 车速
        float R_SPEED = 0;// 最高转速
        float SEC_TIME = 5; // 秒数据，每条数据占用的时间。
        
        @SuppressWarnings("unused")
		String gears;// 档位
        if (hdf != null) {
            // log.info("AnalysisReportThread没有vin:"+strVin+"车辆的不良驾驶定义信息，无法生成此车辆相关报表数据！");
            // return;
            if (hdf.getGREENAREA_RPM_U() != null) {
                GREENAREA_RPM_U = Float.valueOf(hdf.getGREENAREA_RPM_U()).floatValue();
            }
            if (hdf.getGREENAREA_RPM_L() != null) {
                GREENAREA_RPM_L = Float.valueOf(hdf.getGREENAREA_RPM_L()).floatValue();
            }
            if (hdf.getSTANDARD_ROTATE() != null) {
                STANDARD_ROTATE = Float.valueOf(hdf.getSTANDARD_ROTATE()).floatValue();
            }

            // 长时间怠速
            if (hdf.getLidl_spd() != null && hdf.getLidl_rpm() != null
                    && hdf.getLidl_time() != null) {
                LIDL_SPD = Float.valueOf(hdf.getLidl_spd()).floatValue();
                LIDL_RPM = Float.valueOf(hdf.getLidl_rpm()).floatValue();
                LIDL_TIME = Float.valueOf(hdf.getLidl_time()).floatValue();
            }
        }
        int po = 0; // 连续的时候赋值为0，断开开的时候加1，如果大于等于2则说明前一点为为单点
        if (rows != null & rows.size() > 0) {
            clwSecDataPrevious = rows.get(0);

            // 最高车速
            if (rows.get(0).getSPEEDING() != "" && !rows.get(0).getSPEEDING().equals("FFFF")) {
                if (Float.valueOf(rows.get(0).getSPEEDING()).floatValue() > SPEED_MAX) {
                    SPEED_MAX = Float.valueOf(rows.get(0).getSPEEDING()).floatValue();
                }
                // 车速 报表 2011-08-02
                if (rows.get(0).getENGINE_ROTATE_SPEED() != ""
                        && !rows.get(0).getENGINE_ROTATE_SPEED().equals("FFFF")) {
                    if (Float.valueOf(rows.get(0).getENGINE_ROTATE_SPEED()).floatValue() > 0) {
                        V_SPEED = Float.valueOf(rows.get(0).getSPEEDING()).floatValue();
                        v_s = analyseVS(v_s, V_SPEED);
                    }
                }

            }
            // 最高转速
            if (rows.get(0).getENGINE_ROTATE_SPEED() != ""
                    && !rows.get(0).getENGINE_ROTATE_SPEED().equals("FFFF")) {
                if (Float.valueOf(rows.get(0).getENGINE_ROTATE_SPEED()).floatValue() > RPM_MAX) {
                    RPM_MAX = Float.valueOf(rows.get(0).getENGINE_ROTATE_SPEED()).floatValue();
                }
                // 转速 报表 2011-08-02
                R_SPEED = Float.valueOf(rows.get(0).getENGINE_ROTATE_SPEED()).floatValue();
                r_s = analyseRS(r_s, R_SPEED, rows.get(0).getTORQUE());
            }
//            if (clwSecDataPrevious.getON_OFF_VALUE() != null
//                    && clwSecDataPrevious.getON_OFF_VALUE().length() == 32) {
//                reportResult = analyseOnOff(reportResult, clwSecDataPrevious.getON_OFF_VALUE());// 累计开关量
//            }
            // 是否为超长怠速开始 车速<0.5 km/h，发动机转速<800rpm，持续时间>10min
            if (!clwSecDataPrevious.getSPEEDING().equals("FFFF")
                    && !clwSecDataPrevious.getENGINE_ROTATE_SPEED().equals("FFFF")) {
                if (Float.valueOf(clwSecDataPrevious.getSPEEDING()).floatValue() < LIDL_SPD
                        && Float.valueOf(clwSecDataPrevious.getENGINE_ROTATE_SPEED()).floatValue() < LIDL_RPM) {
                    ccdsBegin = clwSecDataPrevious;
                }
            }
            // 是否为超转
            if (!clwSecDataPrevious.getENGINE_ROTATE_SPEED().equals("FFFF")
                    && Float.valueOf(clwSecDataPrevious.getENGINE_ROTATE_SPEED()).floatValue() > STANDARD_ROTATE) {
                czBegin = clwSecDataPrevious;
            }
            float ENGINE_ROTATE_TIME = 0; // 发动机运行时间 2011-08-16
            if (!clwSecDataPrevious.getENGINE_ROTATE_SPEED().equals("FFFF")) {
                if (Float.valueOf(clwSecDataPrevious.getENGINE_ROTATE_SPEED()).floatValue() > ERS) {
                    ENGINE_ROTATE_TIME = SEC_TIME;
                    reportResult.setENGINE_ROTATE_TIME(reportResult.getENGINE_ROTATE_TIME()
                            + ENGINE_ROTATE_TIME);
                }
            }
            float ECONOMIC_RUN_TIME = 0;// 超经济区运行时间
            // 超经济区运行时间计算
            if (!clwSecDataPrevious.getENGINE_ROTATE_SPEED().equals("FFFF")) {
                float previous = 0;
//                , nonce = 0;
                previous = Float.valueOf(clwSecDataPrevious.getENGINE_ROTATE_SPEED()).floatValue();
                if (previous > 0 && (previous > GREENAREA_RPM_U || (previous>ERS && previous < GREENAREA_RPM_L))) {
                    ECONOMIC_RUN_TIME = SEC_TIME;
                    reportResult.setECONOMIC_RUN_TIME(reportResult.getECONOMIC_RUN_TIME()
                            + ECONOMIC_RUN_TIME);
                }
            }
            float XINGCHE_TIME = 0; // 行车时间（当日的行车时间）
            if (!clwSecDataPrevious.getSPEEDING().equals("FFFF")) {
                if (Float.valueOf(clwSecDataPrevious.getSPEEDING()).floatValue() > 0) {
                    XINGCHE_TIME = SEC_TIME;
                    reportResult.setXINGCHE_TIME(reportResult.getXINGCHE_TIME() + XINGCHE_TIME);
                }
            }
            for (int i = 1; i < rows.size(); i++) {
                clwSecData = rows.get(i);
                ENGINE_ROTATE_TIME = 0; // 发动机运行时间 2011-08-16
                if (!clwSecData.getENGINE_ROTATE_SPEED().equals("FFFF")) {
                    if (Float.valueOf(clwSecData.getENGINE_ROTATE_SPEED()).floatValue() > ERS) {
                        ENGINE_ROTATE_TIME = SEC_TIME;
                        reportResult.setENGINE_ROTATE_TIME(reportResult.getENGINE_ROTATE_TIME()
                                + ENGINE_ROTATE_TIME);
                    }

                }
                ECONOMIC_RUN_TIME = 0;// 超经济区运行时间
                // 超经济区运行时间计算
                if (!clwSecData.getENGINE_ROTATE_SPEED().equals("FFFF")) {

                    float previous = 0;
//                    , nonce = 0;
                    previous = Float.valueOf(clwSecData.getENGINE_ROTATE_SPEED()).floatValue();
                    if (previous > 0 && (previous > GREENAREA_RPM_U || (previous>ERS&&previous < GREENAREA_RPM_L))) {
                        ECONOMIC_RUN_TIME = SEC_TIME;
                        reportResult.setECONOMIC_RUN_TIME(reportResult.getECONOMIC_RUN_TIME()
                                + ECONOMIC_RUN_TIME);
                    }
                }
                XINGCHE_TIME = 0; // 行车时间（当日的行车时间）
                if (!clwSecData.getSPEEDING().equals("FFFF")) {
                    if (Float.valueOf(clwSecData.getSPEEDING()).floatValue() > 0) {
                        XINGCHE_TIME = SEC_TIME;
                        reportResult.setXINGCHE_TIME(reportResult.getXINGCHE_TIME() + XINGCHE_TIME);
                    }
                }
                // 最高车速
                if (rows.get(i).getSPEEDING() != "" && !rows.get(i).getSPEEDING().equals("FFFF")) {
                    if (Float.valueOf(rows.get(i).getSPEEDING()).floatValue() > SPEED_MAX) {
                        SPEED_MAX = Float.valueOf(rows.get(i).getSPEEDING()).floatValue();
                    }
                    // 车速 报表 2011-08-02
                    if (rows.get(i).getENGINE_ROTATE_SPEED() != ""
                            && !rows.get(i).getENGINE_ROTATE_SPEED().equals("FFFF")) {
                        if (Float.valueOf(rows.get(i).getENGINE_ROTATE_SPEED()).floatValue() > 0) {
                            V_SPEED = Float.valueOf(rows.get(i).getSPEEDING()).floatValue();
                            v_s = analyseVS(v_s, V_SPEED);
                        }
                    }
                }
                // 最高转速
                if (rows.get(i).getENGINE_ROTATE_SPEED() != ""
                        && !rows.get(i).getENGINE_ROTATE_SPEED().equals("FFFF")) {
                    if (Float.valueOf(rows.get(i).getENGINE_ROTATE_SPEED()).floatValue() > RPM_MAX) {
                        RPM_MAX = Float.valueOf(rows.get(i).getENGINE_ROTATE_SPEED()).floatValue();
                    }
                    // 转速 报表 2011-08-02
                    R_SPEED = Float.valueOf(rows.get(i).getENGINE_ROTATE_SPEED()).floatValue();
                    r_s = analyseRS(r_s, R_SPEED, rows.get(i).getTORQUE());
                }
                diff = (df.parse(clwSecData.getTEMINAL_TIME()).getTime() - df.parse(
                        clwSecDataPrevious.getTEMINAL_TIME()).getTime()) / 1000;
                if (diff >= 0 && diff < secinterval) {
                    continuous = true;// 两条记录连续
                } else {
                    continuous = false;// 两条记录不连续
                }
                // 计算各种持续时间
                if (continuous) {// 记录连续的时候累计时间
                    po = 0;
                    // 超长怠速计算 车速<0.5 km/h，发动机转速<800rpm，持续时间>10min
                    // 2011-08-15 转速为‘FFFF’的断点判断
                    if (clwSecData.getENGINE_ROTATE_SPEED().equals("FFFF")) {
                        if (ccdsBegin != null && ccdsEnd != null) {
                            long cxTime = (df.parse(ccdsEnd.getTEMINAL_TIME()).getTime() - df
                                    .parse(ccdsBegin.getTEMINAL_TIME()).getTime()) / 1000;
                            if (cxTime > LIDL_TIME) {// 持续时间>10min 记录详细表
                                reportDAO.insertCcdsMx(ccdsEnd, ccdsBegin, cxTime, "50");
                            }
                        }
                        ccdsBegin = null;
                        ccdsEnd = null;
                    }

                    if (!clwSecData.getSPEEDING().equals("FFFF")
                            && !clwSecData.getENGINE_ROTATE_SPEED().equals("FFFF")) {
                        if (Float.valueOf(clwSecData.getSPEEDING()).floatValue() < LIDL_SPD
                                && Float.valueOf(clwSecData.getENGINE_ROTATE_SPEED()).floatValue() < LIDL_RPM
                                && Float.valueOf(clwSecData.getENGINE_ROTATE_SPEED()).floatValue() > 0) {
                            if (ccdsBegin == null) {
                                ccdsBegin = clwSecData;
                            } else {
                                ccdsEnd = clwSecData;
                            }
                        } else {// 超长怠速连续记录中结束
                            if (ccdsBegin != null && ccdsEnd != null) {
                                long cxTime = (df.parse(ccdsEnd.getTEMINAL_TIME()).getTime() - df
                                        .parse(ccdsBegin.getTEMINAL_TIME()).getTime()) / 1000;
                                if (cxTime > LIDL_TIME) {// 持续时间>10min 记录详细表
                                    reportDAO.insertCcdsMx(ccdsEnd, ccdsBegin, cxTime, "50");
                                }
                            }
                            ccdsBegin = null;
                            ccdsEnd = null;
                        }
                    }
                    // float XINGCHE_TIME = 0; // 行车时间（当日的行车时间）
                    // if (!clwSecDataPrevious.getSPEEDING().equals("FFFF")
                    // && !clwSecData.getSPEEDING().equals("FFFF")) {
                    // if (Float.valueOf(clwSecDataPrevious.getSPEEDING()).floatValue() > 0
                    // && Float.valueOf(clwSecData.getSPEEDING()).floatValue() > 0) {
                    // XINGCHE_TIME = diff;
                    // }
                    // if (Float.valueOf(clwSecDataPrevious.getSPEEDING()).floatValue() > 0
                    // && Float.valueOf(clwSecData.getSPEEDING()).floatValue() == 0) {
                    // XINGCHE_TIME = diff;
                    // }
                    // if (i == rows.size()
                    // && Float.valueOf(clwSecDataPrevious.getSPEEDING()).floatValue() == 0
                    // && Float.valueOf(clwSecData.getSPEEDING()).floatValue() > 0) {
                    // XINGCHE_TIME = diff;
                    // }
                    // reportResult.setXINGCHE_TIME(reportResult.getXINGCHE_TIME() + XINGCHE_TIME);
                    // }
                    // float ENGINE_ROTATE_TIME = 0; // 发动机运行时间 2011-08-04
                    // if (!clwSecDataPrevious.getENGINE_ROTATE_SPEED().equals("FFFF")
                    // && !clwSecData.getENGINE_ROTATE_SPEED().equals("FFFF")) {
                    // if (Float.valueOf(clwSecDataPrevious.getENGINE_ROTATE_SPEED()).floatValue() >
                    // 200
                    // && Float.valueOf(clwSecData.getENGINE_ROTATE_SPEED()).floatValue() > 200) {
                    // ENGINE_ROTATE_TIME = diff;
                    // }
                    // if (Float.valueOf(clwSecDataPrevious.getENGINE_ROTATE_SPEED()).floatValue() >
                    // 200
                    // && Float.valueOf(clwSecData.getENGINE_ROTATE_SPEED()).floatValue() <= 200) {
                    // ENGINE_ROTATE_TIME = diff;
                    // }
                    // if (i == rows.size()
                    // && Float.valueOf(clwSecDataPrevious.getENGINE_ROTATE_SPEED())
                    // .floatValue() <= 200
                    // && Float.valueOf(clwSecData.getENGINE_ROTATE_SPEED()).floatValue() > 200) {
                    // ENGINE_ROTATE_TIME = diff;
                    // }
                    // reportResult.setENGINE_ROTATE_TIME(reportResult.getENGINE_ROTATE_TIME()
                    // + ENGINE_ROTATE_TIME);
                    // }
                    //
                    // // 超经济区运行时间计算
                    // if (!clwSecData.getENGINE_ROTATE_SPEED().equals("FFFF")
                    // && !clwSecDataPrevious.getENGINE_ROTATE_SPEED().equals("FFFF")) {
                    // float ECONOMIC_RUN_TIME = 0;// 超经济区运行时间
                    // float previous = 0, nonce = 0;
                    // nonce = Float.valueOf(clwSecData.getENGINE_ROTATE_SPEED()).floatValue();
                    // previous = Float.valueOf(clwSecDataPrevious.getENGINE_ROTATE_SPEED())
                    // .floatValue();
                    // if ((previous > GREENAREA_RPM_U || previous < GREENAREA_RPM_L)
                    // && (nonce > GREENAREA_RPM_U || nonce < GREENAREA_RPM_L)) {
                    // ECONOMIC_RUN_TIME = diff;
                    // }
                    // if ((previous > GREENAREA_RPM_U || previous < GREENAREA_RPM_L)
                    // && (nonce < GREENAREA_RPM_U || nonce > GREENAREA_RPM_L)) {
                    // ECONOMIC_RUN_TIME = diff;
                    // }
                    // if (i == rows.size()
                    // && (nonce < GREENAREA_RPM_U || nonce > GREENAREA_RPM_L)
                    // && (previous > GREENAREA_RPM_U || previous < GREENAREA_RPM_L)) {
                    // ECONOMIC_RUN_TIME = diff;
                    // }
                    // reportResult.setECONOMIC_RUN_TIME(reportResult.getECONOMIC_RUN_TIME()
                    // + ECONOMIC_RUN_TIME);
                    // }
                    // 超转
                    if (!clwSecData.getENGINE_ROTATE_SPEED().equals("FFFF")
                            && Float.valueOf(clwSecData.getENGINE_ROTATE_SPEED()).floatValue() > STANDARD_ROTATE) {
                        if (czBegin == null) {
                            czBegin = clwSecData;
                        } else {
                            czEnd = clwSecData;
                        }
                    } else {// 超转连续记录中结束
                        if (czBegin != null && czEnd != null) {
                            long cxTime = (df.parse(czEnd.getTEMINAL_TIME()).getTime() - df.parse(
                                    czBegin.getTEMINAL_TIME()).getTime()) / 1000;
                            // 入明细库
                            if(cxTime<=0){
                            	cxTime=(long)SEC_TIME;
                            }
                            reportDAO.insertCcdsMx(czEnd, czBegin, cxTime, "49");
                        }
                        if (czBegin != null && czEnd == null) {// 只有一条记录 
                            reportDAO.insertCcdsMx(czBegin, czBegin, (long) SEC_TIME, "49");
                        }
                        czBegin = null;
                        czEnd = null;
                    }

                } else { // 连续记录结束
                    po = po + 1;
                    if (po >= 2) {// 前一点为孤立点
                        // // 超经济区运行+1秒
                        // if (!clwSecDataPrevious.getENGINE_ROTATE_SPEED().equals("FFFF")) {
                        // float previous = Float.valueOf(
                        // clwSecDataPrevious.getENGINE_ROTATE_SPEED()).floatValue();
                        // if ((previous > GREENAREA_RPM_U || previous < GREENAREA_RPM_L)) {
                        // reportResult.setECONOMIC_RUN_TIME(reportResult
                        // .getECONOMIC_RUN_TIME()
                        // + reportSecSpace);// 加间隔
                        // }
                        // }
                        // 超转
                        if (!clwSecDataPrevious.getENGINE_ROTATE_SPEED().equals("FFFF")
                                && Float.valueOf(clwSecDataPrevious.getENGINE_ROTATE_SPEED())
                                        .floatValue() > STANDARD_ROTATE) { // 入明细库 单点算按间隔算
                            reportDAO.insertCcdsMx(clwSecDataPrevious, clwSecDataPrevious,
                                    (long) SEC_TIME, "49");
                            czBegin  = null;
                        }

                        // // 行车时间
                        // if (!clwSecDataPrevious.getSPEEDING().equals("FFFF")
                        // && !clwSecDataPrevious.getSPEEDING().equals("0")) {
                        // reportResult.setXINGCHE_TIME(reportResult.getXINGCHE_TIME()
                        // + reportSecSpace);// 加时间间隔
                        // }
                        // // 发动机运行时间
                        // if (!clwSecDataPrevious.getENGINE_ROTATE_SPEED().equals("FFFF")
                        // && Float.valueOf(clwSecDataPrevious.getENGINE_ROTATE_SPEED())
                        // .floatValue() > 200) {
                        // reportResult.setENGINE_ROTATE_TIME(reportResult.getENGINE_ROTATE_TIME()
                        // + reportSecSpace);// 加时间间隔
                        // }
                    }
                    // 超长怠速计算 车速<0.5 km/h，发动机转速<800rpm，持续时间>10min
                    if (ccdsBegin != null && ccdsEnd != null) {
                        long cxTime = (df.parse(ccdsEnd.getTEMINAL_TIME()).getTime() - df.parse(
                                ccdsBegin.getTEMINAL_TIME()).getTime()) / 1000;
                        if (cxTime > LIDL_TIME) {// 持续时间>10min 记录详细表
                            reportDAO.insertCcdsMx(ccdsEnd, ccdsBegin, cxTime, "50");
                        }
                    }
                    // 对超长怠速变量处理
                    ccdsBegin = null;
                    ccdsEnd = null;
                    if (!clwSecData.getSPEEDING().equals("FFFF")
                            && !clwSecData.getENGINE_ROTATE_SPEED().equals("FFFF")) {
                        if (Float.valueOf(clwSecData.getSPEEDING()).floatValue() < LIDL_SPD
                                && Float.valueOf(clwSecData.getENGINE_ROTATE_SPEED()).floatValue() < LIDL_RPM
                                && Float.valueOf(clwSecData.getENGINE_ROTATE_SPEED()).floatValue() > 0) {
                            ccdsBegin = clwSecData;
                        }
                    }
                    // 对超转变量处理
                    if (czBegin != null && czEnd != null) {
                        long cxTime = (df.parse(czEnd.getTEMINAL_TIME()).getTime() - df.parse(
                                czBegin.getTEMINAL_TIME()).getTime()) / 1000;
                        // 入明细库
                        if(cxTime<=0){
                        	cxTime=(long)SEC_TIME;
                        }
                        reportDAO.insertCcdsMx(czEnd, czBegin, cxTime, "49");
                    }
                    if (czBegin != null && czEnd == null) {// 只有一条记录插1秒
                        reportDAO.insertCcdsMx(czBegin, czBegin, (long) SEC_TIME, "49");
                    }
                    czBegin = null;
                    czEnd = null;
                    if (!clwSecData.getENGINE_ROTATE_SPEED().equals("FFFF")
                            && Float.valueOf(clwSecData.getENGINE_ROTATE_SPEED()).floatValue() > STANDARD_ROTATE) {
                        czBegin = clwSecData;
                    }
                    // // 档位不当变量处理
                    // dwbdBegin=null;//档位不当行开始
                    // dwbdEnd=null;//档位不当结束
                    // if(!clwSecData.getSPEEDING().equals("FFFF")&&!clwSecData.getENGINE_ROTATE_SPEED().equals("FFFF")){
                    // gears = AccountUtil.accountGears(clwSecData.getVEHICLE_VIN(),
                    // clwSecData.getSPEEDING(), clwSecData.getENGINE_ROTATE_SPEED());
                    // //行车档位不当
                    // if(AccountUtil.accountGear_unfitAlarm(clwSecData.getSPEEDING(), gears,
                    // hdf).equals("1")){
                    // dwbdBegin=clwSecData;
                    // };
                    // }

                }// 记录连续判断
                clwSecDataPrevious = clwSecData;
//                 System.out.println("idddddddd:" + clwSecDataPrevious.getID());
            }// 循环结束
            //连续记录到末尾后，最后负荷记录入库。
            // 超长怠速计算 车速<0.5 km/h，发动机转速<800rpm，持续时间>10min
            if (ccdsBegin != null && ccdsEnd != null) {
                long cxTime = (df.parse(ccdsEnd.getTEMINAL_TIME()).getTime() - df.parse(
                        ccdsBegin.getTEMINAL_TIME()).getTime()) / 1000;
                if (cxTime > LIDL_TIME) {// 持续时间>10min 记录详细表
                    reportDAO.insertCcdsMx(ccdsEnd, ccdsBegin, cxTime, "50");
                }
            }
            ccdsBegin=null;
            ccdsEnd=null;
         // 对超转变量处理
            if (czBegin != null && czEnd != null) {
                long cxTime = (df.parse(czEnd.getTEMINAL_TIME()).getTime() - df.parse(
                        czBegin.getTEMINAL_TIME()).getTime()) / 1000;
                // 入明细库
                if(cxTime<=0){
                	cxTime=(long)SEC_TIME;
                }
                reportDAO.insertCcdsMx(czEnd, czBegin, cxTime, "49");
            }
            if (czBegin != null && czEnd == null) {// 只有一条记录插1秒
                reportDAO.insertCcdsMx(czBegin, czBegin, (long) SEC_TIME, "49");
            }
            czBegin = null;
            czEnd = null;
            
            float DAISHU_TIME = 0; // 怠速时间(发动机工作时间-行车时间)
            // DAISHU_TIME=Float.valueOf(sumEngine).floatValue() - reportResult.getXINGCHE_TIME();
            DAISHU_TIME = reportResult.getENGINE_ROTATE_TIME() - reportResult.getXINGCHE_TIME();
            if (DAISHU_TIME < 0) {// 对负数怠速时间进行处理，如果为负设置为0
                DAISHU_TIME = 0;
            }
            // 车速分析百分比
            if (v_s != null) {
                analyseVSper(v_s, reportResult.getXINGCHE_TIME());
                reportDAO.deleteVS(strVin, strDate);
                reportDAO.insertVS(v_s, strVin, strDate);
            }

            // 转速百分比
            if (r_s != null) {
                analyseRSper(r_s, reportResult.getXINGCHE_TIME());
                reportDAO.deleteRS(strVin, strDate);
                reportDAO.insertRS(r_s, strVin, strDate);
            }

            reportResult.setDAISHU_TIME(reportResult.getDAISHU_TIME() + DAISHU_TIME);

            reportDAO.updateSecData(reportResult, strVin, strDate);
            // 超长怠速计算
            reportDAO.updateCsdsData(strVin, strDate, "50");
            // 超转
            reportDAO.updateCsdsData(strVin, strDate, "49");
            // 档位不当
            // reportDAO.updateCsdsData(strVin, strDate,"45");
            // 更新日报表最高速度，最高转速
            // reportDAO.updateMaxSpeed(strVin, strDate);
            reportDAO.updateMaxSpeed(strVin, strDate, SPEED_MAX, RPM_MAX);
        }
    }

    // 分析开关量数据
    public DayReport analyseOnOff(DayReport reportBase, RealTimeRecord realRecord,long jiange) {
      
        // 7:雾灯;6:远光灯信号;5:右转向灯信号;4:喇叭信号;3:左转向灯信号;2:倒档信号;1:近光灯信号;0:制动状态;15:严重故障;14:制动气压报警;
        // 13:油压报警;12:点火;11:水位低报警;10:制动蹄片磨损报警;9:空滤堵塞报警;8:SOS开关;23:燃油警告;22:空调状态;21:空挡信号;
        // 20:前门信号;19:中门信号;18:缓速器工作;17:ABS工作;16:加热器工作;31:离合器状态;30:缓速器高温报警信号;29:仓温报警信号;
        // 28:机滤堵塞信号;27:燃油堵塞信号;26:机油温度报警信号;25:开关量31;24:开关量32;OFF:0,ON:1;
    	String signal;
    	signal=realRecord.getON_OFF();
        reportBase.setSHACHE_NUM(reportBase.getSHACHE_NUM() + Long.valueOf(signal.substring(0, 1))); // 刹车次数(当日合计值）;
        reportBase.setQIANMEN_NUM(reportBase.getQIANMEN_NUM() + Long.valueOf(signal.substring(20, 21))); // 前门开启次数（当日合计值）;
        reportBase.setZHONGMEN_NUM(reportBase.getZHONGMEN_NUM() + Long.valueOf(signal.substring(19, 20))); // 中门开启次数（当日合计值）;
        reportBase.setCLUTCH_NUM(reportBase.getCLUTCH_NUM() + Long.valueOf(signal.substring(31, 32))); // 离合器次数(当日合计值）;
        reportBase.setREVERSE_NUM(reportBase.getREVERSE_NUM() + Long.valueOf(signal.substring(2, 3))); // 倒档次数(当日合计值）;
        reportBase.setBRAKE_NUM(reportBase.getBRAKE_NUM() + Long.valueOf(signal.substring(0, 1))); // 制动次数(当日合计值）;
        reportBase.setRETARDER_NUM(reportBase.getRETARDER_NUM()+ Long.valueOf(signal.substring(18, 19))); // 缓速器次数(当日合计值）;
        reportBase.setABS_NUM(reportBase.getABS_NUM() + Long.valueOf(signal.substring(17, 18))); // ABS次数(当日合计值）;
        reportBase.setBRAKE_SHOE_NUM(reportBase.getBRAKE_SHOE_NUM() + Long.valueOf(signal.substring(10, 11))); // 制动蹄片磨损次数;
        // reportBase.setBRAKE_SHOE_TIME(Long.valueOf(signal.substring(6,7)));
        // // 制动蹄片磨损时长;
        reportBase.setAIR_FILTER_CLOG_NUM(reportBase.getAIR_FILTER_CLOG_NUM()
                + Long.valueOf(signal.substring(9, 10))); // 空滤堵塞次数;
        
        //新增开关量2011-09-07 tmc
        if ( realRecord.getSPEEDING() != null &&  Float.valueOf(realRecord.getSPEEDING()).floatValue() >0) {
        	reportBase.setQIANMEN_ERR_NUM(reportBase.getQIANMEN_ERR_NUM()+ Long.valueOf(signal.substring(20, 21)));//前门异常
        	reportBase.setZHONGMEN_ERR_NUM(reportBase.getZHONGMEN_ERR_NUM()+ Long.valueOf(signal.substring(19, 20)));//中门异常	  
        }
        if(signal.substring(10, 11).equals("1")){ // BRAKE_SHOE_LATEST;	     制动蹄片磨损最近报警时间    
           reportBase.setBRAKE_SHOE_LATEST(realRecord.getTERMINAL_TIME());
        }
        
        reportBase.setOIL_PRESSURE_NUM(reportBase.getOIL_PRESSURE_NUM() + Long.valueOf(signal.substring(13, 14))); //OIL_PRESSURE_NUM 机油压力报警次数
        if(signal.substring(13, 14).equals("1")){ // OIL_PRESSURE_LATEST;//	     机油压力最近报警时间  
            reportBase.setOIL_PRESSURE_LATEST(realRecord.getTERMINAL_TIME());
         }
      //GAS_PRESSURE_NUM;制动气压报警次数       GAS_PRESSURE_LATEST; 制动气压最近报警时间        
        reportBase.setGAS_PRESSURE_NUM(reportBase.getGAS_PRESSURE_NUM() + Long.valueOf(signal.substring(14, 15)));  
        if(signal.substring(14, 15).equals("1")){  
            reportBase.setGAS_PRESSURE_LATEST(realRecord.getTERMINAL_TIME());
         }
      //WATER_LEVEL_NUM;//	     水位低报警次数         WATER_LEVEL_LATEST;水位低最近报警时间     
        reportBase.setWATER_LEVEL_NUM(reportBase.getWATER_LEVEL_NUM() + Long.valueOf(signal.substring(11, 12)));  
        if(signal.substring(11, 12).equals("1")){  
            reportBase.setWATER_LEVEL_LATEST(realRecord.getTERMINAL_TIME());
         }
      //RETARDER_TP_HIGH_NUM 缓速器高温报警次数      RETARDER_TP_HIGH_LATEST;缓速器高温最近报警时间         
        reportBase.setRETARDER_TP_HIGH_NUM(reportBase.getRETARDER_TP_HIGH_NUM() + Long.valueOf(signal.substring(30, 31)));  
        if(signal.substring(30, 31).equals("1")){  
            reportBase.setRETARDER_TP_HIGH_LATEST(realRecord.getTERMINAL_TIME());
         }
      //STORAGE_TP_HIGH_NUM;仓温报警次数  STORAGE_TP_HIGH_LATEST;仓温最近报警时间    
        reportBase.setSTORAGE_TP_HIGH_NUM(reportBase.getSTORAGE_TP_HIGH_NUM() + Long.valueOf(signal.substring(29, 30)));  
        if(signal.substring(29, 30).equals("1")){  
            reportBase.setSTORAGE_TP_HIGH_LATEST(realRecord.getTERMINAL_TIME());
         }
      //OIL_FILTER_NUM 燃油滤清报警次数 OIL_FILTER_LATEST;燃油滤清最近报警时间 ----   燃油堵塞信号
        reportBase.setOIL_FILTER_NUM(reportBase.getOIL_FILTER_NUM() + Long.valueOf(signal.substring(27, 28)));  
        if(signal.substring(27, 28).equals("1")){  
            reportBase.setOIL_FILTER_LATEST(realRecord.getTERMINAL_TIME());
         }
      //COOL_LIQUID_TP_NUM 冷却液温度报警次数   COOL_LIQUID_TP_LATEST;冷却液温度最近报警时间     即发动机水温  大于 103度
        if(realRecord.getE_WATER_TEMP()!= null&&!realRecord.getE_WATER_TEMP().equals("FFFF")&&Long.valueOf(realRecord.getE_WATER_TEMP())>103){
            reportBase.setCOOL_LIQUID_TP_NUM(reportBase.getCOOL_LIQUID_TP_NUM() + 1);  
            reportBase.setCOOL_LIQUID_TP_LATEST(realRecord.getTERMINAL_TIME());
        }
      //BATTERY_VOL_LOW_NUM 蓄电池电压低报警次数    BATTERY_VOL_LOW_LATEST蓄电池电压低最近报警时间    低于18V认为是告警
        if( realRecord.getBATTERY_VOLTAGE()!= null&&!realRecord.getBATTERY_VOLTAGE().equals("FFFF")&&Double.valueOf(realRecord.getBATTERY_VOLTAGE())<18){
            reportBase.setBATTERY_VOL_LOW_NUM(reportBase.getBATTERY_VOL_LOW_NUM() + 1);  
            reportBase.setBATTERY_VOL_LOW_LATEST(realRecord.getTERMINAL_TIME());
        }
//      HEAT_SYSTEM_WORK_NUM, HEAT_SYSTEM_WORK_TIME;//	     热管理系统工作时长 
        if(signal.substring(31, 32).equals("1")){ 
        	reportBase.setHEAT_SYSTEM_WORK_NUM(reportBase.getHEAT_SYSTEM_WORK_NUM() + Long.valueOf(signal.substring(31, 32)));  
        	reportBase.setHEAT_SYSTEM_WORK_TIME(reportBase.getHEAT_SYSTEM_WORK_TIME() + jiange);  
         }
        return reportBase;  


    }

    // 分析转速
    private RotateSpeed analyseRS(RotateSpeed r_s, float speed, String torque) {
        // if(r_s.getMAX_ROTATE_SPEED()>0){
        if (speed > r_s.getMAX_ROTATE_SPEED()) r_s.setMAX_ROTATE_SPEED(speed);
        // }else{
        // r_s.setMAX_ROTATE_SPEED(speed);
        // }

        // if(r_s.getMIN_ROTATE_SPEED()>0){
        if (speed < r_s.getMIN_ROTATE_SPEED()) r_s.setMIN_ROTATE_SPEED(speed);
        // }else{
        // r_s.setMIN_ROTATE_SPEED(speed);
        // }
        // 总次数
        if (speed > 0) {
            r_s.setSUMSPEED(r_s.getSUMSPEED() + 1);
        }
        // if(speed==0){
        // r_s.setROTATE_SPEED_0(r_s.getROTATE_SPEED_0()+1);
        // return r_s;
        // }
        if (0 < speed && speed < 100) {
            r_s.setROTATE_SPEED_0_100(r_s.getROTATE_SPEED_0_100() + 1);
            return r_s;
        }
        if (100 <= speed && speed < 200) {
            r_s.setROTATE_SPEED_100_200(r_s.getROTATE_SPEED_100_200() + 1);
            return r_s;
        }
        if (200 <= speed && speed < 300) {
            r_s.setROTATE_SPEED_200_300(r_s.getROTATE_SPEED_200_300() + 1);
            return r_s;
        }
        if (300 <= speed && speed < 400) {
            r_s.setROTATE_SPEED_300_400(r_s.getROTATE_SPEED_300_400() + 1);
            return r_s;
        }
        if (400 <= speed && speed < 500) {
            r_s.setROTATE_SPEED_400_500(r_s.getROTATE_SPEED_400_500() + 1);
            return r_s;
        }
        if (500 <= speed && speed < 600) {
            r_s.setROTATE_SPEED_500_600(r_s.getROTATE_SPEED_500_600() + 1);
            return r_s;
        }
        if (600 <= speed && speed < 700) {
            r_s.setROTATE_SPEED_600_700(r_s.getROTATE_SPEED_600_700() + 1);
            return r_s;
        }
        if (700 <= speed && speed < 800) {
            r_s.setROTATE_SPEED_700_800(r_s.getROTATE_SPEED_700_800() + 1);
            return r_s;
        }
        if (800 <= speed && speed < 900) {
            r_s.setROTATE_SPEED_800_900(r_s.getROTATE_SPEED_800_900() + 1);
            return r_s;
        }
        if (900 <= speed && speed < 1000) {
            r_s.setROTATE_SPEED_900_1000(r_s.getROTATE_SPEED_900_1000() + 1);
            return r_s;
        }
        if (1000 <= speed && speed < 1100) {
            r_s.setROTATE_SPEED_1000_1100(r_s.getROTATE_SPEED_1000_1100() + 1);
            return r_s;
        }
        if (1100 <= speed && speed < 1200) {
            r_s.setROTATE_SPEED_1100_1200(r_s.getROTATE_SPEED_1100_1200() + 1);
            return r_s;
        }
        if (1200 <= speed && speed < 1300) {
            r_s.setROTATE_SPEED_1200_1300(r_s.getROTATE_SPEED_1200_1300() + 1);
            return r_s;
        }
        if (1300 <= speed && speed < 1400) {
            r_s.setROTATE_SPEED_1300_1400(r_s.getROTATE_SPEED_1300_1400() + 1);
            return r_s;
        }
        if (1400 <= speed && speed < 1500) {
            r_s.setROTATE_SPEED_1400_1500(r_s.getROTATE_SPEED_1400_1500() + 1);
            return r_s;
        }
        if (1500 <= speed && speed < 1600) {
            r_s.setROTATE_SPEED_1500_1600(r_s.getROTATE_SPEED_1500_1600() + 1);
            return r_s;
        }
        if (1600 <= speed && speed < 1700) {
            r_s.setROTATE_SPEED_1600_1700(r_s.getROTATE_SPEED_1600_1700() + 1);
            return r_s;
        }
        if (1700 <= speed && speed < 1800) {
            r_s.setROTATE_SPEED_1700_1800(r_s.getROTATE_SPEED_1700_1800() + 1);
            return r_s;
        }
        if (1800 <= speed && speed < 1900) {
            r_s.setROTATE_SPEED_1800_1900(r_s.getROTATE_SPEED_1800_1900() + 1);
            return r_s;
        }
        if (1900 <= speed && speed < 2000) {
            r_s.setROTATE_SPEED_1900_2000(r_s.getROTATE_SPEED_1900_2000() + 1);
            return r_s;
        }
        if (2000 <= speed && speed < 2100) {
            r_s.setROTATE_SPEED_2000_2100(r_s.getROTATE_SPEED_2000_2100() + 1);
            return r_s;
        }
        if (2100 <= speed && speed < 2200) {
            r_s.setROTATE_SPEED_2100_2200(r_s.getROTATE_SPEED_2100_2200() + 1);
            return r_s;
        }
        if (2200 <= speed && speed < 2300) {
            r_s.setROTATE_SPEED_2200_2300(r_s.getROTATE_SPEED_2200_2300() + 1);
            return r_s;
        }
        if (2300 <= speed && speed < 2400) {
            r_s.setROTATE_SPEED_2300_2400(r_s.getROTATE_SPEED_2300_2400() + 1);
        }
        if (2400 <= speed && speed < 2500) {
            r_s.setROTATE_SPEED_2400_2500(r_s.getROTATE_SPEED_2400_2500() + 1);
            return r_s;
        }
        if (2500 <= speed && speed < 2600) {
            r_s.setROTATE_SPEED_2500_2600(r_s.getROTATE_SPEED_2500_2600() + 1);
            return r_s;
        }
        if (2600 <= speed && speed < 2700) {
            r_s.setROTATE_SPEED_2600_2700(r_s.getROTATE_SPEED_2600_2700() + 1);
            return r_s;
        }
        if (2700 <= speed && speed < 2800) {
            r_s.setROTATE_SPEED_2700_2800(r_s.getROTATE_SPEED_2700_2800() + 1);
        }
        if (2800 <= speed && speed < 2900) {
            r_s.setROTATE_SPEED_2800_2900(r_s.getROTATE_SPEED_2800_2900() + 1);
            return r_s;
        }
        if (2900 <= speed && speed < 3000) {
            r_s.setROTATE_SPEED_2900_3000(r_s.getROTATE_SPEED_2900_3000() + 1);
            return r_s;
        }
        if (3000 <= speed) {
            r_s.setROTATE_SPEED_MAX(r_s.getROTATE_SPEED_MAX() + 1);
            return r_s;
        }
        if (1000 <= speed && speed <= 1600) {
            float f = Float.valueOf(torque) * 100;
            if (60 <= f && f <= 80) {
                r_s.setPERCENT_60_80_FUHELV(r_s.getPERCENT_60_80_FUHELV() + 1);
            }
        }
        return r_s;
    }

    // 转速百分比
    public RotateSpeed analyseRSper(RotateSpeed r_s, float runtime) {
        if (r_s.getSUMSPEED() > 0) {
            runtime = r_s.getSUMSPEED() * 5;
//            r_s.setROTATE_SPEED_0_TIME(r_s.getROTATE_SPEED_0() / r_s.getSUMSPEED() * runtime);
            r_s.setROTATE_SPEED_0_100_TIME(r_s.getROTATE_SPEED_0_100() / r_s.getSUMSPEED()
                    * runtime);
            r_s.setROTATE_SPEED_100_200_TIME(r_s.getROTATE_SPEED_100_200() / r_s.getSUMSPEED()
                    * runtime);
            r_s.setROTATE_SPEED_200_300_TIME(r_s.getROTATE_SPEED_200_300() / r_s.getSUMSPEED()
                    * runtime);
            r_s.setROTATE_SPEED_300_400_TIME(r_s.getROTATE_SPEED_300_400() / r_s.getSUMSPEED()
                    * runtime);
            r_s.setROTATE_SPEED_400_500_TIME(r_s.getROTATE_SPEED_400_500() / r_s.getSUMSPEED()
                    * runtime);
            r_s.setROTATE_SPEED_500_600_TIME(r_s.getROTATE_SPEED_500_600() / r_s.getSUMSPEED()
                    * runtime);
            r_s.setROTATE_SPEED_600_700_TIME(r_s.getROTATE_SPEED_700_800() / r_s.getSUMSPEED()
                    * runtime);
            r_s.setROTATE_SPEED_700_800_TIME(r_s.getROTATE_SPEED_700_800() / r_s.getSUMSPEED()
                    * runtime);
            r_s.setROTATE_SPEED_800_900_TIME(r_s.getROTATE_SPEED_800_900() / r_s.getSUMSPEED()
                    * runtime);
            r_s.setROTATE_SPEED_900_1000_TIME(r_s.getROTATE_SPEED_900_1000() / r_s.getSUMSPEED()
                    * runtime);
            r_s.setROTATE_SPEED_1000_1100_TIME(r_s.getROTATE_SPEED_1000_1100() / r_s.getSUMSPEED()
                    * runtime);
            r_s.setROTATE_SPEED_1100_1200_TIME(r_s.getROTATE_SPEED_1100_1200() / r_s.getSUMSPEED()
                    * runtime);
            r_s.setROTATE_SPEED_1200_1300_TIME(r_s.getROTATE_SPEED_1200_1300() / r_s.getSUMSPEED()
                    * runtime);
            r_s.setROTATE_SPEED_1300_1400_TIME(r_s.getROTATE_SPEED_1300_1400() / r_s.getSUMSPEED()
                    * runtime);
            r_s.setROTATE_SPEED_1400_1500_TIME(r_s.getROTATE_SPEED_1400_1500() / r_s.getSUMSPEED()
                    * runtime);
            r_s.setROTATE_SPEED_1500_1600_TIME(r_s.getROTATE_SPEED_1500_1600() / r_s.getSUMSPEED()
                    * runtime);
            r_s.setROTATE_SPEED_1600_1700_TIME(r_s.getROTATE_SPEED_1600_1700() / r_s.getSUMSPEED()
                    * runtime);
            r_s.setROTATE_SPEED_1700_1800_TIME(r_s.getROTATE_SPEED_1700_1800() / r_s.getSUMSPEED()
                    * runtime);
            r_s.setROTATE_SPEED_1800_1900_TIME(r_s.getROTATE_SPEED_1800_1900() / r_s.getSUMSPEED()
                    * runtime);
            r_s.setROTATE_SPEED_1900_2000_TIME(r_s.getROTATE_SPEED_1900_2000() / r_s.getSUMSPEED()
                    * runtime);
            r_s.setROTATE_SPEED_2000_2100_TIME(r_s.getROTATE_SPEED_2000_2100() / r_s.getSUMSPEED()
                    * runtime);
            r_s.setROTATE_SPEED_2100_2200_TIME(r_s.getROTATE_SPEED_2100_2200() / r_s.getSUMSPEED()
                    * runtime);
            r_s.setROTATE_SPEED_2200_2300_TIME(r_s.getROTATE_SPEED_2200_2300() / r_s.getSUMSPEED()
                    * runtime);
            r_s.setROTATE_SPEED_2300_2400_TIME(r_s.getROTATE_SPEED_2300_2400() / r_s.getSUMSPEED()
                    * runtime);
            r_s.setROTATE_SPEED_2400_2500_TIME(r_s.getROTATE_SPEED_2400_2500() / r_s.getSUMSPEED()
                    * runtime);
            r_s.setROTATE_SPEED_2500_2600_TIME(r_s.getROTATE_SPEED_2500_2600() / r_s.getSUMSPEED()
                    * runtime);
            r_s.setROTATE_SPEED_2600_2700_TIME(r_s.getROTATE_SPEED_2600_2700() / r_s.getSUMSPEED()
                    * runtime);
            r_s.setROTATE_SPEED_2700_2800_TIME(r_s.getROTATE_SPEED_2700_2800() / r_s.getSUMSPEED()
                    * runtime);
            r_s.setROTATE_SPEED_2800_2900_TIME(r_s.getROTATE_SPEED_2800_2900() / r_s.getSUMSPEED()
                    * runtime);
            r_s.setROTATE_SPEED_2900_3000_TIME(r_s.getROTATE_SPEED_2900_3000() / r_s.getSUMSPEED()
                    * runtime);
            r_s.setROTATE_SPEED_MAX_TIME(r_s.getROTATE_SPEED_MAX() / r_s.getSUMSPEED() * runtime);
        }
        return r_s;
    }

    // 车速分析次数
    public VehicleSpeed analyseVS(VehicleSpeed v_s, Float speed) {

        // if(v_s.getMAX_SPEEDING()>0){
        if (speed > v_s.getMAX_SPEEDING()) v_s.setMAX_SPEEDING(speed);
        // }else{
        // v_s.setMAX_SPEEDING(speed);
        // }

        // if(v_s.getMIN_SPEEDING()>0){
        if (speed < v_s.getMIN_SPEEDING()) v_s.setMIN_SPEEDING(speed);
        // }else{
        // v_s.setMIN_SPEEDING(speed);
        // }

        // 总次数
        if (speed >= 0) {
            v_s.setSUMSPEED(v_s.getSUMSPEED() + 1);
        }
        if (speed == 0) {
            v_s.setSPEEDING_0(v_s.getSPEEDING_0() + 1);
            return v_s;
        }
        if (0 < speed && speed < 10) {
            v_s.setSPEEDING_0_10(v_s.getSPEEDING_0_10() + 1);
            return v_s;
        }
        if (10 <= speed && speed < 20) {
            v_s.setSPEEDING_10_20(v_s.getSPEEDING_10_20() + 1);
            return v_s;
        }
        if (20 <= speed && speed < 30) {
            v_s.setSPEEDING_20_30(v_s.getSPEEDING_20_30() + 1);
            return v_s;
        }
        if (30 <= speed && speed < 40) {
            v_s.setSPEEDING_30_40(v_s.getSPEEDING_30_40() + 1);
            return v_s;
        }
        if (40 <= speed && speed < 50) {
            v_s.setSPEEDING_40_50(v_s.getSPEEDING_40_50() + 1);
            return v_s;
        }
        if (50 <= speed && speed < 60) {
            v_s.setSPEEDING_50_60(v_s.getSPEEDING_50_60() + 1);
            return v_s;
        }
        if (60 <= speed && speed < 70) {
            v_s.setSPEEDING_60_70(v_s.getSPEEDING_60_70() + 1);
            return v_s;
        }
        if (70 <= speed && speed < 80) {
            v_s.setSPEEDING_70_80(v_s.getSPEEDING_70_80() + 1);
            return v_s;
        }
        if (80 <= speed && speed < 90) {
            v_s.setSPEEDING_80_90(v_s.getSPEEDING_80_90() + 1);
            return v_s;
        }
        if (90 <= speed && speed < 100) {
            v_s.setSPEEDING_90_100(v_s.getSPEEDING_90_100() + 1);
            return v_s;
        }
        if (100 <= speed && speed < 110) {
            v_s.setSPEEDING_100_110(v_s.getSPEEDING_100_110() + 1);
            return v_s;
        }
        if (110 <= speed && speed < 120) {
            v_s.setSPEEDING_110_120(v_s.getSPEEDING_110_120() + 1);
            return v_s;
        }
        if (120 <= speed && speed < 130) {
            v_s.setSPEEDING_120_130(v_s.getSPEEDING_120_130() + 1);
            return v_s;
        }
        if (130 <= speed && speed < 140) {
            v_s.setSPEEDING_130_140(v_s.getSPEEDING_130_140() + 1);
            return v_s;
        }
        if (140 <= speed && speed < 150) {
            v_s.setSPEEDING_140_150(v_s.getSPEEDING_140_150() + 1);
            return v_s;
        }
        if (150 <= speed && speed < 160) {
            v_s.setSPEEDING_150_160(v_s.getSPEEDING_150_160() + 1);
            return v_s;
        }
        if (160 <= speed && speed < 170) {
            v_s.setSPEEDING_160_170(v_s.getSPEEDING_160_170() + 1);
            return v_s;
        }
        if (170 <= speed && speed < 180) {
            v_s.setSPEEDING_170_180(v_s.getSPEEDING_170_180() + 1);
            return v_s;
        }
        if (180 <= speed && speed < 190) {
            v_s.setSPEEDING_180_190(v_s.getSPEEDING_180_190() + 1);
            return v_s;
        }
        if (190 <= speed && speed < 200) {
            v_s.setSPEEDING_190_200(v_s.getSPEEDING_190_200() + 1);
            return v_s;
        }
        if (200 <= speed) {
            v_s.setSPEEDING_MAX(v_s.getSPEEDING_MAX() + 1);
            return v_s;
        }
        return v_s;

    }

    // 车速分析百分比
    public VehicleSpeed analyseVSper(VehicleSpeed v_s, float runtime) {
        if (v_s.getSUMSPEED() > 0) {
            runtime = v_s.getSUMSPEED() * 5;
            // v_s.setSPEEDING_0_TIME((670/674) *10);
            v_s.setSPEEDING_0_TIME((v_s.getSPEEDING_0() / v_s.getSUMSPEED()) * runtime);
            v_s.setSPEEDING_0_10_TIME((v_s.getSPEEDING_0_10() / v_s.getSUMSPEED()) * runtime);
            v_s.setSPEEDING_10_20_TIME((v_s.getSPEEDING_10_20() / v_s.getSUMSPEED()) * runtime);
            v_s.setSPEEDING_20_30_TIME((v_s.getSPEEDING_20_30() / v_s.getSUMSPEED()) * runtime);
            v_s.setSPEEDING_30_40_TIME((v_s.getSPEEDING_30_40() / v_s.getSUMSPEED()) * runtime);
            v_s.setSPEEDING_40_50_TIME((v_s.getSPEEDING_40_50() / v_s.getSUMSPEED()) * runtime);
            v_s.setSPEEDING_50_60_TIME((v_s.getSPEEDING_50_60() / v_s.getSUMSPEED()) * runtime);
            v_s.setSPEEDING_60_70_TIME((v_s.getSPEEDING_60_70() / v_s.getSUMSPEED()) * runtime);
            v_s.setSPEEDING_70_80_TIME((v_s.getSPEEDING_70_80() / v_s.getSUMSPEED()) * runtime);
            v_s.setSPEEDING_80_90_TIME((v_s.getSPEEDING_80_90() / v_s.getSUMSPEED()) * runtime);
            v_s.setSPEEDING_90_100_TIME((v_s.getSPEEDING_90_100() / v_s.getSUMSPEED()) * runtime);
            v_s.setSPEEDING_100_110_TIME((v_s.getSPEEDING_100_110() / v_s.getSUMSPEED()) * runtime);
            v_s.setSPEEDING_110_120_TIME((v_s.getSPEEDING_110_120() / v_s.getSUMSPEED()) * runtime);
            v_s.setSPEEDING_120_130_TIME((v_s.getSPEEDING_120_130() / v_s.getSUMSPEED()) * runtime);
            v_s.setSPEEDING_130_140_TIME((v_s.getSPEEDING_130_140() / v_s.getSUMSPEED()) * runtime);
            v_s.setSPEEDING_140_150_TIME((v_s.getSPEEDING_140_150() / v_s.getSUMSPEED()) * runtime);
            v_s.setSPEEDING_150_160_TIME((v_s.getSPEEDING_150_160() / v_s.getSUMSPEED()) * runtime);
            v_s.setSPEEDING_160_170_TIME((v_s.getSPEEDING_160_170() / v_s.getSUMSPEED()) * runtime);
            v_s.setSPEEDING_170_180_TIME((v_s.getSPEEDING_170_180() / v_s.getSUMSPEED()) * runtime);
            v_s.setSPEEDING_180_190_TIME((v_s.getSPEEDING_180_190() / v_s.getSUMSPEED()) * runtime);
            v_s.setSPEEDING_190_200_TIME((v_s.getSPEEDING_190_200() / v_s.getSUMSPEED()) * runtime);
            v_s.setSPEEDING_MAX_TIME((v_s.getSPEEDING_MAX() / v_s.getSUMSPEED()) * runtime);
        }
        return v_s;

    }

    // 分析开关量
    // private void analyseOnOffList() throws Exception {
    // DayReport reportResult = new DayReport();//存当日累计结果
    // long reportSecSpace= Long
    // .valueOf(Config.props.getProperty("reportSecSpace"));//秒数据时间间隔
    // float diff=0,interval=(float) 0.8;
    // @SuppressWarnings("unused")
    // String type="00";//急加速 47 ;急减速 48
    // //System.out.println(" 开关量interval:"+interval);
    // List<OnOffBean> rows = reportDAO.getClwOnOffRecord(strVin, strDate);
    // boolean continuous=false;//连续两条记录是否连续 连续ture 反之亦然。
    // int po=0; //连续的时候赋值为0，断开开的时候加1，如果大于等于2则说明前一点为为单点
    // OnOffBean rapidPerv=null,rapidNonce=null;
    // if (rows != null & rows.size() > 0) {
    // rapidPerv = rows.get(0);
    // for (int i = 1; i<rows.size(); i++) {
    // rapidNonce=rows.get(i);
    // if (rows.get(i) != null
    // && rows.get(i).getAFTER_VALUE().length() == 32) {
    // reportResult = analyseOnOff(reportResult, rows.get(i).getAFTER_VALUE());// 累计开关量
    // }
    // diff=(df.parse(rows.get(i).getTERMINAL_TIME()).getTime()-df.parse(rows.get(i-1).getTERMINAL_TIME()).getTime())/1000;
    // if(diff>0&&diff<interval){
    // continuous=true;//两条记录连续
    // }else{
    // continuous=false;//两条记录不连续
    // }
    // //计算各种持续时间
    // if(continuous){//记录连续的时候累计时间
    // po=0;
    // float Brake_shoe_time = 0;//制动蹄片磨损时长
    // if(rapidPerv.getAFTER_VALUE().substring(9,
    // 10).equals("1")&&rapidNonce.getAFTER_VALUE().substring(9, 10).equals("1")){
    // Brake_shoe_time=diff;
    // }
    // if(rapidPerv.getAFTER_VALUE().substring(9,
    // 10).equals("1")&&rapidNonce.getAFTER_VALUE().substring(9, 10).equals("0")){
    // Brake_shoe_time=diff/2;
    // }
    // if(i==rows.size()&&rapidPerv.getAFTER_VALUE().substring(9,
    // 10).equals("0")&&rapidNonce.getAFTER_VALUE().substring(9, 10).equals("1")){
    // Brake_shoe_time=diff/2;
    // }
    // reportResult.setBRAKE_SHOE_TIME(reportResult.getBRAKE_SHOE_TIME()+Brake_shoe_time);
    //						
    // float Air_filter_clog_num=0;//空滤堵塞时间
    // if(rapidPerv.getAFTER_VALUE().substring(8,
    // 9).equals("1")&&rapidNonce.getAFTER_VALUE().substring(8, 9).equals("1")){
    // Air_filter_clog_num=diff;
    // }
    // if(rapidPerv.getAFTER_VALUE().substring(8,
    // 9).equals("1")&&rapidNonce.getAFTER_VALUE().substring(8, 9).equals("0")){
    // Air_filter_clog_num=diff/2;
    // }
    // if(i==rows.size()&&rapidPerv.getAFTER_VALUE().substring(8,
    // 9).equals("0")&&rapidNonce.getAFTER_VALUE().substring(8, 9).equals("1")){
    // Air_filter_clog_num=diff/2;
    // }
    // reportResult.setAIR_FILTER_CLOG_TIME(reportResult.getAIR_FILTER_CLOG_TIME()+Air_filter_clog_num);
    //						 					
    // float JIARE_TIME=0; //加热器时间(当日合计时间)
    // if(rapidPerv.getAFTER_VALUE().substring(15,
    // 16).equals("1")&&rapidNonce.getAFTER_VALUE().substring(15, 16).equals("1")){
    // JIARE_TIME=diff;
    // }
    // if(rapidPerv.getAFTER_VALUE().substring(15,
    // 16).equals("1")&&rapidNonce.getAFTER_VALUE().substring(15, 16).equals("0")){
    // JIARE_TIME=diff/2;
    // }
    // if(i==rows.size()&&rapidPerv.getAFTER_VALUE().substring(15,
    // 16).equals("0")&&rapidNonce.getAFTER_VALUE().substring(15, 16).equals("1")){
    // JIARE_TIME=diff/2;
    // }
    // reportResult.setJIARE_TIME(reportResult.getJIARE_TIME()+JIARE_TIME);
    // float KONGTIAO_TIME=0 ; //空调时间(当日合计时间)
    // if(rapidPerv.getAFTER_VALUE().substring(21,
    // 22).equals("1")&&rapidNonce.getAFTER_VALUE().substring(21, 22).equals("1")){
    // KONGTIAO_TIME=diff;
    // }
    // if(rapidPerv.getAFTER_VALUE().substring(21,
    // 22).equals("1")&&rapidNonce.getAFTER_VALUE().substring(21, 22).equals("0")){
    // KONGTIAO_TIME=diff/2;
    // }
    // if(i==rows.size()&&rapidPerv.getAFTER_VALUE().substring(21,
    // 22).equals("0")&&rapidNonce.getAFTER_VALUE().substring(21, 22).equals("1")){
    // KONGTIAO_TIME=diff/2;
    // }
    // reportResult.setKONGTIAO_TIME(reportResult.getKONGTIAO_TIME()+KONGTIAO_TIME);
    // //continue;
    // }else{
    // po=po+1;
    // if(po>=2){//前一点为孤立点//
    // //制动蹄片磨损时长
    // if(rapidPerv.getAFTER_VALUE().substring(0, 1).equals("1"))
    // reportResult.setBRAKE_SHOE_TIME(reportResult.getBRAKE_SHOE_TIME()+reportSecSpace);
    // //空滤堵塞时间
    // if(rapidPerv.getAFTER_VALUE().substring(8, 9).equals("1"))
    // reportResult.setAIR_FILTER_CLOG_TIME(reportResult.getAIR_FILTER_CLOG_TIME()+reportSecSpace);
    // //加热器时间(当日合计时间)
    // if(rapidPerv.getAFTER_VALUE().substring(15, 16).equals("1"))
    // reportResult.setJIARE_TIME(reportResult.getJIARE_TIME()+reportSecSpace);
    // //空调时间(当日合计时间)
    // if(rapidPerv.getAFTER_VALUE().substring(21, 22).equals("1"))
    // reportResult.setKONGTIAO_TIME(reportResult.getKONGTIAO_TIME()+reportSecSpace);
    //							 
    // }
    // rapidPerv=rows.get(i);//新的开始；
    // rapidNonce=null;
    // }
    // }
    //				
    // }
    // //更新数据库
    // reportDAO.updateOnOffData(reportResult,strVin, strDate);
    //			
    // }
    /**
     *  
     */

    public static void main(String[] args) {
        // System.out.println(AnalysisReportThread.buildLocMsgListQueryString("123",
        // String a = "2009-07-29";
        // float aa = (670 / 674) * 10;
        //
        // System.out.println(aa);
        // List list = new ArrayList();
        //  
        // list.add("1");
        // list.add("2");
        // System.out.println(list);
        // list.add(0, "0");
        // System.out.println(list);

        /*
         * List<String> list = new ArrayList<String>(); for (int i = 1; i < 4; i++) {
         * list.add(String.valueOf(i)); } for (int i = 0; i < list.size(); i++) {
         * System.out.println(list.get(i)); if (i == 1) { list.add("11"); list.add("12");
         * list.add("13"); list.add("14"); } }
         */
    }
}
