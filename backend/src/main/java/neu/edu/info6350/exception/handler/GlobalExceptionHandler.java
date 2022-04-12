package neu.edu.info6350.exception.handler;

import neu.edu.info6350.exception.PermissionException;
import neu.edu.info6350.exception.SchemeException;
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
  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public String internal(Exception e) {
    return e.getMessage();
  }

  @ExceptionHandler(MyRuntimeException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String unauthorized(Exception e) {
    return e.getMessage();
  }


  @ExceptionHandler(SchemeException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String schemaException(Exception e) {
    return e.getMessage();
  }

  @ExceptionHandler(PermissionException.class)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  public String permissionException(Exception e) {
    return e.getMessage();
  }


}
