package com.monster.vo;

import lombok.Data;

@Data
public class ParkingVO {
    private Integer parkingId;

    private String parkingNumber;

    private Integer parkingStatus;

    private Integer ownerUid;

    private String ownerName;

    private Integer communityId;
}
