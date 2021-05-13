package com.monster.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.monster.auth.service.AuthService;
import com.monster.common.utils.Result;
import com.monster.entity.Admin;
import com.monster.entity.Notice;
import com.monster.entity.Owner;
import com.monster.entity.Repair;
import com.monster.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author monster
 * @since 2021-04-03
 */
@RestController
@RequestMapping("//notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;
    @Autowired
    private AuthService authService;

    //查找一个社区的所有保修单
    /*
    管理员和业主都可以访问
     */
    @GetMapping("/list")
    public Result list(@RequestParam("token") String token){
        Admin admin = authService.findAdminByToken(token);
        Owner owner = authService.findOwnerByToken(token);
        int id=admin.getCommunityId();
        if(owner!=null){
            id = owner.getCommunityId();
        }
        return Result.build(200,"查找成功",noticeService.list(new QueryWrapper<Notice>().eq("community_id",id).eq("notice_delete",0)));
    }

    @GetMapping("/findById")
    public Result findById(@RequestParam("id") Integer id){
        Notice notice = noticeService.getById(id);
        return Result.build(200,"查找成功");
    }



    @PostMapping("/add")
    public Result add(@RequestParam("token") String token,@RequestBody Notice notice){
        Admin admin = authService.findAdminByToken(token);
        notice.setCommunityId(admin.getCommunityId());
        LocalDateTime now = LocalDateTime.now();
        notice.setNoticeDate(now);
        boolean status = noticeService.save(notice);
        if(status==true){
            return Result.build(200,"添加成功");
        }else{
            return Result. build(400,"添加失败");
        }
    }

    /*
    假删除
     */
    @DeleteMapping("/delete")
    public Result deleteById(@RequestParam("id") Integer id){
        //boolean status = noticeService.removeById(id);
        boolean status = noticeService.removeById(id);
        if(status==false){
            return Result.build(400,"删除失败");
        }else{
            return Result.build(200,"删除成功");
        }
    }

    @PutMapping("/update")
    public Result update(@RequestBody Notice notice){
        boolean status = noticeService.updateById(notice);
        if(status==false){
            return Result.build(400,"更新失败");
        }else{
            return Result.build(200,"更新成功");
        }
    }
}

