package com.appsolute.soom.dormitoryapi.domain.remain.data.response;

import com.appsolute.soom.dormitoryapi.global.data.type.SchoolType;

import java.time.LocalDate;

public record AddRemainResponse(
        Long remainId,
        String accountUUID,
        LocalDate remainAt,
        SchoolType school
) {
}
