package com.codeLife.openThirdParty.infrastructure.app.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codeLife.openThirdParty.domain.app.SysAppConfig;
import com.codeLife.openThirdParty.infrastructure.app.constants.AppRedisKey;
import com.codeLife.openThirdParty.infrastructure.common.util.RedisUtil;
import com.codeLife.openThirdParty.infrastructure.app.mapper.SysAppConfigMapper;
import com.codeLife.openThirdParty.infrastructure.app.service.SysAppConfigService;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 应用配置表(SysAppConfig)表服务实现类
 *
 * @author Hellotzt
 */
@Service("sysAppConfigService")
public class SysAppConfigServiceImpl extends ServiceImpl<SysAppConfigMapper, SysAppConfig> implements SysAppConfigService {
    private final RedisUtil redisUtil;

    public SysAppConfigServiceImpl(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @Override
    public SysAppConfig getAppConfigByAppId(String appId) {
        Object redisResult = redisUtil.get(AppRedisKey.AppIdKey + appId);
        if (ObjectUtil.isNotNull(redisResult)){
            return JSON.parseObject(redisResult.toString(),SysAppConfig.class);
        }
        SysAppConfig appConfig = this.getOne(new LambdaQueryWrapper<SysAppConfig>()
                .eq(SysAppConfig::getAppId, appId));
        if (ObjectUtil.isNull(appConfig)){
            return null;
        }
        redisUtil.setWithExpireTime(AppRedisKey.AppIdKey + appId,appConfig,1, TimeUnit.DAYS);
        return appConfig;
    }
}

