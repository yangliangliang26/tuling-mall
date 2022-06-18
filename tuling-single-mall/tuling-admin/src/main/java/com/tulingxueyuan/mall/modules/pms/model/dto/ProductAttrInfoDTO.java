package com.tulingxueyuan.mall.modules.pms.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ProductAttrInfoDTO", description="初始化筛选属性，商品类型和属性")
public class ProductAttrInfoDTO {
    //属性ID
    private Long attributeId;
    //类型ID
    private Long attributeCategoryId;
}
