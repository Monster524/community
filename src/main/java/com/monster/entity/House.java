package com.monster.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import io.swagger.models.auth.In;
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
    public class House implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "house_id", type = IdType.AUTO)
      private Integer houseId;

      private String houseNumber;

    private Float houseArea;

    private Integer houseStatus;

    private Integer communityId;
    //private Integer ownerUid;
    @TableField(exist = false)
    private Owner owner;



}
