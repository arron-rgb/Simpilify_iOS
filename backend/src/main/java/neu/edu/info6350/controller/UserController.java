package neu.edu.info6350.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import neu.edu.info6350.exception.MyRuntimeException;
import neu.edu.info6350.exception.PermissionException;
import neu.edu.info6350.exception.SchemeException;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import neu.edu.info6350.model.User;
import neu.edu.info6350.model.dto.UserDto;
import neu.edu.info6350.service.UserService;

import java.util.List;
import java.util.Objects;

/**
 * @author arronshentu
 */
@RequestMapping("users")
@RestController
public class UserController {

  @Resource
  UserService userService;

  @PostMapping("/create")
  public User register(@RequestBody @Valid UserDto info, Errors errors) {
    if (errors.hasErrors()) {
      throw new SchemeException(
        Objects.requireNonNull(errors.getFieldError()).getField() + " is not a well-formed field");
    }
    return userService.signUp(info);
  }

  @DeleteMapping("/{mail}")
  public void delete(@PathVariable String mail) {
    List<User> users = userService.list(Wrappers.<User>lambdaQuery().eq(User::getEmail, mail));
    if(users.isEmpty()){
      throw new MyRuntimeException("");
    }
    if(users.size() > 1){
      throw new MyRuntimeException("");
    }
    if(!users.get(0).getId().equals(userService.getId())){
      throw new PermissionException("");
    }
    userService.remove(Wrappers.<User>lambdaQuery().eq(User::getEmail, mail));
  }

  @GetMapping("/self")
  public Object get() {
    return userService.getInfo();
  }

}
