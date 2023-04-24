package com.codeLife.openThirdParty.application.wechat;

import cn.hutool.core.util.ObjectUtil;
import com.codeLife.openThirdParty.domain.wechat.user.dto.AccessTokenDto;
import com.codeLife.openThirdParty.domain.wechat.user.vo.AccessTokenVo;
import com.codeLife.openThirdParty.infrastructure.common.param.CodeMsg;
import com.codeLife.openThirdParty.infrastructure.common.param.ResultData;
import com.codeLife.openThirdParty.infrastructure.common.util.RedisUtil;
import com.codeLife.openThirdParty.infrastructure.wechat.service.user.WechatAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class WechatUserService {
    private final RedisUtil redisUtil;
    private final WechatAuthService wechatAuthService;

    public WechatUserService(RedisUtil redisUtil,WechatAuthService wechatAuthService) {
        this.redisUtil = redisUtil;
        this.wechatAuthService = wechatAuthService;
    }

    public ResultData<Object> getAccessToken(AccessTokenDto dto) {
        if (ObjectUtil.isNull(redisUtil.get(dto.getState()))){
            log.error("请求微信获取token->校验码错误 报文：{}",dto);
            return ResultData.fail(CodeMsg.AUTH_ERROR);
        }
        AccessTokenVo accessTokenVo = wechatAuthService.getAccessToken(dto);
        return ResultData.success(accessTokenVo);
    }
}
