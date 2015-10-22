package com.neusoft.parents.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.constant.Constant;
import com.neusoft.clw.spring.SpringBootStrap;
import com.neusoft.clw.vncs.buffer.DBBuffer;
import com.neusoft.clw.vncs.inside.msg.utils.DateUtil;
import com.neusoft.parents.algorithm.manager.AlgorithmManager;
import com.neusoft.parents.algorithm.manager.AlgorithmUtil;
import com.neusoft.parents.bean.CacheVinTripList;
import com.neusoft.parents.bean.StuSiteNote;
import com.neusoft.parents.bean.VehicleReal;
import com.neusoft.parents.cachemanager.VinTripCacheManager;
import com.neusoft.parents.dao.IParentsDAO;
import com.neusoft.parents.dao.impl.ParentsDAO;
import com.neusoft.parents.pushBuffer.BasicObject;
import com.neusoft.parents.pushBuffer.PushBuffer;
import com.neusoft.parents.pushmessage.domain.BaseNotification;
import com.neusoft.parents.pushmessage.domain.PushMessageCls;
import com.neusoft.parents.pushmessage.manager.PushMsgBuffer;
import com.neusoft.parents.sitenotemanager.SiteNoteManager;
import com.neusoft.parents.utils.CacheUtils;
import com.neusoft.parents.utils.JacksonUtils;


public class SendMsgCommand {
	private Logger log = LoggerFactory.getLogger(SiteNoteManager.class);
	private static final int MaxTimespanInMinute = 5;
	
