package br.com.DecolaTech.barber_shop_api.exception.exceptionhandler;

import br.com.DecolaTech.barber_shop_api.Controller.response.ProblemResponse;
import br.com.DecolaTech.barber_shop_api.exception.EmailInUseException;
import br.com.DecolaTech.barber_shop_api.exception.NotFoundException;
import br.com.DecolaTech.barber_shop_api.exception.PhoneInUseException;
import br.com.DecolaTech.barber_shop_api.exception.ScheduleInUseException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
@Log4j2
@ControllerAdvice
public class BarberShopExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleUncaught(final Exception ex, final WebRequest request){
        log.error("handleUncaught: ", ex);
        var status = INTERNAL_SERVER_ERROR;
        var response = ProblemResponse.builder()
                .status(status.value())
                .timestamp(OffsetDateTime.now())
                .message(ex.getMessage())
                .build();
        return handleExceptionInternal(ex, response, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EmailInUseException.class)
    public ResponseEntity<?> EmailInUseExceptionHandle(final Exception ex, final WebRequest request){
        log.error("EmailInUseException: ", ex);
        var status = HttpStatus.CONFLICT;
        var response = ProblemResponse.builder()
                .status(status.value())
                .timestamp(OffsetDateTime.now())
                .message(ex.getMessage())
                .build();
        return handleExceptionInternal(ex, response, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(PhoneInUseException.class)
    public ResponseEntity<?> PhoneInUseExceptionHandle(final Exception ex, final WebRequest request){
        log.error("PhoneInUseException: ", ex);
        var status = HttpStatus.CONFLICT;
        var response = ProblemResponse.builder()
                .status(status.value())
                .timestamp(OffsetDateTime.now())
                .message(ex.getMessage())
                .build();
        return handleExceptionInternal(ex, response, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(ScheduleInUseException.class)
    public ResponseEntity<?> ScheduleInUseExceptionHandle(final Exception ex, final WebRequest request){
        log.error("ScheduleInUseException: ", ex);
        var status = HttpStatus.BAD_REQUEST;
        var response = ProblemResponse.builder()
                .status(status.value())
                .timestamp(OffsetDateTime.now())
                .message(ex.getMessage())
                .build();
        return handleExceptionInternal(ex, response, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> NotFoundExceptionHandle(final Exception ex, final WebRequest request){
        log.error("NotFoundException: ", ex);
        var status = HttpStatus.BAD_REQUEST;
        var response = ProblemResponse.builder()
                .status(status.value())
                .timestamp(OffsetDateTime.now())
                .message(ex.getMessage())
                .build();
        return handleExceptionInternal(ex, response, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> RuntimeExceptionHandle(final Exception ex, final WebRequest request){
        log.error("NotFoundException: ", ex);
        var status = HttpStatus.BAD_REQUEST;
        var response = ProblemResponse.builder()
                .status(status.value())
                .timestamp(OffsetDateTime.now())
                .message(ex.getMessage())
                .build();
        return handleExceptionInternal(ex, response, new HttpHeaders(), status, request);
    }



}