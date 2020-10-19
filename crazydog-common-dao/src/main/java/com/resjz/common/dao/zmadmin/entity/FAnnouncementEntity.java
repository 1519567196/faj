package com.resjz.common.dao.zmadmin.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 个人中心-消息公告
 * 
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-09-30 09:29:09
 */
@TableName("f_announcement")
public class FAnnouncementEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer itemid;
	/**
	 * 公告标题
	 */
	private String announcementTitle;
	/**
	 * 已读人id，逗号分开
	 */
	private String isReadMember;
	/**
	 * 默认0 正常，1删除
	 */
	private Integer status;
	/**
	 * 公告内容
	 */
	private String announcementContent;
	/**
	 * 推送时间
	 */
	private Date addTime;

	/**
	 * 设置：主键
	 */
	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}
	/**
	 * 获取：主键
	 */
	public Integer getItemid() {
		return itemid;
	}
	/**
	 * 设置：公告标题
	 */
	public void setAnnouncementTitle(String announcementTitle) {
		this.announcementTitle = announcementTitle;
	}
	/**
	 * 获取：公告标题
	 */
	public String getAnnouncementTitle() {
		return announcementTitle;
	}
	/**
	 * 设置：已读人id，逗号分开
	 */
	public void setIsReadMember(String isReadMember) {
		this.isReadMember = isReadMember;
	}
	/**
	 * 获取：已读人id，逗号分开
	 */
	public String getIsReadMember() {
		return isReadMember;
	}
	/**
	 * 设置：默认0 正常，1删除
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：默认0 正常，1删除
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：公告内容
	 */
	public void setAnnouncementContent(String announcementContent) {
		this.announcementContent = announcementContent;
	}
	/**
	 * 获取：公告内容
	 */
	public String getAnnouncementContent() {
		return announcementContent;
	}
	/**
	 * 设置：推送时间
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	/**
	 * 获取：推送时间
	 */
	public Date getAddTime() {
		return addTime;
	}
}
