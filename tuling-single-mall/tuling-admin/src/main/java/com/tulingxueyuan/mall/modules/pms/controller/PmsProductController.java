package com.tulingxueyuan.mall.modules.pms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.pms.model.PmsProduct;
import com.tulingxueyuan.mall.modules.pms.model.dto.ProductCategorylistDtO;
import com.tulingxueyuan.mall.modules.pms.model.dto.ProductSaveDTO;
import com.tulingxueyuan.mall.modules.pms.model.dto.ProductSaveInfoDTO;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 商品信息 前端控制器
 * </p>
 *
 * @author XuShu
 * @since 2022-06-06
 */
@RestController
@RequestMapping("/product")
public class PmsProductController {

    @Autowired
    PmsProductService pmsProductService;

    /**
     *  url:'/product/list',
     *     method:'get',
     *     params:params{
     *          keyword: null,
     *     pageNum: 1,
     *     pageSize: 5,
     *     publishStatus: null,
     *     verifyStatus: null,
     *     productSn: null,
     *     productCategoryId: null,
     *     brandId: null
     *     }
     * @return
     */

    @ApiOperation("商品列表")
    @GetMapping("/list")
    public CommonResult<CommonPage> list(ProductCategorylistDtO productCategorylistDtO){
       Page page=pmsProductService.listpage(productCategorylistDtO);

        return CommonResult.success(CommonPage.restPage(page));
    }
    /** 删除(逻辑删除 )
     * url:'/product/update/deleteStatus',
     *     method:'post',
     *     params:params
     */

    @PostMapping("/update/deleteStatus")
    public CommonResult deleteStatus(@RequestParam("ids") List<Long> ids
                                    /* @RequestParam("deleteStatus") Integer deleteStatus*/){

//       boolean b= pmsProductService.updateStatus(ids);
        boolean b= pmsProductService.removeByIds(ids);
       if (b){
           return CommonResult.success(b);
       }else {
           return CommonResult.failed();
       }
    }
    /** 修改上架状态
     * url:'/product/update/publishStatus',
     *     method:'post',
     *     params:params
     */
    @PostMapping("/update/publishStatus")
    public CommonResult updatepublishStatus(@RequestParam("publishStatus") Integer publishStatus,
                                            @RequestParam("ids") List<Long> ids){

       // boolean b=pmsProductService.updatepublishStatus(ids,publishStatus);
       boolean b= pmsProductService.updateStatusbyid(ids,publishStatus,PmsProduct::getPublishStatus);
        if (b){
            return CommonResult.success(b);
        }else {
            return CommonResult.failed();
        }
    }

    /** 修改新品状态
     * url:'/product/update/newStatus',
     *     method:'post',
     *     params:params
     */
    @PostMapping("/update/newStatus")
    public CommonResult updatenewStatus(@RequestParam("newStatus") Integer newStatus,
                                            @RequestParam("ids") List<Long> ids){

        boolean b=pmsProductService.updateStatusbyid(ids,newStatus,PmsProduct::getNewStatus);
        if (b){
            return CommonResult.success(b);
        }else {
            return CommonResult.failed();
        }
    }

    /** 修改推荐状态
     * url:'/product/update/recommendStatus',
     *     method:'post',
     *     params:params
     */
    @PostMapping("/update/recommendStatus")
    public CommonResult updaterecommendStatus(@RequestParam("recommendStatus") Integer recommendStatus,
                                        @RequestParam("ids") List<Long> ids){

        boolean b=pmsProductService.updateStatusbyid(ids,recommendStatus,PmsProduct::getRecommandStatus);
        if (b){
            return CommonResult.success(b);
        }else {
            return CommonResult.failed();
        }
    }
    /** 商品添加和修改
     *  url:'/product/create',
     *     method:'post',
     *     data:data
     */
    @PostMapping("/create")
    public CommonResult create(@RequestBody ProductSaveDTO productSaveDTO){
       boolean b= pmsProductService.createsave(productSaveDTO);
        if (b){
            return CommonResult.success(b);
        }else {
            return CommonResult.failed();
        }
    }
    /** 商品编辑初始化数据
     *  url:'/product/updateInfo/'+id,
     *     method:'get',
     */
    @GetMapping("/updateInfo/{id}")
    public CommonResult updateInfo(@PathVariable("id") Long id){
        ProductSaveInfoDTO productSaveDTO=pmsProductService.updateInfolist(id);
        return CommonResult.success(productSaveDTO);
    }
    /** 保存修改商品数据
     *  url:'/product/update/'+id,
     *     method:'post',
     *     data:data
     */
    @PostMapping("/update/{id}")
    public CommonResult update(@RequestBody ProductSaveDTO productSaveDTO){
        boolean b=pmsProductService.updatelist(productSaveDTO);
        if (b){
            return CommonResult.success(b);
        }else {
            return CommonResult.failed();
        }

    }
}

