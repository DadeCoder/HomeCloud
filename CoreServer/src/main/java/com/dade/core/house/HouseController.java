package com.dade.core.house;

import com.dade.common.utils.LogUtil;
import com.dade.core.house.dto.*;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Dade on 2017/3/20.
 */
@RestController
@RequestMapping("/api/house")
@CrossOrigin
public class HouseController {

    @Autowired
    HouseServices houseServices;

    @RequestMapping(value = "/getRentHouse")
    public HouseDto getRentHouse(@RequestParam String houseId){
        HouseDto dto = houseServices.getById(houseId);
        return dto;
    }

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


    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public  HouseSearchDto search(@RequestBody List<String> condition, Principal principal){
        HouseSearchDto dto =  houseServices.getSearch(condition);
        LogUtil.info(dto.toString());

        return dto;
    }

    @RequestMapping(value = "/search_rent", method = RequestMethod.POST)
    public  HouseSearchDto searchRent(@RequestBody List<String> condition, Principal principal){
        HouseSearchDto dto =  houseServices.getSearchRent(condition);
        LogUtil.info(dto.toString());

        return dto;
    }


}
