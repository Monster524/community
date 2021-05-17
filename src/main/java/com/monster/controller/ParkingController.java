package com.monster.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.monster.auth.service.AuthService;
import com.monster.common.utils.Result;
import com.monster.entity.Admin;
import com.monster.entity.Car;
import com.monster.entity.Owner;
import com.monster.entity.Parking;
import com.monster.service.ParkingService;
import com.monster.vo.ParkingVO;
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
@RequestMapping("/parking")
public class ParkingController {
    @Autowired
    private ParkingService parkingService;
    @Autowired
    private AuthService authService;

    //查停车表+业主表
    @GetMapping("/list")
    public Result list(@RequestParam("token") String token){
        Admin admin = authService.findAdminByToken(token);
        int id = admin.getCommunityId();
        return Result.build(200,"查找成功",parkingService.list(id));
    }

    //车位编号（精准） 业主姓名（模糊） 状态
    @GetMapping("/selectByMap")
    public Result findByConditions(@RequestParam("token") String token,
                                   @RequestParam(value = "parkingNumber",required = false)
                                           String parkingNumber,
                                   @RequestParam(value = "ownerName",required = false)
                                               String ownerName,
                                   @RequestParam(value = "parkingStatus",required = false)
                                               Integer parkingStatus){
        Admin admin = authService.findAdminByToken(token);
        int id = admin.getCommunityId();
        Map<String,Object> map = new HashMap<>();
        map.put("communityId",id);
        map.put("parkingNumber",parkingNumber);
        map.put("ownerName",ownerName);
        map.put("parkingStatus",parkingStatus);
        return Result.build(200,"查找成功",parkingService.selectByMap(map));
    }

    @PostMapping("/add")
    public Result add(@RequestParam("token") String token,@RequestBody Parking parking){
        Admin admin = authService.findAdminByToken(token);
        int id = admin.getCommunityId();
        parking.setCommunityId(id);
        parking.setParkingStatus(0);
        boolean status = parkingService.save(parking);
        if(status==true){
            return Result.build(200,"添加成功");
        }else{
            return Result. build(400,"添加失败");
        }
    }

    @DeleteMapping("/delete")
    public Result deleteById(@RequestParam("id") Integer id){
        boolean status = parkingService.removeById(id);
        if(status==false){
            return Result.build(400,"删除失败");
        }else{
            return Result.build(200,"删除成功");
        }
    }

    @PutMapping("/update")
    public Result update(@RequestParam("token") String token,@RequestBody Parking parking){

        boolean status = parkingService.updateById(parking);
        if(status==false){
            return Result.build(400,"更新失败");
        }else{
            return Result.build(200,"更新成功");
        }
    }

}

