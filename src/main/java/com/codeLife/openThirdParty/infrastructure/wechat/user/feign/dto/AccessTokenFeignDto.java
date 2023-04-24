package com.codeLife.openThirdParty.infrastructure.wechat.user.feign.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AccessTokenFeignDto {
    /**
     * 应用id
     */
    private String appid;
    /**
     * 应用密钥
     */
    private String secret;
    /**
     * code
     */
    private String code;
    /**
     * 授权类型
     */
    private String grant_type = "authorization_code";
}
