package com.appsolute.soom.dormitoryapi.domain.study.repository;

import com.appsolute.soom.dormitoryapi.domain.study.data.entity.ReserveEntity;
import com.appsolute.soom.dormitoryapi.domain.study.data.type.SchoolType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface ReserveRepository extends JpaRepository<ReserveEntity, Long> {
    boolean existsByAccountUUIDAndReserveAt(String accountUUID, LocalDate reserveAt);

    ReserveEntity getByAccountUUIDAndReserveAt(String accountUUID, LocalDate reserveAt);

    List<ReserveEntity> getAllBySchoolAndReserveAt(SchoolType school, LocalDate reserveAt);

    @Transactional
    void deleteByAccountUUIDAndReserveAt(String accountUUID, LocalDate reserveAt);
}
