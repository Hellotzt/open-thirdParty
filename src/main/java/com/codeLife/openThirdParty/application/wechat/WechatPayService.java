package com.codeLife.openThirdParty.application.wechat;

import cn.hutool.core.util.ObjectUtil;
import com.codeLife.openThirdParty.domain.app.SysAppConfig;
import com.codeLife.openThirdParty.domain.wechat.dto.WechatPayDto;
import com.codeLife.openThirdParty.domain.wechat.vo.WechatPayVo;
import com.codeLife.openThirdParty.infrastructure.app.service.SysAppConfigService;
import org.springframework.stereotype.Service;

@Service
public class WechatPayService {
    private final SysAppConfigService appConfigService;

    public WechatPayService(SysAppConfigService appConfigService) {
        this.appConfigService = appConfigService;
    }


    public void getNativeUrl(WechatPayDto dto) {
        SysAppConfig appConfig = appConfigService.getAppConfigByAppId(dto.getAppId());
        if (ObjectUtil.isNull(appConfig)){

        }
        WechatPayVo payVo = new WechatPayVo().buildByAppConfig(appConfig,null);
        payVo.setDescription("测试商品");
    }
}
