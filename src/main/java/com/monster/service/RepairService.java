package com.monster.service;

import com.monster.entity.Repair;
import com.baomidou.mybatisplus.extension.service.IService;

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
public interface RepairService extends IService<Repair> {

    List<Repair> selectByMap(Map<String, Object> map);
    List<Repair> list(Repair repair);
}
