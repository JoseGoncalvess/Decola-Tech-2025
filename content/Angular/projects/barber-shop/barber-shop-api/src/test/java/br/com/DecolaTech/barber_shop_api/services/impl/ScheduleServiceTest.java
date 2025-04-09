package br.com.DecolaTech.barber_shop_api.services.impl;

import br.com.DecolaTech.barber_shop_api.BarberShopApiApplication;
import br.com.DecolaTech.barber_shop_api.exception.NotFoundException;
import br.com.DecolaTech.barber_shop_api.exception.ScheduleInUseException;
import br.com.DecolaTech.barber_shop_api.models.entity.ClientEntity;
import br.com.DecolaTech.barber_shop_api.models.entity.ScheduleEntity;
import br.com.DecolaTech.barber_shop_api.repository.ScheduleEntityRepository;
import br.com.DecolaTech.barber_shop_api.services.query.impl.ScheduleQueryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.*;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


class ScheduleServiceTest {
    ClientEntity client = new ClientEntity();
    ScheduleEntity scheduleEntity  = new ScheduleEntity();

    @Mock
    private static ScheduleEntityRepository repository;
    @Mock
    private static ScheduleQueryService queryService;

    @InjectMocks
    private  static ScheduleService scheduleService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        client = new ClientEntity(1L,"João","teste@gmail.com","87991959595", Set.of());
    scheduleEntity = new ScheduleEntity(1L,OffsetDateTime.now(),OffsetDateTime.now().plusHours(1L),client);


    }



    @Test
    void testRepositoryMock() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(scheduleEntity));
        Optional<ScheduleEntity> result = repository.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(scheduleEntity, result.get());
    }




    @Test
    void saveOfGreat() {
        when(queryService.verifyIfScheduleExists(scheduleEntity.getStartAt(), scheduleEntity.getEndAt())).thenReturn(true);
        when(repository.save(scheduleEntity)).thenReturn(scheduleEntity);
        when(scheduleService.save(scheduleEntity)).thenReturn(scheduleEntity);
        ScheduleEntity NewScheduleEntity = scheduleService.save(scheduleEntity);

        assertEquals(NewScheduleEntity.getClient(),client);
        assertEquals(NewScheduleEntity.getId(),1L);
        assertEquals(NewScheduleEntity.getStartAt(),scheduleEntity.getStartAt());
        assertEquals(NewScheduleEntity.getEndAt(),scheduleEntity.getEndAt());
    }

    @Test
    void saveThrowsScheduleInUseException() {
        // Configurando o comportamento do mock: queryService lança a exceção
        when(queryService.verifyIfScheduleExists(any(OffsetDateTime.class), any(OffsetDateTime.class)))
                .thenThrow(new ScheduleInUseException("Já existe um cliente agendado no horário solicitado"));

        // Criando a exceção esperada
        ScheduleInUseException exception = assertThrows(ScheduleInUseException.class, () -> {
            // Chamando o método que deve lançar a exceção
            scheduleService.save(scheduleEntity);
        });

        // Verificando se a mensagem da exceção está correta
        assertEquals("Já existe um cliente agendado no horário solicitado", exception.getMessage());

        // Verificando se o método mock foi chamado com os argumentos esperados
        verify(queryService).verifyIfScheduleExists(scheduleEntity.getStartAt(), scheduleEntity.getEndAt());

        // Certifique-se de que o repositório não foi chamado, já que a exceção deve interromper a execução
        verify(repository, never()).save(any(ScheduleEntity.class));
    }


//    @Test
//    void saveOfException() {
//        when(repository.existsByStartAtAndEndAt(scheduleEntity.getStartAt(),
//        scheduleEntity.getEndAt())).thenReturn(false);
//
//        when(queryService.verifyIfScheduleExists(scheduleEntity.getStartAt(), scheduleEntity.getEndAt()))
//                .thenThrow(new ScheduleInUseException("Já existe um cliente agendado no horário solicitado"));
//
//
//        ScheduleInUseException exception = assertThrows(
//                ScheduleInUseException.class, () -> {
//            scheduleService.save(scheduleEntity);
//        });
//
//        assertEquals("Já existe um cliente agendado no horário solicitado", exception.getMessage());
//
//        verify(scheduleService).save(scheduleEntity);
//    }



    @Test
    void updateOfGreat() {
        ClientEntity newcleint = new ClientEntity(12L,"Maria","teste@gmail.com","87991552849", Set.of());
        ScheduleEntity newScheduleEntity = new ScheduleEntity(15L,OffsetDateTime.now().minusHours(3L),OffsetDateTime.now().plusHours(1L),newcleint);

        when(repository.findById(anyLong())).thenReturn(Optional.of(newScheduleEntity));
        when(queryService.findById(anyLong())).thenReturn(newScheduleEntity);

        when(queryService.verifyIfScheduleExists(
                scheduleEntity.getStartAt(),
                scheduleEntity.getEndAt())).thenReturn(true);

        when(repository.save(scheduleEntity)).thenReturn(newScheduleEntity);
        when(scheduleService.update(scheduleEntity)).thenReturn(newScheduleEntity);

        ScheduleEntity ScheduleUpdate =  scheduleService.update(newScheduleEntity);

        assertEquals("Maria",ScheduleUpdate.getClient().getName());
        assertEquals(15L,ScheduleUpdate.getId());
        assertEquals(ScheduleUpdate.getStartAt(),ScheduleUpdate.getStartAt());

        verify(repository).save(newScheduleEntity);
    }


    @Test
    void updateOfException() {
        when(queryService.verifyIfScheduleExists(
                scheduleEntity.getStartAt(),
                scheduleEntity.getEndAt())).thenThrow(new ScheduleInUseException("Já existe um cliente agendado no horário solicitado"));

        ScheduleInUseException exception = assertThrows(ScheduleInUseException.class, () -> {
            scheduleService.save(scheduleEntity);
        });

        assertEquals("Já existe um cliente agendado no horário solicitado", exception.getMessage());

        verify(queryService).verifyIfScheduleExists(scheduleEntity.getStartAt(), scheduleEntity.getEndAt());
    }


    @Test
    void delete() {
        when(queryService.findById(scheduleEntity.getClient().getId())).thenReturn(scheduleEntity);

        scheduleService.delete(scheduleEntity.getClient().getId());

        verify(repository).deleteById(scheduleEntity.getClient().getId());
        verify(queryService).findById(scheduleEntity.getClient().getId());

    }


    @Test
    void deleteOfException() {
        when(queryService.findById(anyLong())).thenThrow(new NotFoundException("Agendamento não encontrado"));

        NotFoundException exception   = assertThrows(NotFoundException.class, () -> {
            queryService.findById(scheduleEntity.getClient().getId());});

        verify(queryService).findById(scheduleEntity.getClient().getId());
        assertEquals("Agendamento não encontrado", exception.getMessage());
    }



}