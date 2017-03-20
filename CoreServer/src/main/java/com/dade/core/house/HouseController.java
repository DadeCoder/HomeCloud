package com.dade.core.house;

import com.dade.common.utils.LogUtil;
import com.dade.core.house.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * Created by Dade on 2017/3/20.
 */
@RestController
@RequestMapping("/api/house")
@CrossOrigin
public class HouseController {

    @Autowired
    HouseServices houseServices;

    @RequestMapping("/get")
    public HouseDto get(@RequestParam String houseId){
        HouseDto dto = houseServices.getById(houseId);
        return dto;
    }

    @RequestMapping("/sellOut")
    public HouseSellOutResDto sellOut(@RequestBody HouseSellOutInpDto dto){
        LogUtil.info(dto.toString());
        HouseSellOutResDto res = new HouseSellOutResDto();
        return res;
    }

    @RequestMapping("/rentOut")
    public HouseRentOutResDto rentOut(@RequestBody HouseRentOutInpDto dto, Principal principal){
        LogUtil.info(dto.toString());
        HouseRentOutResDto res = houseServices.rentOut(dto, principal.getName());

        return res;
    }

}
