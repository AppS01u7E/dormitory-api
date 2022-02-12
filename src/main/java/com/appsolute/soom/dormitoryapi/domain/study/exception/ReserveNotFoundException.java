package com.appsolute.soom.dormitoryapi.domain.study.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ReserveNotFoundException extends RuntimeException {
    private final String accountUUID;
}
