package com.tulingxueyuan.mall.modules.pms.mapper;

import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategoryAttributeRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tulingxueyuan.mall.modules.pms.model.dto.ProductAttrInfoDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类） Mapper 接口
 * </p>
 *
 * @author XuShu
 * @since 2022-06-06
 */
public interface PmsProductCategoryAttributeRelationMapper extends BaseMapper<PmsProductCategoryAttributeRelation> {

    List<ProductAttrInfoDTO> getProductAttrInfo(@Param("cid") Long productCategoryId);
}
