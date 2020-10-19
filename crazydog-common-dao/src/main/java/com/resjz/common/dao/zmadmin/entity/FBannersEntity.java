package com.resjz.common.dao.zmadmin.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * banner图
 * 
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@TableName("f_banners")
public class FBannersEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer itemid;
	/**
	 * 类别 1是banner图，2是城市全景图
	 */
	private Integer type;
	/**
	 * banner图链接
	 */
	private String image;
	/**
	 * vr 链接
	 */
	private String vrUrl;
	/**
	 * 跳转链接
	 */
	private String toUrl;
	/**
	 * 排序，越大越靠前
	 */
	private Integer sort;
	/**
	 * 添加时间
	 */
	private Date addtime;
	/**
	 * 属所地区id
	 */
	private Integer areaid;
	/**
	 * 添加人员id
	 */
	private Long addUserid;
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
	 * 设置：类别 1是banner图，2是城市全景图
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：类别 1是banner图，2是城市全景图
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：banner图链接
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * 获取：banner图链接
	 */
	public String getImage() {
		return image;
	}
	/**
	 * 设置：vr 链接
	 */
	public void setVrUrl(String vrUrl) {
		this.vrUrl = vrUrl;
	}
	/**
	 * 获取：vr 链接
	 */
	public String getVrUrl() {
		return vrUrl;
	}
	/**
	 * 设置：跳转链接
	 */
	public void setToUrl(String toUrl) {
		this.toUrl = toUrl;
	}
	/**
	 * 获取：跳转链接
	 */
	public String getToUrl() {
		return toUrl;
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
	 * 设置：属所地区id
	 */
	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}
	/**
	 * 获取：属所地区id
	 */
	public Integer getAreaid() {
		return areaid;
	}
	/**
	 * 设置：添加人员id
	 */
	public void setAddUserid(Long addUserid) {
		this.addUserid = addUserid;
	}
	/**
	 * 获取：添加人员id
	 */
	public Long getAddUserid() {
		return addUserid;
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
