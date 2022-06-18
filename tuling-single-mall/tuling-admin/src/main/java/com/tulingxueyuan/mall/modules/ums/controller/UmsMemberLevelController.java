package com.tulingxueyuan.mall.modules.ums.controller;


import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.ums.model.UmsMemberLevel;
import com.tulingxueyuan.mall.modules.ums.service.UmsMemberLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 会员等级表 前端控制器
 * </p>
 *
 * @author XuShu
 * @since 2022-06-16
 */
@RestController
@RequestMapping("/memberLevel")
public class UmsMemberLevelController {

    @Autowired
    UmsMemberLevelService umsMemberLevelService;

    /** 查询会员等级
     *  url:'/memberLevel/list',
     *     method:'get',
     *     params:params
     * @param defaultStatus
     * @return
     */
    @GetMapping("/list")
    public CommonResult list(@RequestParam(value = "defaultStatus",defaultValue = "0") Integer defaultStatus){
     List<UmsMemberLevel>  umsMemberLevel=  umsMemberLevelService.listBydefaultStatus(defaultStatus);
     return CommonResult.success(umsMemberLevel);
    }
}

