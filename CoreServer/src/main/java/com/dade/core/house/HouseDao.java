package com.dade.core.house;

import com.dade.common.utils.StringUtil;
import com.dade.core.basic.BasicMongoDao;
import com.dade.core.user.agent.Agent;
import com.dade.core.user.agent.AgentDao;
import com.dade.core.user.purchaser.Purchaser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by Dade on 2017/3/20.
 */
@Component
public class HouseDao extends BasicMongoDao<House> {

    @Autowired
    AgentDao agentDao;

    @Override
    public Class<House> getReturnClass() {
        return House.class;
    }


    public List<House> getSearch(List<String> condition){
        Criteria criteria = Criteria.where(House.FIELD_DELETED).ne(true).and(House.FIELD_ONLINE_TYPE).is(House.ONLINE_SELL);

        if (!StringUtil.isEmpty(condition.get(0)) && !condition.get(0).equals("区域不限"))
            criteria = criteria.and(House.FIELD_DISTRICT).is(condition.get(0));

        if (!StringUtil.isEmpty(condition.get(1)) && !condition.get(1).equals("售价不限")){
            switch (condition.get(1)){
                case "5000":
                    criteria = criteria.and(House.FIELD_SELLPRICE).lte(5000);
                    break;
                case "5000-7000":
                    criteria = criteria.and(House.FIELD_SELLPRICE).gt(5000).lte(7000);
                    break;
                case "7000-10000":
                    criteria = criteria.and(House.FIELD_SELLPRICE).gt(7000).lte(10000);
                    break;
                case "10000-13000":
                    criteria = criteria.and(House.FIELD_SELLPRICE).gt(10000).lte(13000);
                    break;
                case "13000-15000":
                    criteria = criteria.and(House.FIELD_SELLPRICE).gt(13000).lte(15000);
                    break;
                case "15000-17000":
                    criteria = criteria.and(House.FIELD_SELLPRICE).gt(15000).lte(17000);
                    break;
                case "17000-20000":
                    criteria = criteria.and(House.FIELD_SELLPRICE).gt(17000).lte(20000);
                    break;
                case "20000":
                    criteria = criteria.and(House.FIELD_SELLPRICE).gt(20000);
                    break;
            }
        }

        if (!StringUtil.isEmpty(condition.get(2)) && !condition.get(2).equals("面积不限")){
            switch (condition.get(2)){
                case "40":
                    criteria = criteria.and(House.FIELD_AREA).lte(40);
                    break;
                case "40-60":
                    criteria = criteria.and(House.FIELD_AREA).gt(40).lte(60);
                    break;
                case "60-80":
                    criteria = criteria.and(House.FIELD_AREA).gt(60).lte(80);
                    break;
                case "80-100":
                    criteria = criteria.and(House.FIELD_AREA).gt(80).lte(100);
                    break;
                case "100-120":
                    criteria = criteria.and(House.FIELD_AREA).gt(100).lte(120);
                    break;
                case "120-144":
                    criteria = criteria.and(House.FIELD_AREA).gt(120).lte(144);
                    break;
                case "144":
                    criteria = criteria.and(House.FIELD_AREA).gt(144);
                    break;
            }
        }

        if (!StringUtil.isEmpty(condition.get(3)) && !condition.get(3).equals("房型不限"))
            criteria = criteria.and(House.FIELD_HOUSE_TYPE).is(condition.get(3));

        if (!StringUtil.isEmpty(condition.get(4)) && !condition.get(4).equals("楼层不限")){
            switch (condition.get(4)){
                case "低楼层":
                    criteria = criteria.and(House.FIELD_FLOOR).lte(5);
                    break;
                case "中楼层":
                    criteria = criteria.and(House.FIELD_FLOOR).gt(5).lte(10);
                    break;
                case "高楼层":
                    criteria = criteria.and(House.FIELD_FLOOR).gt(10);
                    break;
            }
        }

        return mongoOperations.find(Query.query(criteria), House.class);

    }

    public House atomicCreate(House house){
        Date date = new Date();
        house.setOnlineDate(date);
        house.setRentWay(House.RENT_WAY_DEFAULT);            // 整租

        List<String> agentList = house.getAgentList();
        List<Agent> allAgent = agentDao.getAllAgent();

        // TODO replace
        Random random = new Random();
        int rand = random.nextInt(allAgent.size());
        agentList.add(allAgent.get(rand).getId());

        house.setAnalyze("便利店、银行、茶餐厅、健身房、停车场、物流服务、医院、游泳池。");
        house.setCommunityInfo("兴隆超市、佳兴超市、广州市人民医院、中国工商银行广州支行。");

        mongoOperations.insert(house);

        return house;
    }

    public void savePicUrl(String houseId, String url){

        House house = findById(houseId);
        house.setPicUrl(url);

        mongoOperations.save(house);
    }

    public List<House> getAllHouse(){
        return mongoOperations.findAll(House.class);
    }

}
