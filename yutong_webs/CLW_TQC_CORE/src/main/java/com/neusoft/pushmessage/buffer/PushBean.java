package com.neusoft.pushmessage.buffer;

import com.neusoft.mobile.bean.AlarmBean;
import com.neusoft.mobile.bean.MessageBean;
import com.neusoft.mobile.bean.PhotoBean;

public class PushBean {
	private String type;
	private AlarmBean alarmBean;
	private PhotoBean photoBean;
	private MessageBean messageBean;
	
	public AlarmBean getAlarmBean() {
		return alarmBean;
	}
	public void setAlarmBean(AlarmBean alarmBean) {
		this.alarmBean = alarmBean;
	}
	public PhotoBean getPhotoBean() {
		return photoBean;
	}
	public void setPhotoBean(PhotoBean photoBean) {
		this.photoBean = photoBean;
	}
	public MessageBean getMessageBean() {
		return messageBean;
	}
	public void setMessageBean(MessageBean messageBean) {
		this.messageBean = messageBean;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
		
}
