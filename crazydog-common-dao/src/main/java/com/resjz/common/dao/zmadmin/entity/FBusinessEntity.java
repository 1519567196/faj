package com.resjz.common.dao.zmadmin.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 商务合作
 * 
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@TableName("f_business")
public class FBusinessEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer itemid;
	/**
	 * 联系人姓名
	 */
	private String memberName;
	/**
	 * 电话
	 */
	private String memberMobile;
	/**
	 * 公司名称
	 */
	private String company;
	/**
	 * 公司地址
	 */
	private String address;
	/**
	 * 省id
	 */
	private Integer privinceid;
	/**
	 * 市id
	 */
	private Integer cityid;
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
	 * 设置：联系人姓名
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	/**
	 * 获取：联系人姓名
	 */
	public String getMemberName() {
		return memberName;
	}
	/**
	 * 设置：电话
	 */
	public void setMemberMobile(String memberMobile) {
		this.memberMobile = memberMobile;
	}
	/**
	 * 获取：电话
	 */
	public String getMemberMobile() {
		return memberMobile;
	}
	/**
	 * 设置：公司名称
	 */
	public void setCompany(String company) {
		this.company = company;
	}
	/**
	 * 获取：公司名称
	 */
	public String getCompany() {
		return company;
	}
	/**
	 * 设置：公司地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：公司地址
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：省id
	 */
	public void setPrivinceid(Integer privinceid) {
		this.privinceid = privinceid;
	}
	/**
	 * 获取：省id
	 */
	public Integer getPrivinceid() {
		return privinceid;
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
