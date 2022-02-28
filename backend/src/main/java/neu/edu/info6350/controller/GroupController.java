package neu.edu.info6350.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import neu.edu.info6350.model.Group;
import neu.edu.info6350.model.User;
import neu.edu.info6350.service.GroupService;
import neu.edu.info6350.service.UserService;

/**
 * @author arronshentu
 */

@RequestMapping("groups")
@RestController
public class GroupController {

  @Resource
  UserService userService;
  @Resource
  GroupService groupService;

  @GetMapping("/all")
  List<Group> getAll() {
    User info = userService.getInfo();
    return groupService.list(Wrappers.<Group>lambdaQuery().eq(Group::getUserId, info.getId()));
  }

  @DeleteMapping("/remove")
  void remove(String id) {
    groupService.remove(Wrappers.<Group>lambdaQuery().eq(Group::getId, id));
  }

  @PostMapping("/create")
  Group create(Group group) {
    group.setUserId(userService.getId());
    groupService.save(group);
    return group;
  }

}
