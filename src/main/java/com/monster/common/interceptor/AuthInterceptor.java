package com.monster.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.monster.auth.service.AuthService;
import com.monster.common.utils.HttpContextUtil;
import com.monster.common.utils.Result;
import com.monster.common.utils.TokenUtil;
import com.monster.entity.Admin;
import com.monster.entity.Owner;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * 编写自己的拦截器
 */
public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    private AuthService authService;

    /**
     * 判断当前请求是否带token
     * 预处理回调方法，实现处理器的预处理
     * 返回值：true表示继续流程；false表示流程中断，不会继续调用其他的拦截器或处理器
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws IOException {
        String token = TokenUtil.getRequestToken(request);
        if (StringUtils.isBlank(token)) {
            setReturn(response, 400, "用户未登录，请先登录");
            return false;
        }
        //1. 根据token，查询用户信息
        Owner owner = authService.findOwnerByToken(token);
        Admin admin = authService.findAdminByToken(token);
        //2. 若用户不存在,
        if (owner == null&&admin == null) {
            setReturn(response, 400, "用户不存在");
            return false;
        }
        /*
        //3. token失效
        if(owner!=null){
            if (owner.getExpireTime().isBefore(LocalDateTime.now())) {
                setReturn(response, 400, "用户登录凭证已失效，请重新登录");
                return false;
            }
        }
        if(admin!=null){
            if (admin.getExpireTime().isBefore(LocalDateTime.now())) {
                setReturn(response, 400, "管理员登录凭证已失效，请重新登录");
                return false;
            }
        }*/
        return true;
    }

    /**
     * 后处理回调方法，实现处理器（controller）的后处理，但在渲染视图之前
     * 此时我们可以通过modelAndView对模型数据进行处理或对视图进行处理
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }


    /**
     * 整个请求处理完毕回调方法，即在视图渲染完毕时回调，
     * 如性能监控中我们可以在此记录结束时间并输出消耗时间，
     * 还可以进行一些资源清理，类似于try-catch-finally中的finally，
     * 但仅调用处理器执行链中
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }

    private static void setReturn(HttpServletResponse response, int status, String msg) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtil.getOrigin());
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.setStatus(400);
        response.setContentType("application/json;charset=utf-8");
        Result build = Result.build(status, msg);

        String json = JSON.toJSONString(build);
        httpResponse.getWriter().print(json);
    }

}
