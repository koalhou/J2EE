package com.yutong.clw.nio.mina.coding;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;


public class ClwCodeFactory implements ProtocolCodecFactory {
	
	private static ClwCodeFactory instance;
	
	public static ClwCodeFactory getInstance(){
		if(instance == null){
			instance = new ClwCodeFactory();
		}
		return instance;
	}
	
	public ProtocolDecoder getDecoder(IoSession arg0) throws Exception {
		return new ClwDecoder();
	}

	public ProtocolEncoder getEncoder(IoSession arg0) throws Exception {
		return new ClwEncoder();
	}

}
