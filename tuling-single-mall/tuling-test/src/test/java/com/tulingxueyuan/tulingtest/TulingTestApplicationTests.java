package com.tulingxueyuan.tulingtest;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@SpringBootTest
class TulingTestApplicationTests {

    @Test
    void uploadOSS() throws FileNotFoundException {
        // https://tulingmall-xushu.oss-cn-chengdu.aliyuncs.com/v2-def8223c53c7c90774515cf9c106da57_720w.jpg
        // Endpoint以成都为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-chengdu.aliyuncs.com";

        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        // 1. 创建RAM 子账户
        // 2. 获得accessKeyId  accessKeySecret
        // 3. 给子用户设置权限
        String accessKeyId = "LTAI4GHWCM5LdNoiLy35mBog";
        String accessKeySecret = "Vytl0LAIKGmiWATIuuwevN3LjzQs9S";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dir = simpleDateFormat.format(new Date())+"/";


        // 上传文件流。
        InputStream inputStream = new FileInputStream("C:\\Users\\zengjin\\Pictures\\v2-def8223c53c7c90774515cf9c106da57_720w.jpg");
        ossClient.putObject("tulingmall-xushu", dir+"v2-def8223c53c7c90774515cf9c106da57_720w.jpg", inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();

        System.out.println("上传成功！");
    }

}
