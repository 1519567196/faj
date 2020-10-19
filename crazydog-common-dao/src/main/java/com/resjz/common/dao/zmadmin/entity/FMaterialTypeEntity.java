package com.resjz.common.dao.zmadmin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 建材分类
 * 
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-09-23 09:27:47
 */
@TableName("f_material_type")
public class FMaterialTypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer itemid;
	/**
	 * 分类名称
	 */
	private String type;
	/**
	 * 添加时间
	 */
	private Date addtime;
	/**
	 * 修改时间
	 */
	private Date updatetime;
	/**
	 * 所属地区id
	 */
	private Integer areaid;
	/**
	 * 主分类id
	 */
	private Integer mainMateriaId;
	@TableField(exist = false)
	private String mainMateriaName;

	public String getMainMateriaName() {
		return mainMateriaName;
	}

	public void setMainMateriaName(String mainMateriaName) {
		this.mainMateriaName = mainMateriaName;
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
	 * 设置：分类名称
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：分类名称
	 */
	public String getType() {
		return type;
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
	 * 设置：主分类id
	 */
	public void setMainMateriaId(Integer mainMateriaId) {
		this.mainMateriaId = mainMateriaId;
	}
	/**
	 * 获取：主分类id
	 */
	public Integer getMainMateriaId() {
		return mainMateriaId;
	}
}
