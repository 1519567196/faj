package com.resjz.common.dao.zmadmin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 装修公司案例(家装案例)
 * 
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-09-23 17:03:05
 */
@TableName("f_fitup_company_cases")
public class FFitupCompanyCasesEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer caseId;
	/**
	 * 案例名称
	 */
	private String title;
	/**
	 * 主图
	 */
	private String mainImg;
	/**
	 * 标签 多个用;隔开
	 */
	private String tags;
	/**
	 * 所属企业id
	 */
	private Integer companyId;
	@TableField(exist = false)
	private String companyName;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * 面积(对应属性值表id)
	 */
	private Integer squareid;
	@TableField(exist = false)
	private String squareNum;

	public String getSquareNum() {
		return squareNum;
	}

	public void setSquareNum(String squareNum) {
		this.squareNum = squareNum;
	}

	/**
	 * 户型1.1室，2 ,3 ,4 ,5.4室以上（对应属性值表id）
	 */
	private Integer houseTypeid;
	@TableField(exist = false)
	private String houseType;

	public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}



	/**
	 * 风格，对应属性值表id
	 */
	private Integer styleid;
	@TableField(exist = false)
	private String style;

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	/**
	 * 商圈  对应属性值表id
	 */
	private Integer businessDistrictId;
	@TableField(exist = false)
	private String businessDistrict;

	public String getBusinessDistrict() {
		return businessDistrict;
	}

	public void setBusinessDistrict(String businessDistrict) {
		this.businessDistrict = businessDistrict;
	}

	/**
	 * 公装所需的类型  （对应属性值表id）
	 */
	private Integer publicTypeId;
	@TableField(exist = false)
	private String publicType;

	public String getPublicType() {
		return publicType;
	}

	public void setPublicType(String publicType) {
		this.publicType = publicType;
	}

	/**
	 * 预算 123456（对应属性值表id）
	 */
	private Integer moneyid;
	@TableField(exist = false)
	private String money;

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	/**
	 * 公装还是家装：1公装；2家装
	 */
	private Integer isPublic;
	/**
	 * 是否推荐，默认0,1推荐
	 */
	private Integer recommend;
	/**
	 * 浏览量
	 */
	private Integer views;
	/**
	 * 地区id
	 */
	private Integer areaid;
	@TableField(exist = false)
	private String areaName;

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	/**
	 * 
	 */
	private Date addtime;
	/**
	 * 添加人id
	 */
	private Long adduserid;
	@TableField(exist = false)
	private String adduserName;

	public String getAdduserName() {
		return adduserName;
	}

	public void setAdduserName(String adduserName) {
		this.adduserName = adduserName;
	}

	/**
	 * 修改时间
	 */
	private Date updatetime;
	/**
	 * 案例详情
	 */
	private String content;

	/**
	 * 摘要
	 */
	private String abstractContent;

	public String getAbstractContent() {
		return abstractContent;
	}

	public void setAbstractContent(String abstractContent) {
		this.abstractContent = abstractContent;
	}

	/**
	 * 纬度
	 */
	private String latitude;
	/**
	 * 经度
	 */
	private String longitude;

	/**
	 * 设置：
	 */
	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
	}
	/**
	 * 获取：
	 */
	public Integer getCaseId() {
		return caseId;
	}
	/**
	 * 设置：案例名称
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：案例名称
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：主图
	 */
	public void setMainImg(String mainImg) {
		this.mainImg = mainImg;
	}
	/**
	 * 获取：主图
	 */
	public String getMainImg() {
		return mainImg;
	}
	/**
	 * 设置：标签 多个用;隔开
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}
	/**
	 * 获取：标签 多个用;隔开
	 */
	public String getTags() {
		return tags;
	}
	/**
	 * 设置：所属企业id
	 */
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	/**
	 * 获取：所属企业id
	 */
	public Integer getCompanyId() {
		return companyId;
	}
	/**
	 * 设置：面积(对应属性值表id)
	 */
	public void setSquareid(Integer squareid) {
		this.squareid = squareid;
	}
	/**
	 * 获取：面积(对应属性值表id)
	 */
	public Integer getSquareid() {
		return squareid;
	}
	/**
	 * 设置：户型1.1室，2 ,3 ,4 ,5.4室以上（对应属性值表id）
	 */
	public void setHouseTypeid(Integer houseTypeid) {
		this.houseTypeid = houseTypeid;
	}
	/**
	 * 获取：户型1.1室，2 ,3 ,4 ,5.4室以上（对应属性值表id）
	 */
	public Integer getHouseTypeid() {
		return houseTypeid;
	}
	/**
	 * 设置：风格，对应属性值表id
	 */
	public void setStyleid(Integer styleid) {
		this.styleid = styleid;
	}
	/**
	 * 获取：风格，对应属性值表id
	 */
	public Integer getStyleid() {
		return styleid;
	}
	/**
	 * 设置：商圈  对应属性值表id
	 */
	public void setBusinessDistrictId(Integer businessDistrictId) {
		this.businessDistrictId = businessDistrictId;
	}
	/**
	 * 获取：商圈  对应属性值表id
	 */
	public Integer getBusinessDistrictId() {
		return businessDistrictId;
	}
	/**
	 * 设置：公装所需的类型  （对应属性值表id）
	 */
	public void setPublicTypeId(Integer publicTypeId) {
		this.publicTypeId = publicTypeId;
	}
	/**
	 * 获取：公装所需的类型  （对应属性值表id）
	 */
	public Integer getPublicTypeId() {
		return publicTypeId;
	}
	/**
	 * 设置：预算 123456（对应属性值表id）
	 */
	public void setMoneyid(Integer moneyid) {
		this.moneyid = moneyid;
	}
	/**
	 * 获取：预算 123456（对应属性值表id）
	 */
	public Integer getMoneyid() {
		return moneyid;
	}
	/**
	 * 设置：公装还是家装：1公装；2家装
	 */
	public void setIsPublic(Integer isPublic) {
		this.isPublic = isPublic;
	}
	/**
	 * 获取：公装还是家装：1公装；2家装
	 */
	public Integer getIsPublic() {
		return isPublic;
	}
	/**
	 * 设置：是否推荐，默认0,1推荐
	 */
	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}
	/**
	 * 获取：是否推荐，默认0,1推荐
	 */
	public Integer getRecommend() {
		return recommend;
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
	 * 设置：地区id
	 */
	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}
	/**
	 * 获取：地区id
	 */
	public Integer getAreaid() {
		return areaid;
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
	public void setAdduserid(Long adduserid) {
		this.adduserid = adduserid;
	}
	/**
	 * 获取：添加人id
	 */
	public Long getAdduserid() {
		return adduserid;
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
	 * 设置：案例详情
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：案例详情
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：纬度
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	/**
	 * 获取：纬度
	 */
	public String getLatitude() {
		return latitude;
	}
	/**
	 * 设置：经度
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	/**
	 * 获取：经度
	 */
	public String getLongitude() {
		return longitude;
	}
}
