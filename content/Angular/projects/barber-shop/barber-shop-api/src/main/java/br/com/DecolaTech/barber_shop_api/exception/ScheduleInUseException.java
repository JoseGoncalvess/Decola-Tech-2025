package br.com.DecolaTech.barber_shop_api.exception;


public class ScheduleInUseException extends RuntimeException {

    public ScheduleInUseException(String message) {
        super(message);
    }

}
