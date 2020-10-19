package com.resjz.common.dao.zmadmin.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 联系我们
 * 
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@TableName("f_link_us")
public class FLinkUsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer itemid;
	/**
	 * 地区
	 */
	private String city;
	/**
	 * 
	 */
	private String mobile;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 主图
	 */
	private String image;
	/**
	 * 企业名称
	 */
	private String company;
	/**
	 * 服务时间  默认‘周一至周日’
	 */
	private String workTime;

	public String getWorkTime() {
		return workTime;
	}

	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}

	/**
	 * 添加时间
	 */
	private Date addtime;
	/**
	 * 添加人id
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
	 * 设置：地区
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * 获取：地区
	 */
	public String getCity() {
		return city;
	}
	/**
	 * 设置：
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置：地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：地址
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
	 * 设置：企业名称
	 */
	public void setCompany(String company) {
		this.company = company;
	}
	/**
	 * 获取：企业名称
	 */
	public String getCompany() {
		return company;
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
}
