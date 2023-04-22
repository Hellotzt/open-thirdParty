package com.codeLife.openThirdParty.application.wechat;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson2.JSON;
import com.codeLife.openThirdParty.domain.app.SysAppConfig;
import com.codeLife.openThirdParty.domain.wechat.dto.WechatPayDto;
import com.codeLife.openThirdParty.domain.wechat.vo.WechatPayVo;
import com.codeLife.openThirdParty.infrastructure.app.service.SysAppConfigService;
import com.codeLife.openThirdParty.infrastructure.wechat.service.WechatService;
import org.springframework.stereotype.Service;

@Service
public class WechatPayService {
    private final SysAppConfigService appConfigService;
    private final WechatService wechatService;

    public WechatPayService(SysAppConfigService appConfigService, WechatService wechatService) {
        this.appConfigService = appConfigService;
        this.wechatService = wechatService;
    }


    public void getNativeUrl(WechatPayDto dto) {
        SysAppConfig appConfig = appConfigService.getAppConfigByAppId(dto.getAppId());
        if (ObjectUtil.isNull(appConfig)){

        }
        WechatPayVo payVo = new WechatPayVo().buildByAppConfig(appConfig,null);
        payVo.setDescription("测试商品");
        String nativeToken = wechatService.getNativeToken(JSON.toJSONString(payVo), appConfig);

    }
}
