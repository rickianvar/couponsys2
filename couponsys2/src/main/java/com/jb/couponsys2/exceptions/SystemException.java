package com.jb.couponsys2.exceptions;

public class SystemException extends Exception{

    public SystemException(ErrMsg errMsg) {
        super(errMsg.getMessage());

    }
}
