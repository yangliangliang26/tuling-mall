package com.tulingxueyuan.mall.modules.pms.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="商品分类下列列表", description="初始化筛选属性，商品类型和属性")
public class productCategoryDto {
    private Long id;
    private String name;
    private List<ProductAttributeCateDTO> children;
}
