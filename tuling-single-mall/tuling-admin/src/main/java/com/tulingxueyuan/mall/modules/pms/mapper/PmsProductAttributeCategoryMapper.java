package com.tulingxueyuan.mall.modules.pms.mapper;

import com.tulingxueyuan.mall.modules.pms.model.PmsProductAttributeCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tulingxueyuan.mall.modules.pms.model.dto.ProductAttributeCateDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 产品属性分类表 Mapper 接口
 * </p>
 *
 * @author XuShu
 * @since 2022-06-06
 */
public interface PmsProductAttributeCategoryMapper extends BaseMapper<PmsProductAttributeCategory> {
    List<ProductAttributeCateDTO> getlistWithAttr();

/*
    @Select("select product_attribute_category_id from pms_product_attribute where id=#{id}")
    Long selectId(@Param("id") Long aLong);

    @Select("select type from pms_product_attribute where id=#{id}")
    int selecttype(@Param("id") Long aLong);*/
}
