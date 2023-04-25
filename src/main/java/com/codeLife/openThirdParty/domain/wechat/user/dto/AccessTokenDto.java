package com.codeLife.openThirdParty.domain.wechat.user.dto;

import lombok.Data;

@Data
public class AccessTokenDto {
    /**
     * 应用id
     */
    private String appId;
    /**
     * code
     */
    private String code;
}
