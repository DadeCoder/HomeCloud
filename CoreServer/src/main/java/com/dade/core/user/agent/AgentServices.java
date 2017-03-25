package com.dade.core.user.agent;

import com.dade.core.house.House;
import com.dade.core.house.HouseDao;
import com.dade.core.house.dto.HouseDto;
import com.dade.core.house.dto.HouseDtoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Dade on 2017/3/25.
 */
@Component
public class AgentServices {

    @Autowired
    AgentDao agentDao;

    @Autowired
    HouseDao houseDao;

    public List<HouseDto> getSellAccess(){
        List<House> houseList = houseDao.getSellAccess();
        List<HouseDto> res = HouseDtoFactory.getHouseDto(houseList);

        return res;
    }

    public List<HouseDto> getRentAccess(){
        List<House> houseList = houseDao.getRentAccess();
        List<HouseDto> res = HouseDtoFactory.getHouseDto(houseList);

        return res;
    }

    public void pass(AgentPassDto dto, String phone){

        House house = houseDao.findById(dto.getHouseId());

        if (house == null)
            return;

        Agent agent = agentDao.getByPhone(phone);

        if (agent == null)
            return;

        house.setAccess(House.ACCESS_PASS);
        house.setCommunityInfo(dto.getCommunityInfo());
        house.setAnalyze(dto.getAnalyze());

        List<String> agents = house.getAgentList();
        agents.add(agent.getId());
        house.setAgentList(agents);

        houseDao.save(house);

    }

    public void deny(AgentPassDto dto, String phone){

        House house = houseDao.findById(dto.getHouseId());

        if (house == null)
            return;

        Agent agent = agentDao.getByPhone(phone);

        if (agent == null)
            return;

        house.setAccess(House.ACCESS_DENY);
        house.setDenyInfo(dto.getDenyInfo());

        house.setDenyAgentId(agent.getId());

        houseDao.save(house);
    }

}
