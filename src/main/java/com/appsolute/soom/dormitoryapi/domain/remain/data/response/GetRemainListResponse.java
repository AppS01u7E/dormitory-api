package com.appsolute.soom.dormitoryapi.domain.remain.data.response;

import com.appsolute.soom.dormitoryapi.global.data.type.SchoolType;

import java.time.LocalDate;
import java.util.List;

public record GetRemainListResponse(
        LocalDate remainAt,
        SchoolType school,
        List<String> accountUUIDList
) {
}
