package com.appsolute.soom.dormitoryapi.domain.remain.service;

import com.appsolute.soom.dormitoryapi.domain.remain.data.dto.RemainDto;
import com.appsolute.soom.dormitoryapi.domain.remain.data.entity.RemainEntity;
import com.appsolute.soom.dormitoryapi.domain.remain.exception.AlreadyRemainedException;
import com.appsolute.soom.dormitoryapi.domain.remain.exception.RemainNotFoundException;
import com.appsolute.soom.dormitoryapi.domain.remain.repository.RemainRepository;
import com.appsolute.soom.dormitoryapi.global.data.type.SchoolType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RemainServiceImpl implements RemainService{
    private final RemainRepository remainRepository;
    private final DateService dateService;

    @Override @Transactional
    public RemainDto addRemain(String accountUUID, SchoolType school) {
        if(remainRepository.existsByAccountUUIDAndRemainAt(accountUUID, getRemainAt())) throw new AlreadyRemainedException();
        RemainEntity entity = new RemainEntity(accountUUID, getRemainAt(), school);
        return remainRepository.save(entity).toDto();
    }

    @Override @Transactional
    public RemainDto getRemain(String accountUUID) {
        checkRemainExists(accountUUID);
        return remainRepository.getByAccountUUIDAndRemainAt(accountUUID, getRemainAt()).toDto();
    }

    @Override
    public List<RemainDto> getRemainList(SchoolType school) {
        return remainRepository.getBySchoolAndRemainAt(school, getRemainAt());
    }

    @Override @Transactional
    public void removeRemain(String accountUUID) {
        checkRemainExists(accountUUID);
        remainRepository.deleteByAccountUUID(accountUUID);
    }

    private void checkRemainExists(String accountUUID) {
        if(!remainRepository.existsByAccountUUIDAndRemainAt(accountUUID, getRemainAt())) throw new RemainNotFoundException(accountUUID);
    }

    private LocalDate getRemainAt() {
        return dateService.getRemainStartDay();
    }
}
