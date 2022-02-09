package com.appsolute.soom.dormitoryapi.domain.point.service;

import com.appsolute.soom.dormitoryapi.domain.point.data.dto.PointDto;
import com.appsolute.soom.dormitoryapi.domain.point.data.entity.PointEntity;
import com.appsolute.soom.dormitoryapi.domain.point.data.type.PointType;
import com.appsolute.soom.dormitoryapi.domain.point.repository.PointRepository;
import org.springframework.stereotype.Service;

@Service
public class PointServiceImpl implements PointService{
    PointRepository pointRepository;
    @Override
    public PointDto getPointByAccountUUID(String accountUUID) {
        return getOrCreatePointEntityByAccountUUID(accountUUID).toDto();
    }

    @Override
    public PointDto updatePoint(String accountUUID, PointType type, Integer point) {
        PointEntity entity = getOrCreatePointEntityByAccountUUID(accountUUID);
        switch (type) {
            case PENALTY -> entity.setPenaltyPoint(Math.max(entity.getPenaltyPoint() + point, 0));
            case REWARD -> entity.setRewardPoint(Math.max(entity.getRewardPoint() + point, 0));
        }
        return pointRepository.save(entity).toDto();
    }

    private PointEntity getOrCreatePointEntityByAccountUUID(String accountUUID) {
        PointEntity entity;
        if(!pointRepository.existsByAccountUUID(accountUUID))
            entity = pointRepository.save(new PointEntity(accountUUID));
        else entity = pointRepository.getByAccountUUID(accountUUID);
        return entity;
    }
}
