package com.tulingxueyuan.mall.modules.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.pms.model.PmsBrand;
import com.tulingxueyuan.mall.modules.pms.mapper.PmsBrandMapper;
import com.tulingxueyuan.mall.modules.pms.service.PmsBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 品牌表 服务实现类
 * </p>
 *
 * @author XuShu
 * @since 2022-06-06
 */
@Service
public class PmsBrandServiceImpl extends ServiceImpl<PmsBrandMapper, PmsBrand> implements PmsBrandService {


    @Override
    public Page getPage(Integer pageNum, Integer pageSize, String keyword) {
        Page page = new Page(pageNum, pageSize);

        QueryWrapper<PmsBrand> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(keyword)){
            queryWrapper.lambda().like(PmsBrand::getName,keyword);
        }
        queryWrapper.lambda().orderByAsc(PmsBrand::getSort);
        return this.page(page,queryWrapper);
    }
}
