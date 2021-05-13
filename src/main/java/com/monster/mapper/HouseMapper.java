package com.monster.mapper;

import com.monster.entity.House;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface HouseMapper extends BaseMapper<House> {

    List<House> list(Integer id);

    List<House> selectByMap(Map<String,Object> map);



    void selectOwner(House house);
}
