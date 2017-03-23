package com.dade.core.general;

/**
 * Created by Dade on 2017/3/23.
 */
public class IndexInfoDto {

    private Long users;
    private Long sellHouses;
    private Long rentHouses;
    private Integer doneCases;

    public Long getUsers() {
        return users;
    }

    public void setUsers(Long users) {
        this.users = users;
    }

    public Long getSellHouses() {
        return sellHouses;
    }

    public void setSellHouses(Long sellHouses) {
        this.sellHouses = sellHouses;
    }

    public Long getRentHouses() {
        return rentHouses;
    }

    public void setRentHouses(Long rentHouses) {
        this.rentHouses = rentHouses;
    }

    public Integer getDoneCases() {
        return doneCases;
    }

    public void setDoneCases(Integer doneCases) {
        this.doneCases = doneCases;
    }

    @Override
    public String toString() {
        return "IndexInfoDto{" +
                "users=" + users +
                ", sellHouses=" + sellHouses +
                ", rentHouses=" + rentHouses +
                ", doneCases=" + doneCases +
                '}';
    }
}
