package com.tulingxueyuan.mall.modules.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategory;
import com.tulingxueyuan.mall.modules.pms.mapper.PmsProductCategoryMapper;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategoryAttributeRelation;
import com.tulingxueyuan.mall.modules.pms.model.dto.PmsProductCategoryDTO;
import com.tulingxueyuan.mall.modules.pms.model.dto.ProductAttributeCateDTO;
import com.tulingxueyuan.mall.modules.pms.model.dto.productCategoryDto;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductCategoryAttributeRelationService;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 产品分类 服务实现类
 * </p>
 *
 * @author XuShu
 * @since 2022-06-06
 */
@Service
public class PmsProductCategoryServiceImpl extends ServiceImpl<PmsProductCategoryMapper, PmsProductCategory> implements PmsProductCategoryService {

    @Autowired
    PmsProductCategoryMapper categoryMapper;

    @Autowired
    PmsProductCategoryAttributeRelationService relationService;

    @Override
    public Page<PmsProductCategory> pageList(Long parentId, Integer pageNum, Integer pageSize) {
        Page page=new Page(pageNum,pageSize);

        QueryWrapper<PmsProductCategory> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().
                eq(PmsProductCategory::getParentId,parentId);
        Page page1 = this.page(page, queryWrapper);
        return page1;
    }

    @Override
    public boolean updateStatus(List<Long> ids, Integer navStatus) {
        UpdateWrapper<PmsProductCategory> updateWrapper=new UpdateWrapper<>();
        updateWrapper.lambda().in(PmsProductCategory::getId,ids).set(PmsProductCategory::getNavStatus,navStatus);

        return this.update(updateWrapper);
    }

    @Override
    public Integer updateShowStatus(List<Long> ids, Integer showStatus) {
        /*UpdateWrapper<PmsProductCategory> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().in(PmsProductCategory::getId,ids).set(PmsProductCategory::getShowStatus,showStatus);
        this.update(updateWrapper);*/
        UpdateWrapper<PmsProductCategory> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().in(PmsProductCategory::getId,ids).set(PmsProductCategory::getShowStatus,showStatus);
        int update = categoryMapper.update(new PmsProductCategory(), updateWrapper);
        return update;
    }

    @Override
    @Transactional
    public boolean saveDTO(PmsProductCategoryDTO data) {
        PmsProductCategory pmsProductCategory=new PmsProductCategory();
        BeanUtils.copyProperties(data,pmsProductCategory);

        pmsProductCategory.setProductCount(0);
        if (pmsProductCategory.getParentId()==0){
            pmsProductCategory.setLevel(0);
        }else {
            pmsProductCategory.setLevel(1);
        }
        boolean saveI = this.save(pmsProductCategory);

        boolean b = isSaveBatch(data, pmsProductCategory);

        return b&&saveI;
    }

    @Override
    @Transactional
    public boolean updateAttr(PmsProductCategoryDTO pmsProductCategoryDTO) {
        PmsProductCategory pmsProductCategory = new PmsProductCategory();
        BeanUtils.copyProperties(pmsProductCategoryDTO,pmsProductCategory);
        boolean update = this.updateById(pmsProductCategory);
        //删除
        QueryWrapper<PmsProductCategoryAttributeRelation> relationQueryWrapper = new QueryWrapper<>();
        relationQueryWrapper.lambda()
                .eq(PmsProductCategoryAttributeRelation::getProductCategoryId,pmsProductCategoryDTO.getId());
        boolean remove = relationService.remove(relationQueryWrapper);
        boolean saveBatch = isSaveBatch(pmsProductCategoryDTO, pmsProductCategory);

        return update&&saveBatch&&remove;
    }

    @Override
    public List<productCategoryDto> getwithChildren() {
       return categoryMapper.getwithChildren();
    }
    //将商品类型和商品属性的联系保存

    private boolean isSaveBatch(PmsProductCategoryDTO pmsProductCategoryDTO, PmsProductCategory pmsProductCategory) {
        //保存
        List<PmsProductCategoryAttributeRelation> list = new ArrayList<>();
        for (Long attrId : pmsProductCategoryDTO.getProductAttributeIdList()) {
            PmsProductCategoryAttributeRelation attributeRelation = new PmsProductCategoryAttributeRelation();
            attributeRelation.setProductCategoryId(pmsProductCategory.getId());
            attributeRelation.setProductAttributeId(attrId);
            list.add(attributeRelation);
        }

        return relationService.saveBatch(list);
    }

}
