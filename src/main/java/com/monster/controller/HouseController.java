package com.monster.controller;


import com.monster.auth.service.AuthService;
import com.monster.common.utils.Result;
import com.monster.entity.Admin;
import com.monster.entity.Car;
import com.monster.entity.House;
import com.monster.entity.Owner;
import com.monster.service.CarService;
import com.monster.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.HashMap;
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
@RequestMapping("/house")
public class HouseController {
    @Autowired
    private HouseService houseService;
    @Autowired
    private AuthService authService;

    @GetMapping("/list")
    public Result list(@RequestParam("token") String token){
        Admin admin = authService.findAdminByToken(token);
        int id = admin.getCommunityId();
        return Result.build(200,"查找成功",houseService.list(id));
    }

    //条件查询
    // 车牌号（模糊）业主姓名（模糊）状态
    @GetMapping("/selectByMap")
    public Result findByConditions(@RequestParam("token") String token,
                                   @RequestParam(value = "houseNumber",required = false) String houseNumber,
                                   @RequestParam(value = "houseStatus",required = false) Integer houseStatus,
                                   @RequestParam(value = "ownerName",required = false) String ownerName){

        Admin admin = authService.findAdminByToken(token);
        int id = admin.getCommunityId();
        Map<String,Object> map = new HashMap<>();
        map.put("communityId",id);
        map.put("houseNumber",houseNumber);
        map.put("houseStatus",houseStatus);
        map.put("ownerName",ownerName);
        return Result.build(200,"查询成功",houseService.selectByMap(map));
    }

    @PostMapping("/add")
    public Result add(@RequestParam("token") String token,@RequestBody House house){

        Admin admin = authService.findAdminByToken(token);
        int id = admin.getCommunityId();
        house.setCommunityId(id);
        house.setHouseStatus(0);
        boolean status = houseService.save(house);
        if(status==true){
            return Result.build(200,"添加成功");
        }else{
            return Result. build(400,"添加失败");
        }
    }

    @PutMapping("/update")
    public Result update(@RequestBody House house){
        boolean status = houseService.updateById(house);
        if(status==false){
            return Result.build(400,"更新失败");
        }else{
            return Result.build(200,"更新成功");
        }
    }

    @PutMapping("/selectOwner")
    public Result selectOwner(@RequestBody Map<String,Object> map){
        House house = new House();
        house.setHouseId((Integer) map.get("houseId"));
        house.setHouseStatus((Integer) map.get("houseStatus"));
        Owner owner = new Owner();
        owner.setOwnerUid((Integer) map.get("ownerUid"));
        house.setOwner(owner);
        boolean status = houseService.selectOwner(house);
        if(status==false){
            return Result.build(400,"更新失败");
        }else{
            return Result.build(200,"更新成功");
        }
    }
    @DeleteMapping("/delete")
    public Result deleteById(@RequestParam("houseId") Integer id){
        boolean status = houseService.removeById(id);
        if(status==false){
            return Result.build(400,"删除失败");
        }else{
            return Result.build(200,"删除成功");
        }
    }
}

