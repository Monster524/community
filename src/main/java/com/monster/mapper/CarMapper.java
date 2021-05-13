package com.monster.mapper;

import com.monster.entity.Car;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.monster.entity.Parking;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author monster
 * @since 2021-04-03
 */
@Repository
public interface CarMapper extends BaseMapper<Car> {

    List<Car> list(Integer communityId);
    List<Car> selectByMap(Map map);
    boolean save(Car car);

    boolean add(Car car);
}
