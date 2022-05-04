package neu.edu.info6350;

import java.util.concurrent.ExecutionException;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.cache.LoadingCache;

@SpringBootTest
class Info6350ApplicationTests {

  @Resource
  LoadingCache<String, String> localCache;
  // @Resource
  // UserMapper mapper;

  @Test
  void contextLoads() throws JsonProcessingException, ExecutionException {
    localCache.put("asd", "a");
    System.out.println(localCache.get("asd"));
    System.out.println(localCache.get("a"));
    // mapper.selectList(Wrappers.emptyWrapper());
    // mapper.selectById(0L);
    // Expense expense = new Expense();
    // expense.setExpenseDate(LocalDateTime.now());
    // expense.setDescription("Description");
    // expense.setName("Name");
    // expense.setCostInDollar(2.22);
    // expense.setCategory("1");
    // ObjectMapper mapper = new ObjectMapper();
    // mapper.registerModule(new JavaTimeModule());
    // mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    // System.out.println(mapper.writeValueAsString(expense));
  }

}
