package com.appsolute.soom.dormitoryapi.domain.study.service;

import com.appsolute.soom.dormitoryapi.domain.study.data.dto.ReserveDto;
import com.appsolute.soom.dormitoryapi.domain.study.data.entity.ReserveEntity;
import com.appsolute.soom.dormitoryapi.domain.study.exception.AlreadyReservedException;
import com.appsolute.soom.dormitoryapi.global.data.type.SchoolType;
import com.appsolute.soom.dormitoryapi.domain.study.exception.ReserveNotFoundException;
import com.appsolute.soom.dormitoryapi.domain.study.repository.ReserveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudyReserveServiceImpl implements StudyReserveService{
    private final ReserveRepository reserveRepository;

    @Override
    public ReserveDto reserve(SchoolType type, String accountUUID) {
        if(reserveRepository.existsByAccountUUIDAndReserveAt(accountUUID, LocalDate.now()))
            throw new AlreadyReservedException(accountUUID);

        ReserveEntity entity = new ReserveEntity(accountUUID, LocalDate.now(), type);
        return reserveRepository.save(entity).toDto();
    }

    @Override @Transactional
    public ReserveDto getReserve(String accountUUID) {
        checkReserveIsExists(accountUUID);
        ReserveEntity entity = reserveRepository.getByAccountUUIDAndReserveAt(accountUUID, LocalDate.now());
        return entity.toDto();
    }

    @Override @Transactional
    public void cancelReserve(String accountUUID) {
        checkReserveIsExists(accountUUID);
        reserveRepository.deleteByAccountUUIDAndReserveAt(accountUUID, LocalDate.now());
    }

    private void checkReserveIsExists(String accountUUID) {
        if(!reserveRepository.existsByAccountUUIDAndReserveAt(accountUUID, LocalDate.now())) throw new ReserveNotFoundException(accountUUID);
    }

    @Override
    public List<String> getAllAccountUUIDAtReserve(SchoolType school) {
        return reserveRepository.getAllBySchoolAndReserveAt(school, LocalDate.now()).stream().map(ReserveEntity::getAccountUUID).toList();
    }
}
