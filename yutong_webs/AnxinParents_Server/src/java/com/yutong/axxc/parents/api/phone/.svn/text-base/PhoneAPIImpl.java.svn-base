package com.yutong.axxc.parents.api.phone;

import java.util.Map;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import cn.emay.sdk.client.api.Client;

import com.yutong.axxc.parents.common.ErrorConstant;
import com.yutong.axxc.parents.common.HttpConstant;
import com.yutong.axxc.parents.common.ModCommonConstant;
import com.yutong.axxc.parents.exception.common.ApplicationException;
import com.yutong.axxc.parents.service.BaseService;
import com.yutong.axxc.parents.tools.JacksonUtils;
import com.yutong.axxc.parents.tools.PropertiesTools;
import com.yutong.axxc.parents.tools.ValidateUtil;

public class PhoneAPIImpl implements PhoneAPI {
	private static Logger logger = LoggerFactory
			.getLogger(PhoneAPIImpl.class);
	
	private String softwareSerialNo;
	private String password;

	private final String EMAY_SERIALNO="emay.softwareSerialNo";
	private final String EMAY_KEY="emay.key";
	
	@Autowired
	private BaseService baseService;
	@Override
	public Response send(String req) {
		Map map=JacksonUtils.jsonToMapRuntimeException(req);
		String phone=(String)map.get("phone");
		String type=(String)map.get("verify_type");
		
		logger.debug("请求:{},{}", phone,type);
		if(!StringUtils.hasText(phone)||!StringUtils.hasText(type)||!ValidateUtil.isPhone(phone)){
			logger.error("参数为空");
			throw new ApplicationException(ErrorConstant.ERROR10002,
					Response.Status.BAD_REQUEST);
		}
		
		
		String content="";
		String random=genRandomNum();
			if("1".equals(type)){
				//找回密码
				content="亲爱的安芯平台家长用户，您的验证码是"+random+"，请您登录后马上修改密码，祝您使用安芯家长愉快！【安芯校车平台】";
			}else if("0".equals(type)){
				//注册
				content="亲爱的家长用户，您正在注册安芯家长，验证码是："+random+"，24小时内有效请您及时注册，祝您使用安芯家长愉快！【安芯校车平台】";
			}else if("2".equals(type)){
				//关联认证
				content="亲爱的安芯平台家长用户，您正在关联认证，验证码是："+random+"，24小时内有效请您及时注册，祝您使用安芯家长愉快！【安芯校车平台】";
			}else if("3".equals(type)){
				//更改手机号成功后提示
				content="亲爱的安芯平台家长用户，您的注册手机号已改为："+random+"，如非您本人操作，请您及时联系校车公司，祝您使用安芯家长愉快！【安芯校车平台】";
			}else if("4".equals(type)){
				//更改手机号发送验证
				content="亲爱的家长用户，您正在修改您的注册手机号，验证码是："+random+"，24小时内有效，如非您本人操作，请您及时联系校车公司，祝您使用安芯家长愉快！【安芯校车平台】";
			}
		
		
		Client smsClient;
		try {
			logger.debug("发送请求{}-{}", phone,content);
			softwareSerialNo = PropertiesTools.readValue(
					ModCommonConstant.SYS_CONF_FILE, EMAY_SERIALNO);
			password = PropertiesTools.readValue(
					ModCommonConstant.SYS_CONF_FILE, EMAY_KEY);
//			smsClient = new Client(softwareSerialNo,password);
//			int num = smsClient.sendSMS(
//					new String[] { phone },	content, 5);
//			logger.info("短信服务器返回：{}",num);
			map.clear();
			map.put("verify_code", random);
			return Response
					.ok()
					.entity(JacksonUtils.toJsonRuntimeException(map)).build();
		} catch (Exception e) {
			logger.error("发送短信异常",e);
			throw new ApplicationException(ErrorConstant.ERROR90000,
					Response.Status.BAD_REQUEST);
		}

	}

	private String genRandomNum(){
		String s = "";
		 while(s.length()<6)
		  s+=(int)(Math.random()*10);
		 return s;
	}
}
