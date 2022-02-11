package com.appsolute.soom.dormitoryapi.domain.study.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/dormitory/study")
@RequiredArgsConstructor
@Validated
public class StudyController {
    @PostMapping("/{accountUUID}") //계정 UUID 를 통해 해당 계정을 당일 기숙사 자습 예약열에 추가한다
    public ResponseEntity<?> reserve(@PathVariable String accountUUID) {
        return ResponseEntity.ok(null);
    }
    @DeleteMapping("/{accountUUID}") //"계정 UUID 를 통해 해당 계정을 당일 기숙사 자습 예약열에서 삭제한다
    // 만약, 자습실 예약열에 해당 계정 UUID 가 없다면 오류를 반환한다"
    public ResponseEntity<?> cancelReserve(@PathVariable String accountUUID) {
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{accountUUID}") //계정 UUID 를 통해 해당 계정이 당일 기숙사 자습 예약열에 있는지 조회한다
    public ResponseEntity<?> isReserve(@PathVariable String accountUUID) {
        return ResponseEntity.ok(null);
    }
    @GetMapping //당일 기숙사 자습 예약열을 조회한다.
    public ResponseEntity<?> getReserveList() {
        return ResponseEntity.ok(null);
    }
}
