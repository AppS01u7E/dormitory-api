package com.appsolute.soom.dormitoryapi.domain.study.service;

import com.appsolute.soom.dormitoryapi.domain.study.data.type.SchoolType;
import org.springframework.stereotype.Service;

@Service
public class DummyAccountService implements AccountService {
    @Override
    public SchoolType getDepartmentByAccountUUID(String accountUUID) {
        return SchoolType.GWANGJU;
    }
}
