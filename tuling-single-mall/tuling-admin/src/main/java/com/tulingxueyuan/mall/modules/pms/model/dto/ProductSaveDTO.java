package com.tulingxueyuan.mall.modules.pms.model.dto;

import com.tulingxueyuan.mall.modules.pms.model.*;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ProductSaveDTO商品添加、修改", description="商品修改添加传输对象")
public class ProductSaveDTO extends PmsProduct {
    //会员价格
    private List<PmsMemberPrice> memberPriceList;
    //商品满减
    private List<PmsProductFullReduction> productFullReductionList;
    //商品阶梯价格
    private List<PmsProductLadder> productLadderList;
    //SKU
    private List<PmsProductAttributeValue> productAttributeValueList;
    //SPU
    @Size(min = 1,message = "SKU至少输入一个")
    @Valid
    private List<PmsSkuStock> skuStockList;

}
