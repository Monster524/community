package com.monster.controller;


import com.monster.entity.Community;
import com.monster.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author monster
 * @since 2021-04-03
 */
@RestController
@RequestMapping("//community")
public class CommunityController {

    @Autowired
    private CommunityService communityService;


    @GetMapping("/list")
    public List<Community> list(){
            return this.communityService.list();
    }

    @GetMapping("/find/{id}")
    public Community find(@PathVariable("id") Integer id){
        return this.communityService.getById(id);
    }

    @PostMapping("/add")
    public boolean add(@RequestBody Community community){
        return  this.communityService.save(community);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") Integer id){
        return this.communityService.removeById(id);
    }

    @PutMapping("/update")
    public boolean update(@RequestBody Community community){
        return this.communityService.updateById(community);
    }
}
