package com.codeLife.openThirdParty.infrastructure.wechat.service.feign;

import com.codeLife.openThirdParty.infrastructure.wechat.service.feign.dto.AccessTokenFeignDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "wechatUserFeign", url = "https://api.weixin.qq.com/sns")
public interface WechatUserFeign {
    @GetMapping("/oauth2/access_token")
    String getAccessToken(@SpringQueryMap AccessTokenFeignDto accessTokenFeignDto);
}
