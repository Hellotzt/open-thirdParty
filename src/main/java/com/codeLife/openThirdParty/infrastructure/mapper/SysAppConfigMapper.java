package com.codeLife.openThirdParty.infrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.codeLife.openThirdParty.domain.SysAppConfig;
import org.apache.ibatis.annotations.Mapper;

/**
 * 应用配置表(SysAppConfig)表数据库访问层
 *
 * @author Hellotzt
 */
@Mapper
public interface SysAppConfigMapper extends BaseMapper<SysAppConfig> {

}

