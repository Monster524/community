package com.monster.service;

import com.monster.entity.Complaint;
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
public interface ComplaintService extends IService<Complaint> {

    List<Complaint> selectByMap(Map<String, Object> map);
}
