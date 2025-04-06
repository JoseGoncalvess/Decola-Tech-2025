package br.com.DecolaTech.barber_shop_api.Controller;

import br.com.DecolaTech.barber_shop_api.Controller.request.SaveClientRequest;
import br.com.DecolaTech.barber_shop_api.Controller.request.UpdateClientRequest;
import br.com.DecolaTech.barber_shop_api.Controller.response.ClientDetailResponse;
import br.com.DecolaTech.barber_shop_api.Controller.response.ListClientResponse;
import br.com.DecolaTech.barber_shop_api.Controller.response.SaveClientResponse;
import br.com.DecolaTech.barber_shop_api.Controller.response.UpdateClientResponse;
import br.com.DecolaTech.barber_shop_api.mapers.ClientMapper;
import br.com.DecolaTech.barber_shop_api.services.IClientService;
import br.com.DecolaTech.barber_shop_api.services.query.IClientQueryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("api/v1/clients")
@AllArgsConstructor
public class ClientController {
    private final IClientService service;
    private final IClientQueryService queryService;
    private final ClientMapper mapper;

    @PostMapping
    @ResponseStatus(CREATED)
    SaveClientResponse save(@RequestBody @Valid final SaveClientRequest request){
        System.out.println(request.toString());
        var entity = mapper.toEntity(request);
        service.save(entity);
        return mapper.toSaveResponse(entity);
    }

    @PutMapping("{id}")
    UpdateClientResponse update(@PathVariable final long id, @RequestBody @Valid final UpdateClientRequest request){
        var entity = mapper.toEntity(id, request);
        service.update(entity);
        return mapper.toUpdateResponse(entity);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    void delete(@PathVariable final long id){
        service.delete(id);
    }

    @GetMapping("{id}")
    ClientDetailResponse findById(@PathVariable final long id){
        var entity = queryService.findById(id);
        return mapper.toDetailResponse(entity);
    }

    @GetMapping
    List<ListClientResponse> allClients(){
        var entities = queryService.findAllClients();
        return mapper.toListResponse(entities);
    }
}
