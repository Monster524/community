package com.monster.service.impl;

import com.monster.entity.Parking;
import com.monster.mapper.ParkingMapper;
import com.monster.service.ParkingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.monster.vo.ParkingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.IntFunction;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author monster
 * @since 2021-04-03
 */
@Service
public class ParkingServiceImpl extends ServiceImpl<ParkingMapper, Parking> implements ParkingService {
    @Autowired
    ParkingMapper parkingMapper;


    @Override
    public List<Parking> list(Integer id) {
        return parkingMapper.list(id);
    }

    @Override
    public List<Parking> selectByMap(Map<String, Object> map) {
        return parkingMapper.selectByMap(map);
    }

    @Override
    public void update(Parking parking) {
        parkingMapper.update(parking);
    }



}
