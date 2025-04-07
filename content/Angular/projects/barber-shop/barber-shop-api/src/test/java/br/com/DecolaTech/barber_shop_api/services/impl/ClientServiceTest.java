package br.com.DecolaTech.barber_shop_api.services.impl;

import br.com.DecolaTech.barber_shop_api.exception.EmailInUseException;
import br.com.DecolaTech.barber_shop_api.exception.PhoneInUseException;
import br.com.DecolaTech.barber_shop_api.models.entity.ClientEntity;
import br.com.DecolaTech.barber_shop_api.repository.ClientEntityRepository;
import br.com.DecolaTech.barber_shop_api.services.query.IClientQueryService;
import br.com.DecolaTech.barber_shop_api.services.query.impl.ClientQueryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.util.Assert;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class ClientServiceTest {
    ClientEntity cleint = new ClientEntity(1L,"João","teste@gmail.com","87991959595", Set.of());

    @Mock()
    private static ClientEntityRepository repository ;


    @InjectMocks
    private  ClientQueryService queryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void saveToGreat() {

        when(repository.existsByEmail(cleint.getEmail())).thenReturn(false);
        when(queryService.verifyEmail(cleint.getEmail())).thenReturn(true);

        when(repository.existsByPhone(cleint.getPhone())).thenReturn(false);
        when(queryService.verifyPhone(cleint.getPhone())).thenReturn(true);


        when(repository.save(cleint)).thenReturn(cleint);


        ClientEntity newClient = repository.save(cleint);

        assertEquals(newClient.getName(),"João");
        assertEquals(newClient.getPhone(),"87991959595");
        assertEquals(newClient.getId(),1L);
        assertEquals(newClient.getSchedules(), Set.of());
    }
    @Test
    void saveToEmailInUseException() {
        String message = "O e-mail " + cleint.getEmail() + " já está em uso";
        when(repository.existsByEmail(cleint.getEmail())).thenReturn(true);

        // Verificação da exceção esperada
        EmailInUseException exception = assertThrows(EmailInUseException.class, () -> {
            queryService.verifyEmail(cleint.getEmail());
        });

        // Validar a mensagem da exceção, se necessário
        assertEquals(message, exception.getMessage());

        // Verificar interações no mock
        verify(repository).existsByEmail(cleint.getEmail());

    }

    @Test
    void saveToPhoneInUseException() {
        String PhoneInUseException = "O telefone "+ cleint.getPhone() + " já está em uso";
        when(repository.existsByPhone(cleint.getPhone())).thenReturn(true);

        // Verificação da exceção esperada
        PhoneInUseException exception = assertThrows(PhoneInUseException.class, () -> {
            queryService.verifyPhone(cleint.getPhone());
        });

        // Validar a mensagem da exceção, se necessário
        assertEquals(PhoneInUseException, exception.getMessage());

        // Verificar interações no mock
        verify(repository).existsByPhone(cleint.getPhone());

    }


    @Test
    void update() {
    }

    @Test
    void delete() {
    }



}