package com.dade.core.house;

import com.dade.common.utils.StringUtil;
import com.dade.core.basic.BasicMongoDao;
import com.dade.core.user.agent.Agent;
import com.dade.core.user.agent.AgentDao;
import com.dade.core.user.purchaser.Purchaser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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

    public House getById(String id){
        Criteria criteria = Criteria.where(House.FIELD_ID).is(id)
                .and(House.FIELD_DELETED).ne(true);
        return mongoOperations.findOne(Query.query(criteria), House.class);
    }

    public List<House> getSellAccess(){
        Criteria criteria = Criteria.where(House.FIELD_ACCESS).is(House.ACCESS_DAFAULT)
                .and(House.FIELD_DELETED).ne(true)
                .and(House.FIELD_ONLINE_TYPE).is(House.ONLINE_SELL);

        return mongoOperations.find(Query.query(criteria), House.class);
    }

    public List<House> getRentAccess(){
        Criteria criteria = Criteria.where(House.FIELD_ACCESS).is(House.ACCESS_DAFAULT)
                .and(House.FIELD_DELETED).ne(true)
                .and(House.FIELD_ONLINE_TYPE).is(House.ONLINE_RENT);

        return mongoOperations.find(Query.query(criteria), House.class);
    }

    public void save(House house){
        mongoOperations.save(house);
    }

    public void delete(String houseId){
        Criteria criteria = Criteria.where(House.FIELD_DELETED).ne(true)
                .and(House.FIELD_ID).is(houseId);

        Update update = Update.update(House.FIELD_DELETED, true);
        mongoOperations.updateFirst(Query.query(criteria),update, House.class);

    }

    public List<House> getSearchRent(List<String> condition){
        Criteria criteria = Criteria.where(House.FIELD_DELETED).ne(true)
                .and(House.FIELD_ONLINE_TYPE).is(House.ONLINE_RENT)
                .and(House.FIELD_ACCESS).is(House.ACCESS_PASS);

        if (!StringUtil.isEmpty(condition.get(0)) && !condition.get(0).equals("区域不限"))
            criteria = criteria.and(House.FIELD_DISTRICT).is(condition.get(0));

        if (!StringUtil.isEmpty(condition.get(1)) && !condition.get(1).equals("售价不限")){
            switch (condition.get(1)){
                case "500":
                    criteria = criteria.and(House.FIELD_RENTPRICE).lte(500);
                    break;
                case "500-1000":
                    criteria = criteria.and(House.FIELD_RENTPRICE).gt(500).lte(1000);
                    break;
                case "1000-2000":
                    criteria = criteria.and(House.FIELD_RENTPRICE).gt(1000).lte(2000);
                    break;
                case "2000-5000":
                    criteria = criteria.and(House.FIELD_RENTPRICE).gt(2000).lte(5000);
                    break;
                case "5000-7000":
                    criteria = criteria.and(House.FIELD_RENTPRICE).gt(5000).lte(7000);
                    break;
                case "7000-10000":
                    criteria = criteria.and(House.FIELD_RENTPRICE).gt(7000).lte(10000);
                    break;
                case "10000-20000":
                    criteria = criteria.and(House.FIELD_RENTPRICE).gt(10000).lte(20000);
                    break;
                case "20000":
                    criteria = criteria.and(House.FIELD_RENTPRICE).gt(20000);
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

    public List<House> getSearch(List<String> condition){
        Criteria criteria = Criteria.where(House.FIELD_DELETED).ne(true)
                .and(House.FIELD_ONLINE_TYPE).is(House.ONLINE_SELL)
                .and(House.FIELD_ACCESS).is(House.ACCESS_PASS);

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
        house.setAccess(House.ACCESS_DAFAULT);

//        List<String> agentList = house.getAgentList();
//        List<Agent> allAgent = agentDao.getAllAgent();

//        Random random = new Random();
//        int rand = random.nextInt(allAgent.size());
//        agentList.add(allAgent.get(rand).getId());

//        house.setAnalyze("便利店、银行、茶餐厅、健身房、停车场、物流服务、医院、游泳池。");
//        house.setCommunityInfo("兴隆超市、佳兴超市、广州市人民医院、中国工商银行广州支行。");

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

    public List<House> getAllSellHouse(String phone){
        Criteria criteria = Criteria.where(House.FIELD_ONLINE_TYPE).is(House.ONLINE_SELL)
                .and(House.FIELD_DELETED).ne(true)
                .and(House.FIELD_ACCESS).is(House.ACCESS_PASS)
                .and(House.FIELD_AGENT_LIST).in(phone);

        return find(Query.query(criteria));
    }

    public List<House> getAllRentHouse(String phone){
        Criteria criteria = Criteria.where(House.FIELD_ONLINE_TYPE).is(House.ONLINE_RENT)
                .and(House.FIELD_DELETED).ne(true)
                .and(House.FIELD_ACCESS).is(House.ACCESS_PASS)
                .and(House.FIELD_AGENT_LIST).in(phone);

        return find(Query.query(criteria));
    }

    public Long getSellHousesCount(){
        Criteria criteria = Criteria.where(House.FIELD_DELETED).ne(true).and(House.FIELD_ONLINE_TYPE).is(House.ONLINE_SELL);

        return mongoOperations.count(Query.query(criteria), House.class);
    }

    public Long getRentHousesCount(){
        Criteria criteria = Criteria.where(House.FIELD_DELETED).ne(true).and(House.FIELD_ONLINE_TYPE).is(House.ONLINE_RENT);

        return mongoOperations.count(Query.query(criteria), House.class);
    }

    public Integer getAllHousesCount(){
        return mongoOperations.findAll(House.class).size();
    }

}
