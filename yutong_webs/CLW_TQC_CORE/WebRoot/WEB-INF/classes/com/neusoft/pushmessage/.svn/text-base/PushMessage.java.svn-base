package com.neusoft.pushmessage;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gexin.rp.sdk.base.IIGtPush;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.neusoft.clw.configuration.Config;

public class PushMessage {
	
//	private static final String APPID = Config.props.getProperty("GeXin_AppID");
//	private static final String APPKEY = Config.props.getProperty("GeXin_AppKey");
//	private static final String MASTERSECRET = Config.props.getProperty("GeXin_MasterSecret");
//	 private static final String API = Config.props.getProperty("GeXin_API");
//	private static final String APPID = "BRP9HgMfDL6DEfsthozcU8";
//	private static final String APPKEY = "UBw9WXovx2Akg8PEF13b54";
//	private static final String MASTERSECRET = "xqtEjzrUy78nPUcq76zB9A";
//	 private static final String API = "http://sdk.open.api.igexin.com/apiex.htm"; 
	private static Logger log = LoggerFactory.getLogger(PushMessage.class);
	
	private static final String NAME = "<PushMessage>";
	/**
	 * 
	 * @param CLIENTID 客户端id
	 * @param text	透传内容
	 */
	public static Map<String,Object> pushPassThroughMessage(String CLIENTID,String text){
		// 推送主类
		IIGtPush push = new IGtPush(Config.props.getProperty("GeXin_API"), Config.props.getProperty("GeXin_AppKey"), Config.props.getProperty("GeXin_MasterSecret"));
        try {               
                //单推消息类型
                SingleMessage message = new SingleMessage();
                //通知模版：支持TransmissionTemplate、LinkTemplate、NotificationTemplate，此处以TransmissionTemplate为例
                TransmissionTemplate template = new TransmissionTemplate();
                template.setAppId(Config.props.getProperty("GeXin_AppID"));
                template.setAppkey(Config.props.getProperty("GeXin_AppKey"));
                template.setTransmissionContent(text);
                
                //收到消息是否立即启动应用，1为立即启动，2则广播等待客户端自启动
                template.setTransmissionType(2);                                       
                message.setData(template);
                message.setOffline(true);                                        //用户当前不在线时，是否离线存储,可选
                message.setOfflineExpireTime(Long.parseLong(Config.props.getProperty("offline.expire.time"))*1000);        //离线有效时间，单位为毫秒，可选
                
                Target target1 = new Target();
                target1.setAppId(Config.props.getProperty("GeXin_AppID"));
                target1.setClientId(CLIENTID);
                //单推
                IPushResult ret = push.pushMessageToSingle(message, target1);         
               log.info(NAME+","+ret.getResponse().toString());
                push.close();
                target1=null;
                template=null;
                message=null;
                return ret.getResponse();
        } catch (Exception e) {
                log.error(NAME+"推送信息时发生异常！");
                e.printStackTrace();
                return null;
        }

	}
//	public static void pushPassThroughMessage(String ip,int port,String CLIENTID,String text){
//		// MMP服务IP、端口、第三方appKey、mastsecret、ReceptionHandler(用来处理第三方的业务逻辑)
//		IIGtPush push = GtPush.getInstance(ip, port, APPKEY, MASTERSECRET,
//				new ReceptionHandler() {
//					// 这里第三方用来处理业务逻辑
//					public void receive(IIGtPush push, Map<String, Object> message) {
//						String version_id = (String) message.get("version_id");
//						String grant_type = (String) message.get("grant_type");
//						String result = (String) message.get("result");
//						String user_id = (String) message.get("user_id");
//						String client_id = (String)message.get("client_id");
//						List<String> sqllist = new ArrayList<String>();
//						if(version_id.equals(Constant.MOBILE_CLIENT_VERSION)){
//							if(grant_type.equals("alarm")){
//								String alarm_id = (String) message.get("alarm_id");
//								sqllist.add(BuildPushSql.getInstance().updatePushAlarmState(user_id, result, client_id, alarm_id));
//							}else if(grant_type.equals("1")){
//								String photo_id = (String) message.get("photo_id");
//								sqllist.add(BuildPushSql.getInstance().updatePushPhotoState(user_id, result, client_id, photo_id));
//							}else if(grant_type.equals("2")){
//								String msg_id = (String) message.get("msg_id");
//								sqllist.add(BuildPushSql.getInstance().updatePushMsgState(user_id,result,client_id,msg_id));
//								if(result.equals("1")){
//									sqllist.add(BuildPushSql.getInstance().updateNum(msg_id));
//								}
//							}
//							DBBuffer.getInstance().add(sqllist);
//						}else{
//							log.error(NAME+"应答中协议版本错误version_id:"+version_id);
//						}
//					}
//				});
//
//		try {
//			// 1.连接。连接成功后才可以推送
//			boolean flag = push.connect();
//			if (!flag) {
//				return;
//			}
//
//			// 2.单推消息类型
//			SingleMessage message = new SingleMessage();
//
//			// 3.推送消息模版.这里共有LinkTemplate(点击通知打开网页)、NotificationTemplate(点击通知启动应用)、 TransmissionTemplate(透传消息模板)三种
//			TransmissionTemplate template = new TransmissionTemplate();
//			template.setAppId(APPID);
//			template.setAppkey(APPKEY);
//			template.setTransmissionContent(text); // 透传内容
//			template.setTransmissionType(1);// 透传消息类型 1收到通知立即启动应用 2收到通知不启动应用
//
//			message.setData(template);
//			message.setOffline(true);
//			message.setOfflineExpireTime(72 * 3600 * 1000); // 离线过期时间
//
//			// 4.推送对象
//			Target target = new Target();
//			target.setAppId(APPID);
//			target.setClientId(CLIENTID);
//
//			// 单推
//			IPushResult ret;
//			try {
//				// 5.推送，返回推送结果(连接成功后,这里可以多次推送,也可以多线程调用推送)
//				ret = push.pushMessageToSingle(message, target);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//			// 6.所有推送任务结束后,关闭连接
//			push.close();
//		} catch (Exception e) {
//			log.error(NAME+"推送过程中发生异常："+e.getMessage());
//			e.printStackTrace();
//		}
//
//	}s
}
