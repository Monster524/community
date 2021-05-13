package com.monster.mapper;

import com.monster.entity.Complaint;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
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
public interface ComplaintMapper extends BaseMapper<Complaint> {
    List<Complaint> selectByMap(Map<String,Object> map);
}
