package com.appsolute.soom.dormitoryapi.domain.point.controller;

import com.appsolute.soom.dormitoryapi.domain.point.data.dto.PointDto;
import com.appsolute.soom.dormitoryapi.domain.point.data.response.AddPointResponse;
import com.appsolute.soom.dormitoryapi.domain.point.data.response.GetPointResponse;
import com.appsolute.soom.dormitoryapi.domain.point.data.response.SubPointResponse;
import com.appsolute.soom.dormitoryapi.domain.point.data.type.PointType;
import com.appsolute.soom.dormitoryapi.domain.point.service.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/api/v1/dormitory/point")
@RequiredArgsConstructor
@Validated
public class PointController {
    private final PointService pointService;

    @GetMapping("/{accountUUID}")//계정 UUID 를 통해 해당 계정의 상점과 벌점을 조회한다.
    public ResponseEntity<GetPointResponse> getPoint(@PathVariable String accountUUID) {
        //PointService 를 통해 계정의 상벌점을 조회한다.
        PointDto dto = pointService.getPointByAccountUUID(accountUUID);
        //조회한 결과를 Response Dto 로 치환한다.
        GetPointResponse response = new GetPointResponse(accountUUID, dto.rewardPoint(), dto.penaltyPoint());
        //치환한 데이터를 body 에 담아서 return 한다.
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{accountUUID}/add") //계정 UUID를 통해 해당 계정에 상점 또는 벌점을 부여한다.
    public ResponseEntity<AddPointResponse> addPoint(@PathVariable String accountUUID,
                                                     @RequestParam PointType type,
                                                     @RequestParam @Min(1L) int point) {
        //PointService 에 계정 UUID 와 해석한 상벌점 계수를 넘겨주어서 상벌점을 추가한다
        PointDto updated = pointService.updatePoint(accountUUID, type, point);
        //추가한 상벌점에 관한 정보를 Response Dto 로 치환한다.
        AddPointResponse response = new AddPointResponse(accountUUID, updated.rewardPoint(), updated.penaltyPoint(), type);
        //치환한 데이터를 body 에 담아서 return 한다.
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{accountUUID}/sub") //계정 UUID를 통해 해당 계정에 상점 또는 벌점을 차감한다.
    @Valid
    public ResponseEntity<SubPointResponse> subPoint(@PathVariable String accountUUID,
                                                     @RequestParam PointType type,
                                                     @RequestParam @Min(1L) int point) {
        //PointService 에 계정 UUID 와 해석한 상벌점 계수를 넘겨주어서 상벌점을 차감한다
        PointDto updated = pointService.updatePoint(accountUUID, type, -1 * point);
        //차감한 상벌점에 관한 정보를 ResponseDto 로 치환한다.
        SubPointResponse response = new SubPointResponse(accountUUID, updated.rewardPoint(), updated.penaltyPoint(), type);
        //치환한 데이터를 body 에 담아서 return 한다.
        return ResponseEntity.ok().body(response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handlingValidationError(ConstraintViolationException e) {
        return ResponseEntity.badRequest().body(e.getLocalizedMessage());
    }
}
