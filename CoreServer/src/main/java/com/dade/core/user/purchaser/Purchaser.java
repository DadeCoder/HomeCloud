package com.dade.core.user.purchaser;

import com.dade.core.basic.BasicModelObject;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * 用户模型
 * 买房 卖饭 租房用户统一
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
    private List<FocusHouse> focusHouseList;            // 关注列表
    private List<HouseRecord> houseRecordList;          // 看房列表
    private List<HouseSchedule> houseScheduleList;      // 看房行程

    private Date createDate;
    private Date ModifyDate;

    private Boolean deleted;

    public static final String FIELD_PHONE_NUMBER = "phoneNumber";


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


    public List<FocusHouse> getFocusHouseList() {
        return focusHouseList;
    }

    public void setFocusHouseList(List<FocusHouse> focusHouseList) {
        this.focusHouseList = focusHouseList;
    }

    public List<HouseRecord> getHouseRecordList() {
        return houseRecordList;
    }

    public void setHouseRecordList(List<HouseRecord> houseRecordList) {
        this.houseRecordList = houseRecordList;
    }

    public List<HouseSchedule> getHouseScheduleList() {
        return houseScheduleList;
    }

    public void setHouseScheduleList(List<HouseSchedule> houseScheduleList) {
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
                '}';
    }
}
