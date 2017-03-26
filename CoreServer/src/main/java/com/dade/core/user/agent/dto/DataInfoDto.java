package com.dade.core.user.agent.dto;

import com.dade.core.user.purchaser.Purchaser;

/**
 * Created by Dade on 2017/3/26.
 */
public class DataInfoDto  implements Comparable<DataInfoDto> {

    private Purchaser purchaser;

    private Integer sell;
    private Integer rent;
    private Integer sellOut;
    private Integer rentOut;

    private Integer all;

    public Integer getAll() {
        return all;
    }

    public void setAll(Integer all) {
        this.all = all;
    }

    public Purchaser getPurchaser() {
        return purchaser;
    }

    public void setPurchaser(Purchaser purchaser) {
        this.purchaser = purchaser;
    }

    public Integer getSell() {
        return sell;
    }

    public void setSell(Integer sell) {
        this.sell = sell;
    }

    public Integer getRent() {
        return rent;
    }

    public void setRent(Integer rent) {
        this.rent = rent;
    }

    public Integer getSellOut() {
        return sellOut;
    }

    public void setSellOut(Integer sellOut) {
        this.sellOut = sellOut;
    }

    public Integer getRentOut() {
        return rentOut;
    }

    public void setRentOut(Integer rentOut) {
        this.rentOut = rentOut;
    }

    @Override
    public int compareTo(DataInfoDto o) {
        return o.getAll().compareTo(this.getAll());
    }
}
