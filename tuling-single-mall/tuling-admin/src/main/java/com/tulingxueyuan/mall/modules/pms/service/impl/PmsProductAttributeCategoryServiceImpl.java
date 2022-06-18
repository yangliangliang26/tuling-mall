package com.tulingxueyuan.mall.modules.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductAttribute;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductAttributeCategory;
import com.tulingxueyuan.mall.modules.pms.mapper.PmsProductAttributeCategoryMapper;
import com.tulingxueyuan.mall.modules.pms.model.dto.ProductAttributeCateDTO;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductAttributeCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 产品属性分类表 服务实现类
 * </p>
 *
 * @author XuShu
 * @since 2022-06-06
 */
@Service
public class PmsProductAttributeCategoryServiceImpl extends ServiceImpl<PmsProductAttributeCategoryMapper, PmsProductAttributeCategory> implements PmsProductAttributeCategoryService {

    @Autowired
    PmsProductAttributeCategoryMapper categoryMapper;

    @Override
    public Page<PmsProductAttributeCategory> pageList(Integer pageNum, Integer pageSize) {

        Page page=new Page(pageNum,pageSize);
        Page page1 = this.page(page);

        return page1;
    }

    @Override
    public boolean saveName(PmsProductAttributeCategory pmsProductAttributeCategory) {
      /* PmsProductAttributeCategory pmsProductAttributeCategory= new PmsProductAttributeCategory();
       pmsProductAttributeCategory.setName(name);*/
        return  this.save(pmsProductAttributeCategory);
    }

    @Override
    public boolean updateid(Long id, PmsProductAttributeCategory data) {
        UpdateWrapper<PmsProductAttributeCategory> categoryUpdateWrapper = new UpdateWrapper<>();
       /* categoryUpdateWrapper.lambda().eq(PmsProductAttributeCategory::getId,id)
                .set(PmsProductAttributeCategory::getName,data);*/
        categoryUpdateWrapper.lambda().eq(PmsProductAttributeCategory::getId,id)
                .set(PmsProductAttributeCategory::getName,data.getName());
       return this.update(categoryUpdateWrapper);
    }

    @Override
    public List<ProductAttributeCateDTO> getlistWithAttr() {

        return  categoryMapper.getlistWithAttr();
    }
}
