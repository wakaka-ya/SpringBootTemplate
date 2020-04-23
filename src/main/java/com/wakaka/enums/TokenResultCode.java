package com.wakaka.enums;
/**
 * Description Token异常响应结构
 * USER: zfy
 * Date: 2020/4/1 10:08
 */
public enum TokenResultCode implements BaseErrorInfoInterface {
    PERMISSION_TOKEN_EXPIRED("1001","Token已过期!"),
    PERMISSION_TOKEN_INVALID("1002","Token解析异常!"),
    PERMISSION_SIGNATURE_ERROR("1003","Token签名验证失败!"),
    USER_NOT_LOGGED_IN("1004","用户未登录!"),
;

    /** 错误码 */
    private String resultCode;

    /** 错误描述 */
    private String resultMsg;

    TokenResultCode(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    @Override
    public String getResultCode() {
        return resultCode;
    }

    @Override
    public String getResultMsg() {
        return resultMsg;
    }
}
