/*
 * Copyright (C), CACode, 2020, all rights reserved.
 *
 * Project name： BeanUtil
 * File name： Methods.java
 *  Module declaration:
 * Modify the history:
 * 2020-7-14 - CACode - Create。
 */

package com.cacode.beanutil.libs;

/**
 * @author CACode http://www.adminznh.ren
 * @version 1.0
 * @date 2020/5/1 3:56
 */

import java.lang.reflect.Method;

/**
 * 获取带参方法
 */
public class Methods {
    /**
     * <p>查询方法</p>
     * <p>Inquiry method</p>
     *
     * @param obj        类 -class
     * @param methodName 方法名 -method name
     * @param classType  方法参数类型 -Method parameter type
     * @return 方法 -method
     * @throws NoSuchMethodException 没有此方法
     */
    public Method selectMethod(Object obj, String methodName, Class<?>... classType) throws NoSuchMethodException {
        return obj.getClass().getDeclaredMethod(methodName, classType);
    }
}
