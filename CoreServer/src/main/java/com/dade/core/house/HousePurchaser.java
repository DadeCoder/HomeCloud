package com.dade.core.house;

import java.util.Date;

/**
 * Created by Dade on 2017/3/23.
 */
public class HousePurchaser {

    String userId;

    Date date;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "HousePurchaser{" +
                "userId='" + userId + '\'' +
                ", date=" + date +
                '}';
    }
}