	/**
	 * 将需要推送的消息进行推送           从订购信息筛选出应该要发送的推送提醒，进行发送
	 * @param dd
	 * @param vr
	 */
	public void shouldSendMsg(CacheVinTripList dd,VehicleReal vr)
	{	
		AlgorithmManager algorithmManager = (AlgorithmManager) SpringBootStrap.getInstance().getBean("algorithmManager");
        //for(StuSiteNote stuSiteNote:dd.getStuSiteNoteList())
        for(int i = 0;i < dd.getStuSiteNoteList().size();i++)
		{
        	StuSiteNote stuSiteNote = dd.getStuSiteNoteList().get(i);
			int timespanInMinute = AlgorithmUtil.getTimespanMinutes(new Date(), vr.getTerminal_time());
            if (timespanInMinute < MaxTimespanInMinute)
            {
            	String realLong = vr.getLongitude();
                String realLati = vr.getLatitude();
                //Date terminalDate = vr.getTerminal_time();
                String targetLong = stuSiteNote.getLongitude();
                String targetLati = stuSiteNote.getLatitude();
                //String route_id = stuSiteNote.getRoute_id();
                //Students stu = CacheUtils.getStuStateCache(stuSiteNote.getEmp_code());
//                String direction = "0";
//                if (vr.getDirection() != null)
//                {
//                    direction = vr.getDirection();
//                }
                // 按照时间进行提醒
                if (stuSiteNote.getRemind_type().equals(Constant.REMINDTYPE_TIME))
                {
                	try {
	                	if (realLong != null && realLati != null && targetLong != null && targetLati != null)
	                    {
	//                        int time = algorithmManager.getTimespanInMinute(vr,vr.getVehicle_vin(), route_id,terminalDate, Double.parseDouble(realLong),
	//                                Double.parseDouble(realLati), Double.parseDouble(direction), Double.parseDouble(targetLong),
	//                                Double.parseDouble(targetLati));
	                		
	                       int time = algorithmManager.getTimespanInSec(stuSiteNote.getRoute_id(),stuSiteNote.getSite_id(), Double.parseDouble(realLong),Double.parseDouble(realLati),Integer.parseInt(stuSiteNote.getAhead_note_time())*60,200);
	                       if(time > 0){

                               //异步     将消息的数据体整理成需要发送的消息形式，进行消息推送
	                       		BasicObject boSendCommand=new BasicObject();
	       						boSendCommand.setObjectName("com.neusoft.parents.service.PushMsgCommand");										
	       						boSendCommand.setFunctionName("pushMsg");
	       						List<Object> paramsSendCommand=new ArrayList<Object>();
	       						paramsSendCommand.add(stuSiteNote);
	       						paramsSendCommand.add(vr);
	       						//paramsSendCommand.add(parentsDAO);
	       						boSendCommand.setParamsList(paramsSendCommand);
	       						//将BasicObject加入事件处理缓存
	       						PushBuffer.getInstance().add(boSendCommand);
	                       		
	                       		//pushMsg(stuSiteNote,vr);
	                               
	                               //将推送消息放到数据库中，下次站点缓存的时根绝该数据清理不提醒的缓存
	                               //DBBuffer.getInstance().add(ParentsBuildSQL.getInstance().buildInsertSiteNoteLog(vr, stuSiteNote));
	
	                               // /移除已经推送的站点
	                               dd.getStuSiteNoteList().remove(i); 
	                               i=i-1;
                          
                       
	                       }
//	                        double disTemp = AlgorithmUtil.getDistance(Double.parseDouble(realLong), Double.parseDouble(realLati), Double.parseDouble(targetLong), Double.parseDouble(targetLati));
//	                        if(null == vr.getSpeed() ||"".equals(vr.getSpeed()))
//	                        {
//	                        	vr.setSpeed("0.0");
//	                        }
//	            			int time = (int) (Math.abs(disTemp/1000/Double.parseDouble(vr.getSpeed())*3600));
//	            			//add by liuja   增加30秒钟,用来增加提前量
//	                        int aheadNoteTime = Integer.parseInt(stuSiteNote.getAhead_note_time())*60+30;
//	                        if (time > 0)//if (time != -1)
//	                        {
//	                        	if (time <= aheadNoteTime)         //筛选出应该要发送的推送提醒，进行发送    时间小于员工设置的值时进行推送
//	                            {
//		                                //异步     将消息的数据体整理成需要发送的消息形式，进行消息推送
//		                        		BasicObject boSendCommand=new BasicObject();
//		        						boSendCommand.setObjectName("com.neusoft.parents.service.PushMsgCommand");										
//		        						boSendCommand.setFunctionName("pushMsg");
//		        						List<Object> paramsSendCommand=new ArrayList<Object>();
//		        						paramsSendCommand.add(stuSiteNote);
//		        						paramsSendCommand.add(vr);
//		        						//paramsSendCommand.add(parentsDAO);
//		        						boSendCommand.setParamsList(paramsSendCommand);
//		        						//将BasicObject加入事件处理缓存
//		        						PushBuffer.getInstance().add(boSendCommand);
//		                        		
//		                        		//pushMsg(stuSiteNote,vr);
//		                                
//		                                //将推送消息放到数据库中，下次站点缓存的时根绝该数据清理不提醒的缓存
//		                                //DBBuffer.getInstance().add(ParentsBuildSQL.getInstance().buildInsertSiteNoteLog(vr, stuSiteNote));
//	
//		                                // /移除已经推送的站点
//		                                dd.getStuSiteNoteList().remove(i); 
//		                                i=i-1;
//	                            	//}
//	                               
//	                            }
//	                        }
	                    }
                	} catch(Exception e) {
                		e.printStackTrace();
                	}
                }
                // 按照距离进行提醒
                else if (stuSiteNote.getRemind_type().equals(Constant.REMINDTYPE_MILEAGE))
                {
                	try {
                    if (realLong != null && realLati != null && targetLong != null && targetLati != null)
                    {
//                    	int mileage123 = algorithmManager.getMileage(vr.getVehicle_vin(),stuSiteNote.getRoute_id(),vr.getTerminal_time(), Double.parseDouble(realLong),
//                                Double.parseDouble(realLati), Double.parseDouble(direction), Double.parseDouble(targetLong),
//                                Double.parseDouble(targetLati));
                    	
//                    	int mileage123 = algorithmManager.getMileage(vr.getVehicle_vin(),stuSiteNote.getRoute_id(),stuSiteNote.getSite_id(),vr.getTerminal_time(), Double.parseDouble(realLong),
//                                Double.parseDouble(realLati), 0.0, Double.parseDouble(targetLong),
//                                Double.parseDouble(targetLati));
                    	
                    	//判断是否进行推送
                    	
                    	int mileage = algorithmManager.getMileage(stuSiteNote.getRoute_id(),stuSiteNote.getSite_id(), Double.parseDouble(realLong),Double.parseDouble(realLati),Integer.parseInt(stuSiteNote.getAhead_note_mileage()),200);
                    	if(mileage > 0){
                    		//异步     将消息的数据体整理成需要发送的消息形式，进行消息推送
							BasicObject boSendCommand=new BasicObject();
							boSendCommand.setObjectName("com.neusoft.parents.service.PushMsgCommand");										
							boSendCommand.setFunctionName("pushMsg");
							List<Object> paramsSendCommand=new ArrayList<Object>();
							paramsSendCommand.add(stuSiteNote);
							paramsSendCommand.add(vr);
							//paramsSendCommand.add(parentsDAO);
							boSendCommand.setParamsList(paramsSendCommand);
							//将BasicObject加入事件处理缓存
							PushBuffer.getInstance().add(boSendCommand);
							//pushMsg(stuSiteNote,vr);
							//DBBuffer.getInstance().add(ParentsBuildSQL.getInstance().buildInsertSiteNoteLog(vr, stuSiteNote));

							// 移除已经推送的站点
							dd.getStuSiteNoteList().remove(i); 
							i=i-1;
                    	}
                    	
//                    	double disTemp = AlgorithmUtil.getDistance(Double.parseDouble(realLong), Double.parseDouble(realLati), Double.parseDouble(targetLong), Double.parseDouble(targetLati));
//            			int mileage = (int) (Math.abs(disTemp));
//            			//add by liuja   增加500米,用来增加提前量
//                    	 int aheadNoteMileage = Integer.parseInt(stuSiteNote.getAhead_note_mileage())+500;
//                    	 if (mileage > 0)  //if (mileage != -1)
//                    	 {
//                    		 if (mileage <= aheadNoteMileage)   //筛选出应该要发送的推送提醒，进行发送    距离小于员工设置的值时进行推送
//                    		 {
//	
//                    			//异步     将消息的数据体整理成需要发送的消息形式，进行消息推送
//								BasicObject boSendCommand=new BasicObject();
//								boSendCommand.setObjectName("com.neusoft.parents.service.PushMsgCommand");										
//								boSendCommand.setFunctionName("pushMsg");
//								List<Object> paramsSendCommand=new ArrayList<Object>();
//								paramsSendCommand.add(stuSiteNote);
//								paramsSendCommand.add(vr);
//								//paramsSendCommand.add(parentsDAO);
//								boSendCommand.setParamsList(paramsSendCommand);
//								//将BasicObject加入事件处理缓存
//								PushBuffer.getInstance().add(boSendCommand);
//								//pushMsg(stuSiteNote,vr);
//								//DBBuffer.getInstance().add(ParentsBuildSQL.getInstance().buildInsertSiteNoteLog(vr, stuSiteNote));
//
//								// 移除已经推送的站点
//								dd.getStuSiteNoteList().remove(i); 
//								i=i-1;
//                             	
//                    		 }
//                    	 }
                    }
                	} catch(Exception e) {
                		e.printStackTrace();
                	}
                }
                // 按照站点进行提醒
				else if (stuSiteNote.getRemind_type().equals(Constant.REMINDTYPE_SITE))
				{
				    //log.info("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
				    // 厂外提醒没有按照站点进行提醒的功能，只有厂内提醒的有
				}
            }
		}
        //更新缓存，将已经发送过的推送消息消除   dd
        String key = Constant.STUSITENOTE + dd.getVehicle_vin()+ dd.getTrip_id();
        VinTripCacheManager.getInstance().SyncVehicleRealInfoValue(Constant.OFF, key, dd);
        //log.info("更新缓存成功");

	}	


}
