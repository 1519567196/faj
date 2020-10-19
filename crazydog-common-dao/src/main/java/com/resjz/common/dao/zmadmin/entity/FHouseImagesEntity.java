package com.resjz.common.dao.zmadmin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 楼盘相册
 * 
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-09-12 11:18:39
 */
@TableName("f_house_images")
public class FHouseImagesEntity implements Serializable {
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
	 * 相册id（图片分类）
	 */
	private Integer sortId;

	/**
	 * 排序
	 */
	private Integer rank;

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	@TableField(exist = false)
	private String imageAlbumName;

	public String getImageAlbumName() {
		return imageAlbumName;
	}

	public void setImageAlbumName(String imageAlbumName) {
		this.imageAlbumName = imageAlbumName;
	}

	/**
	 * 图片地址
	 */
	private String image;
	/**
	 * 楼盘id
	 */
	private Integer houseId;


	public String getHouseName() {
		return houseName;
	}

	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}

	@TableField(exist = false)
	private String houseName;
	/**
	 * 添加时间
	 */
	private Date addtime;
	/**
	 * 所属地区id
	 */
	private Integer areaid;
	/**
	 * 添加人id
	 */
	private Long addUserid;
	@TableField(exist = false)
	private String addUserName;
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
	 * 设置：相册id（图片分类）
	 */
	public void setSortId(Integer sortId) {
		this.sortId = sortId;
	}
	/**
	 * 获取：相册id（图片分类）
	 */
	public Integer getSortId() {
		return sortId;
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
	 * 设置：添加人id
	 */
	public void setAddUserid(Long addUserid) {
		this.addUserid = addUserid;
	}
	/**
	 * 获取：添加人id
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

	public String getAddUserName() {
		return addUserName;
	}

	public void setAddUserName(String addUserName) {
		this.addUserName = addUserName;
	}
}
