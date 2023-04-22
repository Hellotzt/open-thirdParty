package com.codeLife.openThirdParty.application.wechat;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson2.JSON;
import com.codeLife.openThirdParty.domain.app.SysAppConfig;
import com.codeLife.openThirdParty.domain.wechat.dto.WechatPayDto;
import com.codeLife.openThirdParty.domain.wechat.vo.WechatPayVo;
import com.codeLife.openThirdParty.infrastructure.app.service.SysAppConfigService;
import com.codeLife.openThirdParty.infrastructure.common.param.CodeMsg;
import com.codeLife.openThirdParty.infrastructure.common.param.ResultData;
import com.codeLife.openThirdParty.infrastructure.wechat.service.WechatService;
import com.codeLife.openThirdParty.infrastructure.wechat.service.feign.vo.NativeCodeVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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
}
