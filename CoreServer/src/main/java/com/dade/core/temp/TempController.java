package com.dade.core.temp;

import com.dade.core.user.agent.Agent;
import com.dade.core.user.agent.AgentDao;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Dade on 2017/3/21.
 */
@RestController
@RequestMapping("/api/test")
public class TempController {

    @Autowired
    AgentDao agentDao;

    @RequestMapping(value = "/addAgent", method = RequestMethod.POST)
    public Agent addAgent(@RequestBody Agent agent){
        agentDao.insert(agent);
        return agent;
    }

    @RequestMapping("/getAll")
    public List<Agent> getAll(){
        return agentDao.getAllAgent();
    }

}
