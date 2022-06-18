package com.tulingxueyuan.mall.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OssEntity {

    //访问时的用户标识
    private String accessKeyId;

    private String accessKey;
    //表单提交策略
    private String policy;
    //对表单签名后的字符串
    private String signature;
    //文件名称
    private String bucketName;
    //站点
    private String host;
    //文件前缀
    private String dir;

    private String expire;
}
