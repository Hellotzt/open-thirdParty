package com.codeLife.openThirdParty.domain.app;


import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 应用配置表(SysAppConfig)表实体类
 *
 * @author Hellotzt
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class SysAppConfig extends Model<SysAppConfig> {
    /**
     * 主键ID
     */
    private Integer id;
    /**
     * 应用名称
     */
    private String appName;
    /**
     * 应用描述
     */
    private String appDescription;
    /**
     * 应用ID
     */
    private String appId;
    /**
     * 微信应用秘钥
     */
    private String wechatAppSecret;
    /**
     * 微信支付商户号
     */
    private String wechatMchId;
    /**
     * 微信支付 ApiV3密钥
     */
    private String wechatApiV3Key;
    /**
     * 微信私钥地址
     */
    private String wechatPrivateKeyPath;
    /**
     * 微信证书序列号
     */
    private String wechatSerialNo;
    /**
     * 微信支付回调地址
     */
    private String wechatPayNotifyUrl;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 是否删除(1已删除,0未删除)
     */
    private Integer deleted;
    /**
     * 创建人
     */
    private String createName;
    /**
     * 更新人
     */
    private String updateName;


}

