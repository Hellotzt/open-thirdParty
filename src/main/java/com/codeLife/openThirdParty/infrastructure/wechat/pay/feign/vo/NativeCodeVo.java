package com.codeLife.openThirdParty.infrastructure.wechat.pay.feign.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NativeCodeVo {
    /**
     * 二维码url
     */
    @JsonProperty("code_url")
    private String codeUrl;
}
