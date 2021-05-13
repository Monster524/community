package com.monster.auth.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TokenVo {
    private String token;
    private LocalDateTime expireTime;
}
