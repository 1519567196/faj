package com.resjz.common.dao.zmadmin.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 预约
 * 
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@TableName("f_appointment")
public class FAppointmentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer itemid;
	/**
	 * 姓名
	 */
	private String memberName;
	/**
	 * 电话
	 */
	private String mobile;
	/**
	 * 省id
	 */
	private Integer provinceid;
	/**
	 * 市id
	 */
	private Integer cityid;
	/**
	 * 区id
	 */
	private Integer townid;
	/**
	 * 小区、楼盘、工装类型
	 */
	private String village;
	/**
	 * 是否回复 默认0 ，1已回复
	 */
	private Integer isReply;
	/**
	 * 1装修优惠，2工装咨询，3购房咨询，4量房咨询，5装修设计
	 */
	private Integer type;
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
	 * 设置：姓名
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	/**
	 * 获取：姓名
	 */
	public String getMemberName() {
		return memberName;
	}
	/**
	 * 设置：电话
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：电话
	 */
	public String getMobile() {
		return mobile;
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
	 * 设置：市id
	 */
	public void setCityid(Integer cityid) {
		this.cityid = cityid;
	}
	/**
	 * 获取：市id
	 */
	public Integer getCityid() {
		return cityid;
	}
	/**
	 * 设置：区id
	 */
	public void setTownid(Integer townid) {
		this.townid = townid;
	}
	/**
	 * 获取：区id
	 */
	public Integer getTownid() {
		return townid;
	}
	/**
	 * 设置：小区、楼盘、工装类型
	 */
	public void setVillage(String village) {
		this.village = village;
	}
	/**
	 * 获取：小区、楼盘、工装类型
	 */
	public String getVillage() {
		return village;
	}
	/**
	 * 设置：是否回复 默认0 ，1已回复
	 */
	public void setIsReply(Integer isReply) {
		this.isReply = isReply;
	}
	/**
	 * 获取：是否回复 默认0 ，1已回复
	 */
	public Integer getIsReply() {
		return isReply;
	}
	/**
	 * 设置：1装修优惠，2工装咨询，3购房咨询，4量房咨询，5装修设计
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：1装修优惠，2工装咨询，3购房咨询，4量房咨询，5装修设计
	 */
	public Integer getType() {
		return type;
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
