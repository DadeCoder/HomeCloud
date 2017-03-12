package com.dade.core.general;

import com.dade.core.user.purchaser.Purchaser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Dade on 2017/3/12.
 */
@RestController
@RequestMapping("/api/general")
public class RegisterController {

    @Autowired
    RegisterService service;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    Purchaser register(@RequestBody RegisterDto registerDto){
        return service.register(registerDto);
    }


}
