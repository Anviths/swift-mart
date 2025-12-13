package com.ecom.config;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class BeanCheck {

    public BeanCheck(ApplicationContext context) {
        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
    }
}
