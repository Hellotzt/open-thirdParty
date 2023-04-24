package com.codeLife.openThirdParty.infrastructure.wechat.user.feign;

import com.codeLife.openThirdParty.infrastructure.wechat.user.feign.dto.AccessTokenFeignDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "wechatAuthFeign", url = "https://api.weixin.qq.com/sns")
public interface WechatAuthFeign {
    @GetMapping("/oauth2/access_token")
    String getAccessToken(@SpringQueryMap AccessTokenFeignDto accessTokenFeignDto);
}
