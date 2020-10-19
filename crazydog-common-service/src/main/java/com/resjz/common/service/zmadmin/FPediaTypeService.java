package com.resjz.common.service.zmadmin;

import com.baomidou.mybatisplus.service.IService;
import com.resjz.common.dao.zmadmin.entity.FPediaTypeEntity;
import com.resjz.common.utils.PageUtils;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;

/**
 * 百科分类
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
public interface FPediaTypeService extends IService<FPediaTypeEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<FPediaTypeEntity>  getMainList(Model model);


}

