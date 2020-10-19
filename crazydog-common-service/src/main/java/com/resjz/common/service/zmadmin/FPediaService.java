package com.resjz.common.service.zmadmin;

import com.baomidou.mybatisplus.service.IService;
import com.resjz.common.dao.zmadmin.entity.FPediaEntity;
import com.resjz.common.dao.zmadmin.entity.FPediaTypeEntity;
import com.resjz.common.utils.PageUtils;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;

/**
 * 百科
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
public interface FPediaService extends IService<FPediaEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<FPediaEntity>  getIndexContents(Model model, List<FPediaTypeEntity> list2);
    List<FPediaEntity>  getIndexContents3(Model model, List<FPediaTypeEntity> list2);
}

