/**
 * @(#)ClientLoggingFeature.java 2013-4-9
 *
 * Copyright 2013 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.yutong.axxc.parents.feature;

import org.apache.cxf.Bus;
import org.apache.cxf.feature.AbstractFeature;
import org.apache.cxf.interceptor.InterceptorProvider;

import com.yutong.axxc.parents.interceptor.ClientLoggingInInterceptor;
import com.yutong.axxc.parents.interceptor.ClientLoggingOutInterceptor;

public class ClientLoggingFeature extends AbstractFeature {
    private ClientLoggingOutInterceptor outInterceptor;
    private ClientLoggingInInterceptor inInterceptor;

    @Override
    protected void initializeProvider(InterceptorProvider provider, Bus bus) {
    	outInterceptor = new ClientLoggingOutInterceptor();
    	inInterceptor = new ClientLoggingInInterceptor();
    	
        provider.getOutInterceptors().add(outInterceptor);
        provider.getOutFaultInterceptors().add(outInterceptor);
        provider.getInInterceptors().add(inInterceptor);
        provider.getInFaultInterceptors().add(inInterceptor);
    }

}
