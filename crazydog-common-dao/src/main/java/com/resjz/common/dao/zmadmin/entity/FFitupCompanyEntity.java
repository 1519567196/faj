package com.resjz.common.dao.zmadmin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 装修公司
 * 
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-09-24 14:29:56
 */
@TableName("f_fitup_company")
public class FFitupCompanyEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer itemid;
	/**
	 * 企业名称
	 */
	private String companyName;
	/**
	 * 公司形象图
	 */
	private String image;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 联系电话
	 */
	private String mobile;
	/**
	 * 经度
	 */
	private Double longitude;
	/**
	 * 纬度
	 */
	private Double latitude;
	/**
	 * 状态默认0， 1删除
	 */
	private Integer status;
	/**
	 * 企业简介
	 */
	private String content;
	/**
	 * 售后质保
	 */
	private String warranty;
	/**
	 * 营业时间
	 */
	private String businessHours;
	/**
	 * 评分
	 */
	private Float score;
	/**
	 * 施工方式半包还是全包，1：全包；2：半包
	 */
	private Integer allHalfContracting;
	/**
	 * 全包/半包 时长
	 */
	private String allorhalfTimes;
	/**
	 * 案例数量
	 */
	private Integer caseCount;
	/**
	 * 设计师人数
	 */
	private Integer designerCount;


	@TableField(exist = false)
	private List<FFitupCompanySkuEntity> attrandattrValue;

	public List<FFitupCompanySkuEntity> getAttrandattrValue() {
		return attrandattrValue;
	}

	public void setAttrandattrValue(List<FFitupCompanySkuEntity> attrandattrValue) {
		this.attrandattrValue = attrandattrValue;
	}

	/**
	 * 标签2，存储方式{“到店礼”：“送手机”；}
	 */
	private String tag2;
	/**
	 * 标签1，多个用;隔开
	 */
	private String tag1;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 添加时间
	 */
	private Date addtime;
	/**
	 * 添加人id
	 */
	private Long addUserid;
	@TableField(exist = false)
	private String addUserName;

	public String getAddUserName() {
		return addUserName;
	}

	public void setAddUserName(String addUserName) {
		this.addUserName = addUserName;
	}
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
	 * 修改时间
	 */
	private Date updatetime;

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
	 * 设置：企业名称
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/**
	 * 获取：企业名称
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * 设置：公司形象图
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * 获取：公司形象图
	 */
	public String getImage() {
		return image;
	}
	/**
	 * 设置：地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：地址
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：联系电话
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：联系电话
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置：经度
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	/**
	 * 获取：经度
	 */
	public Double getLongitude() {
		return longitude;
	}
	/**
	 * 设置：纬度
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	/**
	 * 获取：纬度
	 */
	public Double getLatitude() {
		return latitude;
	}
	/**
	 * 设置：状态默认0， 1删除
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态默认0， 1删除
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：企业简介
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：企业简介
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：售后质保
	 */
	public void setWarranty(String warranty) {
		this.warranty = warranty;
	}
	/**
	 * 获取：售后质保
	 */
	public String getWarranty() {
		return warranty;
	}
	/**
	 * 设置：营业时间
	 */
	public void setBusinessHours(String businessHours) {
		this.businessHours = businessHours;
	}
	/**
	 * 获取：营业时间
	 */
	public String getBusinessHours() {
		return businessHours;
	}
	/**
	 * 设置：评分
	 */
	public void setScore(Float score) {
		this.score = score;
	}
	/**
	 * 获取：评分
	 */
	public Float getScore() {
		return score;
	}
	/**
	 * 设置：施工方式半包还是全包，1：全包；2：半包
	 */
	public void setAllHalfContracting(Integer allHalfContracting) {
		this.allHalfContracting = allHalfContracting;
	}
	/**
	 * 获取：施工方式半包还是全包，1：全包；2：半包
	 */
	public Integer getAllHalfContracting() {
		return allHalfContracting;
	}
	/**
	 * 设置：全包/半包 时长
	 */
	public void setAllorhalfTimes(String allorhalfTimes) {
		this.allorhalfTimes = allorhalfTimes;
	}
	/**
	 * 获取：全包/半包 时长
	 */
	public String getAllorhalfTimes() {
		return allorhalfTimes;
	}
	/**
	 * 设置：案例数量
	 */
	public void setCaseCount(Integer caseCount) {
		this.caseCount = caseCount;
	}
	/**
	 * 获取：案例数量
	 */
	public Integer getCaseCount() {
		return caseCount;
	}
	/**
	 * 设置：设计师人数
	 */
	public void setDesignerCount(Integer designerCount) {
		this.designerCount = designerCount;
	}
	/**
	 * 获取：设计师人数
	 */
	public Integer getDesignerCount() {
		return designerCount;
	}
	/**
	 * 设置：标签2，存储方式{“到店礼”：“送手机”；}
	 */
	public void setTag2(String tag2) {
		this.tag2 = tag2;
	}
	/**
	 * 获取：标签2，存储方式{“到店礼”：“送手机”；}
	 */
	public String getTag2() {
		return tag2;
	}
	/**
	 * 设置：标签1，多个用;隔开
	 */
	public void setTag1(String tag1) {
		this.tag1 = tag1;
	}
	/**
	 * 获取：标签1，多个用;隔开
	 */
	public String getTag1() {
		return tag1;
	}
	/**
	 * 设置：排序
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	/**
	 * 获取：排序
	 */
	public Integer getSort() {
		return sort;
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
}
