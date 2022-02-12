package com.appsolute.soom.dormitoryapi.domain.point.repository;

import com.appsolute.soom.dormitoryapi.domain.point.data.entity.PointEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<PointEntity, String> {
    boolean existsByAccountUUID(String accountUUID);

    PointEntity getByAccountUUID(String accountUUID);
}
