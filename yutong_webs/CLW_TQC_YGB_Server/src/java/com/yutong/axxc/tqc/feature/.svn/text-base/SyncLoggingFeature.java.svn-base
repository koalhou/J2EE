/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.yutong.axxc.tqc.feature;

import org.apache.cxf.Bus;
import org.apache.cxf.annotations.Logging;
import org.apache.cxf.common.injection.NoJSR250Annotations;
import org.apache.cxf.feature.AbstractFeature;
import org.apache.cxf.interceptor.InterceptorProvider;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;

import com.yutong.axxc.tqc.interceptor.DiyLoggingInInterceptor;
import com.yutong.axxc.tqc.interceptor.DiyLoggingOutInterceptor;

/**
 * This class is used to control message-on-the-wire logging. By attaching this
 * feature to an endpoint, you can specify logging. If this feature is present,
 * an endpoint will log input and output of ordinary and log messages.
 * 
 * <pre>
 * <![CDATA[
 *     <jaxws:endpoint ...>
 *       <jaxws:features>
 *        <bean class="org.apache.cxf.feature.DiyLoggingFeature"/>
 *       </jaxws:features>
 *     </jaxws:endpoint>
 *   ]]>
 * </pre>
 */
@NoJSR250Annotations
public class SyncLoggingFeature extends AbstractFeature {
	private static final int DEFAULT_LIMIT = 64 * 1024;
	private static final DiyLoggingInInterceptor IN = new DiyLoggingInInterceptor(
			DEFAULT_LIMIT);
	private static final DiyLoggingOutInterceptor OUT = new DiyLoggingOutInterceptor(
			DEFAULT_LIMIT);

	String inLocation;
	String outLocation;
	boolean prettyLogging;

	int limit = DEFAULT_LIMIT;

	public SyncLoggingFeature() {

	}

	public SyncLoggingFeature(final int lim) {
		limit = lim;
	}

	public SyncLoggingFeature(final String in, final String out) {
		inLocation = in;
		outLocation = out;
	}

	public SyncLoggingFeature(final String in, final String out, final int lim) {
		inLocation = in;
		outLocation = out;
		limit = lim;
	}

	public SyncLoggingFeature(final String in, final String out, final int lim,
			final boolean p) {
		inLocation = in;
		outLocation = out;
		limit = lim;
		prettyLogging = p;
	}

	public SyncLoggingFeature(final Logging annotation) {
		inLocation = annotation.inLocation();
		outLocation = annotation.outLocation();
		limit = annotation.limit();
		prettyLogging = annotation.pretty();
	}

	@Override
	protected void initializeProvider(final InterceptorProvider provider,
			final Bus bus) {

		if (limit == DEFAULT_LIMIT && inLocation == null && outLocation == null
				&& !prettyLogging) {
			provider.getInInterceptors().add(IN);
			provider.getInFaultInterceptors().add(IN);
			provider.getOutInterceptors().add(OUT);
			provider.getOutFaultInterceptors().add(OUT);
		} else {
			final LoggingInInterceptor in = new LoggingInInterceptor(limit);
			in.setOutputLocation(inLocation);
			in.setPrettyLogging(prettyLogging);
			final LoggingOutInterceptor out = new LoggingOutInterceptor(limit);
			out.setOutputLocation(outLocation);
			out.setPrettyLogging(prettyLogging);

			provider.getInInterceptors().add(in);
			provider.getInFaultInterceptors().add(in);
			provider.getOutInterceptors().add(out);
			provider.getOutFaultInterceptors().add(out);
		}
	}

	/**
	 * This function has no effect at this time.
	 * 
	 * @param lim
	 */
	public void setLimit(final int lim) {
		limit = lim;
	}

	/**
	 * Retrieve the value set with {@link #setLimit(int)}.
	 * 
	 * @return
	 */
	public int getLimit() {
		return limit;
	}

	/**
	 * @return
	 */
	public boolean isPrettyLogging() {
		return prettyLogging;
	}

	/**
	 * Turn pretty logging of XML content on/off
	 * 
	 * @param prettyLogging
	 */
	public void setPrettyLogging(final boolean prettyLogging) {
		this.prettyLogging = prettyLogging;
	}
}
