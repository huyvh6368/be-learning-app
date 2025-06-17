package web.elearning.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import web.elearning.dto.ResponseError;


import java.io.IOException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handleBadCredentials(BadCredentialsException ex) {
        ResponseError error = new ResponseError(
                HttpStatus.BAD_REQUEST.value(),
                "email or password is invalid",
                LocalDateTime.now(),
                ex.getMessage(),
                getFilePath(ex)
        );
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllExceptions(Exception ex) {
        ResponseError error = new ResponseError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Lỗi hệ thống",
                LocalDateTime.now(),
                ex.getMessage(),
                getFilePath(ex)
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException ex) {
        ResponseError error = new ResponseError(
                HttpStatus.BAD_REQUEST.value(),
                "Lỗi xử lý dữ liệu",
                LocalDateTime.now(),
                ex.getMessage(),
                getFilePath(ex)
        );
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<?> handleNumberFormatException(NumberFormatException ex) {
        ResponseError error = new ResponseError(
                HttpStatus.BAD_REQUEST.value(),
                "Score không hợp lệ. Vui lòng nhập đúng định dạng số.",
                LocalDateTime.now(),
                ex.getMessage(),
                getFilePath(ex)
        );
        return ResponseEntity.badRequest().body(error);
    }

    private String getFilePath(Exception ex) {
        StackTraceElement[] stackTrace = ex.getStackTrace();
        if (stackTrace.length > 0) {
            StackTraceElement element = stackTrace[0];
            return element.getClassName() + " (line " + element.getLineNumber() + ")";
        }
        return "Unknown";
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<String> handleMaxSizeException(MaxUploadSizeExceededException exc) {
        return ResponseEntity.badRequest().body("File size exceeds the maximum limit");
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> handleIOException(IOException exc) {
        return ResponseEntity.badRequest().body(exc.getMessage());
    }
}
