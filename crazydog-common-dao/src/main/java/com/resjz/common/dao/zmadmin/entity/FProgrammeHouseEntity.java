package com.resjz.common.dao.zmadmin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 我加方案-户型表
 * 
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@TableName("f_programme_house")
public class FProgrammeHouseEntity implements Serializable {
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
	 * 户型结构（几室几厅几卫几厨等等）
	 */
	private String houseStructure;

	public String getHouseStructure() {
		return houseStructure;
	}

	public void setHouseStructure(String houseStructure) {
		this.houseStructure = houseStructure;
	}

	/**
	/**
	 * 户型类别  1：一居室；2：二居室；3：三居室；4：四居室；5：复式；6：跃层；7：别墅；8：其它；
	 */
	private Integer houseType;
	/**
	 * 小区id(关联我加方案小区表)
	 */
	private Integer programmeVillageId;
	@TableField(exist = false)
	private String programmeVillageName;

	public String getProgrammeVillageName() {
		return programmeVillageName;
	}

	public void setProgrammeVillageName(String programmeVillageName) {
		this.programmeVillageName = programmeVillageName;
	}

	/**
	 * 图片地址
	 */
	private String image;
	/**
	 * 是否有全景,默认0,1有
	 */
	private Integer isVr;
	/**
	 * 建筑面积
	 */
	private String builtArea;
	/**
	 * 添加时间
	 */
	private Date addtime;
	/**
	 * 
	 */
	private Long addUserid;
	@TableField(exist = false)
	private String addUserName;
	@TableField(exist = false)
	private List<FProgrammeImagesEntity>  imgesList;
	@TableField(exist = false)
	private List<FProgrammeVrEntity>  vrList;

	public List<FProgrammeVrEntity> getVrList() {
		return vrList;
	}

	public void setVrList(List<FProgrammeVrEntity> vrList) {
		this.vrList = vrList;
	}

	public List<FProgrammeImagesEntity> getImgesList() {
		return imgesList;
	}

	public void setImgesList(List<FProgrammeImagesEntity> imgesList) {
		this.imgesList = imgesList;
	}

	public String getAddUserName() {
		return addUserName;
	}

	public void setAddUserName(String addUserName) {
		this.addUserName = addUserName;
	}

	/**
	 * 所属地区id
	 */
	private Integer areaid;
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
	 * 设置：户型id (关联户型表)
	 */
	public void setHouseType(Integer houseType) {
		this.houseType = houseType;
	}
	/**
	 * 获取：户型id (关联户型表)
	 */
	public Integer getHouseType() {
		return houseType;
	}
	/**
	 * 设置：小区id(关联我加方案小区表)
	 */
	public void setProgrammeVillageId(Integer programmeVillageId) {
		this.programmeVillageId = programmeVillageId;
	}
	/**
	 * 获取：小区id(关联我加方案小区表)
	 */
	public Integer getProgrammeVillageId() {
		return programmeVillageId;
	}
	/**
	 * 设置：图片地址
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * 获取：图片地址
	 */
	public String getImage() {
		return image;
	}
	/**
	 * 设置：是否有全景,默认0,1有
	 */
	public void setIsVr(Integer isVr) {
		this.isVr = isVr;
	}
	/**
	 * 获取：是否有全景,默认0,1有
	 */
	public Integer getIsVr() {
		return isVr;
	}
	/**
	 * 设置：建筑面积
	 */
	public void setBuiltArea(String builtArea) {
		this.builtArea = builtArea;
	}
	/**
	 * 获取：建筑面积
	 */
	public String getBuiltArea() {
		return builtArea;
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

	public Long getAddUserid() {
		return addUserid;
	}

	public void setAddUserid(Long addUserid) {
		this.addUserid = addUserid;
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
}
