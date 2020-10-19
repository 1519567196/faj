package com.resjz.common.dao.zmadmin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 楼盘
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@TableName("f_houses")
public class FHousesEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Integer itemid;

    /*浏览量*/
    private Integer views;

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    /**
     * 楼盘（房产）名称
     */
    private String title;
    /**
     * 楼盘主图
     */
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 详细地址
     */
    private String address;
    @TableField(exist = false)  //楼盘综合评分   取点评表此楼盘的所有综合评分的平均值
    private Double score;
    @TableField(exist = false) //楼盘显示信息，需要关联获取的
    private Map<String,Object> info;

    public Map<String, Object> getInfo() {
        return info;
    }

    public void setInfo(Map<String, Object> info) {
        this.info = info;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    /**
     * 建筑类别
     */
    private String jianzhuType;

    public String getJianzhuType() {
        return jianzhuType;
    }

    public void setJianzhuType(String jianzhuType) {
        this.jianzhuType = jianzhuType;
    }

    /**
     * 楼盘所在地区id
     */
    private Integer townid;

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    @TableField(exist = false)
    private String townName;
    /**
     *
     */
    private BigDecimal longitude;
    /**
     * 纬度
     */
    private BigDecimal latitude;
    /**
     * 开盘时间
     */
    private String openTime;

    /**
     * 户型面积
     */
    private String htypeFloorSpace;

    /**
     * 近期开盘
     */
    private String latelyOpen;

    public String getLatelyOpen() {
        return latelyOpen;
    }

    public void setLatelyOpen(String latelyOpen) {
        this.latelyOpen = latelyOpen;
    }

    public String getHtypeFloorSpace() {
        return htypeFloorSpace;
    }

    public void setHtypeFloorSpace(String htypeFloorSpace) {
        this.htypeFloorSpace = htypeFloorSpace;
    }

    /**
     * 楼盘标签，多个用;分开
     */
    private String tags;
    /**
     * 均价
     */
    private Integer price;
    /**
     * 添加时间
     */
    private Date addtime;


    /**
     * 楼盘类型，对应f_house_sku表的attr_id代表楼盘类型时的值id对应的属性值，多个以逗号分开
     */
    private String housesType;


    public String getHousesType() {
        return housesType;
    }

    public void setHousesType(String housesType) {
        this.housesType = housesType;
    }

    /**
     * 所属地区id
     */
    private Integer areaid;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

	@TableField(exist = false)
    private List<FHouseSkuEntity> attrandattrValue;

	public List<FHouseSkuEntity> getAttrandattrValue() {
		return attrandattrValue;
	}

	public void setAttrandattrValue(List<FHouseSkuEntity> attrandattrValue) {
		this.attrandattrValue = attrandattrValue;
	}

	@TableField(exist = false)
    private String areaName;
    /**
     * 项目特色
     */
    private String teSe;
    /**
     * 物业类别
     */
    private String wuYeType;
    /**
     * 装修情况
     */
    private String zhuangXiu;
    /**
     * 环线位置
     */
    private String huanXian;
    /**
     * 产权年限
     */
    private String chanQuan;
    /**
     * 优惠
     */
    private String youHui;
    /**
     * 销售状态  1:在售；2：售罄
     */
    private Integer saleStatus;
    /**
     * 项目介绍
     */
    private String content;
    /**
     * 已开楼盘
     */
    private String openHouses;
    /**
     * 交房时间
     */
    private String deliveryTime;
    /**
     * 售楼地址
     */
    private String saleAddress;
    /**
     * 资讯电话
     */
    private String telphone;
    /**
     * 主力户型
     */
    private String mainHuXing;
    /**
     * 预售许可证（放图片）
     */
    private String xuKeZheng;
    /**
     * 占地面积
     */
    private String zhanDi;
    /**
     * 建筑面积
     */
    private String jianZhu;
    /**
     *
     */
    private String rongJi;
    /**
     * 绿化率
     */
    private String lvHua;
    /**
     *
     */
    private String cheWei;
    /**
     * 物业公司
     */
    private String wuYeCompany;
    /**
     * 停车配比
     */
    private String tingChe;
    /**
     * 计划户数
     */
    private String huShu;
    /**
     * 楼层状况
     */
    private String louCeng;
    /**
     * 物业费
     */
    private String wuYeMoney;
    /**
     * 楼栋总数
     */
    private String dongShu;
    /**
     * 交通信息
     */
    private String jiaoTong;
    /**
     * 医院信息
     */
    private String yiYuan;
    /**
     * 周边学校
     */
    private String xueXiao;
    /**
     * 综合商场
     */
    private String shangChang;
    /**
     * 公园景观
     */
    private String gongYuan;
    /**
     * 其他配置
     */
    private String qiTa;
    /**
     * 小区配套
     */
    private String peiTao;
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
     * 设置：楼盘（房产）名称
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取：楼盘（房产）名称
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置：详细地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取：详细地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置：楼盘所在地区id
     */
    public void setTownid(Integer townid) {
        this.townid = townid;
    }

    /**
     * 获取：楼盘所在地区id
     */
    public Integer getTownid() {
        return townid;
    }

    /**
     * 设置：
     */
    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    /**
     * 获取：
     */
    public BigDecimal getLongitude() {
        return longitude;
    }

    /**
     * 设置：纬度
     */
    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    /**
     * 获取：纬度
     */
    public BigDecimal getLatitude() {
        return latitude;
    }

    /**
     * 设置： 开盘时间
     */
    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    /**
     * 获取： 开盘时间
     */
    public String getOpenTime() {
        return openTime;
    }

    /**
     * 设置：楼盘标签，多个用;分开
     */
    public void setTags(String tags) {
        this.tags = tags;
    }

    /**
     * 获取：楼盘标签，多个用;分开
     */
    public String getTags() {
        return tags;
    }

    /**
     * 设置：均价
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * 获取：均价
     */
    public Integer getPrice() {
        return price;
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
     * 设置：项目特色
     */
    public void setTeSe(String teSe) {
        this.teSe = teSe;
    }

    /**
     * 获取：项目特色
     */
    public String getTeSe() {
        return teSe;
    }

    /**
     * 设置：物业类别
     */
    public void setWuYeType(String wuYeType) {
        this.wuYeType = wuYeType;
    }

    /**
     * 获取：物业类别
     */
    public String getWuYeType() {
        return wuYeType;
    }

    /**
     * 设置：装修情况
     */
    public void setZhuangXiu(String zhuangXiu) {
        this.zhuangXiu = zhuangXiu;
    }

    /**
     * 获取：装修情况
     */
    public String getZhuangXiu() {
        return zhuangXiu;
    }

    /**
     * 设置：环线位置
     */
    public void setHuanXian(String huanXian) {
        this.huanXian = huanXian;
    }

    /**
     * 获取：环线位置
     */
    public String getHuanXian() {
        return huanXian;
    }

    /**
     * 设置：产权年限
     */
    public void setChanQuan(String chanQuan) {
        this.chanQuan = chanQuan;
    }

    /**
     * 获取：产权年限
     */
    public String getChanQuan() {
        return chanQuan;
    }

    /**
     * 设置：优惠
     */
    public void setYouHui(String youHui) {
        this.youHui = youHui;
    }

    /**
     * 获取：优惠
     */
    public String getYouHui() {
        return youHui;
    }

    /**
     * 设置：销售状态
     */
    public void setSaleStatus(Integer saleStatus) {
        this.saleStatus = saleStatus;
    }

    /**
     * 获取：销售状态
     */
    public Integer getSaleStatus() {
        return saleStatus;
    }

    /**
     * 设置：项目介绍
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取：项目介绍
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置：已开楼盘
     */
    public void setOpenHouses(String openHouses) {
        this.openHouses = openHouses;
    }

    /**
     * 获取：已开楼盘
     */
    public String getOpenHouses() {
        return openHouses;
    }

    /**
     * 设置：交房时间
     */
    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    /**
     * 获取：交房时间
     */
    public String getDeliveryTime() {
        return deliveryTime;
    }

    /**
     * 设置：售楼地址
     */
    public void setSaleAddress(String saleAddress) {
        this.saleAddress = saleAddress;
    }

    /**
     * 获取：售楼地址
     */
    public String getSaleAddress() {
        return saleAddress;
    }

    /**
     * 设置：资讯电话
     */
    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    /**
     * 获取：资讯电话
     */
    public String getTelphone() {
        return telphone;
    }

    /**
     * 设置：主力户型
     */
    public void setMainHuXing(String mainHuXing) {
        this.mainHuXing = mainHuXing;
    }

    /**
     * 获取：主力户型
     */
    public String getMainHuXing() {
        return mainHuXing;
    }

    /**
     * 设置：预售许可证（放图片）
     */
    public void setXuKeZheng(String xuKeZheng) {
        this.xuKeZheng = xuKeZheng;
    }

    /**
     * 获取：预售许可证（放图片）
     */
    public String getXuKeZheng() {
        return xuKeZheng;
    }

    /**
     * 设置：占地面积
     */
    public void setZhanDi(String zhanDi) {
        this.zhanDi = zhanDi;
    }

    /**
     * 获取：占地面积
     */
    public String getZhanDi() {
        return zhanDi;
    }

    /**
     * 设置：建筑面积
     */
    public void setJianZhu(String jianZhu) {
        this.jianZhu = jianZhu;
    }

    /**
     * 获取：建筑面积
     */
    public String getJianZhu() {
        return jianZhu;
    }

    /**
     * 设置：
     */
    public void setRongJi(String rongJi) {
        this.rongJi = rongJi;
    }

    /**
     * 获取：
     */
    public String getRongJi() {
        return rongJi;
    }

    /**
     * 设置：绿化率
     */
    public void setLvHua(String lvHua) {
        this.lvHua = lvHua;
    }

    /**
     * 获取：绿化率
     */
    public String getLvHua() {
        return lvHua;
    }

    /**
     * 设置：
     */
    public void setCheWei(String cheWei) {
        this.cheWei = cheWei;
    }

    /**
     * 获取：
     */
    public String getCheWei() {
        return cheWei;
    }

    /**
     * 设置：物业公司
     */
    public void setWuYeCompany(String wuYeCompany) {
        this.wuYeCompany = wuYeCompany;
    }

    /**
     * 获取：物业公司
     */
    public String getWuYeCompany() {
        return wuYeCompany;
    }

    /**
     * 设置：停车配比
     */
    public void setTingChe(String tingChe) {
        this.tingChe = tingChe;
    }

    /**
     * 获取：停车配比
     */
    public String getTingChe() {
        return tingChe;
    }

    /**
     * 设置：计划户数
     */
    public void setHuShu(String huShu) {
        this.huShu = huShu;
    }

    /**
     * 获取：计划户数
     */
    public String getHuShu() {
        return huShu;
    }

    /**
     * 设置：楼层状况
     */
    public void setLouCeng(String louCeng) {
        this.louCeng = louCeng;
    }

    /**
     * 获取：楼层状况
     */
    public String getLouCeng() {
        return louCeng;
    }

    /**
     * 设置：物业费
     */
    public void setWuYeMoney(String wuYeMoney) {
        this.wuYeMoney = wuYeMoney;
    }

    /**
     * 获取：物业费
     */
    public String getWuYeMoney() {
        return wuYeMoney;
    }

    /**
     * 设置：楼栋总数
     */
    public void setDongShu(String dongShu) {
        this.dongShu = dongShu;
    }

    /**
     * 获取：楼栋总数
     */
    public String getDongShu() {
        return dongShu;
    }

    /**
     * 设置：交通信息
     */
    public void setJiaoTong(String jiaoTong) {
        this.jiaoTong = jiaoTong;
    }

    /**
     * 获取：交通信息
     */
    public String getJiaoTong() {
        return jiaoTong;
    }

    /**
     * 设置：医院信息
     */
    public void setYiYuan(String yiYuan) {
        this.yiYuan = yiYuan;
    }

    /**
     * 获取：医院信息
     */
    public String getYiYuan() {
        return yiYuan;
    }

    /**
     * 设置：周边学校
     */
    public void setXueXiao(String xueXiao) {
        this.xueXiao = xueXiao;
    }

    /**
     * 获取：周边学校
     */
    public String getXueXiao() {
        return xueXiao;
    }

    /**
     * 设置：综合商场
     */
    public void setShangChang(String shangChang) {
        this.shangChang = shangChang;
    }

    /**
     * 获取：综合商场
     */
    public String getShangChang() {
        return shangChang;
    }

    /**
     * 设置：公园景观
     */
    public void setGongYuan(String gongYuan) {
        this.gongYuan = gongYuan;
    }

    /**
     * 获取：公园景观
     */
    public String getGongYuan() {
        return gongYuan;
    }

    /**
     * 设置：其他配置
     */
    public void setQiTa(String qiTa) {
        this.qiTa = qiTa;
    }

    /**
     * 获取：其他配置
     */
    public String getQiTa() {
        return qiTa;
    }

    /**
     * 设置：小区配套
     */
    public void setPeiTao(String peiTao) {
        this.peiTao = peiTao;
    }

    /**
     * 获取：小区配套
     */
    public String getPeiTao() {
        return peiTao;
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
