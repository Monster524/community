package com.monster.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.monster.auth.service.AuthService;
import com.monster.common.utils.Result;
import com.monster.entity.Admin;
import com.monster.entity.Carorder;
import com.monster.entity.Houseorder;
import com.monster.entity.Owner;
import com.monster.service.CarorderService;
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
 * @since 2021-05-20
 */
@RestController
@RequestMapping("//carorder")
public class CarorderController {
    @Autowired
    AuthService authService;
    @Autowired
    CarorderService carorderService;

    @GetMapping("/list")
    public Result list(@RequestParam("token") String token){
        Admin admin = authService.findAdminByToken(token);
        int id = admin.getCommunityId();
       // List<Carorder> doList = carorderService.list(new QueryWrapper<Carorder>().eq("community_id",id).eq("order_status",1));
       // List<Carorder> undoList = carorderService.list(new QueryWrapper<Carorder>().eq("community_id",id).eq("order_status",0));
        Map<String,Object> map1 = new HashMap<>();
        map1.put("communityId",id);
        map1.put("orderStatus",1);
        Map<String,Object> map2 = new HashMap<>();
        map2.put("communityId",id);
        map2.put("orderStatus",0);
        List<Carorder> doList = carorderService.list(map1);
        List<Carorder> undoList = carorderService.list(map2);

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
        int id = owner.getOwnerUid();
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
        List<Carorder> doList = carorderService.owner(map1);
        List<Carorder> undoList = carorderService.owner(map2);

        Map<String,List> map = new HashMap<>();
        map.put("doList",doList);
        map.put("undoList",undoList);
        return Result.build(200,"查找成功",map);
    }

    @GetMapping("/selectByMap")
    public Result list(@RequestParam("token") String token,
                       @RequestParam(value = "orderStatus",required = false)Integer status,
                       @RequestParam(value = "parkingId",required = true)Integer parkingId){
        Admin admin = authService.findAdminByToken(token);
        int id = admin.getCommunityId();
        Map<String,Object> map = new HashMap<>();
        map.put("communityId",id);
        map.put("orderStatus",status);
        map.put("parkingId",parkingId);
        return  Result.build(200,"查找成功",carorderService.selectByMap(map));
    }
    /*
    传入 payId,parkingId,
     */
    @PostMapping("/add")
    public Result add(@RequestParam("token") String token, @RequestBody Map<String,Object> map){
        Admin admin = authService.findAdminByToken(token);
        int id = admin.getCommunityId();
        map.put("communityId",id);
        boolean status = carorderService.save(map);
        if(status==true){
            return Result.build(200,"添加成功");
        }else{
            return Result. build(400,"添加失败");
        }
    }

    @PutMapping("/update")
    public Result update(@RequestBody Carorder carorder){
        boolean status = carorderService.updateById(carorder);
        if(status==false){
            return Result.build(400,"更新失败");
        }else{
            return Result.build(200,"更新成功");
        }
    }

    @DeleteMapping("/delete")
    public Result deleteById(@RequestParam("orderId") Integer orderId){
        boolean status = carorderService.removeById(orderId);
        if(status==false){
            return Result.build(400,"删除失败");
        }else{
            return Result.build(200,"删除成功");
        }
    }
}

