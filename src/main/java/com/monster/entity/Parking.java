package com.monster.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
    public class Parking implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "parking_id", type = IdType.AUTO)
      private Integer parkingId;

    private String parkingNumber;

    private Integer parkingStatus;

   // private Integer ownerUid;

    private Integer communityId;

    @TableField(exist = false)
    private Owner owner;
    @TableField(exist = false)
    private Car car;
}
