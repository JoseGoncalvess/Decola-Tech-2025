package br.com.DecolaTech.barber_shop_api.services.impl;

import br.com.DecolaTech.barber_shop_api.models.entity.ClientEntity;
import br.com.DecolaTech.barber_shop_api.repository.ClientEntityRepository;
import br.com.DecolaTech.barber_shop_api.services.IClientService;
import br.com.DecolaTech.barber_shop_api.services.query.IClientQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientService implements IClientService {

    private final ClientEntityRepository repository;
    private final IClientQueryService queryService;


    @Override
    public ClientEntity save(ClientEntity entity) {
        queryService.verifyEmail(entity.getEmail());
        queryService.verifyPhone(entity.getPhone());

        return repository.save(entity);
    }

    @Override
    public ClientEntity update(ClientEntity entity) {
        queryService.verifyEmail(entity.getEmail());
        queryService.verifyPhone(entity.getPhone());

        var client = queryService.findById(entity.getId());
        client.setName(entity.getName());
        client.setPhone(entity.getPhone());
        client.setEmail(entity.getEmail());
        return repository.save(entity);
    }

    @Override
    public void delete(long id) {
        queryService.findById(id);
        repository.deleteById(id);
    }
}
