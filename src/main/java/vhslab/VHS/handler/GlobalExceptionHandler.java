package vhslab.VHS.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiError> handleRuntime(RuntimeException runtimeException) {
        ApiError error = new ApiError(HttpStatus.BAD_GATEWAY.value(), runtimeException.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException methodArgumentNotValidException) {
        String message = methodArgumentNotValidException.getBindingResult().getFieldError().getDefaultMessage();
        ApiError error = new ApiError(HttpStatus.BAD_GATEWAY.value(), message);
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(VHSAlreadyRentedException.class)
    public ResponseEntity<ApiError> handleVhsAlreadyRented(VHSAlreadyRentedException exception) {
        ApiError error = new ApiError(HttpStatus.CONFLICT.value(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

}
