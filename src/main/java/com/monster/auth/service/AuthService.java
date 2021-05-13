package com.monster.auth.service;

import com.monster.entity.Admin;
import com.monster.entity.Owner;

public interface AuthService {
    /**
     * 根据用户名查找用户
     * @param phone
     * @return
     */
    Owner findOwnerByPhone(String phone);
    Admin findAdminByPhone(String phone);
    /**
     * 为user生成token
     * @param owner
     * @return
     */
    String createOwnerToken(Owner owner);
    String createAdminToken(Admin admin);
    /**
     * 根据token去修改用户token ，使原本token失效
     * @param token
     */
    void ownerLogout(String token);
    void adminLogout(String token);

    /**
     * 根据token获取用户信息
     * @param token
     * @return
     */
    Owner findOwnerByToken(String token);
    Admin findAdminByToken(String token);
}
