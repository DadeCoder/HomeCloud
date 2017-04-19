package com.dade.core.user.purchaser;

import com.dade.common.dto.ImageHeadDto;
import com.dade.common.utils.ImageUtil;
import com.dade.common.utils.LogUtil;
import com.dade.core.general.RegisterDao;
import com.dade.core.house.House;
import com.dade.core.house.HouseDao;
import com.dade.core.house.dto.HouseDto;
import com.dade.core.house.dto.HouseDtoFactory;
import com.dade.core.user.agent.UserDto;
import com.netflix.discovery.converters.Auto;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @Autowired
    RestTemplate restTemplate;

    public List<UserDto> getAllUsers(){
        List<Purchaser> users = purchaserDao.getAllUsers();
        List<UserDto> res = new ArrayList<>();

        for (Purchaser user : users){
            UserDto dto = new UserDto();
            BeanUtils.copyProperties(user, dto);
            if (user.getBuyHouseList() != null)
                dto.setBuyNo(user.getBuyHouseList().size());
            if (user.getRentHouseList() != null)
                dto.setRentNo(user.getRentHouseList().size());
            if (user.getRentOutHouseList() != null)
                dto.setRentOutNo(user.getRentOutHouseList().size());
            if (user.getSellHouseList() != null)
                dto.setSellNo(user.getSellHouseList().size());

            res.add(dto);
        }

        return res;
    }

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

        List<PurchaserHouse> purchaserHouseList = purchaser.getFocusHouseList();
        List<House> houseList = new ArrayList<>();

        HashSet<String> hs = new HashSet<String>();
        purchaserHouseList.stream().forEach( ph -> hs.add(ph.getHouseId()));
        List<PurchaserHouse> purchaserHouses = new ArrayList<>();
        for (String id : hs){
            for (PurchaserHouse ph : purchaserHouseList){
                if (ph.getHouseId().equals(id)){
                    purchaserHouses.add(ph);
                    break;
                }
            }
        }

        for (PurchaserHouse purchaserHouse : purchaserHouses){
            House house = houseDao.findById(purchaserHouse.getHouseId());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
            LogUtil.warn(sdf.format(purchaserHouse.getTime()));
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

        List<PurchaserHouse> purchaserHouseList = purchaser.getFocusHouseList();
        List<House> houseList = new ArrayList<>();

        HashSet<String> hs = new HashSet<String>();
        purchaserHouseList.stream().forEach( ph -> hs.add(ph.getHouseId()));
        List<PurchaserHouse> purchaserHouses = new ArrayList<>();
        for (String id : hs){
            for (PurchaserHouse ph : purchaserHouseList){
                if (ph.getHouseId().equals(id)){
                    purchaserHouses.add(ph);
                    break;
                }
            }
        }

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


    public List<HouseDto> getAllRent(String phone){

        Purchaser purchaser = purchaserDao.getByPhoneNumber(phone);

        if (purchaser==null)
            return new ArrayList<>();

        List<PurchaserHouse> rentHouseList = purchaser.getRentOutHouseList();

        List<House> houseList = new ArrayList<>();

        for (PurchaserHouse purchaserHouse : rentHouseList){
            House house = houseDao.findById(purchaserHouse.getHouseId());
            houseList.add(house);
        }

        List<HouseDto> res = HouseDtoFactory.getHouseDto(houseList);

        return res;


    }

    public List<HouseDto> getRent(String phone){

        Purchaser purchaser = purchaserDao.getByPhoneNumber(phone);

        if (purchaser==null)
            return new ArrayList<>();

        List<PurchaserHouse> rentHouseList = purchaser.getRentOutHouseList();

        List<House> houseList = new ArrayList<>();

        for (PurchaserHouse purchaserHouse : rentHouseList){
            House house = houseDao.getById(purchaserHouse.getHouseId());
            if (house.getAccess() == House.ACCESS_PASS)
                houseList.add(house);
        }

        List<HouseDto> res = HouseDtoFactory.getHouseDto(houseList);

        return res;


    }

    public List<HouseDto> getBuy(String phone){
        Purchaser purchaser = purchaserDao.getByPhoneNumber(phone);

        if (purchaser==null)
            return new ArrayList<>();

        List<PurchaserHouse> sellHouseList = purchaser.getBuyHouseList();

        List<House> houseList = new ArrayList<>();

        for (PurchaserHouse purchaserHouse : sellHouseList){
            House house = houseDao.findById(purchaserHouse.getHouseId());
            if (house != null)
                houseList.add(house);
        }

        if (houseList.size()==0)
            return new ArrayList<>();

        List<HouseDto> res = HouseDtoFactory.getHouseDto(houseList);

        return res;
    }

    public List<HouseDto> getRented(String phone){
        Purchaser purchaser = purchaserDao.getByPhoneNumber(phone);

        if (purchaser==null)
            return new ArrayList<>();

        List<PurchaserHouse> sellHouseList = purchaser.getRentHouseList();

        List<House> houseList = new ArrayList<>();

        for (PurchaserHouse purchaserHouse : sellHouseList){
            House house = houseDao.findById(purchaserHouse.getHouseId());
            if (house != null)
                houseList.add(house);
        }

        if (houseList.size()==0)
            return new ArrayList<>();

        List<HouseDto> res = HouseDtoFactory.getHouseDto(houseList);

        return res;
    }

    public List<HouseDto> getAllSell(String phone){
        Purchaser purchaser = purchaserDao.getByPhoneNumber(phone);

        if (purchaser==null)
            return new ArrayList<>();

        List<PurchaserHouse> sellHouseList = purchaser.getSellHouseList();

        List<House> houseList = new ArrayList<>();

        for (PurchaserHouse purchaserHouse : sellHouseList){
            House house = houseDao.getById(purchaserHouse.getHouseId());
            if (house != null)
                houseList.add(house);
        }

        if (houseList.size()==0)
            return new ArrayList<>();

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
            House house = houseDao.getById(purchaserHouse.getHouseId());
            if (house != null && house.getAccess() == House.ACCESS_PASS)
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

//        List<PurchaserHouse> focusList = purchaser.getBuyHouseList();
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

    public void cancelFocus(String houseId, String purchaserId){
        purchaserDao.cancelFocus(houseId, purchaserId);
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

    public String imageHead(String data, MultipartFile file, String phone){
        final String uri = "http://127.0.0.1:8092/api/purchaser/image_head/";
        ImageHeadDto dto = new ImageHeadDto();

        String ext = FilenameUtils.getExtension(file.getOriginalFilename());
        File ffile = new File("E:/ImageServer/test/default." + ext);
        dto.setData(data);
        dto.setPhone(phone);

        try {
            InputStream input = file.getInputStream();  //这里得包括文件的路径与文件名
            FileOutputStream output = new FileOutputStream(ffile);   //这里是复制的路径，但是也要加复制后的文件名

            byte[] bt = new byte[1024];     //设置每次读多少
            int c;
            while((c=input.read(bt)) > 0){    //读出来
                output.write(bt,0,c);         //写进去
            }
            //关闭输入、输出流
            input.close();
            output.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dto.setFile(ffile);

        String res = restTemplate.postForObject( uri, dto, String.class);
        purchaserDao.saveImage(res, phone);
        String imageHeadUrl = "http://127.0.0.1:8089/" + res;

        return imageHeadUrl;
    }

    /**
     * user change their headimage
     * @param src
     * @param data
     * @param file
     * @return
     */
    @Deprecated
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
