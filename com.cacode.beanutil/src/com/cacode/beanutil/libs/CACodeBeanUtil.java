/*
 * Copyright (C), CACode, 2020, all rights reserved.
 *
 * Project name： BeanUtil
 * File name： CACodeBeanUtil.java
 *  Module declaration:
 * Modify the history:
 * 2020-7-14 - CACode - Create。
 */

package com.cacode.beanutil.libs;

import com.cacode.beanutil.excepotion.NoHaveMethod;
import com.cacode.beanutil.excepotion.QualifierException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * @author CACode http://www.adminznh.ren
 * @version 1.0
 * @date 2020/4/29 7:21
 */

/**
 * <p>你通过这两个接口查看执行逻辑</p>
 * <p>You view the execution logic through these two interfaces</p>
 *
 * @see CACodeMethod
 * @see CACodeField
 * @since 1.0
 */
@SuppressWarnings("all")
public class CACodeBeanUtil implements CACodeMethod, CACodeField {
    /*-------------------------------Fields---------------------------------------*/
    /**
     * <p>你的类</p>
     * <p>you Class</p>
     */
    private Class aClass;
    /**
     * <p>实例化后的对象</p>
     * <p>The instantiated object</p>
     */
    private Object aObj;

    /**
     * <p>设置你的工具类
     * <p>Set your utility class
     *
     * @param aClass 需要制成的工具类
     */
    public CACodeBeanUtil(Class aClass) {
        setaClass(aClass);
        try {
            setaObj(aClass.newInstance());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>设置你的工具类对象
     * <p>Set your utility class object
     *
     * @param aObj 需要制成工具类的对象
     */
    public CACodeBeanUtil(Object aObj) {
        setaObj(aObj);
        setaClass(getaObj().getClass());
    }

    /**
     * <p>使用无参构造方法，你必须调用setaClass(Object)来设置你的类
     * <p>或者通过setaObj(Object)来设置你的类对象
     * <p>但不建议这样做
     * <p> Using the parameterless construction method
     * <p>you must call setaClass (Object) to set your class
     * <p> or set your class object through setaObj (Object)
     * <p> but this is not recommended
     */
    public CACodeBeanUtil() {
    }
    /*-------------------------------Fields END---------------------------------------*/
    /*-------------------------------this.Fields---------------------------------------*/

    /**
     * <p>获取aClass对象</p>
     * <p>Get aClass Object</p>
     *
     * @return aClass对象 -aClass Object
     */
    public Object getaObj() {
        return aObj;
    }

    /**
     * <p>设置aClass对象</p>
     * <p>Set aClass Object</p>
     *
     * @param aObj aClass对象 -aClass Object
     */
    public void setaObj(Object aObj) {
        this.aObj = aObj;
        setaClass(this.getaObj().getClass());
    }

    /**
     * <p>返回你的工具类</p>
     * <p>Return your tool class</p>
     *
     * @return 工具类
     */
    public Class getaClass() {
        return aClass;
    }

    /**
     * <p>设置你的工具类</p>
     * <p>set up your tool class</p>
     *
     * @param aClass 类
     */
    public void setaClass(Class aClass) {
        this.aClass = aClass;
        if (this.getaObj() == null) {
            try {
                setaObj(this.aClass.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        if (this.getaObj().getClass() != aClass) {
            try {
                setaObj(this.aClass.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
    /*-------------------------------this.Fields END---------------------------------------*/
    /*-------------------------------setMethods(...)---------------------------------------*/

    /**
     * <p>调用方法并存入值
     * <p>Call the method and store the value
     *
     * @param method Method对象
     * @param value  传参
     * @throws InvocationTargetException 抛出异常
     * @throws IllegalAccessException    抛出异常
     */
    @Override
    public void setMethods(Method method, Object... value)
            throws InvocationTargetException, IllegalAccessException, QualifierException {
        if (method.canAccess(this.getaObj())) {
            method.invoke(this.getaObj(), value);
        } else {
            throw new QualifierException();
        }
    }

    /**
     * <p>将相同的值赋予给很多方法
     * <p>Assign the same value to many methods</p>
     *
     * @param value  值
     * @param method 方法们
     * @throws InvocationTargetException 抛出异常
     * @throws IllegalAccessException    抛出异常
     */
    @Override
    public void setMethods(Object value, Method... method)
            throws InvocationTargetException, IllegalAccessException, QualifierException {
        for (Method item : method) {
            if (item.canAccess(this.getaObj())) {
                item.invoke(this.getaClass(), value);
            } else {
                throw new QualifierException();
            }
        }
    }

    /**
     * <p>为set方法存值</p>
     * <p>Store value for set method</p>
     *
     * @param map 键值对 Map<方法名,值>
     * @throws NoSuchMethodException 没有该方法
     */
    @Override
    public void setMethods(String methodName, Object value, Class<?>... type)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, QualifierException {
        Method declaredMethod = getaClass().getDeclaredMethod(methodName, type);
        this.getMethod(declaredMethod, value);
    }

    /**
     * 获取所有set方法
     *
     * @param aClass
     * @return
     * @throws NoHaveMethod
     */
    @Override
    public Method[] getSetMethods(Class aClass) throws NoHaveMethod {
        List<Method> methods = new Vector<>(Arrays.asList(aClass.getMethods()));
        Iterator<Method> iterator = methods.iterator();
        while (iterator.hasNext()) {
            Method next = iterator.next();
            if (!next.getName().toLowerCase().contains("set")) {
                iterator.remove();
            }
        }
        if (methods.size() == 0) {
            throw new NoHaveMethod("没有get方法！");
        }
        Method[] methods1 = methods.toArray(new Method[methods.size()]);
        return methods1;
    }

    /**
     * <p>调用方法并返回值
     * <p>Invokes the method and returns the value
     *
     * @param method 方法
     * @param args   参数列表
     * @return 调用方法产生的值
     * @throws InvocationTargetException 抛出异常
     * @throws IllegalAccessException    抛出异常
     */
    @Override
    public Object getMethod(Method method, Object... args)
            throws InvocationTargetException, IllegalAccessException, QualifierException {
        if (method.canAccess(this.getaObj())) {
            return method.invoke(this.getaObj(), args);
        } else {
            throw new QualifierException();
        }
    }

    /**
     * 获取所有get方法
     *
     * @param aClass
     * @return
     * @throws NoHaveMethod
     */
    @Override
    public Method[] getMethods(Class aClass) throws NoHaveMethod {
        List<Method> methods = new Vector<>(Arrays.asList(aClass.getMethods()));
        Iterator<Method> iterator = methods.iterator();
        while (iterator.hasNext()) {
            Method next = iterator.next();
            if (!next.getName().toLowerCase().contains("get")) {
                iterator.remove();
            }
        }
        methods.remove(methods.size() - 1);
        if (methods.size() == 0) {
            throw new NoHaveMethod("没有get方法！");
        }
        Method[] methods1 = methods.toArray(new Method[methods.size()]);
        return methods1;
    }

    /**
     * <p>查询方法返回Method对象</p>
     * <p>配合Object getMethod(Method method, Object... args)使用
     * <p>使用方法：
     * <pre>
     * getMethod(selectMethod("methodName",Object.class));
     * </pre>
     *
     * @param methodName 方法名
     * @param classType  参数列表类型
     * @return Method对象
     * @throws NoSuchMethodException 没有此方法
     */
    @Override
    public Method selectMethod(String methodName, Class<?>... classType) throws NoSuchMethodException {
        return this.getaClass().getDeclaredMethod(methodName, classType);
    }

    /*-------------------------------getMethod(...) END---------------------------------------*/
    /*-------------------------------setField(...)---------------------------------------*/

    /**
     * <p>在指定的obj对象的字段中设置值
     * <p>Sets the value in the field of the specified obj object
     *
     * @param field 字段
     * @param value 值
     * @throws IllegalAccessException
     */
    @Override
    public void setField(Field field, Object value)
            throws IllegalAccessException, QualifierException {
        if (field.canAccess(getaObj())) {
            field.set(this.getaObj(), value);
        } else {
            throw new QualifierException();
        }
    }
    /*-------------------------------setField(...) END---------------------------------------*/
    /*-------------------------------getField(...)---------------------------------------*/

    /**
     * <p>在指定的obj对象的字段获取值
     * <p>Gets a value in the field of the specified obj object
     *
     * @param field 字段
     * @return 获取字段的值
     * @throws IllegalAccessException 抛出异常
     */
    @Override
    public Object getField(Field field)
            throws IllegalAccessException, QualifierException {
        if (field.canAccess(getaObj())) {
            return field.get(this.getaObj());
        } else {
            throw new QualifierException();
        }
    }

    @Override
    public Field[] getFields() throws IllegalAccessException, QualifierException {
        return this.getaClass().getFields();
    }

    /**
     * <p>查询字段</p>
     * p>Query field</p>
     *
     * @param fieldName 字段名 -fieldName
     * @return
     * @throws NoSuchFieldException
     */
    @Override
    public Field selectField(String fieldName) throws NoSuchFieldException {
        return this.getaClass().getDeclaredField(fieldName);
    }
    /*-------------------------------getField(...) END---------------------------------------*/
}
