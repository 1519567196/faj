package com.resjz.common.dao.zmadmin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 我家方案-小区
 * 
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@TableName("f_programme_village")
public class FProgrammeVillageEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer itemid;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 
	 */
	private String address;
	/**
	 * 主图
	 */
	private String image;
	/**
	 * 单价
	 */
	private Integer price;
	/**
	 * 省id
	 */
	private Integer provinceid;
	/**
	 * 
	 */
	private Integer cityid;
	/**
	 * 
	 */
	private Integer townid;
	@TableField(exist = false)
	private String townName; //省市区全拼

	public String getTownName() {
		return townName;
	}

	public void setTownName(String townName) {
		this.townName = townName;
	}



	/**
	 * 所属地区id
	 */
	private Integer areaid;
	@TableField(exist = false)
	private String areaName;

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	/**
	 * 添加时间
	 */
	private Date addtime;
	/**
	 * 添加人员id
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
	 * 设置：
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：主图
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * 获取：主图
	 */
	public String getImage() {
		return image;
	}
	/**
	 * 设置：单价
	 */
	public void setPrice(Integer price) {
		this.price = price;
	}
	/**
	 * 获取：单价
	 */
	public Integer getPrice() {
		return price;
	}
	/**
	 * 设置：省id
	 */
	public void setProvinceid(Integer provinceid) {
		this.provinceid = provinceid;
	}
	/**
	 * 获取：省id
	 */
	public Integer getProvinceid() {
		return provinceid;
	}
	/**
	 * 设置：
	 */
	public void setCityid(Integer cityid) {
		this.cityid = cityid;
	}
	/**
	 * 获取：
	 */
	public Integer getCityid() {
		return cityid;
	}
	/**
	 * 设置：
	 */
	public void setTownid(Integer townid) {
		this.townid = townid;
	}
	/**
	 * 获取：
	 */
	public Integer getTownid() {
		return townid;
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

	public Long getAddUserid() {
		return addUserid;
	}

	public void setAddUserid(Long addUserid) {
		this.addUserid = addUserid;
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
