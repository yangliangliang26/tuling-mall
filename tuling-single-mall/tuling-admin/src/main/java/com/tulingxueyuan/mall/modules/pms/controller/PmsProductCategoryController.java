package com.tulingxueyuan.mall.modules.pms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategory;
import com.tulingxueyuan.mall.modules.pms.model.dto.PmsProductCategoryDTO;
import com.tulingxueyuan.mall.modules.pms.model.dto.ProductAttributeCateDTO;
import com.tulingxueyuan.mall.modules.pms.model.dto.productCategoryDto;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 产品分类 前端控制器
 * </p>
 *
 * @author XuShu
 * @since 2022-06-06
 */
@RestController
@RequestMapping("/productCategory")
public class PmsProductCategoryController {

    @Autowired
    PmsProductCategoryService pmsProductCategoryService;
    /**
     *  url:'/productCategory/list/'+parentId,
     *     method:'get',
     *     params:{
     *          pageNum: 1,
     *           pageSize: 5
     *     }
     *
     */
    @GetMapping("/list/{parentId}")
    public CommonResult<CommonPage<PmsProductCategory>> list(@PathVariable Long parentId,
                                                             @RequestParam(value = "pageNum") Integer pageNum,
                                                             @RequestParam(value = "pageSize") Integer pageSize){
    Page<PmsProductCategory> categoryPage=  pmsProductCategoryService.pageList(parentId,pageNum,pageSize);
    return CommonResult.success(CommonPage.restPage(categoryPage),"");
    }

    /**
     *  url:'/productCategory/update/navStatus',
     *     method:'post',
     *     data:data{
     *         data.append('ids',ids);
     *         data.append('navStatus',row.navStatus);
     *     }
     */
    @PostMapping("/update/navStatus")
    public CommonResult updateStatus(@RequestParam("ids") List<Long> ids,
                                     @RequestParam("navStatus") Integer navStatus){

      boolean b =  pmsProductCategoryService.updateStatus(ids,navStatus);
        if (b){
            return CommonResult.success(b);
        }else {
            return CommonResult.failed();
        }
    }

    /**
     *  url:'/productCategory/update/showStatus',
     *     method:'post',
     *     data:data{
     *         data.append('ids',ids);
     *         data.append('showStatus',row.showStatus);
     *     }
     */

    @PostMapping("/update/showStatus")
    public CommonResult updateShowStatus(@RequestParam("ids") List<Long> ids,
                                         @RequestParam("showStatus") Integer showStatus){
       Integer b= pmsProductCategoryService.updateShowStatus(ids,showStatus);
       if (b==1){
           return CommonResult.success(true);
       }else {
           return CommonResult.failed();
       }
    }
    /**
     *  url:'/productCategory/delete/'+id,
     *     method:'post'
     */
    @PostMapping("/delete/{id}")
    public CommonResult delete(@PathVariable("id") Long id){
        boolean b = pmsProductCategoryService.removeById(id);
        if (b){
            return  CommonResult.success(b);
        }else {
            return CommonResult.failed();
        }
    }
    /**
     *   url:'/productCategory/create',
     *     method:'post',
     *     data:data
     */
    @PostMapping("/create")
    public CommonResult create(@RequestBody PmsProductCategoryDTO data){
       boolean b= pmsProductCategoryService.saveDTO(data);
       if (b){
           return CommonResult.success(b);
       }else {
           return CommonResult.failed();
       }
    }

    /**
     * url:'/productCategory/update/'+id,
     *     method:'post',
     *     data:data
     */
    @PostMapping("/update/{id}")
    public CommonResult update(@RequestBody PmsProductCategoryDTO pmsProductCategoryDTO
                              /* @PathVariable("id") Long id*/){
     //  boolean b= pmsProductCategoryService.updateById(pmsProductCategory);
       boolean b= pmsProductCategoryService.updateAttr(pmsProductCategoryDTO);
       if (b){
           return CommonResult.success(b);
       }else {
           return CommonResult.failed();
       }
    }
    /**
     *  url:'/productCategory/getProductCate'+id,
     *     method:'get'
     */
    @GetMapping("/{id}")
    public CommonResult<PmsProductCategory> getProductCate(@PathVariable("id") Long id){
        PmsProductCategory category = pmsProductCategoryService.getById(id);
        return CommonResult.success(category);
    }
   /* *//**
     *  url:'/productCategory/update/'+id,
     *     method:'post',
     *     data:data
     *//*

    @PostMapping("/update/{id}")
    public CommonResult update1(@PathVariable("id") Long id,
                               @RequestBody PmsProductCategory pmsProductCategory){
        boolean b= pmsProductCategoryService.save(pmsProductCategory);
        if (b){
            return CommonResult.success(b);
        }else {
            return CommonResult.failed();
        }
    }*/
    /**
     * url:'/productCategory/list/withChildren',
     *     method:'get'
     */
    @GetMapping("/list/withChildren")
    public CommonResult getwithChildren(){
      List<productCategoryDto>  list=pmsProductCategoryService.getwithChildren();
      return CommonResult.success(list);
    }
}

