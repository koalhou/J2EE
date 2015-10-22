/**
 * 
 */
package com.neusoft.smsplatform.configuration;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 
 * @author chenqiong
 *
 */
public class MessageConfig extends PropertyPlaceholderConfigurer {

    public static Properties props;

    @Override
    protected void loadProperties(Properties property) throws IOException {
        super.loadProperties(property);
        props = property;
    }
    
    public static String getProperties(String name){
 	   return props.getProperty(name);
    }
}
