package br.com.DecolaTech.barber_shop_api.mapers;


import br.com.DecolaTech.barber_shop_api.Controller.request.SaveScheduleRequest;
import br.com.DecolaTech.barber_shop_api.Controller.response.ClientScheduleAppointmentResponse;
import br.com.DecolaTech.barber_shop_api.Controller.response.SaveScheduleResponse;
import br.com.DecolaTech.barber_shop_api.Controller.response.ScheduleAppointmentMonthResponse;
import br.com.DecolaTech.barber_shop_api.models.entity.ScheduleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface ScheduleMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "client.id", source = "clientId")
    ScheduleEntity toEntity(final SaveScheduleRequest request);

    @Mapping(target = "clientId", source = "client.id")
    SaveScheduleResponse toSaveResponse(final ScheduleEntity entity);

    @Mapping(target = "scheduledAppointments", expression = "java(toClientMonthResponse(entities))")
    ScheduleAppointmentMonthResponse toMonthResponse(final int year, final int month, final List<ScheduleEntity> entities);

    List<ClientScheduleAppointmentResponse> toClientMonthResponse(final List<ScheduleEntity> entities);

    @Mapping(target = "clientId", source = "client.id")
    @Mapping(target = "clientName", source = "client.name")
    @Mapping(target = "day", expression = "java(entity.getStartAt().getDayOfMonth())")
    ClientScheduleAppointmentResponse toClientMonthResponse(final ScheduleEntity entity);

}
