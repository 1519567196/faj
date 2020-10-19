package com.resjz.common.dao.zmadmin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 贴子表
 * 
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@TableName("f_bbs")
public class FBbsEntity implements Serializable {
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
	 * 户用id
	 */
	private Integer memberid;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 是否精华帖，默认0,1是
	 */
	private Integer good;
	/**
	 * 是否置顶，默认0,1是
	 */
	private Integer top;
	/**
	 * 浏览量
	 */
	private Integer views;
	/**
	 * IP地址
	 */
	private String ip;
	/**
	 * 态状，默认0,1屏蔽
	 */
	private Integer status;
	/**
	 * 添加时间
	 */
	private Date addtime;
	/**
	 * 所属地区id
	 */
	private Integer areaid;
	/**
	 * 修改时间
	 */
	private Date updatetime;
	/**
	 * 帖子类型
	 */
	private Integer typeid;

	/**
	 * 发表帖子人
	 */
	@TableField(exist = false)
	private String memberName;

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	/**
	 * 参与回答人数
	 */
	@TableField(exist = false)
	private Integer number;

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	/**
	 * 用户头像
	 */
	@TableField(exist = false)
	private String touxiang;

	public String getTouxiang() {
		return touxiang;
	}

	public void setTouxiang(String touxiang) {
		this.touxiang = touxiang;
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
	 * 设置：户用id
	 */
	public void setMemberid(Integer memberid) {
		this.memberid = memberid;
	}
	/**
	 * 获取：户用id
	 */
	public Integer getMemberid() {
		return memberid;
	}
	/**
	 * 设置：内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：是否精华帖，默认0,1是
	 */
	public void setGood(Integer good) {
		this.good = good;
	}
	/**
	 * 获取：是否精华帖，默认0,1是
	 */
	public Integer getGood() {
		return good;
	}
	/**
	 * 设置：是否置顶，默认0,1是
	 */
	public void setTop(Integer top) {
		this.top = top;
	}
	/**
	 * 获取：是否置顶，默认0,1是
	 */
	public Integer getTop() {
		return top;
	}
	/**
	 * 设置：浏览量
	 */
	public void setViews(Integer views) {
		this.views = views;
	}
	/**
	 * 获取：浏览量
	 */
	public Integer getViews() {
		return views;
	}
	/**
	 * 设置：IP地址
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * 获取：IP地址
	 */
	public String getIp() {
		return ip;
	}
	/**
	 * 设置：态状，默认0,1屏蔽
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：态状，默认0,1屏蔽
	 */
	public Integer getStatus() {
		return status;
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

	public Integer getTypeid() {
		return typeid;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}
}
