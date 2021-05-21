package com.monster.auth.controller;

import com.monster.auth.service.AuthService;
import com.monster.auth.vo.TokenVo;
import com.monster.common.dto.LoginDto;
import com.monster.common.utils.Result;
import com.monster.common.utils.TokenUtil;
import com.monster.entity.Admin;
import com.monster.entity.Owner;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录校验
 */
@RestController
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * 登录
     *
     * @param loginDto
     * @return token登录凭证
     */
    @PostMapping("/owner/login")
    public Result ownerLogin(@Validated @RequestBody LoginDto loginDto,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.build(400,
                    bindingResult.getFieldError().getDefaultMessage());
        }
        String phone = loginDto.getPhone();
        String password = loginDto.getPassword();
        //用户信息
        Owner owner = authService.findOwnerByPhone(phone);//根据电话找到owner
        //账号不存在、密码错误
        if (owner == null || !owner.getOwnerPassword().equals(password)) {
            return Result.build(400, "用户名或密码错误");
        } else {
            //第一次登录时生成token，并保存到数据库
            String token = authService.createOwnerToken(owner);
            TokenVo tokenVo = new TokenVo();
            tokenVo.setToken(token);
            tokenVo.setExpireTime(owner.getExpireTime());
            tokenVo.setCommunityId(owner.getCommunityId());
            return Result.ok(tokenVo);
        }
    }

    @PostMapping("/admin/login")
    public Result adminLogin(@Validated @RequestBody LoginDto loginDto,BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return Result.build(400,
                    bindingResult.getFieldError().getDefaultMessage());
        }
        String phone = loginDto.getPhone();
        String password = loginDto.getPassword();
        //用户信息
        Admin admin = authService.findAdminByPhone(phone);//根据电话找到admin
        //账号不存在、密码错误
        if (admin == null || !admin.getAdminPassword().equals(password)) {
            return Result.build(400, "用户名或密码错误");
        } else {
            //第一次登录时生成token，并保存到数据库
            String token = authService.createAdminToken(admin);
            TokenVo tokenVo = new TokenVo();
            tokenVo.setToken(token);
            tokenVo.setExpireTime(admin.getExpireTime());
            tokenVo.setCommunityId(admin.getCommunityId());
            return Result.ok(tokenVo);
        }
    }

    /**
     * 登出
     *
     * @param
     * @return
     */
    @PostMapping("/owner/logout")
    public Result ownerLogout(HttpServletRequest request) {
        //从request中取出token
        String token = TokenUtil.getRequestToken(request);
        authService.ownerLogout(token);
        return Result.ok();
    }
    @PostMapping("/admin/logout")
    public Result adminLogout(HttpServletRequest request) {
        //从request中取出token
        String token = TokenUtil.getRequestToken(request);
        authService.ownerLogout(token);
        return Result.ok();
    }
    @GetMapping("/admin/getByToken")
    public Result getByToken(HttpServletRequest request) {
        //从request中取出token
        String token = TokenUtil.getRequestToken(request);
        authService.findAdminByToken(token);
        return  Result.build(200,"查找成功",authService.findAdminByToken(token));
    }

    /**
     * 测试
     *
     * @param
     * @return
     */
    @PostMapping("/test")
    public Result test(String token) {
        return Result.ok("恭喜你，验证成功啦，我可以返回数据给你");
    }
}
