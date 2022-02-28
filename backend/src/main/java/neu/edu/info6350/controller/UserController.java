package neu.edu.info6350.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import neu.edu.info6350.model.User;
import neu.edu.info6350.model.dto.UserDto;
import neu.edu.info6350.service.UserService;

/**
 * @author arronshentu
 */
@RequestMapping("users")
@RestController
public class UserController {

  @Resource
  UserService userService;

  @PostMapping("/create")
  public User register(@RequestBody UserDto info) {
    User user = userService.signUp(info);
    return user;
  }

  @DeleteMapping("/remove")
  public void delete(String mail) {
    userService.remove(Wrappers.<User>lambdaQuery().eq(User::getEmail, mail));
  }

  @GetMapping("/self")
  public Object get() {
    return userService.getInfo();
  }

}
