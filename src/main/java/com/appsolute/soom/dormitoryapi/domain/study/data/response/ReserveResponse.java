package com.appsolute.soom.dormitoryapi.domain.study.data.response;

import com.appsolute.soom.dormitoryapi.global.data.type.SchoolType;

import java.time.LocalDate;

public record ReserveResponse(
        Long reserveId,
        String accountUUID,
        LocalDate reserveAt,
        SchoolType school
) {
}
