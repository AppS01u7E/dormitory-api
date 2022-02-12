package com.appsolute.soom.dormitoryapi.domain.remain.data.dto;

import com.appsolute.soom.dormitoryapi.global.data.type.SchoolType;

import java.time.LocalDate;

public record RemainDto(
        Long id,
        String accountUUID,
        LocalDate remainAt,
        SchoolType school
) {
}
