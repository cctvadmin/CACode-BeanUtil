/*
 * Copyright (C), CACode, 2020, all rights reserved.
 *
 * Project name： BeanUtil
 * File name： CACodeMethod.java
 *  Module declaration:
 * Modify the history:
 * 2020-7-14 - CACode - Create。
 */

package com.cacode.beanutil.libs;

import com.cacode.beanutil.excepotion.NoHaveMethod;
import com.cacode.beanutil.excepotion.QualifierException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author CACode http://www.adminznh.ren
 * @version 1.0
 * @date 2020/4/30 13:21
 */
@SuppressWarnings("ALL")
public interface CACodeMethod extends Bean {
    /**
     * <p>一个方法，多个值</p>
     * <p>one_method_multiple_values</p>
     *
     * @param method 方法
     * @param value  值
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Override
    public void setMethods(Method method, Object... value) throws InvocationTargetException, IllegalAccessException, QualifierException;

    /**
     * <p>一个值，多个方法</p>
     * <p>one_value_multiple_methods</p>
     *
     * @param value  值 -value
     * @param method 方法 -method
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Override
    public void setMethods(Object value, Method... method)
            throws InvocationTargetException, IllegalAccessException, QualifierException;

    /**
     * <p>键值对设置多个方法的值</p>
     * <p>key_value_pairs_set_values_​​for_multiple_methods</p>
     *
     * @param map
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Override
    public void setMethods(String methodName, Object value, Class<?>... type)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, QualifierException;

    /**
     * 获取所有set方法
     *
     * @param aClass
     * @return
     * @throws NoHaveMethod
     */
    public Method[] getSetMethods(Class aClass) throws NoHaveMethod;

    /**
     * <p>获取方法返回的值</p>
     * p>get_the_value_returned_by_the_method</p>
     *
     * @param method 方法
     * @param args   值
     * @return 返回的值
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws com.cacode.summer.beanUtil.QualifierException
     */
    @Override
    public Object getMethod(Method method, Object... args)
            throws InvocationTargetException, IllegalAccessException, QualifierException;

    /**
     * 获取所有get方法
     *
     * @param aClass
     * @return
     * @throws NoHaveMethod
     */
    public Method[] getMethods(Class aClass) throws NoHaveMethod;

    /**
     * <p>用于查询方法</p>
     * <p>used_for_query_method</p>
     *
     * @param obj        类对象
     * @param methodName 方法名
     * @param classType  参数列表类型
     * @return 方法
     * @throws NoSuchMethodException 没有找到方法
     */
    @Override
    public Method selectMethod(String methodName, Class<?>... classType) throws NoSuchMethodException;
}
