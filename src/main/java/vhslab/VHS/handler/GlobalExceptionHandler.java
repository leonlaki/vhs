package vhslab.VHS.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Arrays;
import java.util.stream.Collectors;

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

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiError> handleEnumError(MethodArgumentTypeMismatchException exception) {
        Class<?> requiredType = exception.getRequiredType();

        if(requiredType != null && requiredType.isEnum()) {
            String allowedValues = Arrays.stream(requiredType.getEnumConstants()).map(Object::toString).collect(Collectors.joining(", "));
            return ResponseEntity.badRequest().body(new ApiError(400, "Invalid genre value. Valid values are: " + allowedValues));
        }

        return ResponseEntity.badRequest().body(new ApiError(400, "Invalid request parameter."));
    }

}
