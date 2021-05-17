package com.monster.service;

import com.monster.entity.Car;
import com.monster.entity.House;
import com.baomidou.mybatisplus.extension.service.IService;
import javafx.beans.binding.ObjectExpression;

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
public interface HouseService extends IService<House> {
    List<House> list(Integer id);
    List<House> selectByMap(Map<String,Object> map);

    boolean selectOwner(House house);
}
