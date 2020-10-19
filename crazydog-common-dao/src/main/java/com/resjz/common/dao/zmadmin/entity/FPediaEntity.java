package com.resjz.common.dao.zmadmin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 百科
 * 
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@TableName("f_pedia")
public class FPediaEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer itemid;
	/**
	 * 标题名称
	 */
	private String title;
	/**
	 *  点击量
	 */
	private Integer views;
	/**
	 * 缩略图
	 */
	private String imgUrl;
	/**
	 * 来源
	 */
	private String from;
	/**
	 * 标签名称，多个用;隔开
	 */
	private String tags;
	/**
	 * 点赞数
	 */
	private Integer goods;
	/**
	 * 摘要
	 */
	private String subTitle;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 认默0，  1删除
	 */
	private Integer isDelete;
	/**
	 * 1房产百科，2装修百科
	 */
	private Integer subject;
	/**
	 * 所属分类
	 */
	private Integer typeid;
	/**
	 * 所属分类集  例：2,3,4,6,7..
	 */
	private String subCateIds;
	/**
	 * 是否推荐 默认0,1推荐
	 */
	private Integer recommend;
	/**
	 * 添加时间
	 */
	private Date addtime;
	/**
	 * 添加人id
	 */
	private Integer addUserid;
	/**
	 * 所属地区id
	 */
	private Integer areaid;
	/**
	 * 修改时间
	 */
	private Date updatetime;

	//分类名称
	@TableField(exist=false)
	private String typeName;

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
	 * 设置：分类名称
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：分类名称
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置： 点击量
	 */
	public void setViews(Integer views) {
		this.views = views;
	}
	/**
	 * 获取： 点击量
	 */
	public Integer getViews() {
		return views;
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
	 * 设置：标签名称，多个用;隔开
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}
	/**
	 * 获取：标签名称，多个用;隔开
	 */
	public String getTags() {
		return tags;
	}
	/**
	 * 设置：点赞数
	 */
	public void setGoods(Integer goods) {
		this.goods = goods;
	}
	/**
	 * 获取：点赞数
	 */
	public Integer getGoods() {
		return goods;
	}
	/**
	 * 设置：摘要
	 */
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	/**
	 * 获取：摘要
	 */
	public String getSubTitle() {
		return subTitle;
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
	 * 设置：认默0，  1删除
	 */
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	/**
	 * 获取：认默0，  1删除
	 */
	public Integer getIsDelete() {
		return isDelete;
	}
	/**
	 * 设置：1房产百科，2装修百科
	 */
	public void setSubject(Integer subject) {
		this.subject = subject;
	}
	/**
	 * 获取：1房产百科，2装修百科
	 */
	public Integer getSubject() {
		return subject;
	}
	/**
	 * 设置：所属分类集  例：2,3,4,6,7..
	 */
	public void setSubCateIds(String subCateIds) {
		this.subCateIds = subCateIds;
	}
	/**
	 * 获取：所属分类集  例：2,3,4,6,7..
	 */
	public String getSubCateIds() {
		return subCateIds;
	}
	/**
	 * 设置：是否推荐 默认0,1推荐
	 */
	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}
	/**
	 * 获取：是否推荐 默认0,1推荐
	 */
	public Integer getRecommend() {
		return recommend;
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
	public void setAddUserid(Integer addUserid) {
		this.addUserid = addUserid;
	}
	/**
	 * 获取：添加人id
	 */
	public Integer getAddUserid() {
		return addUserid;
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

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
