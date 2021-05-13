package com.monster.service.impl;

import com.monster.entity.Car;
import com.monster.entity.Parking;
import com.monster.mapper.CarMapper;
import com.monster.mapper.ParkingMapper;
import com.monster.service.CarService;
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
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements CarService {
    @Autowired
    CarMapper carMapper;
    @Autowired
    ParkingMapper parkingMapper;
    @Override
    public List<Car> list(Integer id) {
        return carMapper.list(id);
    }

    @Override
    public List<Car> selectByMap(Map<String, Object> map) {
        return carMapper.selectByMap(map);
    }

    @Override
    public boolean removeById(Integer carId,Integer parkingId) {
        carMapper.deleteById(carId);
        Parking parking = new Parking();
        parking.setParkingId(parkingId);
        parking.setParkingStatus(0);
        parkingMapper.updateById(parking);
        return true;
    }

    @Override
    public boolean add(Car car) {
        try{
            carMapper.add(car);
        }catch (Exception e){
            return  false;
        }

        return true;
    }


}
