package com.resjz.common.dao.zmadmin.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 企业站点信息
 * 
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@TableName("f_site")
public class FSiteEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer itemid;
	/**
	 * 企业名称
	 */
	private String company;
	/**
	 * ICP
	 */
	private String icp;
	/**
	 * 联系电话
	 */
	private String mobile;
	/**
	 * 公司地址
	 */
	private String address;
	/**
	 * 工作时间
	 */
	private String workTime;
	/**
	 * 企业简介
	 */
	private String briefIntroduction;
	/**
	 * 企业文化
	 */
	private String culture;
	/**
	 * 公众号二维码
	 */
	private String erCode;
	/**
	 * 备注1
	 */
	private String remark1;
	/**
	 * 备注2
	 */
	private String remark2;
	/**
	 * 图片1
	 */
	private String image1;
	/**
	 * 图片2
	 */
	private String image2;
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
	 * 设置：ICP
	 */
	public void setIcp(String icp) {
		this.icp = icp;
	}
	/**
	 * 获取：ICP
	 */
	public String getIcp() {
		return icp;
	}
	/**
	 * 设置：联系电话
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：联系电话
	 */
	public String getMobile() {
		return mobile;
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
	 * 设置：工作时间
	 */
	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}
	/**
	 * 获取：工作时间
	 */
	public String getWorkTime() {
		return workTime;
	}
	/**
	 * 设置：企业简介
	 */
	public void setBriefIntroduction(String briefIntroduction) {
		this.briefIntroduction = briefIntroduction;
	}
	/**
	 * 获取：企业简介
	 */
	public String getBriefIntroduction() {
		return briefIntroduction;
	}
	/**
	 * 设置：企业文化
	 */
	public void setCulture(String culture) {
		this.culture = culture;
	}
	/**
	 * 获取：企业文化
	 */
	public String getCulture() {
		return culture;
	}
	/**
	 * 设置：公众号二维码
	 */
	public void setErCode(String erCode) {
		this.erCode = erCode;
	}
	/**
	 * 获取：公众号二维码
	 */
	public String getErCode() {
		return erCode;
	}
	/**
	 * 设置：备注1
	 */
	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}
	/**
	 * 获取：备注1
	 */
	public String getRemark1() {
		return remark1;
	}
	/**
	 * 设置：备注2
	 */
	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}
	/**
	 * 获取：备注2
	 */
	public String getRemark2() {
		return remark2;
	}
	/**
	 * 设置：图片1
	 */
	public void setImage1(String image1) {
		this.image1 = image1;
	}
	/**
	 * 获取：图片1
	 */
	public String getImage1() {
		return image1;
	}
	/**
	 * 设置：图片2
	 */
	public void setImage2(String image2) {
		this.image2 = image2;
	}
	/**
	 * 获取：图片2
	 */
	public String getImage2() {
		return image2;
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
