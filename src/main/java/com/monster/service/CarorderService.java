package com.monster.service;

import com.monster.entity.Carorder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.monster.entity.Houseorder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author monster
 * @since 2021-05-20
 */

public interface CarorderService extends IService<Carorder> {
    boolean save(Map<String, Object> map);
    List<Carorder> list(Map<String,Object> map);

    List<Carorder> selectByMap(Map<String, Object> map);
    List<Carorder> owner(Map<String, Object> map1);

}
