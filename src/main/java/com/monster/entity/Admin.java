package com.monster.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author monster
 * @since 2021-04-03
 */
  @Data
  @EqualsAndHashCode(callSuper = false)
    public class Admin implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "admin_id", type = IdType.AUTO)
      private Integer adminId;

    private String adminName;
    private String adminPhone;
    private String adminPassword;
    private Integer communityId;
  /**
   * token 登陆凭证
   */
  private String token;
  /**
   * token 过期时间
   */
  private LocalDateTime expireTime;
  /**
   *  登录时间
   */
  private LocalDateTime loginTime;



}
