package com.resjz.common.dao.zmadmin.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 案例规格属性表
 * 
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@TableName("f_case_sku")
public class FCaseSkuEntity implements Serializable {
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
	 * 案例id(关联案例表)
	 */
	private Integer caseId;

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
	 * 设置：案例id(关联案例表)
	 */
	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
	}
	/**
	 * 获取：案例id(关联案例表)
	 */
	public Integer getCaseId() {
		return caseId;
	}
}
