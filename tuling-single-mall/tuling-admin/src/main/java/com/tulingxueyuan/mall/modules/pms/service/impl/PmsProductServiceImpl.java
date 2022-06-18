package com.tulingxueyuan.mall.modules.pms.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.pms.model.PmsProduct;
import com.tulingxueyuan.mall.modules.pms.mapper.PmsProductMapper;
import com.tulingxueyuan.mall.modules.pms.model.dto.ProductCategorylistDtO;
import com.tulingxueyuan.mall.modules.pms.model.dto.ProductSaveDTO;
import com.tulingxueyuan.mall.modules.pms.model.dto.ProductSaveInfoDTO;
import com.tulingxueyuan.mall.modules.pms.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;

/**
 * <p>
 * 商品信息 服务实现类
 * </p>
 *
 * @author XuShu
 * @since 2022-06-06
 */
@Service
public class PmsProductServiceImpl extends ServiceImpl<PmsProductMapper, PmsProduct> implements PmsProductService {
    @Autowired
    //会员价格
    PmsMemberPriceService priceService;
    @Autowired
    //满减价格
    PmsProductFullReductionService productFullReductionService;
    @Autowired
    //阶梯价格
    PmsProductLadderService ladderService;
    @Autowired
    PmsProductAttributeValueService valueService;
    @Autowired
    PmsSkuStockService skuStockService;
    @Autowired
    PmsProductMapper productMapper;

    @Override
    public Page listpage(ProductCategorylistDtO pcld) {
        Page page=new Page(pcld.getPageNum(),pcld.getPageSize());
        QueryWrapper<PmsProduct> queryWrapper = new QueryWrapper<>();
        LambdaQueryWrapper<PmsProduct> lambda = queryWrapper.lambda();
        //商品名称
        if (!StrUtil.isBlank(pcld.getKeyword())){
            lambda.like(PmsProduct::getName,pcld.getKeyword());
        }
        //商品货号
        if (!StrUtil.isBlank(pcld.getProductSn())){
            lambda.like(PmsProduct::getProductSn,pcld.getProductSn());
        }
        //上架状态
        if (pcld.getPublishStatus()!=null){
            lambda.eq(PmsProduct::getPublishStatus,pcld.getPublishStatus());
        }
        //审核状态
        if (pcld.getVerifyStatus()!=null){
            lambda.eq(PmsProduct::getVerifyStatus,pcld.getVerifyStatus());
        }
        //商品类型
        if (pcld.getProductCategoryId()!=null){
            lambda.eq(PmsProduct::getProductCategoryId,pcld.getProductCategoryId());
        }
        //品牌类型
        if(pcld.getBrandId()!=null){
            lambda.eq(PmsProduct::getBrandId,pcld.getBrandId());
        }
        lambda.orderByAsc(PmsProduct::getSort).eq(PmsProduct::getDeleteStatus,0);
        return this.page(page,queryWrapper);
    }

    @Override
    public boolean updateStatus(List<Long> ids) {
        UpdateWrapper<PmsProduct> updateWrapper = new UpdateWrapper<>();
        if (ids!=null&&ids.size()>0){
            updateWrapper.lambda().in(PmsProduct::getId,ids).set(PmsProduct::getDeleteStatus,1);
        }

        return this.update(updateWrapper);
    }

    @Override
    public boolean updatepublishStatus(List<Long> ids, Integer publishStatus) {
        UpdateWrapper<PmsProduct> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().in(PmsProduct::getId,ids).set(PmsProduct::getPublishStatus,publishStatus);
        return this.update(updateWrapper);
    }

    @Override
    public boolean updatenewStatus(List<Long> ids, Integer newStatus) {
        UpdateWrapper<PmsProduct> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().in(PmsProduct::getId,ids).set(PmsProduct::getNewStatus,newStatus);
        return this.update(updateWrapper);
    }

