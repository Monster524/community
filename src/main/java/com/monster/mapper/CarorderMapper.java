package com.monster.mapper;

import com.monster.entity.Car;
import com.monster.entity.Carorder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.monster.entity.Houseorder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author monster
 * @since 2021-05-20
 */
@Repository
public interface CarorderMapper extends BaseMapper<Carorder> {



    boolean save(Carorder carorder);

    List<Carorder> list(Integer map);
    List<Carorder> owner(Map<String, Object> map);
    List<Carorder> selectByMap(Map<String, Object> map);

    List<Integer> getId(Map<String, Object> map);
}
