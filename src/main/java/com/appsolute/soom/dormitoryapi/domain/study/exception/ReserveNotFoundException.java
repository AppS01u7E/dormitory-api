package com.appsolute.soom.dormitoryapi.domain.study.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReserveNotFoundException extends RuntimeException {
    private final String accountUUID;
}
