package com.dms.base.baseproject.net.error;

/**
 * Created by wanglei on 2016/12/24.
 */

public class NetError extends Exception {
    private Throwable exception;

    private int errorType = OTHER_ERROR;

    public static final int PARSE_ERROR = 0;   //数据解析异常
    public static final int CONNECT_ERROR = 1;   //连接异常
    public static final int AUTH_ERROR = 2;   //用户验证异常
    public static final int NO_DATA_ERROR = 3;   //无数据返回异常
    public static final int BUSINESS_ERROR = 4;   //业务异常
    public static final int OTHER_ERROR = 5;   //其他异常

    public NetError(Throwable exception, int errorType) {
        this.exception = exception;
        this.errorType = errorType;
    }

    public NetError(String detailMessage, int errorType) {
        super(detailMessage);
        this.errorType = errorType;
    }

    @Override
    public String getMessage() {
        if (exception != null) return exception.getMessage();
        return super.getMessage();
    }

    public boolean isParseError() {
        return errorType == PARSE_ERROR;
    }

    public boolean isConnectError() {
        return errorType == CONNECT_ERROR;
    }

    public boolean isAuthError() {
        return errorType == AUTH_ERROR;
    }

    public boolean isNoDataError() {
        return errorType == NO_DATA_ERROR;
    }

    public boolean isBusinessError() {
        return errorType == BUSINESS_ERROR;
    }


}
