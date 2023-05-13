package com.codeLife.openThirdParty.domain.wechat.pay.enums;

/**
 * 交易状态
 * SUCCESS：支付成功
 * REFUND：转入退款
 * NOTPAY：未支付
 * CLOSED：已关闭
 * REVOKED：已撤销（付款码支付）
 * USERPAYING：用户支付中（付款码支付）
 * PAYERROR：支付失败(其他原因，如银行返回失败)
 */
public enum TradeState {
    SUCCESS("SUCCESS","支付成功");
    /**
     * 类型
     */
    private final String type;
    /**
     * 描述
     */
    private final String desc;

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    TradeState(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
