package com.tulingxueyuan.mall.modules.pms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductAttribute;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.pms.model.dto.ProductAttrInfoDTO;

import java.util.List;

/**
 * <p>
 * 商品属性参数表 服务类
 * </p>
 *
 * @author XuShu
 * @since 2022-06-06
 */
public interface PmsProductAttributeService extends IService<PmsProductAttribute> {


    Page pagebyid(Long cid, Integer type, Integer pageNum, Integer pageSize);

    boolean create(PmsProductAttribute pmsProductAttribute);

    boolean removeByidsAttribute(List<Long> ids);

    List<ProductAttrInfoDTO> getProductAttrInfo(Long productCategoryId);
}
