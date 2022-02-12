package com.appsolute.soom.dormitoryapi.global.service;

import com.appsolute.soom.dormitoryapi.global.data.type.SchoolType;

public interface AccountService {
    SchoolType getDepartmentByAccountUUID(String accountUUID);
}
