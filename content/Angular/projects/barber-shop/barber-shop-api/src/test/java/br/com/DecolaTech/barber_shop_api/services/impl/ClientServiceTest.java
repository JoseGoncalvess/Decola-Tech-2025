package br.com.DecolaTech.barber_shop_api.services.impl;

import br.com.DecolaTech.barber_shop_api.exception.EmailInUseException;
import br.com.DecolaTech.barber_shop_api.models.entity.ClientEntity;
import br.com.DecolaTech.barber_shop_api.repository.ClientEntityRepository;
import br.com.DecolaTech.barber_shop_api.services.query.IClientQueryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


class ClientServiceTest {
    ClientEntity client = new ClientEntity(1L,"João","teste@gmail.com","87991959595", Set.of());

    @Mock()
    private static ClientEntityRepository repository ;
    @Mock()
    private static IClientQueryService queryService;


    @InjectMocks
    private  ClientService clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void saveToGreat() {

        when(repository.existsByEmail(client.getEmail())).thenReturn(false);
        when(repository.existsByPhone(client.getPhone())).thenReturn(false);
        when(repository.save(client)).thenReturn(client);

        ClientEntity newClient = clientService.save(client);

        assertEquals(newClient.getName(),"João");
        assertEquals(newClient.getPhone(),"87991959595");
        assertEquals(newClient.getId(),1L);
        assertEquals(newClient.getSchedules(), Set.of());
    }


    @Test
    void saveToPhoneInUseException() {

        String PhoneInUseException = "O telefone "+ client.getPhone() + " já está em uso";

        // Configuração dos mocks para lançar exceção
        doThrow(new RuntimeException(PhoneInUseException)).when(queryService).verifyEmail(client.getEmail());

        // Execução do teste e verificação da exceção
        RuntimeException exception = assertThrows(RuntimeException.class, () -> clientService.save(client));
        assertEquals("O telefone "+ client.getPhone() + " já está em uso", exception.getMessage());
    }




    @Test
    void updateOfGreat() {
        ClientEntity newcleint = new ClientEntity(12L,"Rebeka","testando@gmail.com","89991958476", Set.of());
        when(repository.findById(anyLong())).thenReturn(Optional.of(client));
        when(queryService.findById(anyLong())).thenReturn(client);
        when(clientService.update(newcleint)).thenReturn(newcleint);

        ClientEntity clinetUpdate =  clientService.update(newcleint);

        assertEquals(clinetUpdate.getName(),"Rebeka");
        assertEquals(clinetUpdate.getPhone(),"89991958476");
        assertEquals(clinetUpdate.getEmail(),"testando@gmail.com");
        assertEquals(clinetUpdate.getId(),12L);

    }



    @Test
    void deleteOfGreat() {
        when(queryService.findById(client.getId())).thenReturn(client);

        clientService.delete(client.getId());

        verify(repository).deleteById(client.getId());
        verify(queryService).findById(client.getId());

    }

    @Test
    void deleteOfException() {
        when( queryService.verifyEmail(client.getEmail())).thenReturn(true);
        when(queryService.findById(client.getId())).thenReturn(client);

        clientService.delete(client.getId());

        verify(repository).deleteById(client.getId());
        verify(queryService).findById(client.getId());

    }




    @Test
    void deleteorException() {
        when(queryService.findById(client.getId())).thenThrow(new  EmailInUseException("O e-mail " + client.getEmail() + " já está em uso"));

        EmailInUseException exception   = assertThrows(EmailInUseException.class, () -> {
            queryService.findById(client.getId());});

        verify(queryService).findById(client.getId());
        assertEquals(exception.getMessage(), "O e-mail " + client.getEmail() + " já está em uso");

    }

}