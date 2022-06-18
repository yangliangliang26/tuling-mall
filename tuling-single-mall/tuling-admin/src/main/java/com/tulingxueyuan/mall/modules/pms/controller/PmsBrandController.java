package com.tulingxueyuan.mall.modules.pms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.pms.service.PmsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 品牌表 前端控制器
 * </p>
 *
 * @author XuShu
 * @since 2022-06-06
 */
@RestController
@RequestMapping("/brand")
public class PmsBrandController {

    @Autowired
    PmsBrandService pmsBrandService;
    /**
     * url:'/brand/list',
     *     method:'get',
     *     params:params
     */
    @GetMapping("/list")
    public CommonResult<CommonPage> getlist(@RequestParam("pageNum") Integer pageNum,
                                            @RequestParam("pageSize") Integer pageSize,
                                            @RequestParam(value = "keyword",defaultValue = "") String keyword){

       Page page= pmsBrandService.getPage(pageNum,pageSize,keyword);
        return CommonResult.success(CommonPage.restPage(page));
    }
}

