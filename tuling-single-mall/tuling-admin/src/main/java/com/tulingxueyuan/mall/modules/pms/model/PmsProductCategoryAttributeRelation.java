package com.tulingxueyuan.mall.modules.pms.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类）
 * </p>
 *
 * @author XuShu
 * @since 2022-06-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("pms_product_category_attribute_relation")
@ApiModel(value="PmsProductCategoryAttributeRelation对象", description="产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类）")
public class PmsProductCategoryAttributeRelation implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long productCategoryId;

    private Long productAttributeId;


}
