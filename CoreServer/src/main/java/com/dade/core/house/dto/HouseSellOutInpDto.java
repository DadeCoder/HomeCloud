package com.dade.core.house.dto;

/**
 * Created by Dade on 2017/3/20.
 */
public class HouseSellOutInpDto {

    private Integer area;                            // 面积
    private Integer floor;                           // 楼层
    private String address;                          // 地址
    private String houseType;                        // 户型
    private String community;                        // 小区
    private String district;                         // 区域


    private Integer sellPrice;                      // 出售价格

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

    public Integer getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Integer sellPrice) {
        this.sellPrice = sellPrice;
    }

    @Override
    public String toString() {
        return "HouseSellOutInpDto{" +
                "area=" + area +
                ", floor=" + floor +
                ", address='" + address + '\'' +
                ", houseType='" + houseType + '\'' +
                ", community='" + community + '\'' +
                ", district='" + district + '\'' +
                ", sellPrice=" + sellPrice +
                '}';
    }
}
