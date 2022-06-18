package com.tulingxueyuan.mall.modules.pms.service;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.pms.model.PmsProduct;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.pms.model.dto.ProductCategorylistDtO;
import com.tulingxueyuan.mall.modules.pms.model.dto.ProductSaveDTO;
import com.tulingxueyuan.mall.modules.pms.model.dto.ProductSaveInfoDTO;

import java.util.List;

/**
 * <p>
 * 商品信息 服务类
 * </p>
 *
 * @author XuShu
 * @since 2022-06-06
 */
public interface PmsProductService extends IService<PmsProduct> {

    Page listpage(ProductCategorylistDtO productCategorylistDtO);

    boolean updateStatus(List<Long> ids);

    boolean updatepublishStatus(List<Long> ids, Integer publishStatus);

    boolean updatenewStatus(List<Long> ids, Integer newStatus);

    boolean updaterecommendStatus(List<Long> ids, Integer recommendStatus);

    boolean updateStatusbyid(List<Long> ids, Integer publishStatus,SFunction<PmsProduct, ?>  getStatus);

    boolean createsave(ProductSaveDTO productSaveDTO);

    ProductSaveInfoDTO updateInfolist(Long id);

    boolean updatelist(ProductSaveDTO productSaveDTO);
}
