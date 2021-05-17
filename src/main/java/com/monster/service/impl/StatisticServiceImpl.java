package com.monster.service.impl;

import com.monster.mapper.StatisticMapper;
import com.monster.service.StatisticService;
import com.monster.vo.StatisticVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatisticServiceImpl implements StatisticService {
    @Autowired
    StatisticMapper statisticMapper;

    @Override
    public Map<String,Object> list(){
        Map<String,Object> map = new HashMap<>();
        StatisticVO vo1 = statisticMapper.notpay();
        StatisticVO vo2 = statisticMapper.thismonth();
        StatisticVO vo3 = statisticMapper.thisyear();
        StatisticVO vo4 = statisticMapper.total();
        map.put("notpay",vo1);
        map.put("thismonth",vo2);
        map.put("thisyear",vo3);
        map.put("total",vo4);
        return map;
    }

    @Override
    public Map<String, Object> owner(Integer id) {
        Map<String,Object> map = new HashMap<>();
        StatisticVO vo1 = statisticMapper.ownernotpay(id);
        StatisticVO vo2 = statisticMapper.ownerthismonth(id);
        StatisticVO vo3 = statisticMapper.ownerthisyear(id);
        StatisticVO vo4 = statisticMapper.ownertotal(id);
        map.put("notpay",vo1);
        map.put("thismonth",vo2);
        map.put("thisyear",vo3);
        map.put("total",vo4);
        return map;
    }
}
