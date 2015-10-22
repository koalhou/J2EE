package com.yutong.axxc.parents.api.msg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.yutong.axxc.parents.common.CachedCommon;
import com.yutong.axxc.parents.common.ErrorConstant;
import com.yutong.axxc.parents.entity.homePage.WeatherInfoResp;
import com.yutong.axxc.parents.exception.common.ApplicationException;
import com.yutong.axxc.parents.service.BaseService;
import com.yutong.axxc.parents.service.EtagService;
import com.yutong.axxc.parents.tools.JacksonUtils;
import com.yutong.axxc.parents.tools.WeatherReport;

public class MsgAPIImpl implements MsgAPI{
	private Logger logger = LoggerFactory.getLogger(MsgAPIImpl.class);
	@Context
	private MessageContext context;
	@Autowired
	protected BaseService baseService;
	@Autowired
	protected EtagService etagService;
	
	/**
	 * 1.获取学生所乘坐校车的公司的城市并处理
	 * 2.从中国天气网读取当天的天气信息
	 */
	@Override 
	public Response getWeather(String sid) {

		if(!StringUtils.hasText(sid)){
			logger.error("学生ID为空");
			throw new ApplicationException(ErrorConstant.ERROR10002,
					Response.Status.BAD_REQUEST);
		}
		
		String key=CachedCommon.CACHED_CHILD_ZONE+sid;
		String city=(String)etagService.get(key);
		if(city==null){
			city=baseService.get("Child.getCityBySid", sid);
			city=city.replaceAll("市|自治州|地区|藏族|羌族|回族|土家族|苗族|傣族|彝族|特别行政区", "");
			etagService.put(key, CachedCommon.CACHED_MINTER_7D, city);
		}
		
		
		try {
			key=CachedCommon.CACHED_WEATHER+city;
			WeatherInfoResp iWeatherInfoResp =(WeatherInfoResp)etagService.get(key);
			if(iWeatherInfoResp==null){
				iWeatherInfoResp = WeatherReport.getWeatherInfo(city);
				if(iWeatherInfoResp.getInfo()!=null){
					logger.debug("成功处理获取气象信息请求");
					etagService.put(key, CachedCommon.CACHED_MINTER_5H, iWeatherInfoResp);
				}
			}
			
			Map resultMap=new HashMap();
			if(iWeatherInfoResp.getInfo()!=null){
				resultMap.put("time", iWeatherInfoResp.getTime());
				resultMap.put("infos", iWeatherInfoResp.getInfo() );
				resultMap.put("city", city );
				return  Response.ok().entity(JacksonUtils.toJsonRuntimeException(resultMap)).tag(key).build() ;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		
		return  Response.status(Response.Status.NO_CONTENT)
				.build();
	}

	/**
	 * 获取打卡相关的信息所对应的日期
	 */
	@Override
	public Response getPunch(String req) {
		String usrId = context.getHttpHeaders().getHeaderString("usr_id");
		Map reqMap=JacksonUtils.jsonToMapRuntimeException(req);
		String id=(String)reqMap.get("cld_id");
		String month=(String)reqMap.get("month");
		
		if(!StringUtils.hasText(id)||!StringUtils.hasText(month)){
			logger.error("查询参数为空");
			throw new ApplicationException(ErrorConstant.ERROR10002,
					Response.Status.BAD_REQUEST);
		}
		
		Map map=new HashMap();
		map.put("cld_id", id);
		map.put("month", month);
		map.put("usr_id", usrId);
		List<String> list=baseService.getList("Message.getPunchIn", map);
		Map resultMap=new HashMap();
		if(list!=null&&list.size()>0){
			String key=CachedCommon.CACHED_PUNCH+id+"_"+month;
			etagService.put(key, CachedCommon.CACHED_MINTER_1H, key);
			resultMap.put("ETag", key);
		}
		resultMap.put("date_infos", list);
		return Response.ok()
				.entity(JacksonUtils.toJsonRuntimeException(resultMap)).build();
	}

	//获取速度时优先获取gps速度
	@Override
	public Response getVehicle(String sid) {
		if(!StringUtils.hasText(sid)){
			logger.error("查询参数为空");
			throw new ApplicationException(ErrorConstant.ERROR10002,
					Response.Status.BAD_REQUEST);
		}
		List list=baseService.getList("Message.getRealRiding", sid);
		Map resultMap=new HashMap();
		if(list!=null&&list.size()>0){
			resultMap.put("riding_info", list.get(0));
			return Response.ok()
					.entity(JacksonUtils.toJsonRuntimeException(resultMap)).build();
		}
		return  Response.status(Response.Status.NO_CONTENT)
				.build();
	}


	@Override
	public Response getHistory(String req) {
		Map reqMap=JacksonUtils.jsonToMapRuntimeException(req);
		String id=(String)reqMap.get("cld_id");
		String record_date=(String)reqMap.get("record_date");
		if(!StringUtils.hasText(id)||!StringUtils.hasText(record_date)){
			logger.error("查询参数为空");
			throw new ApplicationException(ErrorConstant.ERROR10002,
					Response.Status.BAD_REQUEST);
		}
		String usrId = context.getHttpHeaders().getHeaderString("usr_id");
		Map map=new HashMap();
		map.put("cld_id", id);
		map.put("record_date", record_date);
		map.put("usr_id", usrId);
		List<String> list=baseService.getList("Message.getHistory", map);
		Map resultMap=new HashMap();
		if(list!=null&&list.size()>0){
			resultMap.put("msg_infos", list);
			return Response.ok()
					.entity(JacksonUtils.toJsonRuntimeException(resultMap)).build();
		}
		return  Response.status(Response.Status.NO_CONTENT)
				.build();
	}

}
