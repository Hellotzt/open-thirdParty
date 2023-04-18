package com.codeLife.openThirdParty.api.app;


import com.codeLife.openThirdParty.application.app.AppConfigService;
import com.codeLife.openThirdParty.domain.app.SysAppConfig;
import com.codeLife.openThirdParty.domain.app.dto.AppConfigDto;
import com.codeLife.openThirdParty.infrastructure.common.param.ResultData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 应用配置表(SysAppConfig)表控制层
 *
 * @author Hellotzt
 */
@RestController
@RequestMapping("/app")
public class AppConfigController {
    private final AppConfigService appConfigService;

    public AppConfigController(AppConfigService appConfigService) {
        this.appConfigService = appConfigService;
    }

    @PostMapping("/getAppConfig")
    public ResultData<SysAppConfig> getAppConfig(@RequestBody AppConfigDto dto){
        SysAppConfig appConfig = appConfigService.getAppConfig(dto);
        return ResultData.success(appConfig);
    }
}

