package cn.imeina.avm.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity serviceErrorHandler(ServiceException exception) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", exception.getCode());
        map.put("message", exception.getMessage());
        return ResponseEntity.ok(map);
    }
}
