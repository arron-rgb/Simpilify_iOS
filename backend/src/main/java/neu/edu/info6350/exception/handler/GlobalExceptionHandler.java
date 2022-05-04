package neu.edu.info6350.exception.handler;

import neu.edu.info6350.util.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import neu.edu.info6350.exception.MyRuntimeException;
import neu.edu.info6350.exception.PermissionException;
import neu.edu.info6350.exception.SchemeException;

/**
 * @author arronshentu
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public Result<String> internal(Exception e) {
    return Result.buildFail(e.getMessage());
  }

//  @ExceptionHandler(MyRuntimeException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public Result<String> unauthorized(Exception e) {
    return Result.buildFail(e.getMessage());
  }

   @ExceptionHandler(MyRuntimeException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Result<String> runtime(MyRuntimeException e) {
    return Result.buildFail(e.getMessage());
  }

  @ExceptionHandler(SchemeException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Result<String> schemaException(SchemeException e) {
    return Result.buildFail(e.getMessage());
  }

  @ExceptionHandler(PermissionException.class)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  public Result<String> permissionException(PermissionException e) {
    return Result.buildFail(e.getMessage());
  }

}
