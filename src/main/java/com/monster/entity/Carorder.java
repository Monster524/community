package com.monster.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author monster
 * @since 2021-05-20
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class Carorder implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "order_id", type = IdType.AUTO)
      private Integer orderId;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderDate;

    private Float orderTotal;

    private String orderContent;

    private Integer orderStatus;

    private Integer communityId;

   // private Integer parkingId;

    @TableField(exist = false)
    private Parking parking;

    @TableField(exist = false)
    private Pay pay;

}
