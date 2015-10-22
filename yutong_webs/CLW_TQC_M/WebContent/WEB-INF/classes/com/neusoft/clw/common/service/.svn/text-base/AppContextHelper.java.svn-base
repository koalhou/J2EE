package com.neusoft.clw.common.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class AppContextHelper implements ApplicationContextAware {

    private static ApplicationContext _appContext;

    /**
     * getBean(String beanName)
     */
    public static Object getBean(String beanName) {
        return _appContext.getBean(beanName);
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        _appContext = applicationContext;
    }
}
