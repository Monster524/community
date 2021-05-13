package com.monster.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.monster.entity.Admin;
import com.monster.entity.Owner;
import com.monster.mapper.AdminMapper;
import com.monster.mapper.OwnerMapper;
import com.monster.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AuthService implements com.monster.auth.service.AuthService {

    @Autowired
    OwnerMapper ownerMapper;
    @Autowired
    AdminMapper adminMapper;


    //12小时后失效
    private final static int EXPIRE = 12;


    @Override
    public Owner findOwnerByPhone(String phone) {

        return ownerMapper.selectOne(new QueryWrapper<Owner>().eq("owner_phone",phone));
    }

    @Override
    public Admin findAdminByPhone(String phone) {
        return adminMapper.selectOne(new QueryWrapper<Admin>().eq("admin_phone",phone));
    }

    @Override
    public String createOwnerToken(Owner owner) {
        //用UUID生成token
        String token = UUID.randomUUID().toString();
        //当前时间
        LocalDateTime now = LocalDateTime.now();
        //过期时间
        LocalDateTime expireTime = now.plusHours(EXPIRE);
        //保存到数据库
        owner.setLoginTime(now);
        owner.setExpireTime(expireTime);
        owner.setToken(token);
        ownerMapper.updateById(owner);
        return token;
    }

    @Override
    public String createAdminToken(Admin admin) {
        //用UUID生成token
        String token = UUID.randomUUID().toString();
        //当前时间
        LocalDateTime now = LocalDateTime.now();
        //过期时间
        LocalDateTime expireTime = now.plusHours(EXPIRE);
        //保存到数据库
        admin.setLoginTime(now);
        admin.setExpireTime(expireTime);
        admin.setToken(token);
        adminMapper.updateById(admin);
        return token;
    }

    @Override
    public void ownerLogout(String token) {
        Owner owner = ownerMapper.selectOne(new QueryWrapper<Owner>().eq("token",token));
        //用UUID生成token
        token = UUID.randomUUID().toString();
        //修改用户的token使原本的token失效，前端需配合将token清除
        owner.setToken(token);
        ownerMapper.insert(owner);
    }

    @Override
    public void adminLogout(String token) {
        Owner owner = ownerMapper.selectOne(new QueryWrapper<Owner>().eq("token",token));
        //用UUID生成token
        token = UUID.randomUUID().toString();
        //修改用户的token使原本的token失效，前端需配合将token清除
        owner.setToken(token);
        ownerMapper.insert(owner);
    }

    @Override
    public Owner findOwnerByToken(String token) {
        return ownerMapper.selectOne(new QueryWrapper<Owner>().eq("token",token));
    }

    @Override
    public Admin findAdminByToken(String token) {
        return adminMapper.selectOne(new QueryWrapper<Admin>().eq("token",token));
    }
}
