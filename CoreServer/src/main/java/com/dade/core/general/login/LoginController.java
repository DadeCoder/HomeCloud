package com.dade.core.general.login;

import com.dade.common.utils.LogUtil;
import com.dade.core.user.purchaser.PurchaserDao;
import com.dade.core.user.purchaser.PurchaserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;

/**
 * Created by Dade on 2017/3/19.
 */
@RestController
@CrossOrigin
public class LoginController {

    @Autowired
    PurchaserService purchaserService;

    @RequestMapping("/agent_login")
    @RolesAllowed("ROLE_AGENT")
    String agentLogin(Principal principal){
        return principal.getName();
    }

    @RequestMapping("/login")
    @RolesAllowed("ROLE_USER")
    String login(Principal principal){
        return principal.getName();
    }

    @RequestMapping("/getInfo")
    String getInfo(Principal principal){
        String info = purchaserService.getInfo(principal.getName());
        LogUtil.info(principal.getName());
        LogUtil.info(info);
        return info;
    }


    @RequestMapping("/test_1")
    String test(Principal principal){
        return principal.getName()+"test_1";
    }

    @RequestMapping("/test__2")
    String test_2(Principal principal){
        return principal.getName()+"test_2";
    }


}
