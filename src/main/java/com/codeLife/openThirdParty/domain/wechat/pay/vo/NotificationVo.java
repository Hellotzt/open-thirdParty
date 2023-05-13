package com.codeLife.openThirdParty.domain.wechat.pay.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NotificationVo {
    /**
     * 应用ID
     */
    @JsonProperty("appid")
    private String appId;
    /**
     * 商户号
     */
    @JsonProperty("mchid")
    private String mchId;
    /**
     * 商户订单号
     */
    @JsonProperty("out_trade_no")
    private String outTradeNo;
    /**
     * 微信支付订单号
     */
    @JsonProperty("transaction_id")
    private String transactionId;
    /**
     * 交易类型
     */
    @JsonProperty("trade_type")
    private String tradeType;
    /**
     * 交易状态
     */
    @JsonProperty("trade_state")
    private String tradeState;
    /**
     * 交易状态描述
     */
    @JsonProperty("trade_state_desc")
    private String tradeStateDesc;
    /**
     * 付款银行
     */
    @JsonProperty("bank_type")
    private String bankType;
    /**
     * 附加数据
     */
    @JsonProperty("attach")
    private String attach;
    /**
     * 支付完成时间
     */
    @JsonProperty("success_time")
    private String successTime;
}
