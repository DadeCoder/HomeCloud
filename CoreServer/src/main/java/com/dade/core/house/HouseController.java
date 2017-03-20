package com.dade.core.house;

import com.dade.common.utils.LogUtil;
import com.dade.core.house.dto.HouseRentOutInpDto;
import com.dade.core.house.dto.HouseRentOutResDto;
import com.dade.core.house.dto.HouseSellOutInpDto;
import com.dade.core.house.dto.HouseSellOutResDto;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Dade on 2017/3/20.
 */
@RestController
@RequestMapping("/api/house")
@CrossOrigin
public class HouseController {

    @RequestMapping("/sellOut")
    public HouseSellOutResDto sellOut(@RequestBody HouseSellOutInpDto dto){
        LogUtil.info(dto.toString());
        HouseSellOutResDto res = new HouseSellOutResDto();
        return res;
    }

    @RequestMapping("/rentOut")
    public HouseRentOutResDto rentOut(@RequestBody HouseRentOutInpDto dto){
        LogUtil.info(dto.toString());
        HouseRentOutResDto res = new HouseRentOutResDto();
        res.setInfo("success!");
        return res;
    }

}
