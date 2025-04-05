package br.com.DecolaTech.barber_shop_api.services.impl;

import br.com.DecolaTech.barber_shop_api.models.entity.ScheduleEntity;
import br.com.DecolaTech.barber_shop_api.repository.ScheduleEntityRepository;
import br.com.DecolaTech.barber_shop_api.services.IScheduleService;
import br.com.DecolaTech.barber_shop_api.services.query.IScheduleQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ScheduleService implements IScheduleService {
    private final ScheduleEntityRepository repository;
    private final IScheduleQueryService queryService;

    @Override
    public ScheduleEntity save(ScheduleEntity entity) {
        queryService.verifyIfScheduleExists(entity.getStartAt(), entity.getEndAt());
        return repository.save(entity);
    }

    @Override
    public ScheduleEntity update(ScheduleEntity entity) {
        queryService.verifyIfScheduleExists(entity.getStartAt(), entity.getEndAt());
        var schedule = queryService.findById(entity.getId());
        schedule.setStartAt(entity.getStartAt());
        schedule.setEndAt(entity.getEndAt());
        return repository.save(schedule);
    }

    @Override
    public void delete(long id) {
        queryService.findById(id);
        repository.deleteById(id);
    }
}
