package br.com.DecolaTech.barber_shop_api.services;

import br.com.DecolaTech.barber_shop_api.models.entity.ClientEntity;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

@Service

public interface IClientService {

    ClientEntity save(final ClientEntity entity);

    ClientEntity update(final ClientEntity entity);

    void delete(final long id);
}
