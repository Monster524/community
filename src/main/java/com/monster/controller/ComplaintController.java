package com.monster.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.monster.auth.service.AuthService;
import com.monster.common.utils.Result;
import com.monster.entity.*;
import com.monster.service.CommunityService;
import com.monster.service.ComplaintService;
import com.monster.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.time.LocalDate;
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
@RequestMapping("//complaint")
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;
    @Autowired
    private AuthService authService;


    //查找一个社区的所有保修单
    @GetMapping("/list")
    public Result list(@RequestParam("token") String token){
        Admin admin = authService.findAdminByToken(token);
        int id = admin.getCommunityId();
       // return Result.build(200,"查找成功",complaintService.list(new QueryWrapper<Complaint>().eq("community_id",id)));
        //List<Complaint> doList = complaintService.list(new QueryWrapper<Complaint>().eq("community_id",id).eq("complaint_status",1));
        //List<Complaint> undoList = complaintService.list(new QueryWrapper<Complaint>().eq("community_id",id).eq("complaint_status",0));
        Complaint doCom = new Complaint();
        doCom.setCommunityId(id);
        doCom.setComplaintStatus(1);
        Complaint undoCom = new Complaint();
        undoCom.setCommunityId(id);
        undoCom.setComplaintStatus(0);
        List<Complaint> doList =complaintService.list(doCom);
        List<Complaint> undoList = complaintService.list(undoCom);
        Map<String,List> map = new HashMap<>();
        map.put("doList",doList);
        map.put("undoList",undoList);
        return Result.build(200,"查找成功",map);
    }

    @GetMapping("/findById")
    public Result findById(@RequestParam("id") Integer id){
        Complaint complaint = complaintService.getById(id);
        return Result.build(200,"查找成功");
    }
    @GetMapping("/findByToken")
    public Result findById(@RequestParam("token") String token){
        Owner owner = authService.findOwnerByToken(token);
        int id = owner.getOwnerUid();
        //Complaint complaint = (Complaint) );
        return Result.build(200,"查找成功",complaintService.list(new QueryWrapper<Complaint>().eq("owner_uid",id)));
    }

    @GetMapping("/selectByMap")
    public Result findByConditions(@RequestParam("token") String token,
                                   @RequestParam(value = "ownerName",required = false)
                                           String ownerName){
        Admin admin = authService.findAdminByToken(token);
        int id = admin.getCommunityId();
        Map<String,Object> map1 = new HashMap<>();
        map1.put("communityId",id);
        map1.put("complaintStatus",1);
        map1.put("ownerName",ownerName);
        Map<String,Object> map2 = new HashMap<>();
        map2.put("communityId",id);
        map2.put("complaintStatus",0);
        map2.put("ownerName",ownerName);
        List<Complaint> doList = complaintService.selectByMap(map1);
        List<Complaint> undoList = complaintService.selectByMap(map2);
        //两个List
        Map<String,List> map = new HashMap<>();
        map.put("doList",doList);
        map.put("undoList",undoList);
        return Result.build(200,"查找成功",map);
    }
    /*
    用户添加 投诉
     */
    @PostMapping("/add")
    public Result add(@RequestParam("token") String token,@RequestBody Complaint complaint){
        Owner owner = authService.findOwnerByToken(token);
        complaint.setOwnerUid(owner.getOwnerUid());
        complaint.setCommunityId(owner.getCommunityId());
        LocalDateTime now = LocalDateTime.now();
        complaint.setComplaintDate(now);
        complaint.setComplaintStatus(0);
        boolean status = complaintService.save(complaint);
        if(status==true){
            return Result.build(200,"添加成功");
        }else{
            return Result. build(400,"添加失败");
        }
    }

    @DeleteMapping("/delete")
    public Result deleteById(@RequestParam("complaintId") Integer complaintId){
        boolean status = complaintService.removeById(complaintId);
        if(status==false){
            return Result.build(400,"删除失败");
        }else{
            return Result.build(200,"删除成功");
        }
    }

    /*
    更改维修状态
     */
    @PutMapping("/update")
    public Result update(@RequestBody Complaint complaint){

        boolean status = complaintService.updateById(complaint);
        if(status==false){
            return Result.build(400,"更新失败");
        }else{
            return Result.build(200,"更新成功");
        }
    }
}

