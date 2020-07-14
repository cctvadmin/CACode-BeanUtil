/*
 * Copyright (C), CACode, 2020, all rights reserved.
 *
 * Project name： BeanUtil
 * File name： CACodeField.java
 *  Module declaration:
 * Modify the history:
 * 2020-7-14 - CACode - Create。
 */

package com.cacode.beanutil.libs;

import com.cacode.beanutil.excepotion.QualifierException;

import java.lang.reflect.Field;

/**
 * @author CACode http://www.adminznh.ren
 * @version 1.0
 * @date 2020/4/30 13:21
 */
@SuppressWarnings("ALL")
public interface CACodeField extends Bean {
    /**
     * <p>设置字段值</p>
     * <p>set_field_value</p>
     *
     * @param field 字段 -field
     * @param value 值 -value
     * @throws IllegalAccessException
     * @throws QualifierException
     */
    @Override
    public void setField(Field field, Object value) throws IllegalAccessException, QualifierException;

    /**
     * <p>获取字段值</p>
     * <p>get_field_value</p>
     *
     * @param field 字段 -field
     * @return 字段的值 -field's value
     * @throws IllegalAccessException
     * @throws QualifierException
     */
    @Override
    public Object getField(Field field) throws IllegalAccessException, QualifierException;

    /**
     * <p>查询字段</p>
     * <p>query_field</p>
     *
     * @param fieldName 字段名 -fieldName
     * @return 字段 -field
     * @throws NoSuchFieldException
     */
    @Override
    public Field selectField(String fieldName) throws NoSuchFieldException;
}
