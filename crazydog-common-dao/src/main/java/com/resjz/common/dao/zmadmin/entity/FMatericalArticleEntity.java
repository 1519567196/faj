package com.resjz.common.dao.zmadmin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 材料资讯
 * 
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@TableName("f_materical_article")
public class FMatericalArticleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer itemid;
	/**
	 * 
	 */
	private String title;
	/**
	 * 摘要
	 */
	private String subTitle;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 浏览量
	 */
	private Integer views;
	/**
	 * 所属材料品牌id
	 */
	private Integer materialBrandId;
	/**
	 * 所属材料分类id
	 */
	private Integer materialTypeId;
	/**
	 * 标签，多个用;分开
	 */
	private String tags;
	/**
	 * 
	 */
	private Date addtime;
	/**
	 * 添加人id
	 */
	private Long addUserid;
	/**
	 * 材料文章分类id
	 */
	private Integer articleTypeId;
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

	//建材品牌分类名称
	@TableField(exist = false)
	private String materialTypeName;

	public String getMaterialTypeName() {
		return materialTypeName;
	}

	public void setMaterialTypeName(String materialTypeName) {
		this.materialTypeName = materialTypeName;
	}

	@TableField(exist = false)
	private String materialTypeName2;

	public String getMaterialTypeName2() {
		return materialTypeName2;
	}

	public void setMaterialTypeName2(String materialTypeName2) {
		this.materialTypeName2 = materialTypeName2;
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
	 * 设置：
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：
	 */
	public String getTitle() {
		return title;
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
	 * 设置：所属材料品牌id
	 */
	public void setMaterialBrandId(Integer materialBrandId) {
		this.materialBrandId = materialBrandId;
	}
	/**
	 * 获取：所属材料品牌id
	 */
	public Integer getMaterialBrandId() {
		return materialBrandId;
	}
	/**
	 * 设置：所属材料分类id
	 */
	public void setMaterialTypeId(Integer materialTypeId) {
		this.materialTypeId = materialTypeId;
	}
	/**
	 * 获取：所属材料分类id
	 */
	public Integer getMaterialTypeId() {
		return materialTypeId;
	}
	/**
	 * 设置：标签，多个用;分开
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}
	/**
	 * 获取：标签，多个用;分开
	 */
	public String getTags() {
		return tags;
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
	 * 设置：材料文章分类id
	 */
	public void setArticleTypeId(Integer articleTypeId) {
		this.articleTypeId = articleTypeId;
	}
	/**
	 * 获取：材料文章分类id
	 */
	public Integer getArticleTypeId() {
		return articleTypeId;
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
