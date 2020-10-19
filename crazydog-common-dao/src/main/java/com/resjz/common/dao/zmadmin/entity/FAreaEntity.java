package com.resjz.common.dao.zmadmin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.List;

/**
 * 地区表
 * 
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-09-11 10:14:18
 */
@TableName("f_area")
public class FAreaEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId
	private Integer areaId;
	/**
	 * 地区名
	 */
	private String areaName;
	/**
	 * 地区名
	 */
	private Integer areaParentId;
	/**
	 * 排序
	 */
	private Integer areaSort;
	/**
	 * 深度从1
	 */
	private Integer areaDeep;
	/**
	 * 备注
	 */
	private String areaRegion;


	public List<FAreaEntity> getChildList() {
		return childList;
	}

	public void setChildList(List<FAreaEntity> childList) {
		this.childList = childList;
	}

	@TableField(exist = false)
	private List<FAreaEntity>  childList;

	/**
	 * 设置：主键id
	 */
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	/**
	 * 获取：主键id
	 */
	public Integer getAreaId() {
		return areaId;
	}
	/**
	 * 设置：地区名
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	/**
	 * 获取：地区名
	 */
	public String getAreaName() {
		return areaName;
	}
	/**
	 * 设置：地区名
	 */
	public void setAreaParentId(Integer areaParentId) {
		this.areaParentId = areaParentId;
	}
	/**
	 * 获取：地区名
	 */
	public Integer getAreaParentId() {
		return areaParentId;
	}
	/**
	 * 设置：排序
	 */
	public void setAreaSort(Integer areaSort) {
		this.areaSort = areaSort;
	}
	/**
	 * 获取：排序
	 */
	public Integer getAreaSort() {
		return areaSort;
	}
	/**
	 * 设置：深度从1
	 */
	public void setAreaDeep(Integer areaDeep) {
		this.areaDeep = areaDeep;
	}
	/**
	 * 获取：深度从1
	 */
	public Integer getAreaDeep() {
		return areaDeep;
	}
	/**
	 * 设置：备注
	 */
	public void setAreaRegion(String areaRegion) {
		this.areaRegion = areaRegion;
	}
	/**
	 * 获取：备注
	 */
	public String getAreaRegion() {
		return areaRegion;
	}
}
