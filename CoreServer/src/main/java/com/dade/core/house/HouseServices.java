package com.dade.core.house;

import com.dade.common.dto.ImageHeadDto;
import com.dade.common.utils.DateUtil;
import com.dade.core.house.dto.*;
import com.dade.core.user.agent.Agent;
import com.dade.core.user.agent.AgentDao;
import com.dade.core.user.purchaser.Purchaser;
import com.dade.core.user.purchaser.PurchaserDao;
import com.dade.core.user.purchaser.PurchaserHouse;
import com.dade.core.user.purchaser.PurchaserService;
import com.netflix.discovery.converters.Auto;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by Dade on 2017/3/20.
 */
@Component
public class HouseServices {

    @Autowired
    HouseDao houseDao;

    @Autowired
    PurchaserService purchaserService;

    @Autowired
    AgentDao agentDao;

    @Autowired
    RestTemplate restTemplate;

    public List<HouseDto> getBeforeOrder(String phone){

        Purchaser purchaser = purchaserService.getByPhone(phone);

        if (purchaser == null)
            return new ArrayList<>();

        List<House> houseList = new ArrayList<>();

        List<PurchaserHouse> orderList = purchaser.getHouseScheduleList();

        for (PurchaserHouse order : orderList){
            if (order.getTime().before(new Date())){
                House house = houseDao.findById(order.getHouseId());
                houseList.add(house);
            }
        }

        List<HouseDto> res = HouseDtoFactory.getHouseDtoPrice(houseList);

        return res;
    }

    public List<HouseDto> getAfterOrder(String phone){

        Purchaser purchaser = purchaserService.getByPhone(phone);

        if (purchaser == null)
            return new ArrayList<>();

        List<House> houseList = new ArrayList<>();

        List<PurchaserHouse> orderList = purchaser.getHouseScheduleList();

        for (PurchaserHouse order : orderList){
            if (order.getTime().after(new Date())){
                House house = houseDao.findById(order.getHouseId());
                houseList.add(house);
            }
        }

        List<HouseDto> res = HouseDtoFactory.getHouseDtoPrice(houseList);

        return res;
    }

    public boolean isOrder(String houseId, String phone){

        House house = houseDao.findById(houseId);

        if (house == null)
            return false;

        Purchaser purchaser = purchaserService.getByPhone(phone);

        if (purchaser == null)
            return false;

        List<HousePurchaser> orderList = house.getOrderList();

        for (HousePurchaser order : orderList){
            if (order.getUserId().equals(purchaser.getId()) && order.getDate().after(new Date()))
                return false;
        }

        return true;
    }

    public void _order(String houseId, Date time, String phone){
        House house = houseDao.findById(houseId);

        if (house == null)
            return;

        Purchaser purchaser = purchaserService.getByPhone(phone);

        if (purchaser == null)
            return;

        List<HousePurchaser> orderList = house.getOrderList();
        HousePurchaser housePurchaser = new HousePurchaser();

        Date orderTime = time;

        housePurchaser.setUserId(purchaser.getId());
        housePurchaser.setDate(orderTime);

        orderList.add(housePurchaser);
        house.setOrderList(orderList);
        houseDao.save(house);

        purchaserService.updateOrder(houseId, phone, orderTime);
    }

    public void order(String houseId, String phone){

        House house = houseDao.findById(houseId);

        if (house == null)
            return;

        Purchaser purchaser = purchaserService.getByPhone(phone);

        if (purchaser == null)
            return;

        List<HousePurchaser> orderList = house.getOrderList();
        HousePurchaser housePurchaser = new HousePurchaser();

        Date orderTime = null;

        if (orderList.size() == 0){
            housePurchaser.setUserId(purchaser.getId());
            housePurchaser.setDate(DateUtil.getTomorrow());
            orderTime = DateUtil.getTomorrow();
        }else{
            Date last = orderList.get(orderList.size()-1).getDate();
            housePurchaser.setUserId(purchaser.getId());
            housePurchaser.setDate(DateUtil.addOneDay(last));
            orderTime = DateUtil.addOneDay(last);
        }

        orderList.add(housePurchaser);
        house.setOrderList(orderList);
        houseDao.save(house);

        purchaserService.updateOrder(houseId, phone, orderTime);

    }

    public void changePrice(String houseId, Integer price){

        House house = houseDao.findById(houseId);

        if (house == null)
            return;

        if (house.getOnlineType() == House.ONLINE_RENT){
            if (house.getRentPrice() < price)
                house.setSellPricePosition(House.SELL_PRICE_UP);
            else
                house.setSellPricePosition(House.SELL_PRICE_DOWN);
            house.setRentPrice(price);
        }else{
            if (house.getSellPrice() < price)
                house.setRentPricePosition(House.SELL_PRICE_UP);
            else
                house.setSellPricePosition(House.SELL_PRICE_DOWN);
            house.setSellPrice(price);
        }

        houseDao.save(house);
    }

    public void stopRent(String houseId, String phone){
        houseDao.delete(houseId);

        purchaserService.updateStopRent(houseId, phone);

    }

    public void stopSell(String houseId, String phone){

        houseDao.delete(houseId);

        purchaserService.updateStopSell(houseId, phone);
    }

    public void rent(String houseId, String phone){
        House house = houseDao.findById(houseId);

        Purchaser purchaser = purchaserService.getByPhone(phone);

        if (purchaser != null){
            house.setRenterId(purchaser.getId());
        }

        houseDao.save(house);

        purchaserService.updateRent(houseId, phone);

        // delete the house
        houseDao.delete(houseId);



    }

