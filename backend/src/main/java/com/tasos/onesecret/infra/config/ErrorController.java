package com.tasos.onesecret.infra.config;

import com.tasos.onesecret.infra.exceptions.CustomRuntimeException;
import jakarta.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ErrorController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = CustomRuntimeException.class)
    protected ResponseEntity<?> handleBadRequestException(CustomRuntimeException e, WebRequest request) {
        log.error(e.getMessage(), e);
        Map<String, String> response = new HashMap<>();
        response.put("message", e.getMessage());
        response.put("stack", getStackTrace(e));
        if (request instanceof ServletWebRequest) {
            HttpServletRequest httpRequest = ((ServletWebRequest) request).getRequest();
            response.put(
                    "path",
                    httpRequest.getRequestURI()
                            + Optional.ofNullable(httpRequest.getQueryString())
                                    .map(qs -> "?" + qs)
                                    .orElse(""));
        }
        return ResponseEntity.status(e.getCode())
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    private String getStackTrace(Throwable t) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        t.printStackTrace(printWriter);
        return stringWriter.toString();
    }
}
