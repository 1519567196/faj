package com.resjz.common.dao.zmadmin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 楼盘规格属性
 * 
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-09-15 15:46:08
 */
@TableName("f_house_sku")
public class FHouseSkuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer itemid;
	/**
	 * 楼盘id
	 */
	private Integer houseId;
	/**
	 * 属性表id(关联属性)
	 */
	private Integer attrId;
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
	 * 属性值id(关联属性值id)
	 */
	private Integer attrValueId;

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
	 * 设置：楼盘id
	 */
	public void setHouseId(Integer houseId) {
		this.houseId = houseId;
	}
	/**
	 * 获取：楼盘id
	 */
	public Integer getHouseId() {
		return houseId;
	}
	/**
	 * 设置：属性表id(关联属性)
	 */
	public void setAttrId(Integer attrId) {
		this.attrId = attrId;
	}
	/**
	 * 获取：属性表id(关联属性)
	 */
	public Integer getAttrId() {
		return attrId;
	}
	/**
	 * 设置：属性值id(关联属性值id)
	 */
	public void setAttrValueId(Integer attrValueId) {
		this.attrValueId = attrValueId;
	}
	/**
	 * 获取：属性值id(关联属性值id)
	 */
	public Integer getAttrValueId() {
		return attrValueId;
	}
}
