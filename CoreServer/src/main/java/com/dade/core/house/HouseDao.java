package com.dade.core.house;

import com.dade.core.basic.BasicMongoDao;
import com.dade.core.user.agent.Agent;
import com.dade.core.user.agent.AgentDao;
import com.dade.core.user.purchaser.Purchaser;
import org.springframework.beans.factory.annotation.Autowired;
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

}
