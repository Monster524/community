package com.monster.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.monster.auth.service.AuthService;
import com.monster.common.utils.Result;
import com.monster.entity.Admin;
import com.monster.entity.Complaint;
import com.monster.entity.Owner;
import com.monster.entity.Repair;
import com.monster.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
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
@RequestMapping("//repair")
public class RepairController {

    @Autowired
    private RepairService repairService;
    @Autowired
    private AuthService authService;

    //查找一个社区的所有保修单
    /*
    管理员可查看
     */
    @GetMapping("/list")
    public Result list(@RequestParam("token") String token){
        Admin admin = authService.findAdminByToken(token);
        int id = admin.getCommunityId();
        return Result.build(200,"查找成功",repairService.list(new QueryWrapper<Repair>().eq("community_id",id)));
    }


    @GetMapping("/findById")
    public Result findById(@RequestParam("id") Integer id){
        Repair repair = repairService.getById(id);
        return Result.build(200,"查找成功");
    }
    @GetMapping("/findByToken")
    public Result findById(@RequestParam("token") String token){
        Owner owner = authService.findOwnerByToken(token);
        int id = owner.getOwnerUid();
        //Complaint complaint = (Complaint) );
        return Result.build(200,"查找成功",repairService.list(new QueryWrapper<Repair>().eq("owner_uid",id)));
    }

    @GetMapping("/selectByMap")
    public Result findByConditions(@RequestParam("token") String token,
                                   @RequestParam("repairStatus")
                                           String repairStatus,
                                   @RequestParam(value = "ownerName",required = false)
                                           String ownerName){
        Admin admin = authService.findAdminByToken(token);
        int id = admin.getCommunityId();
        Map<String,Object> map = new HashMap<>();
        map.put("communityId",id);
        map.put("repairStatus",repairStatus);
        map.put("ownerName",ownerName);
        return Result.build(200,"查找成功",repairService.selectByMap(map));
    }
    /*
    用户添加报修
     */
    @PostMapping("/add")
    public Result add(@RequestParam("token") String token,@RequestBody Repair repair){
        Owner owner = authService.findOwnerByToken(token);
        repair.setOwnerUid(owner.getOwnerUid());
        repair.setCommunityId(owner.getCommunityId());
        LocalDateTime now = LocalDateTime.now();
        repair.setRepairNow(now);
        repair.setRepairStatus(0);
        boolean status = repairService.save(repair);
        if(status==true){
            return Result.build(200,"添加成功");
        }else{
            return Result. build(400,"添加失败");
        }
    }

    @DeleteMapping("/delete")
    public Result deleteById(@RequestParam("repairId") Integer id){
        boolean status = repairService.removeById(id);
        if(status==false){
            return Result.build(400,"删除失败");
        }else{
            return Result.build(200,"删除成功");
        }
    }

    /*
    管理员更改维修表状态
     */
    @PutMapping("/update")
    public Result update(@RequestBody Repair repair){
        boolean status = repairService.updateById(repair);
        if(status==false){
            return Result.build(400,"更新失败");
        }else{
            return Result.build(200,"更新成功");
        }
    }
}

