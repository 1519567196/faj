package com.resjz.common.dao.zmadmin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 房价走势记录表
 * 
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-09-17 17:54:21
 */
@TableName("f_house_price_log")
public class FHousePriceLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer itemid;
	/**
	 * 楼盘id
	 */
	private Integer houseid;

	@TableField(exist = false)
	private String houseName;

	public String getHouseName() {
		return houseName;
	}

	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}

	/**
	 * 均价
	 */
	private Double averagePrice;
	/**
	 * 起价
	 */
	private Double startPrice;
	/**
	 * 年月份
	 */
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM")
	private Date mouth;
	/**
	 * 描述
	 */
	private String remark;
	/**
	 * 添加时间
	 */
	private Date addtime;

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
	 * 设置：楼盘id
	 */
	public void setHouseid(Integer houseid) {
		this.houseid = houseid;
	}
	/**
	 * 获取：楼盘id
	 */
	public Integer getHouseid() {
		return houseid;
	}
	/**
	 * 设置：均价
	 */
	public void setAveragePrice(Double averagePrice) {
		this.averagePrice = averagePrice;
	}
	/**
	 * 获取：均价
	 */
	public Double getAveragePrice() {
		return averagePrice;
	}
	/**
	 * 设置：起价
	 */
	public void setStartPrice(Double startPrice) {
		this.startPrice = startPrice;
	}
	/**
	 * 获取：起价
	 */
	public Double getStartPrice() {
		return startPrice;
	}
	/**
	 * 设置：年月份
	 */
	public void setMouth(Date mouth) {
		this.mouth = mouth;
	}
	/**
	 * 获取：年月份
	 */
	public Date getMouth() {
		return mouth;
	}
	/**
	 * 设置：描述
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：描述
	 */
	public String getRemark() {
		return remark;
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
}
