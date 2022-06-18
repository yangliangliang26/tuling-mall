package com.tulingxueyuan.mall.modules.ums.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tulingxueyuan.mall.modules.ums.mapper.UmsMemberLevelMapper;
import com.tulingxueyuan.mall.modules.ums.model.UmsMemberLevel;
import com.tulingxueyuan.mall.modules.ums.service.UmsMemberLevelService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 会员等级表 服务实现类
 * </p>
 *
 * @author XuShu
 * @since 2022-06-16
 */
@Service
public class UmsMemberLevelServiceImpl extends ServiceImpl<UmsMemberLevelMapper, UmsMemberLevel> implements UmsMemberLevelService {

    @Override
    public List<UmsMemberLevel> listBydefaultStatus(Integer defaultStatus) {
        QueryWrapper<UmsMemberLevel> levelQueryWrapper = new QueryWrapper<>();
        levelQueryWrapper.lambda().eq(UmsMemberLevel::getDefaultStatus,defaultStatus);
        List<UmsMemberLevel> list = this.list(levelQueryWrapper);
        return list;
    }
}
