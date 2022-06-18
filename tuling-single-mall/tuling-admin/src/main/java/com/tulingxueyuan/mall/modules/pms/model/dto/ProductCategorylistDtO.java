package com.tulingxueyuan.mall.modules.pms.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *  keyword: null,
 *     pageNum: 1,
 *     pageSize: 5,
 *     publishStatus: null,
 *     verifyStatus: null,
 *     productSn: null,
 *     productCategoryId: null,
 *     brandId: null
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="商品查询", description="商品类型和对应的属性")
public class ProductCategorylistDtO {
    //商品名称
    private String keyword;
    //当前页码
    private Integer pageNum;
    //页的大小
    private Integer pageSize;
    //上架状态
    private Integer publishStatus;
    //审核状态
    private Integer verifyStatus;
    //货架号
    private String productSn;
    //商品类型ID
    private Long productCategoryId;
    //商品品牌ID
    private Long brandId;
}
