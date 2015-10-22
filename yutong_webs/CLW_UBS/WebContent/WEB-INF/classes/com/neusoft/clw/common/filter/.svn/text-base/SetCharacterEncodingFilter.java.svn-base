/*
 * Copyright 2004 The Apache Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.neusoft.clw.common.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetCharacterEncodingFilter implements Filter {

    protected FilterConfig filterConfig;

    /**
     * 初始化
     */
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    /**
     * 将 inStr 转为 UTF - 8 的编码形式
     * @param inStr 输入字符串
     * @return UTF - 8 的编码形式的字符串
     * @throws UnsupportedEncodingException
     */

    private String toUTF(String inStr) throws UnsupportedEncodingException {
        String outStr = "";
        if (inStr != null) {
            // outStr=java.net.URLDecoder.decode(inStr);// 不用 decode 了 ,
            // 到这的时候就已经自动 decode 过了
            // 将字符串转为 UTF-8 编码形式
            outStr = new String(inStr.getBytes("iso-8859-1"), "UTF-8");
        }
        return outStr;
    }

    /**
     * 中文乱码过滤处理
     */
    public void doFilter(ServletRequest svlrequest,
            ServletResponse svlresponse, FilterChain chain) throws IOException,
            ServletException {
        // 将 Servlet 请求与响应对象转换成 HttpServlet 请求与响应对象
        HttpServletRequest request = (HttpServletRequest) svlrequest;
        HttpServletResponse response = (HttpServletResponse) svlresponse;
        // 获得请求的方式 (1.post or 2.get), 根据不同请求方式进行不同处理
        String method = request.getMethod();

        // 1. 以 post 方式提交的请求 , 直接设置编码为 UTF-8
        if (method.equalsIgnoreCase("post")) {
            try {
                request.setCharacterEncoding("UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else {
            // 2. 以 get 方式提交的请求
            // 取出客户提交的参数集
            Enumeration < String > paramNames = request.getParameterNames();

            // 遍历参数集取出每个参数的名称及值
            while (paramNames.hasMoreElements()) {
                String name = paramNames.nextElement(); // 取出参数名称
                String values[] = request.getParameterValues(name); // 根据参数名称取出其值
                // 如果参数值集不为空
                if (values != null) {
                    // 如果参数值集中只有一个值
                    if (values.length == 1) {
                        try {
                            // 调用 toUTF(values[0]) 函数 ,(values[0] 即第一个参数值 )
                            // 方法转换参数值的字元编码
                            String vlustr = toUTF(values[0]);

                            // 并将该值以属性的形式藏在 request
                            request.setAttribute(name, vlustr);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                    // 如果参数值集中有多个值
                    else {
                        // 遍历参数值集
                        for (int i = 0; i < values.length; i++) {
                            try {
                                // 回圈依次将每个值调用 toUTF(values[i]) 方法转换参数值的字元编码
                                String vlustr = toUTF(values[i]);
                                values[i] = vlustr;
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        }

                        // 将该值以属性的形式藏在 request
                        request.setAttribute(name, values);
                    }
                }
            }
        }

        // 设置响应方式和支持中文的字元集
        response.setContentType("text/html;charset=UTF-8");

        // 继续执行下一个 filter, 无一下个 filter 则执行请求
        chain.doFilter(request, response);
    }

    /**
     * 销毁方法
     */

    public void destroy() {

    }

}
