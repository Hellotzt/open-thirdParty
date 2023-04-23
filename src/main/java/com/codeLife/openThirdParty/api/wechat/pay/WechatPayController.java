package com.codeLife.openThirdParty.api.wechat.pay;

import com.codeLife.openThirdParty.application.wechat.WechatPayService;
import com.codeLife.openThirdParty.domain.wechat.dto.NotificationDto;
import com.codeLife.openThirdParty.domain.wechat.dto.WechatPayDto;
import com.codeLife.openThirdParty.infrastructure.common.param.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 微信支付控制层
 *
 * @author Hellotzt
 */
@Slf4j
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
        return wechatPayService.getNativeUrl(dto);
    }

    @PostMapping("/payNotification/{appId}")
    public String payNotification(@PathVariable("appId") String appId, @RequestBody String reqBody, HttpServletRequest httpServletRequest) {
        NotificationDto dto = new NotificationDto();
        dto.setTimestamp(httpServletRequest.getHeader("Wechatpay-Timestamp"));
        dto.setNonce(httpServletRequest.getHeader("Wechatpay-Nonce"));
        dto.setSignature(httpServletRequest.getHeader("Wechatpay-Signature"));
        dto.setSerial(httpServletRequest.getHeader("Wechatpay-Serial"));
        dto.setReqBody(reqBody);
        dto.setAppId(appId);
        wechatPayService.payNotificationByWechat(dto);
        return "SUCCESS";
    }
}
