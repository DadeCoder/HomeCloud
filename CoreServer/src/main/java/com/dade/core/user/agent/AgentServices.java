package com.dade.core.user.agent;

import com.dade.common.utils.LogUtil;
import com.dade.core.house.CheckRecord;
import com.dade.core.house.House;
import com.dade.core.house.HouseDao;
import com.dade.core.house.dto.HouseDto;
import com.dade.core.house.dto.HouseDtoFactory;
import com.dade.core.user.agent.dto.DataInfoDto;
import com.dade.core.user.purchaser.Purchaser;
import com.dade.core.user.purchaser.PurchaserDao;
import org.bouncycastle.asn1.dvcs.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
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

    @Autowired
    PurchaserDao purchaserDao;

    List<DataInfoDto> getDataInfo(){
        List<DataInfoDto> info = new ArrayList<>();
        List<Purchaser> purchasers = purchaserDao.getAllUsers();

        for (Purchaser user : purchasers){

            DataInfoDto res = new DataInfoDto();
            res.setPurchaser(user);
            if (user.getRentHouseList()!=null)
                res.setRent(user.getRentHouseList().size());

            if (user.getRentOutHouseList()!=null)
                res.setRentOut(user.getRentOutHouseList().size());

            if (user.getSellHouseList()!=null)
                res.setSellOut(user.getSellHouseList().size());

            if (user.getBuyHouseList()!=null)
                res.setSell(user.getBuyHouseList().size());

            res.setAll(res.getRent() + res.getRentOut() + res.getSell() + res.getSellOut());

            info.add(res);
        }
        Collections.sort(info);

        return info;
    }

    void setDate(String houseId, String date, String phone){
        House house = houseDao.findById(houseId);

        if (house == null)
            return;

        Agent agent = agentDao.getByPhone(phone);

        if (agent == null)
            return;

        List<CheckRecord> records = house.getRecordList();

        CheckRecord checkRecord = new CheckRecord();
        checkRecord.setCheckDate(date);
        checkRecord.setCheckId(agent.getId());
        records.add(checkRecord);

        house.setRecordList(records);

        houseDao.save(house);

    }

    List<HouseDto> getSell(String phone){

        Agent agent = agentDao.getByPhone(phone);

        if (agent == null)
            return new ArrayList<>();

        List<House> houseList = houseDao.getAllSellHouse(agent.getId());

        List<HouseDto> res = HouseDtoFactory.getHouseDto(houseList);

        return res;
    }

    List<HouseDto> getRent(String phone){

        Agent agent = agentDao.getByPhone(phone);

        if (agent == null)
            return new ArrayList<>();

        List<House> houseList = houseDao.getAllRentHouse(agent.getId());

        LogUtil.info("size: " + houseList.size());
        LogUtil.info("agentId: " + agent.getId());

        List<HouseDto> res = HouseDtoFactory.getHouseDto(houseList);

        return res;
    }

    void delete(String userId){
        Purchaser user = purchaserDao.findById(userId);
        if (user == null)
            return;

        user.setRole("");

        purchaserDao.save(user);
    }

    void clean(String userId){
        Purchaser user = purchaserDao.findById(userId);
        if (user == null)
            return;

        user.setRole("USER");

        purchaserDao.save(user);
    }

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

        boolean same = false;
        for (String id : agents)
            if (id.equals(agent.getId()))
                same = true;

        if (!same)
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
