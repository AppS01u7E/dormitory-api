package com.appsolute.soom.dormitoryapi.domain.remain.controller;

import com.appsolute.soom.dormitoryapi.domain.remain.data.dto.RemainDto;
import com.appsolute.soom.dormitoryapi.domain.remain.data.response.AddRemainResponse;
import com.appsolute.soom.dormitoryapi.domain.remain.data.response.GetRemainListResponse;
import com.appsolute.soom.dormitoryapi.domain.remain.data.response.IsRemainResponse;
import com.appsolute.soom.dormitoryapi.domain.remain.exception.AlreadyRemainedException;
import com.appsolute.soom.dormitoryapi.domain.remain.exception.RemainNotFoundException;
import com.appsolute.soom.dormitoryapi.domain.remain.service.DateService;
import com.appsolute.soom.dormitoryapi.domain.remain.service.RemainService;
import com.appsolute.soom.dormitoryapi.global.data.type.SchoolType;
import com.appsolute.soom.dormitoryapi.global.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dormitory/remain")
@RequiredArgsConstructor
@Validated
public class RemainController {
    private final AccountService accountService;
    private final RemainService remainService;
    private final DateService dateService;

    @PostMapping("/{accountUUID}") //계정 UUID를 통해 해당 계정을 이번주 잔류 예약열에 추가한다
    public ResponseEntity<AddRemainResponse> addRemain(@PathVariable String accountUUID) {
        //계정 UUID 를 통해 소속학교정보를 불러온다
        SchoolType school = accountService.getDepartmentByAccountUUID(accountUUID);
        //해당 학교 이번주 잔류 예약열에 해당 계정을 추가한다
        RemainDto dto = remainService.addRemain(accountUUID, school);
        //추가한 예약정보를 응답객체에 담에서 반환한다.
        AddRemainResponse response = new AddRemainResponse(dto.id(), dto.accountUUID(), dto.remainAt(), dto.school());
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{accountUUID}") //계정 UUID를 통해 해당 계정이 이번주 잔류 예약열에 있는지 조회한다
    public ResponseEntity<IsRemainResponse> isRemain(@PathVariable String accountUUID) {
        //소속학교 잔류 예약열에서 해당 계정 UUID 를 통한 예약이 존재하는지 조회한다
        RemainDto dto = remainService.getRemain(accountUUID);
        //존재할 경우 응답객체에 해당 예약정보를 담아서 반환한다.
        IsRemainResponse response = new IsRemainResponse(dto.id());
        return ResponseEntity.ok().body(response);
    }

    @GetMapping //이번주 잔류 예약열을 조회한다.
    public ResponseEntity<GetRemainListResponse> getRemainList(@RequestParam SchoolType school) {
        //해당 학교 잔류 에약열을 구한다
        List<RemainDto> dto = remainService.getRemainList(school);
        //응답객체에 예약열을 담아서 반환한다
        GetRemainListResponse response = new GetRemainListResponse(dateService.getRemainStartDay(), school, dto.stream().map(RemainDto::accountUUID).toList());
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{accountUUID}") //"계정 UUID를 통해 해당 계정을 이번주 잔류 예약열에서 삭제한다
    //만약, 잔류 예약열에 해당 계정 UUID 가 없다면 오류를 반환한다"
    public ResponseEntity<?> cancelRemain(@PathVariable String accountUUID) {
        //잔류예약열에서 해당 계정UUID를 가진 예약을 삭제한다.
        remainService.removeRemain(accountUUID);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(RemainNotFoundException.class)
    public ResponseEntity<?> handling(RemainNotFoundException e) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(AlreadyRemainedException.class)
    public ResponseEntity<String> handling(AlreadyRemainedException e) {
        return ResponseEntity.badRequest().body("이미 신청하셧습니다!");
    }
}
