
package validate;

import validate.MissionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class MissionExceptionHandler {

    @ExceptionHandler(MissionException.class)
    public ResponseEntity<Map<String, Object>> handleMissionException(MissionException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", ex.getMessage());
        response.put("errorCode", ex.getErrorCode());
        response.put("timestamp", LocalDateTime.now());
        
        HttpStatus status = getHttpStatus(ex.getErrorCode());
        return new ResponseEntity<>(response, status);
    }
    
    private HttpStatus getHttpStatus(String errorCode) {
        if (errorCode == null) return HttpStatus.BAD_REQUEST;
        
        switch (errorCode) {
            case "NOT_FOUND":
                return HttpStatus.NOT_FOUND;
            case "DUPLICATE_ERROR":
                return HttpStatus.CONFLICT;
            case "VALIDATION_ERROR":
            case "PARSE_ERROR":
            default:
                return HttpStatus.BAD_REQUEST;
        }
    }
}
