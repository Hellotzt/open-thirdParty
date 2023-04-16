package com.codeLife.openThirdParty.infrastructure.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codeLife.openThirdParty.domain.SysAppConfig;
import com.codeLife.openThirdParty.infrastructure.mapper.SysAppConfigMapper;
import com.codeLife.openThirdParty.infrastructure.service.SysAppConfigService;
import org.springframework.stereotype.Service;

/**
 * 应用配置表(SysAppConfig)表服务实现类
 *
 * @author Hellotzt
 */
@Service("sysAppConfigService")
public class SysAppConfigServiceImpl extends ServiceImpl<SysAppConfigMapper, SysAppConfig> implements SysAppConfigService {

    @Override
    public SysAppConfig getAppConfigByAppId(String appId) {
        return this.getOne(new LambdaQueryWrapper<SysAppConfig>()
                .eq(SysAppConfig::getAppId, appId));
    }
}

