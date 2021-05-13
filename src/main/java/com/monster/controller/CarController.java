package com.monster.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.monster.auth.service.AuthService;
import com.monster.common.utils.Result;
import com.monster.entity.*;
import com.monster.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
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
@RequestMapping("//car")
public class CarController {

    @Autowired
    private CarService carService;
    @Autowired
    private AuthService authService;

    @GetMapping("/list")
    public Result list(@RequestParam("token") String token){
        Admin admin = authService.findAdminByToken(token);
        int id = admin.getCommunityId();
        return Result.build(200,"查找成功",carService.list(id));
    }

    //条件查询
    // 车牌号（模糊）业主姓名（模糊）状态
    @GetMapping("/selectByMap")
    public Result selectByMap(@RequestParam("token") String token,
                                   @RequestParam(value = "carNumber",required = false) String carNumber,
                                   @RequestParam(value = "ownerName",required = false) String ownerName){
        Admin admin = authService.findAdminByToken(token);
        int id = admin.getCommunityId();
        Map<String,Object> map = new HashMap<>();
        map.put("communityId",id);
        map.put("carNumber",carNumber);
        map.put("ownerName",ownerName);
        return Result.build(200,"查询成功",carService.selectByMap(map));
    }

    /*
    添加车辆 选择业主（id）-》填写车辆信息-》选车位(id)
     */
    @PostMapping("/add")
    public Result add(@RequestParam("token") String token,@RequestBody Map<String,Object> map){
        Car car = new Car();
        try {
            Admin admin = authService.findAdminByToken(token);
            int id = admin.getCommunityId();
            car.setCommunityId(id);
        }catch (NullPointerException e){
            return Result.build(400,"认证失败");
        }
        car.setCarNumber((String) map.get("carNumber"));
        car.setCarBrand((String) map.get("carBrand"));

        Owner owner = new Owner();
        owner.setOwnerUid((Integer) map.get("ownerUid"));
        car.setOwner(owner);
        Parking parking = new Parking();
        parking.setParkingId((Integer) map.get("parkingId"));
        car.setParking(parking);

        boolean status = carService.add(car);
        if(status==true){
            return Result.build(200,"添加成功");
        }else{
            return Result. build(400,"添加失败");
        }
    }

    /*
    删除：
        params 车辆id 车位id
        return 状态（后端需要将对应车位改为空闲）
     */
    @DeleteMapping("/delete")
    public Result deleteById(@RequestParam("carId") Integer carId,
                             @RequestParam("parkingId") Integer parkingId){
        boolean status = carService.removeById(carId,parkingId);
        if(status==false){
            return Result.build(400,"删除失败");
        }else{
            return Result.build(200,"删除成功");
        }
    }

    /*
    车牌 品牌 业主uid 车位id 状态（不能修改绑定业主）
     */
    @PutMapping("/update")
    public Result update(@RequestBody Car car){
        boolean status = carService.updateById(car);
        if(status==false){
            return Result.build(400,"更新失败");
        }else{
            return Result.build(200,"更新成功");
        }
    }
}

