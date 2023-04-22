package com.codeLife.openThirdParty.infrastructure.wechat.service.feign;

import com.codeLife.openThirdParty.infrastructure.wechat.service.feign.vo.NativeCodeVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "wechatPayFeign", url = "https://api.mch.weixin.qq.com")
public interface WechatPayFeign {
    /**
     * 获取native支付二维码
     * @param reqBody 请求体
     * @param token 认证信息
     * @return 二维码url
     */
    @PostMapping(value = "/v3/pay/transactions/native", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    NativeCodeVo getNativeCodeUrl(@RequestBody String reqBody, @RequestHeader("Authorization") String token);
}
