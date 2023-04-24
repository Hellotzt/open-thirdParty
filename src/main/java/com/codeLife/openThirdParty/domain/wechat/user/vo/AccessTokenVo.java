package com.codeLife.openThirdParty.domain.wechat.user.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AccessTokenVo {
    /**
     *
     */
    @JsonProperty("access_token")
    private String accessToken;
    /**
     *
     */
    @JsonProperty("expires_in")
    private String expiresIn;
    /**
     *
     */
    @JsonProperty("refresh_token")
    private String refreshToken;
    /**
     *
     */
    @JsonProperty("openid")
    private String openId;
    /**
     *
     */
    private String scope;
    /**
     *
     */
    @JsonProperty("unionid")
    private String unionId;
    /**
     * 错误码
     */
    @JsonProperty("errcode")
    private String errorCode;
    /**
     * 错误信息
     */
    @JsonProperty("errmsg")
    private String errMsg;
}
