package com.resjz.common.dao.zmadmin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 我家方案-全景
 * 
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@TableName("f_programme_vr")
public class FProgrammeVrEntity implements Serializable {
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
	 * vr链接
	 */
	private String vrUrl;

	/**
	 * 权重（数值越大越靠前）
	 */
	private Integer sort;

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * 是否首页推荐  1：是   ；0否（默认）
	 */
	private Integer indexpush;

	public Integer getIndexpush() {
		return indexpush;
	}

	public void setIndexpush(Integer indexpush) {
		this.indexpush = indexpush;
	}

	/**
	 * vr首图
	 */
	private String vrImg;
	/**
	 * 属所户型id(关联我加方案户型表)
	 */
	private Integer programmeHouseId;
	@TableField(exist = false)
	private String programmeHouseName;

	public String getProgrammeHouseName() {
		return programmeHouseName;
	}

	public void setProgrammeHouseName(String programmeHouseName) {
		this.programmeHouseName = programmeHouseName;
	}

	/**
	 * 添加时间
	 */
	private Date addtime;
	/**
	 * 加添人id
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
	 * 所属地区id
	 */
	private Integer areaid;

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
	 * 设置：vr链接
	 */
	public void setVrUrl(String vrUrl) {
		this.vrUrl = vrUrl;
	}
	/**
	 * 获取：vr链接
	 */
	public String getVrUrl() {
		return vrUrl;
	}
	/**
	 * 设置：vr首图
	 */
	public void setVrImg(String vrImg) {
		this.vrImg = vrImg;
	}
	/**
	 * 获取：vr首图
	 */
	public String getVrImg() {
		return vrImg;
	}
	/**
	 * 设置：属所户型id(关联我加方案户型表)
	 */
	public void setProgrammeHouseId(Integer programmeHouseId) {
		this.programmeHouseId = programmeHouseId;
	}
	/**
	 * 获取：属所户型id(关联我加方案户型表)
	 */
	public Integer getProgrammeHouseId() {
		return programmeHouseId;
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
	 * 设置：加添人id
	 */
	public void setAddUserid(Long addUserid) {
		this.addUserid = addUserid;
	}
	/**
	 * 获取：加添人id
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
