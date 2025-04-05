package br.com.DecolaTech.barber_shop_api.services;

import br.com.DecolaTech.barber_shop_api.models.entity.ClientEntity;
import br.com.DecolaTech.barber_shop_api.models.entity.ScheduleEntity;
import org.springframework.stereotype.Service;

@Service
public interface IScheduleService {

    ScheduleEntity save(final ScheduleEntity entity);

    ScheduleEntity update(final ScheduleEntity entity);

    void delete(final long id);
}
