package com.codeLife.openThirdParty.application.app;

import com.codeLife.openThirdParty.domain.app.SysAppConfig;
import com.codeLife.openThirdParty.domain.app.dto.AppConfigDto;
import com.codeLife.openThirdParty.infrastructure.app.service.SysAppConfigService;
import org.springframework.stereotype.Service;

@Service
public class AppConfigService {
    private final SysAppConfigService sysAppConfigService;
    public AppConfigService(SysAppConfigService sysAppConfigService) {
        this.sysAppConfigService = sysAppConfigService;
    }

    /**
     * 获取app配置信息
     * @param dto 筛选条件
     * @return 配置信息
     */
    public SysAppConfig getAppConfig(AppConfigDto dto) {
        return sysAppConfigService.getAppConfigByAppId(dto.getAppId());
    }
}
