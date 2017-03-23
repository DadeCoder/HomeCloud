package com.dade.core.house;

import com.dade.core.basic.BasicModelObject;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Dade on 2017/3/12.
 */
@Document
public class House extends BasicModelObject {

    private String id;

    private Integer area;                            // 面积
    private Integer floor;                           // 楼层
    private String address;                          // 地址
    private String houseType;                        // 户型
    private String community;                        // 小区
    private String district;                         // 区域

    private Integer sellPrice;                      // 出售价格
    private Integer rentPrice;                      // 出租价格

    private Integer onlineType;                     // 出租还是出售[0 出租 | 1 出售]
    private String ownerId;                         // 房屋所有人ID
    private String purchaserId;                     // 房屋购买者ID
    private String renterId;                        // 租房者ID


    private Date onlineDate;                        // 上线时间
    private Date sellDate;                          // 卖出时间
    private Date purchaseDate;                      // 租出时间

    private String picUrl;
    private Integer rentWay;                        // 租赁方式[默认整租]

    private Integer sellPricePosition;                  // [0价格没变动|1升价|2降价]
    private Integer rentPricePosition;                  // [0价格没变动|1升价|2降价]

    private List<String> agentList;

    private String analyze;
    private String communityInfo;


    private List<HousePurchaser> orderList;                 // 预约名单

    public static final Integer ONLINE_RENT = 0;        // 出租，见onlineType字段
    public static final Integer ONLINE_SELL = 1;        // 出售，见onlineType字段

    public static final Integer RENT_WAY_DEFAULT = 0;        // 整租，见rentWay字段
    public static final String RENT_WAY_DEFAULT_ZH = "整租";        // 整租，见rentWay字段

    public static final Integer SELL_PRICE_DEFAULT = 0;        // 价格不变，见sellPricePosition字段
    public static final Integer SELL_PRICE_UP = 1;        // 升价，见sellPricePosition字段
    public static final Integer SELL_PRICE_DOWN = 2;        // 降价，见sellPricePosition字段

    public static final Integer RENT_PRICE_DEFAULT = 0;        // 价格不变，见rentPricePosition字段
    public static final Integer RENT_PRICE_UP = 1;        // 升价，见rentPricePosition字段
    public static final Integer RENT_PRICE_DOWN = 2;        // 降价，见rentPricePosition字段

    public static final String FIELD_DISTRICT = "district";
    public static final String FIELD_AREA = "area";
    public static final String FIELD_SELLPRICE = "sellPrice";
    public static final String FIELD_RENTPRICE = "rentPrice";
    public static final String FIELD_FLOOR = "floor";
    public static final String FIELD_HOUSE_TYPE = "houseType";
    public static final String FIELD_ONLINE_TYPE = "onlineType";

    public static final String FIELD_ID = "id";

    public List<HousePurchaser> getOrderList() {

        if (orderList == null)
            orderList = new ArrayList<>();

        return orderList;
    }

    public void setOrderList(List<HousePurchaser> orderList) {
        this.orderList = orderList;
    }

    public Integer getRentPricePosition() {
        return rentPricePosition;
    }

    public void setRentPricePosition(Integer rentPricePosition) {
        this.rentPricePosition = rentPricePosition;
    }

    public Integer getSellPricePosition() {
        return sellPricePosition;
    }

    public void setSellPricePosition(Integer sellPricePosition) {
        this.sellPricePosition = sellPricePosition;
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

    public Integer getRentWay() {
        return rentWay;
    }

    public void setRentWay(Integer rentWay) {
        this.rentWay = rentWay;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public List<String> getAgentList() {

        if (agentList == null)
            agentList = new ArrayList<String>();

        return agentList;
    }

    public void setAgentList(List<String> agentList) {
        this.agentList = agentList;
    }

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

    public Integer getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Integer sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Integer getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(Integer rentPrice) {
        this.rentPrice = rentPrice;
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
                ", sellPrice=" + sellPrice +
                ", rentPrice=" + rentPrice +
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
