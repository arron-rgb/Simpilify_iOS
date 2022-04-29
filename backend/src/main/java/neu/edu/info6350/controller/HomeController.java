package neu.edu.info6350.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import neu.edu.info6350.service.GroupService;
import neu.edu.info6350.service.UserService;

/**
 * @author arronshentu
 */

@RequestMapping("")
@RestController
public class HomeController {

  @GetMapping("verify")
  public String verify(String token) {
    userService.verify(token);
    return "success";
  }

  @GetMapping("join")
  public String joinGroup(String token) {
    groupService.acceptInvitation(token);
    return "success";
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
