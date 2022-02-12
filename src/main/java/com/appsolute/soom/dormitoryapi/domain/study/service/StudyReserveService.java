package com.appsolute.soom.dormitoryapi.domain.study.service;

import com.appsolute.soom.dormitoryapi.domain.study.data.dto.ReserveDto;
import com.appsolute.soom.dormitoryapi.global.data.type.SchoolType;

import java.util.List;

public interface StudyReserveService {
    ReserveDto reserve(SchoolType type, String accountUUID);

    ReserveDto getReserve(String accountUUID);

    void cancelReserve(String accountUUID);

    List<String> getAllAccountUUIDAtReserve(SchoolType school);
}
