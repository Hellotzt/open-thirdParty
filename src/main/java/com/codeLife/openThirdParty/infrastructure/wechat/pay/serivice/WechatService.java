package com.codeLife.openThirdParty.infrastructure.wechat.pay.serivice;

import com.codeLife.openThirdParty.domain.app.SysAppConfig;
import com.codeLife.openThirdParty.infrastructure.wechat.pay.feign.vo.NativeCodeVo;

public interface WechatService {

    /**
     * 获取native支付二维码
     * @param reqBody 请求体
     * @param appConfig 应用配置
     * @return 二维码url
     */
    NativeCodeVo getNativeCodeUrl(String reqBody, SysAppConfig appConfig);
}
