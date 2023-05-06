package com.codeLife.openThirdParty.infrastructure.config;


import cn.hutool.core.date.DateUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.codec.Decoder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

@Configuration
@Slf4j
public class FeignRequestInterceptor implements RequestInterceptor {
    @Bean
    public Decoder feignDecoder(ObjectFactory<HttpMessageConverters> messageConverters) {
        return (response, type) -> {
            Object object = new SpringDecoder(messageConverters).decode(response, type);
            String longString = object.toString();
            log.info("feign响应结果：{}", longString.length() > 150 ? longString.substring(0, 150) : longString);
            return object;
        };
    }

    @SneakyThrows
    @Override
    public void apply(RequestTemplate requestTemplate) {
        StringBuilder sb = new StringBuilder(1000);
        sb.append("\n-----------------------feign调用信息输出:")
                .append(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"))
                .append("-------------------------------------\n")
                .append("请求URL          :").append(requestTemplate.feignTarget().url()).append(requestTemplate.url()).append("\n")
                .append("请求HEADER       :").append(buildHeaderParam(requestTemplate.headers())).append("\n");
        if (null != requestTemplate.body()) {
            sb.append("请求BODY         :").append(new String(requestTemplate.body())).append("\n");
        }
        log.info(sb.toString());
    }

    public String buildHeaderParam(Map<String, Collection<String>> headers){
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Collection<String>> entry : headers.entrySet()) {
            sb.append(entry.getKey()).append(":").append(String.join("", entry.getValue())).append("\n");
        }
        return sb.toString();
    }
}
