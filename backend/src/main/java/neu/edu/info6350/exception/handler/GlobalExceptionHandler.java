package neu.edu.info6350.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import neu.edu.info6350.exception.MyRuntimeException;

/**
 * @author arronshentu
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MyRuntimeException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String unauthorized(Exception e) {
    return e.getMessage();
  }

}
