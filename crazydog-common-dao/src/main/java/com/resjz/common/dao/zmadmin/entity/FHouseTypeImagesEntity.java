package com.resjz.common.dao.zmadmin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 楼盘户型相册
 * 
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-09-16 08:26:41
 */
@TableName("f_house_type_images")
public class FHouseTypeImagesEntity implements Serializable {
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
	 * 图片地址
	 */
	private String image;
	/**
	 * 图片分类，对应f_houseimg_sort表id
	 */
	private Integer sortId;
	/**
	 * 楼盘id
	 */
	private Integer houseId;
	@TableField(exist = false)
	private String houseName;

	public String getHouseName() {
		return houseName;
	}

	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}

	/**
	 * 添加时间
	 */
	private Date addtime;
	/**
	 * 价格
	 */
	private BigDecimal price;
	/**
	 * 销售状态 1:在售；2：售罄
	 */
	private Integer saleStatus;
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

	public String getAddUserName() {
		return addUserName;
	}

	public void setAddUserName(String addUserName) {
		this.addUserName = addUserName;
	}

	/**
	 * 标签
	 */
	private String tags;
	/**
	 * 朝向
	 */
	private String chaoXiang;
	/**
	 * 面积
	 */
	private String mianJi;

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
	 * 设置：图片分类，对应f_houseimg_sort表id
	 */
	public void setSortId(Integer sortId) {
		this.sortId = sortId;
	}
	/**
	 * 获取：图片分类，对应f_houseimg_sort表id
	 */
	public Integer getSortId() {
		return sortId;
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
	 * 设置：价格
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
	 * 获取：价格
	 */
	public BigDecimal getPrice() {
		return price;
	}
	/**
	 * 设置：销售状态
	 */
	public void setSaleStatus(Integer saleStatus) {
		this.saleStatus = saleStatus;
	}
	/**
	 * 获取：销售状态
	 */
	public Integer getSaleStatus() {
		return saleStatus;
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
	 * 设置：标签
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}
	/**
	 * 获取：标签
	 */
	public String getTags() {
		return tags;
	}
	/**
	 * 设置：朝向
	 */
	public void setChaoXiang(String chaoXiang) {
		this.chaoXiang = chaoXiang;
	}
	/**
	 * 获取：朝向
	 */
	public String getChaoXiang() {
		return chaoXiang;
	}
	/**
	 * 设置：面积
	 */
	public void setMianJi(String mianJi) {
		this.mianJi = mianJi;
	}
	/**
	 * 获取：面积
	 */
	public String getMianJi() {
		return mianJi;
	}
}
