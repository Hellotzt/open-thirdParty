package com.codeLife.openThirdParty.domain.wechat.pay.dto;

import lombok.Data;

@Data
public class NotificationDto {
    /**
     * 时间戳
     */
    private String timestamp;
    /**
     * 随机字符串
     */
    private String nonce;
    /**
     * 签名串
     */
    private String signature;
    /**
     * 序列号
     */
    private String serial;
    /**
     * 请求体
     */
    private String reqBody;
    /**
     * 应用id
     */
    private String appId;
}
