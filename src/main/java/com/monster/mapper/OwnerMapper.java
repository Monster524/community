package com.monster.mapper;

import com.monster.entity.Owner;
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
public interface OwnerMapper extends BaseMapper<Owner> {

    List<Owner> selectByMap(Map<String, Object> map);

    List<Owner> list(Integer id);

    Owner getById(Integer id);

    void updatePassword(Owner owner);
}
