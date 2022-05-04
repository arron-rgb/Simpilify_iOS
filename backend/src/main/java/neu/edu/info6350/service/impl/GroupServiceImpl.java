package neu.edu.info6350.service.impl;

import static neu.edu.info6350.exception.Messages.*;

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

  /**
   *
   * @param groupId
   * @param userId
   */
  private void addUser(String groupId, String userId) {
    List<Group> groups = baseMapper.selectList(Wrappers.<Group>lambdaQuery().eq(Group::getUserId, userId));
    if (!groups.isEmpty()) {
      throw new MyRuntimeException(USER_CANNOT_JOIN_TWO_GROUPS);
    }
    Group group = new Group();
    group.setUserId(userId);
    group.setId(groupId);
    baseMapper.insert(group);
    log.info("add {} into group {}", userId, group.getName());
  }

  @Override
  public void invite(String email) {
    User user = userService.checkExist(email);
    String id = userService.getId();
    if (StringUtils.equals(user.getId(), id)) {
      throw new RuntimeException(CANNOT_INVITE_YOURSELF);
    }
    if (!user.getVerified()) {
      throw new RuntimeException(NOT_VERIFIED);
    }
    Group group = getOne(Wrappers.<Group>lambdaQuery().eq(Group::getUserId, id));
    List<Group> list =
      list(Wrappers.<Group>lambdaQuery().eq(Group::getId, group.getId()).eq(Group::getUserId, user.getId()));
    if (!list.isEmpty()) {
      throw new RuntimeException(ALREADY_JOINED_YOUR_GROUP);
    }
    String invitationLink = generateInvitationLink();

    mailUtil.sendMail(invitation, invitationLink, email);
  }

  @Value("${email.invitation.subject}")
  String invitation = "Invitation";

  @Resource
  MailUtil mailUtil;

  @Override
  public void acceptInvitation(String token) {
    Group currentUserGroup = baseMapper.getOneByUserId(userService.getId());
    String existGroupUserId = localCache.getIfPresent(token);
    if (StringUtils.isEmpty(existGroupUserId)) {

      throw new RuntimeException(EXPIRED_TOKEN);
    }
    if (StringUtils.equals(existGroupUserId, token)) {

      throw new RuntimeException(INVALID_TOKEN);
    }
    Group existGroup = getOne(Wrappers.<Group>lambdaQuery().eq(Group::getUserId, existGroupUserId), false);
    if (Objects.nonNull(currentUserGroup) && StringUtils.equals(currentUserGroup.getId(), existGroup.getId())) {

      throw new RuntimeException(JOINED_THIS_GROUP);
    }
    if (Objects.nonNull(currentUserGroup)) {

      throw new MyRuntimeException(YOU_BELONG_TO_ANOTHER_GROUP);
    }
    addUser(existGroup.getId(), userService.getId());
  }

  @Resource
  UserService userService;

  private String generateInvitationLink() {
    String userId = userService.getId();
    String s = localCache.getIfPresent(userId);
    if (s != null) {
      throw new MyRuntimeException(LINK_EXISTS);
    }
    s = RandomStringUtils.randomAlphanumeric(6);;
    localCache.put(s, userId);
    log.info("generate a link {}{}", endpoint + s, s);
    return endpoint + s;
  }
}
