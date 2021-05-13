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
    public class Community implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "community_id", type = IdType.AUTO)
      private Integer communityId;

    private String communityName;

    private String communityAddress;


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
