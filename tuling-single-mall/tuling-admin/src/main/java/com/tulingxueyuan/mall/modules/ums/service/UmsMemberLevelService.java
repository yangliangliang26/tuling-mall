package com.tulingxueyuan.mall.modules.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.ums.model.UmsMemberLevel;

import java.util.List;

/**
 * <p>
 * 会员等级表 服务类
 * </p>
 *
 * @author XuShu
 * @since 2022-06-16
 */
public interface UmsMemberLevelService extends IService<UmsMemberLevel> {

    List<UmsMemberLevel> listBydefaultStatus(Integer defaultStatus);
}
