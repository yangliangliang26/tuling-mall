package com.tulingxueyuan.mall.modules.pms.model;

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
 * 商品评价表
 * </p>
 *
 * @author XuShu
 * @since 2022-06-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("pms_comment")
@ApiModel(value="PmsComment对象", description="商品评价表")
public class PmsComment implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long productId;

    private String memberNickName;

    private String productName;

    @ApiModelProperty(value = "评价星数：0->5")
    private Integer star;

    @ApiModelProperty(value = "评价的ip")
    private String memberIp;

    private Date createTime;

    private Integer showStatus;

    @ApiModelProperty(value = "购买时的商品属性")
    private String productAttribute;

    private Integer collectCouont;

    private Integer readCount;

    private String content;

    @ApiModelProperty(value = "上传图片地址，以逗号隔开")
    private String pics;

    @ApiModelProperty(value = "评论用户头像")
    private String memberIcon;

    private Integer replayCount;


}
