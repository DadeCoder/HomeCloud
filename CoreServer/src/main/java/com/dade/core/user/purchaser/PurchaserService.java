package com.dade.core.user.purchaser;

import com.dade.common.utils.ImageUtil;
import com.dade.core.general.RegisterDao;
import com.dade.core.house.House;
import com.dade.core.house.HouseDao;
import com.dade.core.house.dto.HouseDto;
import com.dade.core.house.dto.HouseDtoFactory;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by Dade on 2017/3/19.
 */
@Component
public class PurchaserService {

    @Autowired
    PurchaserDao purchaserDao;

    @Autowired
    HouseDao houseDao;

    @Autowired
    RegisterDao registerDao;

    public void updateOrder(String houseId, String phone, Date time){
        Purchaser purchaser = getByPhone(phone);

        if (purchaser == null)
            return;

        List<PurchaserHouse> orderList = purchaser.getHouseScheduleList();

        PurchaserHouse purchaserHouse = new PurchaserHouse();
        purchaserHouse.setHouseId(houseId);
        purchaserHouse.setTime(time);

        orderList.add(purchaserHouse);

        purchaser.setHouseScheduleList(orderList);

        purchaserDao.save(purchaser);
    }

    public List<HouseDto> getPriceDown(String phone){
        Purchaser purchaser = getByPhone(phone);

        if (purchaser == null)
            return new ArrayList<>();

        List<PurchaserHouse> purchaserHouses = purchaser.getFocusHouseList();
        List<House> houseList = new ArrayList<>();

        for (PurchaserHouse purchaserHouse : purchaserHouses){
            House house = houseDao.findById(purchaserHouse.getHouseId());
            if (house.getSellPricePosition() == House.SELL_PRICE_DOWN || house.getRentPricePosition() == House.RENT_PRICE_DOWN)
                houseList.add(house);
        }

        List<HouseDto> res = HouseDtoFactory.getHouseDtoPrice(houseList);


        return res;
    }

    public List<HouseDto> getPriceAll(String phone){
        List<HouseDto> up = getPriceUp(phone);
        List<HouseDto> down = getPriceDown(phone);

        up.addAll(down);

        return up;
    }

    public List<HouseDto> getPriceUp(String phone){
        Purchaser purchaser = getByPhone(phone);

        if (purchaser == null)
            return new ArrayList<>();

        List<PurchaserHouse> purchaserHouses = purchaser.getFocusHouseList();
        List<House> houseList = new ArrayList<>();

        for (PurchaserHouse purchaserHouse : purchaserHouses){
            House house = houseDao.findById(purchaserHouse.getHouseId());
            if (house.getSellPricePosition() == House.SELL_PRICE_UP || house.getRentPricePosition() == House.RENT_PRICE_UP)
                houseList.add(house);
        }

        List<HouseDto> res = HouseDtoFactory.getHouseDtoPrice(houseList);


        return res;
    }

    public boolean newPwd(String oldHash, String newHash, String phone){
        Purchaser purchaser = getByPhone(phone);

        if (purchaser == null)
            return false;

        if (!purchaser.getPassword().equals(oldHash))
            return false;

        purchaser.setPassword(newHash);

        purchaserDao.save(purchaser);

        return true;
    }

    public boolean newNick(String nick, String phone){
        Purchaser purchaser = getByPhone(phone);

        if (purchaser == null)
            return false;

        if (registerDao.findByName(nick) != null)
            return false;


        purchaser.setName(nick);

        purchaserDao.save(purchaser);

        return true;
    }

    public String getNick(String phone){
        Purchaser purchaser = getByPhone(phone);

        if (purchaser == null)
            return "";

        return purchaser.getName();
    }

    public void updateStopRent(String houseId, String phone){
        Purchaser purchaser = getByPhone(phone);

        if (purchaser == null)
            return;

        List<PurchaserHouse> rentList = purchaser.getRentHouseList();

        for (PurchaserHouse purchaserHouse : rentList){
            if (purchaserHouse.getHouseId().equals(houseId)){
                rentList.remove(purchaserHouse);
                break;
            }
        }

        purchaserDao.save(purchaser);
    }

    public void updateStopSell(String houseId, String phone){
        Purchaser purchaser = getByPhone(phone);

        if (purchaser == null)
            return;

        List<PurchaserHouse> sellHouseList = purchaser.getSellHouseList();

        for (PurchaserHouse purchaserHouse : sellHouseList){
            if (purchaserHouse.getHouseId().equals(houseId)){
                sellHouseList.remove(purchaserHouse);
                break;
            }

        }

        purchaserDao.save(purchaser);
    }



    public List<HouseDto> getRent(String phone){

        Purchaser purchaser = purchaserDao.getByPhoneNumber(phone);

        if (purchaser==null)
            return new ArrayList<>();

        List<PurchaserHouse> rentHouseList = purchaser.getRentHouseList();

        List<House> houseList = new ArrayList<>();

        for (PurchaserHouse purchaserHouse : rentHouseList){
            House house = houseDao.findById(purchaserHouse.getHouseId());
            houseList.add(house);
        }

        List<HouseDto> res = HouseDtoFactory.getHouseDto(houseList);

        return res;


    }

    public List<HouseDto> getSell(String phone){

        Purchaser purchaser = purchaserDao.getByPhoneNumber(phone);

        if (purchaser==null)
            return new ArrayList<>();

        List<PurchaserHouse> sellHouseList = purchaser.getSellHouseList();

        List<House> houseList = new ArrayList<>();

        for (PurchaserHouse purchaserHouse : sellHouseList){
            House house = houseDao.findById(purchaserHouse.getHouseId());
            houseList.add(house);
        }

        List<HouseDto> res = HouseDtoFactory.getHouseDto(houseList);

        return res;


    }

