package com.dade.core.test;

import com.dade.common.utils.LogUtil;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dade on 2017/3/13.
 */
@RestController
@RequestMapping("/api/test")
@CrossOrigin
public class TestController {

    @RequestMapping(value = "/name", method = RequestMethod.POST)
    RegisterNameDto name(@RequestBody String username){
//        Map<String, Boolean> map = new HashMap<>();
        LogUtil.info(username);
        RegisterNameDto dto = new RegisterNameDto();
        dto.setValid(true);
//        map.put("valid", true);
        return dto;
    }

    @RequestMapping(value = "/regi", method = RequestMethod.POST)
    RegisterDto regi(@RequestBody RegisterDto dto){
        LogUtil.info(dto.toString());
        return dto;
    }



}
