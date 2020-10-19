package com.resjz.common.dao.zmadmin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 属性规格值
 * 
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-09-23 11:28:45
 */
@TableName("f_common_attr_value")
public class FCommonAttrValueEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 属性值id
	 */
	@TableId
	private Integer valueId;
	/**
	 * 属性表id
	 */
	private Integer attrId;
	@TableField(exist = false)
	private String attrName;
	@TableField(exist = false)
	private Integer type;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	/**
	 * 属性值
	 */
	private String title;
	/**
	 * 添加时间
	 */
	private Date addtime;

	/**
	 * 设置：属性值id
	 */
	public void setValueId(Integer valueId) {
		this.valueId = valueId;
	}
	/**
	 * 获取：属性值id
	 */
	public Integer getValueId() {
		return valueId;
	}
	/**
	 * 设置：属性表id
	 */
	public void setAttrId(Integer attrId) {
		this.attrId = attrId;
	}
	/**
	 * 获取：属性表id
	 */
	public Integer getAttrId() {
		return attrId;
	}
	/**
	 * 设置：属性值
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：属性值
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：添加时间
	 */
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	/**
	 * 获取：添加时间
	 */
	public Date getAddtime() {
		return addtime;
	}
}
