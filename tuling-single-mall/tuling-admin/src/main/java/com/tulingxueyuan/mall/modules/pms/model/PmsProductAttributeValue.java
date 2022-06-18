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
 * 存储产品参数信息的表
 * </p>
 *
 * @author XuShu
 * @since 2022-06-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("pms_product_attribute_value")
@ApiModel(value="PmsProductAttributeValue对象", description="存储产品参数信息的表")
public class PmsProductAttributeValue implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long productId;

    private Long productAttributeId;

    @ApiModelProperty(value = "手动添加规格或参数的值，参数单值，规格有多个时以逗号隔开")
    private String value;


}
