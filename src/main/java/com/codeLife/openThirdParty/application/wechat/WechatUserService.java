package com.codeLife.openThirdParty.application.wechat;

import com.codeLife.openThirdParty.domain.wechat.user.dto.AccessTokenDto;
import com.codeLife.openThirdParty.domain.wechat.user.vo.AccessTokenVo;
import com.codeLife.openThirdParty.infrastructure.common.param.ResultData;
import com.codeLife.openThirdParty.infrastructure.wechat.user.service.WechatAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class WechatUserService {
    private final WechatAuthService wechatAuthService;

    public WechatUserService(WechatAuthService wechatAuthService) {
        this.wechatAuthService = wechatAuthService;
    }

    public ResultData<Object> getAccessToken(AccessTokenDto dto) {
        AccessTokenVo accessTokenVo = wechatAuthService.getAccessToken(dto);
        return ResultData.success(accessTokenVo);
    }
}
