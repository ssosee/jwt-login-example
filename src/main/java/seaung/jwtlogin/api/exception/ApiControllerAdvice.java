package seaung.jwtlogin.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import seaung.jwtlogin.api.response.ApiResponse;
import seaung.jwtlogin.api.response.ErrorResponse;

@RestControllerAdvice
public class ApiControllerAdvice {

    @ExceptionHandler(MemberException.class)
    public ResponseEntity<ApiResponse<Integer>> memberExceptionHandler(MemberException e) {
        int code = 1000;
        return new ResponseEntity<>(
                ApiResponse.of(e.getMessage(), HttpStatus.BAD_REQUEST, code),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ApiResponse<Integer>> bindExceptionHandler(BindException e) {
        int code = 100;
        return new ResponseEntity<>(
                ApiResponse.of(e.getBindingResult().getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST, code),
                HttpStatus.BAD_REQUEST);
    }
}
