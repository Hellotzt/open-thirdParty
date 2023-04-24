package com.codeLife.openThirdParty.infrastructure.wechat.pay.serivice.impl;

import cn.hutool.core.util.IdUtil;
import com.codeLife.openThirdParty.domain.app.SysAppConfig;
import com.codeLife.openThirdParty.infrastructure.common.util.PrivateKeyUtil;
import com.codeLife.openThirdParty.infrastructure.wechat.pay.serivice.WechatService;
import com.codeLife.openThirdParty.infrastructure.wechat.pay.feign.WechatPayFeign;
import com.codeLife.openThirdParty.infrastructure.wechat.pay.feign.vo.NativeCodeVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.Signature;
import java.util.Base64;

@Slf4j
@Service
public class WechatServiceImpl implements WechatService {
    private final WechatPayFeign wechatPayFeign;

    public WechatServiceImpl(WechatPayFeign wechatPayFeign) {
        this.wechatPayFeign = wechatPayFeign;
    }

    @Override
    public NativeCodeVo getNativeCodeUrl(String reqBody, SysAppConfig appConfig) {
        String nativeToken = this.getNativeToken(reqBody, appConfig);
        return wechatPayFeign.getNativeCodeUrl(reqBody, nativeToken);
    }

    @Override
    public String getNativeToken(String reqBody, SysAppConfig appConfig) {
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        String nonceStr = IdUtil.fastSimpleUUID();
        String nativePaySign = this.getNativePaySign(reqBody, timestamp, nonceStr, appConfig.getWechatPrivateKeyPath());
        return "WECHATPAY2-SHA256-RSA2048 " + "mchid=\"" + appConfig.getWechatMchId() + "\","
                + "nonce_str=\"" + nonceStr + "\","
                + "timestamp=\"" + timestamp + "\","
                + "serial_no=\"" + appConfig.getWechatSerialNo() + "\","
                + "signature=\"" + nativePaySign + "\"";
    }

    /**
     * 获取微信native支付 sign
     * @param reqBody 请求题
     * @param timestamp 时间戳
     * @param nonceStr 随机字符串
     * @param filePath 密钥地址
     * @return 签名
     */
    private String getNativePaySign(String reqBody, String timestamp, String nonceStr, String filePath) {
        String method = HttpMethod.POST.toString();
        String canonicalUrl = "/v3/pay/transactions/native";
        String data = method + "\n"
                + canonicalUrl + "\n"
                + timestamp + "\n"
                + nonceStr + "\n"
                + reqBody + "\n";

        try {
            PrivateKey privateKeyObj = PrivateKeyUtil.getPrivateKey(filePath);
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(privateKeyObj);
            signature.update(data.getBytes(StandardCharsets.UTF_8));
            byte[] signatureBytes = signature.sign();
            return Base64.getEncoder().encodeToString(signatureBytes);
        } catch (Exception e) {
            log.error("native签名错误：", e);
            throw new RuntimeException(e);
        }

    }

}
