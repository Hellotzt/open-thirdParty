package com.codeLife.openThirdParty.infrastructure.wechat.user.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.codeLife.openThirdParty.domain.app.SysAppConfig;
import com.codeLife.openThirdParty.domain.wechat.user.dto.AccessTokenDto;
import com.codeLife.openThirdParty.domain.wechat.user.vo.AccessTokenVo;
import com.codeLife.openThirdParty.infrastructure.app.service.SysAppConfigService;
import com.codeLife.openThirdParty.infrastructure.wechat.user.feign.WechatAuthFeign;
import com.codeLife.openThirdParty.infrastructure.wechat.user.feign.dto.AccessTokenFeignDto;
import com.codeLife.openThirdParty.infrastructure.wechat.user.service.WechatAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class WechatAuthServiceImpl implements WechatAuthService {
    private final SysAppConfigService appConfigService;
    private final WechatAuthFeign wechatAuthFeign;

    public WechatAuthServiceImpl(SysAppConfigService appConfigService, WechatAuthFeign wechatAuthFeign) {
        this.appConfigService = appConfigService;
        this.wechatAuthFeign = wechatAuthFeign;
    }

    @Override
    public AccessTokenVo getAccessToken(AccessTokenDto dto) {
        SysAppConfig appConfig = appConfigService.getAppConfigByAppId(dto.getAppId());
        AccessTokenFeignDto accessTokenFeignDto = new AccessTokenFeignDto();
        accessTokenFeignDto.setAppid(appConfig.getAppId())
                .setSecret(appConfig.getWechatAppSecret())
                .setCode(dto.getCode());
        String accessToken = wechatAuthFeign.getAccessToken(accessTokenFeignDto);
        return JSONObject.parseObject(accessToken, AccessTokenVo.class);
    }
}
