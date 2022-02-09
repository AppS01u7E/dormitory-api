package com.appsolute.soom.dormitoryapi.domain.point.data.entity;

import com.appsolute.soom.dormitoryapi.domain.point.data.dto.PointDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class PointEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uuid;
    @Column(unique = true)
    private String accountUUID;
    private Integer rewardPoint;
    private Integer penaltyPoint;

    public PointEntity(String accountUUID) {
        this.accountUUID = accountUUID;
    }

    public PointDto toDto() {
        return new PointDto(rewardPoint, penaltyPoint);
    }
}
