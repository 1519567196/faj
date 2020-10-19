package com.resjz.common.dao.zmadmin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 回帖
 * 
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@TableName("f_repley_bbs")
public class FRepleyBbsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer itemid;
	/**
	 * 主贴id
	 */
	private Integer bbsid;
	/**
	 * 回复内容
	 */
	private String repleyContent;
	/**
	 * 回复人id
	 */
	private Integer memberid;
	/**
	 * ip地址
	 */
	private String ip;
	/**
	 * 被跟帖人id
	 */
	private Integer toMemberid;
	/**
	 * 所属地区
	 */
	private Integer areaid;
	/**
	 * 楼层
	 */
	private Integer floor;
	/**
	 * 添加时间
	 */
	private Date addtime;

	@TableField(exist = false)
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@TableField(exist = false)
	private String repleyname;

	public String getRepleyname() {
		return repleyname;
	}

	public void setRepleyname(String repleyname) {
		this.repleyname = repleyname;
	}

	@TableField(exist = false)
	private String tomembername;

	public String getTomembername() {
		return tomembername;
	}

	public void setTomembername(String tomembername) {
		this.tomembername = tomembername;
	}

	@TableField(exist = false)
	private String headimg;

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}

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
	 * 设置：主贴id
	 */
	public void setBbsid(Integer bbsid) {
		this.bbsid = bbsid;
	}
	/**
	 * 获取：主贴id
	 */
	public Integer getBbsid() {
		return bbsid;
	}
	/**
	 * 设置：回复内容
	 */
	public void setRepleyContent(String repleyContent) {
		this.repleyContent = repleyContent;
	}
	/**
	 * 获取：回复内容
	 */
	public String getRepleyContent() {
		return repleyContent;
	}
	/**
	 * 设置：回复人id
	 */
	public void setMemberid(Integer memberid) {
		this.memberid = memberid;
	}
	/**
	 * 获取：回复人id
	 */
	public Integer getMemberid() {
		return memberid;
	}
	/**
	 * 设置：ip地址
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * 获取：ip地址
	 */
	public String getIp() {
		return ip;
	}
	/**
	 * 设置：被跟帖人id
	 */
	public void setToMemberid(Integer toMemberid) {
		this.toMemberid = toMemberid;
	}
	/**
	 * 获取：被跟帖人id
	 */
	public Integer getToMemberid() {
		return toMemberid;
	}
	/**
	 * 设置：所属地区
	 */
	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}
	/**
	 * 获取：所属地区
	 */
	public Integer getAreaid() {
		return areaid;
	}
	/**
	 * 设置：楼层
	 */
	public void setFloor(Integer floor) {
		this.floor = floor;
	}
	/**
	 * 获取：楼层
	 */
	public Integer getFloor() {
		return floor;
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
