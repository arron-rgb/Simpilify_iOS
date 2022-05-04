package neu.edu.info6350.controller;

import static neu.edu.info6350.exception.Messages.*;

import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import lombok.Data;
import lombok.NoArgsConstructor;
import neu.edu.info6350.exception.MyRuntimeException;
import neu.edu.info6350.model.Group;
import neu.edu.info6350.model.User;
import neu.edu.info6350.service.GroupService;
import neu.edu.info6350.service.UserService;
import neu.edu.info6350.util.Result;

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
  Result<String> invite(@RequestBody InviteBody email) {
    groupService.invite(email.getEmail());
    return Result.buildOkData(INVITE_SOMEONE);
  }

  @Data
  @NoArgsConstructor
  static class InviteBody {
    String email;
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  void remove(@PathVariable String id) {
    groupService.remove(Wrappers.<Group>lambdaQuery().eq(Group::getId, id));
  }

  @PostMapping("")
  Group create(Group group) {
    List<Group> list = groupService.list(Wrappers.<Group>lambdaQuery().eq(Group::getUserId, userService.getId()));
    if (!list.isEmpty()) {
      throw new MyRuntimeException(USER_CANNOT_JOIN_TWO_GROUPS);
    }
    group.setUserId(userService.getId());
    groupService.save(group);
    return group;
  }

  @PutMapping("")

  Group update(@RequestBody Group group) {
    if (Objects.isNull(group.getId())) {
      throw new MyRuntimeException(GROUP_ID_CANNOT_BE_NULL);
    }
    if (groupService.getBaseMapper().exists(Wrappers.<Group>lambdaQuery().eq(Group::getId, group.getId()))) {
      throw new MyRuntimeException(GROUP_DOES_NOT_EXIST);
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
    Group one = groupService.getOne(Wrappers.<Group>lambdaQuery().eq(Group::getUserId, userService.getId()), false);
    if (one == null) {
      throw new MyRuntimeException(DON_T_BELONG_TO_A_GROUP);
    }
    return one;
  }

}
