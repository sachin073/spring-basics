package com.featurelogs.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Constructor;

@Component
public class CustomWiredBeanPostProcessor implements BeanPostProcessor {
    @Autowired
    private ConfigurableListableBeanFactory configurableBeanFactory;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(RestController.class)) {
            ReflectionUtils.doWithFields(bean.getClass(), new CustomWiredFieldCallBack(configurableBeanFactory, bean));
        }
        return bean;
    }
}
