package com.monster.mapper;

import com.monster.vo.StatisticVO;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface StatisticMapper {
    Map<String,Object> list();

    StatisticVO notpay();

    StatisticVO thismonth();


    StatisticVO total();

    StatisticVO thisyear();

    StatisticVO ownernotpay(Integer id);

    StatisticVO ownerthismonth(Integer id);

    StatisticVO ownerthisyear(Integer id);

    StatisticVO ownertotal(Integer id);
}
