package com.monster.service;

import com.monster.entity.Parking;
import com.baomidou.mybatisplus.extension.service.IService;
import com.monster.vo.ParkingVO;

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
public interface ParkingService extends IService<Parking> {
    List<Parking> list(Integer id);
    List<Parking> selectByMap(Map<String, Object> map);
}
