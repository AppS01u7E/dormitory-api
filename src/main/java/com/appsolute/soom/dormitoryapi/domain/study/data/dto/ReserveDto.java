package com.appsolute.soom.dormitoryapi.domain.study.data.dto;

import com.appsolute.soom.dormitoryapi.domain.study.data.type.SchoolType;

import java.time.LocalDate;

public record ReserveDto(
        Long id,
        String accountUUID,
        LocalDate reserveAt,
        SchoolType school
) {
}
