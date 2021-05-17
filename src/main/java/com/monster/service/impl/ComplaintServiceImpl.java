package com.monster.service.impl;

import com.monster.entity.Complaint;
import com.monster.mapper.ComplaintMapper;
import com.monster.service.ComplaintService;
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
public class ComplaintServiceImpl extends ServiceImpl<ComplaintMapper, Complaint> implements ComplaintService {
    @Autowired
    ComplaintMapper complaintMapper;

    @Override
    public List<Complaint> selectByMap(Map<String, Object> map) {
        return complaintMapper.selectByMap(map);
    }

    @Override
    public List<Complaint> list(Complaint complaint) {
        return complaintMapper.list(complaint);
    }
}
