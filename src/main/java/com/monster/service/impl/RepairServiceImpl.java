package com.monster.service.impl;

import com.monster.entity.Repair;
import com.monster.mapper.RepairMapper;
import com.monster.service.RepairService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author monster
 * @since 2021-04-03
 */
@Service
public class RepairServiceImpl extends ServiceImpl<RepairMapper, Repair> implements RepairService {
    @Autowired
    RepairMapper repairMapper;

    @Override
    public List<Repair> selectByMap(Map<String, Object> map) {
        return repairMapper.selectByMap(map);
    }
}
