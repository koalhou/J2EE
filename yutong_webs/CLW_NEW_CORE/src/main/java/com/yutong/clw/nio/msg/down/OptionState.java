/*******************************************************************************
 * @(#)Vg3.java 2008-10-24
 *
 * Copyright 2008 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.yutong.clw.nio.msg.down;

import java.nio.ByteBuffer;

import com.yutong.clw.nio.msg.util.IQueueObject;

public class OptionState implements IQueueObject{
    private String option_id;

    private String option_state;

	public String getOption_id() {
		return option_id;
	}

	public void setOption_id(String option_id) {
		this.option_id = option_id;
	}

	public String getOption_state() {
		return option_state;
	}

	public void setOption_state(String option_state) {
		this.option_state = option_state;
	}

	public int getBlockSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void reset() {
		// TODO Auto-generated method stub
		
	}

	public ByteBuffer toByteBuffer() {
		// TODO Auto-generated method stub
		return null;
	}

	public void toObject(byte[] objByteArray) {
		// TODO Auto-generated method stub
		
	}
}
