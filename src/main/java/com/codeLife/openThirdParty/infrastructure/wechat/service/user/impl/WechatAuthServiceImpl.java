package com.codeLife.openThirdParty.infrastructure.wechat.service.user.impl;

import com.alibaba.fastjson2.JSONObject;
import com.codeLife.openThirdParty.domain.app.SysAppConfig;
import com.codeLife.openThirdParty.domain.wechat.user.dto.AccessTokenDto;
import com.codeLife.openThirdParty.domain.wechat.user.vo.AccessTokenVo;
import com.codeLife.openThirdParty.infrastructure.app.service.SysAppConfigService;
import com.codeLife.openThirdParty.infrastructure.wechat.service.feign.WechatUserFeign;
import com.codeLife.openThirdParty.infrastructure.wechat.service.feign.dto.AccessTokenFeignDto;
import com.codeLife.openThirdParty.infrastructure.wechat.service.user.WechatAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class WechatAuthServiceImpl implements WechatAuthService {
    private final SysAppConfigService appConfigService;
    private final WechatUserFeign wechatUserFeign;

    public WechatAuthServiceImpl(SysAppConfigService appConfigService, WechatUserFeign wechatUserFeign) {
        this.appConfigService = appConfigService;
        this.wechatUserFeign = wechatUserFeign;
    }

    @Override
    public AccessTokenVo getAccessToken(AccessTokenDto dto) {
        SysAppConfig appConfig = appConfigService.getAppConfigByAppId(dto.getAppId());
        AccessTokenFeignDto accessTokenFeignDto = new AccessTokenFeignDto();
        accessTokenFeignDto.setAppid(appConfig.getAppId())
                .setSecret(appConfig.getWechatAppSecret())
                .setCode(dto.getCode());
        String accessToken = wechatUserFeign.getAccessToken(accessTokenFeignDto);
        return JSONObject.parseObject(accessToken, AccessTokenVo.class);
    }
}
