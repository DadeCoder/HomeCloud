package com.dade.core.user.agent;

/**
 * Created by Dade on 2017/3/26.
 */
public class UserDto {

    private String id;

    private String name;                                // 姓名
    private Integer age;                                // 年龄
    private String phoneNumber;                         // 手机号
    private String password;                            // 密码
    private String role;                                //  角色 -- 权限控制

    private String imageHeaderUrl;                      // 头像

    private Integer rentNo;
    private Integer rentOutNo;
    private Integer sellNo;
    private Integer buyNo;

    public String getId() {
        return id;
    }

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getImageHeaderUrl() {
        return imageHeaderUrl;
    }

    public void setImageHeaderUrl(String imageHeaderUrl) {
        this.imageHeaderUrl = imageHeaderUrl;
    }

    public Integer getRentNo() {
        return rentNo;
    }

    public void setRentNo(Integer rentNo) {
        this.rentNo = rentNo;
    }

    public Integer getRentOutNo() {
        return rentOutNo;
    }

    public void setRentOutNo(Integer rentOutNo) {
        this.rentOutNo = rentOutNo;
    }

    public Integer getSellNo() {
        return sellNo;
    }

    public void setSellNo(Integer sellNo) {
        this.sellNo = sellNo;
    }

    public Integer getBuyNo() {
        return buyNo;
    }

    public void setBuyNo(Integer buyNo) {
        this.buyNo = buyNo;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", imageHeaderUrl='" + imageHeaderUrl + '\'' +
                ", rentNo=" + rentNo +
                ", rentOutNo=" + rentOutNo +
                ", sellNo=" + sellNo +
                ", buyNo=" + buyNo +
                '}';
    }
}
