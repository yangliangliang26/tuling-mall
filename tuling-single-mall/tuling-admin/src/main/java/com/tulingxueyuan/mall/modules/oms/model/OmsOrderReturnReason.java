package com.tulingxueyuan.mall.modules.oms.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 退货原因表
 * </p>
 *
 * @author XuShu
 * @since 2022-06-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("oms_order_return_reason")
@ApiModel(value="OmsOrderReturnReason对象", description="退货原因表")
public class OmsOrderReturnReason implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "退货类型")
    private String name;

    private Integer sort;

    @ApiModelProperty(value = "状态：0->不启用；1->启用")
    private Integer status;

    @ApiModelProperty(value = "添加时间")
    private Date createTime;


}
