package com.dade.core.user.sysAdmin;

import com.dade.core.user.agent.Agent;
import com.dade.core.user.agent.AgentDao;
import com.dade.core.user.agent.AgentRegiDto;
import com.dade.core.user.purchaser.Purchaser;
import com.dade.core.user.purchaser.PurchaserDao;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Dade on 2017/3/25.
 */
@RestController
@RequestMapping("/api/sys")
@CrossOrigin
public class SysAdminController {

    @Autowired
    AgentDao agentDao;

    @Autowired
    PurchaserDao purchaserDao;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Agent addAgent(@RequestBody AgentRegiDto dto){

        Agent agent = new Agent();
        agent.setName(dto.getUsername());
        agent.setPassword(dto.getPassword());
        agent.setPhone(dto.getPhoneNumber());

        agentDao.insert(agent);

        Purchaser purchaser = new Purchaser();
        purchaser.setName(dto.getUsername());
        purchaser.setPhoneNumber(dto.getPhoneNumber());
        purchaser.setPassword(dto.getPassword());

        purchaserDao.atomicCreateAgent(purchaser);

        return agent;
    }

    @RequestMapping("/getAll")
    public List<Agent> getAll(){
        return agentDao.getAllAgent();
    }

}
