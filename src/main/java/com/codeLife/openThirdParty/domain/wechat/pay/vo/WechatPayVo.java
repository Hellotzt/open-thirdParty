package com.codeLife.openThirdParty.domain.wechat.pay.vo;

import cn.hutool.core.util.StrUtil;
import com.codeLife.openThirdParty.domain.app.SysAppConfig;
import com.codeLife.openThirdParty.domain.wechat.pay.dto.WechatPayDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WechatPayVo {
    /**
     * 应用id
     */
    @JsonProperty("appid")
    private String appId;
    /**
     * 直连商户号
     */
    @JsonProperty("mchid")
    private String mchId;
    /**
     * 商品描述
     */
    private String description;
    /**
     * 商户订单号
     */
    @JsonProperty("out_trade_no")
    private String outTradeNo;
    /**
     * 交易结束时间 yyyy-MM-DDTHH:mm:ss+TIMEZONE
     */
    @JsonProperty("time_expire")
    private String timeExpire;
    /**
     * 附加数据
     */
    private String attach;
    /**
     * 通知地址
     */
    @JsonProperty("notify_url")
    private String notifyUrl;
    /**
     * 订单优惠标记
     */
    @JsonProperty("goods_tag")
    private String goodsTag;
    /**
     * 电子发票入口开放标识
     */
    @JsonProperty("support_fapiao")
    private boolean electronicInvoice = false;
    /**
     * 订单金额信息
     */
    @JsonProperty("amount")
    private WechatAmountVo wechatAmountVo;

    /**
     * 根据app配置构建基础微信支付实体
     *
     * @param appConfig app配置
     * @return 基础微信支付实体
     */
    public WechatPayVo buildByAppConfig(SysAppConfig appConfig, WechatPayDto dto) {
        WechatPayVo payVo = new WechatPayVo();
        payVo.setAppId(appConfig.getAppId());
        payVo.setMchId(appConfig.getWechatMchId());
        payVo.setOutTradeNo(dto.getOutTradeNo());
        wechatAmountVo = new WechatAmountVo();
        wechatAmountVo.setTotal(dto.getTotalPrice() != null ? dto.getTotalPrice() : 1);
        payVo.setDescription(StrUtil.isBlank(dto.getDescription()) ? "test" : dto.getDescription());
        payVo.setWechatAmountVo(wechatAmountVo);
        payVo.setNotifyUrl(appConfig.getWechatPayNotifyUrl());
        return payVo;
    }
}
