package com.dade.core.general;

import com.dade.common.utils.LogUtil;
import com.dade.core.user.purchaser.Purchaser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * Created by Dade on 2017/3/12.
 */
@RestController
@RequestMapping("/api/general")
@CrossOrigin
public class RegisterController {

    @Autowired
    RegisterService service;

    @RequestMapping("/getInfoSpe")
    IndexInfoDto getInfoSpe(Principal principal){
        IndexInfoDto info = service.getInfoSpe(principal.getName());
        return info;
    }

    @RequestMapping("/getInfo")
    IndexInfoDto getInfo(){
        IndexInfoDto info = service.getInfo();
        return info;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    Purchaser register(@RequestBody RegisterDto dto){
        LogUtil.info(dto.toString());
        return service.register(dto);
    }

    /**
     * 根据名字验证是否有人注册
     * @param username
     * @return
     */
    @RequestMapping(value = "/name", method = RequestMethod.GET)
    RegisterNameDto name(@RequestParam String username){
        RegisterNameDto dto = new RegisterNameDto();
        LogUtil.info(username);
        if( service.isNameLegal(username) )
            dto.setValid(true);
        else
            dto.setValid(false);

        return dto;
    }

    @RequestMapping(value = "/phone", method = RequestMethod.GET)
    RegisterNameDto phone(@RequestParam String phone){
        RegisterNameDto dto = new RegisterNameDto();
        LogUtil.info(phone);
        if( service.isPhoneLegal(phone) )
            dto.setValid(true);
        else
            dto.setValid(false);

//        dto.setValid(true);

        return dto;
    }



}
