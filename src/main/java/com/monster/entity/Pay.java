package com.monster.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
    public class Pay implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "pay_id", type = IdType.AUTO)
      private Integer payId;

    private String payName;//收费项名称


  /*

   */
    private Integer payCalculate;//收费项计算公式

    private Float payUnitprice;//收费单价

    private Float payCost;//基础费用


}
