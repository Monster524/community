package com.monster.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author monster
 * @since 2021-05-13
 */
  @Data
  @EqualsAndHashCode(callSuper = false)
    public class Houseorder implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "order_id", type = IdType.AUTO)
      private Integer orderId;

    private LocalDateTime orderDate;

    private Float orderTotal;

    private String orderContent;

    private Integer orderStatus;

    private Integer communityId;

    //private Integer houseId;

    //private Integer payId;


    private House house;

    private Pay pay;
}
