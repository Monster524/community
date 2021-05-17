package com.monster.service;

import org.springframework.stereotype.Repository;

import java.util.Map;


public interface StatisticService {
    Map<String,Object> list();

    Map<String,Object> owner(Integer id);
}
