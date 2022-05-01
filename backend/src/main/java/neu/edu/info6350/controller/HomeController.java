package neu.edu.info6350.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import neu.edu.info6350.service.GroupService;
import neu.edu.info6350.service.UserService;
import neu.edu.info6350.util.Result;

/**
 * @author arronshentu
 */

@RequestMapping("")
@RestController
public class HomeController {

  @GetMapping("verify")
  public Result<String> verify(String token) {
    userService.verify(token);
    return Result.buildOk("success");
  }

  @GetMapping("join")
  public Result<String> joinGroup(String token) {
    groupService.acceptInvitation(token);
    return Result.buildOk("success");
  }

  @GetMapping
  public String index() {
    return "hello";
  }

  @Resource
  GroupService groupService;
  @Resource
  UserService userService;
}
