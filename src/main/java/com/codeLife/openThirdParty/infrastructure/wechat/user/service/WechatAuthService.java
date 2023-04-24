package com.codeLife.openThirdParty.infrastructure.wechat.user.service;

import com.codeLife.openThirdParty.domain.wechat.user.dto.AccessTokenDto;
import com.codeLife.openThirdParty.domain.wechat.user.vo.AccessTokenVo;

public interface WechatAuthService {
    /**
     * 获取微信AccessToken
     * @param dto 请求参数
     * @return 用户token
     */
    AccessTokenVo getAccessToken(AccessTokenDto dto);
}
