/*
 * Copyright (C), CACode, 2020, all rights reserved.
 *
 * Project name： com.cacode.summer
 * File name： NoHaveMethod.java
 *  Module declaration:
 * Modify the history:
 * 2020-6-11 - CACode - Create。
 */

package com.cacode.beanutil.excepotion;

/**
 * @author CACode http://www.adminznh.ren
 * @version 1.0
 * @date 2020/6/8 16:27
 */
public class NoHaveMethod extends QualifierException {
    public NoHaveMethod() {
        super("没有set方法");
    }

    public NoHaveMethod(String message) {
        super(message);
    }

    public NoHaveMethod(String message, Throwable cause) {
        super(message, cause);
    }

    public NoHaveMethod(Throwable cause) {
        super(cause);
    }

    public NoHaveMethod(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
