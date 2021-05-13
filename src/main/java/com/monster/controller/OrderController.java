package com.monster.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.monster.auth.service.AuthService;
import com.monster.common.utils.Result;
import com.monster.entity.Admin;

import com.monster.entity.Houseorder;
import com.monster.service.HouseorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author monster
 * @since 2021-04-03
 */
@RestController
@RequestMapping("//testorder")
public class OrderController {
/*
    @Autowired
    AuthService authService;
    @Autowired
    HouseorderService houseorderService;

    @GetMapping("/list")
    public Result list(@RequestParam("token") String token){
        Admin admin = authService.findAdminByToken(token);
        int id = admin.getCommunityId();
        return  Result.build(200,"查找成功",houseorderService.list(new QueryWrapper<Houseorder>().eq("community_id",id)));
    }

    @PostMapping("/add")
    public Result add(@RequestParam("token") String token, @RequestBody Map<String,Object> map){
        Admin admin = authService.findAdminByToken(token);
        int id = admin.getCommunityId();
        map.put("communityId",id);
        boolean status = houseorderService.save(map);
        if(status==true){
            return Result.build(200,"添加成功");
        }else{
            return Result. build(400,"添加失败");
        }
    }

    @PutMapping("/update")
    public Result update(@RequestBody Houseorder houseorder){
        boolean status = houseorderService.updateById(houseorder);
        if(status==false){
            return Result.build(400,"更新失败");
        }else{
            return Result.build(200,"更新成功");
        }
    }*/
}

