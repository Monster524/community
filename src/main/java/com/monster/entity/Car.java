package com.monster.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
    public class Car implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "car_id", type = IdType.AUTO)
      private Integer carId;

    private String carNumber;

    private String carBrand;

    private Integer communityId;

    //private Integer ownerUid;
    @TableField(exist = false)
    private Owner owner;
    @TableField(exist = false)
    private Parking parking;


}
