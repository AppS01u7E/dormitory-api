package com.appsolute.soom.dormitoryapi.domain.remain.repository;

import com.appsolute.soom.dormitoryapi.domain.remain.data.dto.RemainDto;
import com.appsolute.soom.dormitoryapi.domain.remain.data.entity.RemainEntity;
import com.appsolute.soom.dormitoryapi.global.data.type.SchoolType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface RemainRepository extends JpaRepository<RemainEntity, Long> {
    @Transactional
    void deleteByAccountUUID(String accountUUID);

    RemainEntity getByAccountUUIDAndRemainAt(String accountUUID, LocalDate remainAt);

    List<RemainDto> getBySchoolAndRemainAt(SchoolType school, LocalDate remainAt);

    boolean existsByAccountUUIDAndRemainAt(String accountUUID, LocalDate remainAt);
}
