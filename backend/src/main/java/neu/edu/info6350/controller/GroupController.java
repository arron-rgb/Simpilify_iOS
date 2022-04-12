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

  @GetMapping("")
  List<Group> getAll() {
    User info = userService.getInfo();
    return groupService.list(Wrappers.<Group>lambdaQuery().eq(Group::getUserId, info.getId()));
  }

  @DeleteMapping("/{id}")
  void remove(@PathVariable String id) {
    groupService.remove(Wrappers.<Group>lambdaQuery().eq(Group::getId, id));
  }

  @PostMapping("")
  Group create(Group group) {
    group.setUserId(userService.getId());
    groupService.save(group);
    return group;
  }

  @PutMapping("")
  Group update(Group group) {
    groupService.updateById(group);
    return group;
  }

  @GetMapping("/{id}")
  Group get(@PathVariable String id) {
    return groupService.getById(id);
  }

}
