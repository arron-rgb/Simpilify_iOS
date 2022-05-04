package neu.edu.info6350.model;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author arronshentu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_user")
public class User {

  String id;
  String firstName;
  String lastName;
  String email;
  @JsonIgnore
  String password;

  Boolean verified;

  LocalDateTime createdTime;
  LocalDateTime updatedTime;

}