    @Override
    public boolean updaterecommendStatus(List<Long> ids, Integer recommendStatus) {
        UpdateWrapper<PmsProduct> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().in(PmsProduct::getId,ids).set(PmsProduct::getRecommandStatus,recommendStatus);
        return this.update(updateWrapper);
    }

    @Override

    public boolean updateStatusbyid(List<Long> ids, Integer publishStatus,SFunction<PmsProduct, ?> getStatus) {
        UpdateWrapper<PmsProduct> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().in(PmsProduct::getId,ids).set(getStatus,publishStatus);
        return this.update(updateWrapper);
    }

    @Override
    public boolean createsave(ProductSaveDTO productSaveDTO) {
        PmsProduct pmsProduct=productSaveDTO;
        boolean save = this.save(pmsProduct);
        if (save){
            switch (pmsProduct.getPromotionType()){
                //会员价格
                case 2:  saveMethod(productSaveDTO.getMemberPriceList(),pmsProduct.getId(),priceService);break;
                //满减价格
                case 4:  saveMethod(productSaveDTO.getProductFullReductionList(),pmsProduct.getId(),productFullReductionService);break;
                //阶梯价格
                case 3: saveMethod(productSaveDTO.getProductLadderList(),pmsProduct.getId(),ladderService);break;
            }

            //SKU
            saveMethod(productSaveDTO.getSkuStockList(),pmsProduct.getId(),skuStockService);
            //SPU
            saveMethod(productSaveDTO.getProductAttributeValueList(),pmsProduct.getId(),valueService);

        }
        return save;
    }

    /**
     * 商品编辑数据初始化
     * @param id
     * @return
     */

    @Override
    public ProductSaveInfoDTO updateInfolist(Long id) {
        return productMapper.Infolist(id);
    }

    /**
     * 保存修改的商品数据
     * @param id
     * @param productSaveDTO
     * @return
     */
    @Override
    @Transactional
    public boolean updatelist(ProductSaveDTO productSaveDTO) {
        PmsProduct pmsProduct=productSaveDTO;
        boolean save = this.updateById(pmsProduct);
        if (save){
            switch (pmsProduct.getPromotionType()){
                //会员价格
                case 2:
                    deleteMethod(pmsProduct.getId(),priceService);
                    saveMethod(productSaveDTO.getMemberPriceList(),pmsProduct.getId(),priceService);break;
                //满减价格
                case 4:
                    deleteMethod(pmsProduct.getId(),productFullReductionService);
                    saveMethod(productSaveDTO.getProductFullReductionList(),pmsProduct.getId(),productFullReductionService);break;
                //阶梯价格
                case 3:
                    deleteMethod(pmsProduct.getId(),ladderService);
                    saveMethod(productSaveDTO.getProductLadderList(),pmsProduct.getId(),ladderService);break;
            }

            deleteMethod(pmsProduct.getId(),skuStockService);
            deleteMethod(pmsProduct.getId(),valueService);

            //SKU
            saveMethod(productSaveDTO.getSkuStockList(),pmsProduct.getId(),skuStockService);
            //SPU
            saveMethod(productSaveDTO.getProductAttributeValueList(),pmsProduct.getId(),valueService);

        }
        return save;
    }

    /**
     * 删除
     * @param parentId
     * @param service
     */
    public void deleteMethod(Long parentId, IService service){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("product_id",parentId);
        service.remove(queryWrapper);
    }

    /**
     * 促销类型保存
     * @param list
     * @param parentId
     * @param service
     */
    public void saveMethod(List list, Long parentId, IService service){
        if (CollectionUtils.isEmpty(list)){ return ; }
        try{

            for (Object o : list) {
                Method setProductId = o.getClass().getMethod("setProductId",Long.class);
                Method setId = o.getClass().getMethod("setId", Long.class);
                setId.invoke(o,null);
                setProductId.invoke(o,parentId);
            }
            service.saveBatch(list);
        }catch (Exception e){

        }

    }
}
