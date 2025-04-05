package br.com.DecolaTech.barber_shop_api.Controller.response;


import com.fasterxml.jackson.annotation.JsonProperty;

public record ClientScheduleAppointmentDetailResponse(
        @JsonProperty("id")
        Long id,
        @JsonProperty("name")
        String name,
        @JsonProperty("email")
        String email,
        @JsonProperty("phone")
        String phone
) {}
