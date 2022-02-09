package com.appsolute.soom.dormitoryapi.domain.point.controller;

import com.appsolute.soom.dormitoryapi.domain.point.data.response.AddPointResponse;
import com.appsolute.soom.dormitoryapi.domain.point.data.response.GetPointResponse;
import com.appsolute.soom.dormitoryapi.domain.point.data.response.SubPointResponse;
import com.appsolute.soom.dormitoryapi.domain.point.data.type.PointType;
import com.appsolute.soom.dormitoryapi.domain.point.service.PointService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/dormitory/point")
public class PointController {
    @GetMapping("/{accountUUID}")//계정 UUID 를 통해 해당 계정의 상점과 벌점을 조회한다.
    public ResponseEntity<GetPointResponse> getPoint(@PathVariable String accountUUID) {
        //PointService 를 통해 계정의 상벌점을 조회한다.
        //조회한 결과를 Response Dto 로 치환한다.
        //치환한 데이터를 body 에 담아서 return 한다.
        return ResponseEntity.ok().body(null);
    }

    @PutMapping("/{accountUUID}/add") //계정 UUID를 통해 해당 계정에 상점 또는 벌점을 부여한다.
    public ResponseEntity<AddPointResponse> addPoint(@PathVariable String accountUUID,
                                                     @RequestParam PointType type,
                                                     @RequestParam Integer point) {
        //요청 데이터를 해석하여 추가할 상벌점 수를 구한다
        //PointService 에 계정 UUID 와 해석한 상벌점 계수를 넘겨주어서 상벌점을 추가한다
        //추가한 상벌점에 관한 정보를 Response Dto 로 치환한다.
        //치환한 데이터를 body 에 담아서 return 한다.
        return ResponseEntity.ok().body(null);
    }

    @PutMapping("/{accountUUID}/sub") //계정 UUID를 통해 해당 계정에 상점 또는 벌점을 차감한다.
    public ResponseEntity<SubPointResponse> subPoint(@PathVariable String accountUUID,
                                                     @RequestParam PointType type,
                                                     @RequestParam Integer point) {
        //요청 데이터를 해석하여 차감할 상벌점 수를 구한다
        //PointService 에 계정 UUID 와 해석한 상벌점 계수를 넘겨주어서 상벌점을 차감한다
        //차감한 상벌점에 관한 정보를 ResponseDto 로 치환한다.
        //치환한 데이터를 body 에 담아서 return 한다.
        return ResponseEntity.ok().body(null);
    }
}
