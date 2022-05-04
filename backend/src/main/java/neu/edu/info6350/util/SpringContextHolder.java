package neu.edu.info6350.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Lazy(false)
public class SpringContextHolder implements ApplicationContextAware {

  private static ApplicationContext applicationContext = null;

  public static ApplicationContext getApplicationContext() {
    return applicationContext;
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) {
    SpringContextHolder.applicationContext = applicationContext;
  }

  @SuppressWarnings("unchecked")
  public static <T> T getBean(String name) {
    return (T)applicationContext.getBean(name);
  }

  /**
   * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
   */
  public static <T> T getBean(Class<T> requiredType) {
    return applicationContext.getBean(requiredType);
  }

  /**
   * 清除SpringContextHolder中的ApplicationContext为Null.
   */
  public static void clearHolder() {
    if (log.isDebugEnabled()) {
      log.debug("清除SpringContextHolder中的ApplicationContext:" + applicationContext);
    }
    applicationContext = null;
  }

}
