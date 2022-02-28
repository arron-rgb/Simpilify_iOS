package neu.edu.info6350.repository;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import neu.edu.info6350.model.User;

/**
 * @author arronshentu
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

  default User getOne(String mail) {
    return selectOne(Wrappers.<User>lambdaQuery().eq(User::getEmail, mail));
  }

}
