package br.com.DecolaTech.barber_shop_api.services.query.impl;

import br.com.DecolaTech.barber_shop_api.models.entity.ClientEntity;
import br.com.DecolaTech.barber_shop_api.repository.ClientEntityRepository;
import br.com.DecolaTech.barber_shop_api.services.query.IClientQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ClientQueryService implements IClientQueryService {
    private final ClientEntityRepository repository;

    @Override
    public ClientEntity findById(long id) {
        return repository.findById(id).orElseThrow(()->
                new RuntimeException("Agendamento Não foi encontrado"));
    }

    @Override
    public List<ClientEntity> findAllClients() {
        return repository.findAll();
    }

    @Override
    public void verifyPhone(String phone) {
       if (repository.existsByPhone(phone)){
           throw  new RuntimeException("O telefone "+phone + " já está em uso");
       }
    }

    @Override
    public void verifyPhoneAndId(long id, String phone) {
        var optional = repository.findById(id);
        if (optional.isPresent() && Objects.equals(optional.get().getPhone(),phone)) {
            throw  new RuntimeException("O telefone "+phone + " já Existe e pertence ao usuario "+optional.get().getId());
        }
    }

    @Override
    public void verifyEmail(String email) {

        if (repository.existsByEmail(email)) {
            var message = "O e-mail " + email + " já está em uso";
            throw new RuntimeException(message);

        }
    }

    @Override
    public void verifyEmailAndId(long id, String email) {
            var optional = repository.findById(id);
            if (optional.isPresent() && Objects.equals(optional.get().getPhone(),email)) {
                throw  new RuntimeException("O Email "+ email + " já Existe e pertence ao usuario "+optional.get().getId());
            }
    }
}
