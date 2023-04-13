package com.ydl.constant;

/**
 * @Author: SayHello
 * @Date: 2023/3/2 19:13
 * @Introduction:
 */
public class RedisMessageConstant {
    /**
     * 用于缓存体检预约时发送的验证码
     */
    public static final String SEND_TYPE_ORDER = "001:";

    /**
     * 用于缓存手机号快速登录时发送的验证码
     */
    public static final String SEND_TYPE_LOGIN = "002:";

    /**
     * 用于缓存找回密码时发送的验证码
     */
    public static final String SEND_TYPE_GET_PWD = "003:";

    /**
     * 已登录用户的
     */
    public static final String LOGGED_IN = "004:";

}