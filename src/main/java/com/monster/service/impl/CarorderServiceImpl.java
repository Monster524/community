package com.monster.service.impl;

import com.monster.entity.*;
import com.monster.mapper.*;
import com.monster.service.CarorderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author monster
 * @since 2021-05-20
 */
@Service
public class CarorderServiceImpl extends ServiceImpl<CarorderMapper, Carorder> implements CarorderService {
    @Autowired
    ParkingMapper parkingMapper;
    @Autowired
    PayMapper payMapper;
    @Autowired
    CarorderMapper carorderMapper;

    @Override
    public boolean save(Map<String, Object> map) {
        /*
        传入id
         */
        Carorder carorder = new Carorder();
        carorder.setCommunityId((Integer) map.get("communityId"));

        Parking parking = parkingMapper.selectById((Serializable) map.get("parkingId"));
        Pay pay = payMapper.selectById((Serializable) map.get("payId"));
        carorder.setParking(parking);
        carorder.setPay(pay);


        LocalDateTime now = LocalDateTime.now();
        carorder.setOrderDate(now);

        int formula = pay.getPayCalculate();
        float total = 0;
        switch (formula){
            case 2: total = pay.getPayCost();
                break;
            default:
                System.out.println("error\n");
        }
        carorder.setOrderTotal(total);
        carorder.setOrderContent("停车费："+pay.getPayCost()+"\n"+"总金额："+total+"\n");
        carorder.setOrderStatus(0);

        boolean status = carorderMapper.save(carorder);
        return status;
    }

    @Override
    public List<Carorder> list(Map<String, Object> map) {
        return carorderMapper.list(map);
    }

    @Override
    public List<Carorder> selectByMap(Map<String, Object> map) {
        return carorderMapper.selectByMap(map);
    }

    @Override
    public List<Carorder> owner(Map<String, Object> map) {
        return carorderMapper.owner(map);
    }
}
