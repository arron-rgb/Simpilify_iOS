package neu.edu.info6350.service.impl;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.cache.LoadingCache;

import lombok.extern.slf4j.Slf4j;
import neu.edu.info6350.exception.Messages;
import neu.edu.info6350.exception.MyRuntimeException;
import neu.edu.info6350.model.CustomUserDetails;
import neu.edu.info6350.model.User;
import neu.edu.info6350.model.dto.UserDto;
import neu.edu.info6350.repository.UserMapper;
import neu.edu.info6350.service.UserService;
import neu.edu.info6350.util.MailUtil;

/**
 * @author arronshentu
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService, UserDetailsService {

  @Value("${server.verify-endpoint}")
  String entryPoint;

  @Resource
  MailUtil mailUtil;

  @Override
  public User signUp(UserDto user) {
    User one = mapper.getOne(user.getEmail());
    if (one != null) {
      throw new MyRuntimeException(Messages.USER_EXIST);
    }
    one = new User();
    if (!StringUtils.equals(user.getPassword(), user.getRePassword())) {
      throw new MyRuntimeException(Messages.PASSWORD_DOES_NOT_EQUAL);
    }

    one.setCreatedTime(LocalDateTime.now());
    one.setUpdatedTime(LocalDateTime.now());

    String encode = encoder.encode(user.getPassword());
    one.setEmail(user.getEmail());
    one.setPassword(encode);
    one.setFirstName(user.getFirstName());
    one.setLastName(user.getLastName());
    one.setVerified(false);
    String randomAlphanumeric = RandomStringUtils.randomAlphanumeric(6);
    String url = entryPoint + randomAlphanumeric;
    log.info("{} register", user.getEmail());
    mailUtil.sendMail("Register Link", generateTemplate(url, user.getEmail()), user.getEmail());
    mapper.insert(one);
    localCache.put(randomAlphanumeric, one.getId());
    return one;
  }

  @Override
  public User getInfo() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (StringUtils.isEmpty(auth.getName()) || "anonymousUser".equals(auth.getName())) {
      throw new AuthenticationServiceException("Unauthorized");
    }

    return checkExist(auth.getName());
  }

  @Override
  public void verify(String token) {
    String userId = localCache.getIfPresent(token);
    if (StringUtils.isEmpty(userId)) {
      throw new MyRuntimeException("Link is not valid");
    }
    User user = mapper.selectById(userId);
    if (StringUtils.isEmpty(userId) || Objects.isNull(user)) {
      throw new MyRuntimeException("User does not exist");
    }
    if (user.getVerified()) {
      throw new MyRuntimeException("User has already activated");
    }
    user.setVerified(true);
    log.info("{} verify", user.getEmail());
    mapper.updateById(user);
  }

  @Resource
  LoadingCache<String, String> localCache;

  @Resource
  BCryptPasswordEncoder encoder;

  @Resource
  UserMapper mapper;

  @Override
  public User checkExist(String username) {
    if (StringUtils.isBlank(username)) {
      throw new MyRuntimeException("Username cannot be blank");
    }
    User one = mapper.getOne(username);
    if (one == null) {
      throw new MyRuntimeException("User does not exist");
    }
    return one;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return new CustomUserDetails(checkExist(username));
  }

  private String generateTemplate(String url, String to) {
    return "<h1>Example HTML Message Body</h1> <br>" + "        <p>Here is the link for your last registeration  <br>"
      + "        <a href=" + url + ">Click Here</a> <br>" + "        </p> <br>" + "        <p>Check your name is " + to
      + " <br>" + "        </p>";
  }
}
