package com.resjz.common.dao.zmadmin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 房产资讯
 * 
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@TableName("f_news")
public class FNewsEntity implements Serializable {
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
	 * 分类id
	 */
	private Integer typeid;
	/**
	 * 主图url
	 */
	private String image;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 来源
	 */
	private String from;
	/**
	 * 浏览量
	 */
	private Integer views;
	/**
	 * 是否推荐，默认0 ， 1推荐
	 */
	private Integer isRecommend;
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
	 * 所属地区id
	 */
	private Integer areaid;
	//分类名称
	@TableField(exist = false)
	private String typeName;

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
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
	 * 设置：分类id
	 */
	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}
	/**
	 * 获取：分类id
	 */
	public Integer getTypeid() {
		return typeid;
	}
	/**
	 * 设置：主图url
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * 获取：主图url
	 */
	public String getImage() {
		return image;
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
	 * 设置：来源
	 */
	public void setFrom(String from) {
		this.from = from;
	}
	/**
	 * 获取：来源
	 */
	public String getFrom() {
		return from;
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
	 * 设置：是否推荐，默认0 ， 1推荐
	 */
	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}
	/**
	 * 获取：是否推荐，默认0 ， 1推荐
	 */
	public Integer getIsRecommend() {
		return isRecommend;
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
