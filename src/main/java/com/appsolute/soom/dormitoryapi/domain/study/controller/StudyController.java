package com.appsolute.soom.dormitoryapi.domain.study.controller;

import com.appsolute.soom.dormitoryapi.domain.study.data.dto.ReserveDto;
import com.appsolute.soom.dormitoryapi.domain.study.data.response.GetReserveListResponse;
import com.appsolute.soom.dormitoryapi.domain.study.data.response.IsReserveResponse;
import com.appsolute.soom.dormitoryapi.domain.study.data.response.ReserveResponse;
import com.appsolute.soom.dormitoryapi.global.data.type.SchoolType;
import com.appsolute.soom.dormitoryapi.domain.study.exception.ReserveNotFoundException;
import com.appsolute.soom.dormitoryapi.global.service.AccountService;
import com.appsolute.soom.dormitoryapi.domain.study.service.StudyReserveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/dormitory/study")
@RequiredArgsConstructor
@Validated
public class StudyController {
    private final StudyReserveService studyReserveService;
    private final AccountService accountService;

    @PostMapping("/{accountUUID}") //계정 UUID 를 통해 해당 계정을 당일 기숙사 자습 예약열에 추가한다
    public ResponseEntity<ReserveResponse> reserve(@PathVariable String accountUUID) {
        //accountUUID 를 통해 예약자의 소속학교를 가져온다.
        SchoolType type = accountService.getDepartmentByAccountUUID(accountUUID);
        //예약을 진행한다.
        ReserveDto dto = studyReserveService.reserve(type, accountUUID);
        //결과값을 응답객체에 담아서 반환한다.
        ReserveResponse response = new ReserveResponse(dto.id(), dto.accountUUID(), dto.reserveAt(), dto.school());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{accountUUID}") //"계정 UUID 를 통해 해당 계정을 당일 기숙사 자습 예약열에서 삭제한다
    // 만약, 자습실 예약열에 해당 계정 UUID 가 없다면 오류를 반환한다"
    public ResponseEntity<?> cancelReserve(@PathVariable String accountUUID) {
        studyReserveService.cancelReserve(accountUUID);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{accountUUID}") //계정 UUID 를 통해 해당 계정이 당일 기숙사 자습 예약열에 있는지 조회한다
    public ResponseEntity<IsReserveResponse> getReserve(@PathVariable String accountUUID) {
        // 계정 UUID 를 통해 예약정보를 조회한다. 예약정보가 존재하지 않을경우 예외를 반환한다.
        ReserveDto dto = studyReserveService.getReserve(accountUUID);
        // 예약정보를 응답객체에 담아서 반환한다.
        IsReserveResponse response = new IsReserveResponse(dto.id());
        return ResponseEntity.ok(response);
    }

    @GetMapping //당일 기숙사 자습 예약열을 조회한다.
    public ResponseEntity<GetReserveListResponse> getReserveList(@RequestParam SchoolType school) {
        //모든 예약정보를 조회한다.
        List<String> list = studyReserveService.getAllAccountUUIDAtReserve(school);
        // 예약정보를 응답객체에 담아서 반환한다.
        GetReserveListResponse response = new GetReserveListResponse(school, LocalDate.now(), list);
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(ReserveNotFoundException.class)
    public ResponseEntity<String> handling(ReserveNotFoundException e) {
        return ResponseEntity.notFound().build();
    }
}
