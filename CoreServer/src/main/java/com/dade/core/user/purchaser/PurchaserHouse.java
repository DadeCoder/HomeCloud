package com.dade.core.user.purchaser;

import java.util.Date;

/**
 * Created by Dade on 2017/3/20.
 */
public class PurchaserHouse {

    private String houseId;
    private Date time;

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "PurchaserHouse{" +
                "houseId='" + houseId + '\'' +
                ", time=" + time +
                '}';
    }
}
