package com.monster.service;

import com.monster.entity.Houseorder;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author monster
 * @since 2021-05-13
 */
public interface HouseorderService extends IService<Houseorder> {

    boolean save(Map<String, Object> map);

    List<Houseorder> list(Map<String,Object> map);

    List<Houseorder> selectByMap(Map<String, Object> map);

    List<Houseorder> owner(Map<String, Object> map1);

    List<Integer> getId(Map<String, Object> map);
}
