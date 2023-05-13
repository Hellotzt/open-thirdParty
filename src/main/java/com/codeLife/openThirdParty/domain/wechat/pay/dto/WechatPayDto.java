package com.codeLife.openThirdParty.domain.wechat.pay.dto;

import lombok.Data;

@Data
public class WechatPayDto {
    /**
     * appId
     */
    private String appId;
    /**
     * 订单总价
     */
    private Integer totalPrice;
    /**
     * 外部流水号
     */
    private String outTradeNo;
    /**
     * 商品描述
     */
    private String description;
}
