package com.dade.core.user.purchaser;

import com.dade.core.basic.BasicModelObject;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户模型
 * 买房 卖房 租房 房屋出租用户统一
 * Created by Dade on 2017/3/12.
 */
@Document
public class Purchaser extends BasicModelObject {

    private String id;

    private String name;                                // 姓名
    private Integer age;                                // 年龄
    private String phoneNumber;                         // 手机号
    private String password;                            // 密码
    private String role;                                //  角色 -- 权限控制

    private String imageHeaderUrl;                      // 头像

    // person center
    private List<PurchaserHouse> focusHouseList;            // 关注列表
    private List<PurchaserHouse> houseRecordList;          // 看房列表
    private List<PurchaserHouse> houseScheduleList;      // 看房行程

    // house
    private List<PurchaserHouse> rentHouseList;             // 租房列表
    private List<PurchaserHouse> rentOutHouseList;          // 出租房屋列表
    private List<PurchaserHouse> sellHouseList;             // 卖房列表
    private List<PurchaserHouse> buyHouseList;              // 买房列表

    private Date createDate;
    private Date ModifyDate;

    private Boolean deleted;

    public static final String FIELD_PHONE_NUMBER = "phoneNumber";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_IMAGE_URL = "imageHeaderUrl";




    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getImageHeaderUrl() {
        return imageHeaderUrl;
    }

    public void setImageHeaderUrl(String imageHeaderUrl) {
        this.imageHeaderUrl = imageHeaderUrl;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getId() {
        return id;
    }


    public List<PurchaserHouse> getFocusHouseList() {

        if (focusHouseList == null)
            focusHouseList = new ArrayList<>();

        return focusHouseList;
    }

    public void setFocusHouseList(List<PurchaserHouse> focusHouseList) {
        this.focusHouseList = focusHouseList;
    }

    public List<PurchaserHouse> getHouseRecordList() {
        return houseRecordList;
    }

    public void setHouseRecordList(List<PurchaserHouse> houseRecordList) {
        this.houseRecordList = houseRecordList;
    }

    public List<PurchaserHouse> getHouseScheduleList() {

        if (houseRecordList == null)
            houseRecordList = new ArrayList<>();

        return houseScheduleList;
    }

    public void setHouseScheduleList(List<PurchaserHouse> houseScheduleList) {
        this.houseScheduleList = houseScheduleList;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return ModifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        ModifyDate = modifyDate;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public List<PurchaserHouse> getRentHouseList() {

        if ( rentHouseList == null )
            rentHouseList = new ArrayList<PurchaserHouse>();

        return rentHouseList;
    }

    public void setRentHouseList(List<PurchaserHouse> rentHouseList) {
        this.rentHouseList = rentHouseList;
    }

    public List<PurchaserHouse> getRentOutHouseList() {

        if ( rentOutHouseList == null )
            rentOutHouseList = new ArrayList<PurchaserHouse>();

        return rentOutHouseList;
    }

    public void setRentOutHouseList(List<PurchaserHouse> rentOutHouseList) {
        this.rentOutHouseList = rentOutHouseList;
    }

    public List<PurchaserHouse> getSellHouseList() {
        if (sellHouseList == null)
            sellHouseList = new ArrayList<>();
        return sellHouseList;
    }

    public void setSellHouseList(List<PurchaserHouse> sellHouseList) {
        this.sellHouseList = sellHouseList;
    }

    public List<PurchaserHouse> getBuyHouseList() {
        if (buyHouseList == null)
            buyHouseList = new ArrayList<>();

        return buyHouseList;
    }

    public void setBuyHouseList(List<PurchaserHouse> buyHouseList) {
        this.buyHouseList = buyHouseList;
    }

    @Override
    public String toString() {
        return "Purchaser{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", imageHeaderUrl='" + imageHeaderUrl + '\'' +
                ", focusHouseList=" + focusHouseList +
                ", houseRecordList=" + houseRecordList +
                ", houseScheduleList=" + houseScheduleList +
                ", rentHouseList=" + rentHouseList +
                ", rentOutHouseList=" + rentOutHouseList +
                ", sellHouseList=" + sellHouseList +
                ", buyHouseList=" + buyHouseList +
                ", createDate=" + createDate +
                ", ModifyDate=" + ModifyDate +
                ", deleted=" + deleted +
                '}';
    }
}
