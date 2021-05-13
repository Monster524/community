package com.monster.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Value;

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
    public class Repair implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "repair_id", type = IdType.AUTO)
      private Integer repairId;

    private String repairContent;

    private LocalDateTime repairNow;

    private LocalDateTime repairOrder;

    private Integer repairStatus;

    private Integer ownerUid;

    private Integer communityId;

    @TableField(exist = false)
    private Owner owner;

}
