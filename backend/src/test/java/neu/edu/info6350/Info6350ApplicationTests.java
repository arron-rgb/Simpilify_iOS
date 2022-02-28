package neu.edu.info6350;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import neu.edu.info6350.repository.UserMapper;

@SpringBootTest
class Info6350ApplicationTests {

  @Resource
  UserMapper mapper;

  @Test
  void contextLoads() {
    mapper.selectList(Wrappers.emptyWrapper());
    mapper.selectById(0L);
  }

}
