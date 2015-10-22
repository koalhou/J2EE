/**
 * @author haoxy
 * @createdate 2013年9月14日 下午3:41:36
 * @description 
 */
package com.yutong.axxc.tqc.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.rubyeye.xmemcached.MemcachedClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yutong.axxc.tqc.common.DistanceConstant;
import com.yutong.axxc.tqc.entity.distance.DistanceMsg;
import com.yutong.axxc.tqc.entity.distance.Vehicle;
import com.yutong.axxc.tqc.entity.site.Site;
import com.yutong.axxc.tqc.entity.vehicle.VehicleReal;
import com.yutong.axxc.tqc.mapper.MybatisDAO;
import com.yutong.axxc.tqc.service.algorithm.AlgorithmUtil;
import com.yutong.axxc.tqc.service.algorithm.IMileageCalculator;
import com.yutong.axxc.tqc.service.algorithm.ITimeCalculator;

/**
 * @author haoxy
 * 
 */
@Service
public class DistanceService {
	private static Logger log = LoggerFactory.getLogger(DistanceService.class);

	private static int MaxTimespanInMinute = 5;

	@Autowired
	private MemcachedClient memcachedClient;

	@Autowired
	protected MybatisDAO dao;

	@Autowired
	protected ITimeCalculator timeCalculator;

	@Autowired
	protected IMileageCalculator mileageCalculator;

