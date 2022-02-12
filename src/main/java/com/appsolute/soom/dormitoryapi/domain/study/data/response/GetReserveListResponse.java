package com.appsolute.soom.dormitoryapi.domain.study.data.response;

import com.appsolute.soom.dormitoryapi.global.data.type.SchoolType;

import java.time.LocalDate;
import java.util.List;

public record GetReserveListResponse(
        SchoolType school,
        LocalDate reserveAt,
        List<String> accountUUIDList
) {
}
