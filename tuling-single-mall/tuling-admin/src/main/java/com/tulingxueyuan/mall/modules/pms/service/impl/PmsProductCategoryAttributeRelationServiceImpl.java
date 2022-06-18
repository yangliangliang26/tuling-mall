package com.tulingxueyuan.mall.modules.pms.service.impl;

import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategoryAttributeRelation;
import com.tulingxueyuan.mall.modules.pms.mapper.PmsProductCategoryAttributeRelationMapper;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductCategoryAttributeRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类） 服务实现类
 * </p>
 *
 * @author XuShu
 * @since 2022-06-06
 */
@Service
public class PmsProductCategoryAttributeRelationServiceImpl extends ServiceImpl<PmsProductCategoryAttributeRelationMapper, PmsProductCategoryAttributeRelation> implements PmsProductCategoryAttributeRelationService {

}
