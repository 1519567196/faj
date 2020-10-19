package com.resjz.common.dao.zmadmin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 百科分类
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-10-09 08:21:13
 */
@TableName("f_pedia_type")
public class FPediaTypeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Integer itemid;
    /**
     * 分类名称
     */
    private String title;
    /**
     * 父id
     */
    private Integer parentid;
    @TableField(exist = false)
    private List<FPediaTypeEntity> childList;

    public List<FPediaTypeEntity> getChildList() {
        return childList;
    }

    public void setChildList(List<FPediaTypeEntity> childList) {
        this.childList = childList;
    }

    /**
     * 序排，越大越靠前
     */
    private Integer sort;
    /**
     * 1房产百科，2装修百科
     */
    private Integer subject;
    @TableField(exist = false)
    private String subjectDetail;

    public String getSubjectDetail() {
        return subjectDetail;
    }

    public void setSubjectDetail(String subjectDetail) {
        this.subjectDetail = subjectDetail;
    }

    /**
     * 修改时间
     */
    private Date updatetime;
    /**
     * 所属地区id
     */
    private Integer areaid;
    /**
     * 装修百科类型{1：装修流程；2：装修攻略；3：建材百科；4：搭配指南}
     */
    private Integer zxbkType;

    @TableField(exist = false)
    private String parentName;

    /**
     * 设置：
     */
    public void setItemid(Integer itemid) {
        this.itemid = itemid;
    }

    /**
     * 获取：
     */
    public Integer getItemid() {
        return itemid;
    }

    /**
     * 设置：分类名称
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取：分类名称
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置：父id
     */
    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    /**
     * 获取：父id
     */
    public Integer getParentid() {
        return parentid;
    }

    /**
     * 设置：序排，越大越靠前
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取：序排，越大越靠前
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置：1房产百科，2装修百科
     */
    public void setSubject(Integer subject) {
        this.subject = subject;
    }

    /**
     * 获取：1房产百科，2装修百科
     */
    public Integer getSubject() {
        return subject;
    }

    /**
     * 设置：修改时间
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * 获取：修改时间
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * 设置：所属地区id
     */
    public void setAreaid(Integer areaid) {
        this.areaid = areaid;
    }

    /**
     * 获取：所属地区id
     */
    public Integer getAreaid() {
        return areaid;
    }

    /**
     * 设置：装修百科类型{1：装修流程；2：装修攻略；3：建材百科；4：搭配指南}
     */
    public void setZxbkType(Integer zxbkType) {
        this.zxbkType = zxbkType;
    }

    /**
     * 获取：装修百科类型{1：装修流程；2：装修攻略；3：建材百科；4：搭配指南}
     */
    public Integer getZxbkType() {
        return zxbkType;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