    public void buy(String houseId, String phone){
        House house = houseDao.findById(houseId);

        Purchaser purchaser = purchaserService.getByPhone(phone);

        if (purchaser != null){
            house.setPurchaserId(purchaser.getId());
        }

        houseDao.save(house);

        purchaserService.updateBuy(houseId, phone);

        // delete the house
        houseDao.delete(houseId);
    }

    public HouseSearchDto getSearchRent(List<String> condition){
        List<House> houseList = houseDao.getSearchRent(condition);
        List<HouseDto> houseDtoList = HouseDtoFactory.getHouseDto(houseList);
        HouseSearchDto dto = new HouseSearchDto();
        dto.setRes(houseDtoList);
        return dto;
    }

    public HouseSearchDto getSearch(List<String> condition){
        List<House> houseList = houseDao.getSearch(condition);
        List<HouseDto> houseDtoList = HouseDtoFactory.getHouseDto(houseList);
        HouseSearchDto dto = new HouseSearchDto();
        dto.setRes(houseDtoList);
        return dto;
    }

    public HouseSearchDto getAllHouse(){
        List<House> houseList  = houseDao.getAllHouse();

        List<HouseDto> houseDtoList = HouseDtoFactory.getHouseDto(houseList);

        HouseSearchDto dto = new HouseSearchDto();
        dto.setRes(houseDtoList);
        return dto;
    }

    public HouseDto getById(String houseId){
        House house = houseDao.findById(houseId);
        HouseDto dto = HouseDtoFactory.getHouseDto(house);

        List<Agent> agentList = new ArrayList<>();

        // change agentid to agent
        for (int i=0;i<house.getAgentList().size();i++){
            Agent agent = agentDao.findById(house.getAgentList().get(i));
            agentList.add(agent);
        }

        dto.setRentWay(House.RENT_WAY_DEFAULT_ZH);

        dto.setAgentList(agentList);

        List<Record> records = new ArrayList<>();
        List<CheckRecord> checkRecords = house.getRecordList();

        for (CheckRecord record : checkRecords){
            Record r = new Record();
            r.setDate(record.getCheckDate());
            Agent agent = agentDao.findById(record.getCheckId());
            r.setName(agent.getName());
            r.setPhone(agent.getPhone());

            records.add(r);
        }

        dto.setRecords(records);

        return dto;
    }

    public void savePicUrl(String houseId ,String url){
        houseDao.savePicUrl(houseId, url);
    }

    public HouseRentOutResDto rentOut(HouseRentOutInpDto dto, String phone){
        House inHouse = HouseDtoFactory.getHouse(dto);

        inHouse.setOwnerId(phone);
        inHouse.setOnlineType(House.ONLINE_RENT);

        inHouse.setRentPricePosition(House.RENT_PRICE_DEFAULT);

        House dbHouse = houseDao.atomicCreate(inHouse);
        purchaserService.updateHouseCreate(phone, dbHouse.getId());

        HouseRentOutResDto res = HouseDtoFactory.getHouseRentOut(dbHouse);

        res.setInfo(dbHouse.getId());

        return res;
    }

    public HouseRentOutResDto sellOut(HouseRentOutInpDto dto, String phone){
        House inHouse = HouseDtoFactory.getHouse(dto);

        inHouse.setOwnerId(phone);
        inHouse.setOnlineType(House.ONLINE_SELL);

        inHouse.setSellPricePosition(House.SELL_PRICE_DEFAULT);

        House dbHouse = houseDao.atomicCreate(inHouse);

        // 更新到purchaser
        purchaserService.updateHouseCreateSell(phone, dbHouse.getId());

        HouseRentOutResDto res = HouseDtoFactory.getHouseRentOut(dbHouse);

        res.setInfo(dbHouse.getId());

        return res;
    }

    public String savePicServer(MultipartFile file) {

        String ext = FilenameUtils.getExtension(file.getOriginalFilename());
        File temp = new File("E:/ImageServer/test/default." + ext);

        try {
            InputStream input = file.getInputStream();
            FileOutputStream output = new FileOutputStream(temp);

            byte[] bt = new byte[1024];
            int c;
            while((c=input.read(bt)) > 0){
                output.write(bt,0,c);
            }

            input.close();
            output.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ImageHeadDto dto = new ImageHeadDto();
        dto.setFile(temp);

        final String uri = "http://127.0.0.1:8092/api/house/upload/";
        String res = restTemplate.postForObject( uri, dto, String.class);

        // save to db res - filename - add http,need houseID

        return res;
    }

    public String savePic(MultipartFile file) {

        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String name = df.format(new Date());

        Random random = new Random();
        for(int i = 0 ;i<3 ;i++){
            name += random.nextInt(10);
        }
//        // 文件后缀名称
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());
        String fileName = name + "." + ext;


        OutputStream os = null;
        InputStream fis = null;
        try {

            fis = file.getInputStream();

            String path = "E:/ImageServer/resources/";
            // 2、保存到临时文件
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流保存到本地文件

            File tempFile = new File(path);
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }
            os = new FileOutputStream(tempFile.getPath() + File.separator + fileName);
            // 开始读取
            while ((len = fis.read(bs)) != -1) {
                os.write(bs, 0, len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 完毕，关闭所有链接
            try {
                os.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return fileName;

    }

}
