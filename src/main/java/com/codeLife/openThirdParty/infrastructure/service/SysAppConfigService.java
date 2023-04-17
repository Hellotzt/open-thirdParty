package com.codeLife.openThirdParty.infrastructure.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.codeLife.openThirdParty.domain.SysAppConfig;

/**
 * 应用配置表(SysAppConfig)表服务接口
 *
 * @author Hellotzt
 */
public interface SysAppConfigService extends IService<SysAppConfig> {
    /**
     * 通过appId获取app配置
     * @param appId appId
     * @return app配置信息
     */
    SysAppConfig getAppConfigByAppId(String appId);
}

