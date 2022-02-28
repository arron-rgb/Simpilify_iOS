package neu.edu.info6350.service.impl;

import java.time.LocalDateTime;

import javax.annotation.Resource;

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

import lombok.extern.slf4j.Slf4j;
import neu.edu.info6350.exception.Messages;
import neu.edu.info6350.exception.MyRuntimeException;
import neu.edu.info6350.model.CustomUserDetails;
import neu.edu.info6350.model.User;
import neu.edu.info6350.model.dto.UserDto;
import neu.edu.info6350.repository.UserMapper;
import neu.edu.info6350.service.UserService;

/**
 * @author arronshentu
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService, UserDetailsService {

  @Override
  public User signUp(UserDto user) {
    User one = mapper.getOne(user.getEmail());
    if (one != null) {
      throw new MyRuntimeException(Messages.USER_EXIST);
    }
    one = new User();
    one.setCreatedTime(LocalDateTime.now());
    one.setUpdatedTime(LocalDateTime.now());

    String encode = encoder.encode(user.getPassword());
    one.setEmail(user.getEmail());
    one.setPassword(encode);
    one.setFirstName(user.getFirstName());
    one.setLastName(user.getLastName());
    mapper.insert(one);
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

  @Resource
  BCryptPasswordEncoder encoder;

  @Resource
  UserMapper mapper;

  User checkExist(String username) {
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
}
