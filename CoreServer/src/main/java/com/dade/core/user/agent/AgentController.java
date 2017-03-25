package com.dade.core.user.agent;

import com.dade.common.utils.LogUtil;
import com.dade.core.house.dto.HouseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;
import java.util.List;

/**
 * Created by Dade on 2017/3/25.
 */
@RestController
@RequestMapping("/api/agent")
@RolesAllowed("ROLE_AGENT")
@CrossOrigin
public class AgentController {

    @Autowired
    AgentServices agentServices;

    @RequestMapping("/getSellAccess")
    List<HouseDto> getSellAccess(){
        return agentServices.getSellAccess();
    }

    @RequestMapping("/getRentAccess")
    List<HouseDto> getRentAccess(){
        return agentServices.getRentAccess();
    }

    @RequestMapping(value = "/pass", method = RequestMethod.POST)
    void pass(@RequestBody AgentPassDto dto, Principal principal){
        agentServices.pass(dto, principal.getName());
    }

    @RequestMapping(value = "/deny", method = RequestMethod.POST)
    void deny(@RequestBody AgentPassDto dto, Principal principal){
        agentServices.deny(dto, principal.getName());
    }

}
