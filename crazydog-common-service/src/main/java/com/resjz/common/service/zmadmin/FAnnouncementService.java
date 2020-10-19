package com.resjz.common.service.zmadmin;

import com.baomidou.mybatisplus.service.IService;
import com.resjz.common.dao.zmadmin.entity.FAnnouncementEntity;
import com.resjz.common.utils.PageUtils;


import java.util.Map;

/**
 * 个人中心-消息公告
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-09-30 09:29:09
 */
public interface FAnnouncementService extends IService<FAnnouncementEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

