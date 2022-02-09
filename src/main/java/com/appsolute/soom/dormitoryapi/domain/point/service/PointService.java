package com.appsolute.soom.dormitoryapi.domain.point.service;

import com.appsolute.soom.dormitoryapi.domain.point.data.dto.PointDto;
import com.appsolute.soom.dormitoryapi.domain.point.data.type.PointType;

public interface PointService {
    PointDto getPointByAccountUUID(String accountUUID);

    PointDto addPoint(PointType type, Integer point);

    PointDto subPoint(PointType type, Integer point);
}
