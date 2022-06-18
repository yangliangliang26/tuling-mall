package com.tulingxueyuan.mall.modules.pms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductAttribute;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductAttributeCategory;
import com.tulingxueyuan.mall.modules.pms.model.dto.ProductAttributeCateDTO;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductAttributeCategoryService;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 产品属性分类表 前端控制器
 * </p>
 *
 * @author XuShu
 * @since 2022-06-06
 */
@RestController
//@RequestMapping("/pms/pmsProductAttributeCategory")
@RequestMapping("/productAttribute/category")
public class PmsProductAttributeCategoryController {

    @Autowired
    PmsProductAttributeCategoryService pmsProductAttributeCategoryService;

    /**
     *  url:'/productAttribute/category/list',
     *     method:'get',
     *     params:params
     */

    @GetMapping("/list")
    public CommonResult<CommonPage<PmsProductAttributeCategory>> list(@RequestParam("pageNum") Integer pageNum,
                                         @RequestParam("pageSize") Integer pageSize){
       Page<PmsProductAttributeCategory> pmsProductAttributeCategories= pmsProductAttributeCategoryService.pageList(pageNum,pageSize);
       return CommonResult.success(CommonPage.restPage(pmsProductAttributeCategories));
    }
    /**
     * url:'/productAttribute/category/create',
     *     method:'post',
     *     data:data
     */
    @PostMapping("/create")
    public CommonResult create(PmsProductAttributeCategory pmsProductAttributeCategory){
     // boolean b=  pmsProductAttributeCategoryService.saveName(pmsProductAttributeCategory);
       boolean b= pmsProductAttributeCategoryService.save(pmsProductAttributeCategory);

      if (b){
          return CommonResult.success(b);
      }else{
          return CommonResult.failed();
      }
    }

    /**
     * url:'/productAttribute/category/update/'+id,
     *     method:'post',
     *     data:data
     */

    @PostMapping("/update/{id}")
    public CommonResult update(/*@PathVariable Long id,*/ PmsProductAttributeCategory data){
//       boolean b= pmsProductAttributeCategoryService.updateid(id,data);
        boolean b = pmsProductAttributeCategoryService.updateById(data);

        if (b){
           return CommonResult.success(b);
       }else {
           return CommonResult.failed();
       }
    }
    /**
     *  url:'/productAttribute/category/delete/'+id,
     *     method:'get'
     */
    @GetMapping("/delete/{id}")
    public CommonResult delete(@PathVariable Long id){
       boolean b= pmsProductAttributeCategoryService.removeById(id);
       if (b){
           return CommonResult.success(b);
       }else {
           return CommonResult.failed();
       }
    }
    /**
     *  url:'/productAttribute/category/list/withAttr',
     *     method:'get'
     */
    @GetMapping("/list/withAttr")
    public CommonResult<List<ProductAttributeCateDTO>> listWithAttr(){
      List<ProductAttributeCateDTO> productAttributeCateDTOS=  pmsProductAttributeCategoryService.getlistWithAttr();
      return CommonResult.success(productAttributeCateDTOS);
    }
}

