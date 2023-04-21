package com.codeLife.openThirdParty.api.wechat.pay;

import com.codeLife.openThirdParty.application.wechat.WechatPayService;
import com.codeLife.openThirdParty.domain.wechat.dto.WechatPayDto;
import com.codeLife.openThirdParty.infrastructure.common.param.ResultData;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信支付控制层
 *
 * @author Hellotzt
 */
@RestController
@RequestMapping("/wechat/pay")
public class WechatPayController {
    private final WechatPayService wechatPayService;

    public WechatPayController(WechatPayService wechatPayService) {
        this.wechatPayService = wechatPayService;
    }

    /**
     * 获取native支付二维码
     * @param dto 应用参数
     * @return 二维码数据
     */
    @RequestMapping("/getNativeUrl")
    public ResultData<Object> getNativeUrl(@RequestBody WechatPayDto dto){
        wechatPayService.getNativeUrl(dto);
        return null;
    }
}
