package com.codeLife.openThirdParty.infrastructure.wechat.pay.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "universityPayFeign", url = "127.0.0.1:9000",path = "/api")
public interface UniversityPayFeign {
    @PostMapping("/pay/paySuccess")
    String paySuccess(@RequestParam String outTradeNo);
}
