package com.codeLife.openThirdParty.domain.wechat.vo;

import lombok.Data;

/**
 * 订单金额信息
 */
@Data
public class WechatAmountVo {
    /**
     * 总金额
     */
    private int total;
    /**
     * 货币类型 CNY：人民币
     */
    private String currency = "CNY";
}
