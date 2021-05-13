package com.monster.mapper;

import com.monster.entity.Parking;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.monster.vo.ParkingVO;
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
public interface ParkingMapper extends BaseMapper<Parking> {
     List<Parking> list(Integer communityId);
     List<Parking> selectByMap(Map<String,Object> map);
}
