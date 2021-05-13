package com.monster.controller;

import com.monster.auth.service.AuthService;
import com.monster.common.utils.Result;
import com.monster.entity.Admin;
import com.monster.service.StatisticService;
import com.sun.jndi.cosnaming.CNCtx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("//statistic")
public class StatisticController {
    @Autowired
    private AuthService authService;
    @Autowired
    private StatisticService statisticService;
    @GetMapping("/list")
    public Result list(@RequestParam("token") String token){
        Admin admin = authService.findAdminByToken(token);
        int id = admin.getCommunityId();

        return  Result.build(200,"查找成功",statisticService.list());
    }

}
