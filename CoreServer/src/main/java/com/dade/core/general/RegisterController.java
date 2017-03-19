package com.dade.core.general;

import com.dade.common.utils.LogUtil;
import com.dade.core.user.purchaser.Purchaser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Dade on 2017/3/12.
 */
@RestController
@RequestMapping("/api/general")
@CrossOrigin
public class RegisterController {

    @Autowired
    RegisterService service;

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


}
