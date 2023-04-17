package com.codeLife.openThirdParty.application;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson2.JSON;
import com.codeLife.openThirdParty.domain.SysAppConfig;
import com.codeLife.openThirdParty.domain.dto.AppConfigDto;
import com.codeLife.openThirdParty.infrastructure.common.constants.RedisKey;
import com.codeLife.openThirdParty.infrastructure.common.util.RedisUtil;
import com.codeLife.openThirdParty.infrastructure.service.SysAppConfigService;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class AppConfigService {
    private final SysAppConfigService sysAppConfigService;
    private final RedisUtil redisUtil;
    public AppConfigService(SysAppConfigService sysAppConfigService, RedisUtil redisUtil) {
        this.sysAppConfigService = sysAppConfigService;
        this.redisUtil = redisUtil;
    }

    /**
     * 获取app配置信息
     * @param dto 筛选条件
     * @return 配置信息
     */
    public SysAppConfig getAppConfig(AppConfigDto dto) {
        Object redisResult = redisUtil.get(RedisKey.AppIdKey + dto.getAppId());
        if (ObjectUtil.isNotNull(redisResult)){
            return JSON.parseObject(JSON.toJSONString(redisResult),SysAppConfig.class);
        }
        SysAppConfig appConfig = sysAppConfigService.getAppConfigByAppId(dto.getAppId());
        if (ObjectUtil.isNull(appConfig)){
            return null;
        }
        redisUtil.setWithExpireTime(RedisKey.AppIdKey + dto.getAppId(),appConfig,1, TimeUnit.DAYS);
        return appConfig;
    }
}
