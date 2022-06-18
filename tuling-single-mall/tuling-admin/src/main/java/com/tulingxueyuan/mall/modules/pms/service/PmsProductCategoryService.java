package com.tulingxueyuan.mall.modules.pms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.pms.model.dto.PmsProductCategoryDTO;
import com.tulingxueyuan.mall.modules.pms.model.dto.ProductAttributeCateDTO;
import com.tulingxueyuan.mall.modules.pms.model.dto.productCategoryDto;

import java.util.List;

/**
 * <p>
 * 产品分类 服务类
 * </p>
 *
 * @author XuShu
 * @since 2022-06-06
 */
public interface PmsProductCategoryService extends IService<PmsProductCategory> {

    Page<PmsProductCategory> pageList(Long parentId, Integer pageNum, Integer pageSize);

    boolean updateStatus(List<Long> ids, Integer navStatus);

    Integer updateShowStatus(List<Long> ids, Integer showStatus);

    boolean saveDTO(PmsProductCategoryDTO data);

    boolean updateAttr(PmsProductCategoryDTO pmsProductCategoryupdateDTO);

    List<productCategoryDto> getwithChildren();
}
