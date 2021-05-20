package com.monster.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.monster.auth.service.AuthService;
import com.monster.common.utils.Result;
import com.monster.entity.Admin;
import com.monster.entity.Community;
import com.monster.entity.Owner;
import com.monster.service.OwnerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
 * @since 2021-04-03
 */
@RestController
@RequestMapping("/owner")
@Api(value = "/owner",tags = "业主模块")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;
    @Autowired
    private AuthService authService;
    //注册 校验手机号是否被注册过
    @PostMapping("/register")
    @ApiOperation(value="注册",notes="业主注册")
    public Result register(@RequestBody Owner owner){
       //如果身份证号或者手机号相同返回false
        String id = owner.getOwnerId();
        String phone = owner.getOwnerPhone();
        Owner owner1 = ownerService.getOne(new QueryWrapper<Owner>().eq("owner_id",id).or().eq("owner_phone",phone));
        if(owner1!=null){
            return Result.build(400,"身份证或手机号已注册");
        }else{
            ownerService.save(owner);
            return Result.build(200,"注册成功");
        }
    }



    //查找一个社区的所有住户
    @GetMapping("/list")
    @ApiOperation(value="获取所有业主信息",produces = "application/json",response = Owner.class)
    public Result list(@RequestParam("token") String token){
        Admin admin = authService.findAdminByToken(token);
        int id = admin.getCommunityId();
        return Result.build(200,"查找成功",ownerService.list(id));
    }


    //根据Id查找用户
    @GetMapping("/getById")
    public Result getById(@RequestParam("ownerUid") Integer id){
        return Result.build(200,"查找成功",ownerService.getById(id));
    }
    @GetMapping("/getByToken")
    public Result getByToken(@RequestParam("token") String token){
        Owner owner = authService.findOwnerByToken(token);
        int id = owner.getOwnerUid();
        return Result.build(200,"查找成功",ownerService.getById(id));
    }
    //业主姓名（模糊） 联系方式（精准）
    //名称和联系为空，返回全部
    @GetMapping("/selectByMap")
    public Result findByConditions(@RequestParam("token") String token,
                                   @RequestParam(value = "ownerName",required = false) String ownerName,
                                   @RequestParam(value = "ownerPhone",required = false) String ownerPhone){

        Admin admin = authService.findAdminByToken(token);
        int id = admin.getCommunityId();
        Map<String,Object> map = new HashMap<>();
        map.put("communityId",id);
        map.put("ownerName",ownerName);
        map.put("ownerPhone",ownerPhone);
        return Result.build(200,"查询成功",ownerService.selectByMap(map));
    }


    @DeleteMapping("/delete")
    public Result deleteById(@RequestParam("id") Integer id){
        boolean status = ownerService.removeById(id);
        if(status==false){
            return Result.build(400,"删除失败");
        }else{
            return Result.build(200,"删除成功");
        }
    }

    /*
    验证身份证号和姓名是否匹配
    匹配的话修改密码
     */
    @PutMapping("/setpsw")
    public Result setpsw(@RequestBody Owner owner){
        boolean status = ownerService.updatePassword(owner);
        if(status==false){
            return Result.build(400,"验证信息有误");
        }else{
            return Result.build(200,"修改成功");
        }
    }
    @PutMapping("/update")
    public Result update(@RequestBody Owner owner){
        //修改后的身份证号或者手机号相同返回false
        boolean status = ownerService.updateById(owner);
        if(status==false){
            return Result.build(400,"更新失败");
        }else{
            return Result.build(200,"更新成功");
        }
    }
}

