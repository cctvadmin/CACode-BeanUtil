/*
 * Copyright (C), CACode, 2020, all rights reserved.
 *
 * Project name： com.cacode.summer
 * File name： BeanUtil.java
 *  Module declaration:
 * Modify the history:
 * 2020-6-11 - CACode - Create。
 */

package com.cacode.beanutil.maintool;


import com.cacode.beanutil.excepotion.NoHaveMethod;
import com.cacode.beanutil.excepotion.QualifierException;
import com.cacode.beanutil.libs.CACodeBeanUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author CACode http://www.adminznh.ren
 * @version Summer-BeanUtil V1.0
 * @date 2020/4/30 0:56
 */
@SuppressWarnings("ALL")
public class BeanUtil extends CACodeBeanUtil {
    /**
     * <p>设置你的工具类
     * <p>Set your utility class
     *
     * @param aClass 需要制成的工具类
     */
    public BeanUtil(Class aClass) {
        super(aClass);
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
    public BeanUtil() {
        super();
    }

    /**
     * <p>设置你的工具类对象
     * <p>Set your utility class object
     *
     * @param aObj 需要制成工具类的对象
     */
    public BeanUtil(Object aObj) {
        super(aObj);
    }

    @Override
    public Object getaObj() {
        return super.getaObj();
    }

    @Override
    public void setaObj(Object aObj) {
        super.setaObj(aObj);
    }

    @Override
    public Class getaClass() {
        return super.getaClass();
    }

    @Override
    public void setaClass(Class aClass) {
        super.setaClass(aClass);
    }

    @Override
    public void setMethods(Method method, Object... value)
            throws InvocationTargetException, IllegalAccessException, QualifierException {
        super.setMethods(method, value);
    }

    @Override
    public void setMethods(Object value, Method... method)
            throws InvocationTargetException, IllegalAccessException, QualifierException {
        super.setMethods(value, method);
    }

    @Override
    public void setMethods(String methodName, Object value, Class<?>... type)
            throws NoSuchMethodException, InvocationTargetException,
            IllegalAccessException, QualifierException {
        super.setMethods(methodName, value, type);
    }

    @Override
    public Object getMethod(Method method, Object... args)
            throws InvocationTargetException, IllegalAccessException, QualifierException {
        return super.getMethod(method, args);
    }

    @Override
    public Method[] getMethods(Class aClass) throws NoHaveMethod {
        return super.getMethods(aClass);
    }

    @Override
    public Method selectMethod(String methodName, Class<?>... classType) throws NoSuchMethodException {
        return super.selectMethod(methodName, classType);
    }

    @Override
    public void setField(Field field, Object value)
            throws IllegalAccessException, QualifierException {
        super.setField(field, value);
    }

    @Override
    public Object getField(Field field)
            throws IllegalAccessException, QualifierException {
        return super.getField(field);
    }

    @Override
    public Field selectField(String fieldName) throws NoSuchFieldException {
        return super.selectField(fieldName);
    }

    /**
     * <p>你可以使用此方法生成一个数据库的工具类集合</p>
     * <p>You can use this method to generate a collection of tool classes for a database</p>
     *
     * @param dataSource 数据 -data
     * @return
     * @throws Exception 因为抛出的异常太多，所以直接抛了Exception
     *                   <p>
     *                   Because so many exceptions were thrown, I threw an Exception directly
     */
    @Deprecated(since = "使用不方便，建议使用参数带列名的getTool()")
    public List<?> getTool(List<List<Object>> dataSource)
            throws IllegalAccessException, QualifierException,
            InvocationTargetException, InstantiationException {
        List<Object> tool = new Vector<>();
        List<Method> methods = new Vector<>(Arrays.asList(this.getaClass().getMethods()));
        if (dataSource.size() == 0 || dataSource.get(0).size() == 0) {
            return tool;
        }
        Iterator<Method> iterator = methods.iterator();
        while (iterator.hasNext()) {
            Method next = iterator.next();
            if (!next.getName().contains("set")) {
                iterator.remove();
            }
        }
        if (methods.size() == 0) {
            throw new QualifierException(CACODE + "没有set方法！");
        }
        Collections.sort(methods, new Comparator<Method>() {
            @Override
            public int compare(Method o1, Method o2) {
                return Integer.parseInt(
                        String.valueOf(o1.getName().toCharArray()[o1.getName().length() - 1])
                ) >
                        Integer.parseInt(
                                String.valueOf(o2.getName().toCharArray()[o2.getName().length() - 1])
                        ) ? 1 : -1;
            }
        });
        for (int i = 0; i < dataSource.get(0).size(); i++) {
            for (int j = 0; j < dataSource.size(); j++) {
                this.setMethods(methods.get(j), dataSource.get(j).get(i));
            }
            tool.add(this.getaObj());
            //重新实例化
            this.setaObj(this.getaClass().newInstance());
            this.setaClass(this.getaObj().getClass());
        }
        return tool;
    }

    /**
     * <p>你可以使用此方法生成一个数据库的工具类集合</p>
     * <p>You can use this method to generate a collection of tool classes for a database</p>
     *
     * @param dataSource  数据源
     * @param columnNames 列名
     * @return 工具类
     * @throws Exception 因为抛出的异常太多，所以直接抛了Exception
     *                   <p>
     *                   Because so many exceptions were thrown, I threw an Exception directly
     */
    @Deprecated(since = "有一个不需要新建一个list集合的方法，" +
            "如果你愿意新建一个List<Object>的集合你就用这个方法，" +
            "你愿意吗？反正我不愿意")
    public Map<Integer, ?> getTool(List<List<Object>> dataSource, List<String> columnNames) throws Exception {
        Map<Integer, Object> tool = new HashMap<>(5);
        List<Method> methods = new Vector<>(Arrays.asList(this.getaClass().getMethods()));
        if (dataSource.size() == 0 || dataSource.get(0).size() == 0) {
            return tool;
        }
        Iterator<Method> iterator = methods.iterator();
        int so = 0;
        while (iterator.hasNext()) {
            Method next = iterator.next();
            if (!next.getName().toLowerCase().contains("set")) {
                iterator.remove();
            }
            so++;
        }
        if (methods.size() != columnNames.size()) {
            throw new QualifierException("与字段对应的方法数量不正确");
        }
        if (methods.size() == 0) {
            throw new NoHaveMethod(this.getaClass() + "没有set方法！");
        }
        LinkedList<Method> link = new LinkedList<>();
        for (String item1 : columnNames) {
            for (int i = 0; i < methods.size(); i++) {
                if (methods.get(i).getName().toLowerCase().split("^set")[1].equals(item1.toLowerCase())) {
                    link.addLast(methods.get(i));
                }
            }
        }
        methods = link;
        int index = 0;
        for (int i = 0; i < dataSource.get(0).size(); i++) {
            for (int j = 0; j < dataSource.size(); j++) {
                this.setMethods(methods.get(j), dataSource.get(j).get(i));
            }
            tool.put(index, this.getaObj());
            index++;
            //重新实例化
            this.setaObj(this.getaClass().newInstance());
            this.setaClass(this.getaObj().getClass());
        }
        return tool;
    }

    /**
     * <p>你可以使用此方法生成一个数据库的工具类集合</p>
     * <p>You can use this method to generate a collection of tool classes for a database</p>
     *
     * @param dataSource
     * @param columnNames
     * @return
     * @throws Exception
     */
    public Map<Integer, ?> getTool(List<List<Object>> dataSource, String... columnNames) throws Exception {
        Map<Integer, Object> tool = new HashMap<>();
        List<Method> methods = new Vector<>(Arrays.asList(this.getaClass().getMethods()));
        if (dataSource.size() == 0 || dataSource.get(0).size() == 0) {
            return tool;
        }
        Iterator<Method> iterator = methods.iterator();
        while (iterator.hasNext()) {
            Method next = iterator.next();
            if (!next.getName().contains("set")) {
                iterator.remove();
            }
        }
        if (methods.size() == 0) {
            throw new QualifierException(CACODE + "没有set方法！");
        }
        LinkedList<Method> link = new LinkedList<>();
        for (String item1 : columnNames) {
            for (int i = 0; i < methods.size(); i++) {
                if (methods.get(i).getName().toLowerCase().split("^set")[1].equals(item1.toLowerCase())) {
                    link.addLast(methods.get(i));
                }
            }
        }
        methods = link;
        int index = 0;
        for (int i = 0; i < dataSource.get(0).size(); i++) {
            for (int j = 0; j < dataSource.size(); j++) {
                this.setMethods(methods.get(j), dataSource.get(j).get(i));
            }
            tool.put(index, this.getaObj());
            index++;
            //重新实例化
            this.setaObj(this.getaClass().newInstance());
            this.setaClass(this.getaObj().getClass());
        }
        return tool;
    }
}
