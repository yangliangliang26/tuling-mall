package com.tulingxueyuan.mall.modules.pms.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.pms.mapper.PmsProductAttributeCategoryMapper;
import com.tulingxueyuan.mall.modules.pms.mapper.PmsProductCategoryAttributeRelationMapper;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductAttribute;
import com.tulingxueyuan.mall.modules.pms.mapper.PmsProductAttributeMapper;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductAttributeCategory;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategoryAttributeRelation;
import com.tulingxueyuan.mall.modules.pms.model.dto.ProductAttrInfoDTO;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductAttributeCategoryService;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductAttributeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 商品属性参数表 服务实现类
 * </p>
 *
 * @author XuShu
 * @since 2022-06-06
 */
@Service
public class PmsProductAttributeServiceImpl extends ServiceImpl<PmsProductAttributeMapper, PmsProductAttribute> implements PmsProductAttributeService {

    @Autowired
    PmsProductAttributeCategoryService pmsProductAttributeCategoryService;
    @Autowired
    PmsProductAttributeMapper categoryMapper;
    @Autowired
    PmsProductCategoryAttributeRelationMapper attributeRelationMapper;

    @Override
    public Page pagebyid(Long cid, Integer type, Integer pageNum, Integer pageSize) {
        Page page=new Page(pageNum,pageSize);
        QueryWrapper<PmsProductAttribute> attributeQueryWrapper = new QueryWrapper<>();
        attributeQueryWrapper.lambda()
                .eq(PmsProductAttribute::getProductAttributeCategoryId,cid)
                .eq(PmsProductAttribute::getType,type);
        Page page1 = this.page(page, attributeQueryWrapper);
        return page1;
    }

    @Override
    @Transactional
    public boolean create(PmsProductAttribute pmsProductAttribute) {
       boolean b= this.save(pmsProductAttribute);
       if (b){
           UpdateWrapper<PmsProductAttributeCategory> categoryUpdateWrapper = new UpdateWrapper<>();
           if (pmsProductAttribute.getType()==0){
               categoryUpdateWrapper.lambda().setSql("attribute_count=attribute_count+1")
                       .eq(PmsProductAttributeCategory::getId,pmsProductAttribute.getProductAttributeCategoryId());


           }else{
               categoryUpdateWrapper.lambda().setSql("param_count=param_count+1")
                       .eq(PmsProductAttributeCategory::getId,pmsProductAttribute.getProductAttributeCategoryId());



           }
           pmsProductAttributeCategoryService.update(categoryUpdateWrapper);
       }
        return b;
    }

    @Override
    @Transactional
    public boolean removeByidsAttribute(List<Long> ids) {
      // Long id= categoryMapper.selectId(ids.get(0));
//        int type = categoryMapper.selecttype(ids.get(0));
        if (CollectionUtil.isEmpty(ids)){
            return false;
        }
        PmsProductAttribute pmsProductAttribute=null;
        for (Long i : ids) {
           pmsProductAttribute= this.getById(i);
           if (pmsProductAttribute!=null){
               break;
           }
        }
        int length = categoryMapper.deleteBatchIds(ids);

        if (length>0&&pmsProductAttribute!=null){
            UpdateWrapper<PmsProductAttributeCategory> categoryUpdateWrapper = new UpdateWrapper<>();
            if (pmsProductAttribute.getType()==0){
                categoryUpdateWrapper.lambda().eq(PmsProductAttributeCategory::getId,pmsProductAttribute.getProductAttributeCategoryId())
                        .setSql("attribute_count=attribute_count-"+length);
            }else if (pmsProductAttribute.getType()==1){
                categoryUpdateWrapper.lambda().eq(PmsProductAttributeCategory::getId,pmsProductAttribute.getProductAttributeCategoryId())
                        .setSql("param_count=param_count-"+length);
            }

            return pmsProductAttributeCategoryService.update(categoryUpdateWrapper);
        }
        return length>0;
    }

    @Override
    public List<ProductAttrInfoDTO> getProductAttrInfo(Long productCategoryId) {
        return  attributeRelationMapper.getProductAttrInfo(productCategoryId);
    }

}