	public DistanceMsg getDistance(String stu_id) {
		DistanceMsg msg = null;
//		// 定义返回参数
//		String stationId = null;
//		String stationName = null;
//		String remindAlias = null;
//		String remindType = null;
//		String remindValue = null;
//		String vehiclePlate = null;
//
//		try {
//			int stuId = Integer.parseInt(stu_id);
//			// 获取学生所在的车辆
//			Vehicle v = getVehicleByStuId(stuId);
//			String vin = v.getVin();
//			vehiclePlate = v.getLn();
//
//			// 获取remind_type
//			remindType = getRemindType(stuId);
//
//			// 获取学生当前状态
//			String studentState = getStudentState(stu_id);
//
//			// studentState = DistanceConstant.STUDENTSTATE_ONBUSUP;
//			// 学生状态既不等于不知道也不等于放学下车后
//			if ((!studentState.equals(DistanceConstant.STUDENTSTATE_UNKOWN))
//					&& (!studentState
//							.equals(DistanceConstant.STUDENTSTATE_AFTERSCHOOL))
//					&& (!studentState
//							.equals(DistanceConstant.STUDENTSTATE_INSCHOOL))) {
//				// 根据状态，获取要计算的目标站点
//				Site targetSite = getTargetSiteByStuIdAndVin(stuId,
//						studentState, vin);
//
//				if (targetSite != null) {
//					// 给输出参数赋值
//					// stationId = String.valueOf(targetSite.getSite_Id());
//					// stationName = targetSite.getSite_name();
//					// remindAlias = targetSite.getSite_remark();
//
//					String currentLongitudeStr = null;
//					String currentLantitudeStr = null;
//					String targetLongitudeStr = null;
//					String targetLantitudeeStr = null;
//					// 得到目的地的GPS
//					targetLongitudeStr = targetSite.getSite_longitude();
//					targetLantitudeeStr = targetSite.getSite_latitude();
//
//					if (studentState
//							.equals(DistanceConstant.STUDENTSTATE_INSCHOOL))// 如果当前学生在学校，那么请求的信息，应该是学校到目标站点的数据，而不是当前车辆的具体位置到目标站点的数据。
//					{
//						List<Site> sites = getStudentSites(stuId,
//								DistanceConstant.UPORDOWN_UP);
//
//						if (sites != null && sites.size() > 0) {
//							Site sourceSite = sites.get(0);
//
//							currentLongitudeStr = sourceSite
//									.getSite_longitude();
//							currentLantitudeStr = sourceSite.getSite_latitude();
//						}
//					} else {
//						// 获取当前车辆的位置上报信息
//
//						VehicleReal info = null;
//						try {
//							info = memcachedClient
//									.get(DistanceConstant.VEHICLEREAL + vin);
//						} catch (Exception e) {
//
//						}
//						// 过滤，只有认为实时数据在一定的有效期内，才可计算。
//						if (info != null) {
//							int timespanInMinute = AlgorithmUtil.getTimespanMinutes(new Date(),	info.getUpdate_time());
//
//							if (timespanInMinute > MaxTimespanInMinute) {
//								info = null;
//							}
//						}
//
//						if (info == null) {
//							info = getVehicleRealtimeInfoFromDB(vin);
//							// 过滤，只有认为实时数据在一定的有效期内，才可计算。
//							if (info != null) {
//								int timespanInMinute = AlgorithmUtil.getTimespanMinutes(new Date(),
//												info.getUpdate_time());
//
//								if (timespanInMinute > MaxTimespanInMinute) {
//									info = null;
//								}
//							}
//						}
//						// info = new VehicleRealtimeInfo();
//						// info.setLongitude(String.valueOf(120.662241666667));
//						// info.setLatitude(String.valueOf(31.187625));
//						// info.setTerminal_time(new Date());
//
//						if (info != null) {
//							int timespanInMinute = AlgorithmUtil
//									.getTimespanMinutes(new Date(),
//											info.getUpdate_time());
//
//							if (timespanInMinute <= MaxTimespanInMinute) {
//								currentLongitudeStr = info.getLongitude();
//								currentLantitudeStr = info.getLatitude();
//
//								// 根据不同德提醒类型调用算法接口
//								if (remindType
//										.equals(DistanceConstant.REMINDTYPE_TIME)) {
//									int minutes = timeCalculator
//											.getTimespanInMinute(
//													vin,
//													new Date(),
//													Double.parseDouble(currentLongitudeStr),
//													Double.parseDouble(currentLantitudeStr),
//													Double.parseDouble(targetLongitudeStr),
//													Double.parseDouble(targetLantitudeeStr));
//									remindValue = String.valueOf(minutes);
//								} else if (remindType
//										.equals(DistanceConstant.REMINDTYPE_MILEAGE)) {
//									int mileage = mileageCalculator
//											.getMileage(
//													vin,
//													new Date(),
//													Double.parseDouble(currentLongitudeStr),
//													Double.parseDouble(currentLantitudeStr),
//													Double.parseDouble(targetLongitudeStr),
//													Double.parseDouble(targetLantitudeeStr));
//									remindValue = String.valueOf(mileage);
//								}
//
//								msg = new DistanceMsg();
//
//								msg.setRemindType(remindType);
//								msg.setRemindValue(remindValue);
//								msg.setRemindAlias(remindAlias);
//								msg.setStationName(stationName);
//								msg.setStationId(stationId);
//								msg.setVehichePlate(vehiclePlate);
//							}
//						}
//					}
//				}
//			}
//		} catch (Exception e) {
//			log.error("RelativePositionService_sendRelativePosition:", e);
//		}

		return msg;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<Site> getStudentSites(int stuId, int upOrDown) {
		List<Site> sites = null;

		try {
			Map map = new HashMap();
			map.put("stuId", stuId);
			map.put("controlStation", upOrDown);
			sites = dao.getList("Algorithm.getSitesByStuId", map);
		} catch (Exception e) {
			log.error("RelativePositionService_getStudentSites:", e);
		}

		return sites;
	}

	private String getRemindType(int stuId) {
		return DistanceConstant.REMINDTYPE_MILEAGE;
	}

	private String getStuStateInCache(int stuId) {
//		String state = DistanceConstant.STUDENTSTATE_UNKOWN;
//
//		Students stu = null;
//		try {
//			stu = memcachedClient.get(DistanceConstant.STUSTATEINFO + stuId);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		if (stu != null) {
//			state = stu.getStu_state();
//		} else {
//			state = DistanceConstant.STUDENTSTATE_UNKOWN;
//		}
//
//		return state;
		return null;
	}

	private String getStuStateInDB(int stuId) {
		String state = DistanceConstant.STUDENTSTATE_UNKOWN;

		try {
			int stateInDB = dao.get("Algorithm.getStudentState", stuId);

			state = String.valueOf(stateInDB);
		} catch (Exception e) {

		}

		return state;
	}

	/*
	 * 获取目标站点 如果状态是未知：不做计算 如果状态是上学前：学生上行站点的第一站 如果状态是上学在车上:学生上行站点的最后一站
	 * 如果状态是在学校：学生下行站点的最后一站 如果状态是放学在车上：学生下行站点的最后一站 如果状态的放学下车后：不做计算
	 */
	private Site getTargetSiteByStuIdAndVin(int stuId, String studentState,
			String vin) {
		Site s = null;

		try {
			if (!studentState.equals(String
					.valueOf(DistanceConstant.STUDENTSTATE_UNKOWN))) {
				if (studentState
						.equals(DistanceConstant.STUDENTSTATE_BEFORESCHOOL)) {
					List<Site> sites = getStudentSites(stuId,
							DistanceConstant.UPORDOWN_UP);
					if (sites != null && sites.size() > 0) {
						s = sites.get(0);
					}
				} else if (studentState
						.equals(DistanceConstant.STUDENTSTATE_ONBUSUP)) {
					List<Site> sites = getStudentSites(stuId,
							DistanceConstant.UPORDOWN_UP);
					if (sites != null && sites.size() > 0) {
						s = sites.get(sites.size() - 1);
					}
				} else if (studentState
						.equals(DistanceConstant.STUDENTSTATE_INSCHOOL)) {
					List<Site> sites = getStudentSites(stuId,
							DistanceConstant.UPORDOWN_DOWN);
					if (sites != null && sites.size() > 0) {
						s = sites.get(sites.size() - 1);
					}
				} else if (studentState
						.equals(DistanceConstant.STUDENTSTATE_ONBUSDOWN)) {
					List<Site> sites = getStudentSites(stuId,
							DistanceConstant.UPORDOWN_DOWN);
					if (sites != null && sites.size() > 0) {
						s = sites.get(sites.size() - 1);
					}
				} else if (studentState
						.equals(DistanceConstant.STUDENTSTATE_AFTERSCHOOL)) {

				}
			}
		} catch (Exception e) {
			log.error("RelativePositionService_getTargetSiteByStuIdAndVin:", e);
		}

		return s;
	}

	private Vehicle getVehicleByStuId(int stuId) {
		Vehicle v = dao.get("Algorithm.getVehicleByStuId", stuId);
		return v;
	}

	public String getStudentState(String stuId) {
		int stu_id = Integer.parseInt(stuId);

		String state = DistanceConstant.STUDENTSTATE_UNKOWN;

		state = getStuStateInCache(stu_id);

		if (state.equals(DistanceConstant.STUDENTSTATE_UNKOWN)) {
			state = getStuStateInDB(stu_id);
		}

		return state;
	}

	public VehicleReal getVehicleRealtimeInfo(String vin, String line_id) {
		VehicleReal vr = null;
		try {
//			vr = memcachedClient.get(DistanceConstant.VEHICLEREAL + vin);
			if (vr == null) {
				vr = getVehicleRealtimeInfoFromDB(vin, line_id);
			}
		} catch (Exception e) {
			log.error("DistanceService_getVehicleRealtimeInfo", e);
		}
		return vr;
	}

	private VehicleReal getVehicleRealtimeInfoFromDB(String vin, String line_id) {
		VehicleReal vr = null;
		try {
			Map<String, String> conditions = new HashMap<String, String>();
			conditions.put("VEHICLE_VIN", vin);
			conditions.put("LINE_ID", line_id);
			vr = dao.get("Algorithm.getVehicleRealtimeInfo", conditions);
		} catch (Exception e) {
			log.error("DistanceService_getVehicleRealtimeInfoFromDB", e);
		}
		return vr;
	}
}
