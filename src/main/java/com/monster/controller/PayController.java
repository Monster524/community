package com.monster.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.monster.auth.service.AuthService;
import com.monster.common.utils.Result;
import com.monster.entity.Admin;
import com.monster.entity.Community;
import com.monster.entity.Pay;
import com.monster.service.CommunityService;
import com.monster.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author monster
 * @since 2021-04-03
 */
@RestController
@RequestMapping("//pay")
public class PayController {

    @Autowired
    private PayService payService;
    @Autowired
    private AuthService authService;

    @GetMapping("/list")
    public Result list(@RequestParam("token") String token){
        Admin admin = authService.findAdminByToken(token);
        int id = admin.getCommunityId();
        return  Result.build(200,"查找成功",payService.list());
    }

    @GetMapping("/getById}")
    public Pay find(@RequestParam("id") Integer id){
        return this.payService.getById(id);
    }

    @PostMapping("/add")
    public Result add(@RequestParam("token") String token, @RequestBody Pay pay){
        boolean status = payService.save(pay);
        if(status==true){
            return Result.build(200,"添加成功");
        }else{
            return Result. build(400,"添加失败");
        }
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam("payId") Integer id){
        boolean status = payService.removeById(id);
        if(status==false){
            return Result.build(400,"删除失败");
        }else{
            return Result.build(200,"删除成功");
        }
    }


}

