package com.dade.core.house.dto;

import com.dade.core.user.agent.Agent;

import java.util.List;

/**
 * Created by Dade on 2017/3/20.
 */
public class HouseDto {

    private String id;

    private Integer area;                            // 面积
    private Integer floor;                           // 楼层
    private String address;                          // 地址
    private String houseType;                        // 户型
    private String community;                        // 小区
    private String district;                         // 区域

    private Integer rentPrice;                      // 出售价格
    private Integer sellPrice;                      // 出售价格

    private String picUrl;


    private String date;                            // 上线时间（需要手动复制）
    private String type;                            // 出租还是出售（需要手动复制）

    private List<Agent> agentList;

    private String rentWay;

    private String analyze;
    private String communityInfo;

    private boolean sellShow;
    private boolean rentShow;
    private String typeInfo;
    private String priceInfo;

    public String getPriceInfo() {
        return priceInfo;
    }

    public void setPriceInfo(String priceInfo) {
        this.priceInfo = priceInfo;
    }

    public boolean isSellShow() {
        return sellShow;
    }

    public void setSellShow(boolean sellShow) {
        this.sellShow = sellShow;
    }

    public boolean isRentShow() {
        return rentShow;
    }

    public void setRentShow(boolean rentShow) {
        this.rentShow = rentShow;
    }

    public String getTypeInfo() {
        return typeInfo;
    }

    public void setTypeInfo(String typeInfo) {
        this.typeInfo = typeInfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Integer getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Integer sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getAnalyze() {
        return analyze;
    }

    public void setAnalyze(String analyze) {
        this.analyze = analyze;
    }

    public String getCommunityInfo() {
        return communityInfo;
    }

    public void setCommunityInfo(String communityInfo) {
        this.communityInfo = communityInfo;
    }

    public String getRentWay() {
        return rentWay;
    }

    public void setRentWay(String rentWay) {
        this.rentWay = rentWay;
    }

    public List<Agent> getAgentList() {
        return agentList;
    }

    public void setAgentList(List<Agent> agentList) {
        this.agentList = agentList;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(Integer rentPrice) {
        this.rentPrice = rentPrice;
    }

    @Override
    public String toString() {
        return "HouseDto{" +
                "area=" + area +
                ", floor=" + floor +
                ", address='" + address + '\'' +
                ", houseType='" + houseType + '\'' +
                ", community='" + community + '\'' +
                ", district='" + district + '\'' +
                ", rentPrice=" + rentPrice +
                '}';
    }
}
