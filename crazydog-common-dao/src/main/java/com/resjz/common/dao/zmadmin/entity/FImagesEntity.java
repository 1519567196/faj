package com.resjz.common.dao.zmadmin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 装修相册
 * 
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@TableName("f_images")
public class FImagesEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer itemid;
	/**
	 * 图片名称
	 */
	private String title;
	/**
	 * 相册分类 1装修公司相册，2.楼盘相册
	 */
	private Integer type;
	/**
	 * 所属企业id
	 */
	private Integer companyid;
	@TableField(exist = false)
	private String companyName;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * 图片地址
	 */
	private String image;
	/**
	 * 排序，越大越靠前
	 */
	private Integer sort;
	/**
	 * 添加时间
	 */
	private Date addtime;
	/**
	 * 地区id
	 */
	private Integer areaid;
	/**
	 * 修改时间
	 */
	private Date updatetime;

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
	 * 设置：图片名称
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：图片名称
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：相册分类 1装修公司相册，2.楼盘相册
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：相册分类 1装修公司相册，2.楼盘相册
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：所属企业id
	 */
	public void setCompanyid(Integer companyid) {
		this.companyid = companyid;
	}
	/**
	 * 获取：所属企业id
	 */
	public Integer getCompanyid() {
		return companyid;
	}
	/**
	 * 设置：图片地址
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * 获取：图片地址
	 */
	public String getImage() {
		return image;
	}
	/**
	 * 设置：排序，越大越靠前
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	/**
	 * 获取：排序，越大越靠前
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
}
