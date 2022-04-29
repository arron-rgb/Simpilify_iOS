package neu.edu.info6350.controller;

import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import lombok.Data;
import lombok.NoArgsConstructor;
import neu.edu.info6350.exception.MyRuntimeException;
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

  @PostMapping("/invite")
  String invite(@RequestBody InviteBody email) {
    groupService.invite(email.getEmail());
    return "success";
  }

  @Data
  @NoArgsConstructor
  static class InviteBody {
    String email;
  }

  @DeleteMapping("/{id}")
  void remove(@PathVariable String id) {
    groupService.remove(Wrappers.<Group>lambdaQuery().eq(Group::getId, id));
  }

  @PostMapping("")
  Group create(Group group) {
    List<Group> list = groupService.list(Wrappers.<Group>lambdaQuery().eq(Group::getUserId, userService.getId()));
    if (!list.isEmpty()) {
      throw new MyRuntimeException("You belong to a group");
    }

    group.setUserId(userService.getId());
    groupService.save(group);
    return group;
  }

  @PutMapping("")
  Group update(@RequestBody Group group) {
    if (groupService.getBaseMapper().exists(Wrappers.<Group>lambdaQuery().eq(Group::getId, group.getId()))) {
      throw new MyRuntimeException("Group does not exist");
    }
    if (Objects.isNull(group.getId())) {
      throw new MyRuntimeException("Group id cannot be null");
    }
    groupService.updateById(group);
    return group;
  }

  @GetMapping("/{id}")
  Group get(@PathVariable String id) {
    return groupService.getById(id);
  }

  @GetMapping("/my")
  Group getWhereAmI() {
    // todo if group is null
    return groupService.getOne(Wrappers.<Group>lambdaQuery().eq(Group::getUserId, userService.getId()));
  }

}
