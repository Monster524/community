package com.monster.service.impl;

import com.monster.entity.House;
import com.monster.entity.Houseorder;
import com.monster.entity.Pay;
import com.monster.mapper.HouseMapper;
import com.monster.mapper.HouseorderMapper;
import com.monster.mapper.PayMapper;
import com.monster.service.HouseorderService;
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
 * @since 2021-05-13
 */
@Service
public class HouseorderServiceImpl extends ServiceImpl<HouseorderMapper, Houseorder> implements HouseorderService {
    @Autowired
    HouseMapper houseMapper;
    @Autowired
    PayMapper payMapper;
    @Autowired
    HouseorderMapper houseorderMapper;
    @Override
    public boolean save(Map<String, Object> map) {
        /*
        传入三个id
         */
        Houseorder houseorder = new Houseorder();

        House house = houseMapper.selectById((Serializable) map.get("houseId"));
        Pay pay = payMapper.selectById((Serializable) map.get("payId"));
        houseorder.setHouse(house);
        houseorder.setPay(pay);


        LocalDateTime now = LocalDateTime.now();
        houseorder.setOrderDate(now);

        int formula = pay.getPayCalculate();
        float total = 0;
        switch (formula){
            case 1: total = pay.getPayUnitprice()*house.getHouseArea()+ pay.getPayCost();
            break;
            case 2: total = pay.getPayCost();
            break;
            default:
                System.out.println("error\n");
        }
        houseorder.setOrderTotal(total);
        houseorder.setOrderContent("单价："+pay.getPayUnitprice()+"\n"+"固定收费："+pay.getPayCost()+"\n"+"总金额："+total+"\n");
        houseorder.setOrderStatus(0);
       // houseorder.setPayId((Integer) map.get("payId"));
        //houseorder.setHouseId((Integer) map.get("houseId"));
        houseorder.setCommunityId((Integer) map.get("communityId"));

        boolean status = houseorderMapper.save(houseorder);
        return status;
    }

    @Override
    public List<Houseorder> list(int id) {
        return houseorderMapper.list(id);
    }



    @Override
    public List<Houseorder> selectByMap(Map<String, Object> map) {
        return houseorderMapper.selectByMap(map);
    }

    @Override
    public List<Houseorder> owner(Map<String, Object> map) {
        return houseorderMapper.owner(map);
    }
}
