package com.appsolute.soom.dormitoryapi.domain.study.service;

import com.appsolute.soom.dormitoryapi.domain.study.data.type.SchoolType;

public interface AccountService {
    SchoolType getDepartmentByAccountUUID(String accountUUID);
}
