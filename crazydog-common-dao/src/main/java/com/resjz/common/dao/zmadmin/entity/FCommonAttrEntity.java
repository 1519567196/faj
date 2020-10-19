package com.resjz.common.dao.zmadmin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 属性标签
 * 
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@TableName("f_common_attr")
public class FCommonAttrEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer itemid;
	/**
	 * 属性名称
	 */
	private String title;
	/**
	 * 地区id
	 */
	private Integer areaid;
	/**
	 * 类别 1百科，2装修公司，3.楼盘
	 */
	private Integer type;
	@TableField(exist = false)
	private List<FCommonAttrValueEntity> values;

	public List<FCommonAttrValueEntity> getValues() {
		return values;
	}

	public void setValues(List<FCommonAttrValueEntity> values) {
		this.values = values;
	}

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
	 * 设置：属性名称
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：属性名称
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：地区id
	 */
	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}
	/**
	 * 获取：地区id
	 */
	public Integer getAreaid() {
		return areaid;
	}
	/**
	 * 设置：类别 1百科，2装修公司，3.楼盘
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：类别 1百科，2装修公司，3.楼盘
	 */
	public Integer getType() {
		return type;
	}
}
