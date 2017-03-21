package com.dade.core.house;

import com.dade.common.utils.LogUtil;
import com.dade.core.house.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public HouseDto get(@RequestParam String houseId,
                        @RequestParam String picUrl){
        HouseDto dto = houseServices.getById(houseId);
        houseServices.savePicUrl(houseId, picUrl);
        return dto;
    }

    @RequestMapping("/sellOut")
    public HouseRentOutResDto sellOut(@RequestBody HouseRentOutInpDto dto,
                                      Principal principal){

        LogUtil.info(dto.toString());
        HouseRentOutResDto res = houseServices.sellOut(dto, principal.getName());

        return res;
    }

//    @RequestMapping("/sellOut")
//    public HouseSellOutResDto sellOut(@RequestParam MultipartFile file,
//                                      @RequestBody HouseSellOutInpDto dto){
//        LogUtil.info(dto.toString());
//        HouseSellOutResDto res = new HouseSellOutResDto();
//
//        return res;
//    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upLoad(@RequestParam("file") MultipartFile file){

        String path = houseServices.savePic(file);
        String imageHeadUrl = "http://127.0.0.1:8089/" + path;

        LogUtil.info(imageHeadUrl);

        return imageHeadUrl;

    }



    @RequestMapping("/rentOut")
    public HouseRentOutResDto rentOut(@RequestBody HouseRentOutInpDto dto,
                                      Principal principal){

        LogUtil.info(dto.toString());
        HouseRentOutResDto res = houseServices.rentOut(dto, principal.getName());

        return res;
    }

}
