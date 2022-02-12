package com.appsolute.soom.dormitoryapi.domain.study.data.entity;

import com.appsolute.soom.dormitoryapi.domain.study.data.dto.ReserveDto;
import com.appsolute.soom.dormitoryapi.domain.study.data.type.SchoolType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReserveEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountUUID;
    private LocalDate reserveAt;
    private SchoolType school;

    public ReserveEntity(String accountUUID, LocalDate reserveAt, SchoolType school) {
        this.accountUUID = accountUUID;
        this.reserveAt = reserveAt;
        this.school = school;
    }

    public ReserveDto toDto() {
        return new ReserveDto(id, accountUUID, reserveAt, school);
    }
}
