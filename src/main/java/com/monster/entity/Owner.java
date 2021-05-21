package com.monster.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

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
@ApiModel(value = "Owner")
    public class Owner implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "owner_uid", type = IdType.AUTO)
      private Integer ownerUid;

    private String ownerName;

    private String ownerSex;

    private String ownerId;

    private String ownerPhone;


    private String ownerPassword;

    private Integer communityId;

    private String token;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime expireTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime loginTime;

  @TableField(exist = false)
  private List<House> houses;

  @TableField(exist = false)
  private List<Parking> parkings;

    @TableField(exist = false)
    private List<Car> cars;
}

