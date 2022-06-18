package com.tulingxueyuan.mall.modules.pms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductAttribute;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategory;
import com.tulingxueyuan.mall.modules.pms.model.dto.ProductAttrInfoDTO;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductAttributeService;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 商品属性参数表 前端控制器
 * </p>
 *
 * @author XuShu
 * @since 2022-06-06
 */
@RestController
//@RequestMapping("/pms/pmsProductAttribute")
@RequestMapping("/productAttribute")
public class PmsProductAttributeController {

    @Autowired
    PmsProductAttributeService pmsProductAttributeService;
    /**查询商品属性列表和参数列表(type=0 属性列表   type=1 参数列表)
     *  url:'/productAttribute/list/'+cid,
     *     method:'get',
     *     params:params
     */
    @GetMapping("/list/{cid}")
    public CommonResult<CommonPage> list(@PathVariable Long cid,
                                                              @RequestParam("pageNum") Integer pageNum,
                                                              @RequestParam("pageSize") Integer pageSize,
                                                              @RequestParam("type") Integer type){
       Page page= pmsProductAttributeService.pagebyid(cid,type,pageNum,pageSize);
       return CommonResult.success(CommonPage.restPage(page));


    }
    /** 添加商品属性
     *  url:'/productAttribute/create',
     *     method:'post',
     *     data:data
     */
    @PostMapping("/create")
    public CommonResult create(@RequestBody  PmsProductAttribute pmsProductAttribute){
       // boolean save = pmsProductAttributeService.save(pmsProductAttribute);
        boolean save=pmsProductAttributeService.create(pmsProductAttribute);
        if (save){
            return CommonResult.success(save);
        }else {
            return CommonResult.failed();
        }
    }

    /** 删除商品属性
     *  url:'/productAttribute/delete',
     *     method:'post',
     *     data:data
     */
    @PostMapping("/delete")
    public CommonResult delete(@RequestParam("ids") List<Long> ids){
      // boolean b= pmsProductAttributeService.removeByIds(ids);
       boolean b =pmsProductAttributeService.removeByidsAttribute(ids);
       if (b){
           return CommonResult.success(b);
       }else {
           return CommonResult.failed();
       }

    }

    /** 通过id查询商品列表
     *  url:'/productAttribute/'+id,
     *     method:'get'
     */
    @GetMapping("/{id}")
    public CommonResult<PmsProductAttribute> listbyid(@PathVariable Long id){
        PmsProductAttribute pmsProductAttribute = pmsProductAttributeService.getById(id);
        return CommonResult.success(pmsProductAttribute);
    }

    /** 修改商品属性
     *  url:'/productAttribute/update/'+id,
     *     method:'post',
     *     data:data
     */
    @PostMapping("/update/{id}")
    public CommonResult update(@RequestBody PmsProductAttribute pmsProductAttribute){
        boolean b = pmsProductAttributeService.updateById(pmsProductAttribute);
        if (b){
            return CommonResult.success(b);
        }else {
            return CommonResult.failed();
        }
    }
    /**
     * url:'/productAttribute/attrInfo/'+productCategoryId,
     *     method:'get'
     */

    @GetMapping("/attrInfo/{productCategoryId}")
    public CommonResult getProductAttrInfo(@PathVariable Long productCategoryId){

      List<ProductAttrInfoDTO> productAttrInfoDTOS= pmsProductAttributeService.getProductAttrInfo(productCategoryId);
        return CommonResult.success(productAttrInfoDTOS);
    }
}

