package com.tulingxueyuan.mall.modules.pms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductAttributeCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.pms.model.dto.ProductAttributeCateDTO;

import java.util.List;

/**
 * <p>
 * 产品属性分类表 服务类
 * </p>
 *
 * @author XuShu
 * @since 2022-06-06
 */
public interface PmsProductAttributeCategoryService extends IService<PmsProductAttributeCategory> {

    Page<PmsProductAttributeCategory> pageList(Integer pageNum, Integer pageSize);

    boolean saveName(PmsProductAttributeCategory name);

    boolean updateid(Long id, PmsProductAttributeCategory data);

    List<ProductAttributeCateDTO> getlistWithAttr();
}
