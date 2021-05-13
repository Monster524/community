package com.monster.service;

import com.monster.entity.Car;
import com.baomidou.mybatisplus.extension.service.IService;
import com.monster.entity.Parking;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author monster
 * @since 2021-04-03
 */
public interface CarService extends IService<Car> {
    List<Car> list(Integer id);
    List<Car> selectByMap(Map<String, Object> map);
    boolean removeById(Integer carId,Integer parkingId);
    boolean add(Car car);
}
