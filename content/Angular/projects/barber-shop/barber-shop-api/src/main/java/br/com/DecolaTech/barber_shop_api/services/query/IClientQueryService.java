package br.com.DecolaTech.barber_shop_api.services.query;

import br.com.DecolaTech.barber_shop_api.models.entity.ClientEntity;

import java.util.List;

public interface IClientQueryService {
    ClientEntity findById(final long id);

    List<ClientEntity> findAllClients();

    Boolean verifyPhone(final String phone);

    void verifyPhoneAndId(final long id,final String phone);

    Boolean verifyEmail(final String email);

    void verifyEmailAndId(final long id,final String email);
}
