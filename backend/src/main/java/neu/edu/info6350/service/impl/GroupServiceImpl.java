package neu.edu.info6350.service.impl;

import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.cache.LoadingCache;

import lombok.extern.slf4j.Slf4j;
import neu.edu.info6350.exception.MyRuntimeException;
import neu.edu.info6350.model.Group;
import neu.edu.info6350.model.User;
import neu.edu.info6350.repository.GroupMapper;
import neu.edu.info6350.service.GroupService;
import neu.edu.info6350.service.UserService;
import neu.edu.info6350.util.MailUtil;

/**
 * @author arronshentu
 */
@Slf4j
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group> implements GroupService {
  @Resource
  LoadingCache<String, String> localCache;
  @Value("${server.join-endpoint}")
  String endpoint;

  private void addUser(String groupId, String userId) {
    List<Group> groups = baseMapper.selectList(Wrappers.<Group>lambdaQuery().eq(Group::getUserId, userId));
    if (!groups.isEmpty()) {
      throw new MyRuntimeException("User cannot add two groups");
    }
    Group group = new Group();
    group.setUserId(userId);
    group.setId(groupId);
    baseMapper.insert(group);
    log.info("add {} into group {}", userId, group.getName());
    // 1. 创建group: 检查有无group 有则抛出异常
    // 2. 生成group链接：检查有无group，无则抛出异常，有则生成邀请链接
    // 2.1 邀请链接是发送给特定人的，其他人cannot access
    // 3. 点击邀请链接：1. 检查链接有效性，过期则拒绝 2. 有效则检查是否所属某个group 3. 有则拒绝 无则插入group
  }

  @Override
  public void invite(String email) {
    User user = userService.checkExist(email);
    String id = userService.getId();
    if (StringUtils.equals(user.getId(), id)) {
      throw new RuntimeException("You cannot invite yourself");
    }

    if (!user.getVerified()) {
      throw new RuntimeException("This user is still not verified");
    }
    Group group = getOne(Wrappers.<Group>lambdaQuery().eq(Group::getUserId, id));
    List<Group> list =
      list(Wrappers.<Group>lambdaQuery().eq(Group::getId, group.getId()).eq(Group::getUserId, user.getId()));
    if (!list.isEmpty()) {
      throw new RuntimeException("This user has already joined your group");
    }
    String invitationLink = generateInvitationLink();
    mailUtil.sendMail("Invitation", invitationLink, email);
  }

  @Resource
  MailUtil mailUtil;

  @Override
  public void acceptInvitation(String token) {
    Group currentUserGroup = baseMapper.getOneByUserId(userService.getId());
    String existGroupUserId = localCache.getIfPresent(token);
    if (StringUtils.isEmpty(existGroupUserId)) {
      throw new RuntimeException("Expired token");
    }
    if (StringUtils.equals(existGroupUserId, token)) {
      throw new RuntimeException("Invalid token");
    }
    Group existGroup = getOne(Wrappers.<Group>lambdaQuery().eq(Group::getUserId, existGroupUserId), false);
    if (Objects.nonNull(currentUserGroup) && StringUtils.equals(currentUserGroup.getId(), existGroup.getId())) {
      throw new RuntimeException("You have joined this group");
    }
    if (Objects.nonNull(currentUserGroup)) {
      throw new MyRuntimeException("You belong to another group");
    }
    addUser(existGroup.getId(), userService.getId());
    // 1. 某个用户生成邀请链接, 邀请链接设定ttl
    // 2. 用户b点击该链接
    // 3. 用户b登录
    // 4. 用户b加入该group
    // 5. todo中显示整个group的todos
  }

  @Resource
  UserService userService;

  private String generateInvitationLink() {
    String userId = userService.getId();
    String s = localCache.getIfPresent(userId);
    if (s != null) {
      throw new MyRuntimeException("link exists");
    }
    s = RandomStringUtils.randomAlphanumeric(6);;
    localCache.put(s, userId);
    log.info("generate a link {}{}", endpoint + s, s);
    return endpoint + s;
  }
}
