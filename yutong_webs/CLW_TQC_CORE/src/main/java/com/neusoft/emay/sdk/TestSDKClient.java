package com.neusoft.emay.sdk;

import java.util.Iterator;
import java.util.List;

import cn.emay.sdk.client.api.MO;
import cn.emay.sdk.client.api.StatusReport;

public class TestSDKClient {

	public static String softwareSerialNo="9SDK-EMY-0999-JCRLN";//软件序列号,请通过亿美销售人员获取
	public static String key="ytclw39";//序列号首次激活时自己设定
	public static String password="905030";// 密码,请通过亿美销售人员获取 特服号码015272
	
	public static void init() throws Exception{
		
	}
	

	public static void main(String[] args) {
		
		/**
		 * 获取client的方法1
		 * 此示例中用SDKClient.getClient()获取到Client对象,
		 * 您也可以用Client(softwareSerialNo,password)获取一个Client对象
		 * 注:使用SDKClient.getClient()获取到Client对象的序列号及密码在config.properties中配置
		 */
//		Client client=SDKClient.getClient();
		
		/**
		 * 获取client的方法2
		 * 在构造函数中正确的填写softwareSerialNo和password
		 */
//		Client client2=new Client(softwareSerialNo,password);
		
		try {		
			StartMenu();
			while (true) {
				System.out.println("请输入序号进行操作");
				byte[] command = new byte[4];
				System.in.read(command);
				int operate = 0;
				try {
					String commandString = new String(command);
					commandString = commandString.replaceAll("\r\n", "").trim();
					operate = Integer.parseInt(commandString);
				} catch (Exception e) {
					System.out.println("命令输入错误！！！");
				}
				switch (operate) {
				case 1:
					break;
				case 2:
					testRegistDetailInfo();
					break;
				case 3:
					testGetBalance();
					break;
				case 4:
					testChargeUp();
					break;
				case 5:
					testSerialPwdUpd();
					break;
				case 6:
					testSendSMS();
					break;
				case 7:
					testsSendScheduledSMS();
					break;
				case 8:
					testGetMO();
					break;
				case 9:
					testLogout();
					break;
				case 10:
					System.exit(0);
					break;
				default:
					System.out.println("没有该命令 "+operate);
				break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public static void StartMenu() {
		int i = 1;
		System.out.println(i + "、激活序列号,初次使用、已注销后再次使用时调用该方法.");
		i += 1;
		System.out.println(i + "、企业信息注册,目地在于短信发送异常时Emay可以连系到企业.");
		i += 1;
		System.out.println(i + "、余额查询");
		i += 1;
		System.out.println(i + "、充值");
		i += 1;
		System.out.println(i + "、密码修改");
		i += 1;
		System.out.println(i + "、发送即时短信");
		i += 1;
		System.out.println(i + "、发送定时短信");
		i += 1;
		System.out.println(i + "、获取上行短信");
		i += 1;
		System.out.println(i + "、软件注销");
		i += 1;
		System.out.println(i + "、关闭程序");
	}

	/**
	 * 软件注销
	 * 1、软件注销后像发送短信、接受上行短信接口都无法使用
	 * 2、软件可以重新注册、注册完成后软件序列号的金额保持注销前的状态
	 */
	public static void testLogout() {
		try {
			int a = SDKClient.getClient().logout();
			System.out.println("testLogout:" + a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 软件序列号注册、或则说是激活、软件序列号首次使用必须激活
	 * registEx(String serialpass)
	 * 1、serialpass 软件序列号密码、密码长度为6位的数字字符串、软件序列号和密码请通过亿美销售人员获取
	 */
	public static void testRegistEx() {
		int i = SDKClient.getClient().registEx(password);
		System.out.println("testTegistEx:" + i);
	}
	/**
	 * 发送短信、可以发送定时和即时短信
	 * sendSMS(String[] mobiles,String smsContent, String addSerial, int smsPriority)
	 * 1、mobiles 手机数组长度不能超过1000
	 * 2、smsContent 最多500个汉字或1000个纯英文、请客户不要自行拆分短信内容以免造成混乱、亿美短信平台会根据实际通道自动拆分、计费以实际拆分条数为准、亿美推荐短信长度70字以内 
	 * 3、addSerial 附加码(长度小于15的字符串) 用户可通过附加码自定义短信类别,或添加自定义主叫号码( 联系亿美索取主叫号码列表)
	 * 4、优先级范围1~5，数值越高优先级越高(相对于同一序列号)
	 * 5、其它短信发送请参考使用手册自己尝试使用
	 */
	public static void testSendSMS() {
		try {
//			int i=SDKClient.getClient().sendSMS(new String[] {"13998160696"}, "同步内容","110",3);//带扩展码
			int i=SDKClient.getClient().sendSMS(new String[] {"13998160696"}, "同步内容",3);//带扩展码

			System.out.println("testSendSMS====="+i);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 发送定时短信
	 * sendScheduledSMSEx(String[] mobiles, String smsContent,String sendTime,String srcCharset)
	 * 1、mobiles 手机数组长度不能超过1000
	 * 2、smsContent 最多500个汉字或1000个纯英文、请客户不要自行拆分短信内容以免造成混乱、亿美短信平台会根据实际通道自动拆分、计费以实际拆分条数为准、亿美推荐短信长度70字以内
	 * 3、sendTime 定时短信发送时间 定时时间格式为：年年年年月月日日时时分分秒秒，例如20090801123030 表示2009年8月1日12点30分30秒该条短信会发送到用户手机 
	 * 4、srcCharset 字符编码，默认为"GBK"
	 * 5、其它定时短信发送请参考使用手册自己尝试使用
	 */
	public static void testsSendScheduledSMS() {
		try {
			int i=SDKClient.getClient().sendScheduledSMSEx(new String[] {"15010359299"}, "异步内容", "20091104111010","GBK");
			System.out.println("testsSendScheduledSMS====="+i);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 发送带有信息ID的短信（可查状态报告）
	 * sendSMSEx(mobiles, smsContent, addSerial, srcCharset, smsPriority, smsID)
	 * 1、mobiles 手机数组长度不能超过1000
	 * 2、smsContent 最多500个汉字或1000个纯英文、请客户不要自行拆分短信内容以免造成混乱、亿美短信平台会根据实际通道自动拆分、计费以实际拆分条数为准、亿美推荐短信长度70字以内
	 * 3、addSerial 附加码(长度小于15的字符串) 用户可通过附加码自定义短信类别,或添加自定义主叫号码( 联系亿美索取主叫号码列表)
	 * 4、srcCharset 字符编码，默认为"GBK"
	 * 5、优先级范围1~5，数值越高优先级越高(相对于同一序列号)
	 * 6、信息ID，此ID可以与查询查询状态报告的方法中对照发送信息的状态（成功，失败）
	 * 7、其它定时短信发送请参考使用手册自己尝试使用
	 */
	public static void testsSendSMSAddMessageId() {
		try {
			int i=SDKClient.getClient().sendSMSEx(new String[] {"15010359299"}, "异步内容", "123456", "GBK", 5, 1234567);
			System.out.println("testsSendScheduledSMS====="+i);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 软件序列号充值、如果软件序列号金额不足、那么可以调用该方法给序列号充值
	 * chargeUp(String cardNo, String cardPass)
	 * 1、cardNo 充值卡卡号
	 * 2、cardPass 充值卡密码
	 * 3、充值卡卡号和密码请联系亿美销售人员获得
	 */
	public static void testChargeUp() {
		try {
			int a = SDKClient.getClient().chargeUp("充值卡卡号", "密码");
			System.out.println("testChargeUp:" + a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 企业详细信息注册
	 * registDetailInfo(String eName, String linkMan, String phoneNum,String mobile, String email, String fax, String address,String postcode)
	 * 1、eName 企业名称(最多60字节)
	 * 2、linkMan 联系人姓名(最多20字节)
	 * 3、phoneNum 联系电话(最多20字节)
	 * 4、mobile 联系手机(最多15字节)
	 * 5、email 电子邮件(最多60字节)
	 * 6、fax 联系传真(最多20字节)
	 * 7、address 公司地址(最多60字节)
	 * 8、postcode 邮政编码(最多6字节)
	 * 9、以上参数信息都必须填写、每个参数都不能为空
	 */
	public static void testRegistDetailInfo() {
		try {

			int a = SDKClient.getClient().registDetailInfo("企业名称", "联系人", "固话","13000000000", "邮件", "传真", "企业地址", "邮政编码");
			System.out.println("testRegistDetailInfo:" + a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 修改软件序列号密码、注意修改软件序列号密码以后不需要重新注册(激活)
	 * serialPwdUpd(String serialPwd, String serialPwdNew)
	 * 1、serialPwd 旧密码
	 * 2、serialPwdNew 新密码、长度为6位的数字字符串
	 */
	public static void testSerialPwdUpd() {
		try {
			int a = SDKClient.getClient().serialPwdUpd(password, "123456");
			System.out.println("testSerialPwdUpd:" + a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//获取软件序列号的余额
	public static void testGetBalance() {
		try {
			double a = SDKClient.getClient().getBalance();
			System.out.println("testGetBalance:" + a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//获取发送一条短信所需要的费用
	public static void testGetEachFee() {
		try {
			double a = SDKClient.getClient().getEachFee();
			System.out.println("testGetEachFee:" + a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 1、从EUCP平台接收手机用户上行的短信
	 * 2、返回值list中的每个元素为一个Mo对象
	 * 4、Mo具体数据结构参考使用手册
	 */
	public static void testGetMO() {
		try {
			List<MO> a = SDKClient.getClient().getMO();

			if (a != null) {
				System.out.println("testGetMO1size:" + a.size());
				Iterator<MO> it = a.iterator();

				while (it.hasNext()) {
					MO m = it.next();
					// 实例代码为直接输出
					System.out.println("短信内容:" + m.getSmsContent());
					System.out.println("通道号:" + m.getChannelnumber());
					System.out.println("手机号:" + m.getMobileNumber());
					System.out.println("附加码:" + m.getAddSerialRev());
					System.out.println("附加码:" + m.getAddSerial());
					// 上行短信务必要保存,加入业务逻辑代码,如：保存数据库，写文件等等
				}
			} else {
				System.out.println("NO HAVE MO");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 注册转接功能是指当用户直接回复我们发送的短信时、EUCP平台会把用户的回复信息直接转发到我们注册转接的手机号上、
	 * 最多可以注册10个手机号码或上行服务代码
	 * setMOForward(String forwardMobile)
	 * 1、forwardMobile 目标手机号码，有且只能有一个手机号码
	 * 2、其中还有一个每次可以设置多个转接号码，自己可以结合使用手册尝试使用
	 */
	public static void testSetMOForward() {
		try {
			int a = SDKClient.getClient().setMOForward("15800000000");
			System.out.println("testSetMOForward:" + a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//取消转发功能，即用户的上行短信不会再直接转发到我们注册转接功能所指定的手机号码中
	public static void testCancelMOForward() {
		try {
			int a = SDKClient.getClient().cancelMOForward();
			System.out.println("testCancelMOForward:" + a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//获取状态报告信息
	@SuppressWarnings("unchecked")
	public static void testgetReport(){
		try {
			 List<StatusReport> list=SDKClient.getClient().getReport();
			 if(list!=null){
				Iterator it=list.iterator();
				while(it.hasNext()){
					StatusReport report=(StatusReport)it.next();
					System.out.println("手机号码:"+report.getMobile()+"\t状态:"+report.getReportStatus()+"\t信息ID："+report.getSeqID());
				}
			 }else{
				 System.out.println("no have data");
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
