package com.resjz.common.dao.zmadmin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 * 
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@TableName("f_member")
public class FMemberEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer memberId;

	/**
	 * 是否自动登录
	 */
	@TableField(exist = false)
	private  boolean  auto;

	public boolean getAuto() {
		return auto;
	}

	public void setAuto(boolean auto) {
		this.auto = auto;
	}

	/**
	 * 
	 */
	private String memberName;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 头像
	 */
	private String headImg;
	/**
	 * 性别   1男 2女
	 */
	private Integer sex;
	/**
	 * 昵称
	 */
	private String nick;
	/**
	 * 发帖数量
	 */
	private Integer invitationCount;
	/**
	 * 状态，默认0,1禁用
	 */
	private Integer status;
	/**
	 * 
	 */
	private Date addtime;
	/**
	 * 所在地区id
	 */
	private Integer areaid;

	/**
	 * 设置：
	 */
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	/**
	 * 获取：
	 */
	public Integer getMemberId() {
		return memberId;
	}
	/**
	 * 设置：
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	/**
	 * 获取：
	 */
	public String getMemberName() {
		return memberName;
	}
	/**
	 * 设置：手机号
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：手机号
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置：密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取：密码
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置：头像
	 */
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	/**
	 * 获取：头像
	 */
	public String getHeadImg() {
		return headImg;
	}
	/**
	 * 设置：性别   1男 2女
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	/**
	 * 获取：性别   1男 2女
	 */
	public Integer getSex() {
		return sex;
	}
	/**
	 * 设置：昵称
	 */
	public void setNick(String nick) {
		this.nick = nick;
	}
	/**
	 * 获取：昵称
	 */
	public String getNick() {
		return nick;
	}
	/**
	 * 设置：发帖数量
	 */
	public void setInvitationCount(Integer invitationCount) {
		this.invitationCount = invitationCount;
	}
	/**
	 * 获取：发帖数量
	 */
	public Integer getInvitationCount() {
		return invitationCount;
	}
	/**
	 * 设置：状态，默认0,1禁用
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态，默认0,1禁用
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：
	 */
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	/**
	 * 获取：
	 */
	public Date getAddtime() {
		return addtime;
	}
	/**
	 * 设置：所在地区id
	 */
	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}
	/**
	 * 获取：所在地区id
	 */
	public Integer getAreaid() {
		return areaid;
	}
}
