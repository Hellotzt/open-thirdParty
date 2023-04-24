package com.codeLife.openThirdParty.api.wechat.user;

import com.codeLife.openThirdParty.application.wechat.WechatUserService;
import com.codeLife.openThirdParty.domain.wechat.user.dto.AccessTokenDto;
import com.codeLife.openThirdParty.infrastructure.common.param.ResultData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wechat/user")
public class WechatUserController {
    private final WechatUserService wechatUserService;

    public WechatUserController(WechatUserService wechatUserService) {
        this.wechatUserService = wechatUserService;
    }

    @PostMapping("/getAccessToken")
    public ResultData<Object> getAccessToken(@RequestBody AccessTokenDto dto){
        return wechatUserService.getAccessToken(dto);
    }
}
