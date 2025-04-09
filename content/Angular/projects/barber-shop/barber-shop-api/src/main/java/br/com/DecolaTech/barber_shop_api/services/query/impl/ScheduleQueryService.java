package br.com.DecolaTech.barber_shop_api.services.query.impl;

import br.com.DecolaTech.barber_shop_api.exception.NotFoundException;
import br.com.DecolaTech.barber_shop_api.exception.ScheduleInUseException;
import br.com.DecolaTech.barber_shop_api.models.entity.ScheduleEntity;
import br.com.DecolaTech.barber_shop_api.repository.ScheduleEntityRepository;
import br.com.DecolaTech.barber_shop_api.services.query.IScheduleQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ScheduleQueryService implements IScheduleQueryService {

    private ScheduleEntityRepository repository;
    @Override
    public ScheduleEntity findById(long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Agendamento não encontrado")
        );
    }

    @Override
    public List<ScheduleEntity> findInMonth(OffsetDateTime startAt, OffsetDateTime endAt) {
        return repository.findByStartAtGreaterThanEqualAndEndAtLessThanEqualOrderByStartAtAscEndAtAsc(startAt,endAt);
    }

    @Override
    public boolean verifyIfScheduleExists(OffsetDateTime startAt, OffsetDateTime endAt) {
        if (repository.existsByStartAtAndEndAt(startAt, endAt)) {
            throw  new ScheduleInUseException("Já existe um cliente agendado no horário solicitado");
            
        }
        return  true;
    }
}
