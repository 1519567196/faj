package com.resjz.common.dao.zmadmin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 装修公司装规格属性-属性值表
 * 
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-09-24 18:13:36
 */
@TableName("f_fitup_company_sku")
public class FFitupCompanySkuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer itemid;
	/**
	 * 属性值表id(关联属性值表)
	 */
	private Integer attrValueId;
	/**
	 * 属性id (关联属性表)
	 */
	private Integer attrId;
	/**
	 * 公司id(关联装修公司表)
	 */
	private Integer companyId;
	@TableField(exist = false)
	private String attr;
	@TableField(exist = false)
	private String attrValue;

	public String getAttr() {
		return attr;
	}

	public void setAttr(String attr) {
		this.attr = attr;
	}

	public String getAttrValue() {
		return attrValue;
	}

	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
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
	 * 设置：属性值表id(关联属性值表)
	 */
	public void setAttrValueId(Integer attrValueId) {
		this.attrValueId = attrValueId;
	}
	/**
	 * 获取：属性值表id(关联属性值表)
	 */
	public Integer getAttrValueId() {
		return attrValueId;
	}
	/**
	 * 设置：属性id (关联属性表)
	 */
	public void setAttrId(Integer attrId) {
		this.attrId = attrId;
	}
	/**
	 * 获取：属性id (关联属性表)
	 */
	public Integer getAttrId() {
		return attrId;
	}
	/**
	 * 设置：公司id(关联装修公司表)
	 */
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	/**
	 * 获取：公司id(关联装修公司表)
	 */
	public Integer getCompanyId() {
		return companyId;
	}
}
