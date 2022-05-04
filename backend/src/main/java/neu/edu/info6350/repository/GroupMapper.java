package neu.edu.info6350.repository;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import neu.edu.info6350.model.Group;

/**
 * @author arronshentu
 */
@Mapper
public interface GroupMapper extends BaseMapper<Group> {

  default Group getOneByUserId(String userId) {
    return selectOne(Wrappers.<Group>lambdaQuery().eq(Group::getUserId, userId));
  }

  default Group getOneById(String id) {
    return selectOne(Wrappers.<Group>lambdaQuery().eq(Group::getId, id));
  }

  default boolean exists(String id) {
    return exists(Wrappers.<Group>lambdaQuery().eq(Group::getId, id));
  }
}
