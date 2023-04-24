package com.codeLife.openThirdParty.application.wechat;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson2.JSON;
import com.codeLife.openThirdParty.domain.app.SysAppConfig;
import com.codeLife.openThirdParty.domain.wechat.pay.dto.NotificationDto;
import com.codeLife.openThirdParty.domain.wechat.pay.dto.WechatPayDto;
import com.codeLife.openThirdParty.domain.wechat.pay.vo.WechatPayVo;
import com.codeLife.openThirdParty.infrastructure.app.service.SysAppConfigService;
import com.codeLife.openThirdParty.infrastructure.common.param.CodeMsg;
import com.codeLife.openThirdParty.infrastructure.common.param.ResultData;
import com.codeLife.openThirdParty.infrastructure.common.util.PrivateKeyUtil;
import com.codeLife.openThirdParty.infrastructure.wechat.pay.serivice.WechatService;
import com.codeLife.openThirdParty.infrastructure.wechat.pay.feign.vo.NativeCodeVo;
import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.Verifier;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.cert.CertificatesManager;
import com.wechat.pay.contrib.apache.httpclient.notification.Notification;
import com.wechat.pay.contrib.apache.httpclient.notification.NotificationHandler;
import com.wechat.pay.contrib.apache.httpclient.notification.NotificationRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Slf4j
@Service
public class WechatPayService {
    private final SysAppConfigService appConfigService;
    private final WechatService wechatService;

    public WechatPayService(SysAppConfigService appConfigService, WechatService wechatService) {
        this.appConfigService = appConfigService;
        this.wechatService = wechatService;
    }


    public ResultData<Object> getNativeUrl(WechatPayDto dto) {
        SysAppConfig appConfig = appConfigService.getAppConfigByAppId(dto.getAppId());
        if (ObjectUtil.isNull(appConfig)){
            log.info("获取应用配置失败：{}",dto);
            return ResultData.fail(CodeMsg.SERVER_ERROR);
        }
        WechatPayVo payVo = new WechatPayVo().buildByAppConfig(appConfig,null);
        payVo.setDescription("测试商品");
        NativeCodeVo nativeCodeVo = wechatService.getNativeCodeUrl(JSON.toJSONString(payVo), appConfig);
        return ResultData.success(nativeCodeVo.getCodeUrl());
    }

    public void payNotificationByWechat(NotificationDto dto) {
        try {
            SysAppConfig appConfig = appConfigService.getAppConfigByAppId(dto.getAppId());
            CertificatesManager certificatesManager = CertificatesManager.getInstance();
            // 向证书管理器增加需要自动更新平台证书的商户信息
            certificatesManager.putMerchant(appConfig.getWechatMchId(), new WechatPay2Credentials(appConfig.getWechatMchId(),
                            new PrivateKeySigner(appConfig.getWechatSerialNo(), PrivateKeyUtil.getPrivateKey(appConfig.getWechatPrivateKeyPath()))),
                    appConfig.getWechatApiV3Key().getBytes(StandardCharsets.UTF_8));

            // 从证书管理器中获取verifier
            Verifier verifier = certificatesManager.getVerifier(appConfig.getWechatMchId());
            // 构建request，传入必要参数
            NotificationRequest request = new NotificationRequest.Builder().withSerialNumber(dto.getSerial())
                    .withNonce(dto.getNonce())
                    .withTimestamp(dto.getTimestamp())
                    .withSignature(dto.getSignature())
                    .withBody(dto.getReqBody())
                    .build();
            NotificationHandler handler = new NotificationHandler(verifier, appConfig.getWechatApiV3Key().getBytes(StandardCharsets.UTF_8));
            // 验签和解析请求体
            Notification notification = handler.parse(request);
            Assert.assertNotNull(notification);
            log.info("报文解析结果：{}", notification);
        } catch (Exception e) {
            log.error("微信支付回调处理异常：", e);
            throw new RuntimeException(e);
        }
    }
}
