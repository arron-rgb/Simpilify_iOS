package neu.edu.info6350.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author arronshentu
 */
@Data
@Accessors(chain = true)
public class Message {
  String from;
  String to;
  String content;
  String subject;
  String token;
}
