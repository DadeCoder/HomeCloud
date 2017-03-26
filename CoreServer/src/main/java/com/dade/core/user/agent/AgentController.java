package com.dade.core.user.agent;

import com.dade.common.utils.LogUtil;
import com.dade.core.house.dto.HouseDto;
import com.dade.core.user.agent.dto.DataInfoDto;
import com.dade.core.user.purchaser.Purchaser;
import com.dade.core.user.purchaser.PurchaserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.xml.ws.RequestWrapper;
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

    @Autowired
    PurchaserService purchaserService;

    @RequestMapping("/getDataInfo")
    List<DataInfoDto> getDataInfo(){
        return agentServices.getDataInfo();
    }

    @RequestMapping("/setDate")
    void setDate(@RequestParam String houseId, @RequestParam String date, Principal principal){
        agentServices.setDate(houseId, date, principal.getName());
    }

    @RequestMapping("/getRent")
    List<HouseDto> getRent(Principal principal){
        return agentServices.getRent(principal.getName());
    }

    @RequestMapping("/getSell")
    List<HouseDto> getSell(Principal principal){
        return agentServices.getSell(principal.getName());
    }

    @RequestMapping("/delete")
    void delete(@RequestParam String userId){
        agentServices.delete(userId);
    }

    @RequestMapping("/clean")
    void clean(@RequestParam String userId){
        agentServices.clean(userId);
    }

    @RequestMapping("/getAllUsers")
    List<UserDto> getAllUsers(){
        return purchaserService.getAllUsers();
    }

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
