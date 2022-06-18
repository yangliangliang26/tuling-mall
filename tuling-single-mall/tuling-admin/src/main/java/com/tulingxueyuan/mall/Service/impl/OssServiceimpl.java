package com.tulingxueyuan.mall.Service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.tulingxueyuan.mall.Service.OssService;
import com.tulingxueyuan.mall.dto.OssEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class OssServiceimpl implements OssService {

    @Value("${aliyun.oss.bucketName}")
    String bucket;

    @Value("${aliyun.oss.endpoint}")
    String endpoint;

    @Value("${aliyun.oss.accessKeyId}")
    String accesskey;
    @Value("${aliyun.oss.policy.expire}")
    String expire;

    @Value("${aliyun.oss.dir.prefix}")
    String dir ;
    @Value("${aliyun.oss.maxSize}")
    Long MaxSize;
    @Value("${aliyun.oss.policy.expire}")
    Long expireTime;

    @Autowired
    OSSClient ossClient;


    @Override
    public OssEntity policy(){
//       https://objectjjs.oss-cn-beijing.aliyuncs.com/aa.txt

        String host = "https://" + bucket + "." + endpoint; // host的格式为 bucketname.endpoint
        // callbackUrl为上传回调服务器的URL，请将下面的IP和Port配置为您自己的真实信息。
//       String callbackUrl = "http://88.88.88.88:8888";

        String date =new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String dir1 = dir+"/"+date+"/"; // 用户上传文件时指定的前缀。
        // 用户上传文件时指定的前缀。(意思以一天为单位创建一个目录
        OssEntity respMap = new OssEntity();

        try {
//            long expireTime = 30;
            long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            Date expiration = new Date(expireEndTime);
            // PostObject请求最大可支持的文件大小为5 GB，即CONTENT_LENGTH_RANGE为5*1024*1024*1024。
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0,MaxSize);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

            String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = ossClient.calculatePostSignature(postPolicy);


            respMap.setAccessKeyId(accesskey);
            respMap.setPolicy(encodedPolicy);
            respMap.setSignature(postSignature);
            respMap.setDir(dir1);
            respMap.setHost(host);
            respMap.setExpire(String.valueOf(expireEndTime / 1000));
            // respMap.put("expire", formatISO8601Date(expiration));


        } catch (Exception e) {
            // Assert.fail(e.getMessage());
            System.out.println(e.getMessage());
        } finally {
            ossClient.shutdown();
        }
        return respMap;
}
}
