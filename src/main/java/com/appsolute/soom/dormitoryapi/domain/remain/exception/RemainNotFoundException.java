package com.appsolute.soom.dormitoryapi.domain.remain.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RemainNotFoundException extends RuntimeException {
    private final String accountUUID;
}
