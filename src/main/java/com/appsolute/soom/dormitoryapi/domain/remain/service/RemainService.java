package com.appsolute.soom.dormitoryapi.domain.remain.service;

import com.appsolute.soom.dormitoryapi.domain.remain.data.dto.RemainDto;
import com.appsolute.soom.dormitoryapi.global.data.type.SchoolType;

import java.util.List;

public interface RemainService {
    RemainDto addRemain(String accountUUID, SchoolType school);

    RemainDto getRemain(String accountUUID);

    List<RemainDto> getRemainList(SchoolType school);

    void removeRemain(String accountUUID);
}
