package com.codeLife.openThirdParty.application;

import com.codeLife.openThirdParty.domain.SysAppConfig;
import com.codeLife.openThirdParty.domain.dto.AppConfigDto;
import com.codeLife.openThirdParty.infrastructure.service.SysAppConfigService;
import org.springframework.stereotype.Service;

@Service
public class AppConfigService {
    private final SysAppConfigService sysAppConfigService;

    public AppConfigService(SysAppConfigService sysAppConfigService) {
        this.sysAppConfigService = sysAppConfigService;
    }

    public SysAppConfig getAppConfig(AppConfigDto dto) {
        return sysAppConfigService.getAppConfigByAppId(dto.getAppId());
    }
}
