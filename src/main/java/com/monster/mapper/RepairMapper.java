package com.monster.mapper;

import com.monster.entity.Complaint;
import com.monster.entity.Repair;
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
public interface RepairMapper extends BaseMapper<Repair> {
    List<Repair> selectByMap(Map<String,Object> map);
}
