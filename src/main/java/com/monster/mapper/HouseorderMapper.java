package com.monster.mapper;

import com.monster.entity.Houseorder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author monster
 * @since 2021-05-13
 */
@Repository
public interface HouseorderMapper extends BaseMapper<Houseorder> {

    boolean save(Houseorder houseorder);

    List<Houseorder> list(int id);

    List<Houseorder> selectByMap(Map<String,Object> map);
}
