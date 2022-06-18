package com.tulingxueyuan.mall.modules.pms.mapper;

import com.tulingxueyuan.mall.modules.pms.model.PmsProduct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tulingxueyuan.mall.modules.pms.model.dto.ProductSaveDTO;
import com.tulingxueyuan.mall.modules.pms.model.dto.ProductSaveInfoDTO;

/**
 * <p>
 * 商品信息 Mapper 接口
 * </p>
 *
 * @author XuShu
 * @since 2022-06-06
 */
public interface PmsProductMapper extends BaseMapper<PmsProduct> {

    ProductSaveInfoDTO Infolist(Long id);
}
