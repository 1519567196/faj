package com.resjz.common.dao.zmadmin.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 招聘
 * 
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@TableName("f_job")
public class FJobEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer iobId;
	/**
	 * 职位名称
	 */
	private String job;
	/**
	 * 招聘人数
	 */
	private Integer count;
	/**
	 * 工作地点
	 */
	private String workplace;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 
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

	/**
	 * 设置：
	 */
	public void setIobId(Integer iobId) {
		this.iobId = iobId;
	}
	/**
	 * 获取：
	 */
	public Integer getIobId() {
		return iobId;
	}
	/**
	 * 设置：职位名称
	 */
	public void setJob(String job) {
		this.job = job;
	}
	/**
	 * 获取：职位名称
	 */
	public String getJob() {
		return job;
	}
	/**
	 * 设置：招聘人数
	 */
	public void setCount(Integer count) {
		this.count = count;
	}
	/**
	 * 获取：招聘人数
	 */
	public Integer getCount() {
		return count;
	}
	/**
	 * 设置：工作地点
	 */
	public void setWorkplace(String workplace) {
		this.workplace = workplace;
	}
	/**
	 * 获取：工作地点
	 */
	public String getWorkplace() {
		return workplace;
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
