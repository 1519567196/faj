package com.resjz.common.dao.zmadmin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 楼盘相册表（楼盘图片分类表）
 * 
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-09-12 15:53:02
 */
@TableName("f_houseimg_sort")
public class FHouseimgSortEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer iaId;
	/**
	 * 相册名称
	 */
	private String imageAlbumName;
	/**
	 * 图片数量
	 */
	private Integer imgCount;
	/**
	 * 
	 */
	private Date addTime;
	/**
	 * 添加人id
	 */
	private Long addUserId;

	/**
	 * 1:楼盘相册；2：户型相册
	 */
	private Integer iaType;

	public Integer getIaType() {
		return iaType;
	}

	public void setIaType(Integer iaType) {
		this.iaType = iaType;
	}

	public String getAddUserName() {
		return addUserName;
	}

	public void setAddUserName(String addUserName) {
		this.addUserName = addUserName;
	}
	@TableField(exist = false)
	private String addUserName;

	/**
	 * 设置：
	 */
	public void setIaId(Integer iaId) {
		this.iaId = iaId;
	}
	/**
	 * 获取：
	 */
	public Integer getIaId() {
		return iaId;
	}
	/**
	 * 设置：相册名称
	 */
	public void setImageAlbumName(String imageAlbumName) {
		this.imageAlbumName = imageAlbumName;
	}
	/**
	 * 获取：相册名称
	 */
	public String getImageAlbumName() {
		return imageAlbumName;
	}
	/**
	 * 设置：图片数量
	 */
	public void setImgCount(Integer imgCount) {
		this.imgCount = imgCount;
	}
	/**
	 * 获取：图片数量
	 */
	public Integer getImgCount() {
		return imgCount;
	}
	/**
	 * 设置：
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	/**
	 * 获取：
	 */
	public Date getAddTime() {
		return addTime;
	}
	/**
	 * 设置：添加人id
	 */
	public void setAddUserId(Long addUserId) {
		this.addUserId = addUserId;
	}
	/**
	 * 获取：添加人id
	 */
	public Long getAddUserId() {
		return addUserId;
	}
}
