package com.monster.controller;


import cn.hutool.db.handler.HandleHelper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.monster.auth.service.AuthService;
import com.monster.common.utils.Result;
import com.monster.entity.*;
import com.monster.service.HouseorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author monster
 * @since 2021-05-13
 */
@RestController
@RequestMapping("//order")
public class HouseorderController {


    @Autowired
    AuthService authService;
    @Autowired
    HouseorderService houseorderService;

    @GetMapping("/list")
    public Result list(@RequestParam("token") String token){
        Admin admin = authService.findAdminByToken(token);
        int id = admin.getCommunityId();
       // return  Result.build(200,"查找成功",houseorderService.list(id));
       // List<Houseorder> doList = houseorderService.list(new QueryWrapper<Houseorder>().eq("community_id",id).eq("order_status",1)) ;
       // List<Houseorder> undoList = houseorderService.list(new QueryWrapper<Houseorder>().eq("community_id",id).eq("order_status",0));

        List<Houseorder> doList = houseorderService.list(new QueryWrapper<Houseorder>().eq("community_id",id).eq("order_status",1));
        List<Houseorder> undoList = houseorderService.list(new QueryWrapper<Houseorder>().eq("community_id",id).eq("order_status",0));
        Map<String,List> map = new HashMap<>();
        map.put("doList",doList);
        map.put("undoList",undoList);
        return Result.build(200,"查找成功",map);
    }

    @GetMapping("/findByToken")
    public Result owner(@RequestParam("token") String token,
                        @RequestParam(value = "year",required = false)Integer year,
                        @RequestParam(value = "month",required = false)Integer month){
        Owner owner = authService.findOwnerByToken(token);
        int id = owner.getCommunityId();
        // return  Result.build(200,"查找成功",houseorderService.list(id));
        // List<Houseorder> doList = houseorderService.list(new QueryWrapper<Houseorder>().eq("community_id",id).eq("order_status",1)) ;
        // List<Houseorder> undoList = houseorderService.list(new QueryWrapper<Houseorder>().eq("community_id",id).eq("order_status",0));
        Map<String,Object> map1 = new HashMap<>();
        map1.put("ownerUid",id);
        map1.put("orderStatus",1);
        map1.put("year",year);
        map1.put("month",month);

        Map<String,Object> map2 = new HashMap<>();
        map2.put("ownerUid",id);
        map2.put("orderStatus",0);
        map2.put("year",year);
        map2.put("month",month);
        List<Houseorder> doList = houseorderService.owner(map1);
        List<Houseorder> undoList = houseorderService.owner(map2);

        Map<String,List> map = new HashMap<>();
        map.put("doList",doList);
        map.put("undoList",undoList);
        return Result.build(200,"查找成功",map);
    }


    @GetMapping("/selectByMap")
    public Result list(@RequestParam("token") String token,
                       @RequestParam(value = "orderStatus",required = false)Integer status,
                       @RequestParam(value = "houseId",required = true)Integer houseId){
        Admin admin = authService.findAdminByToken(token);
        int id = admin.getCommunityId();
        Map<String,Object> map = new HashMap<>();
        map.put("communityId",id);
        map.put("orderStatus",status);
        map.put("houseId",houseId);
        return  Result.build(200,"查找成功",houseorderService.selectByMap(map));
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
    }

    @DeleteMapping("/delete")
    public Result deleteById(@RequestParam("orderId") Integer orderId){
        boolean status = houseorderService.removeById(orderId);
        if(status==false){
            return Result.build(400,"删除失败");
        }else{
            return Result.build(200,"删除成功");
        }
    }

}

