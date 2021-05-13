package com.monster.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.TableField;
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
    public class Complaint implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "complaint_id", type = IdType.AUTO)
      private Integer complaintId;

    private String complaintContent;

    private LocalDateTime complaintDate;

    private Integer complaintStatus;

    private Integer ownerUid;

    private Integer communityId;

    @TableField(exist = false)
    private Owner owner;

}
