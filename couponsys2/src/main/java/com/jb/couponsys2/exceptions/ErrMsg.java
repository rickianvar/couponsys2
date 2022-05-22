package com.jb.couponsys2.exceptions;

import lombok.Getter;

import java.util.function.Supplier;

@Getter
public enum ErrMsg {

    COMPANY_NAME_EXISTS("ERR: COMPANY NAME ALREADY EXISTS "),
    NAME_EXISTS(" ERR: NAME ALREADY EXISTS"),
    COMPANY_EMAIL_EXISTS("ERR: COMPANY EMAIL ALREADY EXIST "),
    EMAIL_EXISTS("ERR: EMAIL ALREADY EXIST"),
    COMPANY_ID_NOT_EXISTS("ERR: COMPANY ID NOT FOUND "),
    COUPON_ID_NOT_EXISTS("ERR: COUPON ID NOT FOUND "),
    COUPON_AMOUNT("ERR: NO COUPON LEFT - AMOUNT ZERO "),
    COUPON_EXPIRED("ERR: COUPON EXPIRED  "),
    COMPANY_ID_EXISTS("ERR: COMPANY ID EXIST "),
    CUSTOMER_ID_NOT_EXISTS("ERR: CUSTOMER ID NOT EXIST "),
    COUPON_NO_UPDATE_COMPANY("ERR: UPDATE COUPON - COMPANY ID INPUT <> COUPONS COMPANY ID  "),
    ID_NOT_EXISTS("ERR: ID NOT FOUND "),
    COUPON_EXIST("ERR: COUPON EXIST ");

    private String message;

    ErrMsg(String message) {
        this.message = "\u001B[34m" + message +  "\033[0m" ;
    }

}
