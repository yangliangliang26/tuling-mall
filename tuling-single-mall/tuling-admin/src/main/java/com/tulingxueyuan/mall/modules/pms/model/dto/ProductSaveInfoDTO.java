package com.tulingxueyuan.mall.modules.pms.model.dto;


import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ProductSaveDTO商品修改数据初始化", description="商品初始化传输对象")
public class ProductSaveInfoDTO extends ProductSaveDTO{
    private Long cateParentId;
}