    public void updateRent(String houseId, String phone){
        Purchaser purchaser = getByPhone(phone);

        List<PurchaserHouse> rentList = purchaser.getRentHouseList();

        PurchaserHouse purchaserHouse = new PurchaserHouse();
        purchaserHouse.setHouseId(houseId);
        purchaserHouse.setTime(new Date());

        rentList.add(purchaserHouse);
        purchaser.setRentHouseList(rentList);

        purchaserDao.save(purchaser);
    }

    public void updateBuy(String houseId, String phone){
        Purchaser purchaser = getByPhone(phone);

        List<PurchaserHouse> purchaserHouses = purchaser.getBuyHouseList();
        PurchaserHouse purchaserHouse = new PurchaserHouse();
        purchaserHouse.setHouseId(houseId);
        purchaserHouse.setTime(new Date());

        purchaserHouses.add(purchaserHouse);

        purchaser.setBuyHouseList(purchaserHouses);

        purchaserDao.save(purchaser);

//        purchaserDao.updateFirst(purchaser.getId(), purchaser);

    }

    public List<HouseDto> getFocusSell(String phone){
        Purchaser purchaser = getByPhone(phone);

        if (purchaser == null)
            return new ArrayList<>();

        List<PurchaserHouse> focusList = purchaser.getFocusHouseList();
        List<House> houseList = new ArrayList<>();

        for (PurchaserHouse purchaserHouse : focusList){
            House house = houseDao.findById(purchaserHouse.getHouseId());
            if (house.getOnlineType() == House.ONLINE_SELL)
                houseList.add(house);
        }

        List<HouseDto> res = HouseDtoFactory.getHouseDto(houseList);

        return res;
    }

    public List<HouseDto> getFocusRent(String phone){
        Purchaser purchaser = getByPhone(phone);

        if (purchaser == null)
            return new ArrayList<>();

        List<PurchaserHouse> focusList = purchaser.getFocusHouseList();
        List<House> houseList = new ArrayList<>();

        for (PurchaserHouse purchaserHouse : focusList){
            House house = houseDao.findById(purchaserHouse.getHouseId());
            if (house.getOnlineType() == House.ONLINE_RENT)
                houseList.add(house);
        }

        List<HouseDto> res = HouseDtoFactory.getHouseDto(houseList);

        return res;
    }

    public boolean isFocus(String houseId, String phone){
        return purchaserDao.isFocus(houseId, phone);
    }

    public void focus(String houseId, String purchaserId){
        purchaserDao.focus(houseId, purchaserId);
    }

    public String getInfo(String phone){
        Purchaser purchaser = purchaserDao.getByPhoneNumber(phone);
        String info = purchaser.getName();
        return info;
    }

    public String getImage(String phone){
        Purchaser purchaser = purchaserDao.getByPhoneNumber(phone);
        String info = purchaser.getImageHeaderUrl();
        return info;
    }

    public Purchaser getByPhone(String phone){
        Purchaser purchaser = purchaserDao.getByPhoneNumber(phone);
        return purchaser;
    }

    /**
     * 更新出租房屋列表
     * @param phone
     * @param houseId
     */
    public void updateHouseCreate(String phone, String houseId){
        Purchaser purchaser = getByPhone(phone);

        List<PurchaserHouse> rentOutHouseList = purchaser.getRentOutHouseList();
        PurchaserHouse purchaserHouse = new PurchaserHouse();
        purchaserHouse.setHouseId(houseId);
        purchaserHouse.setTime(new Date());
        rentOutHouseList.add(purchaserHouse);

        purchaser.setRentOutHouseList(rentOutHouseList);

        purchaserDao.atomicUpdate(purchaser);
    }

    /**
     * 更新卖房列表
     * @param phone
     * @param houseId
     */
    public void updateHouseCreateSell(String phone, String houseId){
        Purchaser purchaser = getByPhone(phone);

        List<PurchaserHouse> sellHouseList = purchaser.getSellHouseList();
        PurchaserHouse purchaserHouse = new PurchaserHouse();
        purchaserHouse.setHouseId(houseId);
        purchaserHouse.setTime(new Date());
        sellHouseList.add(purchaserHouse);

        purchaser.setSellHouseList(sellHouseList);

        purchaserDao.atomicUpdate(purchaser);
    }


    /**
     * user change their headimage
     * @param src
     * @param data
     * @param file
     * @return
     */
    public String imageHead(String src, String data, MultipartFile file, String phone){

        JSONObject joData = new JSONObject(data);
        // 用户经过剪辑后的图片的大小
        double x = joData.getDouble("x");
        double y = joData.getDouble("y");
        double w =  joData.getDouble("width");
        double h =  joData.getDouble("height");

        String imageHeadUrl = null;

        try {
            DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String name = df.format(new Date());

            Random random = new Random();
            for(int i = 0 ;i<3 ;i++){
                name += random.nextInt(10);
            }

            // 文件后缀名称
            String ext = FilenameUtils.getExtension(file.getOriginalFilename());
//            String url = "E:/foreground/homeplus/angular-seed/app/upload/";
            String url = "E:/ImageServer/resources/";
//            String url = "http://127.0.0.1/";

            String path = name + "." + ext;
            File pic = new File(url, path);
            if(!pic.exists()){
                pic.mkdirs();
                InputStream is = file.getInputStream();
                ImageUtil.cut(is, pic, (int)x, (int)y, (int)w, (int)h);
                is.close();
            }
//            imageHeadUrl = ".\\upload\\" + path;

//            imageHeadUrl = path;
            imageHeadUrl = "http://127.0.0.1:8089/" + path;

            purchaserDao.saveImage(path, phone);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return imageHeadUrl;
    }


}
