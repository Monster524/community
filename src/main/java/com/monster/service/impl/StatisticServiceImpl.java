package com.monster.service.impl;

import com.monster.mapper.StatisticMapper;
import com.monster.service.StatisticService;
import com.monster.vo.StatisticVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
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
        Map<String,Object> map = new LinkedHashMap<>();

        StatisticVO voh1 = statisticMapper.ownernotpay(id);
        StatisticVO voh2 = statisticMapper.ownerthismonth(id);
        StatisticVO voh3 = statisticMapper.ownerthisyear(id);
        StatisticVO voh4 = statisticMapper.ownertotal(id);
        map.put("notpayhouse",voh1);
        map.put("thismonthhouse",voh2);
        map.put("thisyearhouse",voh3);
        map.put("totalhouse",voh4);

        StatisticVO voc1 = statisticMapper.ownernotpay2(id);
        StatisticVO voc2 = statisticMapper.ownerthismonth2(id);
        StatisticVO voc3 = statisticMapper.ownerthisyear2(id);
        StatisticVO voc4 = statisticMapper.ownertotal2(id);
        map.put("notpaycar",voc1);
        map.put("thismonthcar",voc2);
        map.put("thisyearcar",voc3);
        map.put("totalcar",voc4);
        StatisticVO vo1 = new StatisticVO();
        vo1.setCount(voh1.getCount()+voc1.getCount());
        vo1.setSum(voh1.getSum()+voc1.getSum());
        StatisticVO vo2 = new StatisticVO();
        vo2.setCount(voh2.getCount()+voc2.getCount());
        vo2.setSum(voh2.getSum()+voc2.getSum());
        StatisticVO vo3 = new StatisticVO();
        vo3.setCount(voh3.getCount()+voc3.getCount());
        vo3.setSum(voh3.getSum()+voc3.getSum());
        StatisticVO vo4 = new StatisticVO();
        vo4.setCount(voh4.getCount()+voc4.getCount());
        vo4.setSum(voh4.getSum()+voc4.getSum());
        map.put("notpay",vo1);
        map.put("thismonth",vo2);
        map.put("thisyear",vo3);
        map.put("total",vo4);
        return map;
    }
}
