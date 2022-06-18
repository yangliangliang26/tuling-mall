package com.tulingxueyuan.mall.Controller;

import com.tulingxueyuan.mall.Service.OssService;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.dto.OssEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/aliyun")
public class OssController {
    /**
     *   url:'/aliyun/oss/policy',
     *     method:'get',
     */
    @Autowired
    OssService ossService;

    @GetMapping("/oss/policy")
    public CommonResult<OssEntity> policy(){

        OssEntity policy = ossService.policy();
        return CommonResult.success(policy);
    }

}
