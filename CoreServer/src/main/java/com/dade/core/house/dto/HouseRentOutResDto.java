package com.dade.core.house.dto;

/**
 * Created by Dade on 2017/3/20.
 */
public class HouseRentOutResDto {

    private Integer area;                            // 面积
    private Integer floor;                           // 楼层
    private String address;                          // 地址
    private Integer houseType;                        // 户型
    private String community;                        // 小区
    private String district;                         // 区域

    private Integer rentPrice;                      // 出售价格

    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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

    public Integer getHouseType() {
        return houseType;
    }

    public void setHouseType(Integer houseType) {
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
        return "HouseRentOutResDto{" +
                "area=" + area +
                ", floor=" + floor +
                ", address='" + address + '\'' +
                ", houseType='" + houseType + '\'' +
                ", community='" + community + '\'' +
                ", district='" + district + '\'' +
                ", rentPrice=" + rentPrice +
                ", info='" + info + '\'' +
                '}';
    }
}
