package neu.edu.info6350.service;

import com.baomidou.mybatisplus.extension.service.IService;

import neu.edu.info6350.model.User;
import neu.edu.info6350.model.dto.UserDto;

/**
 * @author arronshentu
 */
public interface UserService extends IService<User> {

  User signUp(UserDto user);

  User getInfo();

  default String getId() {
    return getInfo().getId();
  }

  /**
   * @param token
   */
  void verify(String token);

  User checkExist(String username);
}
