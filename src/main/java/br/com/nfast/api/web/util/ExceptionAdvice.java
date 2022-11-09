package br.com.nfast.api.web.util;

import br.com.nfast.api.utils.Strings;
import org.hibernate.JDBCException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
public class ExceptionAdvice {
    private static final Logger log = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ExceptionHandler
    public ResponseEntity<ApiFail> handleApiException(ApiException ex) {
        return error(ex.getStatus(), ex);
    }

    @ExceptionHandler
    public ResponseEntity<ApiFail> handleRuntimeException(Exception ex) {
        return error(HttpStatus.BAD_REQUEST, ex);
    }

    private ResponseEntity<ApiFail> error(HttpStatus status, Exception ex) {
        if (ex instanceof RuntimeException)
            log.error(ex.getMessage());
        else log.error(ex.getMessage(), ex);

        String message = handleException(ex);
        return ResponseEntity.status(status).body(new ApiFail(status, message));
    }

    public String handleException(Throwable ex) {
        String message = null;
        Throwable throwable = ex.getCause();
        if ((throwable != null) && (throwable instanceof JDBCException)) {
            SQLException sqlException = ((JDBCException) throwable).getSQLException();
            while (sqlException.getNextException() != null)
                sqlException = sqlException.getNextException();

            message = sqlException.getLocalizedMessage();
            if (Strings.containsAny(message, "Onde"))
                message = Strings.lcopy(message, "Onde", false);
            else if (Strings.containsAny(message, "CONTEXT"))
                message = Strings.lcopy(message, "CONTEXT", false);

            if (Strings.containsAny(message, "ERROR:"))
                message = Strings.rcopy(message, "ERROR:", false);
        }

        if (Strings.isEmpty(message))
            message = ex.getMessage();

        return message != null ? message.trim() : null;
    }

}
