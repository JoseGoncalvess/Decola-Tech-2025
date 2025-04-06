package br.com.DecolaTech.barber_shop_api.mapers;

import br.com.DecolaTech.barber_shop_api.Controller.request.SaveClientRequest;
import br.com.DecolaTech.barber_shop_api.Controller.request.UpdateClientRequest;
import br.com.DecolaTech.barber_shop_api.Controller.response.ClientDetailResponse;
import br.com.DecolaTech.barber_shop_api.Controller.response.ListClientResponse;
import br.com.DecolaTech.barber_shop_api.Controller.response.SaveClientResponse;
import br.com.DecolaTech.barber_shop_api.Controller.response.UpdateClientResponse;
import br.com.DecolaTech.barber_shop_api.models.entity.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface ClientMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "schedules", ignore = true)
    ClientEntity toEntity(final SaveClientRequest request);

    SaveClientResponse toSaveResponse(final ClientEntity entity);

    @Mapping(target = "schedules", ignore = true)
    ClientEntity toEntity(final long id, final UpdateClientRequest request);

    UpdateClientResponse toUpdateResponse(final ClientEntity entity);

    ClientDetailResponse toDetailResponse(final ClientEntity entity);

    List<ListClientResponse> toListResponse(final List<ClientEntity> entities);
}
