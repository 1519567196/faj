package com.resjz.common.dao.zmadmin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 楼盘点评表
 * 
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-09-17 17:54:21
 */
@TableName("f_house_comment")
public class FHouseCommentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer itemid;
	/**
	 * 综合评分
	 */
	private Double mainScore;
	/**
	 * 价格评分
	 */
	private Double priceScore;
	/**
	 * 地段评分
	 */
	private Double placeScore;
	/**
	 * 交通
	 */
	private Double trafficScore;
	/**
	 * 配套
	 */
	private Double matchingScore;
	/**
	 * 环境评分
	 */
	private Double environScore;
	/**
	 * 点评内容
	 */
	private String content;
	/**
	 * 是否考虑购买 1有兴趣，2带对比，3暂不考虑
	 */
	private Integer isThink;
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
	 * 评价人id
	 */
	private Integer memberid;

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	@TableField(exist = false)
	private String memberName;
	/**
	 * 图片
	 */
	private String image;
	/**
	 * 添加时间
	 */
	private Date addtime;
	/**
	 * 是否热门，默认0,1热门
	 */
	private Integer isHot;

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
	 * 设置：综合评分
	 */
	public void setMainScore(Double mainScore) {
		this.mainScore = mainScore;
	}
	/**
	 * 获取：综合评分
	 */
	public Double getMainScore() {
		return mainScore;
	}
	/**
	 * 设置：价格评分
	 */
	public void setPriceScore(Double priceScore) {
		this.priceScore = priceScore;
	}
	/**
	 * 获取：价格评分
	 */
	public Double getPriceScore() {
		return priceScore;
	}
	/**
	 * 设置：地段评分
	 */
	public void setPlaceScore(Double placeScore) {
		this.placeScore = placeScore;
	}
	/**
	 * 获取：地段评分
	 */
	public Double getPlaceScore() {
		return placeScore;
	}
	/**
	 * 设置：交通
	 */
	public void setTrafficScore(Double trafficScore) {
		this.trafficScore = trafficScore;
	}
	/**
	 * 获取：交通
	 */
	public Double getTrafficScore() {
		return trafficScore;
	}
	/**
	 * 设置：配套
	 */
	public void setMatchingScore(Double matchingScore) {
		this.matchingScore = matchingScore;
	}
	/**
	 * 获取：配套
	 */
	public Double getMatchingScore() {
		return matchingScore;
	}
	/**
	 * 设置：环境评分
	 */
	public void setEnvironScore(Double environScore) {
		this.environScore = environScore;
	}
	/**
	 * 获取：环境评分
	 */
	public Double getEnvironScore() {
		return environScore;
	}
	/**
	 * 设置：点评内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：点评内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：是否考虑购买 1有兴趣，2带对比，3暂不考虑
	 */
	public void setIsThink(Integer isThink) {
		this.isThink = isThink;
	}
	/**
	 * 获取：是否考虑购买 1有兴趣，2带对比，3暂不考虑
	 */
	public Integer getIsThink() {
		return isThink;
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
	 * 设置：评价人id
	 */
	public void setMemberid(Integer memberid) {
		this.memberid = memberid;
	}
	/**
	 * 获取：评价人id
	 */
	public Integer getMemberid() {
		return memberid;
	}
	/**
	 * 设置：图片
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * 获取：图片
	 */
	public String getImage() {
		return image;
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
	 * 设置：是否热门，默认0,1热门
	 */
	public void setIsHot(Integer isHot) {
		this.isHot = isHot;
	}
	/**
	 * 获取：是否热门，默认0,1热门
	 */
	public Integer getIsHot() {
		return isHot;
	}
}
