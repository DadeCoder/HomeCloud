package com.dade.core.user.agent;

/**
 * Created by Dade on 2017/3/25.
 */
public class AgentPassDto {

    private String houseId;
    private String analyze;
    private String communityInfo;

    private String denyInfo;

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

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getDenyInfo() {
        return denyInfo;
    }

    public void setDenyInfo(String denyInfo) {
        this.denyInfo = denyInfo;
    }

    @Override
    public String toString() {
        return "AgentPassDto{" +
                "houseId='" + houseId + '\'' +
                ", analyze='" + analyze + '\'' +
                ", communityInfo='" + communityInfo + '\'' +
                '}';
    }
}
