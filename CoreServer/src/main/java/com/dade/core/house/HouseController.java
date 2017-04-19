package com.dade.core.house;

import com.dade.common.utils.LogUtil;
import com.dade.core.house.dto.*;
import com.dade.core.user.purchaser.Purchaser;
import com.dade.core.user.purchaser.PurchaserHouse;
import com.dade.core.user.purchaser.PurchaserService;
import com.netflix.discovery.converters.Auto;
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

    @Autowired
    PurchaserService purchaserService;

    @RequestMapping("/getBeforeOrder")
    public List<HouseDto> getBeforeOrder(Principal principal){
        return houseServices.getBeforeOrder(principal.getName());
    }

    @RequestMapping("/getAfterOrder")
    public List<HouseDto> getAfterOrder(Principal principal){
        return houseServices.getAfterOrder(principal.getName());
    }

    @RequestMapping("/isOrder")
    public boolean isOrder(@RequestParam String houseId, Principal principal){
        return houseServices.isOrder(houseId, principal.getName());
    }

    @RequestMapping("/order")
    public void order(@RequestParam String houseId, Principal principal){
        houseServices.order(houseId, principal.getName());
    }

    @RequestMapping("/getPriceAll")
    public List<HouseDto> getPriceAll(Principal principal){
        return purchaserService.getPriceAll(principal.getName());
    }

    @RequestMapping("/getPriceUp")
    public List<HouseDto> getPriceUp(Principal principal){
        return purchaserService.getPriceUp(principal.getName());
    }

    @RequestMapping("/getPriceDown")
    public List<HouseDto> getPriceDown(Principal principal){
        return purchaserService.getPriceDown(principal.getName());
    }

    @RequestMapping("/changePrice")
    public void changePrice(@RequestParam String houseId, @RequestParam Integer price){
        houseServices.changePrice(houseId, price);
    }

    @RequestMapping("/stopRent")
    public void stopRent(@RequestParam String houseId, Principal principal){
        houseServices.stopRent(houseId, principal.getName());
    }

    @RequestMapping("/stopSell")
    public void stopSell(@RequestParam String houseId, Principal principal){
        houseServices.stopSell(houseId, principal.getName());
    }

    @RequestMapping("/rent")
    public void rent(@RequestParam String houseId, Principal principal){
        houseServices.rent(houseId, principal.getName());
    }

    @RequestMapping("/buy")
    public void buy(@RequestParam String houseId, Principal principal){
        houseServices.buy(houseId, principal.getName());
    }

    @RequestMapping("/getFocusRent")
    public List<HouseDto> getFocusRent(Principal principal){
        List<HouseDto> res = purchaserService.getFocusRent(principal.getName());
        return res;
    }

    @RequestMapping("/getFocusSell")
    public List<HouseDto> getFocusSell(Principal principal){
        List<HouseDto> res = purchaserService.getFocusSell(principal.getName());
        return res;
    }

    @RequestMapping("/isFocus")
    public Boolean isFocus(@RequestParam String houseId, Principal principal){
        Boolean res = purchaserService.isFocus(houseId, principal.getName());
        return res;
    }

    @RequestMapping("/focus")
    public void focus(@RequestParam String houseId, Principal principal){
        purchaserService.focus(houseId, principal.getName());

        LogUtil.info("principal: " + principal.getName());

    }

    @RequestMapping("/cancel_focus")
    public void cancelFocus(@RequestParam String houseId, Principal principal){
        purchaserService.cancelFocus(houseId, principal.getName());

        LogUtil.info("principal: " + principal.getName());

    }

    @RequestMapping(value = "/getRentHouse")
    public HouseDto getRentHouse(@RequestParam String houseId){
        HouseDto dto = houseServices.getById(houseId);
        return dto;
    }

    @RequestMapping("/get")
    public HouseDto get(@RequestParam String houseId){
        HouseDto dto = houseServices.getById(houseId);
        //houseServices.savePicUrl(houseId, picUrl);
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
    public String upLoad(@RequestParam("file") MultipartFile file,
                         @RequestParam("houseId") String houseId){

//        String path = houseServices.savePic(file);
        String path = houseServices.savePicServer(file);
        LogUtil.info(houseId);

        String imageHeadUrl = "http://127.0.0.1:8089/" + path;
        LogUtil.info(imageHeadUrl);

        houseServices.savePicUrl(houseId, imageHeadUrl);

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
        Purchaser purchaser = purchaserService.getByPhone(principal.getName());
        List<PurchaserHouse> sellHouseList = purchaser.getSellHouseList();
        LogUtil.info(dto.toString());
        List<HouseDto> res = dto.getRes();
        for (HouseDto hd : res){
            for (PurchaserHouse ph : sellHouseList){
                if (hd.getId().equals(ph.getHouseId())){
                    hd.setSelf(true);
                }
            }
        }

        return dto;
    }

    @RequestMapping(value = "/search_rent", method = RequestMethod.POST)
    public  HouseSearchDto searchRent(@RequestBody List<String> condition, Principal principal){
        HouseSearchDto dto =  houseServices.getSearchRent(condition);


        Purchaser purchaser = purchaserService.getByPhone(principal.getName());
        List<PurchaserHouse> rentOutHouseList = purchaser.getRentOutHouseList();
        LogUtil.info(dto.toString());
        List<HouseDto> res = dto.getRes();
        for (HouseDto hd : res){
            for (PurchaserHouse ph : rentOutHouseList){
                if (hd.getId().equals(ph.getHouseId())){
                    hd.setSelf(true);
                }
            }
        }

//        LogUtil.info(dto.toString());

        return dto;
    }


}
