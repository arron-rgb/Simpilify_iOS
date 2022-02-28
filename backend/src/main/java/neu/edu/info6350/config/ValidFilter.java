package neu.edu.info6350.config;

import static com.baomidou.mybatisplus.core.toolkit.StringPool.UTF_8;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.log.LogMessage;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.www.BasicAuthenticationConverter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import neu.edu.info6350.repository.UserMapper;
import neu.edu.info6350.util.SpringContextHolder;

/**
 * @author arronshentu
 */
public class ValidFilter extends OncePerRequestFilter {

  private final BasicAuthenticationConverter authenticationConverter = new BasicAuthenticationConverter();

  UserMapper mapper;

  public ValidFilter() {
    this.mapper = SpringContextHolder.getBean(UserMapper.class);
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
    throws ServletException, IOException {
    UsernamePasswordAuthenticationToken authRequest = authenticationConverter.convert(request);
    if (authRequest == null) {
      logger.trace("Did not process authentication request since failed to find "
        + "username and password in Basic Authorization header");
      filterChain.doFilter(request, response);
      return;
    }
    String username = authRequest.getName();
    // User one = mapper.getOne(username);
    logger.trace(LogMessage.format("Found username '%s' in Basic Authorization header", username));
    // if (!one.isVerified()) {
    // logger.error(LogMessage.format("This account '%s' is inactive", username));
    // renderJson(response, Result.buildFailData("This account is inactive"), MediaType.APPLICATION_JSON_VALUE);
    // return;
    // }
    filterChain.doFilter(request, response);
  }

  ObjectMapper objectMapper = new ObjectMapper();

  public void renderJson(HttpServletResponse response, Object result, String contentType) {
    response.setCharacterEncoding(UTF_8);
    response.setContentType(contentType);
    try (PrintWriter out = response.getWriter()) {
      String str = objectMapper.writeValueAsString(result);
      logger.debug(LogMessage.format("renderJson %s", str));
      out.append(str);
    } catch (IOException e) {
      logger.error(e.getMessage(), e);
    }
  }
}
