/*
 * Copyright (C), CACode, 2020, all rights reserved.
 *
 * Project name： com.cacode.summer
 * File name： QualifierException.java
 *  Module declaration:
 * Modify the history:
 * 2020-6-11 - CACode - Create。
 */

package com.cacode.beanutil.excepotion;

/**
 * @author CACode http://www.adminznh.ren
 * @version 1.0
 * @date 2020/5/1 5:19
 */
public class QualifierException extends Exception {
    private static final long serialVersionUID = 5034388446362600923L;
    private static final String ERROR_TEXT = "内容不可访问-Content is not accessible";

    public QualifierException() {
        super(ERROR_TEXT);
    }

    public QualifierException(String message) {
        super(message);
    }

    public QualifierException(String message, Throwable cause) {
        super(message, cause);
    }

    public QualifierException(Throwable cause) {
        super(cause);
    }

    public QualifierException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
