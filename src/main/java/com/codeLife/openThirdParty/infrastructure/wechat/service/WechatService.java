package com.codeLife.openThirdParty.infrastructure.wechat.service;

import com.codeLife.openThirdParty.domain.app.SysAppConfig;
import com.codeLife.openThirdParty.infrastructure.wechat.service.feign.vo.NativeCodeVo;

public interface WechatService {
    /**
     * 获取native支付所需token
     * @param reqBody 请求体
     * @param appConfig 应用配置信息
     * @return token
     */
    String getNativeToken(String reqBody, SysAppConfig appConfig);

    /**
     * 获取native支付二维码
     * @param reqBody 请求体
     * @param appConfig 应用配置
     * @return 二维码url
     */
    NativeCodeVo getNativeCodeUrl(String reqBody, SysAppConfig appConfig);
}
