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
 * 产品评价回复表
 * </p>
 *
 * @author XuShu
 * @since 2022-06-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("pms_comment_replay")
@ApiModel(value="PmsCommentReplay对象", description="产品评价回复表")
public class PmsCommentReplay implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long commentId;

    private String memberNickName;

    private String memberIcon;

    private String content;

    private Date createTime;

    @ApiModelProperty(value = "评论人员类型；0->会员；1->管理员")
    private Integer type;


}
