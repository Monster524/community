package com.monster.service.impl;

import com.monster.entity.House;
import com.monster.mapper.HouseMapper;
import com.monster.service.HouseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author monster
 * @since 2021-04-03
 */
@Service
public class HouseServiceImpl extends ServiceImpl<HouseMapper, House> implements HouseService {
    @Autowired
    HouseMapper houseMapper;

    @Override
    public List<House> list(Integer id) {
        return houseMapper.list(id);
    }

    @Override
    public List<House> selectByMap(Map<String, Object> map) {
        return houseMapper.selectByMap(map);
    }

    @Override
    public boolean selectOwner(House house) {
        try{
            houseMapper.selectOwner(house);
        }catch (Exception e){
            return  false;
        }
        return true;
    }


}
