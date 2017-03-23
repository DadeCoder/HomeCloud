package com.dade.core.test;

import com.dade.common.utils.DateUtil;
import com.dade.common.utils.LogUtil;
import com.dade.core.general.RegisterDao;
import com.dade.core.general.RegisterDto;
import com.dade.core.general.RegisterNameDto;
import com.dade.core.house.HouseServices;
import com.dade.core.user.purchaser.Purchaser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by Dade on 2017/3/13.
 */
@RestController
@RequestMapping("/api/test")
@CrossOrigin
public class TestController {

    @Autowired
    RegisterDao registerDao;

    /**
     * 注册
     * @param username
     * @return
     */
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

    @RequestMapping(value = "/findName", method = RequestMethod.GET)
    Purchaser findName(@RequestParam String username){
        LogUtil.info(username);
        Purchaser purchaser = registerDao.findByName(username);
        if (purchaser == null)
            return new Purchaser();
        else
            return purchaser;
    }

    @Autowired
    HouseServices houseServices;

    @RequestMapping("/order")
    public void testBeforeOrder(@RequestParam String houseId,
                                @RequestParam String phone){

        Date when = new Date(DateUtil.getYesterday().getTime());
        houseServices._order(houseId, when, phone);
    }


}
