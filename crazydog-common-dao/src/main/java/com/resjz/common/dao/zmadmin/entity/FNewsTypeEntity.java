package com.resjz.common.dao.zmadmin.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 房产资讯分类
 * 
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@TableName("f_news_type")
public class FNewsTypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer typeid;
	/**
	 * 分类名称
	 */
	private String typeName;
	/**
	 * 排序 越发越靠前
	 */
	private Integer sort;
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
	 * 设置：
	 */
	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}
	/**
	 * 获取：
	 */
	public Integer getTypeid() {
		return typeid;
	}
	/**
	 * 设置：分类名称
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	/**
	 * 获取：分类名称
	 */
	public String getTypeName() {
		return typeName;
	}
	/**
	 * 设置：排序 越发越靠前
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	/**
	 * 获取：排序 越发越靠前
	 */
	public Integer getSort() {
		return sort;
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
}
