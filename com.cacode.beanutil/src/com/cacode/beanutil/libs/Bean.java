/*
 * Copyright (C), CACode, 2020, all rights reserved.
 *
 * Project name： BeanUtil
 * File name： Bean.java
 *  Module declaration:
 * Modify the history:
 * 2020-7-14 - CACode - Create。
 */

/*###*###############################################################*###*
 *###*                                                               *###*
 *###*                                                               *###*
 *###*                            _ooOoo_                            *###*
 *###*                           o8888888o                           *###*
 *###*                           88" . "88                           *###*
 *###*                           (| -_- |)                           *###*
 *###*                            O\ = /O                            *###*
 *###*                        ____/`---'\____                        *###*
 *###*                      .   ' \\| |// `.                         *###*
 *###*                       / \\||| : |||// \                       *###*
 *###*                     / _||||| -:- |||||- \                     *###*
 *###*                       | | \\\ - /// | |                       *###*
 *###*                     | \_| ''\---/'' | |                       *###*
 *###*                      \ .-\__ `-` ___/-. /                     *###*
 *###*                   ___`. .' /--.--\ `. . __                    *###*
 *###*                ."" '< `.___\_<|>_/___.' >'"".                 *###*
 *###*               | | : `- \`.;`\ _ /`;.`/ - ` : | |              *###*
 *###*                 \ \ `-. \_ __\ /__ _/ .-` / /                 *###*
 *###*         ======`-.____`-.___\_____/___.-`____.-'======         *###*
 *###*                            `=---='                            *###*
 *###*                                                               *###*
 *###*         .............................................         *###*
 *###*                  佛祖保佑             永无BUG                  *###*
 *###*                                                               *###*
 *###*   .........................................................   *###*
 *###*                                                               *###*
 *###*                                                               *###*
 *###*###############################################################*###**/

package com.cacode.beanutil.libs;

import com.cacode.beanutil.excepotion.QualifierException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author CACode http://www.adminznh.ren
 * @version 1.0
 * @date 2020/4/30 14:17
 */

/**
 * 顶层接口
 */
@SuppressWarnings("ALL")
public interface Bean {
    public String CACODE = "com.cacode.summer";

    public void setMethods(Method method, Object... value)
            throws InvocationTargetException, IllegalAccessException, QualifierException;

    public void setMethods(Object value, Method... method)
            throws InvocationTargetException, IllegalAccessException, QualifierException;

    public void setMethods(String methodName, Object value, Class<?>... type)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, QualifierException;

    public Object getMethod(Method method, Object... args)
            throws InvocationTargetException, IllegalAccessException, QualifierException;

    public void setField(Field field, Object value) throws IllegalAccessException, QualifierException;

    public Object getField(Field field) throws IllegalAccessException, QualifierException;

    public Field[] getFields() throws IllegalAccessException, QualifierException;

    public Method selectMethod(String methodName, Class<?>... classType) throws NoSuchMethodException;

    public Field selectField(String fieldName) throws NoSuchFieldException;

}
