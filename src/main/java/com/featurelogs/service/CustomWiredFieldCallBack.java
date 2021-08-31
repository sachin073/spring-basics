package com.featurelogs.service;

import com.featurelogs.annotations.CustomWired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;


public class CustomWiredFieldCallBack implements ReflectionUtils.FieldCallback {

    ConfigurableListableBeanFactory configurableBeanFactory;
    Object bean;

    public CustomWiredFieldCallBack(ConfigurableListableBeanFactory configurableBeanFactory, Object bean) {
        this.configurableBeanFactory = configurableBeanFactory;
        this.bean = bean;
    }

    @Override
    public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
        if (!field.isAnnotationPresent(CustomWired.class)) {
            return;
        }
        System.out.println("bean found ");
        Class dummyService = field.getType();
        ReflectionUtils.makeAccessible(field);
        Object dummyServiceInstance = getBeanInstance(dummyService.getSimpleName(), dummyService);
        System.out.println("bean instance" + dummyServiceInstance);

        field.set(bean, dummyServiceInstance);
        System.out.println("bean injected ");

    }

    public Object getBeanInstance(
            String beanName, Class dummyService) {
        Object dummyServiceInstance = null;
        if (!configurableBeanFactory.containsBean(beanName)) {
            System.out.println(beanName + "<><><><><><>" + dummyService);
            Object newInstance = null;
            try {
                Constructor<?> ctr = dummyService.getConstructor();
                newInstance = ctr.newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            dummyServiceInstance = configurableBeanFactory.initializeBean(newInstance, beanName);
            configurableBeanFactory.autowireBeanProperties(dummyServiceInstance, AutowireCapableBeanFactory.AUTOWIRE_BY_NAME, true);
            configurableBeanFactory.registerSingleton(beanName, dummyServiceInstance);
        } else {
            dummyServiceInstance = configurableBeanFactory.getBean(beanName);
        }
        return dummyServiceInstance;
    }
}
