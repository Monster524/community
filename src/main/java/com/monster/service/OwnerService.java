package com.monster.service;

import com.monster.entity.Owner;
import com.baomidou.mybatisplus.extension.service.IService;
import com.monster.mapper.OwnerMapper;
import org.springframework.beans.factory.annotation.Autowired;

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
public interface OwnerService extends IService<Owner> {
    List<Owner> list(Integer id);
    List<Owner> selectByMap(Map<String, Object> map);
    Owner getById(Integer id);

    boolean updatePassword(Owner owner);
}
