package com.tulingxueyuan.mall.modules.pms.model.dto;

import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategory;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="PmsProductCategoryDTO", description="商品类型和对应的属性")
public class PmsProductCategoryDTO extends PmsProductCategory {
    private List<Long> productAttributeIdList;
}
