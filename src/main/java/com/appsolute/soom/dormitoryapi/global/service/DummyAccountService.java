package com.appsolute.soom.dormitoryapi.global.service;

import com.appsolute.soom.dormitoryapi.global.data.type.SchoolType;
import org.springframework.stereotype.Service;

@Service
public class DummyAccountService implements AccountService {
    @Override
    public SchoolType getDepartmentByAccountUUID(String accountUUID) {
        return SchoolType.GWANGJU;
    }
}
