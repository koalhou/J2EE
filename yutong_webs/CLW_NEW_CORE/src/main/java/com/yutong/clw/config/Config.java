/**
 * 
 */
package com.yutong.clw.config;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * @author pudong
 */
public class Config extends PropertyPlaceholderConfigurer {

    public static Properties props;

    @Override
    protected void loadProperties(Properties property) throws IOException {
        super.loadProperties(property);
        props = property;
    }
}
