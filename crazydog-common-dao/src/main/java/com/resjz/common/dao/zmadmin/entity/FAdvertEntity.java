package com.resjz.common.dao.zmadmin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 广告
 * 
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@TableName("f_advert")
public class FAdvertEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 改修时间
	 */
	@TableId
	private Integer itemid;
	/**
	 * 广告位位置
	 */
	private Integer adPlaceId;
	/**
	 * 图片地址
	 */
	private String imgurl;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 跳转链接
	 */
	private String tourl;
	/**
	 * 排序 越大越靠前
	 */
	private Integer sort;
	/**
	 * 状态，默认0  1禁用
	 */
	private Integer status;
	/**
	 * 添加时时间
	 */
	private Date addtime;
	/**
	 * 操作人id
	 */
	private Long adduserid;
	/**
	 * 修改时间
	 */
	private Date updatetime;
	/**
	 * 所属地区id
	 */
	private Integer areaid;

	//分类名称
	@TableField(exist=false)
	private String typeName;

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * 设置：改修时间
	 */
	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}
	/**
	 * 获取：改修时间
	 */
	public Integer getItemid() {
		return itemid;
	}
	/**
	 * 设置：广告位位置
	 */
	public void setAdPlaceId(Integer adPlaceId) {
		this.adPlaceId = adPlaceId;
	}
	/**
	 * 获取：广告位位置
	 */
	public Integer getAdPlaceId() {
		return adPlaceId;
	}
	/**
	 * 设置：图片地址
	 */
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	/**
	 * 获取：图片地址
	 */
	public String getImgurl() {
		return imgurl;
	}
	/**
	 * 设置：标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：跳转链接
	 */
	public void setTourl(String tourl) {
		this.tourl = tourl;
	}
	/**
	 * 获取：跳转链接
	 */
	public String getTourl() {
		return tourl;
	}
	/**
	 * 设置：排序 越大越靠前
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	/**
	 * 获取：排序 越大越靠前
	 */
	public Integer getSort() {
		return sort;
	}
	/**
	 * 设置：状态，默认0  1禁用
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态，默认0  1禁用
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：添加时时间
	 */
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	/**
	 * 获取：添加时时间
	 */
	public Date getAddtime() {
		return addtime;
	}
	/**
	 * 设置：操作人id
	 */
	public void setAdduserid(Long adduserid) {
		this.adduserid = adduserid;
	}
	/**
	 * 获取：操作人id
	 */
	public Long getAdduserid() {
		return adduserid;
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
