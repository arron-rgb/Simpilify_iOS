package neu.edu.info6350.config;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * @author arronshentu
 */
@Component
public class Cache {

  CacheLoader<String, String> loader = new CacheLoader<String, String>() {
    @Override
    public String load(@NotNull String key) {
      return key.toUpperCase();
    }
  };

  @Bean(name = "localCache")
  public LoadingCache<String, String> localCache() {
    return CacheBuilder.newBuilder().expireAfterWrite(5, TimeUnit.MINUTES).build(loader);
  }

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    // Cache cache = new Cache();
    // LoadingCache<String, String> local = cache.localCache();
    // local.put("test", "asd");
    // String test = local.get("test");
    // System.out.println(test);
    // Scanner kb = new Scanner(System.in);
    // String a = kb.next();
    // System.out.println(a);
    // System.out.println(local.get("test"));
    // local.put("test", "asd");
    // System.out.println(local.get("test11"));
    System.out.println(RandomStringUtils.randomAlphanumeric(5));
    System.out.println(RandomStringUtils.randomAlphanumeric(5));
    System.out.println(RandomStringUtils.randomAlphanumeric(5));
  }

}
