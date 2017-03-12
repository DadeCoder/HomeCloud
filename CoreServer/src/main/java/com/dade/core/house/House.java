package com.dade.core.house;

import com.dade.core.basic.BasicModelObject;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by Dade on 2017/3/12.
 */
@Document
public class House extends BasicModelObject {

    private String id;

    private Integer area;                           // 面积
    private Integer floor;                          // 楼层
    private String address;                         // 地址
    private String houseType;                       // 户型
    private String community;                       // 小区
    private String district;                        // 区域

    private Integer sellprice;                      // 出售价格
    private Integer rentprice;                      // 出租价格

    private Integer onlineType;                     // 出租还是出售[0 出租 | 1 出售]
    private String ownerId;                         // 房屋所有人ID
    private String purchaserId;                     // 房屋购买者ID
    private String renterId;                        // 租房者ID


    private Date onlineDate;                        // 上线时间
    private Date sellDate;                          // 卖出时间
    private Date purchaseDate;                      // 租出时间


    public static final Integer ONLINE_RENT = 0;        // 出租，见onlineType字段
    public static final Integer ONLINE_SELL = 1;        // 出售，见onlineType字段


    public void setId(String id) {
        this.id = id;
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

    public Integer getSellprice() {
        return sellprice;
    }

    public void setSellprice(Integer sellprice) {
        this.sellprice = sellprice;
    }

    public Integer getRentprice() {
        return rentprice;
    }

    public void setRentprice(Integer rentprice) {
        this.rentprice = rentprice;
    }

    public Integer getOnlineType() {
        return onlineType;
    }

    public void setOnlineType(Integer onlineType) {
        this.onlineType = onlineType;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getPurchaserId() {
        return purchaserId;
    }

    public void setPurchaserId(String purchaserId) {
        this.purchaserId = purchaserId;
    }

    public String getRenterId() {
        return renterId;
    }

    public void setRenterId(String renterId) {
        this.renterId = renterId;
    }

    public Date getOnlineDate() {
        return onlineDate;
    }

    public void setOnlineDate(Date onlineDate) {
        this.onlineDate = onlineDate;
    }

    public Date getSellDate() {
        return sellDate;
    }

    public void setSellDate(Date sellDate) {
        this.sellDate = sellDate;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "House{" +
                "id='" + id + '\'' +
                ", area=" + area +
                ", floor=" + floor +
                ", address='" + address + '\'' +
                ", houseType='" + houseType + '\'' +
                ", community='" + community + '\'' +
                ", district='" + district + '\'' +
                ", sellprice=" + sellprice +
                ", rentprice=" + rentprice +
                ", onlineType=" + onlineType +
                ", ownerId='" + ownerId + '\'' +
                ", purchaserId='" + purchaserId + '\'' +
                ", renterId='" + renterId + '\'' +
                ", onlineDate=" + onlineDate +
                ", sellDate=" + sellDate +
                ", purchaseDate=" + purchaseDate +
                '}';
    }
}
