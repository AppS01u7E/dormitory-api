package com.appsolute.soom.dormitoryapi.domain.remain.data.entity;

import com.appsolute.soom.dormitoryapi.domain.remain.data.dto.RemainDto;
import com.appsolute.soom.dormitoryapi.global.data.type.SchoolType;
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
public class RemainEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountUUID;
    private LocalDate remainAt;
    private SchoolType school;

    public RemainEntity(String accountUUID, LocalDate remainAt, SchoolType school) {
        this.accountUUID = accountUUID;
        this.remainAt = remainAt;
        this.school = school;
    }

    public RemainDto toDto() {
        return new RemainDto(id, accountUUID, remainAt, school);
    }
}
